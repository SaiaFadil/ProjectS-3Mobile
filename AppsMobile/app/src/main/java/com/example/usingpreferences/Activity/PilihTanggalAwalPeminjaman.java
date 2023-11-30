package com.example.usingpreferences.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.applandeo.materialcalendarview.CalendarDay;
import com.applandeo.materialcalendarview.CalendarView;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.DataShared;
import com.example.usingpreferences.DataModel.TanggalSewaModel;
import com.example.usingpreferences.DataModel.TanggalSewaResponse;
import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihTanggalAwalPeminjaman extends AppCompatActivity {
public static String namatempat;

private DataShared dataShared;

    private boolean isSpecificDate(Long selectedDate) {
        // Replace this logic with your specific date-checking criteria
        // For example, let's assume you have a list of specific dates
        List<Long> specificDates = getSpecificDates();
        return specificDates.contains(selectedDate);
    }
    private List<Long> getSpecificDates() {
        // Replace this with your list of specific dates in milliseconds
        List<Long> specificDates = new ArrayList<>();
        specificDates.add(1699549200000L);
        specificDates.add(1699635600000L);
        // Add more specific dates as needed

        return specificDates;
    }
    private CalendarView calendarView;

    private ArrayList<Calendar> calendars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_tanggal_awal_peminjaman);

        dataShared = new DataShared(this);

        calendarView = findViewById(R.id.calendarView);

//        DatePicker datePicker = findViewById(R.id.datePicker);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//                @Override
//                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                    Toast.makeText(PilihTanggalAwalPeminjaman.this, "month : " + dayOfMonth, Toast.LENGTH_SHORT).show();
//                }
//            });
//        }

//        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
//        builder.setTitleText("Select Date");

//        MaterialDatePicker<Long> materialDatePicker = builder.build();

//        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
//            if (isSpecificDate(selection)) {
//                datePicker.setBackgroundColor(getResources().getColor(R.color.red));
//            } else {
//                // Reset the background color if it's not a specific date
//                datePicker.setBackgroundColor(Color.TRANSPARENT);
//            }
//        });

//        materialDatePicker.show(getSupportFragmentManager(), materialDatePicker.toString());


//        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Toast.makeText(PilihTanggalAwalPeminjaman.this, "Date -> " + dayOfMonth, Toast.LENGTH_SHORT).show();
//                // Panggil metode untuk menyesuaikan latar belakang setiap kali tanggal berubah
//                PilihTanggalAwalPeminjaman.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        customizeDateBackground(datePicker);
//                    }
//                });
//            }
//        });

        TextView NamaTempatDipinjam = findViewById(R.id.NamaTempatDipinjam);
        namatempat = getIntent().getStringExtra("nama_tempat");
        NamaTempatDipinjam.setText(namatempat);
        MaterialButton button_pinjamtanggalawal = findViewById(R.id.button_pinjamtanggalawal);
        button_pinjamtanggalawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar datePicker = calendarView.getFirstSelectedDate();
                // Ambil tanggal yang dipilih dari DatePicker
                int day = datePicker.get(Calendar.DAY_OF_MONTH);
                int month = datePicker.get(Calendar.MONTH) + 1;
                int year = datePicker.get(Calendar.YEAR);
                // Buat Intent untuk berpindah ke halaman selanjutnya
                Intent intent = new Intent(PilihTanggalAwalPeminjaman.this, FormulirPeminjamanTempat.class);
                // Kirim tanggal yang dipilih sebagai data ekstra
                intent.putExtra("nama_tempat",namatempat);
                intent.putExtra("tanggal_awal", year + "/" + month + "/" + day);
                // Mulai halaman selanjutnya
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                dataShared.setData(DataShared.KEY.TANGGAL_MULAI, year + "/" + month + "/" + day);
            }
        });
        ImageButton pinjamback =  findViewById(R.id.pinjamback);
        pinjamback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });



