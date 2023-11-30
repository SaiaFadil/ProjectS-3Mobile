<?php
require('../Koneksi.php');

$id_event = $_POST['id_event'];

$response = array();

$perintah = "SELECT id_detail FROM events WHERE id_event = $id_event";
$result = mysqli_query($konek, $perintah);

if ($result) {
    if (mysqli_num_rows($result) > 0) {
        $idDetail = $result->fetch_assoc()['id_detail'];

        $deleteQuery = "DELETE FROM events WHERE id_event = $id_event";
        $deleteResult = mysqli_query($konek, $deleteQuery);
        if ($deleteResult) {

            $deleteQuery = "DELETE FROM detail_events WHERE id_detail = $idDetail";
            $deleteResult = mysqli_query($konek, $deleteQuery);

            if ($deleteQuery) {
                $response = array("status" => "success", "message" => "Data Berhasil Dihapus");
            } else {
                $response = array("status" => "error", "message" => "Failed to delete data: " . mysqli_error($konek));
            }
        } else {
            $response = array("status" => "error", "message" => "Failed to delete data: " . mysqli_error($konek));
        }
    } else {
        $response = array("status" => "error", "message" => "No data found");
    }
} else {
    $response = array("status" => "error", "message" => "Query execution failed: " . mysqli_error($konek));
}

echo json_encode($response);
mysqli_close($konek);
