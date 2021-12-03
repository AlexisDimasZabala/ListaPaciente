package com.alexdim.listapacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class nuevo_paciente extends AppCompatActivity {

    private EditText nombre, apellido,edad, domicilio, email, documento;

    private Button datosMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_paciente);

        //saco barra superior de aplicacion
        getSupportActionBar().hide();

        findViewById();

        datosMedicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasajeAMedicos();
            }
        });
    }

    private void findViewById(){
        nombre = findViewById(R.id.plRegiPacNombre);
        apellido = findViewById(R.id.plRegiPacApellido);
        edad = findViewById(R.id.plRegiDocCorreo);
        domicilio = findViewById(R.id.plRegiPacDomicilio);
        email = findViewById(R.id.plRegiPacEmail);
        documento = findViewById(R.id.plRegiPacDocumento);
        datosMedicos = findViewById(R.id.btnActivarPersona);
    }

    private void pasajeAMedicos(){
        //compruebo el correo ingresado
        final String compruebaemail = email.getEditableText().toString().trim();
        final String regex = "(?:[^<>()\\[\\].,;:\\s@\"]+(?:\\.[^<>()\\[\\].,;:\\s@\"]+)*|\"[^\\n\"]+\")@(?:[^<>()\\[\\].,;:\\s@\"]+\\.)+[^<>()\\[\\]\\.,;:\\s@\"]{2,63}";

        if (!compruebaemail.matches(regex))
        {
            email.setError("Email incorrecto");
        }else {
            //Guardo los datos ingresados en STRign
            String Nombre = nombre.getText().toString(),
                    Apellido = apellido.getText().toString(),
                    Edad = edad.getText().toString(),
                    Domicilio = domicilio.getText().toString(),
                    Email = email.getText().toString(),
                    Documento = documento.getText().toString();

            Toast.makeText(nuevo_paciente.this, "Success", Toast.LENGTH_SHORT).show();
            //Empiezo la activity y envio los datos obtenidos
            Intent intent = new Intent(nuevo_paciente.this, nuevo_paciente_medicos.class);
            intent.putExtra("EXTRA_NOMBRE", Nombre);
            intent.putExtra("EXTRA_APELLIDO", Apellido);
            intent.putExtra("EXTRA_EDAD", Edad);
            intent.putExtra("EXTRA_DOMICILIO", Domicilio);
            intent.putExtra("EXTRA_EMAIL", Email);
            intent.putExtra("EXTRA_DOCUMENTO", Documento);
            startActivity(intent);
        }
    }
}