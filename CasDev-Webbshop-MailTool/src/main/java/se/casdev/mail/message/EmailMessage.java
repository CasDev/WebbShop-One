package se.casdev.mail.message;

import se.casdev.mail.attribute.Subject;
import se.casdev.userInfo.Customer;

public enum EmailMessage {

	ACTIVATION;

	public static String getMessaage(Subject subject, Customer user) {
		switch (subject) {
		case ACTIVATION:
			String message = "";
			message = message
					.concat("This is an activcation email from webbshop.\n\n");
			message = message
					.concat("Click on this link to activate your acount at webbshop.\n");
			message = message
					.concat("http://127.0.0.1:8080/webbshop_casdev-web-SPRING/"
							+ user.getUserId());

			return message;

		default:

			return "";
		}
	}

}
