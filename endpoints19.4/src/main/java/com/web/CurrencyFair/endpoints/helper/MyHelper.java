package com.web.CurrencyFair.endpoints.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MyHelper {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");// "24-JAN-15 10:27:44"
	List<String> months = Arrays.asList("JAN", "FEB", "MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC");
	
	public String toStringGiven(String day, String month, String year,String hour, String min, String sec) {
		
		return day + "-" +	month + "-" + year + " " + hour + ":" +	min + ":" +	sec;
	}
	
	public static Date toDate(String sDate) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
			Date date = new Date();

			date = sdf.parse(sDate);

			return date;

		} catch (ParseException e) {
			System.out.println("the format given can't be convered to simpleDateFormat");
			e.printStackTrace();
		}

		return null;
	}

	public static String toStringCust(Date date) {
		return sdf.format(date);
	}

	public static SimpleDateFormat getSdf() {
		return sdf;
	}

}
