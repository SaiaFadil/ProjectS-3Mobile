<?php
require('Koneksi.php');

$NamaKategori = $_POST['NamaKategori'];
    
    $sql = "SELECT * FROM kategori_seniman WHERE nama_kategori = '$NamaKategori';";
    $result = $konek->query($sql);
 
    if ($result->num_rows == 1) {
        $TabelKategori = $result->fetch_assoc();

        $response["kode"] = 1;
        $response["pesan"] = "Data Tersedia";
        $response["data"] = $TabelKategori;
        
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Nama Kategori tidak tersedia";
    }

echo json_encode($response);
mysqli_close($konek);
?>
