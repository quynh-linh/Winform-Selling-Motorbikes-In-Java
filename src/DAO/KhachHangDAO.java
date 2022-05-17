package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.KhachHangDTO;


public class KhachHangDAO {
	ConnectUnit connect;

	/**
	 * Lấy thông tin từ Database
	 */
	public ArrayList<KhachHangDTO> docDB(String condition, String orderBy) throws Exception {
		// kết nối CSDL
		connect = new ConnectUnit();
		ResultSet result = this.connect.Select("tbl_khachhang", condition, orderBy);
		ArrayList<KhachHangDTO> list_khachhang = new ArrayList<>();
		while (result.next()) {
			KhachHangDTO kh = new KhachHangDTO();
			kh.setMaKH(result.getString("maKH"));
			kh.setTenKH(result.getString("tenKH"));
		
			kh.setDiaChi(result.getString("diaChi"));
			kh.setSDT(result.getInt("SDT"));
			
			kh.setGioiTinh(result.getString("gioiTinh"));
			list_khachhang.add(kh);
		}
		connect.Close();
		return list_khachhang;
	}

	public ArrayList<KhachHangDTO> docDB(String condition) throws Exception {
		return docDB(condition, null);
	}

	public ArrayList<KhachHangDTO> docDB() throws Exception {
		return docDB(null);
	}

	/**
	 * Tạo thêm 1 hdách hàng dựa theo đã có thông tin trước
	 * 
	 * @return true nếu thành công
	 */
	public Boolean them(KhachHangDTO hd) throws Exception {
		connect = new ConnectUnit();
		// tạo đối tượng truyền vào
		HashMap<String, Object> insertValues = new HashMap<>();
		insertValues.put("maKH", hd.getMaKH());
		insertValues.put("tenKH", hd.getTenKH());
		insertValues.put("diaChi", hd.getDiaChi());
		insertValues.put("SDT", hd.getSDT());
		insertValues.put("gioiTinh", hd.getGioiTinh());
		Boolean check = connect.Insert("tbl_khachhang", insertValues);
		connect.Close();
		return check;
	}

	/**
	 * @param hd chuyền vào dữ liệu nhà cung cấp để xóa
	 * @return true nếu thành công
	 */
	public Boolean xoa(KhachHangDTO hd) throws Exception {
		connect = new ConnectUnit();
		String condition = " maKH  = '" + hd.getMaKH() + "'";
		Boolean check = connect.Delete("tbl_khachhang", condition);
		connect.Close();
		return check;
	}

	/**
	 * @param hd truyền vào dữ liệu nhà cung cấp mới Sửa thông tin đăng nhập
	 * @return true nếu thành công
	 */
	public Boolean sua(KhachHangDTO hd) throws Exception {
		connect = new ConnectUnit();
		// tạo đối tượng truyền vào
		HashMap<String, Object> insertValues = new HashMap<>();
		insertValues.put("tenKH", hd.getTenKH());
		insertValues.put("diaChi", hd.getDiaChi());
		insertValues.put("SDT", hd.getSDT());
		insertValues.put("gioiTinh	", hd.getGioiTinh());
		System.out.println(hd.toString());
		String condition = " maKH  = '" + hd.getMaKH() + "'";
		Boolean check = connect.Update("tbl_khachhang", insertValues, condition);
		connect.Close();
		return check;
	}

}
