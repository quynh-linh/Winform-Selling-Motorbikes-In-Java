package BUS;

import java.util.ArrayList;

import DAO.xemayDAO;
import DTO.XeMayDTO;

public class xeMayBUS {
	private ArrayList<XeMayDTO> list_xm;
	private xemayDAO xmDAL;

	public void docDB() throws Exception {
		list_xm = new ArrayList<>();
		xmDAL = new xemayDAO();
		list_xm = xmDAL.docDB();
	}

	public xeMayBUS() throws Exception {
		list_xm = new ArrayList<>();
		xmDAL = new xemayDAO();
		list_xm = xmDAL.docDB();
	}

	public ArrayList<XeMayDTO> getList_SP() {
		return list_xm;
	}

	public void setList_SP(ArrayList<XeMayDTO> list_xm) {
		this.list_xm = list_xm;
	}

	public boolean kiemTraKhoachinh(XeMayDTO hd) {
		for (XeMayDTO sanpham : list_xm) {
			if (sanpham.getMaXe().equals(hd.getMaXe() + "")) {
				System.out.println("Bị trùng");
				return true;
			}
		}
		return false;
	}

	public String getTenXe(String ma) {
		for (XeMayDTO loai : list_xm) {
			if (loai.getMaXe().equals(ma)) {
				return loai.getTenXe();
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
	public String them(XeMayDTO hd) throws Exception {
		if (!kiemTraKhoachinh(hd)) {
			if (xmDAL.them(hd)) {
				list_xm.add(hd);
				System.out.println("thêm sản phẩm");
				return "Thêm thành công";
			}
		}
		return "Thêm không thành công";
	}

	public String themEcxel(ArrayList<XeMayDTO> hd) throws Exception {
		for (XeMayDTO xeMayDTO : hd) {
			if (xmDAL.themEcxel(hd)) {
				list_xm.add(xeMayDTO);
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
	public String xoa(XeMayDTO hd) throws Exception {
		if (xmDAL.xoa(hd)) {
			// duyệt từng phẩn tử
			for (XeMayDTO sanpham : list_xm) {
				if (sanpham.getMaXe().equals(hd.getMaXe())) {
					list_xm.remove(sanpham);
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
	public String sua(XeMayDTO hd) throws Exception {
		if (xmDAL.sua(hd)) {
			// duyệt từng phẩn tử
			for (XeMayDTO xemay : list_xm) {
				if (xemay.getMaXe().equals(hd.getMaXe())) {
					xemay.setTenXe(hd.getTenXe());
					xemay.setGiaXe(hd.getGiaXe());
					xemay.setSoLuong(hd.getSoLuong());
					xemay.setLoaiXe(hd.getLoaiXe());
					return "Cập nhập thành công";
				}
			}
		}
		return "Cập nhập không thành công";
	}

	public boolean nhapSanPhamTuExcel(String maXe, String tenXe, String giaXe, String soLuong, String loaiXe,
			String image) {

		try {
			String[] loaiTmp = loaiXe.split(" - ");
			String maLoai = loaiTmp[0];
			double gia = Double.valueOf(giaXe);
			int sl = Integer.valueOf(soLuong);
//			donGia = donGia.replace(",", "");
			XeMayDTO sp = new XeMayDTO();
			sp.setMaXe(maXe);
			sp.setTenXe(tenXe);
			sp.setGiaXe(gia);
			sp.setImage(image);
			sp.setSoLuong(sl);
			sp.setLoaiXe(maLoai);
			System.out.println(sp);
			xmDAL.nhapSanPhamTuExcel(sp);
		} catch (Exception e) {
		}
		return false;
	}
}
