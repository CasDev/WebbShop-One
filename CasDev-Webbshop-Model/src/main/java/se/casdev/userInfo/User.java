package se.casdev.userInfo;

public class User {

	private String firstname;
	private String lastname;
	private String email;
	private Address address;
	private String birthDate;
	private String[] birthArray;
	private boolean readOnly;
	private String userId;
	private String password;

	public User() {
		this.firstname = "";
		this.lastname = "";
		this.email = "";
		this.address = new Address();
		this.birthDate = "00-00-00";
		this.birthArray = this.birthDate.split("-");
		this.readOnly = true;
		this.userId = "";
		this.password = "";
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public User setFirstname(String firstname) {
		this.firstname = firstname;
		
		return this;
	}

	public String getLastname() {
		return lastname;
	}

	public User setLastname(String lastname) {
		this.lastname = lastname;
		
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		
		return this;
	}

	public Address getAddress() {
		return address;
	}

	public String getYear() {
		return birthArray[0];
	}

	public String getMonth() {
		return birthArray[1];
	}

	public String getDay() {
		return birthArray[2];
	}

	public User setBirthDate(String birthDate) {
		this.birthDate = birthDate;
		this.birthArray = birthDate.split("-");
		
		return this;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public User setReadOnly() {
		this.readOnly = true;
		
		return this;
	}
	
	public User setReadOnly(boolean read) {
		this.readOnly = read;
		
		return this;
	}
	
	public User setReadWrite() {
		this.readOnly = false;
		
		return this;
	}

	public String getUserId() {
		return userId;
	}
	
	public User setUserId(String userId) {
		this.userId = userId;
		
		return this;
	}

}
