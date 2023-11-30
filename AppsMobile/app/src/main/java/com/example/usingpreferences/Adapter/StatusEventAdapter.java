package com.example.usingpreferences.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usingpreferences.Activity.FormEventDiajukan;
import com.example.usingpreferences.Activity.FormEventDiterimaDiproses;
import com.example.usingpreferences.Activity.FormEventDitolak;
import com.example.usingpreferences.DataModel.ModelStatusEvent;
import com.example.usingpreferences.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class StatusEventAdapter extends RecyclerView.Adapter<StatusEventAdapter.ViewHolder> {

    private Runnable runnable = null;

    private final Activity context;

    private final ArrayList<ModelStatusEvent> models;

    public StatusEventAdapter(Activity context, ArrayList<ModelStatusEvent> models){
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_status_event, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelStatusEvent event = models.get(position);

        holder.txtNama.setText(event.getNamaPengirim());
        holder.txtTanggal.setText(convertToDate(event.getTglAwal()));

        switch (event.getStatus().toLowerCase()){
            case "diterima" : {
                holder.txtStatus.setText("Diterima");
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.greenold));
                break;
            }
            case "diajukan" : {
                holder.txtStatus.setText("Diajukan");
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.blue));
                break;
            }
            case "ditolak" : {
                holder.txtStatus.setText("Ditolak");
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red));
                break;
            }
            case "proses" : {
                holder.txtStatus.setText("Proses");
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.yellow));
                break;
            }
        }

        if (holder.getAbsoluteAdapterPosition() != RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener(v -> {
//                if (runnable != null){
//                    runnable.run();
//                }
                switch (event.getStatus().toLowerCase()){
                    case "diterima" : {
                        context.startActivity(
                                new Intent(context, FormEventDiterimaDiproses.class)
                                        .putExtra(FormEventDiterimaDiproses.ID, models.get(holder.getAbsoluteAdapterPosition()).getIdEvent())
                                        .putExtra(FormEventDiterimaDiproses.TYPE, FormEventDiterimaDiproses.TYPE_DITERIMA)
                        );
                        break;
                    }
                    case "proses" : {
                        context.startActivity(
                                new Intent(context, FormEventDiterimaDiproses.class)
                                        .putExtra(FormEventDiterimaDiproses.ID, models.get(holder.getAbsoluteAdapterPosition()).getIdEvent())
                                        .putExtra(FormEventDiterimaDiproses.TYPE, FormEventDiterimaDiproses.TYPE_DIPROSES)
                        );
                        break;
                    }
                    case "ditolak" : {
                        context.startActivity(
                                new Intent(context, FormEventDitolak.class)
                                        .putExtra(FormEventDitolak.ID, models.get(holder.getAbsoluteAdapterPosition()).getIdEvent())
                        );
                        break;
                    }
                    case "diajukan" : {
                        context.startActivity(
                                new Intent(context, FormEventDiajukan.class)
                                        .putExtra(FormEventDiajukan.ID, models.get(holder.getAbsoluteAdapterPosition()).getIdEvent())
                        );
                        break;
                    }
                }
            });
        }
    }

    public static String convertToDate(@NonNull String date){
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            Date inputDate = inputDateFormat.parse(date);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("id"));
            assert inputDate != null;
            return outputDateFormat.format(inputDate);
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }
        return inputDateFormat.toString();
    }

    @Override
    public int getItemCount() {
        return models != null ? models.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;

        private final TextView txtNama, txtTanggal, txtStatus;

        public ViewHolder(View view){
            super(view);

            this.cardView = view.findViewById(R.id.status_event_card);
            this.txtNama = view.findViewById(R.id.nama_pengirim);
            this.txtTanggal = view.findViewById(R.id.tgl_event);
            this.txtStatus = view.findViewById(R.id.status_name_event);
        }

    }
}
