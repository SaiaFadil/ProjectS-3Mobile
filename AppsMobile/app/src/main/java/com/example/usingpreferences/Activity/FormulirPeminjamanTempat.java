package com.example.usingpreferences.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.DataShared;
import com.example.usingpreferences.DataModel.ModelResponseAll;
import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormulirPeminjamanTempat extends AppCompatActivity {
    private static final int REQUEST_CODE_SELECT_PDF = 1;
    private AppCompatCheckBox checkboxsetuju;
    private DatePickerDialog picker;
    private EditText inpWaktuMulai, inpWaktuAkhir;
    private MaterialButton selectFileButton2;
    private TextView cardViewFileNameTextView1;

    private EditText inpNamaTempat, inpNamaLengkap, inpKtp, inpInstansi, inpNamaKegiatan, inpDeskripsi, inpTanggalMulai, inpTanggalAkhir, inpPeserta, inpNamaPemimjam, InpWaktuMulai;

    private MaterialButton btnPickImage;
    MaterialButton btnkirimpinjam ;

    private DataShared dataShared;
    Calendar tanggalMulaiCalendar , tanggalAkhirCalendar;
    Calendar mulaiAwal;

    byte[] pathSurat;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir_peminjaman_tempat);

        dataShared = new DataShared(this);

        Toast.makeText(this, dataShared.getData(DataShared.KEY.ID_NAMA_TEMPAT), Toast.LENGTH_SHORT).show();

        inpNamaPemimjam = findViewById(R.id.et_namalengkappinjam);
        inpNamaTempat = findViewById(R.id.et_tempatpinjam);
        inpNamaKegiatan = findViewById(R.id.et_namakegiatanpinjam);
        inpKtp = findViewById(R.id.et_ktppinjam);
        inpInstansi = findViewById(R.id.et_instansipinjam);
        inpPeserta = findViewById(R.id.et_jumlahpesertapinjam);
        inpDeskripsi = findViewById(R.id.et_deskripsipinjam);
        btnPickImage = findViewById(R.id.selectFileButton2);
        inpTanggalAkhir = findViewById(R.id.et_tanggalakhirpinjam);
        inpTanggalMulai = findViewById(R.id.et_tanggalawalpinjam);
        inpWaktuMulai = findViewById(R.id.waktuawalpinjam);
        inpWaktuAkhir = findViewById(R.id.waktuakhirpinjam);
        cardViewFileNameTextView1 = findViewById(R.id.textViewButton1);
        checkboxsetuju = findViewById(R.id.checkboxsetuju);
        btnkirimpinjam   = findViewById(R.id.button_kirimpinjam);

        inpWaktuMulai.setInputType(InputType.TYPE_NULL);
        inpWaktuAkhir.setInputType(InputType.TYPE_NULL);

        inpNamaTempat.setText(dataShared.getData(DataShared.KEY.NAMA_TEMPAT));
        inpTanggalMulai.setText(dataShared.getData(DataShared.KEY.TANGGAL_MULAI));
        tanggalAkhirCalendar = Calendar.getInstance();

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuilder filteredStringBuilder = new StringBuilder();

                for (int i = start; i < end; i++) {
                    char currentChar = source.charAt(i);

                    if (Character.isLetter(currentChar) || currentChar == ' ') {
                        filteredStringBuilder.append(currentChar); // Memasukkan karakter yang diizinkan
                    }
                }

                return filteredStringBuilder.toString(); // Mengembalikan karakter yang diizinkan
            }
        };

        InputFilter angkaBoleh = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuilder filteredStringBuilder = new StringBuilder();

                for (int i = start; i < end; i++) {
                    char currentChar = source.charAt(i);

                    if (Character.isLetter(currentChar) || currentChar == ' ' || Character.isDigit(currentChar)) {
                        filteredStringBuilder.append(currentChar); // Memasukkan karakter yang diizinkan
                    }
                }

                return filteredStringBuilder.toString(); // Mengembalikan karakter yang diizinkan
            }
        };

        InputFilter filterDesc = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    char inputChar = source.charAt(i);
                    if (!Character.isLetterOrDigit(inputChar) && !Character.isSpaceChar(inputChar) && inputChar != '\n') {
                        return ""; // Hanya izinkan huruf, spasi, dan Enter (newline)
                    }
                }
                return null; // Input valid
            }
        };

        inpDeskripsi.setFilters(new InputFilter[]{filterDesc , new InputFilter.LengthFilter(500)});
        inpNamaPemimjam.setFilters(new InputFilter[]{filter});
        inpInstansi.setFilters(new InputFilter[]{angkaBoleh});
        inpNamaKegiatan.setFilters(new InputFilter[]{filter});



        btnPickImage.setOnClickListener(v -> {
            Toast.makeText(FormulirPeminjamanTempat.this, "test", Toast.LENGTH_SHORT).show();
            selectFile("image/*", 2);
        });


        inpWaktuMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(inpWaktuMulai);
            }
        });

        inpWaktuAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(inpWaktuAkhir);
            }
        });

        inpTanggalAkhir.setInputType(InputType.TYPE_NULL);

        // Ambil data tanggal dari intent
        String tanggal = getIntent().getStringExtra("tanggal_awal");
        // Cari EditText dengan ID et_tanggalawal

