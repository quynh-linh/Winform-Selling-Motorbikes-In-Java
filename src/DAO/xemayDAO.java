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
	public ArrayList<XeMayDTO> searchGiaXe(String giaMIN , String giaMAX) {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String condition = " giaXe BETWEEN '" + giaMIN + "' AND '" + giaMAX + "' ";
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
	public ArrayList<XeMayDTO> searchLoaiXe(String maLoai) {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String condition = "loaiXe = '" + maLoai + "'";
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
	public ArrayList<XeMayDTO> searchNangCao(String name , String giaMIn , String giaMAX , String maLoai) {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String condition = "( giaXe BETWEEN '" + giaMIn + "' AND '" + giaMAX + "' ) AND (tenXe LIKE '%" + name + "%') \r\n"
					+ "AND (loaiXe = '" + maLoai + "')";
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
			// xóa dữ liệu trước khi nhập từ file excel
			String condition = " maXe  = '" + sp.getMaXe() + "'";
			Boolean check1 = connect.Delete("tbl_xemay", condition);
			// sau khi xóa sẽ thêm dữ liệu từ file excel vào CSDL
			HashMap<String, Object> insertValues = new HashMap<>();
			insertValues.put("maXe", sp.getMaXe());
			insertValues.put("tenXe", sp.getTenXe());
			insertValues.put("giaXe", sp.getGiaXe());
			insertValues.put("soLuong", sp.getSoLuong());
			insertValues.put("loaiXe", sp.getLoaiXe());
			insertValues.put("image", sp.getMyImage());
			Boolean check2 = connect.Insert("tbl_xemay", insertValues);
			return true;
		} catch (SQLException e) {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
