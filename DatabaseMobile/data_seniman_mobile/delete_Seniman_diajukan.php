<?php
require('../Koneksi.php');

$id_seniman = $_POST['id_seniman'];

$cek_iduser = "SELECT * FROM `seniman` WHERE id_seniman = '$id_seniman'";
$eksekusi_cek = mysqli_query($konek, $cek_iduser);
$jumlah_cek = mysqli_num_rows($eksekusi_cek);

$response = array();
if ($jumlah_cek == 1) {
    // Mendapatkan path file dari database
    $get_path_query = "SELECT * FROM seniman WHERE id_seniman = '$id_seniman'";
    $get_path_result = mysqli_query($konek, $get_path_query);
    $row = mysqli_fetch_assoc($get_path_result);
    $path_ktp = $row['ktp_seniman'];
    $path_pass_foto = $row['pass_foto'];
    $path_surat_keterangan = $row['surat_keterangan'];

    // Hapus file dari direktori
    if (file_exists($path_ktp)) {
        unlink($path_ktp);
    }

    if (file_exists($path_pass_foto)) {
        unlink($path_pass_foto);
    }

    if (file_exists($path_surat_keterangan)) {
        unlink($path_surat_keterangan);
    }

    // Hapus record dari database
    $perintah = "DELETE FROM seniman WHERE `id_seniman` = $id_seniman;";
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
