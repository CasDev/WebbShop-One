package se.casdev.product;

public enum StockType {

	IN_STOCK, ON_ORDER, NOT_IN;

	public static String StockTypeOfProduct(StockType type) {

		switch (type) {
		case IN_STOCK:
			return "In stock";
		case ON_ORDER:
			return "On it's way";
		case NOT_IN:
			return "Not in stock";
		default:
			return null;
		}

	}

}
