package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeValidation {
	
	private static final Calendar cTime = Calendar.getInstance();
	
	public TimeValidation() {
		
	}
	
	public static boolean filterOut(String time) throws ParseException {
		Calendar date = TimeConverter.stringToCalendar(time);
		Date now = cTime.getTime();
		return now.before(date.getTime());
	}
	
	public static boolean withInWindow(String aIn, String dIn) throws ParseException {
		Calendar aTime = TimeConverter.stringToCalendar(aIn);
		Calendar dTime = TimeConverter.stringToCalendar(dIn);
		if (dTime.getTimeInMillis() - aTime.getTimeInMillis() > 3600000 && dTime.getTimeInMillis() - aTime.getTimeInMillis() < 5*3600000){
			return true;
		} else {
			return false;
		}
	}
	
	
	
	public static void main(String args[]) throws ParseException{

//		String f = "2016 May 12 23:47 GMT";
//		DateFormat d = new SimpleDateFormat("yyyy MMM dd HH:mm z");
//		Date re = d.parse(f);
		String f = "2016_03_15";
		DateFormat d = new SimpleDateFormat("yyyy_mm_dd");
		Date re = d.parse(f);
		System.out.println(re);
		
	}
}

