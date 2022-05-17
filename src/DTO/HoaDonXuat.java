package DTO;

public class HoaDonXuat {
	private String maPX;
	private String ngayXuat;
	private String maKH;
	private String maNV;
	private double tongTien;
	public HoaDonXuat() {
		
	}
	public HoaDonXuat(String maPX, String ngayXuat, String maKH, String maNV, double tongTien) {
	
		this.maPX = maPX;
		this.ngayXuat = ngayXuat;
		this.maKH = maKH;
		this.maNV = maNV;
		this.tongTien = tongTien;
	}
	public String getMaPX() {
		return maPX;
	}
	public void setMaPX(String maPX) {
		this.maPX = maPX;
	}
	public String getNgayXuat() {
		return ngayXuat;
	}
	public void setNgayXuat(String ngayXuat) {
		this.ngayXuat = ngayXuat;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	
	
}
