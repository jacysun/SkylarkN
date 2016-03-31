import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import controller.AirportParser;
import controller.FlightParser;
import controller.MyTime;
import model.Airport;
import model.Flight;

public class Test {

	public static void main(String[] args) {
//		FlightParser parser = new FlightParser();
//		Airport depAirport = new Airport("Hartsfield-Jackson Atlanta International","ATL",33.641045,-84.427764);
//		Calendar localCal = Calendar.getInstance();
//		localCal.set(Calendar.YEAR, 2016);
//		localCal.set(Calendar.MONTH,4);
//		localCal.set(Calendar.DAY_OF_MONTH,10);
//		//Calendar cal = MyTime.localToGmt(localCal, depAirport);
//		System.out.println(localCal.get(Calendar.MONTH));
//		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
//		//String depDateString = format.format(cal.getTime());
//		System.out.println(depAirport.getCode());
//		System.out.println(depAirport.getName());
//		System.out.println(depAirport.getLatitude());
//		System.out.println(depAirport.getLongitude());
//		System.out.println(depAirport.getTimeZone());
		//System.out.println(depDateString);
		
//		parser.start(depAirport.getCode(), depDateString);
//		List<Flight> flights = parser.flightList;
//		Driver.flightsPrinter(flights);
		HashMap<String, Airport> airportCache = new HashMap<String, Airport>();
		AirportParser parser = new AirportParser();
		List<Airport> airports = parser.start();
		
		for(int i=0;i<airports.size();i++){
			airportCache.put(airports.get(i).getCode(), airports.get(i));
		}
	}

}
