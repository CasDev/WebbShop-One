package se.casdev.database.storage.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jcouchdb.db.Database;
import org.jcouchdb.document.BaseDocument;
import org.jcouchdb.document.ValueRow;
import org.jcouchdb.document.ViewResult;
import org.jcouchdb.exception.NotFoundException;

import se.casdev.category.Category;
import se.casdev.database.access.ConnectingCouchDB;
import se.casdev.database.base.Logic;
import se.casdev.database.storage.CategoryInterface;
import se.casdev.database.storage.ProductInterface;
import se.casdev.product.Product;

public class CouchDBCategoryStorage implements CategoryInterface {

	ConnectingCouchDB connection = new ConnectingCouchDB();

	public CouchDBCategoryStorage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Category> getAllCategories() {
		Database db = connection.getConnection("database", "username",
				"password");
		List<Category> cats = new ArrayList<Category>();
		ViewResult<Map> list = db.listDocuments(null, null);
		for (ValueRow row : list.getRows()) {
			String id = list.getId();
			Category cat = getCategoryById(id);
			cats.add(cat);
		}

		return cats;
	}

	@Override
	public Category getLatesCategory() {
		List<Category> cats = getAllCategories();
		int size = (cats.size() - 1);

		return cats.get(size);
	}

	@Override
	public List<Product> getProductsFromCategoryId(String id) {
		List<Product> list = new ArrayList<Product>();
		ProductInterface product = new CouchDBProductStorage();

		List<Product> prods = product.getAllProducts();
		for (Product prod : prods) {

			if (prod.getCategoryId().equals(id)) {
				list.add(prod);
			}

		}

		return list;

	}

	@Override
	public Category getCategoryByName(String name) {
		Database db = connection.getConnection("database", "username",
				"password");
		String viewName = "getCategoryByName";
		String mapFn = "function(doc) { if (doc.name === \"" + name
				+ "\" ) emit(doc._id, doc._id); }";

		List<ValueRow<BaseDocument>> rows = connection.createView(db, viewName,
				mapFn);
		if (rows == null) {
			return null;
		}

		Category category = null;
		for (ValueRow<BaseDocument> valueRow : rows) {
			category = getCategoryById(valueRow.getId());
		}

		return category;
	}

	@Override
	public Category getCategoryById(String id) {

		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = null;
		try {
			doc = db.getDocument(BaseDocument.class, id);
		} catch (NotFoundException e) {
			doc = null;
		}

		if (doc == null) {
			return null;
		}
		Category cat = new Category();
		cat.setCategoryId(id);
		cat.setEmpty(Boolean.parseBoolean(doc.getProperty("empty").toString()));
		cat.setName(doc.getProperty("name").toString());
		cat.setParentId(doc.getProperty("parentId").toString());

		return cat;
	}

	@Override
	public boolean updateCategoryEmptyById(boolean emptyOrNot, String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = null;
		try {
			doc = db.getDocument(BaseDocument.class, id);
		} catch (NotFoundException e) {
			doc = null;
		}

		if (doc == null) {
			return false;
		}
		doc.setProperty("empty", emptyOrNot);
		db.updateDocument(doc);

		if (getCategoryById(id).isEmpty() == emptyOrNot) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean createCategory(String name, boolean empty, String parentId) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = new BaseDocument();
		String id = Logic.determineCategoryId(name, this.getClass());
		doc.setId(id);
		doc.setProperty("name", name);
		doc.setProperty("empty", empty);
		doc.setProperty("parentId", parentId);
		doc.setProperty("id", id);
		db.createDocument(doc);

		if (getCategoryById(id) != null) {
			return true;
		} else {
			return false;
		}

	}

}
