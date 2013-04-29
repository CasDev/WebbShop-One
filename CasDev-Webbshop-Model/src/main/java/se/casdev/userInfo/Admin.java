package se.casdev.userInfo;

public class Admin extends User {
	
	private AdminRole role;
	
	public Admin() {
		super();
		this.role = AdminRole.ADMIN;
	}
	
	public Admin setRole(AdminRole role) {
		this.role = role;
		
		return this;
	}
	
	public AdminRole getRole() {
		return role;
	}

}
