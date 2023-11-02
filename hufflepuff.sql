-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 02, 2023 at 02:08 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

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
-- Table structure for table `detail_events`
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
-- Dumping data for table `detail_events`
--

INSERT INTO `detail_events` (`id_detail`, `nama_event`, `deskripsi`, `kategori`, `tempat_event`, `tanggal_awal`, `tanggal_akhir`, `link_pendaftaran`, `poster_event`) VALUES
(2, 'turu lah', 'inainvinivn', 'OLAHRAGA', 'nganjuk', '2023-11-15', '2023-11-15', 'google.com', '\\2023\\11\\2.jpg'),
(3, 'turu lah', 'inainvinivn', 'OLAHRAGA', 'planet luar bumi', '2023-11-15', '2023-11-15', 'google.com', '/2023/11/3.jpg'),
(4, 'sunmori', 'ayo sunmori di planet matahari', 'OLAHRAGA', 'planet matahari', '2023-11-15', '2023-11-15', 'google.com', '/2023/11/4.jpg'),
(5, 'mlaku mlaku', 'inainvinivn', 'OLAHRAGA', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/5.jpg'),
(6, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/6.jpg'),
(7, 'agustusan', 'inainvinivn', 'OLAHRAGA', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/7.jpg'),
(8, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/8.jpg'),
(9, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/9.jpg'),
(10, 'turu lah', 'inainvinivn', 'OLAHRAGA', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/10.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `events`
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
-- Dumping data for table `events`
--

INSERT INTO `events` (`id_event`, `nama_pengirim`, `status`, `catatan`, `id_detail`, `id_sewa`, `id_user`) VALUES
(2, 'kareppp ', 'ditolak', 'terserah', 2, NULL, 32),
(3, 'kareppp ', 'diterima', '', 3, NULL, 32),
(4, 'kareppp ', 'ditolak', 'erorr lah ', 4, NULL, 32),
(5, 'kareppp ', 'diajukan', NULL, 5, NULL, 32),
(6, 'kareppp ', 'diterima', '', 6, NULL, 32),
(7, 'kareppp ', 'proses', NULL, 7, NULL, 32),
(8, 'kareppp ', 'diajukan', NULL, 8, NULL, 32),
(9, 'kareppp ', 'diajukan', NULL, 9, NULL, 32),
(10, 'kareppp ', 'proses', NULL, 10, NULL, 32);

-- --------------------------------------------------------

--
-- Table structure for table `kategori_seniman`
--

CREATE TABLE `kategori_seniman` (
  `id_kategori_seniman` int(4) NOT NULL,
  `nama_kategori` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori_seniman`
--

INSERT INTO `kategori_seniman` (`id_kategori_seniman`, `nama_kategori`) VALUES
(1, 'VOC'),
(2, 'THR');

-- --------------------------------------------------------

--
-- Table structure for table `list_tempat`
--

CREATE TABLE `list_tempat` (
  `id_tempat` int(5) NOT NULL,
  `nama_tempat` varchar(50) NOT NULL,
  `alamat_tempat` varchar(50) NOT NULL,
  `deskripsi_tempat` varchar(500) DEFAULT NULL,
  `foto_tempat` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `list_tempat`
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
-- Table structure for table `perpanjangan`
--

CREATE TABLE `perpanjangan` (
  `id_perpanjangan` int(11) NOT NULL,
  `ktp_seniman` varchar(20) NOT NULL,
  `pass_foto` varchar(20) NOT NULL,
  `surat_keterangan` varchar(20) NOT NULL,
  `status` enum('diajukan','proses','diterima','ditolak') DEFAULT NULL,
  `catatan` text DEFAULT NULL,
  `id_seniman` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `refresh_token`
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
-- Dumping data for table `refresh_token`
--

INSERT INTO `refresh_token` (`id_token`, `email`, `token`, `device`, `number`, `created_at`, `updated_at`, `id_user`) VALUES
(28, 'amirzanfikri5@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjozMiwibmFtYV9sZW5na2FwIjoiYW1pcnphbiBmaWtyaSIsIm5vX3RlbHBvbiI6IiIsImplbmlzX2tlbGFtaW4iOiJsYWtpLWxha2kiLCJ0YW5nZ2FsX2xhaGlyIjoiMDAwMC0wMC0wMCIsInRlbXBhdF9sYWhpciI6IiIsImVtYWlsIjoiYW1pcnphbmZpa3JpNUBnbWFpbC5jb20iLCJyb2xlIjoiIiwibnVtYmVyIjozLCJleHAiOjE2OTcxNzIwNDR9._i3Wl0s4I3ElWbN4PJYx3H3Wvl9GDjdtY5joQA_7qdE', 'website', 1, '2023-10-13 03:40:44', '2023-10-13 03:40:44', 32),
(29, 'amirzanfikri5@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjozMiwibmFtYV9sZW5na2FwIjoiYW1pcnphbiBmaWtyaSIsIm5vX3RlbHBvbiI6IiIsImplbmlzX2tlbGFtaW4iOiJsYWtpLWxha2kiLCJ0YW5nZ2FsX2xhaGlyIjoiMDAwMC0wMC0wMCIsInRlbXBhdF9sYWhpciI6IiIsImVtYWlsIjoiYW1pcnphbmZpa3JpNUBnbWFpbC5jb20iLCJyb2xlIjoibWFzeWFyYWthdCIsIm51bWJlciI6MywiZXhwIjoxNjk3MTg4MzUxfQ.6BForsGt966s-MOF8drOomQf6amRzfNqzXlx7Q7T_7U', 'website', 2, '2023-10-13 08:12:31', '2023-10-13 08:12:31', 32),
(58, 'Admin@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjozOCwibmFtYV9sZW5na2FwIjoiYWRtaW4iLCJub190ZWxwb24iOiIwODg4MTUxNTE1IiwiamVuaXNfa2VsYW1pbiI6InBlcmVtcHVhbiIsInRhbmdnYWxfbGFoaXIiOiIyMDIzLTEwLTEzIiwidGVtcGF0X2xhaGlyIjoiYWtoaXJhdCIsImVtYWlsIjoiQWRtaW5AZ21haWwuY29tIiwicm9sZSI6InN1cGVyIGFkbWluIiwibnVtYmVyIjoxLCJleHAiOjMzOTQ4NTE3MjZ9.mEa9weCsbqwvHu32E0Ww-2hihlciOBsfJgOGqJkdozA', 'website', 1, '2023-10-16 02:41:03', '2023-10-16 02:41:03', 38),
(142, 'SuperAdmin@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjozNywibmFtYV9sZW5na2FwIjoic3VwZXIgYWRtaW4iLCJub190ZWxwb24iOiIwODgxMTIyMjMzIiwiamVuaXNfa2VsYW1pbiI6Imxha2ktbGFraSIsInRhbmdnYWxfbGFoaXIiOiIyMDIzLTEwLTA3IiwidGVtcGF0X2xhaGlyIjoicGxhbmV0IGp1cGl0ZXIiLCJlbWFpbCI6IlN1cGVyQWRtaW5AZ21haWwuY29tIiwicm9sZSI6InN1cGVyIGFkbWluIiwiZm90byI6IlwvMzcuanBnIiwibnVtYmVyIjozLCJleHAiOjMzOTc0NjM3NTZ9.Ob4tfUv2q2VNxwcozlGYZ_o79rNz9vJ2wDL9vdYPpqw', 'website', 1, '2023-10-31 05:27:58', '2023-10-31 05:27:58', 37),
(143, 'SuperAdmin@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjozNywibmFtYV9sZW5na2FwIjoic3VwZXIgYWRtaW4iLCJub190ZWxwb24iOiIwODgxMTIyMjMzIiwiamVuaXNfa2VsYW1pbiI6Imxha2ktbGFraSIsInRhbmdnYWxfbGFoaXIiOiIyMDIzLTEwLTA3IiwidGVtcGF0X2xhaGlyIjoicGxhbmV0IGp1cGl0ZXIiLCJlbWFpbCI6IlN1cGVyQWRtaW5AZ21haWwuY29tIiwicm9sZSI6InN1cGVyIGFkbWluIiwiZm90byI6IlwvMzcuanBnIiwibnVtYmVyIjozLCJleHAiOjE2OTg3Mzk0NTd9.ujTqOpUcnapNWGr5Sq48rwE1ByCW8AT7nl-yfqVjqVM', 'website', 2, '2023-10-31 07:04:17', '2023-10-31 07:04:17', 37),
(144, 'SuperAdmin@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZF91c2VyIjozNywibmFtYV9sZW5na2FwIjoic3VwZXIgYWRtaW4iLCJub190ZWxwb24iOiIwODgxMTIyMjMzIiwiamVuaXNfa2VsYW1pbiI6Imxha2ktbGFraSIsInRhbmdnYWxfbGFoaXIiOiIyMDIzLTEwLTA3IiwidGVtcGF0X2xhaGlyIjoicGxhbmV0IGp1cGl0ZXIiLCJlbWFpbCI6IlN1cGVyQWRtaW5AZ21haWwuY29tIiwicm9sZSI6InN1cGVyIGFkbWluIiwiZm90byI6IlwvMzcuanBnIiwibnVtYmVyIjozLCJleHAiOjE2OTg3NDU2MzF9.pcdEiud5y3sBiAYIkk-k5ZbKTmScAXJIsocE6JYqwk8', 'website', 3, '2023-10-31 08:47:11', '2023-10-31 08:47:11', 37);

-- --------------------------------------------------------

--
-- Table structure for table `seniman`
--

CREATE TABLE `seniman` (
  `id_seniman` int(11) NOT NULL,
  `nik` int(16) NOT NULL,
  `nomor_induk` varchar(20) NOT NULL,
  `nama_seniman` varchar(30) NOT NULL,
  `jenis_kelamin` enum('laki-laki','perempuan') NOT NULL,
  `kategori` enum('campursari','dalang','jaranan','karawitan','mc','ludruk','organisasi kesenian musik','organisasi','pramugrari tayup','sanggar','sinden','vocalis','waranggono') NOT NULL,
  `kecamatan` enum('bagor','baron','berbek','gondang','jatikalen','kertosono','lengkong','loceret','nganjuk','ngetos','ngluyu','ngronggot','pace','patianrowo','prambon','rejoso','sawahan','sukomoro','tanjunganom','wilangan') NOT NULL,
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
-- Dumping data for table `seniman`
--

INSERT INTO `seniman` (`id_seniman`, `nik`, `nomor_induk`, `nama_seniman`, `jenis_kelamin`, `kategori`, `kecamatan`, `tempat_lahir`, `tanggal_lahir`, `alamat_seniman`, `no_telpon`, `nama_organisasi`, `jumlah_anggota`, `ktp_seniman`, `pass_foto`, `surat_keterangan`, `tgl_pembuatan`, `tgl_berlaku`, `status`, `catatan`, `id_user`) VALUES
(1, 990155150, '4607', 'joko', 'laki-laki', '', 'nganjuk', 'planet mars', '2023-10-19', 'tersraahhvhh', '089999', 'nnivnanvnvonao', '1001', '/1.jpeg', '/1.png', '/1.pdf', '2023-10-22', '2023-10-22', 'diterima', '', 32),
(2, 2147483647, '7869', 'santi', 'perempuan', '', 'nganjuk', 'nganjuk', '2023-10-27', 'isvnnvisivnsv', '088861616', 'random', '121', '/2.png', '/2.jpeg', '/2.pdf', '2023-10-22', '2023-10-22', 'diterima', '', 32),
(3, 2147483647, '3167', 'ranti', 'perempuan', '', 'nganjuk', 'nganjuk', '2017-04-27', 'isvnnvisivnsv', '088861616', 'random', '121', '/3.png', '/3.jpeg', '/3.pdf', '2023-10-22', '2023-10-22', 'diterima', NULL, 32),
(5, 2147483647, '9664', 'yanti', 'perempuan', '', 'nganjuk', 'planet mars', '2023-10-21', 'planet matahari', '089999', 'terserah lah', '121', '/5.jpg', '/5.jpg', '/5.pdf', '2023-10-24', '2023-10-24', 'proses', NULL, 32),
(6, 2147483647, '5559', 'asep', 'laki-laki', '', 'nganjuk', 'planet mars', '2016-03-21', 'planet matahari', '089999', 'terserah lah', '121', '/6.jpg', '/6.jpg', '/6.pdf', '2023-10-24', '2023-10-24', 'diajukan', NULL, 32),
(7, 2147483647, '5170', 'rana', 'perempuan', '', 'nganjuk', 'planet mars', '2016-05-05', 'planet matahari', '089999', 'terserah lah', '121', '/7.jpg', '/7.jpg', '/7.pdf', '2023-10-24', '2023-10-24', 'proses', NULL, 32),
(8, 990155150, '4265', 'eka', 'perempuan', '', 'nganjuk', 'planet mars', '2023-10-24', 'planet jupiter ', '0888414144', 'gabut', '121', '/8.jpg', '/8.jpg', '/8.pdf', '2023-10-29', '2023-10-29', 'diajukan', NULL, 32),
(9, 990155150, '1936', 'eka', 'perempuan', '', 'nganjuk', 'planet mars', '2023-10-24', 'planet jupiter ', '0888414144', 'gabut', '121', '/9.jpg', '/9.jpg', '/9.pdf', '2023-10-29', '2023-10-29', 'proses', NULL, 32),
(10, 990155150, '4449', 'eka', 'perempuan', '', 'nganjuk', 'planet mars', '2023-10-24', 'planet jupiter ', '0888414144', 'gabut', '121', '/10.jpg', '/10.jpg', '/10.pdf', '2023-10-29', '2023-10-29', 'ditolak', 'terserah', 32),
(21, 1, '', '2', '', 'karawitan', 'jatikalen', '6', '0000-00-00', '8', '9', '11', '12', NULL, NULL, NULL, '0000-00-00', NULL, 'diajukan', NULL, 32);

-- --------------------------------------------------------

--
-- Table structure for table `sewa_tempat`
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
-- Dumping data for table `sewa_tempat`
--

INSERT INTO `sewa_tempat` (`id_sewa`, `nik_sewa`, `nama_peminjam`, `nama_tempat`, `deskripsi_sewa_tempat`, `nama_kegiatan_sewa`, `jumlah_peserta`, `instansi`, `surat_ket_sewa`, `tgl_awal_peminjaman`, `tgl_akhir_peminjaman`, `status`, `catatan`, `id_tempat`, `id_user`) VALUES
(10, 2147483647, 'juniorr', 'china', 'ayo turu', 'turu massal', 1224, 'karate', '/2023/10/10.pdf', '2023-10-29 11:28:00', '2023-10-29 11:30:00', 'diterima', '', 2, 32),
(11, 2147483647, 'juniorr', 'jerman barat', 'ayo turu', 'turu massal', 1224, 'karate', '2023/10/11.pdf', '2023-10-29 11:28:00', '2023-10-29 11:30:00', 'ditolak', 'erorr lah ', 2, 32),
(12, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/2023/11/12.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'diterima', NULL, 2, 32),
(13, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/2023/11/13.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'proses', NULL, 2, 32),
(14, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/2023/11/14.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'diajukan', NULL, 2, 32),
(15, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/2023/11/15.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'proses', NULL, 2, 32),
(16, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/2023/11/16.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'diterima', NULL, 2, 32),
(17, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/2023/11/17.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'ditolak', 'terserah', 2, 32),
(18, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/2023/11/18.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'ditolak', 'terserah', 2, 32),
(19, 2147483647, 'indro', 'jerman amerika', 'random people', 'nyileh panggonan', 1224, 'organisasi', '/2023/11/19.pdf', '2023-11-04 15:44:00', '2023-11-09 15:44:00', 'diterima', NULL, 2, 32);

-- --------------------------------------------------------

--
-- Table structure for table `surat_advis`
--

CREATE TABLE `surat_advis` (
  `id_advis` int(5) NOT NULL,
  `nomor_induk` varchar(20) NOT NULL,
  `nama_advis` varchar(30) NOT NULL,
  `alamat_advis` varchar(50) NOT NULL,
  `deskripsi_advis` varchar(100) DEFAULT NULL,
  `tgl_advis` date NOT NULL,
  `tempat_advis` varchar(30) NOT NULL,
  `status` enum('diajukan','proses','diterima','ditolak') DEFAULT NULL,
  `catatan` text DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  `id_seniman` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `surat_advis`
--

INSERT INTO `surat_advis` (`id_advis`, `nomor_induk`, `nama_advis`, `alamat_advis`, `deskripsi_advis`, `tgl_advis`, `tempat_advis`, `status`, `catatan`, `id_user`, `id_seniman`) VALUES
(3, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'diterima', '', 32, 1),
(4, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'ditolak', 'terserah', 32, 1),
(5, '4607', 'johnn michael robbin', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'diterima', NULL, 32, 1),
(6, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'diterima', NULL, 32, 1),
(7, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'proses', NULL, 32, 1),
(8, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'proses', NULL, 32, 1),
(9, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'ditolak', 'erorr lah ', 32, 1),
(10, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'ditolak', 'erorr lah ', 32, 1),
(11, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'diterima', '', 32, 1),
(12, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'diterima', '', 32, 1),
(13, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'diajukan', NULL, 32, 1),
(14, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'proses', NULL, 32, 1),
(15, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'proses', NULL, 32, 1),
(16, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'ditolak', 'terserah', 32, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
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
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `nama_lengkap`, `no_telpon`, `jenis_kelamin`, `tanggal_lahir`, `tempat_lahir`, `role`, `email`, `password`, `foto`, `verifikasi`) VALUES
(1, 'akun pemulihan', '089999999999', 'laki-laki', '2023-10-31', 'Nganjuk', 'masyarakat', 'admin@gmail.com', '1', NULL, 0),
(32, 'amirzan fikri', '088884144', 'laki-laki', '2000-12-12', 'indonesia', 'masyarakat', 'amirzanfikri5@gmail.com', '$2y$10$q6qLZmxecCFOQJ0nKYKjOuyZMwyv5xj0CrY0A.ehKTeTMwW2ERo2m', NULL, 1),
(33, 'event admin', '088112223392590', 'perempuan', '2023-10-06', 'planet matahari', 'admin tempat', 'AdminEvent1@gmail.com', '$2y$10$WjZyxPJNkjsC56Sg4844mObDz/j/CI05JEFu1tkqwoI4DoNdUJO1C', NULL, 1),
(34, 'admin pentas', '08626268661886', 'perempuan', '2023-10-07', 'jerman', 'admin seniman', 'AdminPentas@gmail.com', '$2y$10$Kto5k8p0BsjvY.EfXhRoQedVqwoq1UmhDF.GQ.GJkewv5nAgBjFyi', NULL, 1),
(35, 'admin tempat', '089095295929505', 'laki-laki', '2023-10-06', 'planet jupiter', 'admin tempat', 'AdminTempat@gmail.com', '$2y$10$de7R3BL2pZRX9QX/TFufF.4BOtqkJOpML2PShgYQRYpDVC2cE2.du', NULL, 1),
(36, 'admin seniman', '08881515157751', 'laki-laki', '2023-10-07', 'akhirat', 'admin seniman', 'AdminSeniman@gmail.com', '$2y$10$bamAD6WkuqxHBFn7U.GQFupabPM03xXRZFEXpPqITn49QuJU7fhCC', '/36.jpeg', 1),
(37, 'super admin', '0881122233', 'laki-laki', '2023-10-07', 'planet jupiter', 'super admin', 'SuperAdmin@gmail.com', '$2y$10$M1fEjUm7I3i7z8bMOSzYm.9WzkGl9rHV8Av5soEhKgXbkkvt8VbO2', '/37.jpg', 1),
(38, 'admin', '0888151515', 'perempuan', '2023-10-13', 'akhirat', 'super admin', 'Admin@gmail.com', '$2y$10$tz2Qd71cf4ZKyk02vz4Ye.smoiw/N1/38KlzpgFNJwWK9VEP9rv6a', NULL, 1),
(41, 'joko', '0881122233', 'laki-laki', '2023-10-13', 'akhirat', 'admin tempat', 'random@gmail.com', '$2y$10$0RlQ7RJ9U1nN7E1YuUkhnOaTYbcANSNIEYzUumq.xCz0g1CjrgKLm', '/41.jpg', 1),
(44, 'xdddd', '085555555525', 'laki-laki', '0000-00-00', '', 'masyarakat', 'fadillahwahyunugraha@gmail.com', '$2y$10$ed.IZYTE4sI.iDqDBOJY..MVjOmGhLy9Vgh.ZAxF4ri4iSPiX/ljy', NULL, 0),
(45, 'eeeeeeee', '0895413793451', 'laki-laki', '0000-00-00', '', 'masyarakat', 'diamanerdi@gmail.com', '$2y$10$UMxSc3lBujrHlzRkxH8DhO.FokBVu6BwIbAPzKMR6dSfo3mdtL8ki', NULL, 0),
(46, 'fadil', '08222533433453', 'laki-laki', '0000-00-00', '', 'masyarakat', 'akunceer6enam@gmail.com', '$2y$10$neAnr5TRp3KbvXepTmiwu.ybtOFIvIMAZ3P6YNahP8E5iP2f2ifbW', NULL, 0),
(47, 'muhammaderdi', '0832897329793', 'laki-laki', '0000-00-00', '', 'masyarakat', 'muhammaderdi1999@gmail.com', '$2y$10$1jAe8iuYCRbRwkvfzq5DSu5E0I4PhisYWB.0r0UErduRWhesPUpRS', NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `verifikasi`
--

CREATE TABLE `verifikasi` (
  `id_verifikasi` int(10) NOT NULL,
  `email` varchar(45) NOT NULL,
  `kode_otp` int(6) NOT NULL,
  `link` varchar(50) NOT NULL,
  `deskripsi` enum('password','email') NOT NULL,
  `send` int(1) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `verifikasi`
--

INSERT INTO `verifikasi` (`id_verifikasi`, `email`, `kode_otp`, `link`, `deskripsi`, `send`, `created_at`, `updated_at`, `id_user`) VALUES
(1, 'amirzanfikri5@gmail.com', 681257, 'beb527e9e2e0358831eb8189c6568ea94d805dc435a69c19b8', 'password', 0, '2023-10-30 03:06:28', '2023-10-30 03:06:28', 32),
(2, 'marshellazalia004@gmail.com', 79112, '', '', 0, '2023-11-01 02:30:22', '0000-00-00 00:00:00', 1),
(3, 'didannio70@gmail.com', 42677, '', '', 0, '2023-11-01 02:31:07', '0000-00-00 00:00:00', 1),
(4, 'marshellazalia004@gmail.com', 95754, '', '', 0, '2023-11-01 02:32:03', '0000-00-00 00:00:00', 1),
(5, 'fadillahwahyunugraha@gmail.com', 215681, '', '', 0, '2023-11-01 02:39:19', '0000-00-00 00:00:00', 1),
(6, 'fadillahwahyunugraha@gmail.com', 240150, '', '', 0, '2023-11-01 02:47:46', '0000-00-00 00:00:00', 1),
(7, 'fadillahwahyunugraha@gmail.com', 309098, '', '', 0, '2023-11-01 02:51:25', '0000-00-00 00:00:00', 1),
(8, 'fadillahwahyunugraha@gmail.com', 725469, '', '', 0, '2023-11-01 02:55:57', '0000-00-00 00:00:00', 1),
(9, 'fadillahwahyunugraha@gmail.com', 215171, '', '', 0, '2023-11-01 02:57:57', '0000-00-00 00:00:00', 1),
(10, 'marshellazalia004@gmail.com', 664660, '', '', 0, '2023-11-01 03:07:03', '2023-11-01 03:07:03', 1),
(11, 'diamanerdi@gmail.com', 929728, '', '', 0, '2023-11-01 03:26:24', '0000-00-00 00:00:00', 1),
(12, 'fadillahwahyunugraha@gmail.com', 521643, '', '', 0, '2023-11-01 05:05:34', '0000-00-00 00:00:00', 1),
(13, 'fadillahwahyunugraha@gmail.com', 113924, '', '', 0, '2023-11-01 05:05:54', '0000-00-00 00:00:00', 1),
(14, 'akunceer6enam@gmail.com', 415928, '', '', 0, '2023-11-01 05:07:45', '0000-00-00 00:00:00', 1),
(15, 'muhammaderdi1999@gmail.com', 495462, '', '', 0, '2023-11-01 15:43:05', '0000-00-00 00:00:00', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_events`
--
ALTER TABLE `detail_events`
  ADD PRIMARY KEY (`id_detail`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id_event`),
  ADD KEY `eventFK` (`id_user`),
  ADD KEY `sewaFK` (`id_sewa`);

--
-- Indexes for table `kategori_seniman`
--
ALTER TABLE `kategori_seniman`
  ADD PRIMARY KEY (`id_kategori_seniman`);

--
-- Indexes for table `list_tempat`
--
ALTER TABLE `list_tempat`
  ADD PRIMARY KEY (`id_tempat`);

--
-- Indexes for table `perpanjangan`
--
ALTER TABLE `perpanjangan`
  ADD PRIMARY KEY (`id_perpanjangan`),
  ADD KEY `senimanPFK` (`id_seniman`);

--
-- Indexes for table `refresh_token`
--
ALTER TABLE `refresh_token`
  ADD PRIMARY KEY (`id_token`),
  ADD KEY `tokenFK` (`id_user`);

--
-- Indexes for table `seniman`
--
ALTER TABLE `seniman`
  ADD PRIMARY KEY (`id_seniman`),
  ADD KEY `senimanFK` (`id_user`);

--
-- Indexes for table `sewa_tempat`
--
ALTER TABLE `sewa_tempat`
  ADD PRIMARY KEY (`id_sewa`);

--
-- Indexes for table `surat_advis`
--
ALTER TABLE `surat_advis`
  ADD PRIMARY KEY (`id_advis`),
  ADD KEY `advisFK` (`id_user`),
  ADD KEY `id_seniman` (`id_seniman`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `verifikasi`
--
ALTER TABLE `verifikasi`
  ADD PRIMARY KEY (`id_verifikasi`),
  ADD KEY `verifyfk` (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_events`
--
ALTER TABLE `detail_events`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `id_event` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `kategori_seniman`
--
ALTER TABLE `kategori_seniman`
  MODIFY `id_kategori_seniman` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `list_tempat`
--
ALTER TABLE `list_tempat`
  MODIFY `id_tempat` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `perpanjangan`
--
ALTER TABLE `perpanjangan`
  MODIFY `id_perpanjangan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `refresh_token`
--
ALTER TABLE `refresh_token`
  MODIFY `id_token` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;

--
-- AUTO_INCREMENT for table `seniman`
--
ALTER TABLE `seniman`
  MODIFY `id_seniman` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `sewa_tempat`
--
ALTER TABLE `sewa_tempat`
  MODIFY `id_sewa` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `surat_advis`
--
ALTER TABLE `surat_advis`
  MODIFY `id_advis` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `verifikasi`
--
ALTER TABLE `verifikasi`
  MODIFY `id_verifikasi` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `eventFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `sewaFK` FOREIGN KEY (`id_sewa`) REFERENCES `sewa_tempat` (`id_sewa`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `perpanjangan`
--
ALTER TABLE `perpanjangan`
  ADD CONSTRAINT `senimanPFK` FOREIGN KEY (`id_seniman`) REFERENCES `seniman` (`id_seniman`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `refresh_token`
--
ALTER TABLE `refresh_token`
  ADD CONSTRAINT `tokenFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `seniman`
--
ALTER TABLE `seniman`
  ADD CONSTRAINT `senimanFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `surat_advis`
--
ALTER TABLE `surat_advis`
  ADD CONSTRAINT `advisFK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `senimanFK12` FOREIGN KEY (`id_seniman`) REFERENCES `seniman` (`id_seniman`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `verifikasi`
--
ALTER TABLE `verifikasi`
  ADD CONSTRAINT `verifyfk` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
