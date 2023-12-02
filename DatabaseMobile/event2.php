<?php
require('Koneksi.php');

$nama_pengirim = $_POST['nama_pengirim'];
$status = $_POST['status'];
// $id_detail = $_POST['id_detail'];
$id_user = $_POST['id_user'];

$response = array();

$sql = "INSERT INTO events (nama_pengirim, status, id_user) 
        VALUES ('$nama_pengirim', '$status', '$id_user')";

if ($konek->query($sql) === TRUE) {
    $response["kode"] = 1;
    $response["pesan"] = "Data telah berhasil dimasukkan.";

 
} else {
    $response["kode"] = 2;
    $response["pesan"] = "Error: " . $sql . "<br>" . $konek->error;
}

$konek->close();

echo json_encode($response);
?>