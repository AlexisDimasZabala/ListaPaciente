package com.alexdim.listapacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistorialClinico extends AppCompatActivity {

    //Array lista contacto
    private ListView listaContactos;
    private ArrayList<Paciente> lista;

    //datos Personales
    private TextView CodPaciente;
    private TextView nombre;
    private TextView apellido;
    private TextView email;
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

    private TextView activo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_clinico);

        //Declaro Texto a subrayar
        TextView txtDP = findViewById(R.id.txtDPSub);
        TextView txtAP = findViewById(R.id.txtAPSub);
        //Span de ambos textos para subrayar
        SpannableString textoDP = new SpannableString("Datos personales:");
        textoDP.setSpan(new UnderlineSpan(), 0, textoDP.length(), 0);
        txtDP.setText(textoDP);
        SpannableString textoAP = new SpannableString("Antecedentes personales:");
        textoAP.setSpan(new UnderlineSpan(), 0, textoAP.length(), 0);
        txtAP.setText(textoAP);

        Bundle extras = getIntent().getExtras();
        int pacienteId = extras.getInt("KEY_ID");

        findViewsById();

        cargarPost(pacienteId);

/*
        //Creo la lista de datos para recibir
        Intent mIntent = getIntent();
        String[] lista ;
        lista = mIntent.getStringArrayExtra("ListaHistorial");

        //seteo los datos con el orden que se creo el array
        nombre.setText(lista[0]);
        apellido.setText(lista[1]);
        edad.setText(lista[2]);
        grupSang.setText(lista[3]);
        telefono.setText(lista[4]);
        riesgo.setText(lista[5]);
        vacCovid.setText(lista[6]);
        direccion.setText(lista[7]);
        alcohol.setText(lista[8]);
        tabaco.setText(lista[9]);
        drogas.setText(lista[10]);
        infusiones.setText(lista[11]);
        respiratorio.setText(lista[12]);
        neurologico.setText(lista[13]);
        quirurgicos.setText(lista[14]);
        alergias.setText(lista[15]);
        //Log.d("My etiqueta de nombre",lista[0]);*/


    }

    private void findViewsById(){
        //Datos Personales
        nombre = findViewById(R.id.txtNombreHistorial);
        apellido = findViewById(R.id.txtApellidoHistorial);
        telefono = findViewById(R.id.txtTelefonoHistorial);
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

    private void cargarPost(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService postService = retrofit.create(ApiService.class);
        Call<Post> call = postService.getPostById(id);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                Post post = (Post) response.body();

                //Datos Personales
                //codPaciente.setText(post.getcodPaciente());  //falta en la vista
                nombre.setText(post.getNombre());
                apellido.setText(post.getApellido());
                telefono.setText(post.getTelefono());
                //textViewEmailDetalle.setText(post.getEmail()); //falta en la vista
                edad.setText(String.valueOf(post.getEdad()));//post.getEdad()); hacia falta conversion a int
                grupSang.setText(post.getGrupoSanguineo());
                telefono.setText(post.getTelefono());
                riesgo.setText(post.getRiesgo());
                vacCovid.setText(post.getVacCovid());
                domicilio.setText(post.getDomicilio());

                //int id, activo)
                //Antecedentes
                alcohol.setText(post.getAlcohol());
                tabaco.setText(post.getTabaco());
                drogas.setText(post.getDrogas());
                infusiones.setText(post.getInfusiones());
                respiratorio.setText(post.getRespiratorio());
                neurologico.setText(post.getNeurologico());
                quirurgicos.setText(post.getQuirurgico());
                alergias.setText(post.getAlergias());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            }
        });
    }
/*
        call.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                Paciente paciente = (Paciente) response.body();

                //Datos Personales
                nombre.setText(paciente.getNombre());
                apellido.setText(paciente.getApellido());
                telefono.setText(paciente.getTelefono());
                grupSang.setText(paciente.getGrupoSanguineo());
                direccion.setText(paciente.getDireccion());
                edad.setText(paciente.getEdad());
                vacCovid.setText(paciente.getVacCovid());
                riesgo.setText(paciente.getRiesgo());

                //Antecedentes personales
                alcohol.setText(paciente.getAlcohol());
                tabaco.setText(paciente.getTabaco());
                drogas.setText(paciente.getDrogas());
                infusiones.setText(paciente.getInfusiones());
                respiratorio.setText(paciente.getRespiratorio());
                neurologico.setText(paciente.getNeurologico());
                quirurgicos.setText(paciente.getQuirurgico());
                alergias.setText(paciente.getAlergias());

                /*textViewNombreDetalle.setText(post.getNombre());
                textViewEmailDetalle.setText(post.getEmail());
                textViewDomicilioDetalle.setText(post.getDomicilio());
                textViewTelefonoDetalle.setText(post.getTelefono());

                //textViewTituloDetalle.setText(post.getTitle());
                //textViewContenidoDetalle.setText(post.getBody());
            }

            @Override
            public void onFailure(Call<Paciente> call, Throwable t) {
            }
        //});
    }*/


    /*

    SpannableString mitextoU = new SpannableString("Mamut chiquito");
    mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
    textView.setText(mitextoU);*/
}