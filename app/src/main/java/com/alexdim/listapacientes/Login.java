package com.alexdim.listapacientes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private TextView txtError;
    private EditText plUsuario;
    private EditText plContra;
    private Button btnIngresar;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        getSupportActionBar().hide();

        txtError = findViewById(R.id.txtLoginError);

        plUsuario = findViewById(R.id.plRegiPacNombre);
        plContra = findViewById(R.id.plLoginContra);

        btnIngresar = findViewById(R.id.btnActivarPersona);

        btnRegistrar = findViewById(R.id.btnRegisDocCompletar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(validarDatos()){
                    String email=plUsuario.getText().toString();
                    String password=plContra.getText().toString();
                    verificarLoginApi(email, password);
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, nuevo_doctor.class);
                startActivity(intent);
            }
        });
    }

    private boolean validarDatos(){

        if(plUsuario.getText().toString().isEmpty()){
            plUsuario.setError("dato obligatorio");
            return false;
        }

        if(plContra.getText().toString().isEmpty()){
            plContra.setError("dato obligatorio");
            return false;
        }
        return true;
    }

    private void verificarLoginApi(String nombre, String contra){

        ApiService api = RetrofitInstance.getInstance().create(ApiService.class);
        Call<String> call = api.Login(nombre,contra);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.code()==200 && !response.body().isEmpty()){
                    logueado();
                }else {
                    incorrecto();
                    //txtError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("API", "onFailure: error interno de validación de API" );
                Toast.makeText(Login.this, "No funciono", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void logueado(){
        GuardarPreferencia(); // gaurda el usuario en sharedpreference
        Toast.makeText(Login.this, "Bienvenido " + plUsuario.getText().toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Login.this, Menu.class);
        startActivity(intent);
    }

    private void incorrecto(){
        plUsuario.setError("Usuario incorrecto");
        plContra.setError("Contraseña incorrecta");
    }

    private void GuardarPreferencia(){
        SharedPreferences preferences = getSharedPreferences
                ("Credenciales", Context.MODE_PRIVATE);

        String nombre = plUsuario.getText().toString();

        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("usuario",nombre);

        editor.commit();

    }

}
