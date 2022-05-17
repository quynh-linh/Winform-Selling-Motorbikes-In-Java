 package DTO;


public class XeMayDTO {
	private String maXe;
	private String tenXe;
	private double giaXe;
	private int soLuong;
	private String loaiXe;
	private String image;
	public XeMayDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public XeMayDTO(String maXe, String tenXe, double giaXe, int soLuong, String loaiXe,
			String image) {
		super();
		this.maXe = maXe;
		this.tenXe = tenXe;
		this.giaXe = giaXe;
		this.soLuong = soLuong;
		this.loaiXe = loaiXe;
		this.image = image;
	}
	
	public String getMyImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public String getMaXe() {
		return maXe;
	}
	public void setMaXe(String maXe) {
		this.maXe = maXe;
	}
	public String getTenXe() {
		return tenXe;
	}
	public void setTenXe(String tenXe) {
		this.tenXe = tenXe;
	}
	public double getGiaXe() {
		return giaXe;
	}
	public void setGiaXe(double giaXe) {
		this.giaXe = giaXe;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getLoaiXe() {
		return loaiXe;
	}
	public void setLoaiXe(String loaiXe) {
		this.loaiXe = loaiXe;
	}

	@Override
	public String toString() {
		return "XeMayDTO [maXe=" + maXe + ", tenXe=" + tenXe + ", giaXe=" + giaXe + ", soLuong=" + soLuong + ", loaiXe="
				+ loaiXe + ", image=" + image + "]";
	}

}
