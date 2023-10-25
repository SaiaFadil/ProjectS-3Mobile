<?php 
require('Koneksi.php');

$perintah = "SELECT * FROM `users` ;";
$eksekusi = mysqli_query($konek,$perintah);
$cek = mysqli_affected_rows($konek);

$response = array();

if($cek > 0){
    $response["kode"] = 1;
    $response["pesan"] = "Data Tersedia";
    $response["data"] = array();
    while ($ambil = mysqli_fetch_object($eksekusi)) {
        $F["id_user"] = $ambil->id_user;
        $F["nama_lengkap"] = $ambil->nama_lengkap;
        $F["no_telpon"] = $ambil->no_telpon;
        $F["jenis_kelamin"] = $ambil->jenis_kelamin;
        $F["tanggal_lahir"] = $ambil->tanggal_lahir;
        $F["tempat_lahir"] = $ambil->tempat_lahir;
        $F["role"] = $ambil->role;
        $F["email"] = $ambil->email;
        $F["password"] = $ambil->password;
        $F["verifikasi"] = $ambil->verifikasi;
        array_push($response["data"],$F);
    }
}
else {
    $response["kode"] = 0;
    $response["pesan"] = "Data Tidak Tersedia";
}

echo json_encode($response);
mysqli_close($konek);
?>
