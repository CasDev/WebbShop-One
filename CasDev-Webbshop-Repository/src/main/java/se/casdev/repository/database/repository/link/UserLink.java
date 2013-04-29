package se.casdev.repository.database.repository.link;

import se.casdev.crypt.Cryptograph;
import se.casdev.database.storage.AdminInterface;
import se.casdev.database.storage.UserInterface;
import se.casdev.database.storage.implement.CouchDBAdminStorage;
import se.casdev.database.storage.implement.CouchDBUserStorage;
import se.casdev.mail.SendEmail;
import se.casdev.mail.attribute.Subject;
import se.casdev.userInfo.Admin;
import se.casdev.userInfo.Customer;
import se.casdev.userInfo.CustomerAvailability;
import se.casdev.repository.database.repository.link.Validator;

public class UserLink {

	private UserInterface uInterface;

	protected UserLink(UserInterface uInterface) {
		this.uInterface = uInterface;
	}

	public boolean updateUserReadOnly(boolean readonly, String id) {

		if (id.startsWith("CUS")) {

			return uInterface.updateUserReadOnlyById(readonly, id);

		}

		return false;
	}

	public boolean activate(String id) {

		Customer user = uInterface.getUserById(id);

		if (user == null) {
			return false;
		}

		if (user.getAvailable().name()
				.equals(CustomerAvailability.AWAITING.name())) {

			boolean ok = uInterface.updateUserAvailableById(
					CustomerAvailability.AVAILABLE.name(), id);

			if (ok) {

				return true;
			}

		} else {

			return false;
		}

		return false;

	}

	public Customer login(String email, String password) {

		boolean isOK = Validator.validateEmail(email)
				&& Validator.validatePassword(password);
		if (isOK) {

			String nPassword = Cryptograph.cryptPassword(password);

			return uInterface.loginWithUser(email, nPassword);

		} else {

			return null;

		}
	}

	public boolean updateUserAddress(String country, String city, String zip,
			String area, String street, String id) {

		boolean isAddressOK = Validator.validateAddress(country, area, zip,
				street, city);

		if (isAddressOK) {
			return uInterface.updateUserAddressById(country, city, zip, area,
					street, id);
		} else {
			return false;
		}
	}

	public boolean updateUserLastname(String lastname, String id) {
		boolean lastnameOK = (lastname.length() > 1);

		if (lastnameOK) {
			return uInterface.updateUserLastnameById(lastname, id);
		} else {
			return false;
		}
	}

	public boolean updateUserEmail(String mail, String id) {
		boolean emailOK = Validator.validateEmail(mail);

		if (emailOK) {
			return uInterface.updateUserMailById(mail, id);
		} else {
			return false;
		}
	}

	public boolean updateUserPassword(String nPassword, String oPassword,
			String id) {
		boolean passwordOK = Validator.validatePassword(nPassword);

		if (passwordOK) {

			String password = Cryptograph.cryptPassword(nPassword);

			return uInterface.updateUserPasswordById(password, oPassword, id);
		} else {
			return false;
		}
	}

	public boolean signup(String firstname, String lastname, String email,
			String password, String birthdate, String country, String area,
			String zip, String street, String city) {

		boolean isOK = Validator.validateEmail(email)
				&& Validator.validatePassword(password)
				&& Validator.validateAddress(country, area, zip, street, city)
				&& Validator.validateBirthdate(birthdate);

		if (isOK) {

			String nPassword = Cryptograph.cryptPassword(password);

			boolean ok = uInterface.createUser(firstname, lastname, birthdate,
					CustomerAvailability.AWAITING.name(), email, true, country,
					city, zip, area, street, nPassword);

			if (ok) {
				Customer user = uInterface.getLatestCustomer();
				AdminInterface aInterface = new CouchDBAdminStorage();
				Admin admin = aInterface.getAdminById("ADM200001");
				String adminPassword = "castell3";
				Subject subject = Subject.ACTIVATION;

				boolean yes = SendEmail.send(user, adminPassword, subject);

				return true;

			}

		} else {

			return false;

		}

		return false;

	}

}
