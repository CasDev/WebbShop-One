package se.casdev.database.storage;

import java.io.File;
import java.util.List;

import se.casdev.product.Product;

public interface ProductInterface {

	Product getLatestProduct();

	Product getProductFromProductId(String id);

	List<Product> getAllProducts();

	boolean createProduct(String title, String price, String numberinstock,
			String typeinstock, String producttype,
			String artist, String categoryid, File filePath, String label,
			String release);
	
	boolean updateProductPrice(String price, String id);
	
	boolean updateProductNumberInStock(String inStock, String id);
	
	boolean updateProductTypeInStock(String type, String id);

}
