package se.casdev.spring.web.form;

import se.casdev.userInfo.Customer;

public class ConfirmOrderForm {

	private String wayOfPay;
	private String payment;
	private String userId;
	private boolean userOK;

	public ConfirmOrderForm() {
		this.payment = "0";
		this.userId = "CUS0000";
		this.wayOfPay = "";
		userOK = true;
	}

	public void setUserOK(boolean userOK) {
		this.userOK = userOK;
	}

	public boolean isUserOK() {
		return userOK;
	}

	public String getPayment() {
		return payment;
	}

	public String getUserId() {
		return userId;
	}

	public String getWayOfPay() {
		return wayOfPay;
	}

	public void setUserId(Customer user) {
		this.userId = user.getUserId();
	}

	public void setPayment(Integer payment) {
		this.payment = payment.toString();
	}

	public void setWayOfPay(String wayOfPay) {
		this.wayOfPay = wayOfPay;
	}

}
