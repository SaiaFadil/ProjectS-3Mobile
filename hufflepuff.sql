-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 06 Okt 2023 pada 17.28
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hufflepuff`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `events`
--

CREATE TABLE `events` (
  `id_event` int(5) NOT NULL,
  `nama_pengirim` varchar(30) NOT NULL,
  `nama_event` varchar(30) NOT NULL,
  `deskripsi_event` varchar(500) DEFAULT NULL,
  `kategori_event` enum('OLAHRAGA','SENI') DEFAULT NULL,
  `tanggal_awal_event` datetime NOT NULL,
  `tanggal_akhir_event` datetime NOT NULL,
  `link_pendaftaran` varchar(100) DEFAULT NULL,
  `poster_event` varchar(10) DEFAULT NULL,
  `id_sewa_tempat` int(11) DEFAULT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `list_tempat`
--

CREATE TABLE `list_tempat` (
  `id_tempat` varchar(5) NOT NULL,
  `nama_tempat` varchar(50) NOT NULL,
  `alamat_tempat` varchar(50) NOT NULL,
  `deskripsi_tempat` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `refresh_token`
--

CREATE TABLE `refresh_token` (
  `id_token` int(10) NOT NULL,
  `email` varchar(45) NOT NULL,
  `token` longtext NOT NULL,
  `device` enum('website','mobile') NOT NULL,
  `number` int(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `refresh_token`
--

INSERT INTO `refresh_token` (`id_token`, `email`, `token`, `device`, `number`, `created_at`, `updated_at`, `id_user`) VALUES
(9, 'amirzanfikri5@gmail.com', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0IiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdCIsImlhdCI6MTY5NTc4MTk3NS43MTYwMDYsIm5iZiI6MTY5NTc4MTk3NS43MTYwMDYsImV4cCI6MTY5NTc4NTU3NS43MTYwMDYsImRhdGEiOnsiZGF0YSI6eyJpZF91c2VyIjoyNywiZW1haWwiOiJhbWlyemFuZmlrcmk1QGdtYWlsLmNvbSIsInBhc3N3b3JkIjoiJDJ5JDEwJEFLZzJ4MG1oT0ZHQ0VzMkxJY2kwWWVCMU5za0hHaTR5QlExWmZTc0xIVnFZTTg4Tk9pMGNxIiwibmFtYV9sZW5na2FwIjoiQW1pcnphbiBGaWtyaSIsInJvbGUiOiJtYXN5YXJha2F0Iiwibm9fdGVscG9uIjoiIiwidGFuZ2dhbF9sYWhpciI6IjAwMDAtMDAtMDAiLCJ0ZW1wYXRfbGFoaXIiOiIiLCJ2ZXJpZmlrYXNpIjowfSwiZXhwIjozNjAwfX0.ngMBYWF54l-YLwYBSP7RisJ9jIc1Nc2UPHM3MVZcNaw', 'website', 1, '2023-09-27 02:32:55', '2023-09-27 02:32:55', 27),
(10, 'amirzanfikri5@gmail.com', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0IiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdCIsImlhdCI6MTY5NTc4NTY4My41MzIwMjIsIm5iZiI6MTY5NTc4NTY4My41MzIwMjIsImV4cCI6MTY5NTc4OTI4My41MzIwMjIsImRhdGEiOnsiZGF0YSI6eyJpZF91c2VyIjoyNywiZW1haWwiOiJhbWlyemFuZmlrcmk1QGdtYWlsLmNvbSIsInBhc3N3b3JkIjoiJDJ5JDEwJEFLZzJ4MG1oT0ZHQ0VzMkxJY2kwWWVCMU5za0hHaTR5QlExWmZTc0xIVnFZTTg4Tk9pMGNxIiwibmFtYV9sZW5na2FwIjoiQW1pcnphbiBGaWtyaSIsInJvbGUiOiJtYXN5YXJha2F0Iiwibm9fdGVscG9uIjoiIiwidGFuZ2dhbF9sYWhpciI6IjAwMDAtMDAtMDAiLCJ0ZW1wYXRfbGFoaXIiOiIiLCJ2ZXJpZmlrYXNpIjowfSwiZXhwIjozNjAwfX0.qH4bglxg5FbYUO8FsQ0fZWsAxzuW-nWNJ6GaxvDxJS4', 'website', 2, '2023-09-27 03:34:43', '2023-09-27 03:34:43', 27);

-- --------------------------------------------------------

--
-- Struktur dari tabel `seniman`
--

CREATE TABLE `seniman` (
  `id_seniman` int(5) NOT NULL,
  `id_user` int(11) NOT NULL,
  `nik` int(16) NOT NULL,
  `nomor_induk` varchar(20) NOT NULL,
  `nama_seniman` varchar(30) NOT NULL,
  `jenis_kelamin` enum('laki-laki','perempuan') NOT NULL,
  `ttl_seniman` varchar(30) NOT NULL,
  `alamat_seniman` varchar(50) NOT NULL,
  `phone_seniman` varchar(15) DEFAULT NULL,
  `nama_organisasi` varchar(50) DEFAULT NULL,
  `jumlah_anggota` varchar(10) DEFAULT NULL,
  `surat_keterangan` varchar(10) DEFAULT NULL,
  `ktp_seniman` varchar(10) DEFAULT NULL,
  `pass_foto` varchar(10) DEFAULT NULL,
  `tgl_pembuatan` date NOT NULL,
  `tgl_berlaku` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `sewa_tempat`
--

CREATE TABLE `sewa_tempat` (
  `id_sewa` int(5) NOT NULL,
  `id_tempat` varchar(5) DEFAULT NULL,
  `nama_tempat` varchar(50) DEFAULT NULL,
  `nama_peminjam` varchar(30) DEFAULT NULL,
  `nik_sewa` int(16) DEFAULT NULL,
  `instansi` varchar(50) DEFAULT NULL,
  `nama_kegiatan_sewa` varchar(50) DEFAULT NULL,
  `jumlah_peserta` int(10) DEFAULT NULL,
  `tgl_awal_peminjaman` date DEFAULT NULL,
  `tgl_akhir_peminjaman` date DEFAULT NULL,
  `deskripsi_sewa_tempat` varchar(100) DEFAULT NULL,
  `surat_ket_sewa` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `surat_advis`
--

CREATE TABLE `surat_advis` (
  `id_advis` int(5) NOT NULL,
  `nomor_induk` varchar(20) DEFAULT NULL,
  `nama_advis` varchar(30) DEFAULT NULL,
  `alamat_advis` varchar(50) DEFAULT NULL,
  `deskripsi_advis` varchar(100) DEFAULT NULL,
  `tgl_advis` date DEFAULT NULL,
  `tempat_advis` varchar(30) DEFAULT NULL,
  `status_advis` enum('diterima','ditolak') DEFAULT NULL,
  `surat_adviscol` varchar(45) DEFAULT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `nama_lengkap` varchar(50) NOT NULL,
  `no_telpon` varchar(15) NOT NULL,
  `jenis_kelamin` enum('laki-laki','perempuan') NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `tempat_lahir` varchar(45) NOT NULL,
  `role` enum('super admin','admin','masyarakat') NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(20) NOT NULL,
  `verifikasi` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id_user`, `nama_lengkap`, `no_telpon`, `jenis_kelamin`, `tanggal_lahir`, `tempat_lahir`, `role`, `email`, `password`, `verifikasi`) VALUES
