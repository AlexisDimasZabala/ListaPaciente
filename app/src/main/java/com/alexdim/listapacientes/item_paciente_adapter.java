package com.alexdim.listapacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class item_paciente_adapter extends BaseAdapter {

    //private List<Paciente> listaPaciente;
    private List<Post> listaPaciente;

    public item_paciente_adapter(List<Post> listaContacto) {
        this.listaPaciente = listaContacto;
    }

    //public item_paciente_adapter(List<Paciente> listaContacto) {
    //    this.listaPaciente = listaContacto;
    //}

    @Override
    public int getCount() {
        return listaPaciente.size();
    }

    @Override
    public Post getItem(int position) {
        return listaPaciente.get(position);
    }

    /*
    @Override
    public Paciente getItem(int position) {
        return listaPaciente.get(position);
    }*/
    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }
/*
    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista;

        if(convertView == null){
            vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_paciente_adapter, parent, false);
        }
        else{
            vista = convertView;
        }

        //Paciente item = getItem(position);
        Post item = getItem(position);

        TextView txtNombrePaciente = vista.findViewById(R.id.txtNombre);
        TextView txtTelefonoPaciente = vista.findViewById(R.id.txtTelefono);
        TextView txtEdadPaciente = vista.findViewById(R.id.txtEdad);
        TextView txtGrupoSanguineoPaciente = vista.findViewById(R.id.txtGrupSang);
        TextView txtVacCovidPaciente = vista.findViewById(R.id.txtVacunaCovid);
        TextView txtRiesgoPaciente = vista.findViewById(R.id.txtRiesgo);

        txtNombrePaciente.setText(item.getNombre());
        txtTelefonoPaciente.setText(item.getTelefono());
        txtEdadPaciente.setText(String.valueOf(item.getEdad()));//Error String.valueOf(int) se necesita
        txtGrupoSanguineoPaciente.setText(item.getGrupoSanguineo());
        txtVacCovidPaciente.setText(item.getVacCovid());
        txtRiesgoPaciente.setText(item.getRiesgo());

/*
        String nombreCompleto;
        nombreCompleto = item.getNombre() + " " + item.getApellido();

        txtNombrePaciente.setText(nombreCompleto);
        txtTelefonoPaciente.setText(item.getTelefono());
        txtEdadPaciente.setText(String.valueOf(item.getEdad()));
        txtGrupoSanguineoPaciente.setText(item.getGrupoSanguineo());
        txtVacCovidPaciente.setText(item.getVacCovid());
        txtRiesgoPaciente.setText(item.getRiesgo());*/

        return vista;
    }

}