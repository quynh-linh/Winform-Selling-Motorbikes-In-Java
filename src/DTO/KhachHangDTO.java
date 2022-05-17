package DTO;

public class KhachHangDTO {
	private String maKH;
	private String tenKH;
	private String diaChi;
	private int SDT;
	private String gioiTinh;
	public KhachHangDTO() {
		
	}
	public KhachHangDTO(String maKH, String tenKH, String diaChi, int sDT, String gioiTinh) {
		
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		SDT = sDT;
		this.gioiTinh = gioiTinh;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public int getSDT() {
		return SDT;
	}
	public void setSDT(int sDT) {
		SDT = sDT;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	

}
