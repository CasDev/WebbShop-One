package se.casdev.database.storage.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jcouchdb.db.Database;
import org.jcouchdb.document.BaseDocument;
import org.jcouchdb.document.ValueRow;
import org.jcouchdb.document.ViewResult;
import org.jcouchdb.exception.NotFoundException;

import se.casdev.database.access.ConnectingCouchDB;
import se.casdev.database.base.Logic;
import se.casdev.database.storage.UserInterface;
import se.casdev.userInfo.Address;
import se.casdev.userInfo.Customer;
import se.casdev.userInfo.CustomerAvailability;
import se.casdev.warnings.Messenge;
import se.casdev.warnings.Warning;

public class CouchDBUserStorage implements UserInterface {

	ConnectingCouchDB connection = new ConnectingCouchDB();

	public CouchDBUserStorage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Customer getUserById(String id) {

		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument user = null;
		try {
			user = db.getDocument(BaseDocument.class, id);
		} catch (NotFoundException e) {
			return null;
		}

		if (user == null) {
			return null;
		}
		Customer customer = new Customer();
		customer.setFirstname(user.getProperty("fName").toString());
		customer.setLastname(user.getProperty("lName").toString());
		customer.setBirthDate(user.getProperty("birthdate").toString());
		customer.setAvailable(CustomerAvailability.valueOf(user.getProperty(
				"available").toString()));
		customer.setEmail(user.getProperty("email").toString());
		customer.setPassword(user.getProperty("password").toString());
		customer.setReadOnly(Boolean.parseBoolean(user.getProperty("readonly")
				.toString()));
		customer.setUserId(id);
		Map<String, Object> adr = (HashMap<String, Object>) user
				.getProperty("address");
		customer.getAddress().setArea(adr.get("area").toString());
		customer.getAddress().setCity(adr.get("city").toString());
		customer.getAddress().setCountry(adr.get("country").toString());
		customer.getAddress().setStreet(adr.get("street").toString());
		customer.getAddress().setZip(adr.get("zip").toString());

		return customer;
	}

	@Override
	public Customer loginWithUser(String email, String password) {

		Database db = connection.getConnection("database", "username",
				"password");
		String viewName = "login";
		String mapFn = "function(doc) { if (doc.email == '" + email
				+ "' && doc.password == '" + password
				+ "')  { emit(doc._id, doc); } }";

		List<ValueRow<BaseDocument>> rows = connection.createView(db, viewName,
				mapFn);
		if (rows.size() <= 0) {
			return null;
		}

		Customer user = new Customer();
		for (ValueRow<BaseDocument> valueRow : rows) {
			user = getUserById(valueRow.getId());
		}

		return user;

	}

	@Override
	public Customer getLatestCustomer() {
		List<Customer> users = getAllCustomers();
		int size = (users.size() - 1);
		return users.get(size);

	}

	@Override
	public Address getAddressFromId(String id) {

		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument user = db.getDocument(BaseDocument.class, id);

		if (user == null) {
			return null;
		}
		Address address = new Address();
		Map<String, Object> adr = (HashMap<String, Object>) user
				.getProperty("address");
		address.setArea(adr.get("area").toString());
		address.setCity(adr.get("city").toString());
		address.setCountry(adr.get("country").toString());
		address.setStreet(adr.get("street").toString());
		address.setZip(adr.get("zip").toString());

		return address;
	}

	@Override
	public List<Customer> getAllCustomers() {
		Database db = connection.getConnection("database", "username",
				"password");
		ViewResult<Map> list = db.listDocuments(null, null);
		List<Customer> customers = new ArrayList<Customer>();
		for (ValueRow row : list.getRows()) {

			String id = row.getId();
			Customer user = getUserById(id);
			customers.add(user);

		}
		return customers;
	}

