package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.ThongKeDTO;

public class ThongKeDAO {
	ConnectUnit connect;
	public ThongKeDTO getThongKe(int nam) {
		ThongKeDTO thongKe = new ThongKeDTO();
		int[] tongThuQuy = new int[4];
		int [] tongNhanVien = new int[4];
		int [] tongXeMay = new int[4];
		tongThuQuy[0] = getTongThuQuy(nam, 1);
		tongThuQuy[1] = getTongThuQuy(nam, 2);
		tongThuQuy[2] = getTongThuQuy(nam, 3);
		tongThuQuy[3] = getTongThuQuy(nam, 4);
		thongKe.setTongQuy(tongThuQuy);
		//
		tongNhanVien[0] = getSoLuongNhanVienYear(nam, 1);
		tongNhanVien[1] = getSoLuongNhanVienYear(nam, 2);
		tongNhanVien[2] = getSoLuongNhanVienYear(nam, 3);
		tongNhanVien[3] = getSoLuongNhanVienYear(nam, 4);
		thongKe.setSoLuongNhanVien(tongNhanVien);
//		//
		tongXeMay[0] = getTongSoLuongSPYear(nam, 1);
		tongXeMay[1] = getTongSoLuongSPYear(nam, 2);
		tongXeMay[2] = getTongSoLuongSPYear(nam, 3);
		tongXeMay[3] = getTongSoLuongSPYear(nam, 4);
		thongKe.setSoLuongXeMay(tongXeMay);
		System.out.println(tongThuQuy);
		System.out.println(tongNhanVien);
		
		return thongKe;
	}
	public int getTongDoanhThu() {
		connect = new ConnectUnit();
		try {
			String od = "tongTien";
			ResultSet result = this.connect.SelectSum("tbl_phieuxuat",od);
			while (result.next()) {
				return result.getInt(1);
			}
			System.out.println(result);
			connect.Close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int getTongSoLuongSP() {
		connect = new ConnectUnit();
		try {
			String cd = "soLuong";
			ResultSet result = this.connect.SelectSum("tbl_xemay",cd);
			while (result.next()) {
				return result.getInt(1);
			}
			connect.Close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int getSoLuongNhanVien() {
		connect = new ConnectUnit();
        try {
			ResultSet result = this.connect.SelectCount("tbl_nhanvien");
            while (result.next()) {
                return result.getInt(1);
            }
        }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0;
    }

	//
	private String[] getQuy(int nam, int quy) {
		int namBatDau = nam;
		int namKetThuc = nam;
		String thangBatDau = "01"; // kiểu String do có số 0 ở phía trước
		String thangKetThuc = "04"; // kiểu String do có số 0 ở phía trước
		String[] kq = new String[2];
		switch (quy) {
		case 1:
			thangBatDau = "01";
			thangKetThuc = "03";
			break;
		case 2:
			thangBatDau = "04";
			thangKetThuc = "06";
			break;
		case 3:
			thangBatDau = "07";
			thangKetThuc = "09";
			break;
		case 4:
			thangBatDau = "10";
			thangKetThuc = "12";
			
		}
		String strBatDau = Integer.toString(namBatDau) + thangBatDau + "01";
		String strKetThuc = Integer.toString(namKetThuc) + thangKetThuc + "30";
		namKetThuc++;
		kq[0] = strBatDau;
		kq[1] = strKetThuc;
		return kq;
	}
	

	private int getTongThuQuy(int nam, int quy) {
		connect = new ConnectUnit();
		String[] dateString = getQuy(nam, quy);
		try {
			String od = "tongTien";
			String cond = "ngayXuat >= " + dateString[0] + " AND ngayXuat <= " + dateString[1] + "";
			ResultSet result = this.connect.SelectSumDT("tbl_phieuxuat", od, cond);
			while (result.next()) {
				return result.getInt(1);
			}
			connect.Close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// lấy số lượng nhân viên theo quý và năm
	private int getSoLuongNhanVienYear(int nam, int quy) {
		connect = new ConnectUnit();
        try {
        	String[] dateString = getQuy(nam, quy);
    		String cond = "ngayLam >= " + dateString[0] + " AND ngayLam < " + dateString[1] + "";
			ResultSet result = this.connect.SelectCount("tbl_nhanvien",cond);
            while (result.next()) {
                return result.getInt(1);
            }
        }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0;
    }
	public int getTongSoLuongSPYear(int nam, int quy) {
		connect = new ConnectUnit();
		try {
			String[] dateString = getQuy(nam, quy);
    		String sql = "SELECT SUM(ctpx.soLuong) "
    				+ "  FROM tbl_phieuxuat AS px INNER JOIN tbl_chitietpx AS ctpx "
    				+ "ON px.maPX =ctpx.maPX "
    				+ "WHERE px.ngayXuat >= " + dateString[0] + " AND px.ngayXuat <= " + dateString[1] + "";
    		PreparedStatement pre = MySQLConnection.connect.prepareCall(sql);
    		ResultSet resultSet = pre.executeQuery();
    		while (resultSet.next()) {
                return resultSet.getInt(1);
            }
			connect.Close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public double getDoanhThuThang(String thang, int nam) {
		connect = new ConnectUnit();
		String thangBD = "";
		String thangKT = "";
		try {
			thangBD = nam + "" + thang + "01";
			thangKT = nam + "" + thang + "30";
			String od = "tongTien";
			String cond = "ngayXuat BETWEEN CAST(" + thangBD + " AS DATE) AND CAST(" + thangKT + " AS DATE)";
			ResultSet result = this.connect.SelectSumDT("tbl_phieuxuat", od, cond);
			while (result.next()) {
				return Double.parseDouble(result.getInt(1) + "");
			}
			connect.Close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nam;
	}

}
