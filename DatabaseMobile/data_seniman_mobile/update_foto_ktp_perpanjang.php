<?php
require('../Koneksi.php');

// Menerima data dari aplikasi Android
$id_perpanjangan = str_replace(['"', "'"], '', $_POST['id_perpanjangan']);

// Menerima file gambar, dokumen PDF, dan gambar
$ktpSeniman = $_FILES['ktp_seniman'];

// Direktori penyimpanan file
$uploadDirKTP = 'uploads/perpanjangan/ktp_seniman/';


// Mendapatkan path file lama sebelum update
$query_get_old_files = "SELECT ktp_seniman, surat_keterangan, pass_foto FROM perpanjangan WHERE id_perpanjangan = ?";
$stmt_get_old_files = mysqli_prepare($konek, $query_get_old_files);
mysqli_stmt_bind_param($stmt_get_old_files, 'i', $id_perpanjangan);
mysqli_stmt_execute($stmt_get_old_files);
$result_old_files = mysqli_stmt_get_result($stmt_get_old_files);

if ($row_old_files = mysqli_fetch_assoc($result_old_files)) {
    $old_ktp_path = $row_old_files['ktp_seniman'];


    // Hapus file lama
    if (!empty($old_ktp_path) && file_exists($old_ktp_path)) {
        unlink($old_ktp_path);
    }


}

$ktpSenimanFileName = $uploadDirKTP . generateUniqueFileName($ktpSeniman['name'], $uploadDirKTP);
move_uploaded_file($ktpSeniman['tmp_name'], $ktpSenimanFileName);


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



// Menyimpan data ke database
$today = date('Y-m-d'); // Mengambil tanggal hari ini
$nextYear = date('Y') + 1; // Mengambil tahun berikutnya
$tgl_pembuatan = $today;
$tgl_berlaku = $nextYear . '-12-31';

$query = "UPDATE perpanjangan SET status = 'diajukan', ktp_seniman = ? WHERE id_perpanjangan = ?";
$stmt = mysqli_prepare($konek, $query);

// Assuming $ktpSenimanFileName and $id_seniman are strings
mysqli_stmt_bind_param($stmt, 'ss', $ktpSenimanFileName, $id_seniman);
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
