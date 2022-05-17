-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 17, 2022 lúc 02:16 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `csdl_java`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_admin`
--

CREATE TABLE `tbl_admin` (
  `adminId` int(11) NOT NULL,
  `adminName` varchar(255) NOT NULL,
  `adminUser` varchar(255) NOT NULL,
  `adminPass` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbl_admin`
--

INSERT INTO `tbl_admin` (`adminId`, `adminName`, `adminUser`, `adminPass`) VALUES
(2, 'Linh', 'linh2106', '123456');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_banhang`
--

CREATE TABLE `tbl_banhang` (
  `maXe` varchar(255) NOT NULL,
  `tenXe` varchar(255) NOT NULL,
  `image` longblob NOT NULL,
  `soLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_chitietpn`
--

CREATE TABLE `tbl_chitietpn` (
  `donGia` float NOT NULL,
  `tongTien` float NOT NULL,
  `soLuong` int(11) NOT NULL,
  `maXe` varchar(255) NOT NULL,
  `maPN` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbl_chitietpn`
--

INSERT INTO `tbl_chitietpn` (`donGia`, `tongTien`, `soLuong`, `maXe`, `maPN`) VALUES
(19900, 1990000000, 100000, 'KS9', 'OPL5');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_chitietpx`
--

CREATE TABLE `tbl_chitietpx` (
  `maPX` varchar(255) NOT NULL,
  `donGia` double NOT NULL,
  `soLuong` int(11) NOT NULL,
  `maLoai` varchar(255) NOT NULL,
  `maXe` varchar(255) NOT NULL,
  `tongTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbl_chitietpx`
--

INSERT INTO `tbl_chitietpx` (`maPX`, `donGia`, `soLuong`, `maLoai`, `maXe`, `tongTien`) VALUES
('PLo1', 75000, 10, 'M2 - 120cc', 'GU3', 750000),
('PLo125', 72000, 10, 'M2 - 120cc', 'HI1', 720000),
('PLo125', 11000, 20, 'M4 - 150cc', 'FT1', 220000),
('OLP', 12000, 10, 'M2 - 120cc', 'QE7', 120000),
('OLP', 50000, 20, 'M4 - 150cc', 'XP0', 1000000),
('OLP', 19900, 30, 'M4 - 150cc', 'KS9', 597000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_khachhang`
--

