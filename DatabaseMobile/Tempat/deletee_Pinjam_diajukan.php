<?php
require('../Koneksi.php');

$id_sewa = $_POST['id_sewa'];

$cek_iduser = "SELECT * FROM `sewa_tempat` WHERE id_sewa = $id_sewa;";
$eksekusi_cek = mysqli_query($konek, $cek_iduser);
$jumlah_cek = mysqli_num_rows($eksekusi_cek);

$response = array();
if ($jumlah_cek == 1) {
    $perintah = "DELETE FROM sewa_tempat  
     WHERE `id_sewa` = $id_sewa;";
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