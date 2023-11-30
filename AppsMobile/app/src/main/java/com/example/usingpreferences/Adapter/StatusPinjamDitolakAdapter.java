package com.example.usingpreferences.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usingpreferences.Activity.FormStatusPinjamDibatalkan;
import com.example.usingpreferences.DataModel.ModelStatusPinjamDitolak;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class StatusPinjamDitolakAdapter extends RecyclerView.Adapter<StatusPinjamDitolakAdapter.ViewHolder>{

    private List<ModelStatusPinjamDitolak> data;
    public static ShimmerFrameLayout mFrameLayout;
    public static LinearLayout mDataSemua;

    public Context context;

    public StatusPinjamDitolakAdapter(Context context, List<ModelStatusPinjamDitolak> data) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status_tempat_ditolak, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        ModelStatusPinjamDitolak item = data.get(position);

        holder.idSewaTextView.setText(item.getId_sewa());
        holder.tglPinjamTextView.setText(item.getTgl_awal_peminjaman());
        holder.namaPinjamTextView.setText(item.getNama_peminjam());


        holder.itemView.setOnClickListener(v -> {
           context. startActivity(new Intent(context, FormStatusPinjamDibatalkan.class)
                   .putExtra("id_sewa", item.getId_sewa())
           );
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
            idSewaTextView = itemView.findViewById(R.id.id_pinjam_ditolak);
            tglPinjamTextView = itemView.findViewById(R.id.tgl_pinjam_ditolak);
            namaPinjamTextView = itemView.findViewById(R.id.nama_pinjam_ditolak);
        }
    }

}
