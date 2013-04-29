package se.casdev.userInfo;

public enum AdminRole {
	
	ADMIN, SUPERADMIN;
	
	public static String determineAdminRole(AdminRole role)
	{
		
		switch (role) {
		case ADMIN:
			return "Admin";
		case SUPERADMIN:
			return "Super Admin";
		default:
			return null;
		}
		
	}

}
