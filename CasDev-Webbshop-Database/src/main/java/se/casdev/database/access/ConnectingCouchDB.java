package se.casdev.database.access;

import java.util.List;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.jcouchdb.db.Database;
import org.jcouchdb.db.Server;
import org.jcouchdb.db.ServerImpl;
import org.jcouchdb.document.BaseDocument;
import org.jcouchdb.document.DesignDocument;
import org.jcouchdb.document.ValueRow;
import org.jcouchdb.document.View;
import org.jcouchdb.document.ViewResult;
import org.jcouchdb.exception.DataAccessException;

public class ConnectingCouchDB {

	private static final String DB_VIEW = ""; // database
	public static final String DB_USER = ""; // username
	public static final String DB_PASSWORD = ""; // password
	private Database db = null;
	private boolean connected = false;

	public Database getConnection(String db, String user, String password) {
		if (authUser(user, password)) {
			Server server = new ServerImpl("localhost", 5984);
			this.db = new Database(server, db);
			this.connected = true;

			return this.db;
		} else {
			return null;
		}
	}

	public void disconnect() {
		if (connected == false) {
			this.connected = true;
		} else {
			this.connected = false;
		}
	}

	private boolean isConnected() {
		return connected;
	}

	public List<ValueRow<BaseDocument>> createView(Database db, String id,
			String mapFn) {
		if (isConnected() && authUser(DB_USER, DB_PASSWORD)) {
			DesignDocument doc = new DesignDocument(DB_VIEW);

			View view = new View();
			view.setMap(mapFn);
			doc.addView(id, view);
			db.createOrUpdateDocument(doc);

			ViewResult<BaseDocument> result = db.queryView(DB_VIEW + "/" + id,
					BaseDocument.class, null, null);

			if (result == null) {
				db.delete(doc);
				return null;
			}

			db.delete(doc);
			return result.getRows();
		} else if (isConnected() == false) {
			return null;
		}

		return null;
	}

	private boolean authUser(String user, String password) {

		try {
			Server server = new ServerImpl("localhost", 5984);
			Database db = new Database(server, "test");
			Credentials cred = new UsernamePasswordCredentials(user, password);
			AuthScope auth = new AuthScope("localhost", 5984);
			server.setCredentials(auth, cred);
			BaseDocument doc = new BaseDocument();
			doc.setProperty("test", true);
			db.createDocument(doc);
			db.delete(doc);
			return true;
		} catch (DataAccessException e) {
			return false;
		}

	}

}
