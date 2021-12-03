package com.alexdim.listapacientes;

public class Paciente {

    //Datos Personales
    private int idPaciente;
    private String nombre;
    private String apellido;
    private int edad;
    private String grupoSanguineo;
    private String riesgo;
    private String vacCovid;
    private String email;
    private String telefono;
    private String foto;
    private String rating;
    private String domicilio;
    private String alcohol;
    private String tabaco;
    private String drogas;
    private String infusiones;
    private String respiratorio;
    private String neurologico;
    private String quirurgico;
    private String alergias;
    private int activo;
    private String documento;

    public Paciente() {
    }

    public Paciente(int idPaciente, String nombre, String apellido, int edad, String grupoSanguineo, String riesgo, String vacCovid, String email, String telefono, String foto, String rating, String domicilio, String alcohol, String tabaco, String drogas, String infusiones, String respiratorio, String neurologico, String quirurgico, String alergias, int activo, String documento) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.grupoSanguineo = grupoSanguineo;
        this.riesgo = riesgo;
        this.vacCovid = vacCovid;
        this.email = email;
        this.telefono = telefono;
        this.foto = foto;
        this.rating = rating;
        this.domicilio = domicilio;
        this.alcohol = alcohol;
        this.tabaco = tabaco;
        this.drogas = drogas;
        this.infusiones = infusiones;
        this.respiratorio = respiratorio;
        this.neurologico = neurologico;
        this.quirurgico = quirurgico;
        this.alergias = alergias;
        this.activo = activo;
        this.documento = documento;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public String getVacCovid() {
        return vacCovid;
    }

    public void setVacCovid(String vacCovid) {
        this.vacCovid = vacCovid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getTabaco() {
        return tabaco;
    }

    public void setTabaco(String tabaco) {
        this.tabaco = tabaco;
    }

    public String getDrogas() {
        return drogas;
    }

    public void setDrogas(String drogas) {
        this.drogas = drogas;
    }

    public String getInfusiones() {
        return infusiones;
    }

    public void setInfusiones(String infusiones) {
        this.infusiones = infusiones;
    }

    public String getRespiratorio() {
        return respiratorio;
    }

    public void setRespiratorio(String respiratorio) {
        this.respiratorio = respiratorio;
    }

    public String getNeurologico() {
        return neurologico;
    }

    public void setNeurologico(String neurologico) {
        this.neurologico = neurologico;
    }

    public String getQuirurgico() {
        return quirurgico;
    }

    public void setQuirurgico(String quirurgico) {
        this.quirurgico = quirurgico;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}