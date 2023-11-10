<?php
require('../Koneksi.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id_user = $_POST['id_user'];

    $sql = "SELECT * FROM surat_advis WHERE id_user = '$id_user' AND status = 'ditolak'  order by tgl_advis desc;";
    $result = $konek->query($sql);

    if ($result->num_rows > 0) {
        $detail = $result->fetch_assoc();

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
