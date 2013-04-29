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
import se.casdev.database.storage.AdminInterface;
import se.casdev.userInfo.Admin;
import se.casdev.userInfo.AdminRole;
import se.casdev.warnings.Messenge;
import se.casdev.warnings.Warning;

public class CouchDBAdminStorage implements AdminInterface {

	ConnectingCouchDB connection = new ConnectingCouchDB();

	public CouchDBAdminStorage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Admin getAdminById(String id) {

		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument user = null;
		try {
			user = db.getDocument(BaseDocument.class, id);
		} catch (NotFoundException e) {
			user = null;
		}

		if (user == null) {
			return null;
		}

		Admin admin = new Admin();
		admin.setBirthDate(user.getProperty("birthdate").toString());
		admin.setEmail(user.getProperty("email").toString());
		admin.setFirstname(user.getProperty("fName").toString());
		admin.setLastname(user.getProperty("lName").toString());
		admin.setPassword(user.getProperty("password").toString());
		admin.setReadOnly(Boolean.parseBoolean(user.getProperty("readonly")
				.toString()));
		admin.setRole(AdminRole.valueOf(user.getProperty("adminrole")
				.toString()));
		admin.setUserId(id);
		Map<String, Object> adr = (HashMap<String, Object>) user
				.getProperty("address");
		admin.getAddress().setArea(adr.get("area").toString());
		admin.getAddress().setCity(adr.get("city").toString());
		admin.getAddress().setCountry(adr.get("country").toString());
		admin.getAddress().setStreet(adr.get("street").toString());
		admin.getAddress().setZip(adr.get("zip").toString());

		return admin;
	}

	@Override
	public Admin loginAdmin(String email, String password) {
		Database db = connection.getConnection("database", "username",
                                               "password");

		String viewName = "login";
		String mapFn = "function(doc) { if (doc.email === \"" + email
				+ "\" && doc.password === \"" + password
				+ "\") emit(doc._id, doc._id); }";

		List<ValueRow<BaseDocument>> rows = connection.createView(db, viewName,
				mapFn);
		if (rows == null) {
			return null;
		}

		Admin admin = new Admin();
		for (ValueRow<BaseDocument> valueRow : rows) {
			admin = getAdminById(valueRow.getId());
		}

		return admin;
	}

	@Override
	public boolean createAdminstration(String firstname, String lastname,
			String email, String country, String street, String city,
			String zip, String area, String birthdate, boolean readOnly,
			String password, String role) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = new BaseDocument();
		String id = Logic.determineAdminId(this.getClass());
		doc.setId(id);
		doc.setProperty("adminid", id);
		doc.setProperty("fName", firstname);
		doc.setProperty("lName", lastname);
		doc.setProperty("password", password);
		doc.setProperty("email", email);
		doc.setProperty("adminrole", role);
		doc.setProperty("birthdate", birthdate);
		doc.setProperty("readonly", readOnly);
		BaseDocument adr = new BaseDocument();
		adr.setProperty("country", country);
		adr.setProperty("street", street);
		adr.setProperty("zip", zip);
		adr.setProperty("area", area);
		adr.setProperty("city", city);
		doc.setProperty("address", adr);
		db.createDocument(doc);

		doc = null;
		try {
			doc = db.getDocument(BaseDocument.class, id);
		} catch (NotFoundException e) {
			doc = null;
		}

		if (doc != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean updateAdminLastnameById(String lastname, String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		doc.setProperty("lName", lastname);
		db.updateDocument(doc);

		String viewName = "checkPassword";
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
	public boolean updateAdminMailById(String email, String id) {
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
	public boolean updateAdminPasswordById(String old_password,
			String new_password, String id) {
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
					"The admin with id#"
							+ id
							+ " has tried to change it's password to what it allready is. You cannot do anything against this"));
			return false;
		}
		db.updateDocument(doc);

		String viewName = "checkPassword";
		String mapFn = "function(doc) { if (doc.adminrole === \""
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

	@Override
	public boolean updateAdminRoleById(AdminRole role, String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		doc.setProperty("adminrole", role.name());
		db.updateDocument(doc);

		String viewName = "checkAdminRole";
		String mapFn = "function(doc) { if (doc.adminrole === \"" + role.name()
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
	public Admin getLatestAdmin() {
		List<Admin> admins = getAllAdmins();
		int size = (admins.size() - 1);
		return admins.get(size);
	}

	@Override
	public List<Admin> getAllAdmins() {
		Database db = connection.getConnection("database", "username",
				"password");
		List<Admin> admins = new ArrayList<Admin>();
		ViewResult<Map> map = db.listDocuments(null, null);
		for (ValueRow row : map.getRows()) {
			String id = row.getId();
			Admin admin = getAdminById(id);
			admins.add(admin);
		}
		return admins;
	}

}
