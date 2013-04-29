package se.casdev.spring.web.form;

import org.hibernate.validator.constraints.NotEmpty;

import se.casdev.userInfo.Customer;

public class UpdateUserForm {

	private String password;
	private boolean passwordOK;
	private String lastname;
	private boolean lastnameOK;
	private String email;
	private boolean emailOK;
	private String street;
	private String area;
	private String country;
	private String zip;
	private String city;
	private boolean cityOK;
	private boolean zipOK;
	private boolean countryOK;
	private boolean areaOK;
	private boolean streetOK;
	private Customer user;

	public UpdateUserForm() {
		this.user = null;
		this.password = "";
		this.lastname = "";
		this.email = "";
		this.emailOK = true;
		this.lastnameOK = true;
		this.passwordOK = true;
		this.street = "";
		this.streetOK = true;
		this.area = "";
		this.areaOK = true;
		this.city = "";
		this.cityOK = true;
		this.country = "";
		this.countryOK = true;
		this.zip = "";
		this.zipOK = true;
	}

	public void setUser(Customer user) {
		this.user = user;
	}

	public String getArea() {
		return area;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getStreet() {
		return street;
	}

	public String getZip() {
		return zip;
	}

	public boolean isAreaOK() {
		return areaOK;
	}

	public boolean isCityOK() {
		return cityOK;
	}

	public boolean isCountryOK() {
		return countryOK;
	}

	public boolean isStreetOK() {
		return streetOK;
	}

	public boolean isZipOK() {
		return zipOK;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setAreaOK(boolean areaOK) {
		this.areaOK = areaOK;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCityOK(boolean cityOK) {
		this.cityOK = cityOK;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCountryOK(boolean countryOK) {
		this.countryOK = countryOK;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setStreetOK(boolean streetOK) {
		this.streetOK = streetOK;
	}

	public void setZipOK(boolean zipOK) {
		this.zipOK = zipOK;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPassword() {
		return password;
	}

	public Customer getUser() {
		return user;
	}

	public boolean isEmailOK() {
		return emailOK;
	}

	public boolean isLastnameOK() {
		return lastnameOK;
	}

	public boolean isPasswordOK() {
		return passwordOK;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmailOK(boolean emailOK) {
		this.emailOK = emailOK;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setLastnameOK(boolean lastnameOK) {
		this.lastnameOK = lastnameOK;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPasswordOK(boolean passwordOK) {
		this.passwordOK = passwordOK;
	}

}
