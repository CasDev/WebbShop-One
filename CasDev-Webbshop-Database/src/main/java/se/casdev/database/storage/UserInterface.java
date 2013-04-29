package se.casdev.database.storage;

import java.util.List;

import se.casdev.userInfo.Customer;
import se.casdev.userInfo.Address;

public interface UserInterface {

	Customer getUserById(String id);

	Customer loginWithUser(String email, String password);

	Customer getLatestCustomer();

	Address getAddressFromId(String id);

	List<Customer> getAllCustomers();

	boolean createUser(String firstname, String lastname, String birthdate,
			String available, String email, boolean readonly, String country,
			String city, String zip, String area, String street, String password);

	boolean updateUserReadOnlyById(boolean readOnly, String id);

	boolean updateUserAddressById(String country, String city, String zip,
			String area, String street, String id);

	boolean updateUserLastnameById(String lastname, String id);

	boolean updateUserMailById(String email, String id);

	boolean updateUserAvailableById(String available, String id);

	boolean updateUserPasswordById(String new_password, String old_password,
			String id);

}
