package se.casdev.database.storage.implement;

import java.util.List;

import se.casdev.database.storage.AdminInterface;
import se.casdev.userInfo.Admin;
import se.casdev.userInfo.AdminRole;

public class MySQLAdminStorage implements AdminInterface {

	@Override
	public boolean createAdminstration(String firstname, String lastname,
			String email, String country, String street, String city,
			String zip, String area, String birthdate, boolean readOnly,
			String password, String role) {
		return false;

	}

	@Override
	public Admin getAdminById(String id) {
		return null;
	}

	@Override
	public boolean updateAdminLastnameById(String lastname, String id) {
		return false;
	}

	@Override
	public boolean updateAdminMailById(String email, String id) {
		return false;
	}

	@Override
	public boolean updateAdminPasswordById(String old_password,
			String new_password, String id) {
		return false;
	}

	@Override
	public boolean updateAdminRoleById(AdminRole role, String id) {
		return false;
	}

	@Override
	public Admin loginAdmin(String username, String password) {
		return null;
	}

	@Override
	public Admin getLatestAdmin() {
		List<Admin> list = getAllAdmins();
		int size = (list.size() - 1);
		return list.get(size);
	}

	@Override
	public List<Admin> getAllAdmins() {
		return null;
	}

}
