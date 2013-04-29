package se.casdev.crypt;

public class Cryptograph {

	public static String cryptPassword(String password) {

		String psw = "";
		char[] passArray = password.toCharArray();

		for (char pass : passArray) {
			psw = psw.concat(Changing.convertThis(pass));
		}

		String nPassword = Hash.HashIt(psw);

		return nPassword;

	}

}