//        ArrayList<Calendar> calendars = new ArrayList<>();
//        Calendar c1 = Calendar.getInstance();
//        c1.setTime(new Date(2023-1900, 10, 20));
//        Calendar c2 = Calendar.getInstance();
//        c2.setTime(new Date(2023-1900, 10, 21));
//        Calendar c3 = Calendar.getInstance();
//        c3.setTime(new Date(2023-1900, 10, 22));
//
//        calendars.add(c1);
//        calendars.add(c2);
//        calendars.add(c3);
//
//        changeBackground(calendars);

        DataShared shared = new DataShared(this);
        RetroServer.getInstance().tanggalSewa(
                shared.getData(DataShared.KEY.ID_NAMA_TEMPAT)
        ).enqueue(new Callback<TanggalSewaResponse>() {
            @Override
            public void onResponse(Call<TanggalSewaResponse> call, Response<TanggalSewaResponse> response) {
                if (response.body() !=  null && response.body().getStatus().equalsIgnoreCase("success")){

                    ArrayList<TanggalSewaModel> models = response.body().getData();

                    for(int i = 0; i < models.size(); i++){
                        Calendar awal = Calendar.getInstance();
                        Calendar akhir = Calendar.getInstance();

                        awal.set(
                                getYear(
                                        models.get(i).getTglAwal()
                                ) ,
                                getMonth(
                                        models.get(i).getTglAwal()
                                ) - 1,
                                getDay(
                                        models.get(i).getTglAwal()
                                )
                        );

                        akhir.set(
                                getYear(
                                        models.get(i).getTglAkhir()
                                ) ,
                                getMonth(
                                        models.get(i).getTglAkhir()
                                ) - 1,
                                getDay(
                                        models.get(i).getTglAkhir()
                                )
                        );

                        calendars.add(awal);
                        calendars.add(akhir);
                    }

                    changeBackground(calendars);

                }else {
                    Toast.makeText(PilihTanggalAwalPeminjaman.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TanggalSewaResponse> call, Throwable t) {
                Toast.makeText(PilihTanggalAwalPeminjaman.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void changeBackground(ArrayList<Calendar> calendars){
        ArrayList<CalendarDay> calendarDays = new ArrayList<>();
        for (Calendar calendar : calendars){
            Log.e("DATE", ""+ calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONDAY)+"-"+calendar.get(Calendar.DAY_OF_MONTH));
            calendarDays.add(new CalendarDay(calendar));
        }

        for (CalendarDay calendarDay : calendarDays){
            calendarDay.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_day));
        }

        calendarView.setCalendarDays(calendarDays);


    }

    public static int getYear(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Handle the exception as needed
        }
    }

    public static int getMonth(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.MONTH) + 1; // Adding 1 because months are 0-indexed
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Handle the exception as needed
        }
    }

    public static int getDay(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Handle the exception as needed
        }
    }

    private void customizeDatePicker(DatePicker datePicker) {
        // Set custom background color logic here
        // For example, set a yellow background for a specific date
        setBackgroundColorForDate(datePicker, 2023, Calendar.NOVEMBER, 15, Color.BLACK);
    }

    private void setBackgroundColorForDate(DatePicker datePicker, int year, int month, int day, int color) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        long milliseconds = calendar.getTimeInMillis();
        long maxDate = datePicker.getCalendarView().getMaxDate();
        long minDate = datePicker.getCalendarView().getMinDate();

        if (milliseconds >= minDate && milliseconds <= maxDate) {
            // Calculate the day index based on milliseconds
            int dayIndex = (int) ((milliseconds - minDate) / (24 * 60 * 60 * 1000));

            // Set the background color for the day index
            datePicker.getCalendarView().getChildAt(0);
        }
    }

    // Metode untuk menyesuaikan latar belakang tanggal tertentu
    private void customizeDateBackground(DatePicker datePicker) {
        // Dapatkan tanggal yang dipilih dari DatePicker
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        // Tambahkan logika Anda di sini untuk menentukan apakah tanggal ini harus memiliki latar belakang yang berbeda
        if (shouldHighlightDate(day, month, year)) {
            // Setel latar belakang warna yang berbeda untuk tanggal tertentu
            highlightDate(datePicker, Color.BLACK); // Ganti dengan warna yang Anda inginkan
        } else {
            // Reset latar belakang jika tidak sesuai dengan kriteria
            resetDateBackground(datePicker);
        }
    }

    // Metode untuk menentukan apakah tanggal ini harus memiliki latar belakang yang berbeda
    private boolean shouldHighlightDate(int day, int month, int year) {
        // Tambahkan logika Anda di sini untuk menentukan kriteria latar belakang
        // Misalnya, Anda mungkin ingin memberikan latar belakang warna pada tanggal tertentu
        // sesuai dengan kondisi tertentu.
        return (day == 15 && month == 11 && year == 2023); // Ganti dengan kriteria Anda
    }

    // Metode untuk menetapkan latar belakang warna untuk tanggal tertentu
    private void highlightDate(DatePicker datePicker, int color) {
        // Dapatkan tampilan kalender dari DatePicker
        LinearLayout ll = (LinearLayout) ((LinearLayout) datePicker.getChildAt(0)).getChildAt(0);

        // Iterasi melalui sel-sel kalender dan setel latar belakang untuk sel yang sesuai dengan tanggal tertentu
        for (int i = 0; i < ll.getChildCount(); i++) {
            LinearLayout ll2 = (LinearLayout) ll.getChildAt(i);
            for (int j = 0; j < ll2.getChildCount(); j++) {
                View dpHeaderText = ll2.getChildAt(j);
                if (dpHeaderText instanceof Button) {
                    Button btn = (Button) dpHeaderText;
                    if (!btn.getText().toString().equals("")) {
                        int dayOfMonth = Integer.parseInt(btn.getText().toString());
                        if (shouldHighlightDate(dayOfMonth, datePicker.getMonth(), datePicker.getYear())) {
                            btn.setBackgroundColor(color);
                        }
                    }
                }
            }
        }
    }

    // Metode untuk mengatur ulang latar belakang ke default
    private void resetDateBackground(DatePicker datePicker) {
        // Dapatkan tampilan kalender dari DatePicker
        LinearLayout ll = (LinearLayout) ((LinearLayout) datePicker.getChildAt(0)).getChildAt(0);

        // Iterasi melalui sel-sel kalender dan setel latar belakang ke default untuk semua sel
        for (int i = 0; i < ll.getChildCount(); i++) {
            LinearLayout ll2 = (LinearLayout) ll.getChildAt(i);
            for (int j = 0; j < ll2.getChildCount(); j++) {
                View dpHeaderText = ll2.getChildAt(j);
                if (dpHeaderText instanceof Button) {
                    Button btn = (Button) dpHeaderText;
                    if (!btn.getText().toString().equals("")) {
                        btn.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        }
    }

    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

    }
}