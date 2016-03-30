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
		//airportsPrinter(airports);
		//System.out.println(airports.size());
		
		//flight infor===================
		Airport depAirport = airports.get(0);
		Airport arriAirport = airports.get(5);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH,4);
		cal.set(Calendar.DAY_OF_MONTH,15);
		//String dateString = "2016_05_15";
		//============================
//		FlightParser flightParser = new FlightParser();
//		flightParser.start(depAirport.getCode(), dateString);
//		List<Flight> flights = flightParser.flightList;
		
		ItineraryBuilder builder = new ItineraryBuilder();
		//List<Flight> flights = builder.nonStopSearch(depAirport, arriAirport, cal);
		//flightsPrinter(flights);
		
		
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
