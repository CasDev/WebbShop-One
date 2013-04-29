package se.casdev.database.access;

public class SQLString {

	// products
	public static final String PRODUCT_LATEST = "SELECT * FROM _product INNER JOIN _category ON _product.categoryid = _category.categoryid INNER JOIN _prodimg ON _product.productid = _prodimg.productid ORDER BY _product.productid ASC";
	public static final String PRODUCTS_GET = "SELECT * FROM _product INNER JOIN _category ON _product.categoryid = _category.categoryid INNER JOIN _prodimg ON _product.productid = _prodimg.productid";
	public static final String PRODUCT_GET_BY_CATEGORY_ID = "SELECT * FROM _product INNER JOIN _category ON _product.categoryid = _category.categoryid INNER JOIN _prodimg ON _product.productid = _prodimg.productid WHERE _product.categoryid = ? AND _product.categoryid = _category.categoryid AND _product.productid = _prodimg.productid ORDER BY _product.productid ASC";
	public static final String PRODUCT_GET_BY_PRODUCT_ID = "SELECT * FROM _product INNER JOIN _category ON _product.categoryid = _category.categoryid INNER JOIN _prodimg ON _product.productid = _prodimg.productid WHERE _product.productid = ? AND _product.categoryid = _category.categoryid AND _product.productid = _prodimg.productid";
	public static final String GET_PRODUCTS_BY_ORDER_ID = "SELECT * FROM _product INNER JOIN _prodimg ON _product.productid = _prodimg.productid INNER JOIN _category ON _product.categoryid = _category.categoryid INNER JOIN _detail ON _product.productid = _detail.productid INNER JOIN _order ON _detail.orderid = _order.orderid WHERE _order.orderid = ? ORDER BY _product.artist ASC";
	public static final String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM _product INNER JOIN _prodimg ON _product.productid = _prodimg.productid INNER JOIN _category ON _product.categoryid = _category.categoryid WHERE _category.categoryid = ? ORDER BY _product.artist ASC";
	public static final String GET_PRODUCTS_ON_SALE = "SELECT * FROM _sale INNER JOIN _product ON _product.productid = _sale.productid INNER JOIN _category ON _category.categoryid = _product.categoryid INNER JOIN _prodimg ON _prodimg.productid = _product.productid";
	public static final String GET_PRODUCT_ON_SALE_BY_ID = "SELECT * FROM _sale INNER JOIN _product ON _product.productid = _sale.productid INNER JOIN _category ON _category.categoryid = _product.categoryid INNER JOIN _prodimg ON _prodimg.productid = _product.productid WHERE _product.productid = ?";
	public static final String GET_ALL_DISCOUNT_CODES_ACTIVE = "SELECT * FROM _discount WHERE _discount.enabled = true";
	public static final String CHECK_DISCOUNTCODE_AVAILABLILLITY = "SELECT * FROM _discount WHERE _discount.enabled = true AND _discount.discountcode = ?";
	public static final String UPDATE_PRODUCT_PRICE = "UPDATE _product SET price = ? WHERE productid = ?";
	public static final String UPDATE_PRODUCT_NUMBER_IN_STOCK = "UPDATE _product SET numberinstock = ? WHERE productid = ?";
	public static final String UPDATE_PRODUCT_TYPE_IN_STOCK = "UPDATE _product SET typeinstock = ? WHERE productid = ?";
	public static final String PRODUCT_CREATE = "INSERT INTO _product (title, price, numberinstock, typeinstock, producttype, productid, artist, categoryid, label, released) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	public static final String PRODUCT_IMG_CREATE = "INSERT INTO _prodimg (productimage, productid) VALUES ( ?, ? )";
	// order
	public static final String ORDER_LATEST = "SELECT * FROM _order ORDER BY _order.orderid ASC";
	public static final String ORDER_GET_BY_USERID = "SELECT * FROM _order WHERE _order.userid = ?";
	public static final String ORDER_GET_BY_ID = "SELECT * FROM _order WHERE _order.orderid = ?";
	public static final String ORDER_CREATE =
    
    
    
    INSERT INTO _order (userid, year, month, day, orderid, totalprice) VALUES ( ?, ?, ?, ?, ?, ? );
    
    INSERT INTO _order (year, userId, month, day, orderid, totalprice) VALUES ( '2013', 'CUSTOMER17', '09', '09', 'order23', 172.2);
	public static final String ORDER_DETAIL_CREATE = "INSERT INTO _detail (orderid, productid, price) VALUES ( ?, ?, ? );";
	public static final String DELETE_DETAIL = "DELETE FROM _detail WHERE _detail.orderid = ?";
	public static final String DELETE_ORDER = "DELETE FROM _order WHERE _order.orderid = ?";
	// user
	public static final String USER_LATEST = "SELECT * FROM _customer INNER JOIN _address ON _customer.userid = _address.userid ORDER BY _customer.userid ASC";
	public static final String GET_ALL_USERS = "SELECT * FROM _customer INNER JOIN _address ON _customer.userid = _address.userid ORDER BY _customer.userid ASC";
	public static final String USER_GET_BY_ID = "SELECT * FROM _customer INNER JOIN _address ON _customer.userid = _address.userid WHERE _customer.userid = ? AND _customer.userid = _address.userid";
	public static final String ADDRESS_GET_BY_ID = "SELECT * FROM _address WHERE _address.userid = ?";
	public static final String LOGIN_USER = "SELECT * FROM _customer INNER JOIN _address ON _customer.userid = _address.userid WHERE _customer.email = ? AND _customer.password = ?";
	public static final String UPDATE_USER_READONLY_BY_ID = "UPDATE _customer SET readonly = ? WHERE userid = ?";
	public static final String UPDATE_USER_LASTNAME_BY_ID = "UPDATE _customer SET lastname = ? WHERE userid = ?";
	public static final String UPDATE_USER_EMAIL_BY_ID = "UPDATE _customer SET email = ? WHERE userid = ?";
	public static final String UPDATE_USER_AVAILABLE_BY_ID = "UPDATE _customer SET available = ? WHERE userid = ?";
	public static final String UPDATE_USER_PASSWORD_BY_ID = "UPDATE _customer SET password = ? WHERE password = ? AND userid = ?";
	public static final String UPDATE_ADDRESS_BY_ID = "UPDATE _address SET country = ?, city = ?, zip = ?, area = ?, street = ? WHERE userid = ?";
	public static final String USER_CREATE = "INSERT INTO _customer(firstname, lastname, birthdate, available, email, readonly, userid, password) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
	public static final String ADDRESS_CREATE = "INSERT INTO _address(country, city, zip, area, street, userid) VALUES ( ?, ?, ?, ?, ?, ? )";
	// category
	public static final String CATEGORYS_GET = "SELECT * FROM _category WHERE _category.parentid != 'ROOT'";
	public static final String GET_CATEGORY_BY_NAME = "SELECT * FROM _category WHERE _category.categoryname = ?";
	public static final String GET_CATEGORY_BY_ID = "SELECT * FROM _category WHERE _category.categoryid = ?";
	public static final String UPDATE_CATEGORY_EMPTY = "UPDATE _category SET empty = ? WHERE categoryid = ?";

}
