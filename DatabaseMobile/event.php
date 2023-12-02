<?php
require('Koneksi.php');


$nama_event = $_POST['nama_event'];
$deskripsi = $_POST['deskripsi'];
$tempat_event = $_POST['tempat_event'];
$tanggal_awal = $_POST['tanggal_awal'];
$tanggal_akhir= $_POST['tanggal_akhir'];
$link_pendaftaran= $_POST['link_pendaftaran'];
$poster_event = $_FILES['poster_event'];


$posterDir = 'uploads/events/';

// Mengunggah gambar KTP Seniman
$posterFileName = $posterDir . basename($poster_event['name']);
move_uploaded_file($poster_event['tmp_name'], $posterFileName);


$sql = "INSERT INTO detail_events
        (nama_event, deskripsi, tempat_event, tanggal_awal, tanggal_akhir, link_pendaftaran, poster_event) 
        VALUES
        ($nama_event, $deskripsi, $tempat_event, $tanggal_awal, $tanggal_akhir, $link_pendaftaran, '$posterFileName')";

$response = array();
if ($konek->query($sql) === TRUE) {
    $response["kode"] = 1;
    $response["pesan"] = "Data telah berhasil dimasukkan.";


       // Ambil id_detail dari detail_events yang baru saja dimasukkan
       $result = $konek->query("SELECT id_detail FROM detail_events ORDER BY id_detail DESC LIMIT 1");
       $ambil = $result->fetch_assoc();
       $id_detail_baru = $ambil['id_detail'];
   
       // Ambil id_event dari events yang baru saja dimasukkan
       $result1 = $konek->query("SELECT id_event FROM events ORDER BY id_event DESC LIMIT 1");
       $ambil1 = $result1->fetch_assoc();
       $id_event_baru = $ambil1['id_event'];
   
       // Update id_detail pada record events yang baru dengan id_detail baru
       $sqlupdate = "UPDATE events SET id_detail = '$id_detail_baru' WHERE id_event = '$id_event_baru'";
       
       if ($konek->query($sqlupdate) === TRUE) {
           $response["kode"] = 1;
           $response["pesan"] = "Data telah berhasil dimasukkan.";
       } else {
           $response["kode"] = 2;
           $response["pesan"] = "Error updating record: " . $konek->error;
       }
} else {
    $response["kode"] = 2;
    $response["pesan"] = "Error: " . $sql . "<br>" . $konek->error;
}


$konek->close();

echo json_encode($response);
?>