package se.casdev.database.storage.implement;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.casdev.category.Category;
import se.casdev.database.access.ConnectingMySql;
import se.casdev.database.access.SQLString;
import se.casdev.database.base.Logic;
import se.casdev.database.storage.CategoryInterface;
import se.casdev.product.Product;
import se.casdev.product.ProductType;
import se.casdev.product.StockType;
import se.casdev.warnings.Messenge;
import se.casdev.warnings.Warning;

public class MySQLCategoryStorage implements CategoryInterface {

	ConnectingMySql connection = new ConnectingMySql();

	@Override
	public List<Category> getAllCategories() {
		Connection conn = connection.getConnection("database",
				"username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.CATEGORYS_GET);

			ResultSet set = statement.executeQuery();
			List<Category> list = new ArrayList<Category>();

			while (set.next()) {
				Category category = new Category();

				category.setCategoryId(set.getString("_category.categoryid"));
				category.setName(set.getString("_category.categoryname"));
				category.setParentId(set.getString("_category.parentid"));
				category.setEmpty(set.getBoolean("_category.empty"));

				list.add(category);
			}

			return new ArrayList<Category>(list);

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return null;
	}

	@Override
	public List<Product> getProductsFromCategoryId(String id) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.GET_PRODUCTS_BY_CATEGORY_ID);

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
				File file = null;
				try {
					new File(
						"which/folder/you/choose"
								+ product.getTitle() + ".extention");
					
				} catch (NullPointerException e) {
					file = null;
				}
				product.setPicture(file);

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
	public Category getCategoryByName(String name) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.GET_CATEGORY_BY_NAME);

			statement.setString(1, name);

			ResultSet set = statement.executeQuery();
			Category category = new Category();

			while (set.next()) {

				category.setCategoryId(set.getString("_category.categoryid"));
				category.setName(set.getString("_category.categoryname"));
				category.setParentId(set.getString("_category.parentid"));
				category.setEmpty(set.getBoolean("_category.empty"));

			}

			return new Category(category);

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return null;
	}

	@Override
	public boolean updateCategoryEmptyById(boolean emptyOrNot, String id) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.UPDATE_CATEGORY_EMPTY);

			statement.setBoolean(1, emptyOrNot);
			statement.setString(2, id);

			statement.executeUpdate();
			conn.commit();

			if (getCategoryById(id).isEmpty() == emptyOrNot) {
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
	public Category getCategoryById(String id) {
		Connection conn = connection.getConnection("database",
                                                   "username", "password");

		try {
			PreparedStatement statement = conn
					.prepareStatement(SQLString.GET_CATEGORY_BY_ID);

			statement.setString(1, id);

			ResultSet set = statement.executeQuery();
			Category category = new Category();

			while (set.next()) {

				category.setCategoryId(set.getString("_category.categoryid"));
				category.setName(set.getString("_category.categoryname"));
				category.setParentId(set.getString("_category.parentid"));
				category.setEmpty(set.getBoolean("_category.empty"));

			}

			return new Category(category);

		} catch (SQLException e) {
			Warning.addClientMessage(new Messenge("subject", "message"));
			Warning.addServerMessage(new Messenge("subject", "message"));
		} finally {
			connection.closeConnection(conn);
		}

		return null;
	}

	@Override
	public Category getLatesCategory() {
		List<Category> categories = getAllCategories();
		int size = (categories.size() - 1);
		return categories.get(size);
	}

	@Override
	public boolean createCategory(String name, boolean empty, String parentId) {
		// TODO Auto-generated method stub
		return false;
	}

}
