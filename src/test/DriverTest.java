package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import controller.DBUpdater;
import controller.DataRetriever;
import controller.MyTime;
import model.Airport;
import model.Flight;
import test.ItineraryBuilderTest.RoundTrip;
import test.ItineraryBuilderTest.Schedule;

/**
 * Test Driver, where real tests conduct here one by one.
 * 
 * @author team 6
 *
 */
public class DriverTest {

	public static void main(String[] args) {

		// Directly call test method in main to conduct unit tests.
		itineraryBuilderTest();

	}

	/**
	 * ====================================================
	 * MyTime.timeZoneForAirport test
	 * ====================================================
	 */
	public static void timeZoneConverterTest() {
		DataRetrieverTest dr = new DataRetrieverTest();
		List<Airport> airports = dr.getAirports();
		Airport testAirport1 = airports.get(3);
		System.out.println("Test airport1: " + testAirport1.getCode());
		Airport testAirport2 = airports.get(24);
//		System.out.println("Test airport2: " + testAirport2.getCode());
//		String testResult1 = MyTime.timeZoneForAirport(testAirport1);
//		System.out.println("Test result 1: " + testResult1);
//		String testResult2 = MyTime.timeZoneForAirport(testAirport2);
//		System.out.println("Test result 2: " + testResult2);
	}

	/**
	 * ====================================================
	 * MyTime.timeZoneForAirport test
	 * ====================================================
	 */

	/**
	 * ==================================================== 
	 * MyTime.timeZoneCache test, Deprecated
	 * ====================================================
	 */

	public static void timeZoneCacheTest() {
		DataRetriever dt = new DataRetriever();
		List<Airport> airports = dt.getAirports();
		long start = System.nanoTime();
		MyTime myTime = new MyTime();
		Calendar cal = Calendar.getInstance();
		myTime.localToGmt(cal, airports.get(5));
		long end = System.nanoTime();
		long used = end - start;
		System.out.println("used:" + TimeUnit.NANOSECONDS.toMillis(used) + " ms");
		myTime.gmtToLocal(cal, airports.get(5));
	}

	/**
	 * ==================================================== MyTime.timeZoneCache
	 * test ====================================================
	 */

	/**
	 * ====================================================
	 * ItineraryBuilder.layoverChecker test
	 * ====================================================
	 */
	public void layoverCheckerTest() {
//		// Flight arrive at DEN
//		Flight flightArrival = new Flight("A320", "3587", 227, "CLT", "2016 May 12 00:51 GMT", "DEN",
//				"2016 May 12 04:41 GMT", 198.00, 220.00, 20, 20);
//		// Flight depart from DEN
//		Flight flightDepart = new Flight("C120", "2681", 327, "DEN", "2016 May 12 05:41 GMT", "BOS",
//				"2016 May 12 21:38 GMT", 198.00, 220.00, 20, 20);
//		MyTime m = new MyTime();
//		ItineraryBuilder builder = new ItineraryBuilder(m);
//		boolean result = builder.layoverChecker(flightArrival, flightDepart);
//		System.out.println(result);
	}

