-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 31, 2024 at 01:57 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

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
-- Table structure for table `benhnhan`
--

CREATE TABLE `benhnhan` (
  `MaBN` int(50) NOT NULL,
  `Hoten` varchar(50) DEFAULT NULL,
  `Ngaysinh` date DEFAULT NULL,
  `Gioitinh` varchar(10) DEFAULT NULL,
  `SDT` varchar(20) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Diachi` varchar(50) DEFAULT NULL,
  `Tiensubenh` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `benhnhan`
--

INSERT INTO `benhnhan` (`MaBN`, `Hoten`, `Ngaysinh`, `Gioitinh`, `SDT`, `Email`, `Diachi`, `Tiensubenh`) VALUES
(1, 'Nguyen Van A', '1990-01-15', 'Nam', '0905123456', 'nguyenvana@gmail.com', '123 Le Loi, Quan 1, HCM', 'Tieu duong'),
(2, 'Le Thi B', '1985-05-20', 'Nu', '0912345678', 'lethib@gmail.com', '456 Hai Ba Trung, Quan 3, HCM', 'Cao huyet ap'),
(3, 'Tran Van C', '1978-11-30', 'Nam', '0987654321', 'tranvanc@gmail.com', '789 Tran Hung Dao, Quan 5, HCM', 'Tim mach'),
(4, 'Pham Thi D', '1995-07-10', 'Nu', '0938765432', 'phamthid@gmail.com', '101 Nguyen Trai, Quan 2, HCM', 'Hen suyen'),
(5, 'Vo Van E', '1988-03-22', 'Nam', '0945123789', 'vovane@gmail.com', '202 Bach Dang, Quan Binh Thanh, HCM', 'Tieu duong'),
(8, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `chitietlichkham`
--

CREATE TABLE `chitietlichkham` (
  `MaHS` int(11) NOT NULL,
  `MaLK` int(11) NOT NULL,
  `Chuandoan` text DEFAULT NULL,
  `Donthuoc` text DEFAULT NULL,
  `Ghichuthem` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietlichkham`
--

INSERT INTO `chitietlichkham` (`MaHS`, `MaLK`, `Chuandoan`, `Donthuoc`, `Ghichuthem`) VALUES
(201, 101, 'Viêm họng', 'Paracetamol 500mg, Amoxicillin 500mg', 'Uống nhiều nước, nghỉ ngơi'),
(202, 102, 'Đau dạ dày', 'Omeprazole 20mg, Metronidazole 500mg', 'Tránh ăn đồ cay nóng'),
(203, 103, 'Cảm cúm', 'Vitamin C, Ibuprofen 200mg', 'Nghỉ ngơi, uống nước ấm');

-- --------------------------------------------------------

--
-- Table structure for table `lichkham`
--

CREATE TABLE `lichkham` (
  `MaLK` int(50) NOT NULL,
  `MaBN` int(50) DEFAULT NULL,
  `Ngaygiodatkham` datetime NOT NULL,
  `Trangthai` varchar(20) NOT NULL,
  `Hoten` varchar(50) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `SDT` varchar(11) DEFAULT NULL,
  `Note` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lichkham`
--

INSERT INTO `lichkham` (`MaLK`, `MaBN`, `Ngaygiodatkham`, `Trangthai`, `Hoten`, `Email`, `SDT`, `Note`) VALUES
(101, 1, '2024-10-20 09:00:00', 'Đã Khám', NULL, NULL, NULL, NULL),
(102, 2, '2024-10-21 14:30:00', 'Chưa Khám', NULL, NULL, NULL, NULL),
(103, 1, '2024-10-22 11:15:00', 'Đã Khám', NULL, NULL, NULL, NULL),
(104, 1, '2024-10-31 08:00:00', 'Pending', 'Nguyen Van A', 'nguyenvana@example.com', '0123456789', 'First consultation'),
(105, 2, '2024-11-01 09:30:00', 'Confirmed', 'Le Thi B', 'lethib@example.com', '0987654321', 'Follow-up visit'),
(106, 3, '2024-11-02 14:00:00', 'Pending', 'Tran Van C', 'tranvanc@example.com', '0912345678', 'Routine check-up'),
(107, 4, '2024-11-03 10:00:00', 'Confirmed', 'Pham Thi D', 'phamthid@example.com', '0901234567', 'Monthly health check'),
(108, 5, '2024-11-04 16:30:00', 'Cancelled', 'Hoang Van E', 'hoangvane@example.com', '0981234567', 'Consultation rescheduled');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaTK` int(50) NOT NULL,
  `TenDN` varchar(50) NOT NULL,
  `Matkhau` varchar(150) NOT NULL,
  `MaBN` int(50) DEFAULT NULL,
  `Vaitro` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`MaTK`, `TenDN`, `Matkhau`, `MaBN`, `Vaitro`) VALUES
(1, 'Bacsi', '$2a$10$J40ipm.8yk21aUqWBiphzO68dP8Rm40NAgKo9rVa6P71Kr57W5KkO', NULL, 'bacsi'),
(2, 'nam', '$2a$10$NhmJEDaPls4Xb1Rc.UlhAORqSLw4UjgRon6LbnpyI8jLLngE/rdMO', 8, 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `benhnhan`
--
ALTER TABLE `benhnhan`
  ADD PRIMARY KEY (`MaBN`);

--
-- Indexes for table `chitietlichkham`
--
ALTER TABLE `chitietlichkham`
  ADD PRIMARY KEY (`MaHS`),
  ADD KEY `MaLK` (`MaLK`);

--
-- Indexes for table `lichkham`
--
ALTER TABLE `lichkham`
  ADD PRIMARY KEY (`MaLK`),
  ADD KEY `MaBN` (`MaBN`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaTK`),
  ADD KEY `MaBN` (`MaBN`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `benhnhan`
--
ALTER TABLE `benhnhan`
  MODIFY `MaBN` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `lichkham`
--
ALTER TABLE `lichkham`
  MODIFY `MaLK` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- AUTO_INCREMENT for table `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `MaTK` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitietlichkham`
--
ALTER TABLE `chitietlichkham`
  ADD CONSTRAINT `chitietlichkham_ibfk_1` FOREIGN KEY (`MaLK`) REFERENCES `lichkham` (`MaLK`);

--
-- Constraints for table `lichkham`
--
ALTER TABLE `lichkham`
  ADD CONSTRAINT `lichkham_ibfk_1` FOREIGN KEY (`MaBN`) REFERENCES `benhnhan` (`MaBN`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `MaBN` FOREIGN KEY (`MaBN`) REFERENCES `benhnhan` (`MaBN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
