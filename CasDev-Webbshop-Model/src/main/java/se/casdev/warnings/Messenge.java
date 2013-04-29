package se.casdev.warnings;

public class Messenge {
	
	private String messenge;
	private String subject;
	
	public Messenge(String subject, String messenge) {
		this.subject = subject;
		this.messenge = messenge;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getMessenge() {
		return messenge;
	}

}
