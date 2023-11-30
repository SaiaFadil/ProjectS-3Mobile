<?php
require('../Koneksi.php');

// Menerima data dari aplikasi Android
$id_event = $_POST['id_event'];
$nama_event = $_POST['nama_event'];
$deskripsi = $_POST['deskripsi'];
$tempat_event = $_POST['tempat_event'];
$tanggal_awal = $_POST['tanggal_awal'];
$tanggal_akhir = $_POST['tanggal_akhir'];
$link_pendaftaran = $_POST['link_pendaftaran'];
$poster_event = $_FILES['poster_event'];

$posterDir = '../uploads/events/';

// Mengunggah gambar KTP Seniman
$posterFileName = $posterDir . basename($poster_event['name']);
move_uploaded_file($poster_event['tmp_name'], $posterFileName);

$cek_iduser = "SELECT * FROM events WHERE id_event = $id_event LIMIT 1";
$eksekusi_cek = mysqli_query($konek, $cek_iduser);
$jumlah_cek = mysqli_num_rows($eksekusi_cek);

if ($jumlah_cek > 0) {
    $response = array();
    $idDetail = $eksekusi_cek->fetch_assoc()["id_detail"];
    if ($jumlah_cek == 1) {

        $sql = "UPDATE events SET status = 'diajukan' WHERE id_event = $id_event";
        $result = mysqli_query($konek, $sql);

        if ($result === true) {
            $perintah = "UPDATE detail_events 
            SET nama_event = $nama_event, deskripsi = $deskripsi, tempat_event = $tempat_event, 
            tanggal_awal = $tanggal_awal, tanggal_akhir = $tanggal_akhir, 
            link_pendaftaran = $link_pendaftaran, poster_event = '$posterFileName'
            WHERE id_detail = '$idDetail';";

            $eksekusi = mysqli_query($konek, $perintah);

            if ($eksekusi) {
                $response = array("status" => "success", "message" => "data di edit");
            } else {
                $response = array("status" => "error", "message" => "data gagal edit");
            }
        }else{
            $response = array("status" => "error", "message" => "data status gagal edit");
        }
    } else {
        $response = array("status" => "error", "message" => "data gagal edit");
    }
} else {
    $response = array("status" => "error", "message" => "id detail not -> $id_event");
}

echo json_encode($response);
mysqli_close($konek);