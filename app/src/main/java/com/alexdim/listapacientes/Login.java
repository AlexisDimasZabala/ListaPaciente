package com.alexdim.listapacientes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Login extends AppCompatActivity {

    private TextView txtError;
    private EditText plUsuario;
    private EditText plContra;
    private Button btnIngresar;
    //private Button btnRegistrar;

    //private int id;
    private String nombre;
    private String contra;

    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        getSupportActionBar().hide();

        Intent intent = getIntent();

        //id = intent.getIntExtra("EXTRA_IDREGISTRO",0);
        //usuario = intent.getStringExtra("EXTRA_USUARIO");
        //contra = intent.getStringExtra("EXTRA_CONTRA");

        txtError = findViewById(R.id.txtLoginError);

        plUsuario = findViewById(R.id.plLoginCorreo);
        plContra = findViewById(R.id.plLoginContra);

        btnIngresar = findViewById(R.id.btnLoginIngresar);

        //btnRegistrar = findViewById(R.id.btnLoginRegistrarse);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    private void login() {
        /*Intent intent = new Intent(Login.this,VistaPaciente.class);
        startActivity(intent);*/

        //verificar usuario y contraseña
        nombre= plUsuario.getText().toString();
        contra= plContra.getText().toString();

        if (!(nombre.equals("") && !contra.equals("")))
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(User.URL_BASE)
                    //.addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(ApiService.class);

            createUser();
        }
    }

    private void createUser(){

        User user = new User(nombre,contra);
        Call<User> call = apiService.post(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //Guardo la respuesta de la api
                List<User> userlist = new ArrayList<>();
                userlist.add(response.body());

                //respuesta de la api
                //Toast.makeText(Login.this, response.code()+ " Response", Toast.LENGTH_SHORT).show();

                if (response.isSuccessful()){
                    if(response.body() != null){
                        Intent intent = new Intent(Login.this,VistaPaciente.class);
                        startActivity(intent);
                    }
                    else {
                        txtError.setText("Usuario o contraseña incorrecta");
                        Toast.makeText(Login.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Login.this, "Sin conneccion a la api", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
