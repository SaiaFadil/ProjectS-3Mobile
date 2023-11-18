package com.example.usingpreferences.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usingpreferences.DataModel.ModelStatusSenimanDiterima;
import com.example.usingpreferences.R;

import java.util.ArrayList;
import java.util.List;

public class StatusSenimanDiterimaAdapter extends RecyclerView.Adapter<StatusSenimanDiterimaAdapter.ViewHolder> {

    private List<ModelStatusSenimanDiterima> data;

    public StatusSenimanDiterimaAdapter(List<ModelStatusSenimanDiterima> data) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status_seniman_diterima, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelStatusSenimanDiterima item = data.get(position);

        holder.idSenimanTextView.setText(item.getId_seniman());
        holder.tglSenimanTextView.setText(item.getTgl_pembuatan());
        holder.namaSenimanTextView.setText(item.getNama_seniman());

//ngisor ki gae ketika di klik dia akan nyimpen data ne buat di tampilne nde layout lanjut an e
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Data dari item yang diklik
//                String idSeniman = item.getId_seniman();
//                String tglSeniman = item.getTgl_pembuatan();
//                String namaSeniman = item.getNama_seniman();
//
//                // Kirim data ke aktivitas selanjutnya
//                Intent intent = new Intent(v.getContext(), FormSenimanDiterima.class);
//                intent.putExtra("id_Seniman", idSeniman);
//                intent.putExtra("tgl_Seniman", tglSeniman);
//                intent.putExtra("nama_Seniman", namaSeniman);
//                v.getContext().startActivity(intent);
//            }
//        });
//selesai ne


    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idSenimanTextView;
        TextView tglSenimanTextView;
        TextView namaSenimanTextView;

        ViewHolder(View itemView) {
            super(itemView);
            idSenimanTextView = itemView.findViewById(R.id.id_seniman_diterima);
            tglSenimanTextView = itemView.findViewById(R.id.tgl_seniman_diterima);
            namaSenimanTextView = itemView.findViewById(R.id.nama_seniman_diterima);
        }
    }
}
