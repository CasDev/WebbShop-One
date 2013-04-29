package se.casdev.database.storage.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.casdev.database.access.ConnectingMySql;
import se.casdev.database.access.SQLString;
import se.casdev.database.base.Logic;
import se.casdev.database.storage.UserInterface;
import se.casdev.userInfo.Address;
import se.casdev.userInfo.Customer;
import se.casdev.userInfo.CustomerAvailability;
import se.casdev.warnings.Messenge;
import se.casdev.warnings.Warning;

public class MySQLUserStorage implements UserInterface {

	ConnectingMySql connection = new ConnectingMySql();

	@Override
	public Customer getUserById(String id) {

		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.USER_GET_BY_ID);

			statement.setString(1, id);

			ResultSet set = statement.executeQuery();
			Customer customer = new Customer();

			while (set.next()) {
				customer.setFirstname(set.getString("_customer.firstname"));
				customer.setLastname(set.getString("_customer.lastname"));
				customer.setBirthDate(set.getString("_customer.birthdate"));
				customer.setAvailable(CustomerAvailability.valueOf(set
						.getString("_customer.available")));
				customer.setEmail(set.getString("_customer.email"));
				customer.setReadOnly(set.getBoolean("_customer.readonly"));
				customer.setUserId(id);
				customer.setPassword(set.getString("_customer.password"));
				customer.getAddress().setCountry("_address.country");
				customer.getAddress().setCity("_address.city");
				customer.getAddress().setArea("_address.area");
				customer.getAddress().setStreet("_address.street");
				customer.getAddress().setZip("_address.zip");
			}

