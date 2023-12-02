<?php
require('../Koneksi.php');

// Menerima data dari aplikasi Android
$nik = $_POST['nik'];
$namaLengkap = str_replace(['"', "'"], '', $_POST['nama_seniman']);
$jenisKelamin = str_replace(['"', "'"], '', $_POST['jenis_kelamin']);
$tempatLahir = str_replace(['"', "'"], '', $_POST['tempat_lahir']);
$tanggalLahir = str_replace(['"', "'"], '', $_POST['tanggal_lahir']);
$alamat = str_replace(['"', "'"], '', $_POST['alamat_seniman']);
$noHandphone = str_replace(['"', "'"], '', $_POST['no_telpon']);
$namaOrganisasi = str_replace(['"', "'"], '', $_POST['nama_organisasi']);
$jumlahAnggota = str_replace(['"', "'"], '', $_POST['jumlah_anggota']);
$singkatan_kategori = str_replace(['"', "'"], '', $_POST['singkatan_kategori']);
$kecamatan = str_replace(['"', "'"], '', $_POST['kecamatan']);
$id_seniman = str_replace(['"', "'"], '', $_POST['id_seniman']);

// Menerima file gambar, dokumen PDF, dan gambar
$ktpSeniman = $_FILES['ktp_seniman'];
$suratKeterangan = $_FILES['surat_keterangan'];
$passFoto = $_FILES['pass_foto'];

// Direktori penyimpanan file
$uploadDirKTP = 'uploads/seniman/ktp_seniman/';
$uploadDirSurat = 'uploads/seniman/surat_keterangan/';
$uploadDirPassFoto = 'uploads/seniman/pass_foto/';

// Mendapatkan path file lama sebelum update
$query_get_old_files = "SELECT ktp_seniman, surat_keterangan, pass_foto FROM seniman WHERE id_seniman = ?";
$stmt_get_old_files = mysqli_prepare($konek, $query_get_old_files);
mysqli_stmt_bind_param($stmt_get_old_files, 'i', $id_seniman);
mysqli_stmt_execute($stmt_get_old_files);
$result_old_files = mysqli_stmt_get_result($stmt_get_old_files);

if ($row_old_files = mysqli_fetch_assoc($result_old_files)) {
    $old_ktp_path = $row_old_files['ktp_seniman'];
    $old_surat_path = $row_old_files['surat_keterangan'];
    $old_pass_path = $row_old_files['pass_foto'];

    // Hapus file lama
    if (!empty($old_ktp_path) && file_exists($old_ktp_path)) {
        unlink($old_ktp_path);
    }

    if (!empty($old_surat_path) && file_exists($old_surat_path)) {
        unlink($old_surat_path);
    }

    if (!empty($old_pass_path) && file_exists($old_pass_path)) {
        unlink($old_pass_path);
    }
}

$ktpSenimanFileName = $uploadDirKTP . generateUniqueFileName($ktpSeniman['name'], $uploadDirKTP);
move_uploaded_file($ktpSeniman['tmp_name'], $ktpSenimanFileName);

// Mengunggah dokumen PDF Surat Keterangan
$suratKeteranganFileName = $uploadDirSurat . generateUniqueFileName2($suratKeterangan['name'], $uploadDirSurat);
move_uploaded_file($suratKeterangan['tmp_name'], $suratKeteranganFileName);

// Mengunggah gambar Pass Foto
$passFotoFileName = $uploadDirPassFoto . generateUniqueFileName3($passFoto['name'], $uploadDirPassFoto);
move_uploaded_file($passFoto['tmp_name'], $passFotoFileName);

// Fungsi untuk menghasilkan nama file unik
function generateUniqueFileName($originalName, $uploadDirKTP) {
    $extension = pathinfo($originalName, PATHINFO_EXTENSION);
    $basename = pathinfo($originalName, PATHINFO_FILENAME);

    // Jika nama file belum ada, langsung gunakan nama asli
    if (!file_exists($uploadDirKTP . $basename . '.' . $extension)) {
        return $basename . '.' . $extension;
    }

    // Jika nama file sudah ada, tambahkan indeks
    $counter = 1;
    while (file_exists($uploadDirKTP . $basename . '(' . $counter . ')' . '.' . $extension)) {
        $counter++;
    }

    return $basename . '(' . $counter . ')' . '.' . $extension;
}

function generateUniqueFileName2($originalName, $uploadDirSurat) {
    $extension = pathinfo($originalName, PATHINFO_EXTENSION);
    $basename = pathinfo($originalName, PATHINFO_FILENAME);

    if (!file_exists($uploadDirSurat . $basename . '.' . $extension)) {
        return $basename . '.' . $extension;
    }

    $counter = 1;
    while (file_exists($uploadDirSurat . $basename . '(' . $counter . ')' . '.' . $extension)) {
        $counter++;
    }

    return $basename . '(' . $counter . ')' . '.' . $extension;
}

function generateUniqueFileName3($originalName, $uploadDirPassFoto) {
    $extension = pathinfo($originalName, PATHINFO_EXTENSION);
    $basename = pathinfo($originalName, PATHINFO_FILENAME);

    if (!file_exists($uploadDirPassFoto . $basename . '.' . $extension)) {
        return $basename . '.' . $extension;
    }

    $counter = 1;
    while (file_exists($uploadDirPassFoto . $basename . '(' . $counter . ')' . '.' . $extension)) {
        $counter++;
    }

    return $basename . '(' . $counter . ')' . '.' . $extension;
}

// Enkripsi nilai $nik dengan Base64
$encryptedNik = base64_encode($nik);

$cek_iduser = "SELECT * FROM `seniman` WHERE id_seniman = ?";
$stmt = mysqli_prepare($konek, $cek_iduser);
mysqli_stmt_bind_param($stmt, 'i', $id_seniman);
mysqli_stmt_execute($stmt);
$result = mysqli_stmt_get_result($stmt);

// Menyimpan data ke database
$today = date('Y-m-d'); // Mengambil tanggal hari ini
$nextYear = date('Y') + 1; // Mengambil tahun berikutnya
$tgl_pembuatan = $today;
$tgl_berlaku = $nextYear . '-12-31';

$query = "UPDATE seniman SET nik = ?, nama_seniman = ?, jenis_kelamin = ?, tempat_lahir = ?, tanggal_lahir = ?, status = 'diajukan', alamat_seniman = ?, no_telpon = ?, nama_organisasi = ?, jumlah_anggota = ?, tgl_pembuatan = ?, tgl_berlaku = ?, singkatan_kategori = ?, kecamatan = ?, ktp_seniman = ?, surat_keterangan = ?, pass_foto = ? WHERE id_seniman = ?";
$stmt = mysqli_prepare($konek, $query);
mysqli_stmt_bind_param($stmt, 'ssssssssssssssssi', $encryptedNik, $namaLengkap, $jenisKelamin, $tempatLahir, $tanggalLahir, $alamat, $noHandphone, $namaOrganisasi, $jumlahAnggota, $tgl_pembuatan, $tgl_berlaku, $singkatan_kategori, $kecamatan, $ktpSenimanFileName, $suratKeteranganFileName, $passFotoFileName, $id_seniman);
mysqli_stmt_execute($stmt);

if (mysqli_stmt_error($stmt)) {
    $response['kode'] = 0;
    $response['pesan'] = 'Database error: ' . mysqli_stmt_error($stmt);
} else {
    $response['kode'] = 1;
    $response['pesan'] = 'Data updated successfully.';
}

// Mengirim respons ke aplikasi Android dalam format JSON
header('Content-type: application/json');
echo json_encode($response);
mysqli_close($konek);
?>
