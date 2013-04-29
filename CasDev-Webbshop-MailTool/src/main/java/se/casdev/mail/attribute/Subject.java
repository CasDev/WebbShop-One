package se.casdev.mail.attribute;

import se.casdev.userInfo.Customer;

public enum Subject {

	ACTIVATION;

	public String getSubject(Customer user) {
		switch (this) {
		case ACTIVATION:

			return "Activation for " + user.getFirstname() + " "
					+ user.getLastname();

		default:
			return null;
		}
	}

}
