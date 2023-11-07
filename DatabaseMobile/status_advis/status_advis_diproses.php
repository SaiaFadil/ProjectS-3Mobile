<?php
require('../Koneksi.php');

$id_user = $_POST['id_user'];

$response = array(); 

$sql = "SELECT * FROM surat_advis WHERE status = 'proses' AND id_user = '$id_user' ORDER BY tgl_advis desc;";
$result = $konek->query($sql);

if ($result->num_rows > 0) {
    $surat_advis = array();

    while ($row = $result->fetch_assoc()) {
        $surat_advis[] = $row; 
    }

    $response["kode"] = 1;
    $response["pesan"] = "Data Tersedia";
    $response["data"] = $surat_advis;
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Data Tidak Tersedia";
}

echo json_encode($response);

mysqli_close($konek);
?>