//        inpTanggalAkhir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Dapatkan tanggal saat ini
//                final Calendar calendar = Calendar.getInstance();
//                int tahunSaatIni = calendar.get(Calendar.YEAR);
//                int bulanSaatIni = calendar.get(Calendar.MONTH);
//                int hariSaatIni = calendar.get(Calendar.DAY_OF_MONTH);
//
//                // Buat DatePickerDialog untuk memilih tanggal akhir
//                DatePickerDialog datePickerDialog = new DatePickerDialog(FormulirPeminjamanTempat.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int tahun, int bulan, int hari) {
//                        // Format tanggal akhir sesuai kebutuhan Anda (misalnya, "dd/MM/yyyy")
//                        String tanggalAkhirFormatted = String.format(Locale.getDefault(), "%02d/%02d/%02d", tahun, bulan + 1, hari);
//                        // Set teks pada EditText tanggal akhir
//                        inpTanggalAkhir.setText(tanggalAkhirFormatted);
//                    }
//                }, tahunSaatIni, bulanSaatIni, hariSaatIni);
//
//                // Tampilkan dialog pemilihan tanggal akhir
//                datePickerDialog.show();
//            }
//        });


        inpTanggalMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int tahunSaatIni = calendar.get(Calendar.YEAR);
                int bulanSaatIni = calendar.get(Calendar.MONTH);
                int hariSaatIni = calendar.get(Calendar.DAY_OF_MONTH);

                // Buat DatePickerDialog untuk memilih tanggal mulai
                DatePickerDialog datePickerDialog = new DatePickerDialog(FormulirPeminjamanTempat.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int tahun, int bulan, int hari) {
                        // Simpan tanggal mulai yang dipilih
                        tanggalMulaiCalendar = Calendar.getInstance();
                        tanggalMulaiCalendar.set(tahun, bulan, hari);

                        // Format tanggal mulai sesuai kebutuhan Anda (misalnya, "dd/MM/yyyy")
                        String tanggalMulaiFormatted = String.format(Locale.getDefault(), "%02d/%02d/%02d", tahun, bulan + 1, hari);
                        // Set teks pada EditText tanggal mulai
                        inpTanggalMulai.setText(tanggalMulaiFormatted);


                    }
                }, tahunSaatIni, bulanSaatIni, hariSaatIni);

                DatePicker datePicker = datePickerDialog.getDatePicker();
                datePicker.setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        inpTanggalAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("tes tanggalMulaiCalendar =" + tanggalMulaiCalendar);

                if(tanggalMulaiCalendar == null){


                    String tanggalPilih = dataShared.getData(DataShared.KEY.TANGGAL_MULAI).toString();
                    String[] bagian = tanggalPilih.split("/");

                    // Konversi setiap bagian ke integer
                    int tahun = Integer.parseInt(bagian[0]);
                    int bulan = Integer.parseInt(bagian[1])-1;
                    int tanggal = Integer.parseInt(bagian[2]);

                    System.out.println("tahun sekarang = "+ tahun);

                    mulaiAwal = Calendar.getInstance();
                    mulaiAwal.set(tahun , bulan , tanggal);

                    if (tanggalPilih.equals("")) {
                        // Berikan pesan bahwa pengguna harus memilih tanggal mulai terlebih dahulu
                        Toast.makeText(FormulirPeminjamanTempat.this, "Harap pilih kembali tanggal mulai untuk mengantisipasi kesalahan saat pemilihan tanggal mulai", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Buat DatePickerDialog untuk memilih tanggal akhir
                    DatePickerDialog datePickerDialog = new DatePickerDialog(FormulirPeminjamanTempat.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int tahun, int bulan, int hari) {
                            // Bandingkan dengan tanggal mulai yang telah dipilih
                            Calendar selectedCalendar = Calendar.getInstance();
                            selectedCalendar.set(tahun, bulan, hari);

                            if (selectedCalendar.after(mulaiAwal)) {

                                // Format tanggal akhir sesuai kebutuhan Anda (misalnya, "dd/MM/yyyy")
                                String tanggalAkhirFormatted1 = String.format(Locale.getDefault(), "%02d/%02d/%02d", tahun, bulan + 1, hari);
                                // Set teks pada EditText tanggal akhir
                                tanggalAkhirCalendar = Calendar.getInstance();
                                tanggalAkhirCalendar.set(tahun,bulan , hari);
                                inpTanggalAkhir.setText(tanggalAkhirFormatted1);
                            } else {
                                Toast.makeText(FormulirPeminjamanTempat.this, "Tanggal selesai tidak boleh sebelum tanggal mulai", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, tahun, bulan, tanggal);

                    DatePicker datePicker = datePickerDialog.getDatePicker();
                    final Calendar calendar = Calendar.getInstance();
                    datePicker.setMinDate(calendar.getTimeInMillis());
                    datePickerDialog.show();

                } else {
                    // Dapatkan tanggal awal dari tanggal yang dipilih sebelumnya
                    int tahunAwal = tanggalMulaiCalendar.get(Calendar.YEAR);
                    int bulanAwal = tanggalMulaiCalendar.get(Calendar.MONTH);
                    int hariAwal = tanggalMulaiCalendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(FormulirPeminjamanTempat.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int tahun, int bulan, int hari) {
                            // Bandingkan dengan tanggal mulai yang telah dipilih
                            Calendar selectedCalendar = Calendar.getInstance();
                            selectedCalendar.set(tahun, bulan, hari);

                            if (selectedCalendar.after(tanggalMulaiCalendar)) {
                                // Format tanggal akhir sesuai kebutuhan Anda (misalnya, "dd/MM/yyyy")
                                String tanggalAkhirFormatted = String.format(Locale.getDefault(), "%02d/%02d/%02d", tahun, bulan + 1, hari);
                                // Set teks pada EditText tanggal akhir
                                tanggalAkhirCalendar.set(tahun,bulan , hari);
                                inpTanggalAkhir.setText(tanggalAkhirFormatted);
                            } else {

                                // Jika tanggal yang dipilih sebelum tanggal mulai, berikan pesan atau lakukan tindakan sesuai kebutuhan Anda
                                Toast.makeText(FormulirPeminjamanTempat.this, "Tanggal selesai tidak boleh sebelum tanggal mulai", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, tahunAwal, bulanAwal, hariAwal);

                    // Tampilkan dialog pemilihan tanggal akhir
                    datePickerDialog.show();
                }


            }
        });

        checkboxsetuju.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Metode ini dipanggil ketika status centang checkbox berubah
                if (!isChecked) {
                    // Tampilkan pesan kesalahan jika checkbox tidak dicentang
                    checkboxsetuju.setError("Anda harus menyetujui");
                } else {
                    // Hapus kesalahan jika checkbox dicentang
                    checkboxsetuju.setError(null);
                }
            }
        });


        cardViewFileNameTextView1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Metode ini dipanggil sebelum teks berubah
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Metode ini dipanggil ketika teks sedang diubah
                String namaFile = s.toString();

                // Periksa apakah nama file kosong
                if (namaFile.trim().isEmpty()) {
                    // Tampilkan pesan kesalahan atau atur status kesalahan untuk nama file
                    // Mengasumsikan cardViewFileNameTextView1 adalah EditText, Anda bisa menggunakan setError
                    cardViewFileNameTextView1.setError("Nama file harus diisi");
                } else {
                    // Hapus kesalahan jika nama file tidak kosong
                    cardViewFileNameTextView1.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Metode ini dipanggil setelah perubahan teks
            }
        });


        EditText editText = findViewById(R.id.et_ktppinjam);

        int minLength = 16;  // Set your desired minimum character limit
        int maxLength = 16; // Set your desired maximum character limit

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before text changes
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called when text is being changed
                String input = s.toString();

                // Check for the minimum length
                if (input.length() < minLength) {
                    editText.setError("No NIK harus diisi");
                } else {
                    editText.setError(null); // Clear the error if the minimum length is met
                }

                // Check for the maximum length
                if (input.length() > maxLength) {
                    editText.setText(input.substring(0, maxLength)); // Trim the text to the maximum length
                    editText.setSelection(maxLength); // Move the cursor to the end
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after text changes
            }
        });

        inpNamaPemimjam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called when the text is being changed
                String input = s.toString();

                // Check maximum length
                if (input.length() > 30) {
                    inpNamaPemimjam.setText(s.subSequence(0, 30)); // Truncate the text to 30 characters
                    inpNamaPemimjam.setSelection(30); // Set the cursor position to the end
                    inpNamaPemimjam.setError("Nama Pemimjam tidak boleh lebih dari 30 karakter");
                } else if (containsNumbers(input)) {
                    inpNamaPemimjam.setText(removeNumbers(input)); // Remove numbers from the text
                    inpNamaPemimjam.setSelection(inpNamaPemimjam.length()); // Set the cursor position to the end
                    inpNamaPemimjam.setError("Nama Pemimjam tidak boleh mengandung angka");
                } else {
                    inpNamaPemimjam.setError(null); // Remove error if conditions are met
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has been changed
            }

            // Helper method to check if the input contains numbers
            private boolean containsNumbers(String input) {
                for (char c : input.toCharArray()) {
                    if (Character.isDigit(c)) {
                        return true;
                    }
                }
                return false;
            }

            // Helper method to remove numbers from the input
            private String removeNumbers(String input) {
                return input.replaceAll("\\d", ""); // Replace all digits with an empty string
            }
        });


        inpInstansi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Metode ini dipanggil sebelum teks berubah
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Metode ini disebut ketika teks sedang diubah
                String input = s.toString();

                // Check minimum length
                if (input.length() < 12) {
                    inpInstansi.setError("Instansi harus diisi");
                } else {
                    inpInstansi.setError(null); // Hapus kesalahan jika panjang minimum terpenuhi
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Metode ini dipanggil setelah perubahan teks
            }
        });


        inpNamaKegiatan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called when the text is being changed
                String input = s.toString();

                // Check if the input contains numbers
                if (containsNumbers(input) || input.trim().isEmpty()) {
                    inpNamaKegiatan.setError("Nama Kegiatan harus diisi dan tidak boleh mengandung angka");
                } else {
                    inpNamaKegiatan.setError(null); // Remove error if the conditions are met
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has been changed
            }

            // Helper method to check if the input contains numbers
            private boolean containsNumbers(String input) {
                for (char c : input.toCharArray()) {
                    if (Character.isDigit(c)) {
                        return true;
                    }
                }
                return false;
            }
        });

        inpPeserta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called when the text is being changed
                String input = s.toString();

                // Check maximum length
                if (input.length() > 5) {
                    inpPeserta.setText(s.subSequence(0, 5)); // Truncate the text to 5 characters
                    inpPeserta.setSelection(5); // Set the cursor position to the end
                    inpPeserta.setError("Jumlah Peserta harus diisi dan tidak boleh lebih dari 5 karakter");
                } else {
                    inpPeserta.setError(null); // Remove error if the length is within the limit
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has been changed
            }
        });

        inpDeskripsi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Metode ini dipanggil sebelum teks berubah
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Metode ini disebut ketika teks sedang diubah
                String input = s.toString();

                // Check minimum length
                if (input.length() < minLength) {
                    inpDeskripsi.setError("Deskripsi harus diisi");
                } else {
                    inpDeskripsi.setError(null); // Hapus kesalahan jika panjang minimum terpenuhi
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Metode ini dipanggil setelah perubahan teks
            }
        });




        btnkirimpinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tanggalMulaiCalendar == null){
                    send();
                } else {
                    if(tanggalMulaiCalendar.after(tanggalAkhirCalendar)){
                        if(inpTanggalMulai.getText().toString().equals(inpTanggalAkhir.getText().toString())){
                            send();
                        } else {
                            Toast.makeText(FormulirPeminjamanTempat.this, "Pastikan memasukan tanggal dengan benarsss", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        send();
                    }
                }



            }
        });


        ImageButton pinjamback = findViewById(R.id.pinjamback);
        pinjamback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void send(){
        // Dapatkan nilai dari kolom input
        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String idUserShared = sharedPreferences.getString("id_user", "");

        System.out.println("ID USER" + idUserShared);
        String namaPemimjam = inpNamaPemimjam.getText().toString().trim();
        String namaTempat = inpNamaTempat.getText().toString().trim();
        String namaKegiatan = inpNamaKegiatan.getText().toString().trim();
        String ktp = inpKtp.getText().toString();
        String instansi = inpInstansi.getText().toString().trim();
        String peserta = inpPeserta.getText().toString().trim();
        String deskripsi = inpDeskripsi.getText().toString().trim();
        String tanggalMulai = inpTanggalMulai.getText().toString().trim();
        String waktuMulai = inpWaktuMulai.getText().toString().trim();
        String tanggalAkhir = inpTanggalAkhir.getText().toString().trim();
        String waktuAkhir = inpWaktuAkhir.getText().toString().trim();

        RequestBody requestBodyIdUser = RequestBody.create(MediaType.parse("multipart/form-data"), idUserShared);
        RequestBody requestBodyNamaPeminjam = RequestBody.create(MediaType.parse("multipart/form-data"), namaPemimjam);
        RequestBody requestBodyNamaTempat = RequestBody.create(MediaType.parse("multipart/form-data"), namaTempat);
        RequestBody requestBodyNamaKegiatan = RequestBody.create(MediaType.parse("multipart/form-data"), namaKegiatan);
        RequestBody requestBodyKtp = RequestBody.create(MediaType.parse("multipart/form-data"), ktp);
        RequestBody requestBodyInstansi = RequestBody.create(MediaType.parse("multipart/form-data"), instansi);
        RequestBody requestBodyPeserta = RequestBody.create(MediaType.parse("multipart/form-data"), peserta);
        RequestBody requestBodyDeskripsi = RequestBody.create(MediaType.parse("multipart/form-data"), deskripsi);
        RequestBody requestBodyTanggalMulai = RequestBody.create(MediaType.parse("multipart/form-data"), tanggalMulai + " " + waktuMulai);
        RequestBody requestBodyStatus = RequestBody.create(MediaType.parse("multipart/form-data"), "diajukan");
        RequestBody requestBodyTanggalAkhir = RequestBody.create(MediaType.parse("multipart/form-data"), tanggalAkhir + " " + waktuAkhir);
        RequestBody requestBodyIdTempat = RequestBody.create(MediaType.parse("multipart/form-data"), dataShared.getData(DataShared.KEY.ID_NAMA_TEMPAT).toString());


        System.out.println("tes hasil ktp = "+ ktp);

        // Periksa apakah jalur file kosong
        String filePath = cardViewFileNameTextView1.getText().toString().trim();
        System.out.println("filePath ==" + filePath);
        if (filePath.equals("Pilih File")) {
            // Display a notification that the file must be selected
            Toast.makeText(FormulirPeminjamanTempat.this, "Harap pilih berkas", Toast.LENGTH_SHORT).show();
            return; // Stop execution if the file path is empty
        }

        // Periksa apakah kotak centang perjanjian dicentang
        CheckBox checkboxSetuju = findViewById(R.id.checkboxsetuju);
        if (!checkboxSetuju.isChecked()) {
            // Display a notification that the checkbox must be checked
            Toast.makeText(FormulirPeminjamanTempat.this, "Harap centang persetujuan", Toast.LENGTH_SHORT).show();
            return; // Stop execution if the checkbox is not checked
        }

        // Periksa apakah ada bidang wajib yang kosong
        if (TextUtils.isEmpty(namaPemimjam)
                || TextUtils.isEmpty(namaTempat)
                || TextUtils.isEmpty(namaKegiatan)
                || TextUtils.isEmpty(ktp)
                || TextUtils.isEmpty(instansi)
                || TextUtils.isEmpty(peserta)
                || TextUtils.isEmpty(deskripsi)
                || TextUtils.isEmpty(tanggalMulai)
                || TextUtils.isEmpty(waktuMulai)
                || TextUtils.isEmpty(tanggalAkhir)
                || TextUtils.isEmpty(waktuAkhir) ) {
            // Display a notification that all fields must be filled
            Toast.makeText(FormulirPeminjamanTempat.this, "Harap isi semua", Toast.LENGTH_SHORT).show();
            return; // Stop execution if any field is empty
        }
        File ktpSenimanFile = new File(filePath);
        RequestBody requestFileKtpSeniman = RequestBody.create(MediaType.parse("multipart/form-data"), pathSurat);
        MultipartBody.Part ktpSenimanPart = MultipartBody.Part.createFormData("surat_ket_sewa", ktpSenimanFile.getName(), requestFileKtpSeniman);

        RetroServer.getConnection().create(APIRequestData.class)
                .sendPinjamTempat(
                        requestBodyNamaPeminjam,
                        requestBodyKtp,
                        requestBodyInstansi,
                        requestBodyNamaKegiatan,
                        requestBodyPeserta,
                        requestBodyNamaTempat,
                        requestBodyDeskripsi,
                        requestBodyTanggalMulai,
                        requestBodyTanggalAkhir,
                        requestBodyStatus,
                        requestBodyDeskripsi,
                        requestBodyIdTempat,
                        requestBodyIdUser,
                        ktpSenimanPart
                ).enqueue(new Callback<ModelResponseAll>() {
                    @Override
                    public void onResponse(Call<ModelResponseAll> call, Response<ModelResponseAll> response) {
                        if (response.body() != null && response.body().getKode() == 1) {
                            Toast.makeText(FormulirPeminjamanTempat.this, "SUKSES", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(FormulirPeminjamanTempat.this, PengajuanBerhasilTerkirim.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(FormulirPeminjamanTempat.this, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelResponseAll> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }


    public static String getFilePathFromUri(Context context, Uri uri) {
        String filePath = null;
        if (uri.getScheme() != null && uri.getScheme().equals("content")) {
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursor = contentResolver.query(uri, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
                if (columnIndex != -1) {
                    filePath = cursor.getString(columnIndex);
                }
                cursor.close();
            }
        }
        if (filePath == null) {
            // Jika pengambilan menggunakan ContentResolver gagal, coba alternatif untuk mengambil jalur dari Uri
            filePath = getFilePathFromUriAlternative(context, uri);
        }

        return filePath;
    }
    public void selectDocumentFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        if (view.getId() == R.id.selectFileButton2) {
            startActivityForResult(
                    Intent.createChooser(intent, "Pilih Dokumen PDF"),
                    REQUEST_CODE_SELECT_PDF
            );
        }
    }
    private static String getFilePathFromUriAlternative(Context context, Uri uri) {
        String filePath = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            if (columnIndex != -1) {
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();
        }

        return filePath;
    }

    public static String getFileExtension(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String extension = mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
        if (extension == null) {
            // Jika tidak dapat menentukan ekstensi, gunakan ekstensi default
            extension = "txt";
        }
        return extension;
    }


    private void showTimePickerDialog(final EditText editText) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Format waktu yang dipilih
                        String selectedTime = String.format("%02d:%02d:%02d", hourOfDay, minute,00);
                        editText.setText(selectedTime);
                    }
                }, 0, 0, true);

        timePickerDialog.show();

    }

    public static byte[] uriToByteArray(Context context, Uri uri) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ContentResolver contentResolver = context.getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(uri);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @SuppressLint("Range")
    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            String[] projection = {OpenableColumns.DISPLAY_NAME};
            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            }
            if (result == null) {
                result = uri.getLastPathSegment();
            }
        }
        return result;
    }

    private void selectFile(String mimeType, int buttonId) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(mimeType);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Pilih File"),
                    buttonId // Menggunakan buttonId sebagai requestCode
            );
        } catch (android.content.ActivityNotFoundException ex) {
            // Handle jika tidak ada aplikasi yang dapat memilih file
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            String selectedFileName = getFileName(data.getData());
            cardViewFileNameTextView1.setText(selectedFileName);
            pathSurat = uriToByteArray(this, data.getData());
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(
                new Intent(FormulirPeminjamanTempat.this, PinjamTempatList.class)
        );
    }
}
