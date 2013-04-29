package se.casdev.database.create;

public class Alternate {

	public static String makeIdNumeral(String id) {

		if (id.contains("Q")) {
			id = id.replace("Q", "");
		}
		if (id.contains("W")) {
			id = id.replace("W", "");
		}
		if (id.contains("E")) {
			id = id.replace("E", "");
		}
		if (id.contains("R")) {
			id = id.replace("R", "");
		}
		if (id.contains("T")) {
			id = id.replace("T", "");
		}
		if (id.contains("Y")) {
			id = id.replace("Y", "");
		}
		if (id.contains("U")) {
			id = id.replace("U", "");
		}
		if (id.contains("I")) {
			id = id.replace("I", "");
		}
		if (id.contains("O")) {
			id = id.replace("O", "");
		}
		if (id.contains("P")) {
			id = id.replace("P", "");
		}
		if (id.contains("A")) {
			id = id.replace("A", "");
		}
		if (id.contains("S")) {
			id = id.replace("S", "");
		}
		if (id.contains("D")) {
			id = id.replace("D", "");
		}
		if (id.contains("F")) {
			id = id.replace("F", "");
		}
		if (id.contains("G")) {
			id = id.replace("G", "");
		}
		if (id.contains("H")) {
			id = id.replace("H", "");
		}
		if (id.contains("J")) {
			id = id.replace("J", "");
		}
		if (id.contains("K")) {
			id = id.replace("K", "");
		}
		if (id.contains("L")) {
			id = id.replace("L", "");
		}
		if (id.contains("Z")) {
			id = id.replace("Z", "");
		}
		if (id.contains("X")) {
			id = id.replace("X", "");
		}
		if (id.contains("C")) {
			id = id.replace("C", "");
		}
		if (id.contains("V")) {
			id = id.replace("V", "");
		}
		if (id.contains("B")) {
			id = id.replace("B", "");
		}
		if (id.contains("N")) {
			id = id.replace("N", "");
		}
		if (id.contains("M")) {
			id = id.replace("M", "");
		}

		return id;
	}
	
	public static String makeItAlphabetical(String id) {
		
		if(id.contains("1")) {
			id = id.replace("1", "");
		}
		if(id.contains("2")) {
			id = id.replace("2", "");
		}
		if(id.contains("3")) {
			id = id.replace("3", "");
		}
		if(id.contains("4")) {
			id = id.replace("4", "");
		}
		if(id.contains("5")) {
			id = id.replace("5", "");
		}
		if(id.contains("6")) {
			id = id.replace("6", "");
		}
		if(id.contains("7")) {
			id = id.replace("7", "");
		}
		if(id.contains("8")) {
			id = id.replace("8", "");
		}
		if(id.contains("9")) {
			id = id.replace("9", "");
		}
		if(id.contains("0")) {
			id = id.replace("0", "");
		}
		
		return id;
		
	}

}
