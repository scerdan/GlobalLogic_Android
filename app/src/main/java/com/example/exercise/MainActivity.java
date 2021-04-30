package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.exercise.interfaces.Service;
import com.example.exercise.models.NotebookModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    ArrayList<NotebookModel> notebookModels = new ArrayList<>();
    private NotebookAdapter notebookAdapter;
    private RecyclerView note_recyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        note_recyclerview = (RecyclerView) findViewById(R.id.rv_Card);
        note_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        getNotebooksResponse();
    }

    private void getNotebooksResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://private-f0eea-mobilegllatam.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Call<List<NotebookModel>> call = service.obtenerListado();

        call.enqueue(new Callback<List<NotebookModel>>() {
            @Override
            public void onResponse(Call<List<NotebookModel>> call, Response<List<NotebookModel>> response) {
                notebookModels = new ArrayList<>(response.body());
                notebookAdapter = new NotebookAdapter(MainActivity.this, notebookModels);
                note_recyclerview.setAdapter(notebookAdapter);
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<NotebookModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}