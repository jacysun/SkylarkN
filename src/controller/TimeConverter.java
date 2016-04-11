package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
		final SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy HH:mm:ss z");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		sdf.format(cal.getTime());
		return sdf.format(cal.getTime());
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
