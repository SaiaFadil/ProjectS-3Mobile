<?php
require('../Koneksi.php');

// Menerima data dari aplikasi Android
$nik = $_POST['nik'];
$namaLengkap = $_POST['nama_seniman'];
$jenisKelamin = $_POST['jenis_kelamin'];
$tempatLahir = $_POST['tempat_lahir'];
$tanggalLahir = $_POST['tanggal_lahir'];
$alamat = $_POST['alamat_seniman'];
$noHandphone = $_POST['no_telpon'];
$namaOrganisasi = $_POST['nama_organisasi'];
$jumlahAnggota = $_POST['jumlah_anggota'];
$status = $_POST['status'];
$singkatan_kategori = $_POST['singkatan_kategori'];
$kecamatan = $_POST['kecamatan'];
$id_user = $_POST['id_user'];
$id_seniman = $_POST['id_seniman'];


$cek_iduser = "SELECT * FROM `seniman` WHERE id_seniman = '$id_seniman'";
$eksekusi_cek = mysqli_query($konek, $cek_iduser);
$jumlah_cek = mysqli_num_rows($eksekusi_cek);

$response = array();
if ($jumlah_cek == 1) {

    $perintah = "UPDATE `seniman` 
    SET `nik` = '$nik',
     `nama_seniman` = '$namaLengkap', 
     `jenis_kelamin` = '$jenisKelamin' , `tempat_lahir` = '$tempatLahir',  `tanggal_lahir` = '$tanggalLahir',  `alamat_seniman` = '$alamat',  `no_telpon` = '$noHandphone', `no_telpon` = '$noHandphone', 
     `nama_organisasi` = '$namaOrganisasi' , `jumlah_anggota` = '$jumlahAnggota',  `status` = 'diajukan',  `singkatan_kategori` = '$singkatan_kategori',  `kecamatan` = '$kecamatan',
     `catatan` = ''
     WHERE 
      `id_seniman` = $id_seniman;";

    $eksekusi = mysqli_query($konek, $perintah);

    if ($eksekusi) {
        $response["kode"] = 1;
        $response["pesan"] = "Update Berhasil";

    } else {
        $response["kode"] = 2;
        $response["pesan"] = "Update Gagal";
    }
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Ada Kesalahan";
}

echo json_encode($response);
mysqli_close($konek);
?>