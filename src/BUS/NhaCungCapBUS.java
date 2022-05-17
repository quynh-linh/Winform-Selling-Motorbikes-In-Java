package BUS;

import java.util.ArrayList;
import DAO.nhaCungCapDAO;
import DTO.NhaCungCap;


public class NhaCungCapBUS {
	private ArrayList<NhaCungCap> list_NCC;
	private nhaCungCapDAO nccDAL;

	public void docDB() throws Exception {
		list_NCC = new ArrayList<>();
		nccDAL = new nhaCungCapDAO();
		list_NCC = nccDAL.docDB();
	}

	public NhaCungCapBUS() throws Exception {
		list_NCC = new ArrayList<>();
		nccDAL = new nhaCungCapDAO();
		list_NCC = nccDAL.docDB();
	}

	public ArrayList<NhaCungCap> getList_NCC() {
		return list_NCC;
	}

	public void setList_SP(ArrayList<NhaCungCap> list_NCC) {
		this.list_NCC = list_NCC;
	}

	public String getTenNV(String ma) {
		for (NhaCungCap loai : list_NCC) {
			if (loai.getMaNhaCC().equals(ma)) {
				return loai.getTenNhaCC();
			}
		}
		return "";
	}
	public String getMaNcc(String tenncc) {
		for (NhaCungCap loai : list_NCC) {
			if (loai.getTenNhaCC().equals(tenncc)) {
				return loai.getMaNhaCC();
			}
		}
		return "";
	}
	public boolean kiemTraKhoachinh(NhaCungCap hd) {
		for (NhaCungCap sanpham : list_NCC) {
			if (sanpham.getMaNhaCC().equals(hd.getMaNhaCC() + "")) {
				System.out.println("Bị trùng");
				return true;
			}
		}
		return false;
	}

	public Boolean them(NhaCungCap hd) throws Exception {
		if (!kiemTraKhoachinh(hd)) {
			if (nccDAL.them(hd)) {
				list_NCC.add(hd);
				System.out.println("thêm sản phẩm");
			}
		}
		return false;
	}

	public String xoa(NhaCungCap hd) throws Exception {
		if (nccDAL.xoa(hd)) {
			// duyệt từng phẩn tử
			for (NhaCungCap sanpham : list_NCC) {
				if (sanpham.getMaNhaCC().equals(hd.getMaNhaCC())) {
					list_NCC.remove(sanpham);
					return "Xóa thành công";
				}
			}
		}
		return "Xóa thất bại";
	}

	
	public String sua(NhaCungCap hd) throws Exception {
		if (nccDAL.sua(hd)) {
			// duyệt từng phẩn tử
			for (NhaCungCap nhacungcap : list_NCC) {
				if (nhacungcap.getMaNhaCC().equals(hd.getMaNhaCC())) {
					nhacungcap.setTenNhaCC(hd.getTenNhaCC());
					nhacungcap.setDiaChi(hd.getDiaChi());
					nhacungcap.setSdtNhaCC(hd.getDiaChi());
					return "Cập nhập thành công";
				}
			}
		}
		return "Cập nhập không thành công";
	}

	public int getNumbNCC() {
		return list_NCC.size();
	}
}
