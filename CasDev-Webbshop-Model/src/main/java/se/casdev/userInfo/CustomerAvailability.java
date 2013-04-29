package se.casdev.userInfo;

public enum CustomerAvailability {
	
	AVAILABLE, UNAVAILABLE, AWAITING;
	
	public static String customerAvailable(CustomerAvailability available)
	{
		
		switch (available) {
		case AVAILABLE:
			return "Available";
		case UNAVAILABLE:
			return "Not available";
		case AWAITING:
			return "Awaiting activation";
		default:
			return null;
		}
		
	}

}
