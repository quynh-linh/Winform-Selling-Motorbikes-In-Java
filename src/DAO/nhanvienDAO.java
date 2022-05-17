package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import DTO.NhanVienDTO;

public class nhanvienDAO {
	ConnectUnit connect;

	/**
	 * Lấy thông tin từ Database
	 */
	public ArrayList<NhanVienDTO> docDB(String condition, String orderBy) throws Exception {
		// kết nối CSDL
		connect = new ConnectUnit();
		ResultSet result = this.connect.Select("tbl_nhanvien", condition, orderBy);
		ArrayList<NhanVienDTO> list_nhanvien = new ArrayList<>();
		while (result.next()) {
			NhanVienDTO nv = new NhanVienDTO();
			nv.setMaNV(result.getString("maNV"));
			nv.setTenNV(result.getString("tenNV"));
			nv.setNgaySinh(result.getString("ngaySinh"));
			nv.setDiaChi(result.getString("diaChi"));
			nv.setSdt(result.getInt("sdt"));
			nv.setNgayLam(result.getString("ngayLam"));
			nv.setLuongNV(result.getDouble("luongNV"));
			nv.setGioiTinh(result.getString("gioiTinh"));
			list_nhanvien.add(nv);
		}
		connect.Close();
		return list_nhanvien;
	}

	public ArrayList<NhanVienDTO> docDB(String condition) throws Exception {
		return docDB(condition, null);
	}

	public ArrayList<NhanVienDTO> docDB() throws Exception {
		return docDB(null);
	}
	public ArrayList<NhanVienDTO> search(String data) {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String condition = " tenNV  LIKE '%" + data + "%'";
			ResultSet result = this.connect.Select("tbl_nhanvien", condition);
			System.out.println(result);
			ArrayList<NhanVienDTO> sanphams = new ArrayList<>();
			while (result.next()) {
				NhanVienDTO nv = new NhanVienDTO();
				nv.setMaNV(result.getString("maNV"));
				nv.setTenNV(result.getString("tenNV"));
				nv.setNgaySinh(result.getString("ngaySinh"));
				nv.setDiaChi(result.getString("diaChi"));
				nv.setSdt(result.getInt("sdt"));
				nv.setNgayLam(result.getString("ngayLam"));
				nv.setLuongNV(result.getDouble("luongNV"));
				nv.setGioiTinh(result.getString("gioiTinh"));
				sanphams.add(nv);
			}
			connect.Close();
			return sanphams;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<NhanVienDTO> sortHight() {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String orderBy = " luongNV  DESC";
			ResultSet result = this.connect.SelectOrderBY("tbl_nhanvien", orderBy);
			System.out.println(result);
			ArrayList<NhanVienDTO> sanphams = new ArrayList<>();
			while (result.next()) {
				NhanVienDTO nv = new NhanVienDTO();
				nv.setMaNV(result.getString("maNV"));
				nv.setTenNV(result.getString("tenNV"));
				nv.setNgaySinh(result.getString("ngaySinh"));
				nv.setDiaChi(result.getString("diaChi"));
				nv.setSdt(result.getInt("sdt"));
				nv.setNgayLam(result.getString("ngayLam"));
				nv.setLuongNV(result.getDouble("luongNV"));
				nv.setGioiTinh(result.getString("gioiTinh"));
				sanphams.add(nv);
			}
			connect.Close();
			return sanphams;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<NhanVienDTO> sortSmall() {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String orderBy = " luongNV  ASC";
			ResultSet result = this.connect.SelectOrderBY("tbl_nhanvien", orderBy);
			System.out.println(result);
			ArrayList<NhanVienDTO> sanphams = new ArrayList<>();
			while (result.next()) {
				NhanVienDTO nv = new NhanVienDTO();
				nv.setMaNV(result.getString("maNV"));
				nv.setTenNV(result.getString("tenNV"));
				nv.setNgaySinh(result.getString("ngaySinh"));
				nv.setDiaChi(result.getString("diaChi"));
				nv.setSdt(result.getInt("sdt"));
				nv.setNgayLam(result.getString("ngayLam"));
				nv.setLuongNV(result.getDouble("luongNV"));
				nv.setGioiTinh(result.getString("gioiTinh"));
				sanphams.add(nv);
			}
			connect.Close();
			return sanphams;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Tạo thêm 1 hdách hàng dựa theo đã có thông tin trước
	 * 
	 * @return true nếu thành công
	 */
	public Boolean them(NhanVienDTO hd) throws Exception {
		connect = new ConnectUnit();
		// tạo đối tượng truyền vào
		HashMap<String, Object> insertValues = new HashMap<>();
		insertValues.put("maNV", hd.getMaNV());
		insertValues.put("tenNV", hd.getTenNV());
		insertValues.put("ngaySinh", hd.getNgaySinh());
		insertValues.put("diaChi", hd.getDiaChi());
		insertValues.put("sdt", hd.getSdt());
		insertValues.put("ngayLam", hd.getNgayLam());
		insertValues.put("luongNV", hd.getLuongNV());
		insertValues.put("gioiTinh", hd.getGioiTinh());
		Boolean check = connect.Insert("tbl_nhanvien", insertValues);
		connect.Close();
		return check;
	}

	/**
	 * @param hd chuyền vào dữ liệu nhà cung cấp để xóa
	 * @return true nếu thành công
	 */
	public Boolean xoa(NhanVienDTO hd) throws Exception {
		connect = new ConnectUnit();
		String condition = " maNV  = '" + hd.getMaNV() + "'";
		Boolean check = connect.Delete("tbl_nhanvien", condition);
		connect.Close();
		return check;
	}

	/**
	 * @param hd truyền vào dữ liệu nhà cung cấp mới Sửa thông tin đăng nhập
	 * @return true nếu thành công
	 */
	public Boolean sua(NhanVienDTO hd) throws Exception {
		connect = new ConnectUnit();
		// tạo đối tượng truyền vào
		HashMap<String, Object> insertValues = new HashMap<>();
		insertValues.put("tenNV", hd.getTenNV());
		insertValues.put("ngaySinh	", hd.getNgaySinh());
		insertValues.put("diaChi", hd.getDiaChi());
		insertValues.put("sdt", hd.getSdt());
		insertValues.put("ngayLam", hd.getNgayLam());
		insertValues.put("luongNV", hd.getLuongNV());
		insertValues.put("gioiTinh	", hd.getGioiTinh());
		System.out.println(hd.toString());
		String condition = " maNV  = '" + hd.getMaNV() + "'";
		Boolean check = connect.Update("tbl_nhanvien", insertValues, condition);
		connect.Close();
		return check;
	}
}
