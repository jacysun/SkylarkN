import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import controller.AirportParser;
import controller.FlightParser;
import controller.ItineraryBuilder;
import controller.ItineraryBuilder.Schedule;
import controller.MyTime;
import model.Airport;
import model.Flight;

public class Driver {

	
	public static void main(String[] args) {
		//itineraryBuilderTest();
		timeZoneCacheTest();
		

	}
		

		
		
/**
 * ====================================================
 * MyTime.timeZoneForAirport test
 * ====================================================
 */
	public void timeZoneConverterTest(){
		AirportParser parser = new AirportParser();
		List<Airport> airports = parser.start();
		Airport testAirport1 = airports.get(3);
		System.out.println("Test airport1: " + testAirport1.getCode());
		Airport testAirport2 = airports.get(24);
		System.out.println("Test airport2: " + testAirport2.getCode());
		String testResult1 = MyTime.timeZoneForAirport(testAirport1);
		System.out.println("Test result 1: " + testResult1);
		String testResult2 = MyTime.timeZoneForAirport(testAirport2);
		System.out.println("Test result 2: " + testResult2);
	}
/**
 * ====================================================
 *  MyTime.timeZoneForAirport test
 * ====================================================
 */	
		
/**
 * ====================================================
 *  MyTime.timeZoneCache test
 * ====================================================
 */
	
	public static void timeZoneCacheTest(){
		AirportParser parser = new AirportParser();
		List<Airport> airports = parser.start();
		long start = System.nanoTime();
		MyTime myTime = new MyTime();
		Calendar cal = Calendar.getInstance();
		myTime.localToGmt(cal, airports.get(5));
		long end = System.nanoTime();
	    long used = end-start;
		System.out.println("used:"+TimeUnit.NANOSECONDS.toMillis(used)+" ms");
		myTime.gmtToLocal(cal, airports.get(5));		
	}
/**
 * ====================================================
 *  MyTime.timeZoneCache test
 * ====================================================
 */
	
	
/**
 * ====================================================
 *  ItineraryBuilder.layoverChecker test
 * ====================================================
 */	  	
	public void layoverCheckerTest(){
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
	}
/**
 * ====================================================
 *  ItineraryBuilder.layoverChecker test
 * ====================================================
 */		
/**
 * ====================================================
 *  ItineraryBuilder.itineraryBuilder test
 * ====================================================
 */ 		
	public static void itineraryBuilderTest() {
		AirportParser parser = new AirportParser();
		List<Airport> airports = parser.start();
		Airport startAirport = airports.get(8);
		System.out.println("startAirport:");
		airportPrinter(startAirport);
		Airport destination = airports.get(4);
		System.out.println("destination:");
		airportPrinter(destination);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 14);
		
		ItineraryBuilder builder = new ItineraryBuilder();
		long start = System.nanoTime();
		List<Schedule> result = builder.itineraryBuilder(startAirport, destination, cal, 2);
		long end = System.nanoTime();
	    long used = end-start;
	    System.out.println("used:"+TimeUnit.NANOSECONDS.toMillis(used)+" ms");
		schedulePrinter(result);
	}
 		
/**
 * ====================================================
 *  ItineraryBuilder.itineraryBuilder test
 * ====================================================
 */		
 		
	
	
	
	/**
	 * Simple method to print information about a flight
	 * 
	 * @param flight
	 */
	public static void flightPrinter(Flight flight){
		System.out.println("airpLane: " + flight.getAirplane());
		System.out.println("number: " + flight.getNumber());
		System.out.println("flightTime: " + flight.getFlightTime());
		System.out.println("departCode: " + flight.getDepartCode());
		System.out.println("departTime: " + flight.getDepartTime());
		System.out.println("arrivalCode: " + flight.getArrivalCode());
		System.out.println("arrivalTime: " + flight.getArrivalTime());
		System.out.println("=========================================");
	}
	
	/**
	 * Simple method to print out list of flights
	 * 
	 * @param flights
	 */
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
	/**
	 * Simple method to print out information about a airport
	 * 
	 * @param airport
	 */
	public static void airportPrinter(Airport airport){
		System.out.println("Code: " + airport.getCode());
		 System.out.println("Name: " + airport.getName());
		 System.out.println("Latitude: " + airport.getLatitude());
		 System.out.println("Longitude: " + airport.getLongitude());
		 System.out.println("timeZone: " + airport.getTimeZone());
		 System.out.println("--------------------");
	}
	
	/**
	 * Simple method to print out list of airports
	 * 
	 * @param airports
	 */
	
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
	
	
	/**
	 *  Simple method to print list of schedules
	 * 
	 * @param schedules
	 */
	public static void schedulePrinter(List<Schedule> schedules){
		for(Schedule schedule: schedules){
			System.out.println("*************************");
			int stop = schedule.getStopCounter();
			System.out.println("Stops: " + stop);
			flightsPrinter(schedule.getVoyoage());
			System.out.println("*************************");
		}
	}

}
