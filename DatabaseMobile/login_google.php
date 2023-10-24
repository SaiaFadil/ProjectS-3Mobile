<?php

/**
 * Digunakan untuk login dengan google.
 */

require "Koneksi.php";

header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'POST') {

    // post request
    $email = $_POST['email'];
        // get data user
        $sql = "SELECT * FROM users WHERE email = '$email' LIMIT 1";
        $result = $konek->query($sql);

        // jika email exist
        if ($result->num_rows == 1) {
            $user = $result->fetch_assoc(); // get user data
            $response = array('status' => 'success', 'message' => 'Login berhasil', 'data' => $user);
        } else {
            $response = array('status' => 'error', 'message' => 'Daftarkan akunmu terlebih dahulu.');
        }

        // close koneksi
        $konek->close();
    }
 else {
    $response = array("status" => "error", "message" => "not post method");
}

// show response
echo json_encode($response);
