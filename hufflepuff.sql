-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 29 Okt 2023 pada 09.22
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
-- Struktur dari tabel `detail_events`
--

CREATE TABLE `detail_events` (
  `id_detail` int(11) NOT NULL,
  `nama_event` varchar(45) NOT NULL,
  `deskripsi` varchar(4000) DEFAULT NULL,
  `kategori` enum('OLAHRAGA','SENI') NOT NULL,
  `tempat_event` varchar(2000) DEFAULT NULL,
  `tanggal_awal` date NOT NULL,
  `tanggal_akhir` date NOT NULL,
  `link_pendaftaran` varchar(2000) DEFAULT NULL,
  `poster_event` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `detail_events`
--

INSERT INTO `detail_events` (`id_detail`, `nama_event`, `deskripsi`, `kategori`, `tempat_event`, `tanggal_awal`, `tanggal_akhir`, `link_pendaftaran`, `poster_event`) VALUES
(2, 'turu lah', 'inainvinivn', 'OLAHRAGA', 'nganjuk', '2023-11-15', '2023-11-15', 'google.com', '/2023/11/2.jpg'),
(3, 'turu lah', 'inainvinivn', 'OLAHRAGA', 'planet luar bumi', '2023-11-15', '2023-11-15', 'google.com', '/2023/11/3.jpg'),
(4, 'sunmori', 'ayo sunmori di planet matahari', 'OLAHRAGA', 'planet matahari', '2023-11-15', '2023-11-15', 'google.com', '/2023/11/4.jpg'),
(5, 'mlaku mlaku', 'inainvinivn', 'OLAHRAGA', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/5.jpg'),
(6, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/6.jpg'),
(7, 'agustusan', 'inainvinivn', 'OLAHRAGA', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/7.jpg'),
(8, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/8.jpg'),
(9, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/9.jpg');

-- --------------------------------------------------------

--
-- Struktur dari tabel `events`
--

CREATE TABLE `events` (
  `id_event` int(11) NOT NULL,
  `nama_pengirim` varchar(30) NOT NULL,
  `status` enum('diajukan','proses','diterima','ditolak') NOT NULL,
  `catatan` text DEFAULT NULL,
  `id_detail` int(11) NOT NULL,
  `id_sewa` int(11) DEFAULT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `events`
--

INSERT INTO `events` (`id_event`, `nama_pengirim`, `status`, `catatan`, `id_detail`, `id_sewa`, `id_user`) VALUES
(2, 'kareppp ', 'proses', NULL, 2, NULL, 32),
(3, 'kareppp ', 'ditolak', 'salahmu dewe', 3, NULL, 32),
(4, 'kareppp ', 'diterima', NULL, 4, NULL, 32),
(5, 'kareppp ', 'diterima', NULL, 5, NULL, 32),
(6, 'kareppp ', 'diajukan', NULL, 6, NULL, 32),
(7, 'kareppp ', 'diterima', NULL, 7, NULL, 32),
(8, 'kareppp ', 'diajukan', NULL, 8, NULL, 32),
(9, 'kareppp ', 'proses', NULL, 9, NULL, 32);

-- --------------------------------------------------------

--
-- Struktur dari tabel `list_tempat`
--

CREATE TABLE `list_tempat` (
  `id_tempat` int(5) NOT NULL,
  `nama_tempat` varchar(50) NOT NULL,
  `alamat_tempat` varchar(50) NOT NULL,
  `deskripsi_tempat` varchar(500) DEFAULT NULL,
  `foto_tempat` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `list_tempat`
--

INSERT INTO `list_tempat` (`id_tempat`, `nama_tempat`, `alamat_tempat`, `deskripsi_tempat`, `foto_tempat`) VALUES
(1, 'Museum Anjuk Ladang ', 'Jl. Gatot Subroto Kec. Nganjuk Kab. Nganjuk ', 'Museum Anjuk Ladang Terletak di kota Nganjuk, tepatnya sebelah timur Terminal Bus Kota Nganjuk, di dalamnya tersimpan benda dan cagar budaya pada zaman Hindu, Doho dan Majapahit yang terdapat di daerah Kabupaten Nganjuk. Disamping itu di simpan Prasasti Anjuk Ladang yang merupakan cikal bakal berdirinya Kabupaten Nganjuk.', '/1.png'),
(2, 'Balai Budaya ', 'Mangundikaran, Kec. Nganjuk, Kab. Nganjuk', 'Gedung Balai Budaya Nganjuk adalah salah satu legenda bangunan bersejarah di Kabupaten Nganjuk. Gedung ini bisa digunakan untuk berbagai acara.', '/2.png'),
(3, 'Monumen Dr. Soetomo ', 'Sono, Ngepeh, Kec. Loceret Kab. Nganjuk', 'Monumen Dr. Soetomo Nganjuk yang menempati tanah seluas 3,5 ha ini merupakan tempat kelahiran Dr. Soetomo Secara keseluruhan kompleks bangunan ini terdiri dari patung Dr. Soetomo, Pendopo induk, yang terletak di belakang patung, dan bangunan pringgitan jumlahnya 2 buah masing-masing 6 x 12 m.', '/3.png'),
(4, 'Air Terjun Sedudo', 'Jl. Sedudo Kec. Sawahan Kab. Nganjuk', 'Air Terjun Sedudo adalah sebuah air terjun dan objek wisata yang terletak di Desa Ngliman Kecamatan Sawahan, Kabupaten Nganjuk, Jawa Timur. Jaraknya sekitar 30 km arah selatan ibu kota kabupaten Nganjuk. Berada pada ketinggian 1.438 meter dpl, ketinggian air terjun ini sekitar 105 meter. Tempat wisata ini memiliki fasilitas yang cukup baik, dan jalur transportasi yang mudah diakses.', '/4.png'),
(5, 'Goa Margo Tresno ', 'Ngluyu, Kec. Ngluyu Kab. Nganjuk ', 'Goa Margo Tresno adalah salah satu obyek wisata di Jawa Timur yang terletak di Dusun Cabean, Desa Sugih Waras, Kecamatan Ngluyu, Kabupaten Nganjuk. Wisata Goa Margo Tresno Nganjuk adalah destinasi wisata yang ramai dengan wisatawan baik dari dalam maupun luar kota pada hari biasa maupun hari liburan dan sudah terkenal di Nganjuk dan sekitarnya.', '/5.png'),
(6, 'Air Terjun Roro Kuning', 'Nglarangan, Bajulan, Kec. Loceret Kab. Nganjuk', 'Air Terjun Roro Kuning adalah sebuah air terjun yang berada sekitar 27–30 km selatan kota Nganjuk, di ketinggian 600 m dpl dan memiliki tinggi antara 10–15 m. Air terjun ini mengalir dari tiga sumber di sekitar Gunung Wilis yang mengalir merambat di sela-sela bebatuan padas di bawah pepohonan hutan pinus.', '/6.png');

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
(28, 'amirzanfikri5@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjozMiwibmFtYV9sZW5na2FwIjoiYW1pcnphbiBmaWtyaSIsIm5vX3RlbHBvbiI6IiIsImplbmlzX2tlbGFtaW4iOiJsYWtpLWxha2kiLCJ0YW5nZ2FsX2xhaGlyIjoiMDAwMC0wMC0wMCIsInRlbXBhdF9sYWhpciI6IiIsImVtYWlsIjoiYW1pcnphbmZpa3JpNUBnbWFpbC5jb20iLCJyb2xlIjoiIiwibnVtYmVyIjozLCJleHAiOjE2OTcxNzIwNDR9._i3Wl0s4I3ElWbN4PJYx3H3Wvl9GDjdtY5joQA_7qdE', 'website', 1, '2023-10-13 03:40:44', '2023-10-13 03:40:44', 32),
(29, 'amirzanfikri5@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjozMiwibmFtYV9sZW5na2FwIjoiYW1pcnphbiBmaWtyaSIsIm5vX3RlbHBvbiI6IiIsImplbmlzX2tlbGFtaW4iOiJsYWtpLWxha2kiLCJ0YW5nZ2FsX2xhaGlyIjoiMDAwMC0wMC0wMCIsInRlbXBhdF9sYWhpciI6IiIsImVtYWlsIjoiYW1pcnphbmZpa3JpNUBnbWFpbC5jb20iLCJyb2xlIjoibWFzeWFyYWthdCIsIm51bWJlciI6MywiZXhwIjoxNjk3MTg4MzUxfQ.6BForsGt966s-MOF8drOomQf6amRzfNqzXlx7Q7T_7U', 'website', 2, '2023-10-13 08:12:31', '2023-10-13 08:12:31', 32),
(58, 'Admin@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjozOCwibmFtYV9sZW5na2FwIjoiYWRtaW4iLCJub190ZWxwb24iOiIwODg4MTUxNTE1IiwiamVuaXNfa2VsYW1pbiI6InBlcmVtcHVhbiIsInRhbmdnYWxfbGFoaXIiOiIyMDIzLTEwLTEzIiwidGVtcGF0X2xhaGlyIjoiYWtoaXJhdCIsImVtYWlsIjoiQWRtaW5AZ21haWwuY29tIiwicm9sZSI6InN1cGVyIGFkbWluIiwibnVtYmVyIjoxLCJleHAiOjMzOTQ4NTE3MjZ9.mEa9weCsbqwvHu32E0Ww-2hihlciOBsfJgOGqJkdozA', 'website', 1, '2023-10-16 02:41:03', '2023-10-16 02:41:03', 38),
(106, 'SuperAdmin@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjozNywibmFtYV9sZW5na2FwIjoic3VwZXIgYWRtaW4iLCJub190ZWxwb24iOiIwODgxMTIyMjMzIiwiamVuaXNfa2VsYW1pbiI6Imxha2ktbGFraSIsInRhbmdnYWxfbGFoaXIiOiIyMDIzLTEwLTA3IiwidGVtcGF0X2xhaGlyIjoicGxhbmV0IGp1cGl0ZXIiLCJlbWFpbCI6IlN1cGVyQWRtaW5AZ21haWwuY29tIiwicm9sZSI6InN1cGVyIGFkbWluIiwibnVtYmVyIjozLCJleHAiOjE2OTgyMzAwMzN9.c1OtgDEylg4OHRMfw8QBurSWePyNNj09lFdok32ddMg', 'website', 1, '2023-10-25 09:33:53', '2023-10-25 09:33:53', 37),
(107, 'SuperAdmin@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjozNywibmFtYV9sZW5na2FwIjoic3VwZXIgYWRtaW4iLCJub190ZWxwb24iOiIwODgxMTIyMjMzIiwiamVuaXNfa2VsYW1pbiI6Imxha2ktbGFraSIsInRhbmdnYWxfbGFoaXIiOiIyMDIzLTEwLTA3IiwidGVtcGF0X2xhaGlyIjoicGxhbmV0IGp1cGl0ZXIiLCJlbWFpbCI6IlN1cGVyQWRtaW5AZ21haWwuY29tIiwicm9sZSI6InN1cGVyIGFkbWluIiwibnVtYmVyIjozLCJleHAiOjE2OTgyNzU5Nzh9.xxSCMD6RlDCdaxKpyrpkpKFmrlOhd8LfusdPNoBcazg', 'website', 2, '2023-10-25 22:19:38', '2023-10-25 22:19:38', 37),
(108, 'SuperAdmin@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjozNywibmFtYV9sZW5na2FwIjoic3VwZXIgYWRtaW4iLCJub190ZWxwb24iOiIwODgxMTIyMjMzIiwiamVuaXNfa2VsYW1pbiI6Imxha2ktbGFraSIsInRhbmdnYWxfbGFoaXIiOiIyMDIzLTEwLTA3IiwidGVtcGF0X2xhaGlyIjoicGxhbmV0IGp1cGl0ZXIiLCJlbWFpbCI6IlN1cGVyQWRtaW5AZ21haWwuY29tIiwicm9sZSI6InN1cGVyIGFkbWluIiwibnVtYmVyIjozLCJleHAiOjE2OTgyNzk2MDZ9.s9tz07MI-ZpFPQtxXX-XgK6Y_6D3NGyQKXKIuBBPPoY', 'website', 3, '2023-10-25 23:20:06', '2023-10-25 23:20:06', 37);

-- --------------------------------------------------------

--
-- Struktur dari tabel `seniman`
--

CREATE TABLE `seniman` (
  `id_seniman` int(5) NOT NULL,
  `nik` int(16) NOT NULL,
  `nomor_induk` varchar(20) NOT NULL,
  `nama_seniman` varchar(30) NOT NULL,
  `jenis_kelamin` enum('laki-laki','perempuan') NOT NULL,
  `tempat_lahir` varchar(30) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `alamat_seniman` varchar(50) NOT NULL,
  `no_telpon` varchar(15) DEFAULT NULL,
  `nama_organisasi` varchar(50) DEFAULT NULL,
  `jumlah_anggota` varchar(10) DEFAULT NULL,
  `ktp_seniman` varchar(20) DEFAULT NULL,
  `pass_foto` varchar(20) DEFAULT NULL,
  `surat_keterangan` varchar(50) DEFAULT NULL,
  `tgl_pembuatan` date NOT NULL,
  `tgl_berlaku` date DEFAULT NULL,
  `status` enum('diajukan','proses','diterima','ditolak') NOT NULL,
  `catatan` text DEFAULT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `seniman`
--

INSERT INTO `seniman` (`id_seniman`, `nik`, `nomor_induk`, `nama_seniman`, `jenis_kelamin`, `tempat_lahir`, `tanggal_lahir`, `alamat_seniman`, `no_telpon`, `nama_organisasi`, `jumlah_anggota`, `ktp_seniman`, `pass_foto`, `surat_keterangan`, `tgl_pembuatan`, `tgl_berlaku`, `status`, `catatan`, `id_user`) VALUES
(1, 990155150, '4607', 'joko', 'laki-laki', 'planet mars', '2023-10-19', 'tersraahhvhh', '089999', 'nnivnanvnvonao', '1001', '/ktp/1.jpeg', '/pass_foto/1.png', '/surat_keterangan/1.pdf', '2023-10-22', '2023-10-22', 'diterima', NULL, 32),
(2, 2147483647, '7869', 'santi', 'perempuan', 'nganjuk', '2023-10-27', 'isvnnvisivnsv', '088861616', 'random', '121', '/ktp/2.png', '/pass_foto/2.jpeg', '/surat_keterangan/2.pdf', '2023-10-22', '2023-10-22', 'ditolak', 'alamat tidak jelas', 32),
(3, 2147483647, '3167', 'ranti', 'perempuan', 'nganjuk', '2017-04-27', 'isvnnvisivnsv', '088861616', 'random', '121', '/ktp/3.png', '/pass_foto/3.jpeg', '/surat_keterangan/3.pdf', '2023-10-22', '2023-10-22', 'proses', NULL, 32),
(5, 2147483647, '9664', 'yanti', 'perempuan', 'planet mars', '2023-10-21', 'planet matahari', '089999', 'terserah lah', '121', '/ktp/5.jpg', '/pass_foto/5.jpg', '/surat_keterangan/5.pdf', '2023-10-24', '2023-10-24', 'ditolak', 'nama kurang lengkap', 32),
(6, 2147483647, '5559', 'asep', 'laki-laki', 'planet mars', '2016-03-21', 'planet matahari', '089999', 'terserah lah', '121', '/ktp/6.jpg', '/pass_foto/6.jpg', '/surat_keterangan/6.pdf', '2023-10-24', '2023-10-24', '', NULL, 32),
(7, 2147483647, '5170', 'rana', 'perempuan', 'planet mars', '2016-05-05', 'planet matahari', '089999', 'terserah lah', '121', '/ktp/7.jpg', '/pass_foto/7.jpg', '/surat_keterangan/7.pdf', '2023-10-24', '2023-10-24', 'diajukan', NULL, 32);

-- --------------------------------------------------------

--
-- Struktur dari tabel `sewa_tempat`
--

CREATE TABLE `sewa_tempat` (
  `id_sewa` int(5) NOT NULL,
  `nik_sewa` int(16) DEFAULT NULL,
  `nama_peminjam` varchar(30) DEFAULT NULL,
  `nama_tempat` varchar(50) DEFAULT NULL,
  `deskripsi_sewa_tempat` varchar(100) DEFAULT NULL,
  `nama_kegiatan_sewa` varchar(50) DEFAULT NULL,
  `jumlah_peserta` int(10) DEFAULT NULL,
  `instansi` varchar(50) DEFAULT NULL,
  `surat_ket_sewa` varchar(50) DEFAULT NULL,
  `tgl_awal_peminjaman` datetime DEFAULT NULL,
  `tgl_akhir_peminjaman` datetime DEFAULT NULL,
  `status` enum('diajukan','proses','diterima','ditolak') NOT NULL,
  `catatan` text DEFAULT NULL,
  `id_tempat` int(5) NOT NULL,
  `id_user` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `sewa_tempat`
--

INSERT INTO `sewa_tempat` (`id_sewa`, `nik_sewa`, `nama_peminjam`, `nama_tempat`, `deskripsi_sewa_tempat`, `nama_kegiatan_sewa`, `jumlah_peserta`, `instansi`, `surat_ket_sewa`, `tgl_awal_peminjaman`, `tgl_akhir_peminjaman`, `status`, `catatan`, `id_tempat`, `id_user`) VALUES
(10, 2147483647, 'juniorr', 'china', 'ayo turu', 'turu massal', 1224, 'karate', '/surat_keterangan/2023/10/10.pdf', '2023-10-29 11:28:00', '2023-10-29 11:30:00', 'ditolak', 'salahmu dewe', 2, 32),
(11, 2147483647, 'juniorr', 'jerman barat', 'ayo turu', 'turu massal', 1224, 'karate', '/surat_keterangan/2023/10/11.pdf', '2023-10-29 11:28:00', '2023-10-29 11:30:00', 'ditolak', 'mantap', 2, 32),
(12, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/surat_keterangan/2023/11/12.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'diterima', NULL, 2, 32),
(13, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/surat_keterangan/2023/11/13.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'proses', NULL, 2, 32),
(14, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/surat_keterangan/2023/11/14.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'diajukan', NULL, 2, 32),
(15, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/surat_keterangan/2023/11/15.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'diajukan', NULL, 2, 32),
(16, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/surat_keterangan/2023/11/16.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'diajukan', NULL, 2, 32),
(17, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/surat_keterangan/2023/11/17.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'diajukan', NULL, 2, 32),
(18, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/surat_keterangan/2023/11/18.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'diajukan', NULL, 2, 32),
(19, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/surat_keterangan/2023/11/19.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'diajukan', NULL, 2, 32);

-- --------------------------------------------------------

--
-- Struktur dari tabel `surat_advis`
--

CREATE TABLE `surat_advis` (
  `id_advis` int(5) NOT NULL,
  `nomor_induk` varchar(20) NOT NULL,
  `nama_advis` varchar(30) NOT NULL,
  `alamat_advis` varchar(50) NOT NULL,
  `deskripsi_advis` varchar(100) DEFAULT NULL,
  `tgl_advis` date NOT NULL,
  `tempat_advis` varchar(30) NOT NULL,
  `status` enum('diterima','ditolak') DEFAULT NULL,
  `catatan` text DEFAULT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `surat_advis`
--

INSERT INTO `surat_advis` (`id_advis`, `nomor_induk`, `nama_advis`, `alamat_advis`, `deskripsi_advis`, `tgl_advis`, `tempat_advis`, `status`, `catatan`, `id_user`) VALUES
(1, 'DLG2023001', 'Seni Tari', 'Nganjuk', 'Pentas Seni Tari dalam rangka lomba akhir tahun', '0000-00-00', 'Kabupaten', '', '', 1);

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
  `role` enum('super admin','admin event','admin pentas','admin seniman','admin tempat','masyarakat') NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(191) NOT NULL,
  `foto` varchar(45) DEFAULT NULL,
  `verifikasi` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id_user`, `nama_lengkap`, `no_telpon`, `jenis_kelamin`, `tanggal_lahir`, `tempat_lahir`, `role`, `email`, `password`, `foto`, `verifikasi`) VALUES
(1, 'akun pemulihan', '089999999999', 'laki-laki', '2023-10-29', 'Nganjuk', 'super admin', 'akunceer1satu@gmail.com', '1', NULL, 0),
(32, 'amirzan fikri', '088884144', 'laki-laki', '2000-12-12', 'indonesia', 'masyarakat', 'amirzanfikri5@gmail.com', '$2y$10$q6qLZmxecCFOQJ0nKYKjOuyZMwyv5xj0CrY0A.ehKTeTMwW2ERo2m', NULL, 1),
(33, 'event admin', '088112223392590', 'perempuan', '2023-10-06', 'planet matahari', 'admin event', 'AdminEvent1@gmail.com', '$2y$10$GwXkmSLqY.Xx.QbXB9gqlezrPBwMT/i3.0CWJkDQyDC1UTvT.AcKm', NULL, 1),
(34, 'admin pentas', '08626268661886', 'perempuan', '2023-10-07', 'jerman', 'admin pentas', 'AdminPentas@gmail.com', '$2y$10$vr/ZoXhq2wtgZDpSIqNxtuzGjBiChSPqk7iw/TCioOKZQ0O7BsBem', NULL, 1),
(35, 'admin tempat', '089095295929505', 'laki-laki', '2023-10-06', 'planet jupiter', 'admin tempat', 'AdminTempat@gmail.com', '$2y$10$de7R3BL2pZRX9QX/TFufF.4BOtqkJOpML2PShgYQRYpDVC2cE2.du', NULL, 1),
(36, 'admin seniman', '08881515157751', 'laki-laki', '2023-10-07', 'akhirat', 'admin seniman', 'AdminSeniman@gmail.com', '$2y$10$bamAD6WkuqxHBFn7U.GQFupabPM03xXRZFEXpPqITn49QuJU7fhCC', NULL, 1),
(37, 'super admin', '0881122233', 'laki-laki', '2023-10-07', 'planet jupiter', 'super admin', 'SuperAdmin@gmail.com', '$2y$10$FiZNvFMIYLIAUGqNiN9VQeKaA6vTzr/7.nXJMn9KEN/67nFksrlsS', NULL, 1),
(38, 'admin', '0888151515', 'perempuan', '2023-10-13', 'akhirat', 'super admin', 'Admin@gmail.com', '$2y$10$tz2Qd71cf4ZKyk02vz4Ye.smoiw/N1/38KlzpgFNJwWK9VEP9rv6a', NULL, 1),
(41, 'joko', '0881122233', 'laki-laki', '2023-10-13', 'akhirat', 'admin tempat', 'random@gmail.com', '$2y$10$0RlQ7RJ9U1nN7E1YuUkhnOaTYbcANSNIEYzUumq.xCz0g1CjrgKLm', '/41.jpg', 1),
(42, 'asep', '0881122233', 'laki-laki', '2023-10-14', 'akhirat', 'super admin', 'terserah@gmail.com', '$2y$10$xOVWc1Q8CvI1kSsco0qfs.enMFFOHuZdws.otsKPWyBrncw7RAQDS', '/42.jpg', 1),
(49, 'fadil', '0854213346433', 'laki-laki', '0000-00-00', '', 'masyarakat', 'fadillahwahyunugraha@gmail.com', '$2y$10$cy9eBMwt8J8jRIlJTQaTmOxv/lwcB3oUM1Oa/ZFtSRKsYyeM0cWeu', NULL, 0),
(50, 'fadil', '085322552555', 'laki-laki', '0000-00-00', '', 'masyarakat', 'dappganzzshop@gmail.com', '$2y$10$05x4.7quHSxTfY2yVJKj/.ix6UhkItNwuusYNezU12nHvxfHQOjfy', NULL, 0),
(51, 'fadill', '0854331364616', 'laki-laki', '0000-00-00', '', 'masyarakat', 'akunceer3tiga@gmail.com', '$2y$10$zUs5P8eP9XhMBT8GUNPUHe0k/1W9qZvCIO6YC.5dQkcETv0WrYerq', NULL, 0);

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
  `id_user` int(11) DEFAULT NULL,
  `resend` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `verifikasi`
--

INSERT INTO `verifikasi` (`id_verifikasi`, `email`, `kode_otp`, `link`, `deskripsi`, `created_at`, `updated_at`, `id_user`, `resend`) VALUES
(16, 'fadillahwahyunugraha@gmail.com', 842591, '', '', '2023-10-29 03:04:07', '2023-10-29 03:04:07', 1, 0),
(17, 'fadillahwahyunugraha@gmail.com', 234926, '', '', '2023-10-29 03:16:23', '2023-10-29 03:16:23', 1, 0),
(18, 'fadillahwahyunugraha@gmail.com', 578716, '', '', '2023-10-29 03:23:12', '2023-10-29 03:23:12', 1, 0),
(19, 'fadillahwahyunugraha@gmail.com', 657362, '', '', '2023-10-29 03:32:52', '2023-10-29 03:32:52', 1, 0),
(20, 'fadillahwahyunugraha@gmail.com', 550530, '', '', '2023-10-29 03:44:07', '2023-10-29 03:44:07', 1, 0),
(21, 'fadillahwahyunugraha@gmail.com', 36817, '', '', '2023-10-29 03:59:04', '2023-10-29 03:59:04', 1, 0),
(22, 'fadillahwahyunugraha@gmail.com', 132160, '', '', '2023-10-29 04:01:33', '2023-10-29 03:59:35', 1, 0),
(23, 'fadillahwahyunugraha@gmail.com', 173255, '', '', '2023-10-29 04:41:48', '2023-10-29 04:41:48', 1, 0),
(24, 'fadillahwahyunugraha@gmail.com', 398272, '', '', '2023-10-29 04:43:14', '2023-10-29 04:43:14', 1, 0),
(25, 'fadillahwahyunugraha@gmail.com', 21834, '', '', '2023-10-29 04:46:21', '2023-10-29 04:46:21', 1, 0),
(26, 'fadillahwahyunugraha@gmail.com', 783639, '', '', '2023-10-29 04:46:35', '2023-10-29 04:46:35', 1, 0),
(27, 'fadillahwahyunugraha@gmail.com', 515200, '', '', '2023-10-29 04:50:01', '2023-10-29 04:50:01', 1, 0),
(28, 'fadillahwahyunugraha@gmail.com', 886880, '', '', '2023-10-29 04:51:41', '2023-10-29 04:51:41', 1, 0),
(29, 'fadillahwahyunugraha@gmail.com', 443627, '', '', '2023-10-29 05:03:50', '2023-10-29 05:03:50', 1, 0),
(30, 'fadillahwahyunugraha@gmail.com', 669355, '', '', '2023-10-29 05:06:22', '2023-10-29 05:05:38', 1, 0),
(31, 'akunceer2dua@gmail.com', 391800, '', '', '2023-10-29 05:14:06', '2023-10-29 05:14:06', 1, 0),
(32, 'fadillahwahyunugraha@gmail.com', 690630, '', '', '2023-10-29 05:36:17', '2023-10-29 05:34:49', 1, 0),
(33, 'akunceer1satu@gmail.com', 116979, '', '', '2023-10-29 05:40:08', '2023-10-29 05:40:08', 1, 0),
(34, 'fadillahwahyunugraha@gmail.com', 460363, '', '', '2023-10-29 05:41:37', '2023-10-29 05:41:37', 1, 0),
(35, 'fadillahwahyunugraha@gmail.com', 403344, '', '', '2023-10-29 05:44:07', '2023-10-29 05:44:07', 1, 0),
(36, 'fadillahwahyunugraha@gmail.com', 800036, '', '', '2023-10-29 05:45:47', '2023-10-29 05:45:47', 1, 0),
(37, 'fadillahwahyunugraha@gmail.com', 716098, '', '', '2023-10-29 05:48:29', '2023-10-29 05:48:29', 1, 0),
(38, 'dappganzzshop@gmail.com', 579203, '', '', '2023-10-29 06:07:18', '2023-10-29 06:07:18', 1, 0),
(39, 'dappganzzshop@gmail.com', 355052, '', '', '2023-10-29 06:10:26', '2023-10-29 06:10:26', 1, 0),
(40, 'dappganzzshop@gmail.com', 965112, '', '', '2023-10-29 06:41:00', '2023-10-29 06:11:48', 1, 0),
(41, 'fadillahwahyunugraha@gmail.com', 735719, '', '', '2023-10-29 06:19:05', '2023-10-29 06:19:05', 1, 0),
(42, 'fadillahwahyunugraha@gmail.com', 400253, '', '', '2023-10-29 06:32:33', '2023-10-29 06:32:33', 1, 0),
(43, 'fadillahwahyunugraha@gmail.com', 95422, '', '', '2023-10-29 06:33:32', '2023-10-29 06:33:32', 1, 0),
(44, 'fadillahwahyunugraha@gmail.com', 187580, '', '', '2023-10-29 06:37:11', '2023-10-29 06:37:11', 1, 0),
(45, 'fadillahwahyunugraha@gmail.com', 853831, '', '', '2023-10-29 06:39:48', '2023-10-29 06:39:48', 1, 0),
(46, 'dreamteampkmkc@gmail.com', 425006, '', '', '2023-10-29 06:51:04', '2023-10-29 06:48:24', 1, 0),
(47, 'akunceer3tiga@gmail.com', 961583, '', '', '2023-10-29 06:53:55', '2023-10-29 06:53:25', 1, 0),
(48, 'akunceer3tiga@gmail.com', 612727, '', '', '2023-10-29 06:57:12', '2023-10-29 06:57:12', 1, 0),
(49, 'akunceer3tiga@gmail.com', 999750, '', '', '2023-10-29 06:59:20', '2023-10-29 06:59:02', 1, 0),
(50, 'akunceer3tiga@gmail.com', 99096, '', '', '2023-10-29 07:01:28', '2023-10-29 07:01:10', 1, 0),
(51, 'akunceer3tiga@gmail.com', 351241, '', '', '2023-10-29 07:05:29', '2023-10-29 07:04:50', 1, 0),
(52, 'akunceer3tiga@gmail.com', 674379, '', '', '2023-10-29 07:08:38', '2023-10-29 07:08:07', 1, 0),
(53, 'akunceer3tiga@gmail.com', 805587, '', '', '2023-10-29 07:13:06', '2023-10-29 07:12:37', 1, 0),
(54, 'akunceer3tiga@gmail.com', 570886, '', '', '2023-10-29 07:15:26', '2023-10-29 07:14:57', 1, 0),
(55, 'akunceer3tiga@gmail.com', 262438, '', '', '2023-10-29 07:17:07', '2023-10-29 07:16:38', 1, 0),
(56, 'akunceer3tiga@gmail.com', 424420, '', '', '2023-10-29 07:24:10', '2023-10-29 07:19:45', 1, 0),
(57, 'akunceer3tiga@gmail.com', 409349, '', '', '2023-10-29 07:25:45', '2023-10-29 07:25:26', 1, 0),
(58, 'akunceer2dua@gmail.com', 972634, '', '', '2023-10-29 07:27:45', '2023-10-29 07:26:51', 1, 0),
(59, 'dappganzz4@gmail.com', 358633, '', '', '2023-10-29 07:32:17', '2023-10-29 07:30:52', 1, 0),
(60, 'akunceer3tiga@gmail.com', 144729, '', '', '2023-10-29 07:35:54', '2023-10-29 07:34:54', 1, 0),
(61, 'akunceer5lima@gmail.com', 725946, '', '', '2023-10-29 07:38:00', '2023-10-29 07:37:42', 1, 0),
(62, 'akunceer5lima@gmail.com', 502975, '', '', '2023-10-29 07:37:42', '2023-10-29 07:37:42', 1, 0),
(63, 'dappganzz4@gmail.com', 988935, '', '', '2023-10-29 07:41:26', '2023-10-29 07:41:09', 1, 0),
(64, 'akunceer2dua@gmail.com', 6929, '', '', '2023-10-29 08:08:09', '2023-10-29 07:42:17', 1, 0),
(65, 'fadillahwahyunugraha@gmail.com', 764400, '', '', '2023-10-29 07:46:44', '2023-10-29 07:46:44', 1, 0),
(66, 'fadillahwahyunugraha@gmail.com', 650294, '', '', '2023-10-29 07:52:29', '2023-10-29 07:52:29', 1, 0),
(67, 'fadillahwahyunugraha@gmail.com', 365577, '', '', '2023-10-29 07:54:55', '2023-10-29 07:54:55', 1, 0),
(68, 'fadillahwahyunugraha@gmail.com', 490032, '', '', '2023-10-29 07:55:48', '2023-10-29 07:55:48', 1, 0),
(69, 'fadillahwahyunugraha@gmail.com', 235892, '', '', '2023-10-29 07:58:05', '2023-10-29 07:58:05', 1, 0),
(70, 'fadillahwahyunugraha@gmail.com', 427155, '', '', '2023-10-29 07:59:55', '2023-10-29 07:59:55', 1, 0),
(71, 'fadillahwahyunugraha@gmail.com', 627068, '', '', '2023-10-29 08:04:30', '2023-10-29 08:04:30', 1, 0),
(72, 'fadillahwahyunugraha@gmail.com', 613704, '', '', '2023-10-29 08:05:55', '2023-10-29 08:05:55', 1, 0),
(73, 'fadillahwahyunugraha@gmail.com', 946566, '', '', '2023-10-29 08:06:06', '2023-10-29 08:06:06', 1, 0),
(74, 'fadillahwahyunugraha@gmail.com', 24483, '', '', '2023-10-29 08:07:53', '2023-10-29 08:07:53', 1, 0),
(75, 'fadillahwahyunugraha@gmail.com', 181058, '', '', '2023-10-29 08:11:15', '2023-10-29 08:11:15', 1, 0),
(76, 'akunceer3tiga@gmail.com', 631855, '', '', '2023-10-29 08:14:50', '2023-10-29 08:12:28', 1, 0),
(77, 'fadillahwahyunugraha@gmail.com', 405856, '', '', '2023-10-29 08:14:22', '2023-10-29 08:14:22', 1, 0);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detail_events`
--
ALTER TABLE `detail_events`
  ADD PRIMARY KEY (`id_detail`);

--
-- Indeks untuk tabel `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id_event`),
  ADD KEY `eventFK` (`id_user`),
  ADD KEY `sewaFK` (`id_sewa`);

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
-- AUTO_INCREMENT untuk tabel `detail_events`
--
ALTER TABLE `detail_events`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `events`
--
ALTER TABLE `events`
  MODIFY `id_event` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `list_tempat`
--
ALTER TABLE `list_tempat`
  MODIFY `id_tempat` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `refresh_token`
--
ALTER TABLE `refresh_token`
  MODIFY `id_token` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- AUTO_INCREMENT untuk tabel `seniman`
--
ALTER TABLE `seniman`
  MODIFY `id_seniman` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `sewa_tempat`
--
ALTER TABLE `sewa_tempat`
  MODIFY `id_sewa` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT untuk tabel `surat_advis`
--
ALTER TABLE `surat_advis`
  MODIFY `id_advis` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT untuk tabel `verifikasi`
--
ALTER TABLE `verifikasi`
  MODIFY `id_verifikasi` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `eventFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `sewaFK` FOREIGN KEY (`id_sewa`) REFERENCES `sewa_tempat` (`id_sewa`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
  ADD CONSTRAINT `advisFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ketidakleluasaan untuk tabel `verifikasi`
--
ALTER TABLE `verifikasi`
  ADD CONSTRAINT `verifyfk` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
