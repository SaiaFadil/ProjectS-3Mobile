<?php
require('../Koneksi.php');

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $id_event = $_GET['id_event'];

    // Gunakan parameterized query untuk mencegah SQL injection
    $sql = "SELECT e.id_user, e.nama_pengirim, e.status, e.catatan, d.id_detail, d.nama_event, d.tempat_event, 
        d.tanggal_awal, d.tanggal_akhir,d.deskripsi, d.link_pendaftaran, d.poster_event
        FROM events AS e
        JOIN detail_events AS d ON e.id_detail = d.id_detail
        WHERE e.id_event = ?
        LIMIT 1";

    // Persiapkan statement dan ikat parameter
    $stmt = $konek->prepare($sql);
    $stmt->bind_param("s", $id_event);

    // Eksekusi statement
    $stmt->execute();
    $result = $stmt->get_result();

    // Periksa apakah query berhasil dieksekusi
    if ($result->num_rows > 0) {
        $detail = $result->fetch_assoc();

        $response = array("status" => "success", "message" => "data diambil", "data" => $detail);
    } else {
        $response = array("status" => "error", "message" => "data gagal diambil");
    }

    // Tutup statement
    $stmt->close();
} else {
    $response["kode"] = 2;
    $response["pesan"] = "Metode tidak valid";
}

// Keluarkan response sebagai JSON
echo json_encode($response);

// Tutup koneksi
$konek->close();
?>

