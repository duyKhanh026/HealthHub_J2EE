-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 24, 2024 lúc 02:28 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `phongkham`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `benhnhan`
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
-- Đang đổ dữ liệu cho bảng `benhnhan`
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
-- Cấu trúc bảng cho bảng `chitietlichkham`
--

CREATE TABLE `chitietlichkham` (
  `MaHS` int(11) NOT NULL,
  `MaLK` int(11) NOT NULL,
  `Chuandoan` text NOT NULL,
  `Donthuoc` text NOT NULL,
  `Ghichuthem` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietlichkham`
--

INSERT INTO `chitietlichkham` (`MaHS`, `MaLK`, `Chuandoan`, `Donthuoc`, `Ghichuthem`) VALUES
(201, 101, 'Viêm họng', 'Paracetamol 500mg, Amoxicillin 500mg', 'Uống nhiều nước, nghỉ ngơi'),
(202, 102, 'Đau dạ dày', 'Omeprazole 20mg, Metronidazole 500mg', 'Tránh ăn đồ cay nóng'),
(203, 103, 'Cảm cúm', 'Vitamin C, Ibuprofen 200mg', 'Nghỉ ngơi, uống nước ấm');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lichkham`
--

CREATE TABLE `lichkham` (
  `MaLK` int(50) NOT NULL,
  `MaBN` int(50) NOT NULL,
  `Ngaygiodatkham` datetime NOT NULL,
  `Trangthai` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `lichkham`
--

INSERT INTO `lichkham` (`MaLK`, `MaBN`, `Ngaygiodatkham`, `Trangthai`) VALUES
(101, 1, '2024-10-20 09:00:00', 'Completed'),
(102, 2, '2024-10-21 14:30:00', 'Pending'),
(103, 1, '2024-10-22 11:15:00', 'Completed');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaTK` int(50) NOT NULL,
  `TenDN` varchar(50) NOT NULL,
  `Matkhau` varchar(150) NOT NULL,
  `MaBN` int(50) DEFAULT NULL,
  `Vaitro` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`MaTK`, `TenDN`, `Matkhau`, `MaBN`, `Vaitro`) VALUES
(1, 'Bacsi', '$2a$10$J40ipm.8yk21aUqWBiphzO68dP8Rm40NAgKo9rVa6P71Kr57W5KkO', NULL, 'bacsi'),
(2, 'nam', '$2a$10$NhmJEDaPls4Xb1Rc.UlhAORqSLw4UjgRon6LbnpyI8jLLngE/rdMO', 8, 'user');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `benhnhan`
--
ALTER TABLE `benhnhan`
  ADD PRIMARY KEY (`MaBN`);

--
-- Chỉ mục cho bảng `chitietlichkham`
--
ALTER TABLE `chitietlichkham`
  ADD PRIMARY KEY (`MaHS`),
  ADD KEY `MaLK` (`MaLK`);

--
-- Chỉ mục cho bảng `lichkham`
--
ALTER TABLE `lichkham`
  ADD PRIMARY KEY (`MaLK`),
  ADD KEY `MaBN` (`MaBN`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaTK`),
  ADD KEY `MaBN` (`MaBN`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `benhnhan`
--
ALTER TABLE `benhnhan`
  MODIFY `MaBN` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `MaTK` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietlichkham`
--
ALTER TABLE `chitietlichkham`
  ADD CONSTRAINT `chitietlichkham_ibfk_1` FOREIGN KEY (`MaLK`) REFERENCES `lichkham` (`MaLK`);

--
-- Các ràng buộc cho bảng `lichkham`
--
ALTER TABLE `lichkham`
  ADD CONSTRAINT `lichkham_ibfk_1` FOREIGN KEY (`MaBN`) REFERENCES `benhnhan` (`MaBN`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `MaBN` FOREIGN KEY (`MaBN`) REFERENCES `benhnhan` (`MaBN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