			return new Customer(customer);

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return null;
	}

	@Override
	public Customer loginWithUser(String email, String password) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.LOGIN_USER);

			statement.setString(1, email);
			statement.setString(2, password);

			ResultSet set = statement.executeQuery();
			Customer customer = new Customer();

			while (set.next()) {
				customer.setFirstname(set.getString("_customer.firstname"));
				customer.setLastname(set.getString("_customer.lastname"));
				customer.setBirthDate(set.getString("_customer.birthdate"));
				customer.setAvailable(CustomerAvailability.valueOf(set
						.getString("_customer.available")));
				customer.setEmail(email);
				customer.setReadOnly(set.getBoolean("_customer.readonly"));
				customer.setUserId(set.getString("_customer.userid"));
				customer.setPassword(password);
				customer.getAddress().setCountry("_address.country");
				customer.getAddress().setCity("_address.city");
				customer.getAddress().setArea("_address.area");
				customer.getAddress().setStreet("_address.street");
				customer.getAddress().setZip("_address.zip");
			}

			return new Customer(customer);

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return null;
	}

	@Override
	public Customer getLatestCustomer() {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.USER_LATEST);

			ResultSet set = statement.executeQuery();
			Customer customer = new Customer();

			while (set.next()) {
				customer.setFirstname(set.getString("_customer.firstname"));
				customer.setLastname(set.getString("_customer.lastname"));
				customer.setBirthDate(set.getString("_customer.birthdate"));
				customer.setAvailable(CustomerAvailability.valueOf(set
						.getString("_customer.available")));
				customer.setEmail(set.getString("_customer.email"));
				customer.setReadOnly(set.getBoolean("_customer.readonly"));
				customer.setUserId(set.getString("_customer.userid"));
				customer.setPassword(set.getString("_customer.password"));
				customer.getAddress().setCountry("_address.country");
				customer.getAddress().setCity("_address.city");
				customer.getAddress().setArea("_address.area");
				customer.getAddress().setStreet("_address.street");
				customer.getAddress().setZip("_address.zip");
			}

			return new Customer(customer);

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return null;
	}

	@Override
	public Address getAddressFromId(String id) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.ADDRESS_GET_BY_ID);

			statement.setString(1, id);

			ResultSet set = statement.executeQuery();
			Address address = new Address();

			while (set.next()) {

				address.setArea(set.getString("_address.area"));
				address.setCity(set.getString("_address.city"));
				address.setCountry(set.getString("_address.country"));
				address.setStreet(set.getString("_address.city"));
				address.setZip(set.getString("_address.zip"));

			}

			return new Address(address);

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.GET_ALL_USERS);

			ResultSet set = statement.executeQuery();
			List<Customer> list = new ArrayList<Customer>();

			while (set.next()) {
				Customer customer = new Customer();

				customer.setFirstname(set.getString("_customer.firstname"));
				customer.setLastname(set.getString("_customer.lastname"));
				customer.setBirthDate(set.getString("_customer.birthdate"));
				customer.setAvailable(CustomerAvailability.valueOf(set
						.getString("_customer.available")));
				customer.setEmail(set.getString("_customer.email"));
				customer.setReadOnly(set.getBoolean("_customer.readonly"));
				customer.setUserId(set.getString("_customer.userid"));
				customer.setPassword(set.getString("_customer.password"));
				customer.getAddress().setCountry("_address.country");
				customer.getAddress().setCity("_address.city");
				customer.getAddress().setArea("_address.area");
				customer.getAddress().setStreet("_address.street");
				customer.getAddress().setZip("_address.zip");

				list.add(new Customer(customer));
			}

			return new ArrayList<Customer>(list);

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return null;
	}

	@Override
	public boolean createUser(String firstname, String lastname,
			String birthdate, String available, String email, boolean readonly,
			String country, String city, String zip, String area,
			String street, String password) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.USER_CREATE);

			String id = Logic.determineUserId(this.getClass());
			statement.setString(1, firstname);
			statement.setString(2, lastname);
			statement.setString(3, birthdate);
			statement.setString(4, available);
			statement.setString(5, email);
			statement.setBoolean(6, readonly);
			statement.setString(7, id);
			statement.setString(8, password);

			statement.executeUpdate();
			conn.commit();

			if (getUserById(id) != null) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return false;

	}

	@Override
	public boolean updateUserReadOnlyById(boolean readOnly, String id) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.UPDATE_USER_READONLY_BY_ID);

			statement.setBoolean(1, readOnly);
			statement.setString(2, id);

			statement.executeUpdate();
			conn.commit();

			if (getUserById(id).isReadOnly() == readOnly) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return false;
	}

	@Override
	public boolean updateUserLastnameById(String lastname, String id) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.UPDATE_USER_LASTNAME_BY_ID);

			statement.setString(1, lastname);
			statement.setString(2, id);

			statement.executeUpdate();
			conn.commit();

			if (getUserById(id).getLastname().equals(lastname)) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return false;
	}

	@Override
	public boolean updateUserMailById(String email, String id) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.UPDATE_USER_EMAIL_BY_ID);

			statement.setString(1, email);
			statement.setString(2, id);

			statement.executeUpdate();
			conn.commit();

			if (getUserById(id).getEmail().equals(email)) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return false;
	}

	@Override
	public boolean updateUserAvailableById(String available, String id) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.UPDATE_USER_AVAILABLE_BY_ID);

			statement.setString(1, available);
			statement.setString(2, id);

			statement.executeUpdate();
			conn.commit();

			if (getUserById(id).getAvailable().name().equals(available)) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return false;
	}

	@Override
	public boolean updateUserAddressById(String country, String city,
			String zip, String area, String street, String id) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.UPDATE_ADDRESS_BY_ID);

			statement.setString(1, country);
			statement.setString(2, city);
			statement.setString(3, zip);
			statement.setString(4, area);
			statement.setString(5, street);
			statement.setString(6, id);

			statement.executeUpdate();
			conn.commit();
			Address address = new Address();

			address.setCountry(country);
			address.setCity(city);
			address.setZip(zip);
			address.setArea(area);
			address.setStreet(street);

			if (getUserById(id).getAddress().equals(address)) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return false;
	}

	@Override
	public boolean updateUserPasswordById(String new_password,
			String old_password, String id) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.UPDATE_USER_PASSWORD_BY_ID);

			statement.setString(1, new_password);
			statement.setString(2, old_password);
			statement.setString(3, id);

			statement.executeUpdate();
			conn.commit();

			if (getUserById(id).getPassword().equals(new_password)) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return false;
	}

}
