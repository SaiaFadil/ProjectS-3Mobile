<?php
require('Koneksi.php');

// Menerima data dari aplikasi Android
$nam = $_POST['nama_lengkap'];
$telp = $_POST['no_telpon'];
$em = $_POST['email'];
$pas = $_POST['password'];

// Periksa apakah email sudah terdaftar
$cek_email = "SELECT * FROM `users` WHERE email = '$em'";
$eksekusi_cek = mysqli_query($konek, $cek_email);
$jumlah_cek = mysqli_num_rows($eksekusi_cek);

$response = array();
if ($jumlah_cek > 0) {
    $response["kode"] = 0;
    $response["pesan"] = "Email sudah terdaftar";
} else {
    // Jika email belum terdaftar, lakukan proses registrasi
    $perintah = "INSERT INTO `users` (nama_lengkap, no_telpon, email, password,role) VALUES ('$nam', '$telp', '$em', '$pas','masyarakat')";
    $eksekusi = mysqli_query($konek, $perintah);

    if ($eksekusi) {
        $response["kode"] = 1;
        $response["pesan"] = "Registrasi berhasil";
    } else {
        $response["kode"] = 2;
        $response["pesan"] = "Registrasi gagal";
    }
}

echo json_encode($response);
mysqli_close($konek);
?>
