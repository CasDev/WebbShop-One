package se.casdev.product;

import java.io.File;
import java.sql.Blob;

import se.casdev.category.Category;

public class Product {

	private ProductType type;
	private String title;
	private String artist;
	private String price;
	private String categoryId;
	private String prodctId;
	private String inStock;
	private StockType typeInStock;
	private File picture;
	private Category category;
	private String release;
	private String label;

	public Product() {
		this.title = "";
		this.artist = "";
		this.price = "";
		this.type = null;
		this.categoryId = "";
		this.inStock = "";
		this.typeInStock = StockType.NOT_IN;
		this.picture = null;
		this.prodctId = "";
		this.category = new Category();
		this.release = "xxxx";
		this.label = "";
	}
	
	public Product(Product product) {
		this.title = product.getTitle();
		this.artist = product.getArtist();
		this.price = product.getPrice();
		this.type = product.getType();
		this.categoryId = product.getCategoryId();
		this.inStock = product.getInStock();
		this.typeInStock = product.getTypeInStock();
		this.picture = product.getPicture();
		this.prodctId = product.getProdctId();
		this.category = product.getCategory();
		this.label = product.getLabel();
		this.release = product.getRelease();
	}
	
	public String getLabel() {
		return label;
	}
	
	public Product setLabel(String label) {
		this.label = label;
		
		return this;
	}
	
	public String getRelease() {
		return release;
	}
	
	public Product setRelease(String release) {
		this.release = release;
		
		return this;
	}
	
	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	
	public Product setCategoryId(String categoryId) {
		this.categoryId = categoryId;
		
		return this;
	}

	public Product setTitle(String title) {
		this.title = title;
		
		return this;
	}

	public String getArtist() {
		return artist;
	}

	public Product setArtist(String artist) {
		this.artist = artist;
		
		return this;
	}

	public String getPrice() {
		return price;
	}

	public Product setPrice(String price) {
		this.price = price;
		
		return this;
	}

	public ProductType getType() {
		return type;
	}

	public Product setType(ProductType type) {
		this.type = type;
		
		return this;
	}

	public String getInStock() {
		return inStock;
	}

	public void setInStock(String inStock) {
		this.inStock = inStock;
		if (Integer.parseInt(inStock) <= 0) {
			if (!typeInStock.name().equals(StockType.ON_ORDER.name())) {
				this.typeInStock = StockType.NOT_IN;
			}
		}
	}

	public StockType getTypeInStock() {
		return typeInStock;
	}

	public Product setTypeInStock(StockType typeInStock) {
		this.typeInStock = typeInStock;
		
		return this;
	}

	public String getProdctId() {
		return prodctId;
	}

	public Product setProdctId(String prodctId) {
		this.prodctId = prodctId;
		
		return this;
	}

	public boolean isAvailable() {
		if (Integer.parseInt(this.inStock) > 0) {
			return true;
		}
		return false;
	}
	
	public File getPicture() {
		return picture;
	}
	
	public Product setPicture(File picture) {
		this.picture = picture;
		
		return this;
	}
	
}
