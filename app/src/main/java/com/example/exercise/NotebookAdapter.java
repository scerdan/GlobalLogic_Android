package com.example.exercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise.models.NotebookModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class NotebookAdapter extends RecyclerView.Adapter<NotebookAdapter.ViewHolder> {

    private final ArrayList<NotebookModel> notebookModels;
    private ArrayList<NotebookAdapter> NotebookAdapter = new ArrayList<>();
    private Context context;


    public NotebookAdapter(Context context, ArrayList<NotebookModel> notebookModels) {
        this.notebookModels = notebookModels;
        this.context = context;
    }

    @NonNull
    @Override
    public NotebookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotebookAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(notebookModels.get(i).getTitle());
        viewHolder.desc.setText(notebookModels.get(i).getDescription());

        Picasso.get().load(notebookModels.get(i).getImage()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return notebookModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView title;
        private final TextView desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.iv_Img);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            desc = (TextView) itemView.findViewById(R.id.tv_SubTitle);
        }
    }
}
