<?php
require('../Koneksi.php');

// Menerima data dari aplikasi Android
$id_perpanjangan = isset($_POST['id_perpanjangan']) ? str_replace(['"', "'"], '', $_POST['id_perpanjangan']) : null;

// Menerima file gambar, dokumen PDF, dan gambar
$ktpSeniman = isset($_FILES['ktp_seniman']) ? $_FILES['ktp_seniman'] : null;
$suratKeterangan = isset($_FILES['surat_keterangan']) ? $_FILES['surat_keterangan'] : null;
$passFoto = isset($_FILES['pass_foto']) ? $_FILES['pass_foto'] : null;

// Pastikan kunci yang diharapkan ada sebelum mengakses array
if ($id_perpanjangan !== null || $ktpSeniman !== null || $suratKeterangan !== null || $passFoto !== null) {
    // Direktori penyimpanan file
    $uploadDirKTP = 'uploads/perpanjangan/ktp_seniman/';
    $uploadDirSurat = 'uploads/perpanjangan/surat_keterangan/';
    $uploadDirPassFoto = 'uploads/perpanjangan/pass_foto/';
    $today = date('Y-m-d'); // Mengambil tanggal hari ini
    $tgl_pembuatan = $today;
// Mendapatkan path file lama sebelum update
$query_get_old_files = "SELECT ktp_seniman, surat_keterangan, pass_foto FROM perpanjangan WHERE id_perpanjangan = ?";
$stmt_get_old_files = mysqli_prepare($konek, $query_get_old_files);
mysqli_stmt_bind_param($stmt_get_old_files, 'i', $id_perpanjangan);
mysqli_stmt_execute($stmt_get_old_files);
$result_old_files = mysqli_stmt_get_result($stmt_get_old_files);

if ($row_old_files = mysqli_fetch_assoc($result_old_files)) {
    $old_ktp_path = $row_old_files['ktp_seniman'];
    $old_surat_path = $row_old_files['surat_keterangan'];
    $old_pass_path = $row_old_files['pass_foto'];

    // Hapus file lama
    if (!empty($old_ktp_path) && file_exists($old_ktp_path) && !empty($ktpSeniman['tmp_name']) && $old_ktp_path !== $uploadDirKTP . generateUniqueFileName($ktpSeniman['name'], $uploadDirKTP)) {
        unlink($old_ktp_path);
    }

    if (!empty($old_surat_path) && file_exists($old_surat_path) && !empty($suratKeterangan['tmp_name']) && $old_surat_path !== $uploadDirSurat . generateUniqueFileName2($suratKeterangan['name'], $uploadDirSurat)) {
        unlink($old_surat_path);
    }

    if (!empty($old_pass_path) && file_exists($old_pass_path) && !empty($passFoto['tmp_name']) && $old_pass_path !== $uploadDirPassFoto . generateUniqueFileName3($passFoto['name'], $uploadDirPassFoto)) {
        unlink($old_pass_path);
    }
}

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

// Update database fields only if the corresponding file has been uploaded
$ktpSenimanFileName = !empty($ktpSeniman['tmp_name']) ? $uploadDirKTP . generateUniqueFileName($ktpSeniman['name'], $uploadDirKTP) : null;
$suratKeteranganFileName = !empty($suratKeterangan['tmp_name']) ? $uploadDirSurat . generateUniqueFileName2($suratKeterangan['name'], $uploadDirSurat) : null;
$passFotoFileName = !empty($passFoto['tmp_name']) ? $uploadDirPassFoto . generateUniqueFileName3($passFoto['name'], $uploadDirPassFoto) : null;

// Database update
$query = "UPDATE perpanjangan SET status = 'diajukan', tgl_pembuatan = ?, ktp_seniman = IFNULL(?, ktp_seniman), surat_keterangan = IFNULL(?, surat_keterangan), pass_foto = IFNULL(?, pass_foto), catatan = '' WHERE id_perpanjangan = ?";
$stmt = mysqli_prepare($konek, $query);
mysqli_stmt_bind_param($stmt, 'ssssi', $tgl_pembuatan, $ktpSenimanFileName, $suratKeteranganFileName, $passFotoFileName, $id_perpanjangan);

// Use a transaction for database operations
mysqli_begin_transaction($konek);
mysqli_stmt_execute($stmt);

if (mysqli_stmt_error($stmt)) {
    // Rollback on error
    mysqli_rollback($konek);

    $response['kode'] = 0;
    $response['pesan'] = 'Database error: ' . mysqli_stmt_error($stmt);
} else {
    // Commit the changes
    mysqli_commit($konek);

    $response['kode'] = 1;
    $response['pesan'] = 'Data updated successfully.';
}
header('Content-type: application/json');
    echo json_encode($response);
    mysqli_close($konek);
} else {
    $response['kode'] = 0;
    $response['pesan'] = 'Data tidak lengkap atau kunci tidak sesuai.';
    // Mengirim respons ke aplikasi Android dalam format JSON
    header('Content-type: application/json');
    echo json_encode($response);
    mysqli_close($konek);
}
?>