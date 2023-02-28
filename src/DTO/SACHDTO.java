package DTO;

public class SACHDTO {
	private String maSach;
	private String tenSach;
	private int soluong;
	private float gia;
	public SACHDTO() {
		
	}
	public SACHDTO(String maSach, String tenSach, int soluong, float gia) {
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.soluong = soluong;
		this.gia = gia;
	}
	public String getMaSach() {
		return maSach;
	}
	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}
	public String getTenSach() {
		return tenSach;
	}
	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public float getGia() {
		return gia;
	}
	public void setGia(float gia) {
		this.gia = gia;
	}
}
