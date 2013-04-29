package se.casdev.database.storage.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jcouchdb.db.Database;
import org.jcouchdb.document.BaseDocument;
import org.jcouchdb.document.ValueRow;
import org.jcouchdb.document.ViewResult;

import se.casdev.database.access.ConnectingCouchDB;
import se.casdev.database.base.Logic;
import se.casdev.database.storage.OrderInterface;
import se.casdev.database.storage.ProductInterface;
import se.casdev.order.Order;
import se.casdev.product.Product;

public class CouchDBOrderStorage implements OrderInterface {

	ConnectingCouchDB connection = new ConnectingCouchDB();

	public CouchDBOrderStorage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Order getLatestOrder() {

		List<Order> orders = getAllOrders();
		int size = (orders.size() - 1);
		Order order = orders.get(size);
		return order;
	}

	@Override
	public Order getOrderFromId(String id) {

		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		Order order = new Order();
		order.setDay(doc.getProperty("day").toString());
		order.setMonth(doc.getProperty("month").toString());
		order.setYear(doc.getProperty("year").toString());
		order.setOrderid(id);
		List<Product> products = getProductsFromOrderId(id);
		order.setUserId(doc.getProperty("userid").toString());
		order.setProducts(products);

		return order;
	}

	@Override
	public List<Order> getOrdersFromUserId(String id) {

		Database db = connection.getConnection("database", "username",
				"password");
		String viewName = "getByName";
		String mapFn = "function(doc) { if(doc.userid === \"" + id
				+ "\") emit(doc.id, doc)}";
		List<ValueRow<BaseDocument>> list = connection.createView(db, viewName,
				mapFn);
		if (list == null) {
			return null;
		}
		List<Order> send = new ArrayList<Order>();
		for (ValueRow<BaseDocument> valueRow : list) {
			String orderid = valueRow.getId();
			Order order = getOrderFromId(orderid);
			send.add(order);
		}

		return send;
	}

	@Override
	public List<Product> getProductsFromOrderId(String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		ProductInterface product = new CouchDBProductStorage();
		List<Product> products = new ArrayList<Product>();
		long amountsOfProduct = (Long) doc.getProperty("amountsOfProducts");
		Map<String, Object> prods = (HashMap<String, Object>) doc
				.getProperty("products");
		for (int i = 1; i <= amountsOfProduct; i++) {
			String prodId = prods.get("productId #" + i).toString();
			Product prod = product.getProductFromProductId(prodId);
			products.add(prod);
		}

		return products;
	}

	@Override
	public boolean createOrder(String year, String month, String day,
			String userid, List<Product> products, String totalprice) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = new BaseDocument();
		String id = Logic.determineOrderId(userid, this.getClass());
		doc.setProperty("year", year);
		doc.setProperty("month", month);
		doc.setProperty("day", day);
		doc.setId(id);
		doc.setProperty("orderid", id);
		doc.setProperty("userid", userid);
		doc.setProperty("totalprice", totalprice);
		int i = 1;
		BaseDocument docs = new BaseDocument();
		for (Product product : products) {
			docs.setProperty("productId #" + i, product.getProdctId());
			i++;
		}
		doc.setProperty("products", docs);
		doc.setProperty("amountsOfProducts", products.size());
		doc.setId(id);
		db.createDocument(doc);

		if (getOrderFromId(id) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void deleteOrderById(String id) {
		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		db.delete(doc);

	}

	@Override
	public void deleteDetailByOrderId(String id) {

		Database db = connection.getConnection("database", "username",
				"password");
		BaseDocument doc = db.getDocument(BaseDocument.class, id);
		BaseDocument docs = new BaseDocument();
		docs.setProperty("productId #1", "-");
		doc.setProperty("products", docs);
		db.updateDocument(doc);

	}

	@Override
	public List<Order> getAllOrders() {

		Database db = connection.getConnection("database", "username",
				"password");
		ViewResult<Map> list = db.listDocuments(null, null);
		List<Order> orders = new ArrayList<Order>();
		for (ValueRow row : list.getRows()) {
			String id = row.getId();
			Order order = getOrderFromId(id);
			orders.add(order);
		}

		return orders;
	}

}
