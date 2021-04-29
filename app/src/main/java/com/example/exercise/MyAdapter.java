package com.example.exercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class MyAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> rTitle;
    ArrayList<String> rDescription;
    int[] rImgs;

    MyAdapter (Context c, ArrayList<String> title) {
        super(c, R.layout.item, R.id.tv_title, title);
        this.context = c;
        this.rTitle = title;
//        this.rDescription = description;
//        this.rImgs = imgs;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.item, parent, false);
        ImageView images = row.findViewById(R.id.iv_Img);
        TextView myTitle = row.findViewById(R.id.tv_title);
        TextView myDescription = row.findViewById(R.id.tv_SubTitle);

        // now set our resources on views
//        images.setImageResource(rImgs[position]);
        myTitle.setText(rTitle.get(position));
//        myDescription.setText(rDescription.get(position));
        return row;
    }
}
