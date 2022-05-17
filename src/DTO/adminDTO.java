package DTO;

public class adminDTO {
	private String adminId;
	private String adminName;
	private String adminUser;
	private String adminPass;
	public adminDTO() {
		// TODO Auto-generated constructor stub
	}
	public adminDTO(String adminId, String adminName, String adminUser, String adminPass) {
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminUser = adminUser;
		this.adminPass = adminPass;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}
	public String getAdminPass() {
		return adminPass;
	}
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
}
