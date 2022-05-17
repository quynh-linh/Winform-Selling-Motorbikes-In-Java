package DTO;

public class BanHangDTO {

	 
	
	 private String Maxe;
	 private String Tenxe;
	 private byte[] Image;
	 private int quantity;
	public BanHangDTO() {
		
	}
	public BanHangDTO(String maxe, String tenxe, byte[] image, int quantity) {
		
		Maxe = maxe;
		Tenxe = tenxe;
		Image = image;
		this.quantity = quantity;
	}
	public String getMaxe() {
		return Maxe;
	}
	public void setMaxe(String maxe) {
		Maxe = maxe;
	}
	public String getTenxe() {
		return Tenxe;
	}
	public void setTenxe(String tenxe) {
		Tenxe = tenxe;
	}
	public byte[] getMyImage() {
		return Image;
	}
	public void setImage(byte[] image) {
		Image = image;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	 
	
	 
}
