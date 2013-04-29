package se.casdev.userInfo;

public class Address {

	private String country;
	private String city;
	private String zip;
	private String area;
	private String street;

	public Address() {
		this.country = "";
		this.city = "";
		this.zip = "";
		this.area = "";
		this.street = "";
	}

	public Address(Address address) {
		this.country = address.getCountry();
		this.city = address.getCity();
		this.zip = address.getZip();
		this.area = address.getArea();
		this.street = address.getStreet();
	}

	public Address setCountry(String country) {
		this.country = country;
		
		return this;
	}

	public String getCountry() {
		return country;
	}

	public Address setCity(String city) {
		this.city = city;
		
		return this;
	}

	public String getCity() {
		return city;
	}

	public Address setZip(String zip) {
		this.zip = zip;
		
		return this;
	}

	public String getZip() {
		return zip;
	}

	public Address setStreet(String street) {
		this.street = street;
		
		return this;
	}

	public String getStreet() {
		return street;
	}

	public String getArea() {
		return area;
	}

	public Address setArea(String area) {
		this.area = area;
		
		return this;
	}

	@Override
	public int hashCode() {

		int hash = 178;
		hash += country.hashCode();
		hash += city.hashCode();
		hash += zip.hashCode();
		hash += area.hashCode();
		hash += street.hashCode();

		return hash;

	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Address) {
			if (this.hashCode() == obj.hashCode()) {
				return true;
			}
		} else {
			return false;
		}
		
		return false;

	}
}
