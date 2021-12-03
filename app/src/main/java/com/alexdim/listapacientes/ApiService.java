package com.alexdim.listapacientes;

import java.util.List;

import okhttp3.Callback;
import okhttp3.MediaType;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("Doctor/IniciarSesion")
    Call<String> Login(@Query("nombre") String nombre, @Query("contra") String contra);

    @POST("Doctor/AgregarDoctor")
    Call<Integer> agregarDoctor(@Body Doctor doctor);

    @GET("Paciente/ListarPorDocumento/{documento}")
    Call<Paciente> ListarPorDocumento(@Path("documento") String Documento);

    @POST("Paciente/AgregarPaciente")
    Call<Integer> agregarPaciente(@Body Paciente paciente);

    @PUT("Paciente/EditarPaciente")
    Call<Integer> EditarPaciente(@Body Paciente paciente);

    @PUT("Paciente/Activar/{documento}")
    Call<Integer> ActivarPaciente(@Path("documento") String Documento);

    @PUT("Paciente/Desactivar/{documento}")
    Call<Integer> DesactivarPaciente(@Path("documento") String Documento);

    //@PUT("alumno/editar")
    //Call<Integer> editarAlumno(@Body Paciente alumno);

    //@GET("alumno/listartodos")
    //Call<List<Paciente>> ListarTodos();

    //@GET("alumno/listarfavoritos")
    //Call<List<Paciente>> ListarFavoritos(@Header("Authorization") String token);

}