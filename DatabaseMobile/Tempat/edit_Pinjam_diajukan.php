<?php
require('../Koneksi.php');

// Menerima data dari aplikasi Android
$nama_peminjam = $_POST['nama_peminjam'];
$nik_sewa = $_POST['nik_sewa'];
$nama_tempat = $_POST['nama_tempat'];
$deskripsi_sewa_tempat = $_POST['deskripsi_sewa_tempat'];
$nama_kegiatan_sewa = $_POST['nama_kegiatan_sewa'];
$jumlah_peserta= $_POST['jumlah_peserta'];
$instansi= $_POST['instansi'];
$surat_ket_sewa = $_FILES['surat_ket_sewa'];
$tgl_awal_peminjaman = $_POST['tgl_awal_peminjaman'];
$tgl_akhir_peminjaman = $_POST['tgl_akhir_peminjaman'];
$status = $_POST['status'];
$catatan = $_POST['catatan'];
$id_tempat = $_POST['id_tempat'];
$id_user = $_POST['id_user'];

$cek_iduser = "SELECT * FROM `sewa_tempat` WHERE id_sewa = '$id_sewa'";
$eksekusi_cek = mysqli_query($konek, $cek_iduser);
$jumlah_cek = mysqli_num_rows($eksekusi_cek);

$response = array();
if ($jumlah_cek == 1) {

    $perintah = "UPDATE `sewa_tempat` 
    SET `nik_sewa` = '$nik_sewa',
     `nama_peminjam` = '$nama_peminjam', 
     `nama_tempat` = '$nama_tempat' , `deskripsi_sewa_tempat` = '$deskripsi_sewa_tempat',  `nama_kegiatan_sewa` = '$nama_kegiatan_sewa',  `jumlah_peserta` = '$jumlah_peserta',  `instansi` = '$instansi', `surat_ket_sewa ` = '$surat_ket_sewa ', 
     `tgl_awal_peminjaman` = '$tgl_awal_peminjaman' , `tgl_akhir_peminjaman` = '$tgl_akhir_peminjaman',  `status` = '$status',  `catatan` = '$catatan',  `id_tempat` = '$id_tempat','id_user' = '$id_user'
     WHERE 
      `id_sewa` = $id_sewa;";
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

   