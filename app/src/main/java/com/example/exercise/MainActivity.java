package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.exercise.interfaces.Service;
import com.example.exercise.models.Notebooks;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    // CREAR METODO QUE CONSULTE EL API
    // CREAR EL LIST VIEW PERSONALIZADO
    // LLENAR LISTVIEW CON LOS DATOS DEL GET
    // ABRIR FRAGMENT CUANDO SE SELECCIONE UN ELEMENTO
    Retrofit retrofit;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn1);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://private-f0eea-mobilegllatam.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ObtainData();
    }

    private void ObtainData() {
        Service service = retrofit.create(Service.class);
        Call<List<Notebooks>> listado = service.obtenerListado();
        listado.enqueue(new Callback<List<Notebooks>>() {
            @Override
            public void onResponse(Call<List<Notebooks>> call, Response<List<Notebooks>> response) {
                Log.i("200","200");
                List<Notebooks> listado = response.body();

                for (int i = 0; i < listado.size(); i++) {
                    String noteTitle = listado.get(i).getTitle();
                    String noteDesc = listado.get(i).getDescription();
                    String noteImg = listado.get(i).getImage();

                    Log.i("ver", noteTitle+noteDesc+noteImg);
                    }
            }

            @Override
            public void onFailure(Call<List<Notebooks>> call, Throwable t) {
                Log.i("400","400"+t.getMessage());
            }
        });
    }

}