package DAO;

import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.XeMayDTO;

public class xemayDAO {
	ConnectUnit connect;
	FileOutputStream fs = null;

	/**
	 * Lấy thông tin từ Database
	 */
	public ArrayList<XeMayDTO> docDB(String condition, String orderBy) throws Exception {
		// kết nối CSDL
		connect = new ConnectUnit();
		ResultSet result = this.connect.Select("tbl_xemay", condition, orderBy);
		ArrayList<XeMayDTO> sanphams = new ArrayList<>();
		while (result.next()) {
			XeMayDTO xemay = new XeMayDTO();
			xemay.setMaXe(result.getString("maXe"));
			xemay.setTenXe(result.getString("tenXe"));
			xemay.setGiaXe(result.getDouble("giaXe"));
			xemay.setSoLuong(result.getInt("soLuong"));
			xemay.setLoaiXe(result.getString("loaiXe"));
			xemay.setImage(result.getString("image"));
			System.out.println("Image retrieved successfully.");
			sanphams.add(xemay);
		}
		connect.Close();
		return sanphams;
	}

	public ArrayList<XeMayDTO> docDB(String condition) throws Exception {
		return docDB(condition, null);
	}

	public ArrayList<XeMayDTO> docDB() throws Exception {
		return docDB(null);
	}

	/**
	 * Tạo thêm 1 hdách hàng dựa theo đã có thông tin trước
	 * 
	 * @return true nếu thành công
	 */
	public Boolean them(XeMayDTO hd) throws Exception {
		connect = new ConnectUnit();
		// tạo đối tượng truyền vào
		HashMap<String, Object> insertValues = new HashMap<>();
		insertValues.put("maXe", hd.getMaXe());
		insertValues.put("tenXe", hd.getTenXe());
		insertValues.put("giaXe", hd.getGiaXe());
		insertValues.put("soLuong", hd.getSoLuong());
		insertValues.put("loaiXe", hd.getLoaiXe());
		insertValues.put("image", hd.getMyImage());
		Boolean check = connect.Insert("tbl_xemay", insertValues);
		connect.Close();
		return check;
	}

	// them excel
	public Boolean themEcxel(ArrayList<XeMayDTO> hd) throws Exception {
		connect = new ConnectUnit();
		// tạo đối tượng truyền vào
		HashMap<String, Object> insertValues = new HashMap<>();
		for (XeMayDTO xeMayDTO : hd) {
			insertValues.put("maXe", xeMayDTO.getMaXe());
			insertValues.put("tenXe", xeMayDTO.getTenXe());
			insertValues.put("giaXe", xeMayDTO.getGiaXe());
			insertValues.put("soLuong", xeMayDTO.getSoLuong());
			insertValues.put("loaiXe", xeMayDTO.getLoaiXe());
			insertValues.put("image", xeMayDTO.getMyImage());
			Boolean check = connect.Insert("tbl_xemay", insertValues);
			connect.Close();
			return check;
		}
		return false;
	}

	/**
	 * @param hd chuyền vào dữ liệu nhà cung cấp để xóa
	 * @return true nếu thành công
	 */
	public Boolean xoa(XeMayDTO hd) throws Exception {
		connect = new ConnectUnit();
		String condition = " maXe  = '" + hd.getMaXe() + "'";
		Boolean check = connect.Delete("tbl_xemay", condition);
		connect.Close();
		return check;
	}

	/**
	 * @param hd truyền vào dữ liệu nhà cung cấp mới Sửa thông tin đăng nhập
	 * @return true nếu thành công
	 */
	public Boolean sua(XeMayDTO hd) throws Exception {
		connect = new ConnectUnit();
		// tạo đối tượng truyền vào
		HashMap<String, Object> insertValues = new HashMap<>();
		insertValues.put("tenXe", hd.getTenXe());
		insertValues.put("giaXe	", hd.getGiaXe());
		insertValues.put("soLuong", hd.getSoLuong());
		insertValues.put("loaiXe", hd.getLoaiXe());
		insertValues.put("image", hd.getMyImage());
		System.out.println(hd.toString());
		String condition = " maXe  = '" + hd.getMaXe() + "'";
		Boolean check = connect.Update("tbl_xemay", insertValues, condition);
		connect.Close();
		return check;
	}

	public ArrayList<XeMayDTO> search(String data) {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String condition = " tenXe  LIKE '%" + data + "%'";
			ResultSet result = this.connect.Select("tbl_xemay", condition);
			System.out.println(result);
			ArrayList<XeMayDTO> sanphams = new ArrayList<>();
			while (result.next()) {
				XeMayDTO xemay = new XeMayDTO();
				xemay.setMaXe(result.getString("maXe"));
				xemay.setTenXe(result.getString("tenXe"));
				xemay.setGiaXe(result.getDouble("giaXe"));
				xemay.setSoLuong(result.getInt("soLuong"));
				xemay.setLoaiXe(result.getString("loaiXe"));
				xemay.setImage(result.getString("image"));
				sanphams.add(xemay);
			}
			connect.Close();
			return sanphams;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<XeMayDTO> sortHight() {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String orderBy = " giaXe  DESC";
			ResultSet result = this.connect.SelectOrderBY("tbl_xemay", orderBy);
			System.out.println(result);
			ArrayList<XeMayDTO> sanphams = new ArrayList<>();
			while (result.next()) {
				XeMayDTO xemay = new XeMayDTO();
				xemay.setMaXe(result.getString("maXe"));
				xemay.setTenXe(result.getString("tenXe"));
				xemay.setGiaXe(result.getDouble("giaXe"));
				xemay.setSoLuong(result.getInt("soLuong"));
				xemay.setLoaiXe(result.getString("loaiXe"));
				xemay.setImage(result.getString("image"));
				sanphams.add(xemay);
			}
			connect.Close();
			return sanphams;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<XeMayDTO> sortSmall() {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String orderBy = " giaXe  ASC";
			ResultSet result = this.connect.SelectOrderBY("tbl_xemay", orderBy);
			System.out.println(result);
			ArrayList<XeMayDTO> sanphams = new ArrayList<>();
			while (result.next()) {
				XeMayDTO xemay = new XeMayDTO();
				xemay.setMaXe(result.getString("maXe"));
				xemay.setTenXe(result.getString("tenXe"));
				xemay.setGiaXe(result.getDouble("giaXe"));
				xemay.setSoLuong(result.getInt("soLuong"));
				xemay.setLoaiXe(result.getString("loaiXe"));
				xemay.setImage(result.getString("image"));
				sanphams.add(xemay);
			}
			connect.Close();
			return sanphams;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean nhapSanPhamTuExcel(XeMayDTO sp) {
		try {
			String sql = "DELETE * FROM tbl_xemay; "
					+ "INSERT INTO tbl_xemay(maXe, tenXe, giaXe, soLuong, loaiXe, image) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pre = MySQLConnection.connect.prepareStatement(sql);
			pre.setString(1, sp.getMaXe());
			pre.setString(2, sp.getTenXe());
			pre.setDouble(3, sp.getGiaXe());
			pre.setInt(4, sp.getSoLuong());
			pre.setString(5, sp.getLoaiXe());
			pre.setString(6, sp.getMyImage());
			pre.execute();
			return true;
		} catch (SQLException e) {
		}
		return false;
	}
}
