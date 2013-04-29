package se.casdev.repository.database.repository.link;

import se.casdev.database.storage.AdminInterface;

public class AdminLink {
	
	private AdminInterface aInterface;
	
	protected AdminLink(AdminInterface aInterface) {
		this.aInterface = aInterface;
	}

}
