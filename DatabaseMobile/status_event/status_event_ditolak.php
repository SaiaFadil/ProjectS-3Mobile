<?php
require('../Koneksi.php');

$response = array(); 

$sql = "SELECT DISTINCT events.id_user, events.nama_pengirim, events.status, events.catatan, detail_events.*
        FROM events 
        JOIN detail_events ON events.id_event = events.id_event
        WHERE events.status = 'ditolak' 
        ORDER BY detail_events.tanggal_awal DESC;";

$result = $konek->query($sql);

if ($result) {
    if ($result->num_rows > 0) {
        $events = array();

        while ($row = $result->fetch_assoc()) {
            $events[] = $row; 
        }

        $response["kode"] = 1;
        $response["pesan"] = "Data Tersedia";
        $response["data"] = $events;
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Data Tidak Tersedia";
    }
} else {
    $response["kode"] = -1;
    $response["pesan"] = "Error in query: " . $konek->error;
}

echo json_encode($response);

mysqli_close($konek);
?>
