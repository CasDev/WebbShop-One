package se.casdev.database.storage.implement;

import java.io.File;
import java.util.List;

import se.casdev.database.base.Logic;
import se.casdev.database.storage.ProductInterface;
import se.casdev.product.Product;

public class MySQLProductStorage implements ProductInterface {

	@Override
	public Product getLatestProduct() {
		return null;
	}

	@Override
	public Product getProductFromProductId(String id) {
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		return null;
	}

	@Override
	public boolean createProduct(String title, String price,
			String numberinstock, String typeinstock, String producttype,
			String artist, String categoryid, File filePath, String label,
			String release) {
		String id = Logic.determineProductId(producttype, this.getClass());

		return false;

	}

	@Override
	public boolean updateProductPrice(String price, String id) {
		return false;
	}

	@Override
	public boolean updateProductNumberInStock(String inStock, String id) {
		return false;
	}

	@Override
	public boolean updateProductTypeInStock(String type, String id) {
		return false;
	}

}
