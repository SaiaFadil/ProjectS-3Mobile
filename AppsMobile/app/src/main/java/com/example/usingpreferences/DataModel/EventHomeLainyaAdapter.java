package com.example.usingpreferences.DataModel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.usingpreferences.Activity.DetailEventDashboard;
import com.example.usingpreferences.R;

import java.util.ArrayList;

public class EventHomeLainyaAdapter extends RecyclerView.Adapter<EventHomeLainyaAdapter.ViewHolder>{

    public Context context;
    public ArrayList<EventHomeModel> models;

    public EventHomeLainyaAdapter(Context context, ArrayList<EventHomeModel> models){
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_event_home_lainya, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        EventHomeModel model = models.get(position);
        holder.txtNama.setText(model.getNamaEvent());

        holder.itemView.setOnClickListener(v -> {
            context.startActivity(new Intent(
                    context,
                    DetailEventDashboard.class)
                    .putExtra("nama", model.getNamaEvent())
                    .putExtra("gbr", model.getImgEvent())
                    .putExtra("tgl", model.getTanggal())
                    .putExtra("poster", model.getImgEvent())
                    .putExtra("deskripsi", model.getDeskripsi())
                    .putExtra("link", model.getLinkPendaftaran())
            );
        });

        Glide.with(context)
                .load("https://elok.tifnganjuk.com/DatabaseMobile/uploads/events" + model.getImgEvent())
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return models != null ? models.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView txtNama;

        public ViewHolder(View view){
            super(view);

            imageView = view.findViewById(R.id.home_img);
            txtNama = view.findViewById(R.id.home_event);
        }

    }
}
