<?php
require('../Koneksi.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id_perpanjangan = $_POST['id_perpanjangan'];

    $sql = "SELECT p.*, s.nik, s.nomor_induk, s.nama_seniman, s.catatan FROM perpanjangan p
            JOIN seniman s ON p.id_seniman = s.id_seniman
            WHERE p.id_perpanjangan = '$id_perpanjangan';";

    $result = $konek->query($sql);

    if ($result->num_rows > 0) {
        $detail = $result->fetch_assoc();

        // Dekripsi kolom 'nik' jika tidak kosong
        if (!empty($detail['nik'])) {
            $detail['nik'] = base64_decode($detail['nik']);
            $detail['nik'] = preg_replace("/[^0-9]/", "", $detail['nik']); // Hapus karakter selain angka
            $detail['nik'] = trim($detail['nik']); 
        }

        $response["kode"] = 1;
        $response["pesan"] = "Data Tersedia";
        $response["data"] = $detail;
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Data Tidak Ditemukan";
    }
} else {
    $response["kode"] = 2;
    $response["pesan"] = "Metode tidak valid";
}

echo json_encode($response);
$konek->close();
?>
