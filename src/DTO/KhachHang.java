package DTO;

public class KhachHang {
	private String diaChi;
	private int sdtKH;
	private String tenKH;
	private int maKH;
	
	public KhachHang(String diaChi, int sdtKH, String tenKH, int maKH) {
		this.diaChi = diaChi;
		this.sdtKH = sdtKH;
		this.tenKH = tenKH;
		this.maKH = maKH;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public int getSdtKH() {
		return sdtKH;
	}
	public void setSdtKH(int sdtKH) {
		this.sdtKH = sdtKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
}
