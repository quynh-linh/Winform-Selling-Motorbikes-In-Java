package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;


import DTO.ChiTietHoaDonNhap;

public class ChiTietPhieuNhapDAO {
	ConnectUnit connect;
	 /**
     * Lấy thông tin từ Database
     */
    public ArrayList<ChiTietHoaDonNhap> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new ConnectUnit();
        ResultSet result = this.connect.Select("tbl_chitietpn", condition, orderBy);
        ArrayList<ChiTietHoaDonNhap> sanphams = new ArrayList<>();
        while (result.next() ) {
        	ChiTietHoaDonNhap ct = new ChiTietHoaDonNhap();
        	ct.setDonGia(result.getFloat("donGia"));
        	ct.setTongTien(result.getFloat("tongTien"));
        	ct.setSoLuong(result.getInt("soLuong"));
        	ct.setMaXe(result.getString("maXe"));
        	ct.setMaPN(result.getString("maPN"));
            sanphams.add(ct);
        }
        connect.Close();
        return sanphams;
    }
    public ArrayList<ChiTietHoaDonNhap> docDB1(String condition) throws Exception {
        // kết nối CSDL
        connect = new ConnectUnit();
        String cd = " maPN  = '" + condition  + "'";
        ResultSet result = this.connect.Select("tbl_chitietpn", cd);
        ArrayList<ChiTietHoaDonNhap> sanphams = new ArrayList<>();
        while ( result.next() ) {
        	ChiTietHoaDonNhap ct = new ChiTietHoaDonNhap();
        	ct.setDonGia(result.getFloat("donGia"));
        	ct.setTongTien(result.getFloat("tongTien"));
        	ct.setSoLuong(result.getInt("soLuong"));
        	ct.setMaXe(result.getString("maXe"));
        	ct.setMaPN(result.getString("maPN"));
            sanphams.add(ct);
        }
        connect.Close();
        return sanphams;
    }
    public ArrayList<ChiTietHoaDonNhap> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    public ArrayList<ChiTietHoaDonNhap> docDB() throws Exception {
        return docDB1(null);
    }
    public Boolean them(ChiTietHoaDonNhap hd) throws Exception {
        connect = new ConnectUnit();
        String sqlUpdateSP = "UPDATE tbl_xemay SET soLuong = soLuong + ? WHERE maXe  = ?";
        PreparedStatement pre = MySQLConnection.connect.prepareCall(sqlUpdateSP);
        System.out.println(pre);
        pre.setInt(1, hd.getSoLuong());
        pre.setString(2, hd.getMaXe());
        pre.executeUpdate();
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("donGia", hd.getDonGia());
        insertValues.put("tongTien", hd.getTongTien());
        insertValues.put("soLuong", hd.getSoLuong());
        insertValues.put("maXe", hd.getMaXe());
        insertValues.put("maPN", hd.getMaPN());
        Boolean check_them = connect.Insert("tbl_chitietpn", insertValues);
        connect.Close();
        return check_them;	
    }
}
