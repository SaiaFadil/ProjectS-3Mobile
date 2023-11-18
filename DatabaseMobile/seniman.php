<?php
include "Koneksi.php"; // Sertakan file koneksi ke database

// Ambil data dari permintaan POST
$namaSeniman = $_POST['nama_seniman'];
$kategoriSeniman = $_POST['kategori_seniman'];
// Ambil data lainnya sesuai kebutuhan

// Buat query untuk insert data ke tabel "seniman"
$query = "INSERT INTO seniman (nama_seniman, kategori_seniman, ...) 
          VALUES ('$namaSeniman', '$kategoriSeniman', ...)";

if ($konek->query($query) === TRUE) {
    // Data berhasil disimpan
    echo json_encode(array("message" => "Data berhasil disimpan"));
} else {
    // Gagal menyimpan data
    echo json_encode(array("message" => "Gagal menyimpan data: " . $koneksi->error));
}

$konek->close();
?>
