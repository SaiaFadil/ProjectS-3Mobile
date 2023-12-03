<?php
require('Koneksi.php');

// Mendapatkan nilai dari formulir atau variabel yang Anda miliki
$nomor_induk = $_POST['nomor_induk'];
$nama_advis = $_POST['nama_advis'];
$alamat_advis = $_POST['alamat_advis'];
$deskripsi_advis = $_POST['deskripsi_advis'];
$tgl_advis = $_POST['tgl_advis'];
$tempat_advis = $_POST['tempat_advis'];
$status = $_POST['status'];
$catatan = $_POST['catatan'];
$id_user = $_POST['id_user'];
$id_seniman = $_POST['id_seniman'];
$current_time = date("Y-m-d H:i:s");
// Menyimpan ke tabel pengguna
// Membuat query SQL untuk melakukan INSERT
$sql = "INSERT INTO surat_advis(nomor_induk, nama_advis, alamat_advis, deskripsi_advis, tgl_advis, tempat_advis, status, catatan, id_user,id_seniman,created_at) VALUES ('$nomor_induk', '$nama_advis', '$alamat_advis', '$deskripsi_advis', '$tgl_advis', '$tempat_advis', '$status', '$catatan', '$id_user', '$id_seniman','$current_time')";

$response = array();
if ($konek->query($sql) === TRUE) {
    $response["kode"] = 1;
    $response["pesan"] = "Data telah berhasil dimasukkan.";
} else {
    $response["kode"] = 2;
    $response["pesan"] = "Error: " . $sql . "<br>" . $konek->error;
}

// Menutup koneksi ke database
$konek->close();

echo json_encode($response);
?>