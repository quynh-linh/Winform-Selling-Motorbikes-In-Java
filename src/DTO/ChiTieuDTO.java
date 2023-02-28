package DTO;

public class ChiTieuDTO {
	private String maNV;
	private String ho;
	private String ten;
	private String ngaySinh;
	private String ngayLam;
	
	public ChiTieuDTO(String maNV, String ho, String ten, String ngaySinh, String ngayLam) {
		this.maNV = maNV;
		this.ho = ho;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.ngayLam = ngayLam;
	}
	public ChiTieuDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getNgayLam() {
		return ngayLam;
	}

	public void setNgayLam(String ngayLam) {
		this.ngayLam = ngayLam;
	}
	@Override
	public String toString() {
		return "ChiTieuDTO [maNV=" + maNV + ", ho=" + ho + ", ten=" + ten + ", ngaySinh=" + ngaySinh + ", ngayLam="
				+ ngayLam + "]";
	}
}
