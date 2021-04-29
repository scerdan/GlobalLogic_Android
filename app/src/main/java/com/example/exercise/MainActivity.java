package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.exercise.interfaces.Service;
import com.example.exercise.models.Notebooks;
import com.example.exercise.util.ConnectionRest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    // LLENAR LISTVIEW CON LOS DATOS DEL GET
    // ABRIR FRAGMENT CUANDO SE SELECCIONE UN ELEMENTO
    Retrofit retrofit;
    TextView tv_Title;
    TextView tv_SubTitle;
    ImageView iv_Img;
    ListView listadoView;

    ArrayAdapter<String> arrayAdapter = null;
    String[] BOX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listadoView = (ListView) findViewById(R.id.lv_listadoView);
        tv_Title = (TextView) findViewById(R.id.tv_title);
        tv_SubTitle = (TextView) findViewById(R.id.tv_SubTitle);
        iv_Img = (ImageView) findViewById(R.id.iv_Img);


        CallAPI();
    }

    private void CallAPI() {
        Service service = ConnectionRest.getConnetion().create(Service.class);

        Call<List<Notebooks>> listado = service.obtenerListado();
        listado.enqueue(new Callback<List<Notebooks>>() {
            @Override
            public void onResponse(Call<List<Notebooks>> call, Response<List<Notebooks>> response) {
                Log.i("200", "200");
                List<Notebooks> listado = response.body();

                for (int i = 0; i < listado.size(); i++) {
                    String name = listado.get(i).getTitle();
                    String desc = listado.get(i).getDescription();
                    String img = listado.get(i).getImage();

                    BOX = new String[]{name, desc, img};

//                    Log.i("ver", String.valueOf(listado));
                    Log.i("box", Arrays.toString(BOX));
//                    System.out.println(itemsName);
                }
                cargarADAPTER();

            }

            @Override
            public void onFailure(Call<List<Notebooks>> call, Throwable t) {
                Log.i("400", "400" + t.getMessage());
            }
        });
    }

    private void cargarADAPTER() {
        if (BOX != null) {
            arrayAdapter = new ArrayAdapter<>(this, R.layout.item , BOX);
            listadoView.setAdapter(arrayAdapter);
        }
    }
}