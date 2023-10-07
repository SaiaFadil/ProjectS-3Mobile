<?php
require('Koneksi.php');

// Menerima data dari aplikasi Android
$em = $_POST['email']; // 'email' harus sesuai dengan key yang dikirim dari Android
$pas = $_POST['password']; // 'password' harus sesuai dengan key yang dikirim dari Android

$perintah = "SELECT * FROM `users` WHERE email = '$em';";
$eksekusi = mysqli_query($konek, $perintah);
$cek = mysqli_affected_rows($konek);

$response = array();

if ($cek > 0) {
    $ambil = mysqli_fetch_object($eksekusi);
    $password_db = $ambil->password;
    $role_db = $ambil->role;
    if ($pas == $password_db) {
        if ($role_db == 'masyarakat') {

            // Password benar
            $response["kode"] = 1;
            $response["pesan"] = "Data Tersedia";
            $response["data"] = array();
            $F["id_user"] = $ambil->id_user;
            $F["nama_lengkap"] = $ambil->nama_lengkap;
            $F["no_telpon"] = $ambil->no_telpon;
            $F["tanggal_lahir"] = $ambil->tanggal_lahir;
            $F["tempat_lahir"] = $ambil->tempat_lahir;
            $F["role"] = $ambil->role;
            $F["email"] = $ambil->email;
            $F["password"] = $ambil->password;
            $F["verifikasi"] = $ambil->verifikasi;
            array_push($response["data"], $F);
        } else {
            $response["kode"] = 3;
            $response["pesan"] = "User Bukan Masyarakat";
        }
    } else {
        // Password salah
        $response["kode"] = 2;
        $response["pesan"] = "Password Salah";
    }
} else {
    // Email tidak ditemukan di database
    $response["kode"] = 0;
    $response["pesan"] = "Data Tidak Tersedia";
}

echo json_encode($response);
mysqli_close($konek);

?>
