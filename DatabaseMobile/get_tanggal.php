<?php
require('Koneksi.php');

header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'GET') {

    $idTempat = $_GET['id_tempat'];

    $sql = "SELECT 
        IFNULL(DATE(tgl_awal_peminjaman), '0000-00-00') AS tgl_awal, 
        IFNULL(DATE(tgl_akhir_peminjaman), '0000-00-00') AS tgl_akhir
    FROM sewa_tempat WHERE id_tempat = $idTempat
    "; 

    $result = mysqli_query($konek, $sql);

    if  ($result->num_rows > 0) {
        $response = array("status"=>"success", "message"=>"data didapatkan", "data"=> $result->fetch_all(MYSQLI_ASSOC));
    }else{
        $response = array("status"=> "success","message"=> "data gagal didapatkan", "data"=>[]);
    }
} else {
    $response = array("status"=> "error","message"=> "data gagal didapatkan");
}

echo json_encode($response);
mysqli_close($konek);
?>
