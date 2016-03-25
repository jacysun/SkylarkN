import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import controller.MyTime;
import model.Airport;

public class Driver {

	public static void main(String[] args) {
		String date = "2016 Mar 13 20:30 GMT";
		Calendar cal = MyTime.StringToCalendar(date);
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println(cal.get(Calendar.MONTH));
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		System.out.println(cal.get(Calendar.HOUR));
		System.out.println(cal.get(Calendar.MINUTE));
		System.out.println(cal.getTimeZone());
//		Airport depAirport = new Airport();
//		depAirport.setCode("ALT");
//		depAirport.setLatitude(33.641045);
//		depAirport.setLongitude(-84.427764);
//		Calendar depDate = Calendar.getInstance();
//		
//		
//		Calendar gmtDepDate = MyTime.localToGmt(depDate, depAirport);
//		// Format calendar to string, call FlightParser to get all flights depart from depAirport
//		SimpleDateFormat format = new SimpleDateFormat("yyyy_mm_dd");
//		String depDateString = format.format(gmtDepDate);

	}

}
