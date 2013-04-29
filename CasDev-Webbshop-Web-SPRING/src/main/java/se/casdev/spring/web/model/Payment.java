package se.casdev.spring.web.model;

import java.util.ArrayList;
import java.util.List;

public enum Payment {

	INVOICE, CASH;

	public static List<String> getWaysOfPayment() {
		List<String> payments = new ArrayList<String>();

		for (Payment payment : Payment.values()) {
			payments.add(payment.toString());
		}

		return payments;
	}

}