(27, 'Fadillah wahyu', '08556763287', 'laki-laki', '2003-10-24', 'Nganjuk', 'masyarakat', 'Fadillahwahyunugraha@gmail.com', '1', 0),
(104, 'JettDhilz', '08562736723', 'laki-laki', '2003-03-23', 'Blitar', 'masyarakat', 'Fadil@gmail.com', '123', 0),
(105, 'Masdhilz', '085255365422', 'laki-laki', '2004-05-22', 'Kediri', 'masyarakat', 'dappganzzshop@gmail.com', 'Masdhilz1', 0),
(106, 'AkunTumbal', '085613976464', 'laki-laki', '1945-10-20', 'Amerika', 'masyarakat', 'akuntumbal1@gmail.com', 'AkunTumbal1', 0),
(107, 'Saia Fadil', '08649533157', 'laki-laki', '2003-02-23', 'Nganjuk', 'masyarakat', 'saiafadil27@gmail.com', 'saiafadil1', 0),
(108, 'Akun Baru', '08946653165', 'laki-laki', '2003-10-24', 'Nganjuk', 'masyarakat', 'AkunBaruu29@gmail.com', 'Fadilaja1', 0),
(109, 'Tria cantik', '089534274304', 'laki-laki', '0000-00-00', '', 'masyarakat', 'triaynta@gmail.com', 'triacumel123', 0),
(110, 'Fadillah wahyu', '08134257616', 'laki-laki', '0000-00-00', '', 'masyarakat', 'akunceer1satu@gmail.com', 'fadil123', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `verifikasi`
--

CREATE TABLE `verifikasi` (
  `id_verifikasi` int(10) NOT NULL,
  `email` varchar(45) NOT NULL,
  `kode_otp` int(6) NOT NULL,
  `link` varchar(50) NOT NULL,
  `deskripsi` enum('password','email') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `verifikasi`
--

INSERT INTO `verifikasi` (`id_verifikasi`, `email`, `kode_otp`, `link`, `deskripsi`, `created_at`, `updated_at`, `id_user`) VALUES
(1, 'amirzanfikri5@gmail.com', 587147, 'e9f51a92071d6152f3ba7f95bd5440ae051ec74b979dd9b21a', 'password', '2023-09-26 16:03:24', '2023-09-26 16:03:24', 27);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id_event`),
  ADD KEY `eventFK` (`id_user`);

--
-- Indeks untuk tabel `list_tempat`
--
ALTER TABLE `list_tempat`
  ADD PRIMARY KEY (`id_tempat`);

--
-- Indeks untuk tabel `refresh_token`
--
ALTER TABLE `refresh_token`
  ADD PRIMARY KEY (`id_token`),
  ADD KEY `tokenFK` (`id_user`);

--
-- Indeks untuk tabel `seniman`
--
ALTER TABLE `seniman`
  ADD PRIMARY KEY (`id_seniman`),
  ADD KEY `senimanFK` (`id_user`);

--
-- Indeks untuk tabel `sewa_tempat`
--
ALTER TABLE `sewa_tempat`
  ADD PRIMARY KEY (`id_sewa`);

--
-- Indeks untuk tabel `surat_advis`
--
ALTER TABLE `surat_advis`
  ADD PRIMARY KEY (`id_advis`),
  ADD KEY `advisFK` (`id_user`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- Indeks untuk tabel `verifikasi`
--
ALTER TABLE `verifikasi`
  ADD PRIMARY KEY (`id_verifikasi`),
  ADD KEY `verifyfk` (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `events`
--
ALTER TABLE `events`
  MODIFY `id_event` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `refresh_token`
--
ALTER TABLE `refresh_token`
  MODIFY `id_token` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT untuk tabel `seniman`
--
ALTER TABLE `seniman`
  MODIFY `id_seniman` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `sewa_tempat`
--
ALTER TABLE `sewa_tempat`
  MODIFY `id_sewa` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `surat_advis`
--
ALTER TABLE `surat_advis`
  MODIFY `id_advis` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111;

--
-- AUTO_INCREMENT untuk tabel `verifikasi`
--
ALTER TABLE `verifikasi`
  MODIFY `id_verifikasi` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `eventFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ketidakleluasaan untuk tabel `refresh_token`
--
ALTER TABLE `refresh_token`
  ADD CONSTRAINT `tokenFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `seniman`
--
ALTER TABLE `seniman`
  ADD CONSTRAINT `senimanFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ketidakleluasaan untuk tabel `surat_advis`
--
ALTER TABLE `surat_advis`
  ADD CONSTRAINT `advisFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `verifikasi`
--
ALTER TABLE `verifikasi`
  ADD CONSTRAINT `verifyfk` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
