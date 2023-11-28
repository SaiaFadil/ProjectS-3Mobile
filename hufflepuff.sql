-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 28, 2023 at 12:17 PM
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
  `tempat_event` varchar(2000) DEFAULT NULL,
  `tanggal_awal` date NOT NULL,
  `tanggal_akhir` date NOT NULL,
  `link_pendaftaran` varchar(2000) DEFAULT NULL,
  `poster_event` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_events`
--

INSERT INTO `detail_events` (`id_detail`, `nama_event`, `deskripsi`, `tempat_event`, `tanggal_awal`, `tanggal_akhir`, `link_pendaftaran`, `poster_event`) VALUES
(2, 'turu lah', 'inainvinivn', 'nganjuk', '2023-11-15', '2023-11-15', 'google.com', '\\2023\\11\\2.jpg'),
(3, 'turu lah', 'inainvinivn', 'planet luar bumi', '2023-11-15', '2023-11-15', 'google.com', '/2023/11/3.jpg'),
(4, 'sunmori', 'ayo sunmori di planet matahari', 'planet matahari', '2023-11-15', '2023-11-15', 'google.com', '/2023/11/4.jpg'),
(5, 'mlaku mlaku', 'inainvinivn', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/5.jpg'),
(6, 'turu lah', 'inainvinivn', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/6.jpg'),
(7, 'agustusan', 'inainvinivn', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/7.jpg'),
(8, 'turu lah', 'inainvinivn', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/8.jpg'),
(9, 'turu lah', 'inainvinivn', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/9.jpg'),
(10, 'turu lah', 'inainvinivn', NULL, '2023-11-15', '2023-11-15', 'google.com', '/2023/11/10.jpg');

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
  `nama_kategori` varchar(20) NOT NULL,
  `singkatan_kategori` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori_seniman`
--

INSERT INTO `kategori_seniman` (`id_kategori_seniman`, `nama_kategori`, `singkatan_kategori`) VALUES
(1, 'Dalang', 'DLG'),
(2, 'REOG', 'REOG'),
(3, 'Lord', 'LRD');

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
  `ktp_seniman` varchar(100) NOT NULL,
  `pass_foto` varchar(100) NOT NULL,
  `surat_keterangan` varchar(100) NOT NULL,
  `status` enum('diajukan','proses','diterima','ditolak') DEFAULT NULL,
  `catatan` text DEFAULT NULL,
  `id_seniman` int(11) NOT NULL,
  `tgl_pembuatan` date NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `perpanjangan`
--

