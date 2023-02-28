package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.ChiTieuDTO;
import DTO.XeMayDTO;

public class ChiTieuDAO {
	public ArrayList<ChiTieuDTO> docDB() throws Exception {
		// kết nối CSDL
		MySQL mysql = new MySQL();
		String sql = "SELECT * FROM `tbl_chitueu`";
		PreparedStatement pre = MySQL.connect.prepareStatement(sql);
		ResultSet result = pre.executeQuery() ;
		ArrayList<ChiTieuDTO> sanphams = new ArrayList<>();
		while (result.next()) {
			ChiTieuDTO ct = new ChiTieuDTO();
			ct.setMaNV(result.getString("maNV"));
			ct.setHo(result.getString("ho"));
			ct.setTen(result.getString("ten"));
			ct.setNgaySinh(result.getString("ngaySinh"));
			ct.setNgayLam(result.getString("ngayBatDauLam"));
			sanphams.add(ct);
		}
		mysql.Close();
		return sanphams;
	} 
	public boolean xoaChiTieu(String maNV) {
		try {
			MySQL mysql = new MySQL();
			String sql = "DELETE FROM `tbl_chitueu` WHERE maNV ='" + maNV + "'";
			Statement pre = MySQL.connect.createStatement();
			pre.execute(sql);
			mysql.Close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return false;
	}
	public boolean capNhap(ChiTieuDTO ct) {
		try {
			MySQL mysql = new MySQL();
			String sql = "UPDATE `tbl_chitueu` SET "
					+ "ho= ?,"
					+ "ten= ?,"
					+ "ngaySinh= ?,"
					+ "ngayBatDauLam= ? "
					+ "WHERE maNV = ?";
			PreparedStatement pre = mysql.connect.prepareStatement(sql);
			pre.setString(1, ct.getHo());
			pre.setString(2, ct.getTen());
			pre.setString(3, ct.getNgaySinh());
			pre.setString(4, ct.getNgayLam());
			pre.setString(5, ct.getMaNV());
			pre.execute();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
}
