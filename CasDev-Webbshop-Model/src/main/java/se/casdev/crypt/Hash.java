package se.casdev.crypt;

public class Hash {

	private Hash() {
	}

	protected static String HashIt(String password) {

		int hash = password.hashCode();
		double nHash = (hash * Math.PI);
		double xnHash = (nHash / 4);
		float endHash = (float) (xnHash * 3.8);

		return Float.toString(endHash);

	}

}
