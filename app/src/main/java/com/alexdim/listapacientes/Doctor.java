package com.alexdim.listapacientes;

public class Doctor {

    public int IdDoctor;
    public String Nombre;
    public String Apellido;
    public String Email;
    public String Especialidad;
    public String Contra;
    public int Activo;

    public Doctor() {
    }

    public Doctor(int idDoctor, String nombre, String apellido, String email, String especialidad, String contra, int activo) {
        IdDoctor = idDoctor;
        Nombre = nombre;
        Apellido = apellido;
        Email = email;
        Especialidad = especialidad;
        Contra = contra;
        Activo = activo;
    }

    public int getIdDoctor() {
        return IdDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        IdDoctor = idDoctor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String especialidad) {
        Especialidad = especialidad;
    }

    public String getContra() {
        return Contra;
    }

    public void setContra(String contra) {
        Contra = contra;
    }

    public int getActivo() {
        return Activo;
    }

    public void setActivo(int activo) {
        Activo = activo;
    }
}
