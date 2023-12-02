<?php
require('../Koneksi.php');

$id_user = $_POST['id_user'];

$response = array(); 

$sql = "SELECT * FROM perpanjangan join seniman on seniman.id_seniman = perpanjangan.id_seniman WHERE perpanjangan.status = 'diterima' AND perpanjangan.id_user = '$id_user' ORDER BY perpanjangan.tgl_pembuatan desc;";
$result = $konek->query($sql);

if ($result->num_rows > 0) {
    $seniman = array();

    while ($row = $result->fetch_assoc()) {
        $seniman[] = $row; 
    }

    $response["kode"] = 1;
    $response["pesan"] = "Data Tersedia";
    $response["data"] = $seniman;
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Data Tidak Tersedia";
}

echo json_encode($response);

mysqli_close($konek);
?>
