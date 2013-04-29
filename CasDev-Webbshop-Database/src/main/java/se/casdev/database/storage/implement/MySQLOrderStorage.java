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
import se.casdev.database.storage.OrderInterface;
import se.casdev.order.Order;
import se.casdev.product.Product;
import se.casdev.product.ProductType;
import se.casdev.product.StockType;
import se.casdev.warnings.Messenge;
import se.casdev.warnings.Warning;

public class MySQLOrderStorage implements OrderInterface {

	ConnectingMySql connection = new ConnectingMySql();

	@Override
	public Order getLatestOrder() {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.ORDER_LATEST);

			ResultSet set = statement.executeQuery();
			Order order = new Order();

			while (set.next()) {
				order.setYear(set.getString("_order.year"));
				order.setMonth(set.getString("_order.month"));
				order.setMonth(set.getString("_order.day"));
				order.setUserId(set.getString("_order.userid"));
				order.setOrderid(set.getString("_order.orderid"));
				order.setProducts(getProductsFromOrderId(order.getOrderid()));
			}

			return new Order(order);

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return null;

	}

	@Override
	public Order getOrderFromId(String id) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.ORDER_GET_BY_ID);

			statement.setString(1, id);

			ResultSet set = statement.executeQuery();
			Order order = new Order();

			while (set.next()) {
				order.setYear(set.getString("_order.year"));
				order.setMonth(set.getString("_order.month"));
				order.setMonth(set.getString("_order.day"));
				order.setUserId(set.getString("_order.userid"));
				order.setOrderid(set.getString("_order.orderid"));
				order.setProducts(getProductsFromOrderId(id));
			}

			return new Order(order);

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return null;
	}

	@Override
	public List<Order> getOrdersFromUserId(String id) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.ORDER_GET_BY_USERID);

			statement.setString(1, id);

			ResultSet set = statement.executeQuery();
			List<Order> list = new ArrayList<Order>();

			while (set.next()) {
				Order order = new Order();

				order.setYear(set.getString("_order.year"));
				order.setMonth(set.getString("_order.month"));
				order.setMonth(set.getString("_order.day"));
				order.setUserId(set.getString("_order.userid"));
				order.setOrderid(set.getString("_order.orderid"));
				order.setProducts(getProductsFromOrderId(id));

				list.add(order);
			}

			return new ArrayList<Order>(list);

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return null;

	}

	@Override
	public List<Product> getProductsFromOrderId(String id) {

		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.GET_PRODUCTS_BY_ORDER_ID);

			statement.setString(1, id);

			ResultSet set = statement.executeQuery();
			List<Product> list = new ArrayList<Product>();

			while (set.next()) {
				Product product = new Product();

				product.setTitle(set.getString("_product.title"));
				product.setPrice(set.getString("_product.price"));
				product.setTypeInStock(StockType.valueOf(set
						.getString("_product.typeinstock")));
				product.setInStock(set.getString("_product.numberinstock"));
				product.setType(ProductType.valueOf(set
						.getString("_product.producttype")));
				product.setProdctId(set.getString("_product.productid"));
				product.setArtist(set.getString("_product.artist"));
				product.setCategoryId(set.getString("_product.categoryid"));
				product.setLabel(set.getString("_product.label"));
				product.setRelease(set.getString("_product.release"));
				product.setPicture(null);

				list.add(product);
			}

			return new ArrayList<Product>(list);

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return null;
	}

	@Override
	public boolean createOrder(String year, String month, String day,
			String userid, List<Product> products, String totalprice) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.ORDER_CREATE);

			String orderid = Logic.determineOrderId(userid, this.getClass());
			statement.setString(1, userid);
			statement.setString(2, year);
			statement.setString(3, month);
			statement.setString(4, day);
			statement.setString(5, orderid);
			statement.setString(6, totalprice);

			statement.executeUpdate();
			conn.commit();

			if (getOrderFromId(orderid) != null) {
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
	public void deleteOrderById(String id) {

	}

	@Override
	public void deleteDetailByOrderId(String id) {

	}

	@Override
	public List<Order> getAllOrders() {

		return null;
	}

}
