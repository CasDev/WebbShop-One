package se.casdev.spring.web.form;

import org.springframework.stereotype.Component;

@Component
public class AddProductForm {

	private String id;
	private String quantity;

	public AddProductForm() {
		this.id = "";
		this.quantity = "";
	}

	public String getId() {
		return id;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
