<?php
require('../Koneksi.php');

$id_advis = $_POST['id_advis'];

$cek_iduser = "SELECT * FROM `surat_advis` WHERE id_advis = '$id_advis'";
$eksekusi_cek = mysqli_query($konek, $cek_iduser);
$jumlah_cek = mysqli_num_rows($eksekusi_cek);

$response = array();
if ($jumlah_cek == 1) {
    $perintah = "DELETE FROM surat_advis  
     WHERE 
      `id_advis` = $id_advis;";
    $eksekusi = mysqli_query($konek, $perintah);
    if ($eksekusi) {
        $response["kode"] = 1;
        $response["pesan"] = "Hapus Berhasil";
    } else {
        $response["kode"] = 2;
        $response["pesan"] = "Hapus Gagal";
    }
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Ada Kesalahan";
}

echo json_encode($response);
mysqli_close($konek);
?>