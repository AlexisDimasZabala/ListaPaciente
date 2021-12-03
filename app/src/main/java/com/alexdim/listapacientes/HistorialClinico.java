package com.alexdim.listapacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorialClinico extends AppCompatActivity {

    //Array lista contacto
    private ListView listaContactos;
    private ArrayList<Paciente> lista;

    //Foto y rating Paciente
    private ImageView foto;
    private RatingBar ratingBar;

    //datos Personales
    private TextView nombre;
    private TextView apellido;
    private TextView edad;
    private TextView grupSang;
    private TextView telefono;
    private TextView riesgo;
    private TextView vacCovid;

    //Antecendentes personales
    private TextView domicilio;
    private TextView alcohol;
    private TextView tabaco;
    private TextView drogas;
    private TextView infusiones;
    private TextView respiratorio;
    private TextView neurologico;
    private TextView quirurgicos;
    private TextView alergias;

    private String email,fotoStr,documento;
    private int id_paciente;
    private int activo;

    private Button btnHistorialEditar,btnEliminarPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_clinico);

        getSupportActionBar().hide();

        //Declaro Texto a subrayar
        TextView txtDP = findViewById(R.id.txtDPSub);
        TextView txtAP = findViewById(R.id.txtAPSub);
        TextView txtRT = findViewById(R.id.txtRatingBar);
        //Span de textos para subrayar
        SpannableString textoDP = new SpannableString("Datos personales:");
        textoDP.setSpan(new UnderlineSpan(), 0, textoDP.length(), 0);
        txtDP.setText(textoDP);
        SpannableString textoAP = new SpannableString("Antecedentes personales:");
        textoAP.setSpan(new UnderlineSpan(), 0, textoAP.length(), 0);
        txtAP.setText(textoAP);
        SpannableString textoRT = new SpannableString("Prioridad:");
        textoAP.setSpan(new UnderlineSpan(), 0, textoAP.length(), 0);
        txtAP.setText(textoRT);

        Bundle extras = getIntent().getExtras();
        String Documento = extras.getString("EXTRA_DOCUMENTO");
        cargarDatosDesdeApi(Documento);

        findViewsById();

        btnHistorialEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditarHistorial();
            }
        });

        btnEliminarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EliminarPaciente(Documento);
            }
        });
    }

    private void EditarHistorial(){
        Intent intent = new Intent(HistorialClinico.this, Editar_historial_clinico.class);
        intent.putExtra("EXTRA_IDPACIENTE", id_paciente);
        intent.putExtra("EXTRA_DOCUMENTO", documento);
        intent.putExtra("EXTRA_EMAIL", email);
        intent.putExtra("EXTRA_FOTO", fotoStr);
        intent.putExtra("EXTRA_EDAD", edad.getText().toString());
        intent.putExtra("EXTRA_NOMBRE", nombre.getText().toString());
        intent.putExtra("EXTRA_TELEFONO", telefono.getText().toString());
        intent.putExtra("EXTRA_GRUPSANG", grupSang.getText().toString());
        intent.putExtra("EXTRA_DOMICILIO", domicilio.getText().toString());
        intent.putExtra("EXTRA_APELLIDO", apellido.getText().toString());
        intent.putExtra("EXTRA_VACCOVID", vacCovid.getText().toString());
        intent.putExtra("EXTRA_RIESGO", riesgo.getText().toString());//aca
        intent.putExtra("EXTRA_ALCOHOL", alcohol.getText().toString());
        intent.putExtra("EXTRA_TABACO", tabaco.getText().toString());
        intent.putExtra("EXTRA_DROGAS", drogas.getText().toString());
        intent.putExtra("EXTRA_INFUSIONES", infusiones.getText().toString());
        intent.putExtra("EXTRA_RESPIRATORIO", respiratorio.getText().toString());
        intent.putExtra("EXTRA_NEUROLOGICO", neurologico.getText().toString());
        intent.putExtra("EXTRA_QUIRURGICO", quirurgicos.getText().toString());
        intent.putExtra("EXTRA_ALERGIAS", alergias.getText().toString());
        startActivity(intent);
    }

    private void findViewsById(){
        //btn editar
        btnHistorialEditar = findViewById(R.id.btnActivarPersona);
        btnEliminarPersona = findViewById(R.id.btnHistorialEliminarPersona);

        //foto y rating paciente
        foto = findViewById(R.id.imgActivarPaciente);
        ratingBar = findViewById(R.id.ratingHistorialPrioridad);

        //Datos Personales
        nombre = findViewById(R.id.txtNombreHistorial);
        apellido = findViewById(R.id.txtApellidoHistorial);
        telefono = findViewById(R.id.txtActivarDocumento);
        grupSang = findViewById(R.id.txtGrupoSangHistorial);
        domicilio = findViewById(R.id.txtDireccionHistorial);
        edad = findViewById(R.id.txtEdadHistorial);
        vacCovid = findViewById(R.id.txtVacCovHistorial);
        riesgo = findViewById(R.id.txtRiesgoHistorial);

        //Antecedentes personales
        alcohol = findViewById(R.id.txtAlcoholHistorial);
        tabaco = findViewById(R.id.txtTabacoHistorial);
        drogas = findViewById(R.id.txtDrogasHistorial);
        infusiones = findViewById(R.id.txtInfusionesHistorial);
        respiratorio = findViewById(R.id.txtRespiratorioHistorial);
        neurologico = findViewById(R.id.txtNeurologicoHistorial);
        quirurgicos = findViewById(R.id.txtQuirurgicoHistorial);
        alergias = findViewById(R.id.txtAlergiasHistorial);
    }

    private void cargarDatosDesdeApi(String Documento) {
        final Paciente item = new Paciente();

        ApiService api = RetrofitInstance.getInstance().create(ApiService.class);
        Call<Paciente> call = api.ListarPorDocumento(Documento);

        call.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                if (response.code() == 404){
                    Intent intent = new Intent(HistorialClinico.this, Menu.class);
                    Toast.makeText(HistorialClinico.this, "Persona inexistente", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }else
                {
                    if (response.body() == null || response.body().getActivo() == 0) {
                        Intent intent = new Intent(HistorialClinico.this, Menu.class);
                        Toast.makeText(HistorialClinico.this, "Persona Inactiva", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                    documento = response.body().getDocumento();
                    id_paciente = response.body().getIdPaciente();
                    email = response.body().getEmail();
                    activo = response.body().getActivo();
                    fotoStr = response.body().getFoto();
                    nombre.setText(response.body().getNombre());
                    apellido.setText(response.body().getApellido());
                    edad.setText(String.valueOf(response.body().getEdad()));
                    grupSang.setText(response.body().getGrupoSanguineo());
                    riesgo.setText(response.body().getRiesgo());
                    vacCovid.setText(response.body().getVacCovid());
                    //email.setText(response.body().getEmail());
                    telefono.setText(response.body().getTelefono());
                    domicilio.setText(response.body().getDomicilio());
                    alcohol.setText(response.body().getAlcohol());
                    tabaco.setText(response.body().getTabaco());
                    drogas.setText(response.body().getDrogas());
                    infusiones.setText(response.body().getInfusiones());
                    respiratorio.setText(response.body().getRespiratorio());
                    neurologico.setText(response.body().getNeurologico());
                    quirurgicos.setText(response.body().getQuirurgico());
                    alergias.setText(response.body().getAlergias());

                    //Foto y rating paciente
                    Picasso.get()
                            .load(response.body().getFoto())
                            .error(R.mipmap.ic_launcher_round)
                            .into(foto);
                    float rating = Float.parseFloat(response.body().getRating());
                    ratingBar.setRating(rating);
                }
            }

            @Override
            public void onFailure(Call<Paciente> call, Throwable t) {
                Toast.makeText(HistorialClinico.this, "Fallo envio a Api", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void EliminarPaciente(String Documento){
        // definimos llamada al API
        ApiService api = RetrofitInstance.getInstance().create(ApiService.class);

        Call<Integer> call = null;

        call = api.DesactivarPaciente(Documento);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.code() == 200){
                    Toast.makeText(HistorialClinico.this, "Eliminado Con Exito", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(HistorialClinico.this, Menu.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(HistorialClinico.this, "Hubo un error", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(HistorialClinico.this, "Error: "+ t, Toast.LENGTH_LONG).show();
            }
        });
    }


}