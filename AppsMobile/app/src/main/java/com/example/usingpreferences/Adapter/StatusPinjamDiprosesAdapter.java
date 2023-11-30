package com.example.usingpreferences.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usingpreferences.DataModel.ModelStatusPinjamDiproses;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class StatusPinjamDiprosesAdapter extends RecyclerView.Adapter<StatusPinjamDiprosesAdapter.ViewHolder> {

    private List<ModelStatusPinjamDiproses> data;
    public static ShimmerFrameLayout mFrameLayout;
    public static LinearLayout mDataSemua;

    public StatusPinjamDiprosesAdapter(List<ModelStatusPinjamDiproses> data) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status_tempat_diproses, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        ModelStatusPinjamDiproses item = data.get(position);

        holder.idSewaTextView.setText(item.getId_sewa());
        holder.tglPinjamTextView.setText(item.getTgl_awal_peminjaman());
        holder.namaPinjamTextView.setText(item.getNama_peminjam());


        holder.itemView.setOnClickListener(v -> {

        });


    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idSewaTextView;
        TextView tglPinjamTextView;
        TextView namaPinjamTextView;
        ShimmerFrameLayout mFrameLayout;

        ViewHolder(View itemView) {
            super(itemView);
            mDataSemua = itemView.findViewById(R.id.data_view);
            mFrameLayout = itemView.findViewById(R.id.shimmer_view);
            idSewaTextView = itemView.findViewById(R.id.id_pinjam_diproses);
            tglPinjamTextView = itemView.findViewById(R.id.tgl_pinjam_diproses);
            namaPinjamTextView = itemView.findViewById(R.id.nama_pinjam_diproses);
        }
    }

}