CREATE TABLE `tbl_khachhang` (
  `maKH` varchar(255) NOT NULL,
  `tenKH` varchar(255) NOT NULL,
  `diaChi` varchar(255) NOT NULL,
  `SDT` int(30) NOT NULL,
  `gioiTinh` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbl_khachhang`
--

INSERT INTO `tbl_khachhang` (`maKH`, `tenKH`, `diaChi`, `SDT`, `gioiTinh`) VALUES
('M3', 'Khánh Linh', 'Đà Nẵng', 981984623, 'Nữ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_loaixe`
--

CREATE TABLE `tbl_loaixe` (
  `maLoai` varchar(255) NOT NULL,
  `tenLoai` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbl_loaixe`
--

INSERT INTO `tbl_loaixe` (`maLoai`, `tenLoai`) VALUES
('M1', '50cc'),
('M2', '120cc'),
('M3', '125cc'),
('M4', '150cc'),
('M5', '100cc'),
('M6', '175cc'),
('M7', '200cc');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_ncc`
--

CREATE TABLE `tbl_ncc` (
  `maNhaCC` varchar(255) NOT NULL,
  `tenNhaCC` varchar(255) NOT NULL,
  `diaChi` varchar(255) NOT NULL,
  `sdtNhaCC` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbl_ncc`
--

INSERT INTO `tbl_ncc` (`maNhaCC`, `tenNhaCC`, `diaChi`, `sdtNhaCC`) VALUES
('D2', 'Ducati', 'Italy', '+8401231'),
('DM1', 'HonDa', 'Nhật Bản', '+84098786'),
('KGY4', 'Yamaha', 'Nhật Bản', '+84219034');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_nhanvien`
--

CREATE TABLE `tbl_nhanvien` (
  `maNV` varchar(255) NOT NULL,
  `tenNV` varchar(255) NOT NULL,
  `ngaySinh` date NOT NULL,
  `diaChi` varchar(255) NOT NULL,
  `sdt` int(11) NOT NULL,
  `ngayLam` date NOT NULL,
  `luongNV` double NOT NULL,
  `gioiTinh` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbl_nhanvien`
--

INSERT INTO `tbl_nhanvien` (`maNV`, `tenNV`, `ngaySinh`, `diaChi`, `sdt`, `ngayLam`, `luongNV`, `gioiTinh`) VALUES
('C1', 'Maria', '2022-04-14', 'VietNam', 976543976, '2022-04-21', 4500000, 'Nam'),
('L1', 'Alex', '1998-07-21', 'Sài Gòn', 98567431, '2021-09-02', 8000, 'Nam'),
('M1', 'Quỳnh Ninh', '2002-05-02', 'Hồ Chí Minh', 981984623, '2022-05-15', 8000, 'Nam'),
('M2', 'Mạnh', '2012-04-27', 'Hồ Chí Minh', 981984623, '2022-04-30', 6000000, 'Nam'),
('M3', 'Quỳnh Khánh', '1999-03-18', 'Gia Lai', 981984623, '2022-05-15', 9000, 'Nam'),
('O1', 'Quỳnh', '2003-09-12', 'Sài Gòn', 981985623, '2022-04-03', 87000, 'Nữ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_phieunhap`
--

CREATE TABLE `tbl_phieunhap` (
  `maPN` varchar(255) NOT NULL,
  `ngayNhap` date NOT NULL,
  `maNV` varchar(255) NOT NULL,
  `maNCC` varchar(255) NOT NULL,
  `tongTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbl_phieunhap`
--

INSERT INTO `tbl_phieunhap` (`maPN`, `ngayNhap`, `maNV`, `maNCC`, `tongTien`) VALUES
('OPL5', '2022-04-17', 'O1', 'DM1', 1990000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_phieuxuat`
--

CREATE TABLE `tbl_phieuxuat` (
  `maPX` varchar(255) NOT NULL,
  `ngayXuat` date NOT NULL,
  `maKH` varchar(255) NOT NULL,
  `maNV` varchar(255) NOT NULL,
  `tongTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbl_phieuxuat`
--

INSERT INTO `tbl_phieuxuat` (`maPX`, `ngayXuat`, `maKH`, `maNV`, `tongTien`) VALUES
('OLP', '2022-05-17', 'M1', 'M1', 1717000),
('PLo1', '2022-05-15', 'M1', 'C1', 750000),
('PLo125', '2022-05-15', 'M1', 'M2', 940000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_xemay`
--

CREATE TABLE `tbl_xemay` (
  `maXe` varchar(255) NOT NULL,
  `tenXe` varchar(255) NOT NULL,
  `giaXe` double(20,0) NOT NULL,
  `soLuong` double NOT NULL,
  `loaiXe` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbl_xemay`
--

INSERT INTO `tbl_xemay` (`maXe`, `tenXe`, `giaXe`, `soLuong`, `loaiXe`, `image`) VALUES
('AI9', 'Yamaha R1M', 48600, 186, 'M4', 'Yamaha R1M.jpg'),
('BD4', ' DUCATI FBX', 87900, 135, 'M4', 'DUCATI FBX.jpg'),
('DF3', 'Vespa Scooter', 10100, 600, 'M2', 'Vespa Scooter.jpg'),
('DL8', 'Yamaha Aerox', 15000, 1200, 'M2', 'Yamaha Aerox.jpg'),
('FK2', 'YAMAHA YZF-R1M', 79000, 270, 'M4', 'YAMAHA YZF-R1M.jpg'),
('FT1', 'AWS_2334', 11000, 70, 'M4', 'AWS_2334.jpg'),
('GT4', 'Honda Monkey', 23000, 280, 'M2', 'Honda Monkey.jpg'),
('GU3', 'Akt 200', 75000, 340, 'M2', 'Akt Tt Ds 200 repainted.jpg'),
('HI1', 'MT-09', 72000, 100, 'M2', '22 MT-09.jpg'),
('IU6', 'Wild West Motorcycle', 21800, 39, 'M3', 'Wild West Motorcycle.jpg'),
('JR0', 'Scooter Tula T-200', 29900, 219, 'M2', 'Scooter Tula T-200.jpg'),
('KI5', 'Ducati Diavel Carbon', 80000, 59, 'M4', 'Ducati Diavel Carbon.jpg'),
('KS9', 'Toriyama DragonSeeker', 19900, 99991, 'M4', 'Toriyama DragonSeeker.jpg'),
('LD8', 'Ducati Diavel', 79900, 76, 'M4', 'Ducati Diavel.jpg'),
('OP1', 'Sportster S', 47900, 60, 'M3', 'Sportster S Motorcycle.jpg'),
('QE7', 'Scooter 125cc', 12000, 580, 'M2', 'Scooter 125cc.jpg'),
('RE7', 'BMW 30', 12000, 130, 'M2', 'BMW 30 AG.jpg'),
('SD5', 'Honda VRX400', 79000, 118, 'M2', 'Honda VRX400.jpg'),
('VQ1', 'HONDA SUPER CUB', 54000, 320, 'M2', 'HONDA SUPER CUB 110.jpg'),
('VS7', 'Fat Boy Motorcycle', 23000, 210, 'M3', 'Fat Boy Motorcycle.jpg'),
('VT5', 'TNT 135', 23400, 130, 'M3', 'TNT 135.jpg'),
('XP0', 'The Terminus Enforcer', 50000, 15, 'M4', 'The Terminus Enforcer.jpg'),
('XS2', 'Honda Motra', 25600, 210, 'M2', 'Honda Motra.jpg'),
('XU5', 'Z1000 KAWASAKI', 65000, 280, 'M4', 'Z1000 KAWASAKI.jpg'),
('YR0', 'Cafe Racer', 89000, 118, 'M4', 'Cafe Racer.jpg'),
('ZA3', 'Harley-Davidson Police', 42100, 87, 'M3', 'Harley-Davidson Police.jpg'),
('ZC4', 'Triumph Speed Triple1050', 69900, 218, 'M4', 'Triumph Speed Triple 1050.jpg');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tbl_admin`
--
ALTER TABLE `tbl_admin`
  ADD PRIMARY KEY (`adminId`);

--
-- Chỉ mục cho bảng `tbl_banhang`
--
ALTER TABLE `tbl_banhang`
  ADD UNIQUE KEY `maXe` (`maXe`);

--
-- Chỉ mục cho bảng `tbl_chitietpn`
--
ALTER TABLE `tbl_chitietpn`
  ADD KEY `maPN` (`maPN`),
  ADD KEY `maXe` (`maXe`);

--
-- Chỉ mục cho bảng `tbl_chitietpx`
--
ALTER TABLE `tbl_chitietpx`
  ADD KEY `maXe` (`maXe`),
  ADD KEY `maPX` (`maPX`);

--
-- Chỉ mục cho bảng `tbl_khachhang`
--
ALTER TABLE `tbl_khachhang`
  ADD PRIMARY KEY (`maKH`);

--
-- Chỉ mục cho bảng `tbl_loaixe`
--
ALTER TABLE `tbl_loaixe`
  ADD PRIMARY KEY (`maLoai`);

--
-- Chỉ mục cho bảng `tbl_ncc`
--
ALTER TABLE `tbl_ncc`
  ADD PRIMARY KEY (`maNhaCC`);

--
-- Chỉ mục cho bảng `tbl_nhanvien`
--
ALTER TABLE `tbl_nhanvien`
  ADD PRIMARY KEY (`maNV`);

--
-- Chỉ mục cho bảng `tbl_phieunhap`
--
ALTER TABLE `tbl_phieunhap`
  ADD PRIMARY KEY (`maPN`),
  ADD KEY `maNCC` (`maNCC`),
  ADD KEY `maNV` (`maNV`);

--
-- Chỉ mục cho bảng `tbl_phieuxuat`
--
ALTER TABLE `tbl_phieuxuat`
  ADD PRIMARY KEY (`maPX`),
  ADD KEY `maNV` (`maNV`),
  ADD KEY `maKH` (`maKH`);

--
-- Chỉ mục cho bảng `tbl_xemay`
--
ALTER TABLE `tbl_xemay`
  ADD PRIMARY KEY (`maXe`),
  ADD KEY `loaiXe` (`loaiXe`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tbl_admin`
--
ALTER TABLE `tbl_admin`
  MODIFY `adminId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `tbl_chitietpn`
--
ALTER TABLE `tbl_chitietpn`
  ADD CONSTRAINT `tbl_chitietpn_ibfk_1` FOREIGN KEY (`maPN`) REFERENCES `tbl_phieunhap` (`maPN`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_chitietpn_ibfk_2` FOREIGN KEY (`maXe`) REFERENCES `tbl_xemay` (`maXe`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `tbl_chitietpx`
--
ALTER TABLE `tbl_chitietpx`
  ADD CONSTRAINT `tbl_chitietpx_ibfk_1` FOREIGN KEY (`maXe`) REFERENCES `tbl_xemay` (`maXe`),
  ADD CONSTRAINT `tbl_chitietpx_ibfk_2` FOREIGN KEY (`maPX`) REFERENCES `tbl_phieuxuat` (`maPX`);

--
-- Các ràng buộc cho bảng `tbl_phieunhap`
--
ALTER TABLE `tbl_phieunhap`
  ADD CONSTRAINT `tbl_phieunhap_ibfk_1` FOREIGN KEY (`maNCC`) REFERENCES `tbl_ncc` (`maNhaCC`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_phieunhap_ibfk_2` FOREIGN KEY (`maNV`) REFERENCES `tbl_nhanvien` (`maNV`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `tbl_phieuxuat`
--
ALTER TABLE `tbl_phieuxuat`
  ADD CONSTRAINT `tbl_phieuxuat_ibfk_1` FOREIGN KEY (`maNV`) REFERENCES `tbl_nhanvien` (`maNV`),
  ADD CONSTRAINT `tbl_phieuxuat_ibfk_2` FOREIGN KEY (`maKH`) REFERENCES `tbl_khachhang` (`maKH`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
