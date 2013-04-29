package se.casdev.database.storage;

import java.util.List;

import se.casdev.order.Order;
import se.casdev.product.Product;

public interface OrderInterface {

	Order getLatestOrder();

	Order getOrderFromId(String id);
	
	List<Order> getAllOrders();

	List<Order> getOrdersFromUserId(String id);

	List<Product> getProductsFromOrderId(String id);

	boolean createOrder(String year, String month, String day,
			String userid, List<Product> products, String totalprice);
	
	void deleteOrderById(String id);
	
	void deleteDetailByOrderId(String id);

}
