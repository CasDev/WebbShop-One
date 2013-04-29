package se.casdev.database.storage.implement;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import se.casdev.database.access.ConnectingMongoDB;
import se.casdev.database.base.Logic;
import se.casdev.database.storage.AdminInterface;
import se.casdev.userInfo.Admin;
import se.casdev.userInfo.AdminRole;

public class MongoDBAdminStorage implements AdminInterface {

	ConnectingMongoDB connect = new ConnectingMongoDB("database");

	@Override
	public Admin getLatestAdmin() {
		List<Admin> list = getAllAdmins();
		int i = (list.size() - 1);
		return list.get(i);
	}

	@Override
	public List<Admin> getAllAdmins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin loginAdmin(String email, String password) {
		DB db = connect.getConnection();
		Admin admin = null;
		if (connect.auth("username", "password")) {
			DBCollection coll = db.getCollection("database");
			DBObject ref = new BasicDBObject();
			ref.put("email", email);
			ref.put("password", password);
			DBCursor cur = coll.find(ref);
			if (cur.hasNext()) {
				DBObject obj = cur.next();
				String id = obj.get("id").toString();
				admin = getAdminById(id);
			}

		}

		return admin;
	}

	@Override
	public Admin getAdminById(String id) {

		DB db = connect.getConnection();
		Admin admin = new Admin();
		if (connect.auth("username", "password")) {
			DBCollection coll = db.getCollection("database");
			DBObject ref = new BasicDBObject();
			ref.put("id", id);
			DBCursor cur = coll.find(ref);
			if (cur.hasNext()) {
				DBObject obj = cur.next();
				admin.setFirstname(obj.get("firstname").toString());
				admin.setLastname(obj.get("lastname").toString());
				admin.setEmail(obj.get("email").toString());
				admin.setBirthDate(obj.get("birthdate").toString());
				admin.setReadOnly(Boolean.parseBoolean(obj.get("readOnly")
						.toString()));
				admin.setPassword(obj.get("password").toString());
				admin.setUserId(id);
				admin.setRole(AdminRole.valueOf(obj.get("role").toString()));
				admin.getAddress().setArea(obj.get("area").toString());
				admin.getAddress().setCity(obj.get("city").toString());
				admin.getAddress().setCountry(obj.get("country").toString());
				admin.getAddress().setStreet(obj.get("street").toString());
				admin.getAddress().setZip(obj.get("zip").toString());
			}
		}
		return admin;
	}

	@Override
	public boolean createAdminstration(String firstname, String lastname,
			String email, String country, String street, String city,
			String zip, String area, String birthdate, boolean readOnly,
			String password, String role) {

		DB db = connect.getConnection();
		String id = null;
		if (connect.auth("username", "password")) {
			id = Logic.determineAdminId(this.getClass());
			DBObject object = new BasicDBObject();
			object.put("firstname", firstname);
			object.put("lastname", lastname);
			object.put("email", email);
			object.put("country", country);
			object.put("street", street);
			object.put("city", city);
			object.put("zap", zip);
			object.put("area", area);
			object.put("birthdate", birthdate);
			object.put("readOnly", readOnly);
			object.put("password", password);
			object.put("role", role);
			object.put("id", id);
			db.createCollection(id, object);
		} else {
			return false;
		}

		if (getAdminById(id) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateAdminLastnameById(String lastname, String id) {
		DB db = connect.getConnection();
		if (connect.auth("username", "password")) {
			DBCollection coll = db.getCollection("database");
			DBObject ref = new BasicDBObject();
			ref.put("id", id);
			DBCursor cur = coll.find(ref);
			if (cur.hasNext()) {

				DBObject obj = cur.next();
				obj.put("lastname", lastname);
				db.createCollection(id, obj);
				// TODO: check if this works

			}

		}

		if (getAdminById(id).getLastname().equals(lastname)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateAdminMailById(String email, String id) {
		DB db = connect.getConnection();
		if (connect.auth("username", "password")) {
			DBCollection coll = db.getCollection("database");
			DBObject ref = new BasicDBObject();
			ref.put("id", id);
			DBCursor cur = coll.find(ref);
			if (cur.hasNext()) {

				DBObject obj = cur.next();
				obj.put("email", email);
				db.createCollection(id, obj);
				// TODO: check if this works

			}

		}

		if (getAdminById(id).getEmail().equals(email)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateAdminPasswordById(String old_password,
			String new_password, String id) {
		DB db = connect.getConnection();
		if (!old_password.equals(new_password)
				&& getAdminById(id).getPassword().equals(old_password)) {
            if (connect.auth("username", "password")) {
				DBCollection coll = db.getCollection("database");
				DBObject ref = new BasicDBObject();
				ref.put("id", id);
				DBCursor cur = coll.find(ref);
				if (cur.hasNext()) {

					DBObject obj = cur.next();
					obj.put("password", new_password);
					db.createCollection(id, obj);
					// TODO: check if this works

				}

			}

		}

		if (getAdminById(id).getPassword().equals(new_password)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateAdminRoleById(AdminRole role, String id) {
		DB db = connect.getConnection();
		if (connect.auth("username", "password")) {
			DBCollection coll = db.getCollection("database");
			DBObject ref = new BasicDBObject();
			ref.put("id", id);
			DBCursor cur = coll.find(ref);
			if (cur.hasNext()) {

				DBObject obj = cur.next();
				obj.put("role", role.name());
				db.createCollection(id, obj);
				// TODO: check if this works

			}

		}

		if (getAdminById(id).getRole().equals(role)) {
			return true;
		} else {
			return false;
		}
	}

}
