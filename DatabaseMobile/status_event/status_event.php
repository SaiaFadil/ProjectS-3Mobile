<?php
require('../Koneksi.php');

// $id_user = $_POST['id_user'];

$response = array();

$id_user = $_GET["id_user"];

$sql = "SELECT e.id_event, e.nama_pengirim, e.status, d.*
FROM events AS e
JOIN detail_events AS d 
ON d.id_detail = e.id_detail 
WHERE e.id_user = $id_user
ORDER BY d.tanggal_awal DESC
    ;
";
$result = $konek->query($sql);

if ($result->num_rows > 0) {
    $event = array();

    while ($row = $result->fetch_assoc()) {
        $event[] = $row;
    }

    $response = array("status" => "success", "message" => "data diambil", "data" => $event);
} else {
    $response = array("status" => "error", "message" => "data gagal diambil");
}

echo json_encode($response);

mysqli_close($konek);


?>
