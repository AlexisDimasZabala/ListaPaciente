package com.alexdim.listapacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    private Button btnMenuNuevo,btnMenuBuscar,btnMenuActivar;

    private EditText plMenuDocumento;

    private TextView txtLoginNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().hide();

        findViewById();//vistas de activity

        CargarPreferencia(); // Carga el usuario en sharedpreference

        btnMenuNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, nuevo_paciente.class);
                startActivity(intent);
            }
        });

        btnMenuBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(plMenuDocumento.getText().toString())){
                    plMenuDocumento.setError("Ingrese documento valido");
                }else {
                    String Documento = plMenuDocumento.getText().toString();
                    Intent intent = new Intent(Menu.this, HistorialClinico.class);
                    intent.putExtra("EXTRA_DOCUMENTO", Documento);
                    startActivity(intent);
                }
            }
        });

        btnMenuActivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(plMenuDocumento.getText().toString())){
                    plMenuDocumento.setError("Ingrese documento valido");
                }else {
                    //voy a la activity activar
                    String Documento = plMenuDocumento.getText().toString();
                    Intent intent = new Intent(Menu.this, ActivarPaciente.class);
                    intent.putExtra("EXTRA_DOCUMENTO", Documento);
                    startActivity(intent);
                }
            }
        });
    }

    private void findViewById(){
        btnMenuBuscar = findViewById(R.id.btnMenuBuscar);
        btnMenuNuevo = findViewById(R.id.btnMenuNuevo);
        btnMenuActivar = findViewById(R.id.btnMenuActivar);

        plMenuDocumento = findViewById(R.id.plRegiPacNombre);
        txtLoginNombre = findViewById(R.id.txtLoginPrincipal);
    }

    private void CargarPreferencia(){
        SharedPreferences preferences = getSharedPreferences
                ("Credenciales", Context.MODE_PRIVATE);

        String user=preferences.getString("usuario","Nombre");

        txtLoginNombre.setText("Especialista " + user);
    }
}