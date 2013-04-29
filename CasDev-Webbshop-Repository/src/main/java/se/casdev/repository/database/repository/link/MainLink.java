package se.casdev.repository.database.repository.link;

import se.casdev.database.storage.AdminInterface;
import se.casdev.database.storage.CategoryInterface;
import se.casdev.database.storage.OrderInterface;
import se.casdev.database.storage.ProductInterface;
import se.casdev.database.storage.UserInterface;
import se.casdev.database.storage.implement.CouchDBAdminStorage;
import se.casdev.database.storage.implement.CouchDBCategoryStorage;
import se.casdev.database.storage.implement.CouchDBOrderStorage;
import se.casdev.database.storage.implement.CouchDBProductStorage;
import se.casdev.database.storage.implement.CouchDBUserStorage;
import se.casdev.database.storage.implement.MySQLAdminStorage;
import se.casdev.database.storage.implement.MySQLCategoryStorage;
import se.casdev.database.storage.implement.MySQLOrderStorage;
import se.casdev.database.storage.implement.MySQLProductStorage;
import se.casdev.database.storage.implement.MySQLUserStorage;

public class MainLink {

	private UserInterface uInterface;
	private ProductInterface pInterface;
	private OrderInterface oInterface;
	private AdminInterface aInterface;
	private CategoryInterface cInterface;
	public final static MainLink COUCHDB = new MainLink(
			new CouchDBUserStorage(), new CouchDBProductStorage(),
			new CouchDBOrderStorage(), new CouchDBAdminStorage(),
			new CouchDBCategoryStorage());
	public final static MainLink MYSQL = new MainLink(new MySQLUserStorage(),
			new MySQLProductStorage(), new MySQLOrderStorage(),
			new MySQLAdminStorage(), new MySQLCategoryStorage());

	public MainLink(UserInterface uInterface, ProductInterface pInterface,
			OrderInterface oInterface, AdminInterface aInterface,
			CategoryInterface cInterface) {
		this.uInterface = uInterface;
		this.pInterface = pInterface;
		this.oInterface = oInterface;
		this.aInterface = aInterface;
		this.cInterface = cInterface;
	}

	public AdminLink getAdminLink() {
		return new AdminLink(aInterface);
	}

	public OrderLink getOrderLink() {
		return new OrderLink(oInterface);
	}

	public UserLink getUserLink() {
		return new UserLink(uInterface);
	}

	public ProductLink getProductLink() {
		return new ProductLink(pInterface);
	}

	public CategoryLink getCategoryLink() {
		return new CategoryLink(cInterface);
	}

}
