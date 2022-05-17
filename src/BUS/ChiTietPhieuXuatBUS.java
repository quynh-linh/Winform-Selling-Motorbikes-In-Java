package BUS;

import java.util.ArrayList;

import DAO.ChiTietPhieuXuatDAO;
import DTO.ChiTietHoaDonXuat;


public class ChiTietPhieuXuatBUS {
	private ArrayList<ChiTietHoaDonXuat> list_cthdx;
	private ChiTietPhieuXuatDAO ctpnDAL;

	public void docDB() throws Exception {
		list_cthdx = new ArrayList<>();
		ctpnDAL = new ChiTietPhieuXuatDAO();
		list_cthdx = ctpnDAL.docDB();
	}

	public ChiTietPhieuXuatBUS() throws Exception {
		list_cthdx = new ArrayList<>();
		ctpnDAL = new ChiTietPhieuXuatDAO();
		list_cthdx = ctpnDAL.docDB();
	}

	public ArrayList<ChiTietHoaDonXuat> getList_SP() {
		return list_cthdx;
	}

	public void setList_SP(ArrayList<ChiTietHoaDonXuat> list_cthdx) {
		this.list_cthdx = list_cthdx;
	}

	public int getNumbPN() {
		return list_cthdx.size();
	}
	public boolean luuCTPhieuXuat(ChiTietHoaDonXuat ctpx) throws Exception {
        return ctpnDAL.them(ctpx);
    }
}

