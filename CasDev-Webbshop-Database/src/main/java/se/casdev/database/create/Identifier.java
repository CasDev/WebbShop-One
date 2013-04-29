package se.casdev.database.create;

public class Identifier {
	
	private int number;
	private String identifyer;
	
	public Identifier(int number, String identifyer) {
		this.identifyer = identifyer;
		this.number = number;
	}
	
	public String getIdentifyer() {
		return identifyer;
	}
	
	public int getNumber() {
		return number;
	}

}
