package se.casdev.database.access;

import java.net.UnknownHostException;

import se.casdev.warnings.Messenge;
import se.casdev.warnings.Warning;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class ConnectingMongoDB {

	private DB database;

	public ConnectingMongoDB(String db) {
		this.database = getConnection(db);
	}

	private DB getConnection(String db) {
		Mongo mongo = null;
		try {
			mongo = new Mongo("localhost", 27017);
		} catch (UnknownHostException e) {
			Warning.addClientMessage(new Messenge("cannot.find.localhost", "I am sorry, but we are having some technical difficulties"));
			Warning.addServerMessage(new Messenge("cannot.find.localhost", "The MongoDB database cannot find a database at this localhost. Please check the host and port"));
		} catch (MongoException e) {
			// TODO: what will happen here?
		}
		DB database = mongo.getDB(db);

		return database;
	}

	public DB getConnection() {
		return database;
	}

	public boolean auth(String username, String password) {
		if (database.authenticate(username, password.toCharArray())) {
			return true;
		} else {
			return false;
		}
	}
}
