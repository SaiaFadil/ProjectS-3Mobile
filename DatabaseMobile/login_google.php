<?php
require('Koneksi.php');

header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $email = $_POST['email'];
 
    
    $sql = "SELECT * FROM users WHERE email = '$email' LIMIT 1";
    $result = $konek->query($sql);
 
    if ($result->num_rows == 1) {
        $user = $result->fetch_assoc();
        $role_db = $user['role'];

            if ($role_db == 'masyarakat') {
                $response["kode"] = 1;
                $response["pesan"] = "Data Tersedia";
                $response["data"] = array();
                $response["data"]["id_user"] = $user['id_user'];
                $response["data"]["nama_lengkap"] = $user['nama_lengkap'];
                $response["data"]["no_telpon"] = $user['no_telpon'];
                $response["data"]["jenis_kelamin"] = $user['jenis_kelamin'];
                $response["data"]["tanggal_lahir"] = $user['tanggal_lahir'];
                $response["data"]["tempat_lahir"] = $user['tempat_lahir'];
                $response["data"]["role"] = $user['role'];  
                $response["data"]["email"] = $user['email'];
                $response["data"]["password"] = $user['password'];
                $response["data"]["verifikasi"] = $user['verifikasi'];
            } else {
                $response["kode"] = 2;
                $response["pesan"] = "User Bukan Masyarakat";
            }
        
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Akun Belum Terdaftar";
    }
} else {
    $response = array("kode" => 3, "pesan" => "Metode tidak valid");
}

echo json_encode($response);
mysqli_close($konek);
?>
