package DTO;

public class NhatKyDTO {
	private String id;
	private String ngay;
	private float mucChi;
	private float soTien;
	private String ghiChu;
	public NhatKyDTO() {
		// TODO Auto-generated constructor stub
	}
	public NhatKyDTO(String id, String ngay, float mucChi, float soTien, String ghiChu) {
		this.id = id;
		this.ngay = ngay;
		this.mucChi = mucChi;
		this.soTien = soTien;
		this.ghiChu = ghiChu;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNgay() {
		return ngay;
	}
	public void setNgay(String ngay) {
		this.ngay = ngay;
	}
	public float getMucChi() {
		return mucChi;
	}
	public void setMucChi(float mucChi) {
		this.mucChi = mucChi;
	}
	public float getSoTien() {
		return soTien;
	}
	public void setSoTien(float soTien) {
		this.soTien = soTien;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
}
