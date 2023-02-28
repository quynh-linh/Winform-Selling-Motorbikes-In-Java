package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import DTO.LoaiXe;


public class loaixeDAO {
	ConnectUnit connect;
	/**
	 * Lấy thông tin từ Database
	 */
	public ArrayList<LoaiXe> docDB(String condition, String orderBy) throws Exception {
		// kết nối CSDL
		connect = new ConnectUnit();

		ResultSet result = this.connect.Select("tbl_loaixe", condition, orderBy);
		ArrayList<LoaiXe> loaixes = new ArrayList<>();
		while (result.next()) {
			LoaiXe lx = new LoaiXe();
			lx.setMaLoai(result.getString("maLoai"));
			lx.setTenLoai(result.getString("tenLoai"));
			loaixes.add(lx);
		}
		connect.Close();
		return loaixes;
	}
	public ArrayList<LoaiXe> docDB(String condition) throws Exception {
		return docDB(condition, null);
	}

	public ArrayList<LoaiXe> docDB() throws Exception {
		return docDB(null);
	}

	public ArrayList<LoaiXe> search(String data) {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String condition = " tenLoai  LIKE '%" + data + "%'";
			ResultSet result = this.connect.Select("tbl_loaixe", condition);
			System.out.println(result);
			ArrayList<LoaiXe> sanphams = new ArrayList<>();
			while (result.next()) {
				LoaiXe lx = new LoaiXe();
				lx.setMaLoai(result.getString("maLoai"));
				lx.setTenLoai(result.getString("tenLoai"));
				sanphams.add(lx);
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
	 * Tạo thêm 1 nhà cung cấp dựa theo đã có thông tin trước
	 * 
	 * @return true nếu thành công
	 */
	public Boolean them(LoaiXe hd) throws Exception {
		connect = new ConnectUnit();
		// tạo đối tượng truyền vào
		HashMap<String, Object> insertValues = new HashMap<>();
		insertValues.put("maLoai", hd.getMaLoai());
		insertValues.put("tenLoai", hd.getTenLoai());
		Boolean check = connect.Insert("tbl_loaixe", insertValues);
		connect.Close();
		return check;
	}
	/**
	 * @param hd chuyền vào dữ liệu nhà cung cấp để xóa
	 * @return true nếu thành công
	 */
	public Boolean xoa(LoaiXe hd) throws Exception {
		connect = new ConnectUnit();
		String condition = " maLoai = '" + hd.getMaLoai() + "'";
		Boolean check = connect.Delete("tbl_loaixe", condition);
		connect.Close();
		return check;
	}
	/**
	 * @param hd truyền vào dữ liệu nhà cung cấp mới Sửa thông tin đăng nhập 
	 * @return true nếu thành công
	 */
	public Boolean sua(LoaiXe hd) throws Exception {
		connect = new ConnectUnit();
		// tạo đối tượng truyền vào
		HashMap<String, Object> insertValues = new HashMap<>();
		insertValues.put("tenLoai", hd.getTenLoai());
		String condition = " maLoai  = '" + hd.getMaLoai() + "'";
		Boolean check = connect.Update("tbl_loaixe", insertValues, condition);
		connect.Close();
		return check;
	}
}
