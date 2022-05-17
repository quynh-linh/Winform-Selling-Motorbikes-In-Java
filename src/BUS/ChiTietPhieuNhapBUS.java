package BUS;

import java.util.ArrayList;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietHoaDonNhap;


public class ChiTietPhieuNhapBUS {
	private ArrayList<ChiTietHoaDonNhap> list_cthdn;
	private ChiTietPhieuNhapDAO ctpnDAL;

	public void docDB() throws Exception {
		list_cthdn = new ArrayList<>();
		ctpnDAL = new ChiTietPhieuNhapDAO();
		list_cthdn = ctpnDAL.docDB();
	}

	public ChiTietPhieuNhapBUS() throws Exception {
		list_cthdn = new ArrayList<>();
		ctpnDAL = new ChiTietPhieuNhapDAO();
		list_cthdn = ctpnDAL.docDB();
	}

	public ArrayList<ChiTietHoaDonNhap> getList_SP() {
		return list_cthdn;
	}

	public void setList_SP(ArrayList<ChiTietHoaDonNhap> list_cthdn) {
		this.list_cthdn = list_cthdn;
	}

	public int getNumbPN() {
		return list_cthdn.size();
	}
	public boolean luuCTPhieuNhap(ChiTietHoaDonNhap ctpn) throws Exception {
        return ctpnDAL.them(ctpn);
    }
}
