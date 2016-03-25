package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;

import java.util.List;

import model.Airport;

import model.Flight;

public class ItineraryBuilder {

	/**
	 * 
	 * Filter flight list with non-stop condition from a itinerary
	 * 
	 * In parameter input, flightList is a list of flights got from
	 * FlightParser,
	 * 
	 * depAirport, arriAirport and depDate are the airports and departure date
	 * picked by user.
	 * 
	 * 
	 * 
	 * @param depAirport
	 * 
	 * @param arriAirport
	 * 
	 * @param depDate
	 * 
	 * @return
	 * 
	 */

	public List<Flight> nonStopSearch(Airport depAirport, Airport arriAirport,
			Calendar depDate) {

		// List to contain filtered flights
		List<Flight> resultList = new ArrayList<>();
		String arriAirportCode = arriAirport.getCode();

		// Convert local time from user input to GMT
		Calendar gmtDepDate = MyTime.localToGmt(depDate, depAirport);
		// Format calendar to string, call FlightParser to get all flights depart from depAirport
		SimpleDateFormat format = new SimpleDateFormat("yyyy_mm_dd");
		String depDateString = format.format(gmtDepDate);
		FlightParser parser = new FlightParser();
		parser.start(depAirport.getCode(), depDateString);
		List<Flight> flightList = parser.flightList;

		// Walk through the input flight list, compared with inputs, if this
		// flight has same departure date and destination, add it to result
		// List.
		for (Flight flight : flightList) {

			Calendar depCal = MyTime.StringToCalendar(flight.getDepartTime());

			if (depCal.get(Calendar.DAY_OF_MONTH) == gmtDepDate.get(Calendar.DAY_OF_MONTH)

					&& flight.getArrivalCode().equals(arriAirportCode)) {

				resultList.add(flight);
			}
		}
		return resultList;
	}

	
}
