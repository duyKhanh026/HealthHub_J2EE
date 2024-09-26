-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 26, 2024 at 02:46 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `phongkham`
--

-- --------------------------------------------------------

--
-- Table structure for table `bacsi`
--

CREATE TABLE `bacsi` (
  `MaBS` int(50) NOT NULL,
  `Hoten` varchar(50) NOT NULL,
  `Chuyenkhoa` varchar(50) NOT NULL,
  `SDT` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Phongkham` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `benhnhan`
--

CREATE TABLE `benhnhan` (
  `MaBN` int(50) NOT NULL,
  `Hoten` varchar(50) NOT NULL,
  `Ngaysinh` date NOT NULL,
  `Gioitinh` varchar(10) NOT NULL,
  `SDT` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Diachi` varchar(50) NOT NULL,
  `Tiensubenh` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hosobenhan`
--

CREATE TABLE `hosobenhan` (
  `MaHS` int(11) NOT NULL,
  `MaLK` int(11) NOT NULL,
  `Chuandoan` text NOT NULL,
  `Donthuoc` text NOT NULL,
  `Ghichuthem` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lichkham`
--

CREATE TABLE `lichkham` (
  `MaLK` int(50) NOT NULL,
  `MaBN` int(50) NOT NULL,
  `MaBS` int(50) NOT NULL,
  `Ngaygiodatkham` datetime NOT NULL,
  `Trangthai` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lichlamviecbs`
--

CREATE TABLE `lichlamviecbs` (
  `MaLLV` int(11) NOT NULL,
  `MaBS` int(11) NOT NULL,
  `GioBDLV` time NOT NULL,
  `GioKTLV` time NOT NULL,
  `NgayLV` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaTK` int(50) NOT NULL,
  `TenDN` varchar(50) NOT NULL,
  `Matkhau` varchar(50) NOT NULL,
  `Vaitro` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bacsi`
--
ALTER TABLE `bacsi`
  ADD PRIMARY KEY (`MaBS`);

--
-- Indexes for table `benhnhan`
--
ALTER TABLE `benhnhan`
  ADD PRIMARY KEY (`MaBN`);

--
-- Indexes for table `hosobenhan`
--
ALTER TABLE `hosobenhan`
  ADD PRIMARY KEY (`MaHS`),
  ADD KEY `MaLK` (`MaLK`);

--
-- Indexes for table `lichkham`
--
ALTER TABLE `lichkham`
  ADD PRIMARY KEY (`MaLK`),
  ADD KEY `MaBN` (`MaBN`),
  ADD KEY `MaBS` (`MaBS`);

--
-- Indexes for table `lichlamviecbs`
--
ALTER TABLE `lichlamviecbs`
  ADD PRIMARY KEY (`MaLLV`),
  ADD KEY `MaBS` (`MaBS`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaTK`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hosobenhan`
--
ALTER TABLE `hosobenhan`
  ADD CONSTRAINT `hosobenhan_ibfk_1` FOREIGN KEY (`MaLK`) REFERENCES `lichkham` (`MaLK`);

--
-- Constraints for table `lichkham`
--
ALTER TABLE `lichkham`
  ADD CONSTRAINT `lichkham_ibfk_1` FOREIGN KEY (`MaBN`) REFERENCES `benhnhan` (`MaBN`),
  ADD CONSTRAINT `lichkham_ibfk_2` FOREIGN KEY (`MaBS`) REFERENCES `bacsi` (`MaBS`);

--
-- Constraints for table `lichlamviecbs`
--
ALTER TABLE `lichlamviecbs`
  ADD CONSTRAINT `lichlamviecbs_ibfk_1` FOREIGN KEY (`MaBS`) REFERENCES `bacsi` (`MaBS`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
