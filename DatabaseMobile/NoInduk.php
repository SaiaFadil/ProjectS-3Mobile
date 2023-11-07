<?php
// Koneksi ke database MySQL
require('Koneksi.php');
// Menerima data dari aplikasi Android
$nik = $_POST['nik'];
$namaLengkap = $_POST['nama_seniman'];
$jenisKelamin = $_POST['jenis_kelamin'];
$tempatLahir = $_POST['tempat_lahir'];
$tanggalLahir = $_POST['tanggal_lahir'];
$alamat = $_POST['alamat_seniman'];
$noHandphone = $_POST['no_telpon'];
$namaOrganisasi = $_POST['nama_organisasi'];
$jumlahAnggota = $_POST['jumlah_anggota'];
$status = $_POST['status'];
$singkatan_kategori = $_POST['singkatan_kategori'];
$kecamatan = $_POST['kecamatan'];
$id_user = $_POST['id_user'];

// Menerima file gambar, dokumen PDF, dan gambar
$ktpSeniman = $_FILES['ktp_seniman'];
$suratKeterangan = $_FILES['surat_keterangan'];
$passFoto = $_FILES['pass_foto'];

// Direktori penyimpanan file
$uploadDirKTP = 'uploads/ktp_seniman/';
$uploadDirSurat = 'uploads/surat_keterangan/';
$uploadDirPassFoto = 'uploads/pass_foto/';

// Mengunggah gambar KTP Seniman
$ktpSenimanFileName = $uploadDirKTP . basename($ktpSeniman['name']);
move_uploaded_file($ktpSeniman['tmp_name'], $ktpSenimanFileName);

// Mengunggah dokumen PDF Surat Keterangan
$suratKeteranganFileName = $uploadDirSurat . basename($suratKeterangan['name']);
move_uploaded_file($suratKeterangan['tmp_name'], $suratKeteranganFileName);

// Mengunggah gambar Pass Foto
$passFotoFileName = $uploadDirPassFoto . basename($passFoto['name']);
move_uploaded_file($passFoto['tmp_name'], $passFotoFileName);

// Enkripsi nilai $nik dengan Base64
$encryptedNik = base64_encode($nik);

// Menyimpan data ke database
$today = date('Y-m-d'); // Mengambil tanggal hari ini
$nextYear = date('Y') + 1; // Mengambil tahun berikutnya
$tgl_pembuatan = $today;
$tgl_berlaku = $nextYear . '-12-31';

$query = "INSERT INTO seniman (nik, nama_seniman, jenis_kelamin, tempat_lahir, tanggal_lahir, alamat_seniman, no_telpon, nama_organisasi, jumlah_anggota, status, tgl_pembuatan, tgl_berlaku, id_user, singkatan_kategori, kecamatan, ktp_seniman, surat_keterangan, pass_foto) 
          VALUES ('$encryptedNik', $namaLengkap, $jenisKelamin, $tempatLahir, $tanggalLahir, $alamat, $noHandphone, $namaOrganisasi, $jumlahAnggota, $status, '$tgl_pembuatan', '$tgl_berlaku', $id_user, $singkatan_kategori, $kecamatan, '$ktpSenimanFileName','$suratKeteranganFileName', '$passFotoFileName')";

if ($konek->query($query) === TRUE) {
    $response['status'] = 'success';
    $response['message'] = 'Data berhasil disimpan';
} else {
    $response['status'] = 'error';
    $response['message'] = 'Gagal menyimpan data: ' . $konek->error;
}

// Mengirim respons ke aplikasi Android dalam format JSON
header('Content-type: application/json');
echo json_encode($response);
?>
