<?php

require('../Koneksi.php');

// Membuat query SQL untuk mengambil data kategori
$sql = "SELECT id_kategori_seniman, nama_kategori FROM kategori_seniman
group by id_kategori_seniman
order by id_kategori_seniman asc";
$result = $konek->query($sql);

if ($result->num_rows > 0) {
    $kategori = array();
    while ($row = $result->fetch_assoc()) {
        $kategori[] = $row;
    }
    
} else {
    echo "Tidak ada data kategori.";
}
echo json_encode($kategori);
// Menutup koneksi ke database
$konek->close();

?>
