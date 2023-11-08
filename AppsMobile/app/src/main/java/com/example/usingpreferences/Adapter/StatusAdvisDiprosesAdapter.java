package com.example.usingpreferences.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usingpreferences.Activity.FormAdvisDiproses;
import com.example.usingpreferences.DataModel.ModelStatusAdvisDiproses;
import com.example.usingpreferences.R;

import java.util.ArrayList;
import java.util.List;

public class StatusAdvisDiprosesAdapter extends RecyclerView.Adapter<StatusAdvisDiprosesAdapter.ViewHolder> {

    private List<ModelStatusAdvisDiproses> data;

    public StatusAdvisDiprosesAdapter(List<ModelStatusAdvisDiproses> data) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
    }

    @NonNull
    @Override
    public StatusAdvisDiprosesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status_pentas_proses, parent, false);
        return new StatusAdvisDiprosesAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull StatusAdvisDiprosesAdapter.ViewHolder holder, int position) {
        ModelStatusAdvisDiproses item = data.get(position);

        holder.idAdvisTextView.setText(item.getId_advis());
        holder.tglAdvisTextView.setText(item.getTgl_advis());
        holder.namaAdvisTextView.setText(item.getNama_advis());

//ngisor ki gae ketika di klik dia akan nyimpen data ne buat di tampilne nde layout lanjut an e
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Data dari item yang diklik
                String idAdvis = item.getId_advis();
                String tglAdvis = item.getTgl_advis();
                String namaAdvis = item.getNama_advis();

                // Kirim data ke aktivitas selanjutnya
                Intent intent = new Intent(v.getContext(), FormAdvisDiproses.class);
                intent.putExtra("id_advis", idAdvis);
                intent.putExtra("tgl_advis", tglAdvis);
                intent.putExtra("nama_advis", namaAdvis);
                v.getContext().startActivity(intent);
            }
        });
//selesai


    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idAdvisTextView;
        TextView tglAdvisTextView;
        TextView namaAdvisTextView;

        ViewHolder(View itemView) {
            super(itemView);
            idAdvisTextView = itemView.findViewById(R.id.id_advis_diproses);
            tglAdvisTextView = itemView.findViewById(R.id.tgl_advis_diproses);
            namaAdvisTextView = itemView.findViewById(R.id.nama_advis_diproses);
        }
    }
}
