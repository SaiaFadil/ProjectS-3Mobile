<?php
require('Koneksi.php');


$id_user = $_POST['id_user'];
 
    
    $sql = "SELECT * FROM seniman WHERE id_user = '$id_user' LIMIT 1";
    $result = $konek->query($sql);
 
    if ($result->num_rows == 1) {
        $seniman = $result->fetch_assoc();



        $response["kode"] = 1;
        $response["pesan"] = "Data Tersedia";
        $response["data"] = $seniman;
        
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "User Tidak Memiliki Nomor Induk Seniman";
    }

echo json_encode($response);
mysqli_close($konek);
?>
