package se.casdev.repository.database.repository.link;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se.casdev.database.storage.ProductInterface;
import se.casdev.product.Product;

public class ProductLink {

	private ProductInterface pInterface;

	protected ProductLink(ProductInterface pInterface) {
		this.pInterface = pInterface;
	}

	public Product getProduct(String id) {
		if (Validator.validateProductId(id)) {
			return pInterface.getProductFromProductId(id);
		} else {
			return null;
		}
	}

	public List<Product> getAll() {
		return pInterface.getAllProducts();
	}

}
