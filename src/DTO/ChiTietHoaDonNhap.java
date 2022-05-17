package DTO;

public class ChiTietHoaDonNhap {

	private float donGia;
	private float tongTien;
	private int soLuong;
	private String maXe;
	private String maPN;

	public ChiTietHoaDonNhap() {
		// TODO Auto-generated constructor stub
	}

	public ChiTietHoaDonNhap(float donGia, float tongTien, int soLuong, String maXe, String maPN) {
		this.donGia = donGia;
		this.tongTien = tongTien;
		this.soLuong = soLuong;
		this.maXe = maXe;
		this.maPN = maPN;
	}
	public String getMaXe() {
		return maXe;
	}

	public void setMaXe(String maXe) {
		this.maXe = maXe;
	}

	public String getMaPN() {
		return maPN;
	}

	public void setMaPN(String maPN) {
		this.maPN = maPN;
	}

	public String getXeMayDTO() {
		return maXe;
	}

	public void setXeMayDTO(String maXe) {
		this.maXe = maXe;
	}

	public float getDonGia() {
		return donGia;
	}

	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}

	public float getTongTien() {
		return tongTien;
	}

	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
}
