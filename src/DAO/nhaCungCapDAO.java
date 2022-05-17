package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import DTO.NhaCungCap;


public class nhaCungCapDAO {
	ConnectUnit connect;

	/**
	 * Lấy thông tin từ Database
	 */
	public ArrayList<NhaCungCap> docDB(String condition, String orderBy) throws Exception {
		// kết nối CSDL
		connect = new ConnectUnit();

		ResultSet result = this.connect.Select("tbl_ncc", condition, orderBy);
		ArrayList<NhaCungCap> sanphams = new ArrayList<>();
		while (result.next()) {
			NhaCungCap ncc = new NhaCungCap();
			ncc.setMaNhaCC(result.getString("maNhaCC"));
			ncc.setTenNhaCC(result.getString("tenNhaCC"));
			ncc.setDiaChi(result.getString("diaChi"));
			ncc.setSdtNhaCC(result.getString("sdtNhaCC"));
			sanphams.add(ncc);
		}
		connect.Close();
		return sanphams;
	}

	public ArrayList<NhaCungCap> docDB(String condition) throws Exception {
		return docDB(condition, null);
	}

	public ArrayList<NhaCungCap> docDB() throws Exception {
		return docDB(null);
	}
	public ArrayList<NhaCungCap> search(String data) {
		try {
			// kết nối CSDL
			connect = new ConnectUnit();
			String condition = " tenNhaCC  LIKE '%" + data + "%'";
			ResultSet result = this.connect.Select("tbl_ncc", condition);
			System.out.println(result);
			ArrayList<NhaCungCap> sanphams = new ArrayList<>();
			while (result.next()) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setMaNhaCC(result.getString("maNhaCC"));
				ncc.setTenNhaCC(result.getString("tenNhaCC"));
				ncc.setDiaChi(result.getString("diaChi"));
				ncc.setSdtNhaCC(result.getString("sdtNhaCC"));
				sanphams.add(ncc);
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
	public Boolean them(NhaCungCap hd) throws Exception {
		connect = new ConnectUnit();
		// tạo đối tượng truyền vào
		HashMap<String, Object> insertValues = new HashMap<>();
		insertValues.put("maNhaCC", hd.getMaNhaCC());
		insertValues.put("tenNhaCC", hd.getTenNhaCC());
		insertValues.put("diaChi", hd.getDiaChi());
		insertValues.put("sdtNhaCC", hd.getSdtNhaCC());
		Boolean check = connect.Insert("tbl_ncc", insertValues);
		connect.Close();
		return check;
	}
	/**
	 * @param hd chuyền vào dữ liệu nhà cung cấp để xóa
	 * @return true nếu thành công
	 */
	public Boolean xoa(NhaCungCap hd) throws Exception {
		connect = new ConnectUnit();
		String condition = " maNhaCC = '" + hd.getMaNhaCC() + "'";
		Boolean check = connect.Delete("tbl_ncc", condition);
		connect.Close();
		return check;
	}
	/**
	 * @param hd truyền vào dữ liệu nhà cung cấp mới Sửa thông tin đăng nhập 
	 * @return true nếu thành công
	 */
	public Boolean sua(NhaCungCap hd) throws Exception {
		connect = new ConnectUnit();
		// tạo đối tượng truyền vào
		HashMap<String, Object> insertValues = new HashMap<>();
		insertValues.put("tenNhaCC", hd.getTenNhaCC());
		insertValues.put("diaChi", hd.getDiaChi());
		insertValues.put("sdtNhaCC", hd.getSdtNhaCC());
		System.out.println(hd.toString());
		String condition = " maNhaCC = '" + hd.getMaNhaCC() + "'";
		Boolean check = connect.Update("tbl_ncc", insertValues, condition);
		connect.Close();
		return check;
	}
}
