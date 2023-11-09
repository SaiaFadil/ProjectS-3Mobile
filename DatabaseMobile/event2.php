<?php
require('Koneksi.php');

$nama_pengirim = $_POST['nama_pengirim'];
$status = $_POST['status'];
// $catatan= $_POST['catatan'];
$id_detail = $_POST['id_detail'];
// $id_sewa= $_POST['id_sewa'];
// $id_user= $_POST['id_user'];



$sql = "INSERT INTO events
        ( nama_pengirim, status, catatan, id_detail, id_sewa, id_user) 
        VALUES
        ( '$nama_pengirim', '$status', '', '$id_detail', '1', '32')";

$response = array();
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