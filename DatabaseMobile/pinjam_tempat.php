<?php
require('Koneksi.php');

header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
$nik_sewa = $_POST['nik_sewa'];
$nama_peminjam = $_POST['nama_peminjam'];
$nama_tempat = $_POST['nama_tempat'];
$deskripsi_sewa_tempat = $_POST['deskripsi_sewa_tempat'];
$nama_kegiatan_sewa = $_POST['nama_kegiatan_sewa'];
$jumlah_peserta= $_POST['jumlah_peserta'];
$instansi= $_POST['instansi'];
$surat_ket_sewa = $_FILES['surat_ket_sewa'];
$tgl_awal_peminjaman = $_POST['tgl_awal_peminjaman'];
$tgl_akhir_peminjaman = $_POST['tgl_akhir_peminjaman'];
$catatan = $_POST['catatan'];
$id_tempat = $_POST['id_tempat'];
$id_user = $_POST['id_user'];


$uploadDirKTP = 'uploads/pinjam/';

$ktpSenimanFileName = $uploadDirKTP . basename($surat_ket_sewa['name']);
move_uploaded_file($surat_ket_sewa['tmp_name'], $ktpSenimanFileName);

// echo json_encode($_POST);
// exit();
$sql = "INSERT INTO sewa_tempat
        (nik_sewa, nama_peminjam, nama_tempat, deskripsi_sewa_tempat, nama_kegiatan_sewa, jumlah_peserta, instansi, surat_ket_sewa, tgl_awal_peminjaman, tgl_akhir_peminjaman, status, catatan, id_tempat, id_user) 
        VALUES
        ($nik_sewa, $nama_peminjam, $nama_tempat, $deskripsi_sewa_tempat, $nama_kegiatan_sewa, $jumlah_peserta, $instansi, '$ktpSenimanFileName', $tgl_awal_peminjaman, $tgl_akhir_peminjaman, 'diajukan ', $catatan, $id_tempat, $id_user )";

$response = array();
if ($konek->query($sql) === TRUE) {
    $response["kode"] = 1;
    $response["pesan"] = "Data telah berhasil dimasukkan.";
} else {
    $response["kode"] = 2;
    $response["pesan"] = "Error: " . $sql . "<br>" . $konek->error;
}



$konek->close();
}else{
  $response = array("status"=>"error", "message"=>"not post method");
}



echo json_encode($response);
?>