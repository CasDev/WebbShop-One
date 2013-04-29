package se.casdev.database.base;

import java.util.Calendar;

import se.casdev.database.storage.AdminInterface;
import se.casdev.database.storage.CategoryInterface;
import se.casdev.database.storage.OrderInterface;
import se.casdev.database.storage.ProductInterface;
import se.casdev.database.storage.UserInterface;
import sun.java2d.pipe.NullPipe;

public class Logic {

	public static <T> String determineAdminId(Class<T> clas) {
		AdminInterface product = null;
		try {
			product = (AdminInterface) clas.newInstance();
		} catch (InstantiationException e) {
			// TODO: what will happen here?
		} catch (IllegalAccessException e) {
			// TODO: what will happen here?
		}
		String latestAdminId = null;
		try {
			latestAdminId = product.getLatestAdmin().getUserId();
		} catch (NullPointerException e) {

			return "ADM1000001";
		}
		String start = "";
		char[] endLetters = latestAdminId.toCharArray();
		start = start.concat("A");
		start = start.concat("D");
		start = start.concat("M");
		start = start.toUpperCase();
		String end = "";
		end = end.concat(Character.toString(endLetters[3]));
		end = end.concat(Character.toString(endLetters[4]));
		end = end.concat(Character.toString(endLetters[5]));
		end = end.concat(Character.toString(endLetters[6]));
		end = end.concat(Character.toString(endLetters[7]));
		end = end.concat(Character.toString(endLetters[8]));
		int idNumber = addOne(end);
		String newId = "";
		newId = Integer.toString(idNumber);
		String name = "";
		name = name.concat(start);
		name = name.concat(newId);

		return name;
	}

	public static <T> String determineProductId(String type,
			Class<T> productClass) {

		ProductInterface product = null;
		try {
			product = (ProductInterface) productClass.newInstance();
		} catch (InstantiationException e) {
			// TODO: what will happen here?
		} catch (IllegalAccessException e) {
			// TODO: what will happen here?
		}

		String latestProductId = null;
		try {
			latestProductId = product.getLatestProduct().getProdctId();
		} catch (NullPointerException e) {
			String start = "";
			char[] threeLetterStart = type.toCharArray();
			start = start.concat(Character.toString(threeLetterStart[0]));
			start = start.concat(Character.toString(threeLetterStart[1]));
			start = start.concat(Character.toString(threeLetterStart[2]));
			start = start.toUpperCase();
			start = start.concat("3000001");

			return start;
		}
		String start = "";
		char[] endLetters = latestProductId.toCharArray();
		char[] threeLetterStart = type.toCharArray();
		start = start.concat(Character.toString(threeLetterStart[0]));
		start = start.concat(Character.toString(threeLetterStart[1]));
		start = start.concat(Character.toString(threeLetterStart[2]));
		start = start.toUpperCase();
		String end = "";
		end = end.concat(Character.toString(endLetters[0]));
		end = end.concat(Character.toString(endLetters[1]));
		end = end.concat(Character.toString(endLetters[2]));
		end = end.concat(Character.toString(endLetters[3]));
		end = end.concat(Character.toString(endLetters[4]));
		end = end.concat(Character.toString(endLetters[5]));
		int idNumber = addOne(end);
		String newId = "";
		newId = Integer.toString(idNumber);
		String name = "";
		name = name.concat(newId);
		name = name.concat(start);

		return name;
	}

	public static <T> String determineUserId(Class<T> userClass) {

		UserInterface user = null;
		try {
			user = (UserInterface) userClass.newInstance();
		} catch (InstantiationException e) {
			// TODO: what will happen here?
		} catch (IllegalAccessException e) {
			// TODO: what will happen here?
		}

		String latestCustomerId = null;
		try {
			latestCustomerId = user.getLatestCustomer().getUserId();
		} catch (NullPointerException e) {
			return "CUS3001";
		}
		String name = "";
		String customerIdNumber = "";
		char[] idArray = latestCustomerId.toCharArray();
		customerIdNumber = customerIdNumber.concat(Character
				.toString(idArray[3]));
		customerIdNumber = customerIdNumber.concat(Character
				.toString(idArray[4]));
		customerIdNumber = customerIdNumber.concat(Character
				.toString(idArray[5]));
		customerIdNumber = customerIdNumber.concat(Character
				.toString(idArray[6]));
		int customerNr = addOne(customerIdNumber);
		name = name.concat(Character.toString(idArray[0]));
		name = name.concat(Character.toString(idArray[1]));
		name = name.concat(Character.toString(idArray[2]));
		name = name.concat(Integer.toString(customerNr));

		return name;
	}

	private static int addOne(String customerIdNumber) {
		return Integer.parseInt(customerIdNumber) + 1;
	}

	public static <T> String determineOrderId(String userid, Class<T> orderClass) {

		OrderInterface order = null;
		try {
			order = (OrderInterface) orderClass.newInstance();
		} catch (InstantiationException e) {
			// TODO: what will happen here?
		} catch (IllegalAccessException e) {
			// TODO: what will happen here?
		}

		String name = "";
		String latestOrderId = null;
		try {
			latestOrderId = order.getLatestOrder().getOrderid();
		} catch (NullPointerException e) {
			return "ORD10001" + userid;
		}
		char[] idArray = latestOrderId.toCharArray();
		String orderIdNumber = "";
		orderIdNumber = orderIdNumber.concat(Character.toString(idArray[3]));
		orderIdNumber = orderIdNumber.concat(Character.toString(idArray[4]));
		orderIdNumber = orderIdNumber.concat(Character.toString(idArray[5]));
		orderIdNumber = orderIdNumber.concat(Character.toString(idArray[6]));
		orderIdNumber = orderIdNumber.concat(Character.toString(idArray[7]));
		int newInt = Integer.parseInt(orderIdNumber) + 1;
		name = name.concat("ORD");
		name = name.concat(Integer.toString(newInt));
		name = name.concat(userid);

		return name;
	}

	public static String determineTodaysYear() {
		String thisYear = Integer.toString(Calendar.getInstance().get(
				Calendar.YEAR));

		return thisYear;
	}

	public static String determineTodaysMonth() {
		int thisMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		String month = "";

		if (thisMonth >= 10) {
			month = month.concat(Integer.toString(thisMonth));
		}
		if (thisMonth < 10 || thisMonth > 0) {
			month = month.concat("0");
			month = month.concat(Integer.toString(thisMonth));
		}

		return month;
	}

	public static String determineTodaysDay() {
		int thisDay = Calendar.getInstance().get(Calendar.DATE);
		String day = "";

		if (thisDay >= 10) {
			day = day.concat(Integer.toString(thisDay));
		} else if (thisDay < 10 || thisDay > 0) {
			day = day.concat("0");
			day = day.concat(Integer.toString(thisDay));
		}

		return day;
	}

	public static <T> String determineCategoryId(String name, Class<T> clas) {
		CategoryInterface category = null;
		try {
			category = (CategoryInterface) clas.newInstance();
		} catch (InstantiationException e) {
			// TODO: what will happen here?
		} catch (IllegalAccessException e) {
			// TODO: what will happen here?
		}

		int amount = category.getAllCategories().size();
		String id = "";
		char[] threeLetter = name.toCharArray();
		id = id.concat(Character.toString(threeLetter[0]));
		id = id.concat(Character.toString(threeLetter[1]));
		id = id.concat(Character.toString(threeLetter[2]));
		id = id.concat(Integer.toString(amount));
		id = id.concat("0");
		id = id.concat("0");

		return id;
	}

}
