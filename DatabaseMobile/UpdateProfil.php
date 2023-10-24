<?php
require('Koneksi.php');

// Menerima data dari aplikasi Android
$iduser = $_POST['id_user'];
$nam = $_POST['nama_lengkap'];
$telp = $_POST['no_telpon'];
$jeniskel = $_POST['jenis_kelamin'];
$tgllahir = $_POST['tanggal_lahir'];
$tmptlahir = $_POST['tempat_lahir'];
$em = $_POST['email'];

$cek_iduser = "SELECT * FROM `users` WHERE id_user = '$iduser'";
$eksekusi_cek = mysqli_query($konek, $cek_iduser);
$jumlah_cek = mysqli_num_rows($eksekusi_cek);

$response = array();
if ($jumlah_cek > 0) {
    
    $perintah = "UPDATE `users` SET
     nama_lengkap = '$nam' ,
     no_telpon = '$telp',
     jenis_kelamin = '$jeniskel',
     tanggal_lahir = '$tgllahir',
     tempat_lahir = '$tmptlahir',
     email = '$em' where id_user = '$iduser';
     ";

    $eksekusi = mysqli_query($konek, $perintah);

    if ($eksekusi) {
        $response["kode"] = 1;
        $response["pesan"] = "Update Berhasil";
       
    } else {
        $response["kode"] = 2;
        $response["pesan"] = "Update Gagal";
    }
}else{
    $response["kode"] = 0;
        $response["pesan"] = "Ada Kesalahan";
}

echo json_encode($response);
mysqli_close($konek);
?>
