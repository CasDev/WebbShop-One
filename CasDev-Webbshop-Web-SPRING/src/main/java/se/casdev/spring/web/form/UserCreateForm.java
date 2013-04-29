package se.casdev.spring.web.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class UserCreateForm {

	private String country;
	private String city;
	private String street;
	private String zip;
	private String area;
	private String password;
	private String verifyPassword;
	private String birthdate;
	private String email;
	private String firstname;
	private String lastname;
	private boolean firstnameOK;
	private boolean lastnameOK;
	private boolean emailOK;
	private boolean birthdateOK;
	private boolean passwordOK;
	private boolean addressOK;
	private boolean passwordVerified;

	public UserCreateForm() {
		this.country = "";
		this.city = "";
		this.street = "";
		this.area = "";
		this.zip = "";
		this.password = "";
		this.verifyPassword = "";
		this.birthdate = "";
		this.email = "";
		this.addressOK = true;
		this.emailOK = true;
		this.lastnameOK = true;
		this.firstnameOK = true;
		this.passwordOK = true;
		this.passwordVerified = true;
	}

	public void setPasswordVerified(boolean passwordVerified) {
		this.passwordVerified = passwordVerified;
	}

	public boolean isAddressOK() {
		return addressOK;
	}

	public boolean isBirthdateOK() {
		return birthdateOK;
	}

	public boolean isEmailOK() {
		return emailOK;
	}

	public boolean isLastnameOK() {
		return lastnameOK;
	}

	public boolean isFirstnameOK() {
		return firstnameOK;
	}

	public boolean isPasswordOK() {
		return passwordOK;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyPassword() {
		return verifyPassword;
	}

	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isPasswordVerified() {
		return this.password.equals(this.verifyPassword);
	}

	public boolean isVerified() {
		return this.passwordVerified;
	}

	public void setAddressOK(boolean addressOK) {
		this.addressOK = addressOK;
	}

	public void setBirthdateOK(boolean birthdateOK) {
		this.birthdateOK = birthdateOK;
	}

	public void setEmailOK(boolean emailOK) {
		this.emailOK = emailOK;
	}

	public void setLastnameOK(boolean lastnameOK) {
		this.lastnameOK = lastnameOK;
	}

	public void setFirstnameOK(boolean nameOK) {
		this.firstnameOK = nameOK;
	}

	public void setPasswordOK(boolean passwordOK) {
		this.passwordOK = passwordOK;
	}

}
