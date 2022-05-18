package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import DTO.ChiTietHoaDonXuat;

public class ChiTietPhieuXuatDAO {
	ConnectUnit connect;
	/**
	 * Lấy thông tin từ Database
	 */
	public ArrayList<ChiTietHoaDonXuat> docDB(String condition, String orderBy) throws Exception {
		// kết nối CSDL
		connect = new ConnectUnit();
		ResultSet result = this.connect.Select("tbl_chitietpx", condition, orderBy);
		ArrayList<ChiTietHoaDonXuat> sanphams = new ArrayList<>();
		while (result.next()) {
			ChiTietHoaDonXuat ct = new ChiTietHoaDonXuat();
			ct.setMaPX(result.getString("maPX"));
			ct.setDonGia(result.getDouble("donGia"));
			ct.setSoLuong(result.getInt("soLuong"));
			ct.setMaLoai(result.getString("maLoai"));
			ct.setMaXe(result.getString("maXe"));
			ct.setTongTien(result.getDouble("tongTien"));
			sanphams.add(ct);
		}
		connect.Close();
		return sanphams;
	}

	public ArrayList<ChiTietHoaDonXuat> docDB1(String condition) throws Exception {
		// kết nối CSDL
		connect = new ConnectUnit();
		String cd = " maPX  = '" + condition + "'";

		ResultSet result = this.connect.Select("tbl_chitietpx", cd);
		ArrayList<ChiTietHoaDonXuat> sanphams = new ArrayList<>();
		while (result.next()) {
			ChiTietHoaDonXuat ct = new ChiTietHoaDonXuat();
			ct.setMaPX(result.getString("maPX"));
			ct.setDonGia(result.getDouble("donGia"));
			ct.setSoLuong(result.getInt("soLuong"));
			ct.setMaLoai(result.getString("maLoai"));
			ct.setMaXe(result.getString("maXe"));
			ct.setTongTien(result.getDouble("tongTien"));
			sanphams.add(ct);
		}
		connect.Close();
		return sanphams;
	}

	public ArrayList<ChiTietHoaDonXuat> docDB(String condition) throws Exception {
		return docDB(condition, null);
	}

	public ArrayList<ChiTietHoaDonXuat> docDB() throws Exception {
		return docDB1(null);
	}

	/**
	 * Tạo thêm 1 hdách hàng dựa theo đã có thông tin trước
	 * 
	 * @return true nếu thành công
	 */
	public Boolean them(ChiTietHoaDonXuat hd) throws Exception {
		connect = new ConnectUnit();
		String sqlUpdateSP = "UPDATE tbl_xemay SET soLuong = soLuong - ? WHERE maXe  = ?";
		PreparedStatement pre = MySQLConnection.connect.prepareCall(sqlUpdateSP);
		System.out.println(pre);
		pre.setInt(1, hd.getSoLuong());
		pre.setString(2, hd.getMaXe());
		pre.executeUpdate();
		// tạo đối tượng truyền vào
		HashMap<String, Object> insertValues = new HashMap<>();
		insertValues.put("maPX", hd.getMaPX());
		insertValues.put("donGia", hd.getDonGia());
		insertValues.put("soLuong", hd.getSoLuong());
		insertValues.put("maLoai", hd.getMaLoai());
		insertValues.put("maXe", hd.getMaXe());
		insertValues.put("tongTien", hd.gettongTien());
		Boolean check_them = connect.Insert("tbl_chitietpx", insertValues);
		connect.Close();
		return check_them;
	}
}
