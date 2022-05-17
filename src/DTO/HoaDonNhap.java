package DTO;

public class HoaDonNhap {
	private String maPN;
	private String ngayNhap;
	private String maNV;
	private String maNCC;
	private Double tongTien;
	
	public HoaDonNhap(String maPN,String ngayNhap,String maNV, String maNCC, Double tongTien) {
		this.maPN = maPN;
		this.ngayNhap = ngayNhap;
		this.maNV = maNV;
		this.maNCC = maNCC;
		this.tongTien = tongTien;
	}

	
	public HoaDonNhap() {
		// TODO Auto-generated constructor stub
	}


	public String getMaPN() {
		return maPN;
	}


	public void setMaPN(String maPN) {
		this.maPN = maPN;
	}


	public String getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(String ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public Double getTongTien() {
		return tongTien;
	}

	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}

	@Override
	public String toString() {
		return "HoaDonNhap [maPN=" + maPN + ", ngayNhap=" + ngayNhap + ", maNV=" + maNV + ", maNCC=" + maNCC
				+ ", tongTien=" + tongTien + "]";
	}

}
