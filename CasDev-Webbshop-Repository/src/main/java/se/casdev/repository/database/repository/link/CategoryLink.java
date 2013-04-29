package se.casdev.repository.database.repository.link;

import java.util.List;

import se.casdev.category.Category;
import se.casdev.database.storage.CategoryInterface;
import se.casdev.product.Product;

public class CategoryLink {

	private CategoryInterface cInterface;

	protected CategoryLink(CategoryInterface cInterface) {
		this.cInterface = cInterface;
	}

	public List<Category> getAllCadegories() {

		return cInterface.getAllCategories();

	}

	public boolean saveCategory(String name, String parentId) {

		boolean nameOK = (name.length() > 3);
		boolean parentIdOK = (findCategory(parentId) != null);

		if (nameOK && parentIdOK) {

			return cInterface.createCategory(name, true, parentId);

		} else {
			return false;
		}
	}

	public Category findCategory(String id) {
		boolean idOK = Validator.validateCategoryId(id);

		if (idOK) {
			return cInterface.getCategoryById(id);
		} else {
			return null;
		}
	}

	public List<Product> getAllFromCategory(String id) {
		return cInterface.getProductsFromCategoryId(id);
	}

}
