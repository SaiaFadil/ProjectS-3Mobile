package com.example.usingpreferences.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usingpreferences.Activity.FormSenimanDiajukan;
import com.example.usingpreferences.DataModel.ModelStatusSenimanDiajukan;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class StatusSenimanDiajukanAdapter extends RecyclerView.Adapter<StatusSenimanDiajukanAdapter.ViewHolder> {

    private List<ModelStatusSenimanDiajukan> data;
    public static ShimmerFrameLayout mFrameLayout;
    public static LinearLayout mDataSemua;

    public StatusSenimanDiajukanAdapter(List<ModelStatusSenimanDiajukan> data) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status_seniman_diajukan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelStatusSenimanDiajukan item = data.get(position);

        holder.idSenimanTextView.setText(item.getId_seniman());
        holder.tglSenimanTextView.setText(item.getTgl_pembuatan());
        holder.namaSenimanTextView.setText(item.getNama_seniman());

        //ngisor ki gae ketika di klik dia akan nyimpen data ne buat di tampilne nde layout lanjut an e
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Data dari item yang diklik
                String idSeniman = item.getId_seniman();

                // Kirim data ke aktivitas selanjutnya
                Intent intent = new Intent(v.getContext(), FormSenimanDiajukan.class);
                intent.putExtra("id_seniman", idSeniman);
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
        TextView idSenimanTextView;
        TextView tglSenimanTextView;
        TextView namaSenimanTextView;
        ShimmerFrameLayout mFrameLayout;

        ViewHolder(View itemView) {
            super(itemView);
            mDataSemua = itemView.findViewById(R.id.data_view);
            mFrameLayout = itemView.findViewById(R.id.shimmer_view);
            idSenimanTextView = itemView.findViewById(R.id.id_seniman_diajukan);
            tglSenimanTextView = itemView.findViewById(R.id.tgl_seniman_diajukan);
            namaSenimanTextView = itemView.findViewById(R.id.nama_seniman_diajukan);
        }
    }
}
