package com.example.usingpreferences.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usingpreferences.Activity.FormAdvisDiajukan;
import com.example.usingpreferences.DataModel.ModelStatusAdvisDiajukan;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class StatusAdvisDiajukanAdapter extends RecyclerView.Adapter<StatusAdvisDiajukanAdapter.ViewHolder> {

    private List<ModelStatusAdvisDiajukan> data;
    public static ShimmerFrameLayout mFrameLayout;
    public static LinearLayout mDataSemua;

    public StatusAdvisDiajukanAdapter(List<ModelStatusAdvisDiajukan> data) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status_pentas_diajukan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        ModelStatusAdvisDiajukan item = data.get(position);

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
                Intent intent = new Intent(v.getContext(), FormAdvisDiajukan.class);


                intent.putExtra("id_advis", idAdvis);
                intent.putExtra("tgl_advis", tglAdvis);
                intent.putExtra("nama_advis", namaAdvis);
                v.getContext().startActivity(intent);
            }
        });
//selesai ne


    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idAdvisTextView;
        TextView tglAdvisTextView;
        TextView namaAdvisTextView;
        ShimmerFrameLayout mFrameLayout;

        ViewHolder(View itemView) {
            super(itemView);
            mDataSemua = itemView.findViewById(R.id.data_view);
            mFrameLayout = itemView.findViewById(R.id.shimmer_view);
            idAdvisTextView = itemView.findViewById(R.id.id_advis_diajukan);
            tglAdvisTextView = itemView.findViewById(R.id.tgl_advis_diajukan);
            namaAdvisTextView = itemView.findViewById(R.id.nama_advis_diajukan);
        }
    }
}