	@Override
	public boolean createUser(String firstname, String lastname,
			String birthdate, String available, String email, boolean readonly,
			String country, String city, String zip, String area,
			String street, String password) {
		Database db = connection.getConnection("database", "username",
				"password");
		String id = Logic.determineUserId(this.getClass());
		BaseDocument user = new BaseDocument();
		user.setProperty("fName", firstname);
		user.setProperty("lName", lastname);
		user.setProperty("birthdate", birthdate);
		user.setProperty("available", available);
		user.setProperty("email", email);
		user.setProperty("readonly", readonly);
		user.setProperty("userid", id);
		user.setProperty("password", password);
		BaseDocument address = new BaseDocument();
		address.setProperty("country", country);
		address.setProperty("city", city);
		address.setProperty("zip", zip);
		address.setProperty("area", area);
		address.setProperty("street", street);
		user.setProperty("address", address);
		user.setId(id);

		db.createDocument(user);
		BaseDocument doc = db.getDocument(BaseDocument.class, id);

		if (doc != null) {

			return true;

		} else {

			return false;

		}

	}

	@Override
	public boolean updateUserReadOnlyById(boolean readOnly, String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		doc.setProperty("readonly", readOnly);
		db.updateDocument(doc);

		String viewName = "checkReadOnly";
		String mapFn = "function(doc) { if (doc.readonly === \"" + readOnly
				+ "\" && doc._id === \"" + id + "\") emit(doc._id, doc._id); }";

		List<ValueRow<BaseDocument>> rows = connection.createView(db, viewName,
				mapFn);
		if (rows == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean updateUserAddressById(String country, String city,
			String zip, String area, String street, String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		BaseDocument address = new BaseDocument();
		address.setProperty("country", country);
		address.setProperty("city", city);
		address.setProperty("zip", zip);
		address.setProperty("area", area);
		address.setProperty("street", street);
		doc.setProperty("address", address);
		db.updateDocument(doc);

		String viewName = "checkAddress";
		String mapFn = "function(doc) { if (doc.address.country == '" + country
				+ "' && doc.address.country == '" + country
				+ "' && doc.address.city == '" + city
				+ "' && doc.address.zip == '" + zip
				+ "' && doc.address.area == '" + area
				+ "' && doc.address.street == '" + street + "' && doc._id == '"
				+ id + "') emit(doc._id, doc._id); }";

		List<ValueRow<BaseDocument>> rows = connection.createView(db, viewName,
				mapFn);
		if (rows == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean updateUserLastnameById(String lastname, String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		doc.setProperty("lName", lastname);
		db.updateDocument(doc);

		String viewName = "checkLastname";
		String mapFn = "function(doc) { if (doc.lName === \"" + lastname
				+ "\" && doc._id === \"" + id + "\") emit(doc._id, doc._id); }";

		List<ValueRow<BaseDocument>> rows = connection.createView(db, viewName,
				mapFn);
		if (rows == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean updateUserMailById(String email, String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		doc.setProperty("email", email);
		db.updateDocument(doc);

		String viewName = "checkEmail";
		String mapFn = "function(doc) { if (doc.email === \"" + email
				+ "\" && doc._id === \"" + id + "\") emit(doc._id, doc._id); }";

		List<ValueRow<BaseDocument>> rows = connection.createView(db, viewName,
				mapFn);
		if (rows == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean updateUserAvailableById(String available, String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		doc.setProperty("available", available);
		db.updateDocument(doc);

		String viewName = "checkAvailable";
		String mapFn = "function(doc) { if (doc.available === \"" + available
				+ "\" && doc._id === \"" + id + "\") emit(doc._id, doc._id); }";

		List<ValueRow<BaseDocument>> rows = connection.createView(db, viewName,
				mapFn);
		if (rows == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean updateUserPasswordById(String new_password,
			String old_password, String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		if (doc.getProperty("password").toString().equals(old_password)
				&& !old_password.equals(new_password)) {
			doc.setProperty("password", new_password);
		} else {
			Warning.addClientMessage(new Messenge("password.cannot.be.same",
					"You have given your password the same password you allready obtain"));
			Warning.addServerMessage(new Messenge(
					"password.cannot.be.same",
					"The user with id#"
							+ id
							+ " has tried to change it's password to what it allready is. You cannot do anything against this"));
			return false;
		}
		db.updateDocument(doc);

		String viewName = "checkPassword";
		String mapFn = "function(doc) { if (doc.available === \""
				+ new_password + "\" && doc._id === \"" + id
				+ "\") emit(doc._id, doc._id); }";

		List<ValueRow<BaseDocument>> rows = connection.createView(db, viewName,
				mapFn);
		if (rows == null) {
			return false;
		} else {
			return true;
		}
	}

}
