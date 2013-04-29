package se.casdev.category;

public class Category {

	private String categoryId;
	private String name;
	private String parentId;
	private boolean empty;

	public Category() {
		this.categoryId = "";
		this.name = "";
		this.parentId = "";
		this.empty = false;
	}
	
	public Category(Category category) {
		this.categoryId = category.getCategoryId();
		this.name = category.getName();
		this.parentId = category.getParentId();
		this.empty = category.isEmpty();
	}

	public String getCategoryId() {
		return categoryId;
	}
	
	public Category setCategoryId(String categoryId) {
		this.categoryId = categoryId;
		
		return this;
	}
	
	public String getParentId() {
		return parentId;
	}
	
	public Category setParentId(String parentId) {
		this.parentId = parentId;
		
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public Category setName(String name) {
		this.name = name;
		
		return this;
	}
	
	public boolean isEmpty() {
		return empty;
	}
	
	public Category setEmpty(boolean empty) {
		this.empty = empty;
		
		return this;
	}

}
