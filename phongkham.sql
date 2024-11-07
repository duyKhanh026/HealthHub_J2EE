-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 07, 2024 at 04:34 AM
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
(1, 'Nguyen Van A', '2090-01-15', 'adf', '0905123456', 'nguyenvana@gmail.com', '123 Le Loi, Quan 1, HCM', '123'),
(2, 'Le Thi B', '1985-05-20', 'Nu', '0912345678', 'lethib@gmail.com', '456 Hai Ba Trung, Quan 3, HCM', 'Cao huyet ap'),
(3, 'Tran Van C', '1978-11-30', 'Nam', '0987654321', 'tranvanc@gmail.com', '789 Tran Hung Dao, Quan 5, HCM', 'Tim mach'),
(4, 'Pham Thi D', '1995-07-10', 'Nu', '0938765432', 'phamthid@gmail.com', '101 Nguyen Trai, Quan 2, HCM', 'Hen suyen'),
(5, 'Vo Van E', '1988-03-22', 'Nam', '0945123789', 'vovane@gmail.com', '202 Bach Dang, Quan Binh Thanh, HCM', 'Tieu duong'),
(8, 'Le Hoai Nam', '2000-11-11', 'Nam', '1234567899', 'namhoaile2003@gmail.com', 'q12', 'trầm cảm'),
(19, 'Lee', '2024-11-14', 'Nam', '0937888555', 'namhoaile203@gmail.com', 'q1', 'Đau đầu ');

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
(203, 103, 'Cảm cúm', 'Vitamin C, Ibuprofen 200mg', 'Nghỉ ngơi, uống nước ấm'),
(204, 104, 'a', 'bv', 'c'),
(205, 105, NULL, NULL, NULL),
(206, 106, 'a', 'bv', 'c'),
(207, 107, NULL, NULL, NULL),
(208, 108, NULL, NULL, NULL),
(211, 125, 'Bị khùng', 'Vaccin 108', 'Nhớ uống thuốc đầy đủ');

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
(101, 1, '2024-10-20 09:00:00', 'Đã khám', NULL, NULL, NULL, NULL),
(102, 2, '2024-10-21 14:30:00', 'Chưa Khám', NULL, NULL, NULL, NULL),
(103, 1, '2024-10-22 11:15:00', 'Đã Khám', NULL, NULL, NULL, NULL),
(104, 1, '2024-10-31 08:00:00', 'Pending', 'Nguyen Van A', 'nguyenvana@example.com', '0123456789', 'First consultation'),
(105, 2, '2024-11-01 09:30:00', 'Confirmed', 'Le Thi B', 'lethib@example.com', '0987654321', 'Follow-up visit'),
(106, 3, '2024-11-02 14:00:00', 'Đã khám', 'Tran Van C', 'tranvanc@example.com', '0912345678', 'Routine check-up'),
(107, 4, '2024-11-03 10:00:00', 'Confirmed', 'Pham Thi D', 'phamthid@example.com', '0901234567', 'Monthly health check'),
(108, 5, '2024-11-04 16:30:00', 'Cancelled', 'Hoang Van E', 'hoangvane@example.com', '0981234567', 'Consultation rescheduled'),
(124, 8, '2024-11-06 15:34:45', 'Pending', 'Le Hoai Nam', 'nam@gmail.com', '0939804455', 'đi khám đúng giờ'),
(125, 8, '2024-10-09 15:35:56', 'Confirmed', 'Le Hoai Nam', 'nam@gmail.com', '0939804455', 'tt'),
(126, 8, '2024-07-24 15:36:30', 'Cancelled', 'Le Hoai Nam', 'nam@gmail.com', '0939804455', 'rr');

-- --------------------------------------------------------

--
-- Table structure for table `password_reset_token`
--

