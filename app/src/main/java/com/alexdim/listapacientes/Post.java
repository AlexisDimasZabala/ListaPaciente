package com.alexdim.listapacientes;

public class Post {

    //Foto Paciente
    private String foto;
    //rating
    private float rating;

    //Datos Personales
    private int id;
    private int CodPaciente;
    private String nombre;
    private String apellido;
    private String email;
    private int edad;
    private String grupoSanguineo;
    private String telefono;
    private String riesgo;
    private String vacCovid;

    //Datos Historial
    private String domicilio;
    private String alcohol;
    private String tabaco;
    private String drogas;
    private String infusiones;
    private String respiratorio;
    private String neurologico;
    private String quirurgico;
    private String alergias;

    public Post(int id, int codPaciente, String nombre, String apellido, String email, int edad, String grupoSanguineo, String telefono, String riesgo, String vacCovid, String domicilio, String alcohol, String tabaco, String drogas, String infusiones, String respiratorio, String neurologico, String quirurgico, String alergias, float rating ,String foto) {
        this.id = id;
        this.CodPaciente = codPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.edad = edad;
        this.grupoSanguineo = grupoSanguineo;
        this.telefono = telefono;
        this.riesgo = riesgo;
        this.vacCovid = vacCovid;
        this.domicilio = domicilio;
        this.alcohol = alcohol;
        this.tabaco = tabaco;
        this.drogas = drogas;
        this.infusiones = infusiones;
        this.respiratorio = respiratorio;
        this.neurologico = neurologico;
        this.quirurgico = quirurgico;
        this.alergias = alergias;
        this.rating = rating;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodPaciente() {
        return CodPaciente;
    }

    public void setCodPaciente(int codPaciente) {
        CodPaciente = codPaciente;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() { return edad; }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}