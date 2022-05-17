package BUS;

import java.util.ArrayList;

import DAO.PhieuNhapDAO;
import DTO.HoaDonNhap;

public class PhieuNhapBUS {
	private ArrayList<HoaDonNhap> list_hdn;
	private PhieuNhapDAO pnDAL;

	public void docDB() throws Exception {
		list_hdn = new ArrayList<>();
		pnDAL = new PhieuNhapDAO();
		list_hdn = pnDAL.docDB();
	}

	public PhieuNhapBUS() throws Exception {
		list_hdn = new ArrayList<>();
		pnDAL = new PhieuNhapDAO();
		list_hdn = pnDAL.docDB();
	}

	public ArrayList<HoaDonNhap> getList_SP() {
		return list_hdn;
	}

	public void setList_SP(ArrayList<HoaDonNhap> list_nv) {
		this.list_hdn = list_nv;
	}

	public boolean kiemTraKhoachinh(HoaDonNhap hd) {
		for (HoaDonNhap nhanvien : list_hdn) {
			if (nhanvien.getMaPN().equals(hd.getMaNV() + "")) {
				System.out.println("Bị trùng");
				return true;
			}
		}
		return false;
	}

	public int getNumbPN() {
		return list_hdn.size();
	}
	// -----------------------------------------------------------------------------------------------
	/**
	 * thêm 1sản phẩm vào danh sách và database
	 * 
	 * @return true nếu thành công
	 * @throws Exception 
	 */
	public boolean themPhieuNhap(String maPn , String ngayNhap , String nhaCungCap, String nhanVien, double tongTien) throws Exception {
        HoaDonNhap pn = new HoaDonNhap();
        pn.setMaPN(maPn);
        pn.setNgayNhap(ngayNhap);
        pn.setMaNCC(nhaCungCap);
        pn.setMaNV(nhanVien);
        pn.setTongTien(tongTien);
        return pnDAL.them(pn);
    }
	/**
	 * xóa 1sản phẩm hdỏi danh sách và database
	 * 
	 * @return true nếu thành công
	 */
	public String xoa(HoaDonNhap hd) throws Exception {
		if (pnDAL.xoa(hd)) {
			// duyệt từng phẩn tử
			for (HoaDonNhap pn : list_hdn) {
				if (pn.getMaPN().equals(hd.getMaPN())) {
					list_hdn.remove(pn);
					return "Xóa thành công";
				}
			}
		}
		return "Xóa thất bại";
	}
}
