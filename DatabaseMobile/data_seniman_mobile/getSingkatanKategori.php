<?php
require('../Koneksi.php');

$NamaKategori = $_POST['NamaKategori'];
    
    $sql = "SELECT singkatan_kategori FROM kategori_seniman WHERE nama_kategori = '$NamaKategori' LIMIT 1;";
    $result = $konek->query($sql);
 
    if ($result->num_rows == 1) {
        $TabelKategori = $result->fetch_assoc();

        $response["kode"] = 1;
        $response["pesan"] = "Data Tersedia";
        $response["data"] = $TabelKategori['singkatan_kategori'];
        
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Nama Kategori tidak tersedia";
    }

echo json_encode($response);
mysqli_close($konek);
?>
