<?php
require('Koneksi.php');


    $email = $_POST['email'];
 
    
    $sql = "SELECT * FROM users WHERE email = '$email' LIMIT 1";
    $result = $konek->query($sql);
 
    if ($result->num_rows == 1) {
        $response["kode"] = 1;
        $response["pesan"] = "Lanjut";
        
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Akun Belum Terdaftar";
    }

echo json_encode($response);
mysqli_close($konek);
?>
