package BUS;

import DAO.ThongKeDAO;
import DTO.ThongKeDTO;

public class ThongKeBUS {
	public ThongKeDAO thongKeDAO = new ThongKeDAO();

	public ThongKeDTO thongKe(int nam) {
		return thongKeDAO.getThongKe(nam);
	}
	public int getTongDoanhThu() {
		return thongKeDAO.getTongDoanhThu();
	}
	public int getTongNhanVien() {
		return thongKeDAO.getSoLuongNhanVien();
	}
	public int getTongXeMay() {
		return thongKeDAO.getTongSoLuongSP();
	}
	public double getDoanhThuThang(String thang, int nam) {
		return thongKeDAO.getDoanhThuThang(thang, nam);
	}
}
