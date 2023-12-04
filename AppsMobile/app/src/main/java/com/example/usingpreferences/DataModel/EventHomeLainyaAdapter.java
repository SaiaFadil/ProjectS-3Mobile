package com.example.usingpreferences.DataModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usingpreferences.R;

import java.util.ArrayList;

public class EventHomeLainyaAdapter extends RecyclerView.Adapter<EventHomeLainyaAdapter.ViewHolder>{

    public ArrayList<EventHomeModel> models;

    public EventHomeLainyaAdapter(ArrayList<EventHomeModel> models){
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
