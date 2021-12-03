package com.alexdim.listapacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editar_historial_clinico extends AppCompatActivity {

    //pasados desde Historial Clinico
    private String Nombre,Telefono,GrupSang,Domicilio,Apellido,
            VacCovid,Riesgo,Edad,Email,FotoStr,Documento,
            Alcohol,Tabaco,Drogas,Infusiones,Respiratorio,Neurologico,
            Quirurgico,Alergias;
    private int Id_paciente;

    //Cambiar los datos
    private EditText EdtNombre,EdtTelefono,EdtDomicilio,EdtApellido,EdtEdad;

    //Settear los datos spinner
    private Spinner spiGrupoSang,spiRiesgo,spiVacCovid,spiAlcohol,spiTabaco,spiDrogas,
            spiInfusiones,spiRespiratorio,spiNeurologico,spiQuirurgico,spiAlergias;

    //btnGuardado de la edicion
    private Button btnGuardar;

    //puntos de rating de la persona al editar
    private int puntos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_historial_clinico);

        getSupportActionBar().hide();

        ObtengoDatos();//datos de la activity para editar
        setSpinners();//spinners de seleccion de datos
        BuscarVistas();//findViewById
        MostrarDatos();//Muestro los datos de texto recibidos

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sacarRating();
                grabarDatos();
            }
        });
    }

    private void ObtengoDatos(){
        //obtengo datos desde Historial CLinico
        Bundle extras = getIntent().getExtras();
        Id_paciente = extras.getInt("EXTRA_IDPACIENTE");
        Email = extras.getString("EXTRA_EMAIL");
        Documento = extras.getString("EXTRA_DOCUMENTO");
        FotoStr = extras.getString("EXTRA_FOTO");
        Nombre = extras.getString("EXTRA_NOMBRE");
        Telefono = extras.getString("EXTRA_TELEFONO");
        GrupSang = extras.getString("EXTRA_GRUPSANG");
        Domicilio = extras.getString("EXTRA_DOMICILIO");
        Apellido= extras.getString("EXTRA_APELLIDO");
        VacCovid= extras.getString("EXTRA_VACCOVID");
        Riesgo= extras.getString("EXTRA_RIESGO");
        Edad = extras.getString("EXTRA_EDAD");
        Alcohol = extras.getString("EXTRA_ALCOHOL");
        Tabaco = extras.getString("EXTRA_TABACO");
        Drogas = extras.getString("EXTRA_DROGAS");
        Infusiones = extras.getString("EXTRA_INFUSIONES");
        Respiratorio= extras.getString("EXTRA_RESPIRATORIO");
        Neurologico= extras.getString("EXTRA_NEUROLOGICO");
        Quirurgico= extras.getString("EXTRA_QUIRURGICO");
        Alergias = extras.getString("EXTRA_ALERGIAS");
    }

    private void grabarDatos(){
        // definimos llamada al API
        ApiService api = RetrofitInstance.getInstance().create(ApiService.class);

        Call<Integer> call = null;

        //asignamos alumno a editar/agregar
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(Id_paciente);
        paciente.setNombre(EdtNombre.getText().toString());
        paciente.setApellido(EdtApellido.getText().toString());
        paciente.setEdad(Integer.valueOf(EdtEdad.getText().toString()));
        paciente.setGrupoSanguineo(spiGrupoSang.getSelectedItem().toString());
        paciente.setRiesgo(spiRiesgo.getSelectedItem().toString());
        paciente.setVacCovid(spiVacCovid.getSelectedItem().toString());
        paciente.setEmail(Email);
        paciente.setTelefono(EdtTelefono.getText().toString());
        paciente.setFoto(FotoStr);
        paciente.setRating(String.valueOf(puntos));
        paciente.setDomicilio(EdtDomicilio.getText().toString());
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

        call = api.EditarPaciente(paciente);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Toast.makeText(Editar_historial_clinico.this, "Guardado con exito", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Editar_historial_clinico.this, Menu.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(Editar_historial_clinico.this, "Error: "+ t, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sacarRating(){
        if (spiRiesgo.getSelectedItem().toString().equals("Si")) {puntos +=1;}
        if (spiTabaco.getSelectedItem().toString().equals("Habitual")) {puntos +=1;}
        if (spiDrogas.getSelectedItem().toString().equals("Habitual")) {puntos +=1;}
        if (!"Sin Datos".equals(spiRespiratorio.getSelectedItem().toString())) {puntos +=1;}
        if (!"Sin Datos".equals(spiAlergias.getSelectedItem().toString())) {puntos +=1;}
    }

    private void MostrarDatos(){
        EdtNombre.setText(Nombre);
        EdtApellido.setText(Apellido);
        EdtDomicilio.setText(Domicilio);
        EdtEdad.setText(Edad);
        EdtTelefono.setText(Telefono);
    }

    private void BuscarVistas(){
        //btn
        btnGuardar = findViewById(R.id.btnActivarPersona);
        //edittext
        EdtNombre = findViewById(R.id.edtEditarNombre);
        EdtTelefono = findViewById(R.id.edtEditarTelefono);
        EdtDomicilio = findViewById(R.id.edtEditarDireccion);
        EdtApellido = findViewById(R.id.edtEditarApellido);
        EdtEdad = findViewById(R.id.edtEditarEdad);
    }

    private void setSpinners(){
        //busco los sppinner en la vista
        spiGrupoSang=findViewById(R.id.spiEditarGrupSang);
        spiRiesgo=findViewById(R.id.spiEditarRiesgo);
        spiVacCovid=findViewById(R.id.spiEditarVacCovid);
        spiAlcohol=findViewById(R.id.spiEditarAlcohol);
        spiTabaco=findViewById(R.id.spiEditarTabaco);
        spiDrogas=findViewById(R.id.spiEditarDrogas);
        spiInfusiones=findViewById(R.id.spiEditarInfusiones);
        spiRespiratorio=findViewById(R.id.spiEditarRespiratorio);
        spiNeurologico=findViewById(R.id.spiEditarNeurologico);
        spiQuirurgico=findViewById(R.id.spiEditarQuirugico);
        spiAlergias=findViewById(R.id.spiEditarAlergias);

        //busco la lista para cada uno de los spinners
        ArrayAdapter<CharSequence> adapterGrupoSang=ArrayAdapter.createFromResource(this, R.array.GrupoSanguineo, android.R.layout.simple_spinner_item);
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

        //seteo la lista a cada spinners
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

        //Seteo los spinners como estan en la BD
        //spiGrupoSang.setSelection();
        spiGrupoSang.setSelection(getIndex(spiGrupoSang, GrupSang),true);
        spiRiesgo.setSelection(getIndex(spiRiesgo, Riesgo),true);
        spiVacCovid.setSelection(getIndex(spiVacCovid, VacCovid),true);
        spiAlcohol.setSelection(getIndex(spiAlcohol, Alcohol),true);
        spiTabaco.setSelection(getIndex(spiTabaco, Tabaco),true);
        spiDrogas.setSelection(getIndex(spiDrogas, Drogas),true);
        spiInfusiones.setSelection(getIndex(spiInfusiones, Infusiones),true);
        spiRespiratorio.setSelection(getIndex(spiRespiratorio, Respiratorio),true);
        spiNeurologico.setSelection(getIndex(spiNeurologico, Neurologico),true);
        spiQuirurgico.setSelection(getIndex(spiQuirurgico, Quirurgico),true);
        spiAlergias.setSelection(getIndex(spiAlergias, Alergias),true);
    }

    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }
}