INSERT INTO `perpanjangan` (`id_perpanjangan`, `ktp_seniman`, `pass_foto`, `surat_keterangan`, `status`, `catatan`, `id_seniman`, `tgl_pembuatan`, `id_user`) VALUES
(4, 'uploads/perpanjangan/ktp_seniman/wkwkwkw(1).png', 'uploads/perpanjangan/pass_foto/wkwkwkw.png', 'uploads/perpanjangan/surat_keterangan/GTW(1).pdf', 'diajukan', '', 1, '2023-11-28', 2),
(5, 'uploads/perpanjangan/ktp_seniman/wkwkwkw.png', 'uploads/perpanjangan/pass_foto/lord.png', 'uploads/perpanjangan/surat_keterangan/GTW.pdf', 'diajukan', '', 1, '2023-11-28', 2);

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
  `nik` varchar(120) NOT NULL,
  `nomor_induk` varchar(50) NOT NULL,
  `nama_seniman` varchar(30) NOT NULL,
  `jenis_kelamin` enum('laki-laki','perempuan') NOT NULL,
  `kecamatan` enum('bagor','baron','berbek','gondang','jatikalen','kertosono','lengkong','loceret','nganjuk','ngetos','ngluyu','ngronggot','pace','patianrowo','prambon','rejoso','sawahan','sukomoro','tanjunganom','wilangan') NOT NULL,
  `singkatan_kategori` varchar(12) NOT NULL,
  `tempat_lahir` varchar(30) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `alamat_seniman` varchar(50) NOT NULL,
  `no_telpon` varchar(15) DEFAULT NULL,
  `nama_organisasi` varchar(50) DEFAULT NULL,
  `jumlah_anggota` varchar(10) DEFAULT NULL,
  `ktp_seniman` varchar(100) DEFAULT NULL,
  `pass_foto` varchar(100) DEFAULT NULL,
  `surat_keterangan` varchar(100) DEFAULT NULL,
  `tgl_pembuatan` date NOT NULL,
  `tgl_berlaku` date DEFAULT NULL,
  `status` enum('diajukan','proses','diterima','ditolak') NOT NULL,
  `catatan` text DEFAULT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seniman`
--

INSERT INTO `seniman` (`id_seniman`, `nik`, `nomor_induk`, `nama_seniman`, `jenis_kelamin`, `kecamatan`, `singkatan_kategori`, `tempat_lahir`, `tanggal_lahir`, `alamat_seniman`, `no_telpon`, `nama_organisasi`, `jumlah_anggota`, `ktp_seniman`, `pass_foto`, `surat_keterangan`, `tgl_pembuatan`, `tgl_berlaku`, `status`, `catatan`, `id_user`) VALUES
(1, 'MzY1ODczODI3ODM3MjgxNw==', 'DLG/004/1442.332/202', 'ERDI SEPTA WAHYU PRATAMA', 'perempuan', 'prambon', 'DLG', 'Kediri', '2003-09-20', 'Ds.Singkalanyar, Kec.Prambon, Kab.Nganjuk', '0895413793451', '-', '1', 'uploads/seniman/ktp_seniman/wkwkwkw.png', 'uploads/seniman/pass_foto/lord.png', 'uploads/seniman/surat_keterangan/GTW.pdf', '2023-11-26', '2024-12-31', 'diajukan', '', 2),
(4, 'MzY1MzI3NjU0MjM1Njc0NQ==', '', 'ERDI SEPTA WAHYU PRATAMA', 'laki-laki', 'nganjuk', 'REOG', 'NGANJUK', '2023-11-10', 'NGANJUK', '08324732874324', 'Sumail', '12', 'uploads/seniman/ktp_seniman/wkwkwkw(1).png', 'uploads/seniman/pass_foto/lord(1).png', 'uploads/seniman/surat_keterangan/GTW(2).pdf', '2023-11-28', '2024-12-31', 'diajukan', '', 2),
(5, 'MzUxODEzNDgxMTAzMDAwMQ==', '', 'Laila Wulandari', 'perempuan', 'nganjuk', 'REOG', 'Nganjuk', '2003-11-08', 'Jl. Barito 3', '085815612568', 'Wahyu Negoro', '6', 'uploads/seniman/ktp_seniman/Screenshot_20231128_170422.jpg', 'uploads/seniman/pass_foto/Screenshot_20231128_172128.jpg', 'uploads/seniman/surat_keterangan/surat orisinalitas.pdf', '2023-11-28', '2024-12-31', 'diajukan', '', 3);

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
  `id_seniman` int(11) NOT NULL,
  `kode_surat` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `surat_advis`
--

INSERT INTO `surat_advis` (`id_advis`, `nomor_induk`, `nama_advis`, `alamat_advis`, `deskripsi_advis`, `tgl_advis`, `tempat_advis`, `status`, `catatan`, `id_user`, `id_seniman`, `kode_surat`) VALUES
(3, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'diterima', '', 32, 1, NULL),
(4, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'ditolak', 'terserah', 32, 1, NULL),
(5, '4607', 'johnn michael robbin', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'diterima', NULL, 32, 1, NULL),
(6, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'diterima', NULL, 32, 1, NULL),
(7, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'proses', NULL, 32, 1, NULL),
(8, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'proses', NULL, 32, 1, NULL),
(9, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'ditolak', 'erorr lah ', 32, 1, NULL),
(10, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'ditolak', 'erorr lah ', 32, 1, NULL),
(11, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'diterima', '', 32, 1, NULL),
(12, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'diterima', '', 32, 1, NULL),
(13, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'diajukan', NULL, 32, 1, NULL),
(14, '4607', 'asep joko michael ', 'nganjuk indonesia', 'wayang nggolek  ', '2023-11-15', 'planet mars indonesia', 'proses', NULL, 32, 1, NULL),
(83750, 'KRW/007/411.302/2023', 'Fadillah Wahyu Nugraha', 'Ploso,nganjuk', 'gatau', '2023-11-30', 'stadion', 'diterima', '', 22225, 78, '738580046'),
(83751, 'KRW/007/411.302/2023', 'Fadillah Wahyu Nugraha', 'Ploso,nganjuk', 'Pentas Seni Tari Tradisional', '2023-11-22', 'Balai Budaya Nganjuk', 'diajukan', '', 22225, 78, NULL),
(83752, 'KRW/007/411.302/2023', 'Fadillah Wahyu Nugraha', 'Ploso,nganjuk', 'drt', '2023-11-30', 'trh', 'ditolak', 'Maaf Anda Bukan Orang Dalam,Perbanyak Hartamu Untuk Membuat ami Berubah Fikiran!', 22225, 78, NULL),
(83753, 'KRW/007/411.302/2023', 'Fadillah Wahyu Nugraha', 'Ploso,nganjuk', 'pentas gatau', '2023-11-30', 'Nganjuk', 'diterima', '', 22225, 78, '81514041197'),
(83754, 'KRW/007/411.302/2023', 'Fadillah Wahyu Nugraha', 'Ploso,nganjuk', 'gatu', '2023-11-29', 'ddd', 'proses', '', 22225, 78, NULL),
(83755, 'KRW/007/411.302/2023', 'Fadillah Wahyu Nugraha', 'Ploso,nganjuk', 'adfg', '2023-11-21', 'bjkl', 'diajukan', '', 22225, 78, NULL);

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
(1, 'admin', '08374343434343', 'laki-laki', '2023-11-09', 'Ngajuk', 'masyarakat', 'diamanerdi@gmail.com', '123', NULL, 0),
(2, 'ERDI SEPTA WAHYU PRATAMA', '0895413793451', 'laki-laki', '2003-09-20', 'Nganjuk', 'masyarakat', 'muhammaderdi1999@gmail.com', '$2y$10$7QOjamVu1mON15Zs.iTt8ujmSWFVDN/BPcCfGvjQL4A3dGhndkxXW', NULL, 0),
(3, 'Laila Wulandari', '085736145858', 'perempuan', '2003-11-08', 'Nganjuk', 'masyarakat', 'emailwulannn0@gmail.com', '$2y$10$UNmnFElhJOUyux5O041g8eq.umPjfvZeBbTSmPEX48Y7KDQSmi5Km', NULL, 0);

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
  `id_user` int(11) NOT NULL,
  `resend` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `verifikasi`
--

INSERT INTO `verifikasi` (`id_verifikasi`, `email`, `kode_otp`, `link`, `deskripsi`, `send`, `created_at`, `updated_at`, `id_user`, `resend`) VALUES
(1, 'amirzanfikri5@gmail.com', 681257, 'beb527e9e2e0358831eb8189c6568ea94d805dc435a69c19b8', 'password', 0, '2023-10-30 03:06:28', '2023-10-30 03:06:28', 32, 0),
(2, 'marshellazalia004@gmail.com', 79112, '', '', 0, '2023-11-01 02:30:22', '0000-00-00 00:00:00', 1, 0),
(3, 'didannio70@gmail.com', 42677, '', '', 0, '2023-11-01 02:31:07', '0000-00-00 00:00:00', 1, 0),
(4, 'marshellazalia004@gmail.com', 95754, '', '', 0, '2023-11-01 02:32:03', '0000-00-00 00:00:00', 1, 0),
(5, 'fadillahwahyunugraha@gmail.com', 215681, '', '', 0, '2023-11-01 02:39:19', '0000-00-00 00:00:00', 1, 0),
(6, 'fadillahwahyunugraha@gmail.com', 240150, '', '', 0, '2023-11-01 02:47:46', '0000-00-00 00:00:00', 1, 0),
(7, 'fadillahwahyunugraha@gmail.com', 309098, '', '', 0, '2023-11-01 02:51:25', '0000-00-00 00:00:00', 1, 0),
(8, 'fadillahwahyunugraha@gmail.com', 725469, '', '', 0, '2023-11-01 02:55:57', '0000-00-00 00:00:00', 1, 0),
(9, 'fadillahwahyunugraha@gmail.com', 215171, '', '', 0, '2023-11-01 02:57:57', '0000-00-00 00:00:00', 1, 0),
(10, 'marshellazalia004@gmail.com', 664660, '', '', 0, '2023-11-01 03:07:03', '2023-11-01 03:07:03', 1, 0),
(11, 'diamanerdi@gmail.com', 929728, '', '', 0, '2023-11-01 03:26:24', '0000-00-00 00:00:00', 1, 0),
(12, 'fadillahwahyunugraha@gmail.com', 521643, '', '', 0, '2023-11-01 05:05:34', '0000-00-00 00:00:00', 1, 0),
(13, 'fadillahwahyunugraha@gmail.com', 113924, '', '', 0, '2023-11-01 05:05:54', '0000-00-00 00:00:00', 1, 0),
(14, 'akunceer6enam@gmail.com', 415928, '', '', 0, '2023-11-01 05:07:45', '0000-00-00 00:00:00', 1, 0),
(15, 'ardigemers9.7@gmail.com', 926842, '', '', 0, '2023-11-01 14:46:02', '0000-00-00 00:00:00', 1, 0),
(19, 'muhammaderdi1999@gmail.com', 353125, '', '', 0, '2023-11-06 04:46:49', '0000-00-00 00:00:00', 1, 0),
(20, 'fadillahwahyunugraha@gmail.com', 696754, '', '', 0, '2023-11-06 09:48:37', '0000-00-00 00:00:00', 1, 0),
(21, 'akunceer2dua@gmail.com', 898595, '', '', 0, '2023-11-09 07:59:49', '0000-00-00 00:00:00', 1, 0),
(22, 'akunceer2dua@gmail.com', 470661, '', '', 0, '2023-11-09 08:00:55', '0000-00-00 00:00:00', 1, 0),
(23, 'akunceer2dua@gmail.com', 951040, '', '', 0, '2023-11-09 08:01:19', '0000-00-00 00:00:00', 1, 0),
(24, 'akunceer5lima@gmail.com', 250300, '', '', 0, '2023-11-09 08:08:23', '0000-00-00 00:00:00', 1, 0),
(25, 'akunceer5lima@gmail.com', 609741, '', '', 0, '2023-11-09 08:15:32', '2023-11-09 08:15:32', 1, 0),
(26, 'ayuhananira@gmail.com', 912065, '', '', 0, '2023-11-15 04:10:56', '2023-11-15 04:10:56', 1, 0),
(27, 'ayuhananira@gmail.com', 450240, '', '', 0, '2023-11-15 04:11:41', '2023-11-15 04:11:41', 1, 0),
(28, 'ayuhananira@gmail.com', 274292, '', '', 0, '2023-11-15 04:55:34', '0000-00-00 00:00:00', 1, 0),
(29, 'didannio70@gmail.com', 340086, '', '', 0, '2023-11-17 02:14:28', '2023-11-17 02:14:28', 1, 0),
(30, 'didannio70@gmail.com', 416025, '', '', 0, '2023-11-17 02:28:46', '0000-00-00 00:00:00', 1, 0),
(31, 'didannio70@gmail.com', 242604, '', '', 0, '2023-11-17 04:05:36', '2023-11-17 04:05:36', 1, 0),
(32, 'dewinova140@gmail.com', 474994, '', '', 0, '2023-11-17 04:20:51', '2023-11-17 04:20:51', 1, 0),
(33, 'dewinova140@gmail.com', 788414, '', '', 0, '2023-11-17 04:21:12', '0000-00-00 00:00:00', 1, 0),
(34, 'dewinova140@gmail.com', 615853, '', '', 0, '2023-11-17 04:26:10', '2023-11-17 04:26:10', 1, 0),
(35, 'e41220943@student.polije.ac.id', 116717, '', '', 0, '2023-11-17 04:28:12', '0000-00-00 00:00:00', 1, 0),
(36, 'muhammaderdi1999@gmail.com', 346137, '', '', 0, '2023-11-26 03:20:47', '2023-11-26 03:20:47', 1, 0),
(37, 'emailwulannn0@gmail.com', 253069, '', '', 0, '2023-11-28 10:02:04', '0000-00-00 00:00:00', 1, 0);

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
  ADD UNIQUE KEY `id_detail` (`id_detail`),
  ADD KEY `eventFK` (`id_user`),
  ADD KEY `sewaFK` (`id_sewa`);

--
-- Indexes for table `kategori_seniman`
--
ALTER TABLE `kategori_seniman`
  ADD PRIMARY KEY (`id_kategori_seniman`),
  ADD UNIQUE KEY `singkatan_kategori` (`singkatan_kategori`),
  ADD UNIQUE KEY `nama_kategori` (`nama_kategori`);

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
  ADD KEY `senimanPFK` (`id_seniman`),
  ADD KEY `id_user` (`id_user`);

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
  ADD KEY `senimanFK` (`id_user`),
  ADD KEY `FK_KATEGORI` (`singkatan_kategori`);

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
  MODIFY `id_kategori_seniman` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `list_tempat`
--
ALTER TABLE `list_tempat`
  MODIFY `id_tempat` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `perpanjangan`
--
ALTER TABLE `perpanjangan`
  MODIFY `id_perpanjangan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `refresh_token`
--
ALTER TABLE `refresh_token`
  MODIFY `id_token` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;

--
-- AUTO_INCREMENT for table `seniman`
--
ALTER TABLE `seniman`
  MODIFY `id_seniman` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `sewa_tempat`
--
ALTER TABLE `sewa_tempat`
  MODIFY `id_sewa` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `surat_advis`
--
ALTER TABLE `surat_advis`
  MODIFY `id_advis` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83757;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `verifikasi`
--
ALTER TABLE `verifikasi`
  MODIFY `id_verifikasi` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `perpanjangan`
--
ALTER TABLE `perpanjangan`
  ADD CONSTRAINT `senimanPFK` FOREIGN KEY (`id_seniman`) REFERENCES `seniman` (`id_seniman`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `seniman`
--
ALTER TABLE `seniman`
  ADD CONSTRAINT `FK_KATEGORI` FOREIGN KEY (`singkatan_kategori`) REFERENCES `kategori_seniman` (`singkatan_kategori`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
