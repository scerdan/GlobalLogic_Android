package com.example.exercise;

;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class SecondActivity extends AppCompatActivity {
    ImageView imageView_Detalle;
    TextView title_Detalle;
    TextView desc_Detalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imageView_Detalle = (ImageView) findViewById(R.id.imageView2);
        title_Detalle = (TextView) findViewById(R.id.textView2);
        desc_Detalle = (TextView) findViewById(R.id.textView3);

        String valorIMG = getIntent().getExtras().getString("imagname");
        title_Detalle.setText(getIntent().getStringExtra("titlename"));
        desc_Detalle.setText(getIntent().getStringExtra("desname"));

        cargarDATA(valorIMG);
    }

    public void cargarDATA(String image) {
        Glide.with(this)
                .load(image)
                .into(imageView_Detalle);
    }
}