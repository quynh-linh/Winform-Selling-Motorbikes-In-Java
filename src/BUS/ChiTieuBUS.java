package BUS;

import java.util.ArrayList;

import DAO.ChiTieuDAO;
import DAO.xemayDAO;
import DTO.ChiTieuDTO;
import DTO.XeMayDTO;

public class ChiTieuBUS {
	private ArrayList<ChiTieuDTO> list_xm;
	private ChiTieuDAO xmDAL;
	public ChiTieuBUS() throws Exception {
		list_xm = new ArrayList<>();
		xmDAL = new ChiTieuDAO();
		list_xm = xmDAL.docDB();
	}
	public String xoaNVBUS(String maNV) {
		ChiTieuDAO ct = new ChiTieuDAO();
		if (ct.xoaChiTieu(maNV)) {
			return "Xóa thành công";
		} else {
			return "Xóa không thành công";
		}
	}
	public String suaNVBUS(ChiTieuDTO maNV) {	
		ChiTieuDAO ct = new ChiTieuDAO();
		if (ct.capNhap(maNV)) {
			return "Sửa thành công";
		} else {
			return "Sửa không thành công";
		}
	}
	public String timKiem(String maNV) {
		for (ChiTieuDTO sanpham : list_xm) {
			if (sanpham.getMaNV().equals(maNV+"")) {
				String sql = sanpham.getHo()+sanpham.getTen()+sanpham.getNgayLam()+sanpham.getNgaySinh();
				return sql;
			}
		}
		return null;
	}		
}
