package DTO;


public class ChiTietHoaDonXuat{
	private String maPX;
	private Double donGia;
	private int soLuong;
	private String maLoai;
	private String maXe;
	private Double tongTien;
	public ChiTietHoaDonXuat() {
		
	}
	public ChiTietHoaDonXuat(String maPX, Double donGia, int soLuong, String maLoai, String maXe, Double tongTien) {
		
		this.maPX = maPX;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.maLoai = maLoai;
		this.maXe = maXe;
		this.tongTien = tongTien;
	}
	public String getMaPX() {
		return maPX;
	}
	public void setMaPX(String maPX) {
		this.maPX = maPX;
	}
	public Double getDonGia() {
		return donGia;
	}
	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getMaXe() {
		return maXe;
	}
	public void setMaXe(String maXe) {
		this.maXe = maXe;
	}
	public Double gettongTien() {
		return tongTien;
	}
	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}
	
}
