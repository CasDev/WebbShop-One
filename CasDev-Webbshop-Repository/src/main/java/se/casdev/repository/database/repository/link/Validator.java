package se.casdev.repository.database.repository.link;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public static boolean validateEmail(String email) {

		Pattern patern = Pattern
				.compile("^([a-zA-Z0-9-_]{1,}+@+[a-zA-Z.]{1,})(\\.[a-z]{2,})$");
		Matcher matcher = patern.matcher(email);

		return matcher.matches();

	}

	public static boolean validateProductId(String id) {
		Pattern patern = Pattern.compile("^([0-9]{6,6}+[A-Z]{3,3})$");
		Matcher matcher = patern.matcher(id);

		return matcher.matches();
	}

	public static boolean validatePassword(String password) {
		Pattern patern = Pattern.compile("^[a-zA-Z0-9]{6,}$");
		Matcher matcher = patern.matcher(password);

		return matcher.matches();
	}

	public static boolean validateAddress(String land, String ort,
			String zipNum, String gata, String stad) {

		boolean landOK = validateCountry(land);
		boolean ortOK = validateArea(ort);
		boolean zipOK = validateZip(zipNum);
		boolean gataOK = validateStreet(gata);
		boolean stadOK = validateCity(stad);

		if (landOK && ortOK && zipOK && gataOK && stadOK) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validateCity(String city) {
		Pattern patern = Pattern.compile("^([ a-zA-Z]{1,})$");
		Matcher matcher = patern.matcher(city);

		return matcher.matches();
	}

	public static boolean validateFirstname(String name) {
		Pattern patern = Pattern.compile("^([ a-zA-Z]{1,})$");
		Matcher matcher = patern.matcher(name);

		return matcher.matches();
	}

	public static boolean validateLastname(String name) {
		Pattern patern = Pattern.compile("^([ a-zA-Z]{1,})$");
		Matcher matcher = patern.matcher(name);

		return matcher.matches();
	}

	public static boolean validateStreet(String street) {
		Pattern patern = Pattern.compile("^([ a-zA-Z0-9]{1,})$");
		Matcher matcher = patern.matcher(street);

		return matcher.matches();
	}

	public static boolean validateZip(String zip) {
		Pattern patern = Pattern.compile("^([0-9]{5,5})$");
		Matcher matcher = patern.matcher(zip);

		return matcher.matches();
	}

	public static boolean validateArea(String area) {
		Pattern patern = Pattern.compile("^([ a-zA-Z]{1,})$");
		Matcher matcher = patern.matcher(area);

		return matcher.matches();
	}

	public static boolean validateCountry(String country) {
		Pattern patern = Pattern.compile("^([ a-zA-Z]{1,})$");
		Matcher matcher = patern.matcher(country);

		return matcher.matches();
	}

	public static boolean validateBirthdate(String birthdate) {
		Pattern patern = Pattern
				.compile("^([0-9]{2,2}+-+[0-9]{2,2}+-+[0-9]{2,2})$");
		Matcher matcher = patern.matcher(birthdate);

		return matcher.matches();
	}

	public static boolean validateCategoryId(String id) {
		Pattern patern = Pattern.compile("^([A-Z]{3,3}+[0-9]{3,3})$");
		Matcher matcher = patern.matcher(id);

		return matcher.matches();
	}

}
