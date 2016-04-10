package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public interface TimeConverter {
	
	public static void main(String args[]) throws ParseException{
		System.out.println(calendarToString(Calendar.getInstance()));
	}
	
	public static Calendar stringToCalendar(String input) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy MMM dd HH:mm z");
		Date date = format.parse(input);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	public static String calendarToString(Calendar cal) throws ParseException {
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String month;
		switch (cal.get(Calendar.MONTH)+1) {
			case 1: month = "JAN";
					break;
			case 2: month = "FEB";
					break;
			case 3: month = "MAR";
					break;
			case 4: month = "APR";
					break;
			case 5: month = "MAY";
					break;
			case 6: month = "JUN";
					break;
			case 7: month = "JUL";
					break;
			case 8: month = "AUG";
					break;
			case 9: month = "SEP";
					break;
			case 10: month = "OCT";
					break;
			case 11: month = "NOV";
					break;
			case 12: month = "DEC";
					break;
			default: month = "Invalid";
					break;
		}
		String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		String hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		String minute = String.valueOf(cal.get(Calendar.MINUTE));
		String date = month + "/" + day + "/" + year + " " + hour + ":" + minute;
		return date;
	}
	
	public static Calendar webInputToCalendar(String input) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy_MM_dd");
		Date date = format.parse(input);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	public static String CalendarToWebInput(Calendar cal) {
		int month = cal.get(Calendar.MONTH) + 1;
		String sDate = ""+cal.get(Calendar.YEAR)+"_"+month+"_"+cal.get(Calendar.DATE);
		return sDate;
	}
}
