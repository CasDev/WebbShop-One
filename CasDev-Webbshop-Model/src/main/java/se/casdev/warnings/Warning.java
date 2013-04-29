package se.casdev.warnings;

import java.util.ArrayList;
import java.util.List;

public class Warning {
	
	private static List<Messenge> serverList = new ArrayList<Messenge>();
	private static List<Messenge> clientList = new ArrayList<Messenge>();
	
	private Warning() {
		// don't do anything
	}
	
	public static void addClientMessage(Messenge message)
	{
		clientList.add(message);
	}
	
	public static ArrayList<Messenge> getClientMessages()
	{
		return (ArrayList<Messenge>) clientList;
	}
	
	public static void addServerMessage(Messenge message)
	{
		serverList.add(message);
	}
	
	public static ArrayList<Messenge> getServerMessages()
	{
		return (ArrayList<Messenge>) serverList;
	}
	
	public static void clearClientMessages() {
		clientList.clear();
	}
	
	public static void clearServerMessages() {
		serverList.clear();
	}

}
