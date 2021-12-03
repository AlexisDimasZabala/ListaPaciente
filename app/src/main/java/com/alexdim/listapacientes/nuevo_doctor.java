package com.alexdim.listapacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class nuevo_doctor extends AppCompatActivity {

    private EditText correo,nombre,apellido,contra,contraR;

    private Button regreso,completar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_doctor);

        getSupportActionBar().hide();

        findViewById();

        regreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nuevo_doctor.this, Login.class);
                startActivity(intent);
            }
        });

        completar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompletarDatos();
            }
        });
    }

    private void CompletarDatos(){
        //compruebo el correo ingresado
        final String compruebaemail = correo.getEditableText().toString().trim();
        final String regex = "(?:[^<>()\\[\\].,;:\\s@\"]+(?:\\.[^<>()\\[\\].,;:\\s@\"]+)*|\"[^\\n\"]+\")@(?:[^<>()\\[\\].,;:\\s@\"]+\\.)+[^<>()\\[\\]\\.,;:\\s@\"]{2,63}";

        if (!compruebaemail.matches(regex))
        {
            correo.setError("Email incorrecto");
        }else {
            //si el correo es correcto compruebo la contraseña sea identica
            String c=contra.getText().toString(),c1=contraR.getText().toString();
            if ( c.equals(c1)) {
                //guardo los datos de sesion en la base de datos
                grabarDatos();
            }
            else {
                Toast.makeText(nuevo_doctor.this, "Las contraseñas no coincide", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void findViewById(){
        //EditText
        correo = findViewById(R.id.plRegiDocCorreo);
        nombre = findViewById(R.id.plRegiDocNombre);
        apellido = findViewById(R.id.plRegiDocApellido);
        contra = findViewById(R.id.plRegiDocContra);
        contraR = findViewById(R.id.plRegiDocContraR);

        //btn
        regreso = findViewById(R.id.btnActivarPersona);
        completar = findViewById(R.id.btnRegisDocCompletar);
    }

    private void grabarDatos(){
        ApiService api = RetrofitInstance.getInstance().create(ApiService.class);

        Call<Integer> call = null;

        String Nombre=nombre.getText().toString(),
                Apellido=apellido.getText().toString(),
                Correo=correo.getText().toString(),
                Contra=contraR.getText().toString();

        //asignamos alumno a editar/agregar
        Doctor doctor = new Doctor();
        doctor.setIdDoctor(0);
        doctor.setNombre(Nombre);
        doctor.setApellido(Apellido);
        doctor.setEmail(Correo);
        doctor.setContra(Contra);
        //doctor.setEspecialidad();
        doctor.setActivo(1);

        call = api.agregarDoctor(doctor);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Toast.makeText(nuevo_doctor.this, "Registrado con exito", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(nuevo_doctor.this, Login.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(nuevo_doctor.this, "Error: "+ t, Toast.LENGTH_LONG).show();
            }
        });
    }
}