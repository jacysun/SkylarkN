import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import controller.AirportParser;
import controller.FlightParser;
import controller.ItineraryBuilder;
import controller.MyTime;
import model.Airport;
import model.Flight;

public class Driver {

	public static void main(String[] args) {
//		String date = "2016 Mar 13 20:30 GMT";
//		Calendar cal = MyTime.StringToCalendar(date);
//		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
//		System.out.println(cal.get(Calendar.MONTH));
//		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
//		System.out.println(cal.get(Calendar.HOUR_OF_DAY));
//		System.out.println(cal.get(Calendar.MINUTE));
//		System.out.println(cal.getTimeZone());
		
		AirportParser parser = new AirportParser();
		List<Airport> airports = parser.start();
/**
 * ====================================================
 * MyTime.timeZoneForAirport test
 * ====================================================
 */
//		Airport testAirport1 = airports.get(3);
//		System.out.println("Test airport1: " + testAirport1.getCode());
//		Airport testAirport2 = airports.get(24);
//		System.out.println("Test airport2: " + testAirport2.getCode());
//		String testResult1 = MyTime.timeZoneForAirport(testAirport1);
//		System.out.println("Test result 1: " + testResult1);
//		String testResult2 = MyTime.timeZoneForAirport(testAirport2);
//		System.out.println("Test result 2: " + testResult2);
		
/**
 * ====================================================
 *  MyTime.timeZoneForAirport test
 * ====================================================
 */		
/**
 * ====================================================
 *  MyTime.layoverChecker test
 * ====================================================
 */	  	
		// Flight arrive at DEN
		Flight flightArrival = new Flight("A320","3587","227"
		 ,"CLT","2016 May 13 09:51 GMT"
		 ,"DEN","2016 May 13 13:38 GMT"
		 ,"198.00","220.00",20,20);
		// Flight depart from DEN
 		Flight flightDepart = new Flight("C120","2681","327"
		 ,"DEN","2016 May 13 16:31 GMT"
		 ,"BOS","2016 May 13 21:38 GMT"
		 ,"198.00","220.00",20,20);
 		
 		ItineraryBuilder builder = new ItineraryBuilder();
 		boolean result = builder.layoverChecker(flightArrival, flightDepart);
 		System.out.println(result);
	
/**
 * ====================================================
 *  MyTime.layoverChecker test
 * ====================================================
 */		

		
		
//		Calendar gmtDepDate = MyTime.localToGmt(depDate, depAirport);
//		// Format calendar to string, call FlightParser to get all flights depart from depAirport
//		SimpleDateFormat format = new SimpleDateFormat("yyyy_mm_dd");
//		String depDateString = format.format(gmtDepDate);

	}
	
	// A helper method to print a list of flights
	public static void flightsPrinter(List<Flight> flights){
		/**String airplane;
		String number;
		String flightTime;
		String departCode;
		String departTime;
		String arrivalCode;
		String arrivalTime;
		String firstClassPrice;
		String coachPrice;
		int firstClassSeats;
		int coachSeats;
		**/
		
		for(Flight flight : flights){
			System.out.println("airpLane: " + flight.getAirplane());
			System.out.println("number: " + flight.getNumber());
			System.out.println("flightTime: " + flight.getFlightTime());
			System.out.println("departCode: " + flight.getDepartCode());
			System.out.println("departTime: " + flight.getDepartTime());
			System.out.println("arrivalCode: " + flight.getArrivalCode());
			System.out.println("arrivalTime: " + flight.getArrivalTime());
			System.out.println("=========================================");
		}
	}
	
	// A helper method to print list of airport
	public static void airportsPrinter(List<Airport> airports){
		for(Airport airport : airports){
			 System.out.println("Code: " + airport.getCode());
			 System.out.println("Name: " + airport.getName());
			 System.out.println("Latitude: " + airport.getLatitude());
			 System.out.println("Longitude: " + airport.getLongitude());
			 System.out.println("timeZone: " + airport.getTimeZone());
			 System.out.println("--------------------");
		}
	}

}
