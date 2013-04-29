package se.casdev.repository.database.repository.link;

import java.util.Calendar;
import java.util.List;

import se.casdev.database.storage.OrderInterface;
import se.casdev.product.Product;

public class OrderLink {

	private OrderInterface oInterface;

	protected OrderLink(OrderInterface oInterface) {
		this.oInterface = oInterface;
	}

	public boolean saveOrder(String userId, List<Product> products) {

		Integer price = 0;

		for (Product product : products) {
			price += Integer.parseInt(product.getPrice());
		}
		String total = price.toString();

		Calendar cal = Calendar.getInstance();
		Integer dayInt = cal.get(Calendar.DATE);
		String day = dayInt.toString();
		Integer yearInt = cal.get(Calendar.YEAR);
		String year = yearInt.toString();
		Integer monthInt = cal.get(Calendar.MONTH);
		String month = monthInt.toString();

		return oInterface
				.createOrder(year, month, day, userId, products, total);
	}
}
