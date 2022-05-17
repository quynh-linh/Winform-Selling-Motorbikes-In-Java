package DTO;

public class NhanVienDTO {
	private String maNV;
	private String tenNV;
	private String  ngaySinh;
	private String diaChi;
	private int sdt;
	private String ngayLam;
	private double luongNV;
	private String gioiTinh;
	public NhanVienDTO() {
		// TODO Auto-generated constructor stub
	}
	public NhanVienDTO(String maNV, String tenNV, String ngaySinh, String diaChi, int sdt, String ngayLam,
			double luongNV, String gioiTinh) {
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.ngayLam = ngayLam;
		this.luongNV = luongNV;
		this.gioiTinh = gioiTinh;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public int getSdt() {
		return sdt;
	}
	public void setSdt(int sdt) {
		this.sdt = sdt;
	}
	public String getNgayLam() {
		return ngayLam;
	}
	public void setNgayLam(String ngayLam) {
		this.ngayLam = ngayLam;
	}
	public double getLuongNV() {
		return luongNV;
	}
	public void setLuongNV(double luongNV) {
		this.luongNV = luongNV;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	@Override
	public String toString() {
		return "NhanVienDTO [maNV=" + maNV + ", tenNV=" + tenNV + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi
				+ ", sdt=" + sdt + ", ngayLam=" + ngayLam + ", luongNV=" + luongNV + ", gioiTinh=" + gioiTinh + "]";
	}
}
