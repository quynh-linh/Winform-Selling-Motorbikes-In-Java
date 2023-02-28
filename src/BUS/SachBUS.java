package BUS;

import java.util.ArrayList;

import DAO.SachDAO;
import DTO.SACHDTO;
import DTO.XeMayDTO;

public class SachBUS {
	private ArrayList<SACHDTO> list_xm;
	private SachDAO xmDAL;
	public SachBUS() throws Exception {
		list_xm = new ArrayList<>();
		xmDAL = new SachDAO();
		list_xm = xmDAL.docDB();
	}
	public boolean KiemtrakhoaChinh(SACHDTO sach) {
		for (SACHDTO sanpham : list_xm) {
			if (sanpham.getMaSach().equals(sach.getMaSach()+ "")) {
				return false;
			}
		}
		return true;
	}
	public String themSach(SACHDTO sach) {
		SachDAO dao = new SachDAO();
		if (KiemtrakhoaChinh(sach)) {
			if (dao.themSach(sach)) {
				return "Thêm thành công";
			} else {
				return "Thêm không thành công";
			}
		} else {
			return "Bị trùng khóa chính";
		}
		
	}
}
