package DTO;

public class NhaCungCap {
	private String maNhaCC;
	private String tenNhaCC;
	private String diaChi;
	private String sdtNhaCC;

	public NhaCungCap(String maNhaCC, String tenNhaCC, String diaChi, String sdtNhaCC) {
		this.maNhaCC = maNhaCC;
		this.tenNhaCC = tenNhaCC;
		this.diaChi = diaChi;
		this.sdtNhaCC = sdtNhaCC;
	}

	public NhaCungCap() {
		// TODO Auto-generated constructor stub
	}

	public String getMaNhaCC() {
		return maNhaCC;
	}

	public void setMaNhaCC(String maNhaCC) {
		this.maNhaCC = maNhaCC;
	}

	public String getTenNhaCC() {
		return tenNhaCC;
	}

	public void setTenNhaCC(String tenNhaCC) {
		this.tenNhaCC = tenNhaCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		;
		this.diaChi = diaChi;
	}

	public String getSdtNhaCC() {
		return sdtNhaCC;
	}

	public void setSdtNhaCC(String sdtNhaCC) {
		this.sdtNhaCC = sdtNhaCC;
	}
}
