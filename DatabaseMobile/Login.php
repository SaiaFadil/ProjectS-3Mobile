<?php
require('Koneksi.php');

header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $email = $_POST['email'];
    $password = $_POST['password'];

    // Lakukan sanitasi input untuk mencegah SQL injection (gunakan prepared statement jika memungkinkan)
    $email = mysqli_real_escape_string($konek, $email);

    $sql = "SELECT * FROM users WHERE email = '$email' LIMIT 1";
    $result = $konek->query($sql);

    if ($result->num_rows == 1) {
        $user = $result->fetch_assoc();
        $hashedPasswordFromDatabase = $user['password'];

        // Memeriksa apakah password yang dimasukkan oleh pengguna cocok dengan hash di database
        if (password_verify($password, $hashedPasswordFromDatabase)) {
            $role_db = $user['role'];

            if ($role_db == 'masyarakat') {
                $response["kode"] = 1;
                $response["pesan"] = "Data Tersedia";
                $response["data"] = $user;
            } else {
                $response["kode"] = 3;
                $response["pesan"] = "User Bukan Masyarakat";
            }
        } else {
            $response["kode"] = 2;
            $response["pesan"] = "Email atau Password Salah";
        }
    } else {
        $response["kode"] = 2;
        $response["pesan"] = "Email atau Password Salah";
    }
} else {
    $response = array("kode" => 2, "pesan" => "Metode tidak valid");
}

echo json_encode($response);
mysqli_close($konek);
?>
