<?php
// Koneksi ke database
require "Koneksi.php";

$konek= new mysqli($host, $username, $password, $database);

if ($konek->connect_error) {
    die('Koneksi gagal: ' . $konek->connect_error);
}

// Menerima data dari aplikasi Android
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nik = $_POST['nik'];
    $nomor_induk = $_POST['nomor_induk'];
    $nama_seniman = $_POST['nama_seniman'];
    $jenis_kelamin = $_POST['jenis_kelamin'];
    $ttl_seniman = $_POST['ttl_seniman'];
    $alamat_seniman = $_POST['alamat_seniman'];
    $phone_seniman = $_POST['phone_seniman'];
    $nama_organisasi = $_POST['nama_organisasi'];
    $jumlah_anggota = $_POST['jumlah_anggota'];
    $surat_keterangan = $_POST['surat_keterangan'];
    $ktp_seniman = $_POST['ktp_seniman'];
    $pass_foto = $_POST['pass_foto'];
    $tgl_pembuatan = $_POST['tgl_pembuatan'];
    $tgl_berlaku = $_POST['tgl_berlaku'];

    // Menyimpan data ke tabel "seniman"
    $query = "INSERT INTO seniman (nik, nomor_induk, nama_seniman, jenis_kelamin, ttl_seniman, alamat_seniman, phone_seniman, nama_organisasi, jumlah_anggota, surat_keterangan, ktp_seniman, pass_foto, tgl_pembuatan, tgl_berlaku) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    $stmt = $konek->prepare($query);
    $stmt->bind_param('ssssssssssssss', $nik, $nomor_induk, $nama_seniman, $jenis_kelamin, $ttl_seniman, $alamat_seniman, $phone_seniman, $nama_organisasi, $jumlah_anggota, $surat_keterangan, $ktp_seniman, $pass_foto, $tgl_pembuatan, $tgl_berlaku);

    if ($stmt->execute()) {
        echo 'Data berhasil disimpan.';
    } else {
        echo 'Gagal menyimpan data: ' . $stmt->error;
    }

    $stmt->close();
} else {
    echo 'Permintaan tidak valid.';
}

