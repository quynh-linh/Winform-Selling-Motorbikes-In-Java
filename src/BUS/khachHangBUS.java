package BUS;

import java.util.ArrayList;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;

public class khachHangBUS {

	private ArrayList<KhachHangDTO> list_kh;
	private KhachHangDAO nvDAL;

	public void docDB() throws Exception {
		list_kh = new ArrayList<>();
		nvDAL = new KhachHangDAO();
		list_kh = nvDAL.docDB();
	}

	public khachHangBUS() throws Exception {
		list_kh = new ArrayList<>();
		nvDAL = new KhachHangDAO();
		list_kh = nvDAL.docDB();
	}

	public ArrayList<KhachHangDTO> getList_kh() {
		return list_kh;
	}

	public void setList_SP(ArrayList<KhachHangDTO> list_kh) {
		this.list_kh = list_kh;
	}

	public boolean kiemTraKhoachinh(KhachHangDTO hd) {
		for (KhachHangDTO nhanvien : list_kh) {
			if (nhanvien.getMaKH().equals(hd.getMaKH() + "")) {
				System.out.println("Bị trùng");
				return true;
			}
		}
		return false;
	}

	public int getNumbKH() {
		return list_kh.size();
	}

	public String getTenNV(String ma) {
		for (KhachHangDTO loai : list_kh) {
			if (loai.getMaKH().equals(ma)) {
				return loai.getTenKH();
			}
		}
		return "";
	}

	// -----------------------------------------------------------------------------------------------
	/**
	 * thêm 1sản phẩm vào danh sách và database
	 * 
	 * @return true nếu thành công
	 */
	public String them(KhachHangDTO hd) throws Exception {
		if (!kiemTraKhoachinh(hd)) {
			if (nvDAL.them(hd)) {
				list_kh.add(hd);
				System.out.println("Thêm sản phẩm");
				return "Thêm thành công";
			}
		}
		return "Thêm không thành công";
	}
	/**
	 * xóa 1sản phẩm hdỏi danh sách và database
	 * 
	 * @return true nếu thành công
	 */
	public String xoa(KhachHangDTO hd) throws Exception {
		if (nvDAL.xoa(hd)) {
			// duyệt từng phẩn tử
			for (KhachHangDTO nhanvien : list_kh) {
				if (nhanvien.getMaKH().equals(hd.getMaKH())) {
					list_kh.remove(nhanvien);
					return "Xóa thành công";
				}
			}
		}
		return "Xóa thất bại";
	}

	/**
	 * sửa thông tin của 1sản phẩm <br>
	 * - Trừ thông tin đăng nhập củasản phẩm đó
	 * 
	 * @return true nếu thực hiện thành công
	 */
	public String sua(KhachHangDTO hd) throws Exception {
		if (nvDAL.sua(hd)) {
			// duyệt từng phẩn tử
			for (KhachHangDTO kh : list_kh) {
				if (kh.getMaKH().equals(hd.getMaKH())) {
					kh.setTenKH(null);
					kh.setDiaChi(hd.getDiaChi());
					kh.setSDT(hd.getSDT());
					kh.setGioiTinh(hd.getGioiTinh());
					return "Cập nhập thành công";
				}
			}
		}
		return "Cập nhập không thành công";
	}

	public String getMaKH(String tenncc) {
		for (KhachHangDTO loai : list_kh) {
			if (loai.getTenKH().equals(tenncc)) {
				return loai.getMaKH();
			}
		}
		return "";
	}

}
