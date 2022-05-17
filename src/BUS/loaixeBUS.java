package BUS;

import java.util.ArrayList;

import DAO.loaixeDAO;
import DTO.LoaiXe;

public class loaixeBUS {
	private ArrayList<LoaiXe> list_lxs;
	private loaixeDAO lxDAL;

	public void docDB() throws Exception {
		list_lxs = new ArrayList<>();
		lxDAL = new loaixeDAO();
		list_lxs = lxDAL.docDB();
	}

	public loaixeBUS() throws Exception {
		list_lxs = new ArrayList<>();
		lxDAL = new loaixeDAO();
		list_lxs = lxDAL.docDB();
	}

	public ArrayList<LoaiXe> getList_XM() {
		return list_lxs;
	}

	public void setList_SP(ArrayList<LoaiXe> list_lxs) {
		this.list_lxs = list_lxs;
	}

	public boolean kiemTraKhoachinh(LoaiXe hd) {
		for (LoaiXe sanpham : list_lxs) {
			if (sanpham.getMaLoai().equals(hd.getMaLoai() + "")) {
				System.out.println("Bị trùng");
				return true;
			}
		}
		return false;
	}

	public String getTenLoai(String ma) {
		for (LoaiXe loai : list_lxs) {
			if (loai.getMaLoai().equals(ma)) {
				return loai.getMaLoai() + " - " + loai.getTenLoai();
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
	public String them(LoaiXe hd) throws Exception {
		if (!kiemTraKhoachinh(hd)) {
			if (lxDAL.them(hd)) {
				list_lxs.add(hd);
				System.out.println("thêm sản phẩm");
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
	public String xoa(LoaiXe hd) throws Exception {
		if (lxDAL.xoa(hd)) {
			// duyệt từng phẩn tử
			for (LoaiXe sanpham : list_lxs) {
				if (sanpham.getMaLoai().equals(hd.getMaLoai())) {
					list_lxs.remove(sanpham);
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
	public String sua(LoaiXe hd) throws Exception {
		if (lxDAL.sua(hd)) {
			// duyệt từng phẩn tử
			for (LoaiXe loaixe : list_lxs) {
				if (loaixe.getMaLoai().equals(hd.getMaLoai())) {
					loaixe.setTenLoai(hd.getTenLoai());
					return "Cập nhập thành công";
				}
			}
		}
		return "Cập nhập không thành công";
	}

	public int getNumbXeMay() {
		return list_lxs.size();
	}
}
