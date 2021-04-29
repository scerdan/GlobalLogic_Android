package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.exercise.interfaces.Service;
import com.example.exercise.models.Notebooks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    // CREAR EL LIST VIEW PERSONALIZADO
    // LLENAR LISTVIEW CON LOS DATOS DEL GET
    // ABRIR FRAGMENT CUANDO SE SELECCIONE UN ELEMENTO
    Retrofit retrofit;
    TextView tv_Title;
    TextView tv_SubTitle;
    ImageView iv_Img;
    ListView listadoView;
    ArrayList<String> itemNames;
    ArrayList<String> itemDesc;
    ArrayList<Integer> itemImgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listadoView = (ListView) findViewById(R.id.lv_listadoView);
        tv_Title = (TextView) findViewById(R.id.tv_title);
        tv_SubTitle = (TextView) findViewById(R.id.tv_SubTitle);
        iv_Img = (ImageView) findViewById(R.id.iv_Img);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://private-f0eea-mobilegllatam.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ObtainData();
        if (itemNames != null) {
            MyAdapter(itemNames);
        }

    }

    private void ObtainData() {
        Service service = retrofit.create(Service.class);
        Call<List<Notebooks>> listado = service.obtenerListado();
        listado.enqueue(new Callback<List<Notebooks>>() {
            @Override
            public void onResponse(Call<List<Notebooks>> call, Response<List<Notebooks>> response) {
                Log.i("200", "200");
                List<Notebooks> listado = response.body();
                itemNames = new ArrayList<>();
                itemDesc = new ArrayList<>();
                itemImgs = new ArrayList<>();
                for (int i = 0; i < 30; i++) {
                    String noteTitle = listado.get(i).getTitle();
                    String noteDesc = listado.get(i).getDescription();
                    String noteImg = listado.get(i).getImage();

                    itemNames.add(noteTitle);
                    itemDesc.add(noteDesc);
//                    itemImgs.add(noteImg);
                    Log.i("ver", noteTitle + " " + noteDesc + " " + noteImg);
                }

            }

            @Override
            public void onFailure(Call<List<Notebooks>> call, Throwable t) {
                Log.i("400", "400" + t.getMessage());
            }
        });
    }

    private void MyAdapter(ArrayList<String> title) {
//CARGO EJEMPLO DE LIST VIEW
        MyAdapter adapter = new MyAdapter(this, title);
        listadoView.setAdapter(adapter);
    }

}