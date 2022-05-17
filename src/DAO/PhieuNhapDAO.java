package DAO;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;


import DTO.HoaDonNhap;

public class PhieuNhapDAO {
	ConnectUnit connect;
	FileOutputStream fs = null;
	public static Connection conn = null;
	/**
	 * Lấy thông tin từ Database
	 */
	public ArrayList<HoaDonNhap> docDB(String condition, String orderBy) throws Exception {
		// kết nối CSDL
		connect = new ConnectUnit();
		ResultSet result = this.connect.Select("tbl_phieunhap", condition, orderBy);
		ArrayList<HoaDonNhap> sanphams = new ArrayList<>();
		while (result.next()) {
			HoaDonNhap pn = new HoaDonNhap();
			pn.setMaPN(result.getString("maPN"));
			pn.setNgayNhap(result.getString("ngayNhap"));
			pn.setMaNV(result.getString("maNV"));
			pn.setMaNCC(result.getString("maNCC"));
			pn.setTongTien(result.getDouble("tongTien"));
			sanphams.add(pn);
		}
		connect.Close();
		return sanphams;
	}

	public ArrayList<HoaDonNhap> docDB(String condition) throws Exception {
		return docDB(condition, null);
	}

	public ArrayList<HoaDonNhap> docDB() throws Exception {
		return docDB(null);
	}
	public ArrayList<HoaDonNhap> search(String data) {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String condition = " maPN  LIKE '%" + data + "%'";
			ResultSet result = this.connect.Select("tbl_phieunhap", condition);
			System.out.println(result);
			ArrayList<HoaDonNhap> sanphams = new ArrayList<>();
			while (result.next()) {
				HoaDonNhap pn = new HoaDonNhap();
				pn.setMaPN(result.getString("maPN"));
				pn.setNgayNhap(result.getString("ngayNhap"));
				pn.setMaNV(result.getString("maNV"));
				pn.setMaNCC(result.getString("maNCC"));
				pn.setTongTien(result.getDouble("tongTien"));
				sanphams.add(pn);
			}
			connect.Close();
			return sanphams;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<HoaDonNhap> sortHight() {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String orderBy = " tongTien  DESC";
			ResultSet result = this.connect.SelectOrderBY("tbl_phieunhap", orderBy);
			System.out.println(result);
			ArrayList<HoaDonNhap> sanphams = new ArrayList<>();
			while (result.next()) {
				HoaDonNhap pn = new HoaDonNhap();
				pn.setMaPN(result.getString("maPN"));
				pn.setNgayNhap(result.getString("ngayNhap"));
				pn.setMaNV(result.getString("maNV"));
				pn.setMaNCC(result.getString("maNCC"));
				pn.setTongTien(result.getDouble("tongTien"));
				sanphams.add(pn);
			}
			connect.Close();
			return sanphams;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<HoaDonNhap> sortSmall() {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String orderBy = " tongTien  ASC";
			ResultSet result = this.connect.SelectOrderBY("tbl_phieunhap", orderBy);
			System.out.println(result);
			ArrayList<HoaDonNhap> sanphams = new ArrayList<>();
			while (result.next()) {
				HoaDonNhap pn = new HoaDonNhap();
				pn.setMaPN(result.getString("maPN"));
				pn.setNgayNhap(result.getString("ngayNhap"));
				pn.setMaNV(result.getString("maNV"));
				pn.setMaNCC(result.getString("maNCC"));
				pn.setTongTien(result.getDouble("tongTien"));
				sanphams.add(pn);
			}
			connect.Close();
			return sanphams;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Boolean them(HoaDonNhap hd) throws Exception {
		connect = new ConnectUnit();
		HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("maPN", hd.getMaPN());
        insertValues.put("ngayNhap", hd.getNgayNhap());
        insertValues.put("maNV", hd.getMaNV());
        insertValues.put("maNCC", hd.getMaNCC());
        insertValues.put("tongTien", hd.getTongTien());
        Boolean check = connect.Insert("tbl_phieunhap", insertValues);
        connect.Close();
        return check;		
	}
	public Boolean xoa(HoaDonNhap hd) throws Exception {
		connect = new ConnectUnit();
		String condition = " maPN  = '" + hd.getMaPN() + "'";
		Boolean check = connect.Delete("tbl_phieunhap", condition);
		connect.Close();
		return check;
	}
}
