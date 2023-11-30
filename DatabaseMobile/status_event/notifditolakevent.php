<?php
require('../Koneksi.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id_event = $_POST['id_event'];

    $sql = "SELECT DISTINCT events.id_user, events.nama_pengirim, events.status, events.catatan, detail_events.*
        FROM events 
        JOIN detail_events ON events.id_event = events.id_event
        WHERE events.status = 'ditolak' 
        ORDER BY detail_events.tanggal_awal DESC;";
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
