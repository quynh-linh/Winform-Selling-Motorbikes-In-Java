package BUS;

import java.util.ArrayList;

import DAO.nhanvienDAO;
import DTO.NhanVienDTO;

public class NhanVienBUS {
	private ArrayList<NhanVienDTO> list_nv;
	private nhanvienDAO nvDAL;
	public void docDB() throws Exception {
		list_nv = new ArrayList<>();
		nvDAL = new nhanvienDAO();
		list_nv = nvDAL.docDB();
	}
	public NhanVienBUS() throws Exception {
		list_nv = new ArrayList<>();
		nvDAL = new nhanvienDAO();
		list_nv = nvDAL.docDB();
	}
	public ArrayList<NhanVienDTO> getList_SP() {
		return list_nv;
	}

	public void setList_SP(ArrayList<NhanVienDTO> list_nv) {
		this.list_nv = list_nv;
	}
	public boolean kiemTraKhoachinh(NhanVienDTO hd) {
		for (NhanVienDTO nhanvien : list_nv) {
			if (nhanvien.getMaNV().equals(hd.getMaNV() + "")) {
				System.out.println("Bị trùng");
				return true;
			}
		}
		return false;
	}
	public int getNumbNV() {
		return list_nv.size();
	}
	public String getTenNV(String ma) {
		for (NhanVienDTO loai : list_nv) {
			if (loai.getMaNV().equals(ma)) {
				return loai.getTenNV();
			}
		}
		return "";
	}
	public String getMaNV(String tenNV) {
		for (NhanVienDTO loai : list_nv) {
			if (loai.getTenNV().equals(tenNV)) {
				return loai.getMaNV();
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
	public String them(NhanVienDTO hd) throws Exception {
		if (!kiemTraKhoachinh(hd)) {
			if (nvDAL.them(hd)) {
				list_nv.add(hd);
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
	public String xoa(NhanVienDTO hd) throws Exception {
		if (nvDAL.xoa(hd)) {
			// duyệt từng phẩn tử
			for (NhanVienDTO nhanvien : list_nv) {
				if (nhanvien.getMaNV().equals(hd.getMaNV())) {
					list_nv.remove(nhanvien);
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
	public String sua(NhanVienDTO hd) throws Exception {
		if (nvDAL.sua(hd)) {
			// duyệt từng phẩn tử
			for (NhanVienDTO nhanvien : list_nv) {
				if (nhanvien.getMaNV().equals(hd.getMaNV())) {
					nhanvien.setTenNV(hd.getTenNV());
					nhanvien.setNgaySinh(hd.getNgaySinh());
					nhanvien.setDiaChi(hd.getDiaChi());
					nhanvien.setSdt(hd.getSdt());
					nhanvien.setNgayLam(hd.getNgayLam());
					nhanvien.setLuongNV(hd.getLuongNV());
					nhanvien.setGioiTinh(hd.getGioiTinh());
					return "Cập nhập thành công";
				}
			}
		}
		return "Cập nhập không thành công";
	}
}
