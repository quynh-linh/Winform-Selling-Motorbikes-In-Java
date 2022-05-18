-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 18, 2022 lúc 06:58 PM
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
-- Cấu trúc bảng cho bảng `tbl_chitietpn`
--

CREATE TABLE `tbl_chitietpn` (
  `donGia` float NOT NULL,
  `tongTien` float NOT NULL,
  `soLuong` int(11) NOT NULL,
  `maXe` varchar(255) NOT NULL,
  `maPN` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
('M1', 72000, 1, 'M1 - 50cc', 'HI1', 72000),
('M2', 72000, 1, 'M1 - 50cc', 'HI1', 72000),
('M2', 23400, 2, 'M3 - 125cc', 'VT5', 46800),
('M4', 12000, 10, 'M6 - 175cc', 'QE7', 120000),
('OP1', 80000, 1, 'M4 - 150cc', 'KI5', 80000);

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
('KOl1', 'Quỳnh', 'Gia Lai', 981984623, 'Nam'),
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
('M10', '250cc'),
('M11', '275cc'),
('M12', '300cc'),
('M3', '125cc'),
('M4', '150cc'),
('M5', '100cc'),
('M6', '175cc'),
('M7', '200cc'),
('M8', '225cc'),
('M9', '75cc');

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
('KGY4', 'Yamaha', 'Nhật Bản', '+84219034'),
('M10', ' CÔNG TY TNHH THƯƠNG MẠI VÀ DỊCH VỤ HIỆP GIA PHÁT', '76 Điện Biên Phủ, P. Chính Gián, Q. Thanh Khê, Tp. Đà Nẵng', '(0511) 3649993'),
('M11', 'Toyota Motor Corporation', 'JaPan', '0981984623'),
('M12', 'Tập đoàn Volkswagen', 'Đức', '0981984623'),
('M13', 'Daimler AG', 'Đức', '0981984623'),
('M2', 'Công Ty CPTM & XNK Việt Hồng Chinh', ' Km 5 Đ. 9D KCN Nam, TP. Đông Hà, Tỉnh Quảng Trị', '0942 944 357'),
('M3', 'Công ty Mô tô Mã Lực', ' 651 Âu Cơ, Phường Hòa Thạnh, Quận Tân Phú, TP Hồ Chí Minh (TPHCM)', '0906 358 398'),
('M4', ' CÔNG TY TNHH HƯỚNG TUYẾT', 'Tiểu Khu Phú Mỹ, Thị Trấn Phú Xuyên, Huyện Phú Xuyên, Tp. Hà Nội', '(050) 0462073'),
('M5', ' CÔNG TY TNHH SẢN XUẤT LẮP RÁP TUẤN NGHĨA', 'Số 18 Trần Quang Diệu, Ô Chợ Dừa, Quận Đống Đa, TP Hà Nội (TPHN)', '(024) 39329266'),
('M6', ' CÔNG TY TNHH XNK Ô TÔ ĐẠI ĐÔ THÀNH', '47 Điện Biên Phủ, Đakao, Quận 1, Thành Phố Hồ Chí Minh (TPHCM)', '(028) 39117788'),
('M7', ' CÔNG TY TNHH HOÀ BÌNH MINH', 'Lô 8A, Đường Đồng Khởi, P.Tân Hiệp, Tp.Biên Hoà, Đồng Nai', '(061) 3675522'),
('M8', ' CÔNG TY TNHH SẢN XUẤT VÀ THƯƠNG MẠI KHANG THỊNH', 'Số 2A Quán Sứ, Q. Hoàn Kiếm, TP. Hà Nội (TPHN)', '(024) 36811428'),
('M9', ' CÔNG TY TNHH THƯƠNG MẠI NGỌC HOA', '138-140 Hoàng Diệu, P. 9, Q. 4, Tp. Hồ Chí Minh', '(08) 39400925');

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
('M2', 'Mạnh', '2012-04-27', 'Hồ Chí Minh', 981984623, '2022-04-30', 6000000, 'Nam'),
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
('M1', '2022-02-18', 'KOl1', 'M2', 72000),
('M2', '2022-07-18', 'M3', 'C1', 118800),
('M4', '2022-04-18', 'KOl1', 'C1', 120000),
('OP1', '2022-11-17', 'KOl1', 'M2', 80000);

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
('DF3', 'Vespa Scooter', 10100, 600, 'M12', 'Vespa Scooter.jpg'),
('DL8', 'Yamaha Aerox', 15000, 1200, 'M1', 'Yamaha Aerox.jpg'),
('FK2', 'YAMAHA YZF-R1M', 79000, 260, 'M4', 'YAMAHA YZF-R1M.jpg'),
('FT1', 'AWS_2334', 11000, 69, 'M4', 'AWS_2334.jpg'),
('GT4', 'Honda Monkey', 23000, 280, 'M8', 'Honda Monkey.jpg'),
('GU3', 'Akt 200', 75000, 340, 'M6', 'Akt Tt Ds 200 repainted.jpg'),
('HI1', 'MT-09', 72000, 86, 'M1', '22 MT-09.jpg'),
('IU6', 'Wild West Motorcycle', 21800, 24, 'M3', 'Wild West Motorcycle.jpg'),
('JR0', 'Scooter Tula T-200', 29900, 218, 'M4', 'Scooter Tula T-200.jpg'),
('KI5', 'Ducati Diavel Carbon', 80000, 58, 'M4', 'Ducati Diavel Carbon.jpg'),
('KS9', 'Toriyama DragonSeeker', 19900, 98990, 'M4', 'Toriyama DragonSeeker.jpg'),
('LD8', 'Ducati Diavel', 79900, 75, 'M4', 'Ducati Diavel.jpg'),
('OP1', 'Sportster S', 47900, 59, 'M3', 'Sportster S Motorcycle.jpg'),
('QE7', 'Scooter 125cc', 12000, 570, 'M6', 'Scooter 125cc.jpg'),
('RE7', 'BMW 30', 12000, 128, 'M3', 'BMW 30 AG.jpg'),
('SD5', 'Honda VRX400', 79000, 118, 'M7', 'Honda VRX400.jpg'),
('VQ1', 'HONDA SUPER CUB', 54000, 320, 'M11', 'HONDA SUPER CUB 110.jpg'),
('VS7', 'Fat Boy Motorcycle', 23000, 210, 'M3', 'Fat Boy Motorcycle.jpg'),
('VT5', 'TNT 135', 23400, 128, 'M3', 'TNT 135.jpg'),
('XP0', 'The Terminus Enforcer', 50000, 15, 'M4', 'The Terminus Enforcer.jpg'),
('XS2', 'Honda Motra', 25600, 210, 'M8', 'Honda Motra.jpg'),
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
-- Chỉ mục cho bảng `tbl_chitietpn`
--
ALTER TABLE `tbl_chitietpn`
  ADD KEY `maPN` (`maPN`),
  ADD KEY `maXe` (`maXe`);

--
-- Chỉ mục cho bảng `tbl_chitietpx`
--
ALTER TABLE `tbl_chitietpx`
  ADD KEY `tbl_chitietpx_ibfk_1` (`maPX`),
  ADD KEY `tbl_chitietpx_ibfk_2` (`maXe`);

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
  ADD KEY `maKH` (`maKH`),
  ADD KEY `maNV` (`maNV`);

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
  ADD CONSTRAINT `tbl_chitietpx_ibfk_1` FOREIGN KEY (`maPX`) REFERENCES `tbl_phieuxuat` (`maPX`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_chitietpx_ibfk_2` FOREIGN KEY (`maXe`) REFERENCES `tbl_xemay` (`maXe`) ON DELETE CASCADE ON UPDATE CASCADE;

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
  ADD CONSTRAINT `tbl_phieuxuat_ibfk_1` FOREIGN KEY (`maKH`) REFERENCES `tbl_khachhang` (`maKH`),
  ADD CONSTRAINT `tbl_phieuxuat_ibfk_2` FOREIGN KEY (`maNV`) REFERENCES `tbl_nhanvien` (`maNV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
