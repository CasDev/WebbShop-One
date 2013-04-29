package se.casdev.product;

public enum ProductType {

	COMPACT_DISC, LONG_PLAY, DOWNLOAD;

	public static String productType(ProductType type) {
		switch (type) {
		case COMPACT_DISC:
			return "CD";
		case LONG_PLAY:
			return "LP";
		case DOWNLOAD:
			return "Download";
		default:
			return null;
		}
	}

}
