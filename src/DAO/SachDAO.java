package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.ChiTieuDTO;
import DTO.SACHDTO;

public class SachDAO {
	public ArrayList<SACHDTO> docDB() throws Exception {
		// kết nối CSDL
		MySQL mysql = new MySQL();
		String sql = "SELECT * FROM `tbl_sach`";
		PreparedStatement pre = MySQL.connect.prepareStatement(sql);
		ResultSet result = pre.executeQuery() ;
		ArrayList<SACHDTO> sanphams = new ArrayList<>();
		while (result.next()) {
			SACHDTO ct = new SACHDTO();
			ct.setMaSach(result.getString("MASACH"));
			ct.setTenSach(result.getString("TENSACH"));
			ct.setSoluong(result.getInt("SOLUONG"));
			ct.setGia(result.getFloat("DONGIA"));
			sanphams.add(ct);
		}
		mysql.Close();
		return sanphams;
	} 
	public boolean themSach (SACHDTO sach) {
		try {
			MySQL mysql = new MySQL();
			String sql = "INSERT INTO `tbl_sach`(`MASACH`, `TENSACH`, `SOLUONG`, `DONGIA`) "
					+ "VALUES (?,?,?,?)";
			PreparedStatement sta = mysql.connect.prepareStatement(sql);
			sta.setString(1, sach.getMaSach());
			sta.setString(2, sach.getTenSach());
			sta.setInt(3, sach.getSoluong());
			sta.setFloat(4, sach.getGia());
			sta.execute();
			mysql.Close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
