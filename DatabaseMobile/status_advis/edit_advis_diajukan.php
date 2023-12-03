<?php
require('../Koneksi.php');

// Menerima data dari aplikasi Android
$id_advis = $_POST['id_advis'];
$deskripsi_advis = $_POST['deskripsi_advis'];
$tgl_advis = $_POST['tgl_advis'];
$tempat_advis = $_POST['tempat_advis'];
$current_time = date("Y-m-d H:i:s");



$cek_iduser = "SELECT * FROM `surat_advis` WHERE id_advis = '$id_advis'";
$eksekusi_cek = mysqli_query($konek, $cek_iduser);
$jumlah_cek = mysqli_num_rows($eksekusi_cek);

$response = array();
if ($jumlah_cek == 1) {

    $perintah = "UPDATE `surat_advis` 
    SET `deskripsi_advis` = '$deskripsi_advis',
     `tgl_advis` = '$tgl_advis', 
     `tempat_advis` = '$tempat_advis', `updated_at` = '$current_time'
     WHERE 
      `id_advis` = $id_advis;";

    $eksekusi = mysqli_query($konek, $perintah);

    if ($eksekusi) {
        $response["kode"] = 1;
        $response["pesan"] = "Update Berhasil";

    } else {
        $response["kode"] = 2;
        $response["pesan"] = "Update Gagal";
    }
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Ada Kesalahan";
}

echo json_encode($response);
mysqli_close($konek);
?>