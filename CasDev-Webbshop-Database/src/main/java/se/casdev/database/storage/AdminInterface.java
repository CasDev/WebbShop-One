package se.casdev.database.storage;

import java.util.List;

import se.casdev.userInfo.Admin;
import se.casdev.userInfo.AdminRole;

public interface AdminInterface {

	Admin getLatestAdmin();

	List<Admin> getAllAdmins();

	Admin loginAdmin(String email, String password);

	Admin getAdminById(String id);

	boolean createAdminstration(String firstname, String lastname,
			String email, String country, String street, String city,
			String zip, String area, String birthdate, boolean readOnly,
			String password, String role);

	boolean updateAdminLastnameById(String lastname, String id);

	boolean updateAdminMailById(String email, String id);

	boolean updateAdminPasswordById(String old_password, String new_password,
			String id);

	boolean updateAdminRoleById(AdminRole role, String id);

}
