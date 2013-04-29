package se.casdev.order;

import java.util.ArrayList;
import java.util.List;

import se.casdev.product.Product;

public class Order {

	private List<Product> products;
	private String year;
	private String month;
	private String day;
	private String userId;
	private String orderid;
	private String orderDate;

	public Order() {
		this.products = new ArrayList<Product>();
		this.userId = "";
		this.year = "xxxx";
		this.month = "xx";
		this.day = "xx";
		this.orderid = "";
		this.orderDate = this.year + "/" + this.month + "/" + this.day;
	}

	public Order(Order order) {
		this.products = order.getProducts();
		this.userId = order.getUserId();
		this.year = order.getYear();
		this.month = order.getMonth();
		this.day = order.getDay();
		this.orderid = order.getOrderid();
	}

	public Integer getAmountOfProducts() {
		return this.products.size();
	}

	public List<Product> getProducts() {
		return products;
	}

	public Order setProducts(List<Product> products) {
		this.products = products;

		return this;
	}

	public String getUserId() {
		return userId;
	}

	public Order setUserId(String userId) {
		this.userId = userId;

		return this;
	}

	public String getTotalPrice() {
		int prices = 0;

		for (Product product : this.products) {
			int prize = Integer.parseInt(product.getPrice());
			prices += prize;
		}

		return Integer.toString(prices);
	}

	public String getYear() {
		return year;
	}

	public Order setYear(String year) {
		this.year = year;

		return this;
	}

	public String getMonth() {
		return month;
	}

	public Order setMonth(String month) {
		this.month = month;

		return this;
	}

	public String getDay() {
		return day;
	}

	public Order setDay(String day) {
		this.day = day;

		return this;
	}

	public String getOrderid() {
		return orderid;
	}

	public Order setOrderid(String orderid) {
		this.orderid = orderid;

		return this;
	}

	public String getOrderDate() {
		return orderDate;
	}

}
