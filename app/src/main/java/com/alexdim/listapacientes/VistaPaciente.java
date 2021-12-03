package com.alexdim.listapacientes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VistaPaciente extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private ListView listaContactos;
    private item_paciente_adapter adapter;
    private List<Post> listaPost;//ArrayList<Paciente> lista;

    //private SwipeRefreshLayout swipeRefreshLayout=null;
    private SwipeRefreshLayout SwipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        //Buscar en el layoult
        SwipeLayout = findViewById(R.id.swiperefresh);
        // Agregando un listenner
        SwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Codigo de seguridad
                Toast.makeText(getApplicationContext(), "Actualizar!!", Toast.LENGTH_LONG).show();
                // Espera por la animacion 3 segundo
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Para la animacion por 2 segundos
                        SwipeLayout.setRefreshing(false);
                    }
                }, 2000); // Delay in millis
            }
        });

        listaPost = new ArrayList<Post>();

        //obtenerListaApi();

        adapter = new item_paciente_adapter(listaPost);

        listaContactos = findViewById(R.id.ListViewPaciente);

        listaContactos.setAdapter(adapter);

        listaContactos.setOnItemClickListener(this);

    }
    /*private void obtenerListaApi(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService postService = retrofit.create(ApiService.class);
        Call< List<Post> > call = postService.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                for(Post post :  response.body()) {
                    listaPost.add(post);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }*/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(VistaPaciente.this, HistorialClinico.class);
        intent.putExtra("KEY_ID", listaPost.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {

    }
}
