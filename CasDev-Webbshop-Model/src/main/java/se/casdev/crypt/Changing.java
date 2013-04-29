package se.casdev.crypt;

public class Changing {

	private Changing() {
		// TODO Auto-generated constructor stub
	}

	protected static String convertThis(Character ch) {

		String psw = "";

		if (ch.equals('w')) {
			psw = psw.concat("q");
		} else if (ch.equals('e')) {
			psw = psw.concat("w");
		} else if (ch.equals('r')) {
			psw = psw.concat("e");
		} else if (ch.equals('t')) {
			psw = psw.concat("r");
		} else if (ch.equals('y')) {
			psw = psw.concat("t");
		} else if (ch.equals('u')) {
			psw = psw.concat("y");
		} else if (ch.equals('i')) {
			psw = psw.concat("u");
		} else if (ch.equals('o')) {
			psw = psw.concat("i");
		} else if (ch.equals('p')) {
			psw = psw.concat("o");
		} else if (ch.equals('å')) {
			psw = psw.concat("p");
		} else if (ch.equals('q')) {
			psw = psw.concat("å");
		} else if (ch.equals('s')) {
			psw = psw.concat("a");
		} else if (ch.equals('d')) {
			psw = psw.concat("s");
		} else if (ch.equals('f')) {
			psw = psw.concat("d");
		} else if (ch.equals('g')) {
			psw = psw.concat("f");
		} else if (ch.equals('h')) {
			psw = psw.concat("g");
		} else if (ch.equals('j')) {
			psw = psw.concat("h");
		} else if (ch.equals('k')) {
			psw = psw.concat("j");
		} else if (ch.equals('l')) {
			psw = psw.concat("k");
		} else if (ch.equals('ö')) {
			psw = psw.concat("l");
		} else if (ch.equals('ä')) {
			psw = psw.concat("ö");
		} else if (ch.equals('a')) {
			psw = psw.concat("ä");
		} else if (ch.equals('x')) {
			psw = psw.concat("z");
		} else if (ch.equals('c')) {
			psw = psw.concat("x");
		} else if (ch.equals('v')) {
			psw = psw.concat("c");
		} else if (ch.equals('b')) {
			psw = psw.concat("v");
		} else if (ch.equals('n')) {
			psw = psw.concat("b");
		} else if (ch.equals('m')) {
			psw = psw.concat("n");
		} else if (ch.equals('z')) {
			psw = psw.concat("m");
		} else if (ch.equals('W')) {
			psw = psw.concat("Q");
		} else if (ch.equals('E')) {
			psw = psw.concat("W");
		} else if (ch.equals('R')) {
			psw = psw.concat("E");
		} else if (ch.equals('T')) {
			psw = psw.concat("R");
		} else if (ch.equals('Y')) {
			psw = psw.concat("T");
		} else if (ch.equals('U')) {
			psw = psw.concat("Y");
		} else if (ch.equals('I')) {
			psw = psw.concat("U");
		} else if (ch.equals('O')) {
			psw = psw.concat("I");
		} else if (ch.equals('P')) {
			psw = psw.concat("O");
		} else if (ch.equals('Å')) {
			psw = psw.concat("P");
		} else if (ch.equals('Q')) {
			psw = psw.concat("Å");
		} else if (ch.equals('S')) {
			psw = psw.concat("A");
		} else if (ch.equals('D')) {
			psw = psw.concat("S");
		} else if (ch.equals('F')) {
			psw = psw.concat("D");
		} else if (ch.equals('G')) {
			psw = psw.concat("F");
		} else if (ch.equals('H')) {
			psw = psw.concat("G");
		} else if (ch.equals('J')) {
			psw = psw.concat("H");
		} else if (ch.equals('K')) {
			psw = psw.concat("J");
		} else if (ch.equals('L')) {
			psw = psw.concat("K");
		} else if (ch.equals('Ö')) {
			psw = psw.concat("L");
		} else if (ch.equals('Ä')) {
			psw = psw.concat("Ö");
		} else if (ch.equals('A')) {
			psw = psw.concat("Ä");
		} else if (ch.equals('X')) {
			psw = psw.concat("Z");
		} else if (ch.equals('C')) {
			psw = psw.concat("X");
		} else if (ch.equals('V')) {
			psw = psw.concat("C");
		} else if (ch.equals('B')) {
			psw = psw.concat("V");
		} else if (ch.equals('N')) {
			psw = psw.concat("B");
		} else if (ch.equals('M')) {
			psw = psw.concat("N");
		} else if (ch.equals('Z')) {
			psw = psw.concat("M");
		} else if (ch.equals('1')) {
			psw = psw.concat("2");
		} else if (ch.equals('2')) {
			psw = psw.concat("3");
		} else if (ch.equals('3')) {
			psw = psw.concat("4");
		} else if (ch.equals('4')) {
			psw = psw.concat("5");
		} else if (ch.equals('5')) {
			psw = psw.concat("6");
		} else if (ch.equals('6')) {
			psw = psw.concat("7");
		} else if (ch.equals('7')) {
			psw = psw.concat("8");
		} else if (ch.equals('8')) {
			psw = psw.concat("9");
		} else if (ch.equals('9')) {
			psw = psw.concat("0");
		} else if (ch.equals('0')) {
			psw = psw.concat("1");
		}

		return psw;

	}

}
