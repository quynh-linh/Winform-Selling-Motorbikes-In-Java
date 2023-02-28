package BUS;

import java.util.ArrayList;

import DAO.NhatKyDAO;
import DTO.NhatKyDTO;

public class NhatKyBUS {
	private ArrayList<NhatKyDTO> listNK;
	private NhatKyDAO nkdao;
	public NhatKyBUS() throws Exception {
		listNK = new ArrayList<NhatKyDTO>();
		nkdao = new NhatKyDAO();
		listNK = nkdao.docDB();
	}
	public float tongSoTien() {
		float tong = 0;
		for (NhatKyDTO nhatKyDTO : listNK) {
			tong = tong + nhatKyDTO.getSoTien();
		}
		return tong;
	}
	public String themNhatKy(NhatKyDTO nk ) {
		if (nkdao.themNhatKy(nk)) {
			return "Thêm thành công";
		} else {
			return "Thêm không thành công ";
		}
	}
}