CREATE TABLE `password_reset_token` (
  `id` int(11) NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `expiry_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `password_reset_token`
--

INSERT INTO `password_reset_token` (`id`, `token`, `user_id`, `expiry_date`) VALUES
(1, '54a66724-30b8-48c7-b3d0-8c33923d4bae', 8, '2024-11-04 02:16:49'),
(2, 'b5d90a54-adee-4114-abeb-cd900beacdcb', 8, '2024-11-04 02:18:37'),
(3, '8b50ba17-7ee0-4851-ae46-24e2de4f82e8', 8, '2024-11-04 02:20:30'),
(4, '69b5aacf-b6e2-4340-8567-7d861cab4c16', 8, '2024-11-04 02:25:36'),
(5, 'adb1e2b7-93c5-4e69-a8d7-8f9ee2113cd5', 8, '2024-11-04 02:28:05'),
(6, '11b5c3f4-b1fe-40e0-ae33-37333240c783', 8, '2024-11-04 02:33:29'),
(7, 'daec33cf-a6f3-49aa-ad90-d8b7f5ec79d5', 8, '2024-11-04 02:35:13'),
(8, 'dc8a8457-5f94-42c2-8a9d-c98a8d1a35e1', 8, '2024-11-04 02:36:15'),
(9, 'c808c944-0a7b-4a0c-934c-3d40efe871aa', 8, '2024-11-04 02:40:39'),
(10, 'c2b6a745-995c-4292-a134-0a92b6d7071a', 8, '2024-11-04 02:44:08'),
(11, '522948b1-39c6-4b44-ba11-33dbbf89056b', 8, '2024-11-04 02:48:21'),
(12, '35e52584-31df-4919-b99d-890de445ac97', 8, '2024-11-04 02:51:18'),
(15, '212d7c5f-661b-427a-aeff-0ae16ae0f3ef', 8, '2024-11-04 03:04:53'),
(17, '1b76759b-d9dd-412e-b226-f6e15c665afa', 8, '2024-11-04 03:13:30');

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
(1, 'Bacsi', '$2a$10$J40ipm.8yk21aUqWBiphzO68dP8Rm40NAgKo9rVa6P71Kr57W5KkO', NULL, 'BACSI'),
(2, 'nam', '$2a$10$Ly.ahGjAwnJL5BhRoG9mwuqZxk/KKRg9jHN0.k2Y1s2AIRLu19io2', 8, 'USER'),
(10, 'lee', '$2a$10$U4leUvb3FF4JkLem5vuGQez4FGBY9euoPEay1HHd6YKLM7M8vDsOK', 19, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `thanhtoan`
--

CREATE TABLE `thanhtoan` (
  `MaTT` int(50) NOT NULL,
  `MaLK` int(50) NOT NULL,
  `Sotien` int(20) DEFAULT NULL,
  `Ngaythanhtoan` datetime DEFAULT NULL,
  `Hinhthucthanhtoan` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thanhtoan`
--

INSERT INTO `thanhtoan` (`MaTT`, `MaLK`, `Sotien`, `Ngaythanhtoan`, `Hinhthucthanhtoan`) VALUES
(301, 101, 200000, '2024-11-06 11:14:23', 'VNPAY'),
(302, 102, 500000, '2024-11-07 07:50:32', 'Tiền mặt');

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
-- Indexes for table `password_reset_token`
--
ALTER TABLE `password_reset_token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user` (`user_id`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaTK`),
  ADD KEY `MaBN` (`MaBN`);

--
-- Indexes for table `thanhtoan`
--
ALTER TABLE `thanhtoan`
  ADD PRIMARY KEY (`MaTT`),
  ADD KEY `MaLK` (`MaLK`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `benhnhan`
--
ALTER TABLE `benhnhan`
  MODIFY `MaBN` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `chitietlichkham`
--
ALTER TABLE `chitietlichkham`
  MODIFY `MaHS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=212;

--
-- AUTO_INCREMENT for table `lichkham`
--
ALTER TABLE `lichkham`
  MODIFY `MaLK` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=127;

--
-- AUTO_INCREMENT for table `password_reset_token`
--
ALTER TABLE `password_reset_token`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `MaTK` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `thanhtoan`
--
ALTER TABLE `thanhtoan`
  MODIFY `MaTT` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=303;

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
-- Constraints for table `password_reset_token`
--
ALTER TABLE `password_reset_token`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `benhnhan` (`MaBN`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `MaBN` FOREIGN KEY (`MaBN`) REFERENCES `benhnhan` (`MaBN`);

--
-- Constraints for table `thanhtoan`
--
ALTER TABLE `thanhtoan`
  ADD CONSTRAINT `thanhtoan_ibfk_1` FOREIGN KEY (`MaLK`) REFERENCES `lichkham` (`MaLK`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
