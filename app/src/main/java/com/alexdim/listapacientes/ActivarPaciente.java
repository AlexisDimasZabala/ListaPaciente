package com.alexdim.listapacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivarPaciente extends AppCompatActivity {

    private TextView txtActivarDocumento,txtActivarNombre,txtActivarApellido;

    private Button btnActivarPersona;

    private ImageView imgActivarPaciente;

    private String Documento, nombre, apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activar_paciente);

        getSupportActionBar().hide();
        findViewById();
        ReciboDatos();

        btnActivarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabarDatos();
            }
        });
    }

    public void findViewById() {
        //textos
        txtActivarDocumento = findViewById(R.id.txtActivarDocumento);
        txtActivarNombre = findViewById(R.id.txtActivarNombre);
        txtActivarApellido = findViewById(R.id.txtActivarApellido);
        //bottones activar persona
        btnActivarPersona = findViewById(R.id.btnActivarPersona);
        //imagen de la persona a activar
        imgActivarPaciente = findViewById(R.id.imgActivarPaciente);
    }

    private void ReciboDatos(){
        Bundle extras = getIntent().getExtras();
        Documento = extras.getString("EXTRA_DOCUMENTO");
        cargarDatosDesdeApi(Documento);
    }

    private void cargarDatosDesdeApi(String Documento) {
        final Paciente item = new Paciente();

        ApiService api = RetrofitInstance.getInstance().create(ApiService.class);
        Call<Paciente> call = api.ListarPorDocumento(Documento);

        call.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                //Toast.makeText(ActivarPaciente.this, "vuelta " + Documento, Toast.LENGTH_SHORT).show();
                if (response.code() == 404){
                    Intent intent = new Intent(ActivarPaciente.this, Menu.class);
                    Toast.makeText(ActivarPaciente.this, "Persona inexistente", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }else
                {
                    //Datos para insertar en la vista
                    if (response.body().getDocumento() == null) {
                        Intent intent = new Intent(ActivarPaciente.this, Menu.class);
                        Toast.makeText(ActivarPaciente.this, "Paciente inexistente", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    } else {
                        nombre = response.body().getNombre();
                        apellido = response.body().getApellido();

                        txtActivarNombre.setText(nombre);
                        txtActivarApellido.setText(apellido);
                        txtActivarDocumento.setText(Documento);

                        //Foto y rating paciente
                        Picasso.get()
                                .load(response.body().getFoto())
                                .error(R.mipmap.ic_launcher_round)
                                .into(imgActivarPaciente);
                    }
                }
            }

            @Override
            public void onFailure(Call<Paciente> call, Throwable t) {
                Toast.makeText(ActivarPaciente.this, "Fallo envio a Api", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void grabarDatos(){
        // definimos llamada al API
        ApiService api = RetrofitInstance.getInstance().create(ApiService.class);

        Call<Integer> call = null;

        call = api.ActivarPaciente(Documento);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.code() == 200){
                    Toast.makeText(ActivarPaciente.this, "Activado Con Exito", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ActivarPaciente.this, Menu.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ActivarPaciente.this, "Hubo un error", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(ActivarPaciente.this, "Error: "+ t, Toast.LENGTH_LONG).show();
            }
        });
    }
}