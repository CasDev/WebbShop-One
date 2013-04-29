package se.casdev.database.storage;

import java.util.List;

import se.casdev.category.Category;
import se.casdev.product.Product;

public interface CategoryInterface {
	
	List<Category> getAllCategories();
	
	Category getLatesCategory();
	
	List<Product> getProductsFromCategoryId(String id);
	
	Category getCategoryByName(String name);
	
	Category getCategoryById(String id);
	
	boolean updateCategoryEmptyById(boolean emptyOrNot, String id);
	
	boolean createCategory(String name, boolean empty, String parentId);

}
