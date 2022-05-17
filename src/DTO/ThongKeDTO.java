package DTO;

public class ThongKeDTO {
	private int[] soLuongXeMay;
	private int[] soLuongNhanVien;
	private int[] TongQuy;
	
	public ThongKeDTO() {
		
	}
	
	public ThongKeDTO(int[] soLuongXeMay, int[] soLuongNhanVien, int[] tongQuy) {
		this.soLuongXeMay = soLuongXeMay;
		this.soLuongNhanVien = soLuongNhanVien;
		TongQuy = tongQuy;
	}
	public int[] getSoLuongXeMay() {
		return soLuongXeMay;
	}

	public int[] getSoLuongNhanVien() {
		return soLuongNhanVien;
	}

	public int[] getTongQuy() {
		return TongQuy;
	}
	public void setSoLuongNhanVien(int[] soLuongNhanVien) {
		this.soLuongNhanVien = soLuongNhanVien;
	}

	public void setTongQuy(int[] tongQuy) {
		this.TongQuy = tongQuy;
	}
	

	public void setSoLuongXeMay(int[] soLuongXeMay) {
		this.soLuongXeMay = soLuongXeMay;
	}
	public int getSoLuongXeMay(int quy) {
		return soLuongXeMay[quy -1];
	}
	public int getSoLuongNhanVien(int quy) {
		return soLuongNhanVien[quy -1];
	}

	public int getTongQuy(int quy) {
		return TongQuy[quy -1];
	}
	
	//
	public int getTongXeMay() {
		int tong = 0;
		for (int i = 0; i < 4; i++) {
			tong += soLuongXeMay[i];
			System.out.println(tong);
		}
		return tong;
	}
	//
	public int getTongNhanVien() {
		int tong = 0;
		for (int i = 0; i < 4; i++) {
			tong += soLuongNhanVien[i];
		}
		System.out.println(tong);
		return tong;
	}
	//
	public int getTongDoanhThu() {
		int tong = 0;
		for (int i = 0; i < 4; i++) {
			tong += TongQuy[i];
			System.out.println(tong);
		}
		
		return tong;
	}
}
