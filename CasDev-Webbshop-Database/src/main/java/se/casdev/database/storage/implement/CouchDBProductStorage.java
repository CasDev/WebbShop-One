package se.casdev.database.storage.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jcouchdb.db.Database;
import org.jcouchdb.document.BaseDocument;
import org.jcouchdb.document.ValueRow;
import org.jcouchdb.document.ViewResult;

import se.casdev.database.access.ConnectingCouchDB;
import se.casdev.database.base.Logic;
import se.casdev.database.storage.ProductInterface;
import se.casdev.product.Product;
import se.casdev.product.ProductType;
import se.casdev.product.StockType;
import se.casdev.warnings.Messenge;
import se.casdev.warnings.Warning;

public class CouchDBProductStorage implements ProductInterface {

	ConnectingCouchDB connection = new ConnectingCouchDB();

	public CouchDBProductStorage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Product getLatestProduct() {
		List<Product> products = getAllProducts();
		int size = (products.size() - 1);
		Product product = products.get(size);
		return product;
	}

	@Override
	public Product getProductFromProductId(String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		Product product = new Product();
		product.setTitle(doc.getProperty("title").toString());
		product.setArtist(doc.getProperty("artist").toString());
		product.setCategoryId(doc.getProperty("categoryId").toString());
		product.setInStock(doc.getProperty("inStock").toString());
		product.setLabel(doc.getProperty("label").toString());
		File file = null;
		try {
			new File("/which/folder/you/choose" + product.getTitle() + ".extention");

		} catch (NullPointerException e) {
			file = null;
		}
		product.setPicture(file);
		product.setPrice(doc.getProperty("price").toString());
		product.setProdctId(id);
		product.setRelease(doc.getProperty("release").toString());
		product.setType(ProductType.valueOf(doc.getProperty("productType")
				.toString()));
		product.setTypeInStock(StockType.valueOf(doc.getProperty("stockType")
				.toString()));

		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		Database db = connection.getConnection("database", "username",
				"password");
		ViewResult<Map> list = db.listDocuments(null, null);
		List<Product> products = new ArrayList<Product>();
		for (ValueRow row : list.getRows()) {

			String id = row.getId();
			Product product = getProductFromProductId(id);
			products.add(product);

		}
		return products;
	}

	@Override
	public boolean createProduct(String title, String price,
			String numberinstock, String typeinstock, String producttype,
			String artist, String categoryid, File filePath, String label,
			String release) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = new BaseDocument();
		String id = Logic.determineProductId(producttype, this.getClass());
		doc.setProperty("stockType", typeinstock);
		doc.setProperty("productType", producttype);
		doc.setProperty("title", title);
		doc.setProperty("release", release);
		doc.setProperty("price", price);
		doc.setProperty("label", label);
		doc.setProperty("inStock", numberinstock);
		doc.setProperty("categoryId", categoryid);
		doc.setProperty("artist", artist);
		doc.setProperty("productId", id);
		doc.setId(id);
		boolean rename = false;
		File file = null;
		if (filePath != null) {
			file = filePath;
			rename = file.renameTo(new File("/which/folder/you/choose/" + title
					+ ".extention"));
			// althou this will delete the file
		} else {

			// then there isn't any file :P
			file = null;

		}
		db.createDocument(doc);

		if (rename) {
			if (getProductFromProductId(id) != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	@Override
	public boolean updateProductPrice(String price, String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		doc.setProperty("price", price);
		db.updateDocument(doc);
		String viewName = "productPrice";
		String mapFn = "function(doc) { if (doc.price === \"" + price
				+ "\" && doc._id === \"" + id + "\") emit(doc._id, doc._id); }";

		List<ValueRow<BaseDocument>> rows = connection.createView(db, viewName,
				mapFn);

		if (rows != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean updateProductNumberInStock(String inStock, String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		doc.setProperty("inStock", inStock);
		db.updateDocument(doc);

		String viewName = "productNumberInStock";
		String mapFn = "function(doc) { if (doc.inStock === \"" + inStock
				+ "\" && doc._id === \"" + id + "\") emit(doc._id, doc._id); }";

		List<ValueRow<BaseDocument>> rows = connection.createView(db, viewName,
				mapFn);

		if (rows != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean updateProductTypeInStock(String type, String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		doc.setProperty("stockType", type);
		db.updateDocument(doc);

		String viewName = "productStockType";
		String mapFn = "function(doc) { if (doc.stockType === \"" + type
				+ "\" && doc._id === \"" + id + "\") emit(doc._id, doc._id); }";

		List<ValueRow<BaseDocument>> rows = connection.createView(db, viewName,
				mapFn);

		if (rows != null) {
			return true;
		} else {
			return false;
		}

	}

}
