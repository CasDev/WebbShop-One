package se.casdev.userInfo;

import java.util.ArrayList;
import java.util.List;

import se.casdev.order.Order;

public class Customer extends User {

	private CustomerAvailability available;
	private List<Order> orderList;

	public Customer() {
		super();
		this.orderList = new ArrayList<Order>();
		this.available = CustomerAvailability.AWAITING;
	}
	
	public Customer(Customer customer) {
		this.setAvailable(customer.getAvailable());
		this.setBirthDate(customer.getYear() +"-"+ customer.getMonth() +"-"+ customer.getDay());
		this.setEmail(customer.getEmail());
		this.setFirstname(customer.getFirstname());
		this.setLastname(customer.getLastname());
		this.setPassword(customer.getPassword());
		this.setReadOnly(customer.isReadOnly());
		this.setUserId(customer.getUserId());
		this.getAddress().setArea(customer.getAddress().getArea());
		this.getAddress().setCity(customer.getAddress().getCity());
		this.getAddress().setCountry(customer.getAddress().getCountry());
		this.getAddress().setStreet(customer.getAddress().getStreet());
		this.getAddress().setZip(customer.getAddress().getZip());
	}

	public String toAddress() {
		String addressNote = "";

		addressNote = addressNote.concat(super.getFirstname() + " "
				+ super.getLastname());
		addressNote = addressNote.concat("\n");
		addressNote = addressNote.concat(super.getAddress().getStreet());
		addressNote = addressNote.concat("\n");
		addressNote = addressNote.concat(super.getAddress().getZip() + " "
				+ super.getAddress().getArea());
		addressNote = addressNote.concat("\n");
		addressNote = addressNote.concat(super.getAddress().getCountry());

		return addressNote;
	}

	public ArrayList<Order> getOrders() {
		return (ArrayList<Order>) orderList;
	}

	public Customer setAvailable(CustomerAvailability available) {
		this.available = available;
		
		return this;
	}
	
	public CustomerAvailability getAvailable() {
		return available;
	}
	
}
