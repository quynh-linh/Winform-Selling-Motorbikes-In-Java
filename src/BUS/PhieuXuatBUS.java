package BUS;

import java.util.ArrayList;

import DAO.PhieuXuatDAO;
import DTO.HoaDonXuat;

public class PhieuXuatBUS {

	private ArrayList<HoaDonXuat> list_hdn;
	private PhieuXuatDAO pnDAL;

	public void docDB() throws Exception {
		list_hdn = new ArrayList<>();
		pnDAL = new PhieuXuatDAO();
		list_hdn = pnDAL.docDB();
	}

	public PhieuXuatBUS() throws Exception {
		list_hdn = new ArrayList<>();
		pnDAL = new PhieuXuatDAO();
		list_hdn = pnDAL.docDB();
	}

	public ArrayList<HoaDonXuat> getList_SP() {
		return list_hdn;
	}

	public void setList_SP(ArrayList<HoaDonXuat> list_nv) {
		this.list_hdn = list_nv;
	}

	public boolean kiemTraKhoachinh(HoaDonXuat hd) {
		for (HoaDonXuat nhanvien : list_hdn) {
			if (nhanvien.getMaPX().equals(hd.getMaNV() + "")) {
				System.out.println("Bị trùng");
				return false;
			}
		}
		return true;
	}
	public String kiemTraKhoachinh1(HoaDonXuat hd) {
		for (HoaDonXuat nhanvien : list_hdn) {
			if (nhanvien.getMaPX().equals(hd.getMaNV() + "")) {
				System.out.println("Bị trùng");
				return "Trùng mã phiếu xuất";
			}
		}
		return "Không trùng mã phiếu xuất";
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
	public String themPhieuXuat(String maPX , String ngayXuat , String khachHang, String nhanVien, double tongTien) throws Exception {
		HoaDonXuat pn = new HoaDonXuat();
        pn.setMaPX(maPX);
        pn.setNgayXuat(ngayXuat);
        pn.setMaKH(khachHang);
        pn.setMaNV(nhanVien);
        pn.setTongTien(tongTien);
        if(!kiemTraKhoachinh(pn)) {
        	pnDAL.them(pn);
        	return "Thêm phiếu xuất thành công" ;
        } else {
        	return "Bị trùng mã phiếu xuất";
        }
//        return "NUll";
    }
	public String xoa(HoaDonXuat hd) throws Exception {
		if (pnDAL.xoa(hd)) {
			// duyệt từng phẩn tử
			for (HoaDonXuat pn : list_hdn) {
				if (pn.getMaPX().equals(hd.getMaPX())) {
					list_hdn.remove(pn);
					return "Xóa thành công";
				}
			}
		}
		return "Xóa thất bại";
	}

}
