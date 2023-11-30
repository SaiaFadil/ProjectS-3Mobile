<?php
require('../Koneksi.php');

$id_user = $_POST['id_user'];

$response = array(); 

$sql = "SELECT * FROM sewa_tempat WHERE status = 'proses' AND id_user = '$id_user' ORDER BY tgl_awal_peminjaman desc;";
$result = $konek->query($sql);

if ($result->num_rows > 0) {
    $sewa_tempat = array();

    while ($row = $result->fetch_assoc()) {
        $sewa_tempat[] = $row; 
    }

    $response["kode"] = 1;
    $response["pesan"] = "Data Tersedia";
    $response["data"] = $sewa_tempat;
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Data Tidak Tersedia";
}

echo json_encode($response);

mysqli_close($konek);
?>
