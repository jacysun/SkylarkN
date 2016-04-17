import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import controller.AirportParser;
import controller.FlightParser;
import controller.MyTime;
import model.Airport;
import model.Flight;

public class Test {

	public static void main(String[] args) {

		Date maxDate = new Date(Long.MAX_VALUE);
		Calendar maxCal = Calendar.getInstance();
		maxCal.setTime(maxDate);
		System.out.println(maxCal.get(Calendar.YEAR));
		
	}

}
