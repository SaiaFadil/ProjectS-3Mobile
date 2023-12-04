-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 04 Des 2023 pada 04.48
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
  `poster_event` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `detail_events`
--

INSERT INTO `detail_events` (`id_detail`, `nama_event`, `deskripsi`, `kategori`, `tempat_event`, `tanggal_awal`, `tanggal_akhir`, `link_pendaftaran`, `poster_event`) VALUES
(19, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/16.jpg'),
(20, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/16.jpg'),
(21, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/16.jpg'),
(22, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/16.jpg'),
(23, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/16.jpg'),
(24, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/16.jpg'),
(25, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/16.jpg'),
(26, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/16.jpg'),
(27, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/16.jpg'),
(28, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/17.jpg'),
(29, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/18.jpg'),
(30, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/19.jpg'),
(31, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/20.jpg'),
(32, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/21.jpg'),
(33, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/22.jpg'),
(34, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/23.jpg'),
(35, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/24.jpg'),
(36, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/25.jpg'),
(37, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/26.jpg'),
(38, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/27.jpg'),
(39, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/28.jpg'),
(40, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/29.jpg'),
(41, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/30.jpg'),
(42, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-12-15', '2023-12-15', 'google.com', '/2023/12/31.jpg');

-- --------------------------------------------------------

--
-- Struktur dari tabel `events`
--

CREATE TABLE `events` (
  `id_event` int(11) NOT NULL,
  `nama_pengirim` varchar(30) NOT NULL,
  `status` enum('diajukan','proses','diterima','ditolak') NOT NULL,
  `catatan` text DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `id_detail` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `events`
--

INSERT INTO `events` (`id_event`, `nama_pengirim`, `status`, `catatan`, `created_at`, `updated_at`, `id_detail`, `id_user`) VALUES
(16, 'kareppp ', 'diterima', NULL, '2023-11-20 08:56:15', '2023-11-20 08:56:15', 27, 2),
(17, 'kareppp ', 'diterima', NULL, '2023-11-20 08:56:20', '2023-11-20 08:56:20', 28, 2),
(18, 'kareppp ', 'diterima', NULL, '2023-11-20 08:56:21', '2023-11-20 08:56:21', 29, 2),
(19, 'kareppp ', 'diterima', NULL, '2023-11-20 08:56:22', '2023-11-20 08:56:22', 30, 2),
(20, 'kareppp ', 'diterima', NULL, '2023-11-20 08:56:22', '2023-11-20 08:56:22', 31, 2),
(21, 'kareppp ', 'diterima', NULL, '2023-11-20 08:56:23', '2023-11-20 08:56:23', 32, 2),
(22, 'kareppp ', 'diajukan', NULL, '2023-11-24 09:57:11', '2023-11-24 09:57:11', 33, 2),
(23, 'kareppp ', 'diajukan', NULL, '2023-11-24 09:57:14', '2023-11-24 09:57:14', 34, 2),
(24, 'kareppp ', 'diajukan', NULL, '2023-11-24 09:57:14', '2023-11-24 09:57:14', 35, 2),
(25, 'kareppp ', 'diterima', NULL, '2023-11-24 09:57:15', '2023-11-24 09:57:15', 36, 2),
(26, 'kareppp ', 'diajukan', NULL, '2023-11-26 20:15:04', '2023-11-26 20:15:04', 37, 2),
(27, 'kareppp ', 'diajukan', NULL, '2023-11-26 20:15:05', '2023-11-26 20:15:05', 38, 2),
(28, 'kareppp ', 'diajukan', NULL, '2023-11-26 20:15:06', '2023-11-26 20:15:06', 39, 2),
(29, 'kareppp ', 'diajukan', NULL, '2023-11-26 20:15:08', '2023-11-26 20:15:08', 40, 2),
(30, 'kareppp ', 'diajukan', NULL, '2023-11-26 20:15:09', '2023-11-26 20:15:09', 41, 2),
(31, 'kareppp ', 'ditolak', 'salahmu dewe\r\n', '2023-11-26 20:15:11', '2023-11-26 20:15:11', 42, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `histori_nis`
--

CREATE TABLE `histori_nis` (
  `id_histori` int(11) NOT NULL,
  `nis` varchar(45) NOT NULL,
  `tahun` varchar(5) NOT NULL,
  `id_seniman` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori_seniman`
--

CREATE TABLE `kategori_seniman` (
  `id_kategori_seniman` int(2) NOT NULL,
  `nama_kategori` varchar(45) NOT NULL,
  `singkatan_kategori` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kategori_seniman`
--

INSERT INTO `kategori_seniman` (`id_kategori_seniman`, `nama_kategori`, `singkatan_kategori`) VALUES
(1, 'campursari', 'CAMP'),
(2, 'dalang', 'DLG'),
(3, 'jaranan', 'JKP'),
(4, 'karawitan', 'KRW'),
(5, 'mc', 'MC'),
(6, 'ludruk', 'LDR'),
(7, 'organisasi kesenian musik', 'OKM'),
(8, 'organisasi', 'ORG'),
(9, 'pramugari tayup', 'PRAM'),
(10, 'sanggar', 'SGR'),
(11, 'sinden', 'SIND'),
(12, 'vocalis', 'VOC'),
(13, 'waranggono', 'WAR'),
(14, 'barongsai', 'BAR'),
(15, 'ketoprak', 'KTP'),
(16, 'pataji', 'PTJ'),
(17, 'reog', 'REOG'),
(18, 'taman hiburan rakyat', 'THR'),
(19, 'pelawak', 'PLWK');

-- --------------------------------------------------------

--
-- Struktur dari tabel `list_tempat`
--

CREATE TABLE `list_tempat` (
  `id_tempat` int(5) NOT NULL,
  `nama_tempat` varchar(50) NOT NULL,
  `alamat_tempat` varchar(50) NOT NULL,
  `deskripsi_tempat` varchar(500) NOT NULL,
  `pengelola` varchar(50) NOT NULL,
  `contact_person` varchar(15) NOT NULL,
  `foto_tempat` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `list_tempat`
--

INSERT INTO `list_tempat` (`id_tempat`, `nama_tempat`, `alamat_tempat`, `deskripsi_tempat`, `pengelola`, `contact_person`, `foto_tempat`) VALUES
(1, 'Museum Anjuk Ladang ', 'Jl. Gatot Subroto Kec. Nganjuk Kab. Nganjuk ', 'Museum Anjuk Ladang Terletak di kota Nganjuk, tepatnya sebelah timur Terminal Bus Kota Nganjuk, di dalamnya tersimpan benda dan cagar budaya pada zaman Hindu, Doho dan Majapahit yang terdapat di daerah Kabupaten Nganjuk. Disamping itu di simpan Prasasti Anjuk Ladang yang merupakan cikal bakal berdirinya Kabupaten Nganjuk.', 'wong terserah', '08414141', '/1.png'),
(2, 'Balai Budaya ', 'Mangundikaran, Kec. Nganjuk, Kab. Nganjuk', 'Gedung Balai Budaya Nganjuk adalah salah satu legenda bangunan bersejarah di Kabupaten Nganjuk. Gedung ini bisa digunakan untuk berbagai acara.', 'random', '0888515151', '/2.png'),
(3, 'Monumen Dr. Soetomo ', 'Sono, Ngepeh, Kec. Loceret Kab. Nganjuk', 'Monumen Dr. Soetomo Nganjuk yang menempati tanah seluas 3,5 ha ini merupakan tempat kelahiran Dr. Soetomo Secara keseluruhan kompleks bangunan ini terdiri dari patung Dr. Soetomo, Pendopo induk, yang terletak di belakang patung, dan bangunan pringgitan jumlahnya 2 buah masing-masing 6 x 12 m.', 'gabut', '08881515', '/3.png'),
(4, 'Air Terjun Sedudo', 'Jl. Sedudo Kec. Sawahan Kab. Nganjuk', 'Air Terjun Sedudo adalah sebuah air terjun dan objek wisata yang terletak di Desa Ngliman Kecamatan Sawahan, Kabupaten Nganjuk, Jawa Timur. Jaraknya sekitar 30 km arah selatan ibu kota kabupaten Nganjuk. Berada pada ketinggian 1.438 meter dpl, ketinggian air terjun ini sekitar 105 meter. Tempat wisata ini memiliki fasilitas yang cukup baik, dan jalur transportasi yang mudah diakses.', 'nvonvonvoa', '08885151', '/4.png'),
(5, 'Goa Margo Tresno ', 'Ngluyu, Kec. Ngluyu Kab. Nganjuk ', 'Goa Margo Tresno adalah salah satu obyek wisata di Jawa Timur yang terletak di Dusun Cabean, Desa Sugih Waras, Kecamatan Ngluyu, Kabupaten Nganjuk. Wisata Goa Margo Tresno Nganjuk adalah destinasi wisata yang ramai dengan wisatawan baik dari dalam maupun luar kota pada hari biasa maupun hari liburan dan sudah terkenal di Nganjuk dan sekitarnya.', 'vaiai', '08885115', '/5.png'),
(6, 'Air Terjun Roro Kuning', 'Nglarangan, Bajulan, Kec. Loceret Kab. Nganjuk', 'Air Terjun Roro Kuning adalah sebuah air terjun yang berada sekitar 27–30 km selatan kota Nganjuk, di ketinggian 600 m dpl dan memiliki tinggi antara 10–15 m. Air terjun ini mengalir dari tiga sumber di sekitar Gunung Wilis yang mengalir merambat di sela-sela bebatuan padas di bawah pepohonan hutan pinus.', 'avavava', '08855155', '/6.png'),
(19, 'tanahh', 'jalan pegunungan', 'aaonna\r\n', 'admminnn', '088999151', '/7.png');

-- --------------------------------------------------------

--
-- Struktur dari tabel `perpanjangan`
--

CREATE TABLE `perpanjangan` (
  `id_perpanjangan` int(11) NOT NULL,
  `nik` varchar(45) NOT NULL,
  `ktp_seniman` text NOT NULL,
  `pass_foto` text NOT NULL,
  `surat_keterangan` text NOT NULL,
  `tgl_pembuatan` date NOT NULL,
  `kode_verifikasi` varchar(45) DEFAULT NULL,
  `status` enum('diajukan','proses','diterima','ditolak') NOT NULL,
  `catatan` text DEFAULT NULL,
  `id_seniman` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `perpanjangan`
--

INSERT INTO `perpanjangan` (`id_perpanjangan`, `nik`, `ktp_seniman`, `pass_foto`, `surat_keterangan`, `tgl_pembuatan`, `kode_verifikasi`, `status`, `catatan`, `id_seniman`, `id_user`) VALUES
(7, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6.jpg', '/download.jpeg', '/11. Recursive 1.pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2),
(8, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6(1).jpg', '/download(1).jpeg', '/11. Recursive 1(1).pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2),
(9, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6(2).jpg', '/download(2).jpeg', '/11. Recursive 1(2).pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2),
(10, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6(3).jpg', '/download(3).jpeg', '/11. Recursive 1(3).pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2),
(11, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6(4).jpg', '/download(4).jpeg', '/11. Recursive 1(4).pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2),
(12, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6(5).jpg', '/download(5).jpeg', '/11. Recursive 1(5).pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2),
(13, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6(6).jpg', '/download(6).jpeg', '/11. Recursive 1(6).pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2),
(14, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6(7).jpg', '/download(7).jpeg', '/11. Recursive 1(7).pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2),
(15, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6(8).jpg', '/download(8).jpeg', '/11. Recursive 1(8).pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2),
(16, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6(9).jpg', '/download(9).jpeg', '/11. Recursive 1(9).pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2),
(17, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6(10).jpg', '/download(10).jpeg', '/11. Recursive 1(10).pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2),
(18, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6(11).jpg', '/download(11).jpeg', '/11. Recursive 1(11).pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2),
(19, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6(12).jpg', '/download(12).jpeg', '/11. Recursive 1(12).pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2),
(20, '41414515', '/_106804932_b820b700-66c0-4874-84c6-783e271f76e6(13).jpg', '/download(13).jpeg', '/11. Recursive 1(13).pdf', '0000-00-00', NULL, 'diajukan', NULL, 89, 2);

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
(304, 'SuperAdmin@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjoxLCJuYW1hX2xlbmdrYXAiOiJzdXBlciBhZG1pbiIsIm5vX3RlbHBvbiI6IjA4ODExMjIyMzMiLCJqZW5pc19rZWxhbWluIjoibGFraS1sYWtpIiwidGFuZ2dhbF9sYWhpciI6IjIwMjMtMTAtMDciLCJ0ZW1wYXRfbGFoaXIiOiJwbGFuZXQganVwaXRlciIsImVtYWlsIjoiU3VwZXJBZG1pbkBnbWFpbC5jb20iLCJyb2xlIjoic3VwZXIgYWRtaW4iLCJmb3RvIjoiXC8xLmpwZWciLCJudW1iZXIiOjMsImV4cCI6MTcwMTUxNTEwMn0.csQ-XkNCk5bbSra0nzoDUU27GRkCKxiqw4oP6TLZuBU', 'website', 1, '2023-12-01 11:05:02', '2023-12-01 11:05:02', 1),
(305, 'SuperAdmin@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjoxLCJuYW1hX2xlbmdrYXAiOiJzdXBlciBhZG1pbiIsIm5vX3RlbHBvbiI6IjA4ODExMjIyMzMiLCJqZW5pc19rZWxhbWluIjoibGFraS1sYWtpIiwidGFuZ2dhbF9sYWhpciI6IjIwMjMtMTAtMDciLCJ0ZW1wYXRfbGFoaXIiOiJwbGFuZXQganVwaXRlciIsImVtYWlsIjoiU3VwZXJBZG1pbkBnbWFpbC5jb20iLCJyb2xlIjoic3VwZXIgYWRtaW4iLCJmb3RvIjoiXC8xLmpwZWciLCJudW1iZXIiOjMsImV4cCI6MTcwMTUyMjA3OX0.woHzMZo7IWcMK_MEv1utsSCaZnPkhRg7CVPdSFikVqs', 'website', 2, '2023-12-01 13:01:19', '2023-12-01 13:01:19', 1),
(306, 'SuperAdmin@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjoxLCJuYW1hX2xlbmdrYXAiOiJzdXBlciBhZG1pbiIsIm5vX3RlbHBvbiI6IjA4ODExMjIyMzMiLCJqZW5pc19rZWxhbWluIjoibGFraS1sYWtpIiwidGFuZ2dhbF9sYWhpciI6IjIwMjMtMTAtMDciLCJ0ZW1wYXRfbGFoaXIiOiJwbGFuZXQganVwaXRlciIsImVtYWlsIjoiU3VwZXJBZG1pbkBnbWFpbC5jb20iLCJyb2xlIjoic3VwZXIgYWRtaW4iLCJmb3RvIjoiXC8xLmpwZWciLCJudW1iZXIiOjMsImV4cCI6MTcwMTUyNTcyNn0.bA4vEXHuVINGES9s744SCNaFRloS9pkXNvI2EWQ2G2I', 'website', 3, '2023-12-01 14:02:06', '2023-12-01 14:02:06', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `seniman`
--

CREATE TABLE `seniman` (
  `id_seniman` int(11) NOT NULL,
  `nik` int(16) NOT NULL,
  `nomor_induk` varchar(20) DEFAULT NULL,
  `nama_seniman` varchar(30) NOT NULL,
  `jenis_kelamin` enum('laki-laki','perempuan') NOT NULL,
  `kecamatan` enum('bagor','baron','berbek','gondang','jatikalen','kertosono','lengkong','loceret','nganjuk','ngetos','ngluyu','ngronggot','pace','patianrowo','prambon','rejoso','sawahan','sukomoro','tanjunganom','wilangan') NOT NULL,
  `tempat_lahir` varchar(30) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `alamat_seniman` varchar(50) NOT NULL,
  `no_telpon` varchar(15) NOT NULL,
  `nama_organisasi` varchar(50) DEFAULT NULL,
  `jumlah_anggota` int(5) DEFAULT NULL,
  `ktp_seniman` text NOT NULL,
  `pass_foto` text NOT NULL,
  `surat_keterangan` text NOT NULL,
  `tgl_pembuatan` date NOT NULL,
  `tgl_berlaku` date NOT NULL,
  `kode_verifikasi` varchar(45) DEFAULT NULL,
  `status` enum('diajukan','proses','diterima','ditolak') NOT NULL,
  `catatan` text DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `id_kategori_seniman` int(2) NOT NULL,
  `id_user` int(11) NOT NULL,
  `singkatan_kategori` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `sewa_tempat`
--

CREATE TABLE `sewa_tempat` (
  `id_sewa` int(5) NOT NULL,
  `nik_sewa` int(16) NOT NULL,
  `nama_peminjam` varchar(30) NOT NULL,
  `nama_tempat` varchar(50) DEFAULT NULL,
  `deskripsi_sewa_tempat` varchar(100) DEFAULT NULL,
  `nama_kegiatan_sewa` varchar(50) DEFAULT NULL,
  `jumlah_peserta` int(10) DEFAULT NULL,
  `instansi` varchar(50) DEFAULT NULL,
  `surat_ket_sewa` text DEFAULT NULL,
  `tgl_awal_peminjaman` datetime DEFAULT NULL,
  `tgl_akhir_peminjaman` datetime DEFAULT NULL,
  `kode_verifikasi` varchar(45) DEFAULT NULL,
  `status` enum('diajukan','proses','diterima','ditolak') NOT NULL,
  `catatan` text DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `id_tempat` int(5) NOT NULL,
  `id_user` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `sewa_tempat`
--

INSERT INTO `sewa_tempat` (`id_sewa`, `nik_sewa`, `nama_peminjam`, `nama_tempat`, `deskripsi_sewa_tempat`, `nama_kegiatan_sewa`, `jumlah_peserta`, `instansi`, `surat_ket_sewa`, `tgl_awal_peminjaman`, `tgl_akhir_peminjaman`, `kode_verifikasi`, `status`, `catatan`, `created_at`, `updated_at`, `id_tempat`, `id_user`) VALUES
(20, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1.pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-16 22:04:35', '2023-11-16 22:04:35', 2, 2),
(21, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-16 22:04:37', '2023-11-16 22:04:37', 2, 2),
(22, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-16 22:04:39', '2023-11-16 22:04:39', 2, 2),
(23, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-21 14:31:33', '2023-11-21 14:31:33', 2, 2),
(24, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-21 14:31:34', '2023-11-21 14:31:34', 2, 2),
(25, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-21 14:31:35', '2023-11-21 14:31:35', 2, 2),
(26, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', '', 'diajukan', NULL, '2023-11-21 14:31:36', '2023-11-21 14:31:36', 2, 2),
(27, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-21 14:31:36', '2023-11-21 14:31:36', 2, 2),
(28, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-21 14:31:37', '2023-11-21 14:31:37', 2, 2),
(29, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:07', '2023-11-26 20:17:07', 2, 2),
(30, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:08', '2023-11-26 20:17:08', 2, 2),
(31, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:09', '2023-11-26 20:17:09', 2, 2),
(32, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:10', '2023-11-26 20:17:10', 2, 2),
(33, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:11', '2023-11-26 20:17:11', 2, 2),
(34, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:12', '2023-11-26 20:17:12', 2, 2),
(35, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:16', '2023-11-26 20:17:16', 2, 2),
(36, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:17', '2023-11-26 20:17:17', 2, 2),
(37, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:18', '2023-11-26 20:17:18', 2, 2),
(38, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:20', '2023-11-26 20:17:20', 2, 2),
(39, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:23', '2023-11-26 20:17:23', 2, 2),
(40, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:24', '2023-11-26 20:17:24', 2, 2),
(41, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:25', '2023-11-26 20:17:25', 2, 2),
(42, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:25', '2023-11-26 20:17:25', 2, 2),
(43, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:29', '2023-11-26 20:17:29', 2, 2),
(44, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:17:30', '2023-11-26 20:17:30', 2, 2),
(45, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:20:18', '2023-11-26 20:20:18', 2, 2),
(46, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:20:19', '2023-11-26 20:20:19', 2, 2),
(47, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', '', 'ditolak', '0', '2023-11-26 20:20:20', '2023-11-26 20:20:20', 2, 2),
(48, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', NULL, 'diajukan', NULL, '2023-11-26 20:20:22', '2023-11-26 20:20:22', 2, 2),
(49, 2147483647, 'joshhh', 'amerika', 'menyewa tempat karepku lah', 'olahraga sepeda', 100, 'jerman', '/11. Recursive 1(1).pdf', '2023-12-14 06:01:01', '2023-12-30 12:10:11', '6566b6b8d8', 'diterima', NULL, '2023-11-26 20:20:23', '2023-11-26 20:20:23', 2, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `surat_advis`
--

CREATE TABLE `surat_advis` (
  `id_advis` int(5) NOT NULL,
  `nomor_induk` varchar(20) NOT NULL,
  `nama_advis` varchar(30) NOT NULL,
  `alamat_advis` varchar(100) NOT NULL,
  `deskripsi_advis` varchar(100) DEFAULT NULL,
  `tgl_advis` date NOT NULL,
  `tempat_advis` varchar(30) NOT NULL,
  `kode_verifikasi` varchar(45) DEFAULT NULL,
  `status` enum('diajukan','proses','diterima','ditolak') DEFAULT NULL,
  `catatan` text DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_seniman` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `surat_advis`
--

INSERT INTO `surat_advis` (`id_advis`, `nomor_induk`, `nama_advis`, `alamat_advis`, `deskripsi_advis`, `tgl_advis`, `tempat_advis`, `kode_verifikasi`, `status`, `catatan`, `created_at`, `updated_at`, `id_user`, `id_seniman`) VALUES
(23, 'DLG/001/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', '656011f4d9', 'diterima', NULL, '2023-11-24 10:00:46', '2023-11-24 10:00:46', 2, 90),
(24, 'DLG/001/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', '', 'proses', NULL, '2023-11-24 10:00:47', '2023-11-24 10:00:47', 2, 90),
(25, 'DLG/001/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-24 10:00:49', '2023-11-24 10:00:49', 2, 90),
(26, 'DLG/001/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', '656011ecba', 'diterima', NULL, '2023-11-24 10:00:49', '2023-11-24 10:00:49', 2, 90),
(27, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:35', '2023-11-26 20:18:35', 2, 89),
(28, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:36', '2023-11-26 20:18:36', 2, 89),
(29, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:37', '2023-11-26 20:18:37', 2, 89),
(30, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:38', '2023-11-26 20:18:38', 2, 89),
(31, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:39', '2023-11-26 20:18:39', 2, 89),
(32, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:40', '2023-11-26 20:18:40', 2, 89),
(33, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:40', '2023-11-26 20:18:40', 2, 89),
(34, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:41', '2023-11-26 20:18:41', 2, 89),
(35, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:42', '2023-11-26 20:18:42', 2, 89),
(36, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:42', '2023-11-26 20:18:42', 2, 89),
(37, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:47', '2023-11-26 20:18:47', 2, 89),
(38, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:48', '2023-11-26 20:18:48', 2, 89),
(39, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:49', '2023-11-26 20:18:49', 2, 89),
(40, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:50', '2023-11-26 20:18:50', 2, 89),
(41, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:51', '2023-11-26 20:18:51', 2, 89),
(42, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', '6566b6c605', 'diterima', NULL, '2023-11-26 20:18:52', '2023-11-26 20:18:52', 2, 89),
(43, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:53', '2023-11-26 20:18:53', 2, 89),
(44, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', NULL, 'diajukan', NULL, '2023-11-26 20:18:55', '2023-11-26 20:18:55', 2, 89),
(45, 'DLG/004/411.302/2023', 'aseppp', 'nganjukinonesioa', 'wayanggg', '2024-11-20', 'planet nganjuk', '', 'ditolak', 'aokwokaokook', '2023-11-26 20:18:58', '2023-11-26 20:18:58', 2, 89),
(46, 'DLG/005/411.302/2023', 'Fadillah wahyu nugraha', 'Nganjuk', 'Blitar', '2024-02-02', 'Blitar', '23e23mj2ni23b2i3', 'diajukan', '', '2023-12-03 07:41:23', '2023-12-03 07:42:39', 2, 101);

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
  `role` enum('super admin','admin event','admin seniman','admin tempat','masyarakat') NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(191) NOT NULL,
  `foto` varchar(45) DEFAULT NULL,
  `verifikasi` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id_user`, `nama_lengkap`, `no_telpon`, `jenis_kelamin`, `tanggal_lahir`, `tempat_lahir`, `role`, `email`, `password`, `foto`, `verifikasi`) VALUES
(1, 'super admin', '0881122233', 'laki-laki', '2023-10-07', 'planet jupiter', 'super admin', 'SuperAdmin@gmail.com', '$2y$10$M1fEjUm7I3i7z8bMOSzYm.9WzkGl9rHV8Av5soEhKgXbkkvt8VbO2', '/1.jpeg', 1),
(2, 'Fadillah wahyu nugraha', '0881122233', 'laki-laki', '2023-11-01', 'Blitar', 'masyarakat', 'fadillahwahyunugraha@gmail.com', '$2y$10$k0TaeSpsz6uGjWYDnoyi2OY9uv3qpmS8PITqgzr5nAQHfMu2z9dd.', '/45.jpeg', 1),
(46, 'admin seniman', '0888616161', 'laki-laki', '2023-11-04', 'jakarta bumi indonesia', 'admin seniman', 'AdminSeniman@gmail.com', '$2y$10$6HBxJHSsXi8BQU6BO5aWOOHMz4W900W/EYaTzX9dL486seaeSxo.6', '/46.jpeg', 1),
(47, 'admin tempat', '0888616161', 'perempuan', '2023-11-18', 'planet mars', 'admin tempat', 'AdminTempat@gmail.com', '$2y$10$euG7yuF809L7TkwJ2sSGeekfG1rI1WFu9O9c8ocwbgDUaR5jYMKqC', '/47.jpg', 1),
(48, 'admin event', '08881661', 'perempuan', '2023-11-04', 'planet jupiter', 'admin event', 'AdminEvent@gmail.com', '$2y$10$5Nc3eDjWLgcDLvGs68a88ummOXRpONff3n4hXPjt/QpbjL4D2CkGu', NULL, 1),
(52, 'AdminTesting', '088418441441', 'perempuan', '2023-10-31', 'jakarta pulau sumatra', 'super admin', 'AdminTest@gmail.com', '$2y$10$mqjaS23H8EgOfx1dwKmO4eJhVw3Fers7yJOvzHfOTJss5cDgUkVOy', '/52.jpeg', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `verifikasi`
--

CREATE TABLE `verifikasi` (
  `id_verifikasi` int(10) UNSIGNED NOT NULL,
  `email` varchar(45) NOT NULL,
  `kode_otp` int(6) NOT NULL,
  `link` varchar(50) NOT NULL,
  `deskripsi` enum('password','email') NOT NULL,
  `send` int(2) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `verifikasi`
--

INSERT INTO `verifikasi` (`id_verifikasi`, `email`, `kode_otp`, `link`, `deskripsi`, `send`, `created_at`, `updated_at`, `id_user`) VALUES
(18, 'amirzanfikri5@gmail.com', 268645, 'd210a056afd65edfcb25a21c8fb625b177c7e1f2d249730464', 'email', 0, '2023-11-16 22:24:11', '2023-11-16 22:24:11', 2),
(19, 'amirzanfikri5@gmail.com', 960690, '46b7e244fe40721e01f8cb3e9b5ae8fab04e257f5f4c841672', 'password', 0, '2023-11-16 22:24:52', '2023-11-16 22:24:52', 2);

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
  ADD KEY `detailFK` (`id_detail`);

--
-- Indeks untuk tabel `histori_nis`
--
ALTER TABLE `histori_nis`
  ADD PRIMARY KEY (`id_histori`),
  ADD KEY `senimanHFK` (`id_seniman`);

--
-- Indeks untuk tabel `kategori_seniman`
--
ALTER TABLE `kategori_seniman`
  ADD PRIMARY KEY (`id_kategori_seniman`),
  ADD UNIQUE KEY `nama_kategori` (`nama_kategori`),
  ADD UNIQUE KEY `singkatan_kategori` (`singkatan_kategori`);

--
-- Indeks untuk tabel `list_tempat`
--
ALTER TABLE `list_tempat`
  ADD PRIMARY KEY (`id_tempat`);

--
-- Indeks untuk tabel `perpanjangan`
--
ALTER TABLE `perpanjangan`
  ADD PRIMARY KEY (`id_perpanjangan`),
  ADD KEY `senimanPFK` (`id_seniman`),
  ADD KEY `userPFK` (`id_user`);

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
  ADD KEY `senimanFK` (`id_user`),
  ADD KEY `kategoriSFK` (`id_kategori_seniman`),
  ADD KEY `singkatan_kategori` (`singkatan_kategori`);

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
  ADD KEY `advisFK` (`id_user`),
  ADD KEY `senimanSAFK` (`id_seniman`);

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
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT untuk tabel `events`
--
ALTER TABLE `events`
  MODIFY `id_event` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT untuk tabel `histori_nis`
--
ALTER TABLE `histori_nis`
  MODIFY `id_histori` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `kategori_seniman`
--
ALTER TABLE `kategori_seniman`
  MODIFY `id_kategori_seniman` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT untuk tabel `list_tempat`
--
ALTER TABLE `list_tempat`
  MODIFY `id_tempat` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT untuk tabel `perpanjangan`
--
ALTER TABLE `perpanjangan`
  MODIFY `id_perpanjangan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT untuk tabel `refresh_token`
--
ALTER TABLE `refresh_token`
  MODIFY `id_token` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=307;

--
-- AUTO_INCREMENT untuk tabel `seniman`
--
ALTER TABLE `seniman`
  MODIFY `id_seniman` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=112;

--
-- AUTO_INCREMENT untuk tabel `sewa_tempat`
--
ALTER TABLE `sewa_tempat`
  MODIFY `id_sewa` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT untuk tabel `surat_advis`
--
ALTER TABLE `surat_advis`
  MODIFY `id_advis` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT untuk tabel `verifikasi`
--
ALTER TABLE `verifikasi`
  MODIFY `id_verifikasi` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `detailFK` FOREIGN KEY (`id_detail`) REFERENCES `detail_events` (`id_detail`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `eventFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `histori_nis`
--
ALTER TABLE `histori_nis`
  ADD CONSTRAINT `senimanHFK` FOREIGN KEY (`id_seniman`) REFERENCES `seniman` (`id_seniman`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `perpanjangan`
--
ALTER TABLE `perpanjangan`
  ADD CONSTRAINT `senimanPFK` FOREIGN KEY (`id_seniman`) REFERENCES `seniman` (`id_seniman`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `userPFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `refresh_token`
--
ALTER TABLE `refresh_token`
  ADD CONSTRAINT `tokenFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `seniman`
--
ALTER TABLE `seniman`
  ADD CONSTRAINT `kategoriFK` FOREIGN KEY (`id_kategori_seniman`) REFERENCES `kategori_seniman` (`id_kategori_seniman`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `senimanFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `singkatanFK` FOREIGN KEY (`singkatan_kategori`) REFERENCES `kategori_seniman` (`singkatan_kategori`);

--
-- Ketidakleluasaan untuk tabel `surat_advis`
--
ALTER TABLE `surat_advis`
  ADD CONSTRAINT `advisFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `senimanSAFK` FOREIGN KEY (`id_seniman`) REFERENCES `seniman` (`id_seniman`);

--
-- Ketidakleluasaan untuk tabel `verifikasi`
--
ALTER TABLE `verifikasi`
  ADD CONSTRAINT `verifyfk` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
