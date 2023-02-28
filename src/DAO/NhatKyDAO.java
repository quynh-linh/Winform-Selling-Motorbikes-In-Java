package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.ChiTieuDTO;
import DTO.NhanVienDTO;
import DTO.NhatKyDTO;

public class NhatKyDAO {

	public ArrayList<NhatKyDTO> docDB() throws Exception {
		MySQL mysql = new MySQL();
		String sql = "SELECT * FROM `nhatkychi`";
		PreparedStatement pre = MySQL.connect.prepareStatement(sql);
		ResultSet result = pre.executeQuery();
		ArrayList<NhatKyDTO> sanphams = new ArrayList<>();
		while (result.next()) {
			NhatKyDTO ct = new NhatKyDTO();
			ct.setId(result.getString("ID"));
			ct.setNgay(result.getString("Ngay"));
			ct.setMucChi(result.getFloat("MucChi"));
			ct.setSoTien(result.getFloat("SoTien"));
			ct.setGhiChu(result.getString("GhiChu"));
			sanphams.add(ct);
		}
		mysql.Close();
		return sanphams;
	}

	public boolean themNhatKy(NhatKyDTO nk) {
		try {
			MySQL mysql = new MySQL();
			String sql = "INSERT INTO `nhatkychi`(`ID`, `Ngay`, `MucChi`, `SoTien`, `GhiChu`) " + "VALUES (?,?,?,?,?)";
			PreparedStatement pre = mysql.connect.prepareStatement(sql);
			pre.setString(1, nk.getId());
			pre.setString(2, nk.getNgay());
			pre.setFloat(3, nk.getMucChi());
			pre.setFloat(4, nk.getSoTien());
			pre.setString(5, nk.getGhiChu());
			pre.execute();
			mysql.Close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
