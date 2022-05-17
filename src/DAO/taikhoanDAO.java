package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.adminDTO;

public class taikhoanDAO {
	ConnectUnit connect;
	/**
	 * Lấy thông tin từ Database
	 */
	public ArrayList<adminDTO> docDB(String condition, String orderBy) throws Exception {
		// kết nối CSDL
		connect = new ConnectUnit();
		ResultSet result = this.connect.Select("tbl_admin", condition, orderBy);
		ArrayList<adminDTO> admin = new ArrayList<>();
		while (result.next()) {
			adminDTO ad = new adminDTO();
			ad.setAdminId(result.getString("adminId"));
			ad.setAdminName(result.getString("adminName"));
			ad.setAdminUser(result.getString("adminUser"));
			ad.setAdminPass(result.getString("adminPass"));
			admin.add(ad);
		}
		connect.Close();
		return admin;
	}
	public ArrayList<adminDTO> docDB(String condition) throws Exception {
		return docDB(condition, null);
	}

	public ArrayList<adminDTO> docDB() throws Exception {
		return docDB(null);
	}
}
