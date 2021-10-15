package com.alexdim.listapacientes;

import com.google.gson.annotations.SerializedName;

public class User {

    public static final String URL_BASE = "http://192.168.1.110:5001";

    @SerializedName("nombre")
    String mName;

    @SerializedName("contra")
    String mContra;

    public User(String nombre, String contra) {
        this.mName = nombre;
        this.mContra = contra;
    }
}
/*
    private String nombre;
    private String contra;


    public User(String nombre, String contra) {
        this.nombre = nombre;
        this.contra = contra;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre +
                ", contra: " + contra;
    }*/
