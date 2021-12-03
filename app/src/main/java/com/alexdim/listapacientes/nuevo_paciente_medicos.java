package com.alexdim.listapacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class nuevo_paciente_medicos extends AppCompatActivity {

    private Button btnIngresarPaciente;
    private Spinner spiGrupoSang,spiRiesgo,spiVacCovid,spiAlcohol,spiTabaco,spiDrogas,
            spiInfusiones,spiRespiratorio,spiNeurologico,spiQuirurgico,spiAlergias;

    private String Nombre,Apellido,Edad,Domicilio,Email,Documento,FotoPaciente;

    private int puntos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_paciente_medicos);

        getSupportActionBar().hide();

        obetenerDatos(); // datos desde nuevo_paciente

        setSpinners(); //declaro spinners - lista de datos seleccionables

        btnIngresarPaciente = findViewById(R.id.btnActivarPersona);

        btnIngresarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sacarRating();//saco el rating segun datos ingresados
                urlPersona();//Le asigno una Foto Url aleatoria
                grabarDatos();//Guardo el paciente ingresado en la base de datos
            }
        });
    }

    private void obetenerDatos(){
        //obtengo datos de la clase nuevo_paciente
        Bundle extras = getIntent().getExtras();
        Nombre = extras.getString("EXTRA_NOMBRE");
        Apellido = extras.getString("EXTRA_APELLIDO");
        Edad = extras.getString("EXTRA_EDAD");
        Domicilio = extras.getString("EXTRA_DOMICILIO");
        Email = extras.getString("EXTRA_EMAIL");
        Documento = extras.getString("EXTRA_DOCUMENTO");
    }

    private void grabarDatos(){
        // definimos llamada al API
        ApiService api = RetrofitInstance.getInstance().create(ApiService.class);

        Call<Integer> call = null;

        //asignamos alumno a editar/agregar
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(0);
        paciente.setNombre(Nombre);
        paciente.setApellido(Apellido);
        paciente.setEdad(Integer.valueOf(Edad));
        paciente.setGrupoSanguineo(spiGrupoSang.getSelectedItem().toString());
        paciente.setRiesgo(spiRiesgo.getSelectedItem().toString());
        paciente.setVacCovid(spiVacCovid.getSelectedItem().toString());
        paciente.setEmail(Email);
        //paciente.setTelefono("2954");
        paciente.setFoto(FotoPaciente);
        paciente.setRating(String.valueOf(puntos));
        paciente.setDomicilio(Domicilio);
        paciente.setAlcohol(spiAlcohol.getSelectedItem().toString());
        paciente.setTabaco(spiTabaco.getSelectedItem().toString());
        paciente.setDrogas(spiDrogas.getSelectedItem().toString());
        paciente.setInfusiones(spiInfusiones.getSelectedItem().toString());
        paciente.setRespiratorio(spiRespiratorio.getSelectedItem().toString());
        paciente.setNeurologico(spiNeurologico.getSelectedItem().toString());
        paciente.setQuirurgico(spiQuirurgico.getSelectedItem().toString());
        paciente.setAlergias(spiAlergias.getSelectedItem().toString());
        paciente.setActivo(1);
        paciente.setDocumento(Documento);

        call = api.agregarPaciente(paciente);

        /*if (this.ID_ACTUAL == 0) {
            // estamos agragando nuevo registro
        } else {
            // estamos editando registro
            alumno.setIdalumno(this.ID_ACTUAL);
            alumno.setActivo(chkDetalleFavorito.isChecked() ? 1 : 0);
            call = api.editarAlumno(alumno);
        }*/

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Toast.makeText(nuevo_paciente_medicos.this, "Paciente Guardado", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(nuevo_paciente_medicos.this, Menu.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(nuevo_paciente_medicos.this, "Error: "+ t, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sacarRating(){
        if (spiRiesgo.getSelectedItem().toString() == "Si") {puntos +=1;}
        if (spiTabaco.getSelectedItem().toString() == "Habitual") {puntos +=1;}
        if (spiDrogas.getSelectedItem().toString() == "Habitual") {puntos +=1;}
        if (spiRespiratorio.getSelectedItem().toString() != "Sin Datos") {puntos +=1;}
        if (spiAlergias.getSelectedItem().toString() != "Sin Datos") {puntos +=1;}
    }

    private void urlPersona(){
        int minimo=0,maximo=10;

        String[] arr ={"https://lorempixel.com/400/400/people/0/",
                        "https://lorempixel.com/400/400/people/1/",
                        "https://lorempixel.com/400/400/people/2/",
                        "https://lorempixel.com/400/400/people/3/",
                        "https://lorempixel.com/400/400/people/4/",
                        "https://lorempixel.com/400/400/people/5/",
                        "https://lorempixel.com/400/400/people/6/",
                        "https://lorempixel.com/400/400/people/7/",
                        "https://lorempixel.com/400/400/people/8/",
                        "https://lorempixel.com/400/400/people/9/",
                        "https://lorempixel.com/400/400/people/10/"};

        int indiceAleatorio = ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
        FotoPaciente = arr[indiceAleatorio];
    }

    private void setSpinners(){
        spiGrupoSang=findViewById(R.id.spiRegistroGrupoSanguineo);
        spiRiesgo=findViewById(R.id.spiRegistroRiesgo);
        spiVacCovid=findViewById(R.id.spiRegistroVacCovid);
        spiAlcohol=findViewById(R.id.spiRegistroAlcohol);
        spiTabaco=findViewById(R.id.spiRegistroTabaco);
        spiDrogas=findViewById(R.id.spiRegistroDrogas);
        spiInfusiones=findViewById(R.id.spiRegistroInfusiones);
        spiRespiratorio=findViewById(R.id.spiRegistroRespiratorio);
        spiNeurologico=findViewById(R.id.spiRegistroNeurologico);
        spiQuirurgico=findViewById(R.id.spiRegistroQuirurgico);
        spiAlergias=findViewById(R.id.spiRegistroAlergias);

        ArrayAdapter<CharSequence>adapterGrupoSang=ArrayAdapter.createFromResource(this, R.array.GrupoSanguineo, android.R.layout.simple_spinner_item);
        adapterGrupoSang.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adapterRiesgo=ArrayAdapter.createFromResource(this, R.array.Riesgo, android.R.layout.simple_spinner_item);
        adapterRiesgo.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adapterVacCovid=ArrayAdapter.createFromResource(this, R.array.VacCovid, android.R.layout.simple_spinner_item);
        adapterVacCovid.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adapterAlcohol=ArrayAdapter.createFromResource(this, R.array.Alcohol, android.R.layout.simple_spinner_item);
        adapterAlcohol.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adapterTabaco=ArrayAdapter.createFromResource(this, R.array.Tabaco, android.R.layout.simple_spinner_item);
        adapterTabaco.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adapterDrogas=ArrayAdapter.createFromResource(this, R.array.Drogas, android.R.layout.simple_spinner_item);
        adapterDrogas.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adapterInfusiones=ArrayAdapter.createFromResource(this, R.array.Infusiones, android.R.layout.simple_spinner_item);
        adapterInfusiones.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adapterRespiratorio=ArrayAdapter.createFromResource(this, R.array.Respiratorio, android.R.layout.simple_spinner_item);
        adapterRespiratorio.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adapterNeurologico=ArrayAdapter.createFromResource(this, R.array.Neurologico, android.R.layout.simple_spinner_item);
        adapterNeurologico.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adapterQuirurgico=ArrayAdapter.createFromResource(this, R.array.Quirurgico, android.R.layout.simple_spinner_item);
        adapterQuirurgico.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adapterAlergias=ArrayAdapter.createFromResource(this, R.array.Alergias, android.R.layout.simple_spinner_item);
        adapterAlergias.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spiGrupoSang.setAdapter(adapterGrupoSang);
        spiRiesgo.setAdapter(adapterRiesgo);
        spiVacCovid.setAdapter(adapterVacCovid);
        spiAlcohol.setAdapter(adapterAlcohol);
        spiTabaco.setAdapter(adapterTabaco);
        spiDrogas.setAdapter(adapterDrogas);
        spiInfusiones.setAdapter(adapterInfusiones);
        spiRespiratorio.setAdapter(adapterRespiratorio);
        spiNeurologico.setAdapter(adapterNeurologico);
        spiQuirurgico.setAdapter(adapterQuirurgico);
        spiAlergias.setAdapter(adapterAlergias);
    }
}