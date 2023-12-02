<?php
require('../Koneksi.php');

// Menerima data dari aplikasi Android
$id_seniman = str_replace(['"', "'"], '', $_POST['id_seniman']);

// Menerima file gambar, dokumen PDF, dan gambar
$passFoto = $_FILES['pass_foto'];

// Direktori penyimpanan file
$uploadDirPassFoto = 'uploads/seniman/pass_foto/';


// Mendapatkan path file lama sebelum update
$query_get_old_files = "SELECT ktp_seniman, surat_keterangan, pass_foto FROM seniman WHERE id_seniman = ?";
$stmt_get_old_files = mysqli_prepare($konek, $query_get_old_files);
mysqli_stmt_bind_param($stmt_get_old_files, 'i', $id_seniman);
mysqli_stmt_execute($stmt_get_old_files);
$result_old_files = mysqli_stmt_get_result($stmt_get_old_files);

if ($row_old_files = mysqli_fetch_assoc($result_old_files)) {
    $old_pass_path = $row_old_files['pass_foto'];


    if (!empty($old_pass_path) && file_exists($old_pass_path)) {
        unlink($old_pass_path);
    }
}

// Mengunggah gambar Pass Foto
$passFotoFileName = $uploadDirPassFoto . generateUniqueFileName3($passFoto['name'], $uploadDirPassFoto);
move_uploaded_file($passFoto['tmp_name'], $passFotoFileName);


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

// Menyimpan data ke database
$today = date('Y-m-d'); // Mengambil tanggal hari ini
$nextYear = date('Y') + 1; // Mengambil tahun berikutnya
$tgl_pembuatan = $today;
$tgl_berlaku = $nextYear . '-12-31';

$query = "UPDATE seniman SET status = 'diajukan', pass_foto = ? WHERE id_seniman = ?";
$stmt = mysqli_prepare($konek, $query);

// Assuming $passFotoFileName and $id_seniman are strings
mysqli_stmt_bind_param($stmt, 'ss', $passFotoFileName, $id_seniman);
mysqli_stmt_execute($stmt); // Added a semicolon here

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
