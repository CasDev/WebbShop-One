package se.casdev.spring.web.controller;

import java.util.ArrayList;
import java.util.List;

import se.casdev.product.Product;
import se.casdev.repository.database.repository.link.MainLink;
import se.casdev.userInfo.Customer;

public class Controller {

	public static MainLink link = MainLink.COUCHDB;

	public static void main(String[] args) {

		System.out.println(link.getCategoryLink().getAllCadegories().get(1));

		List<Product> products = link.getProductLink().getAll();
		System.out.println(products.get(7));
		System.out.println(link.getCategoryLink().getAllFromCategory("COM100")
				.get(0));
		List<Product> list = new ArrayList<Product>();
		list.add(products.get(5));
		list.add(products.get(2));
		list.add(products.get(1));
		list.add(products.get(8));

		System.out.println(link.getUserLink().signup("firstname", "lastname",
				"an.email@hmail.net", "secretly", "1980-01-01", "Sweden",
				"Tyreso", "13501", "Street 89", "Sthlm"));
		Customer user = link.getUserLink()
				.login("an.email@hmail.net", "secretly");
		System.out.println(user.getEmail());

		System.out.println(link.getOrderLink().saveOrder(user.getUserId(),
				products));
		System.out.println(link.getUserLink().updateUserPassword("rispig",
				user.getPassword(), user.getUserId()));
		System.out.println(link.getUserLink()
				.login("an.mail@hmail.net", "rispig").getPassword());

	}
}