	/**
	 * ====================================================
	 * ItineraryBuilder.layoverChecker test
	 * ====================================================
	 */
	/**
	 * ====================================================
	 * ItineraryBuilder.itineraryBuilder test
	 * ====================================================
	 */
	public static void itineraryBuilderTest() {
		DataRetrieverTest dtt = new DataRetrieverTest();
		List<Airport> airports = dtt.getAirports();
		Airport startAirport = airports.get(4);
		System.out.println("startAirport:");
		airportPrinter(startAirport);
		Airport destination = airports.get(1);
		System.out.println("destination:");
		airportPrinter(destination);
		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.YEAR, 2016);
		startCal.set(Calendar.MONTH, 4);
		startCal.set(Calendar.DAY_OF_MONTH, 12);
		Calendar returnCal = Calendar.getInstance();
		returnCal.set(Calendar.YEAR, 2016);
		returnCal.set(Calendar.MONTH,4);
		returnCal.set(Calendar.DAY_OF_MONTH,13);
		MyTime myTime = new MyTime();
//		try {
//			TimeUnit.SECONDS.sleep(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		myTime.stop();
		ItineraryBuilderTest builder = new ItineraryBuilderTest(myTime);
		long start = System.nanoTime();
//		List<Schedule> result = builder.oneWayTrip(startAirport, destination, startCal, 2, false);
		List<RoundTrip> container = builder.roundTrip(startAirport, destination, startCal, 2, true, startCal);
		List<Schedule> result = builder.oneWayTrip(startAirport, destination, startCal, 2, true);
//		List<RoundTrip> container = builder.roundTrip(startAirport, destination, startCal, 2, true, startCal);
		long end = System.nanoTime();
		long used = end - start;
		System.out.println("used:" + TimeUnit.NANOSECONDS.toMillis(used) + " ms");
//		System.out.println(container.size());
		schedulePrinter(result);
//		roundTripPrinter(container);
		
		
	}

	/**
	 * ====================================================
	 * ItineraryBuilder.itineraryBuilder test
	 * ====================================================
	 */

	/**
	 * Simple method to print information about a flight
	 * 
	 * @param flight
	 */
	public static void flightPrinter(Flight flight) {
		System.out.println("airpLane: " + flight.getAirplane());
		System.out.println("number: " + flight.getNumber());
		System.out.println("flightTime: " + flight.getDuration());
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
	public static void flightsPrinter(List<Flight> flights) {
		/**
		 * String airplane; String number; String flightTime; String departCode;
		 * String departTime; String arrivalCode; String arrivalTime; String
		 * firstClassPrice; String coachPrice; int firstClassSeats; int
		 * coachSeats;
		 **/

		for (Flight flight : flights) {
			System.out.println("airpLane: " + flight.getAirplane());
			System.out.println("number: " + flight.getNumber());
			System.out.println("flightTime: " + flight.getDuration());
			System.out.println("departCode: " + flight.getDepartCode());
			System.out.println("departTime: " + flight.getDepartTime());
			System.out.println("arrivalCode: " + flight.getArrivalCode());
			System.out.println("arrivalTime: " + flight.getArrivalTime());
			System.out.println("Coach: " + flight.getCoachSeats());
			System.out.println("First Class: " + flight.getFirstClassSeats());
			System.out.println("=========================================");
		}
	}

	/**
	 * Simple method to print out information about a airport
	 * 
	 * @param airport
	 */
	public static void airportPrinter(Airport airport) {
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
	public static void airportsPrinter(List<Airport> airports) {
		for (Airport airport : airports) {
			System.out.println("Code: " + airport.getCode());
			System.out.println("Name: " + airport.getName());
			System.out.println("Latitude: " + airport.getLatitude());
			System.out.println("Longitude: " + airport.getLongitude());
			System.out.println("timeZone: " + airport.getTimeZone());
			System.out.println("--------------------");
		}
	}

	/**
	 * Simple method to print list of schedules
	 * 
	 * @param schedules
	 */
	public static void schedulePrinter(List<Schedule> schedules) {
		for (Schedule schedule : schedules) {
			System.out.println("*************************");
			int stop = schedule.getStopCounter();
			System.out.println("Stops: " + stop);
			flightsPrinter(schedule.getVoyoage());
			System.out.println("*************************");
		}
	}
	/**
	 * Simple method to print schedules for list of roundtrips
	 * 
	 * @param container
	 */
	public static void roundTripPrinter(List<RoundTrip> container){
		for(RoundTrip trip: container){
			System.out.println("+++++++++++++++++++++++++++");
			System.out.println("Leave Schedule");
			flightsPrinter(trip.getOutBound().getVoyoage());
			System.out.println("Return Schedule");
			flightsPrinter(trip.getInBound().getVoyoage());
			System.out.println("+++++++++++++++++++++++++++");
		}
	}
	
	public static void updateDBTest() {
		DataRetriever dt = new DataRetriever();
		List<Flight> flights = new ArrayList<Flight>();
		flights = dt.getFlights("BOS", "2016_05_12");
		String flightNumber = flights.get(0).getNumber();
		int seatsReservedStart = flights.get(0).getFirstClassSeats();
		String seatType = "FirstClass";
		
		DBUpdater db = new DBUpdater();
		db.lock();
		System.out.println("----------------------------");
		db.reserveSeat(flightNumber, seatType);
		System.out.println("----------------------------");
		db.unlock();
		System.out.println("----------------------------");
		
		flights.clear();
		flights = dt.getFlights("BOS", "2016_05_12");
		int seatsReservedEnd = flights.get(0).getFirstClassSeats();
		System.out.println("Flight Number: " + flightNumber);
		System.out.println("Reserved Seat Type: " + seatType);
		System.out.println("SeatReserved before: " + seatsReservedStart);
		System.out.println("SeatReserved after: " + seatsReservedEnd);
	}
}

