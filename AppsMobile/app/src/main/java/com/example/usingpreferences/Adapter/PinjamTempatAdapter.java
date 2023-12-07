package com.example.usingpreferences.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.Adapter.AdapterListener;
import com.example.usingpreferences.DataModel.ListTempatModel;
import com.example.usingpreferences.R;

import java.util.ArrayList;

public class PinjamTempatAdapter extends RecyclerView.Adapter<PinjamTempatAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ListTempatModel> models;
    private AdapterListener listener;
    public PinjamTempatAdapter(Context context, ArrayList<ListTempatModel> models, AdapterListener listener){
        this.context = context;
        this.models = models;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_pinjam_tempat, parent, false)
        );
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ListTempatModel list = models.get(position);

        holder.setImage(context, list.getFotoTempat());
        holder.txtNama.setText(list.getNamaTempat());
        holder.txtDesc.setText(list.getAlamatTempat());

        if (holder.getAbsoluteAdapterPosition() != RecyclerView.NO_POSITION){
            if (listener != null){
                holder.itemView.setOnClickListener(v -> {
                    listener.onClickListener(position);
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return models != null ? models.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgTempat;

        private final TextView txtNama, txtDesc;

        public ViewHolder(View view){
            super(view);

            imgTempat = view.findViewById(R.id.ipt_image);
            txtNama = view.findViewById(R.id.ipt_nama);
            txtDesc = view.findViewById(R.id.ipt_desc);
        }

        public void setImage(Context context, String url){
            Glide.with(context)
                    .load(RetroServer.BASE_URL + url)
                    .placeholder(R.drawable.ic_back)
                    .centerCrop()
                    .into(imgTempat);
        }

    }
}
