package DAO;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.HoaDonXuat;

public class PhieuXuatDAO {
	ConnectUnit connect;
	FileOutputStream fs = null;
	public static Connection conn = null;

	/**
	 * Lấy thông tin từ Database
	 */
	public ArrayList<HoaDonXuat> docDB(String condition, String orderBy) throws Exception {
		// kết nối CSDL
		connect = new ConnectUnit();
		ResultSet result = this.connect.Select("tbl_phieuxuat", condition, orderBy);
		ArrayList<HoaDonXuat> sanphams = new ArrayList<>();
		while (result.next()) {
			HoaDonXuat px = new HoaDonXuat();
			px.setMaPX(result.getString("maPX"));
			px.setNgayXuat(result.getString("ngayXuat"));
			px.setMaKH(result.getString("maKH"));
			px.setMaNV(result.getString("maNV"));
			px.setTongTien(result.getDouble("tongTien"));
			sanphams.add(px);
		}
		connect.Close();
		return sanphams;
	}

	public ArrayList<HoaDonXuat> docDB(String condition) throws Exception {
		return docDB(condition, null);
	}

	public ArrayList<HoaDonXuat> docDB() throws Exception {
		return docDB(null);
	}

	public Boolean them(HoaDonXuat hd) throws Exception {
		connect = new ConnectUnit();
		HashMap<String, Object> insertValues = new HashMap<>();
		insertValues.put("maPX", hd.getMaPX());
		insertValues.put("ngayXuat", hd.getNgayXuat());
		insertValues.put("maKH", hd.getMaKH());
		insertValues.put("maNV", hd.getMaNV());
		insertValues.put("tongTien", hd.getTongTien());
		Boolean check = connect.Insert("tbl_phieuxuat", insertValues);
		connect.Close();
		return check;
	}

	public Boolean xoa(HoaDonXuat hd) throws Exception {
		connect = new ConnectUnit();
		String condition = " maPX  = '" + hd.getMaPX() + "'";
		Boolean check = connect.Delete("tbl_phieuxuat", condition);
		connect.Close();
		return check;
	}

}
