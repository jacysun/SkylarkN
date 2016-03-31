package controller;

import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;

import java.util.List;
import java.util.Queue;

import model.Airport;

import model.Flight;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class ItineraryBuilder {
	
	private HashMap<String, Airport> airportCache = new HashMap<String, Airport>();
	private MyTime myTime;
	
	public ItineraryBuilder(){
		AirportParser parser = new AirportParser();
		List<Airport> airports = parser.start();
		
		for(int i=0;i<airports.size();i++){
			this.airportCache.put(airports.get(i).getCode(), airports.get(i));
		}
		this.myTime = new MyTime();
	}
	
	public void timeZoneSetter(Airport airport){
		if(airport.getTimeZone()==null){
			airport.setTimeZone(MyTime.timeZoneForAirport(airport));
		}
	}

	/**
	 * 
	 * Filter flight list with non-stop condition from a itinerary
	 * 
	 * In parameter input, flightList is a list of flights got from
	 * FlightParser,depAirport, arriAirport and depDate are the airports and departure date
	 * picked by user.
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

//	public List<Flight> nonStopSearch(Airport depAirport, Airport arriAirport,
//			Calendar depDate) {
//
//		// List to contain filtered flights
//		List<Flight> resultList = new ArrayList<>();
//		String arriAirportCode = arriAirport.getCode();
//
//		// Convert local time from user input to GMT
//		Calendar gmtDepDate = MyTime.localToGmt(depDate, depAirport);
//		// Format calendar to string, call FlightParser to get all flights depart from depAirport
//		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
//		String depDateString = format.format(gmtDepDate.getTime());
//		FlightParser parser = new FlightParser();
//		parser.start(depAirport.getCode(), depDateString);
//		List<Flight> flightList = parser.flightList;
//
//		// Walk through the input flight list, compared with inputs, if this
//		// flight has same departure date and destination, add it to result
//		// List.
//		for (Flight flight : flightList) {
//
//			Calendar depCal = MyTime.StringToCalendar(flight.getDepartTime(),"GMT");
//
//			if (depCal.get(Calendar.DAY_OF_MONTH) == gmtDepDate.get(Calendar.DAY_OF_MONTH)
//
//					&& flight.getArrivalCode().equals(arriAirportCode)) {
//
//				resultList.add(flight);
//			}
//		}
//		return resultList;
//	}
	
	/**
	 * Check if the interval between two flights are 2~5 hour which is a qualified layover.
	 * 
	 * @param flightFrom
	 * @param flightTo
	 * @return boolean 
	 */
	public boolean layoverChecker(Flight flightFrom,Flight flightTo){
		if(!flightFrom.getArrivalCode().equals(flightTo.getDepartCode())){
			System.out.print("These two flights are not in the same airport!!!");
			return false;
		}
		Calendar calFrom = myTime.StringToCalendar(flightFrom.getArrivalTime(),"GMT");
		Calendar calTo = myTime.StringToCalendar(flightTo.getDepartTime(),"GMT");
		if(calTo.before(calFrom)){
			System.out.println("The flight take off before you arrival, you can't catch it!!");
			System.out.println("flight departure time " + calTo.get(Calendar.HOUR_OF_DAY) + ":" + calTo.get(Calendar.MINUTE));
			System.out.println("flight arrival time " + calFrom.get(Calendar.HOUR_OF_DAY) + ":" + calFrom.get(Calendar.MINUTE)); 
			return false;
		}
		double timeInterval = MyTime.getInterval(calFrom, calTo);
		if(timeInterval>=2&&timeInterval<=5){
			return true;
		}else{
			System.out.println("It's not a qualified layover. Layover time: " + timeInterval);
			return false;
		}	
	}
	
	/**
	 * Works as a node in itinerary building process
	 * 
	 * @author yizhu
	 *
	 */
	public class Schedule{
		private int stopCounter;
		private List<Flight> voyoage = new ArrayList<>();
		private Airport currentAirport;
		
		// create a new schedule from a start airport
		public Schedule(Airport airport){
			this.stopCounter = -1;
			this.currentAirport = airport;
		}
		
		// create new schedule from previous one
		public Schedule(Schedule preStop){
			this.stopCounter = preStop.getStopCounter();
			Collections.copy(this.voyoage, preStop.getVoyoage());
			this.currentAirport = preStop.getCurrentAirport();
		}
		
		public int getStopCounter(){
			return this.stopCounter;
		}
		
		public void enterAirport(){
			this.stopCounter++;
		}
		
		public List<Flight> getVoyoage(){
			return this.voyoage;
		}
		
		public Airport getCurrentAirport(){
			return this.currentAirport;
		}
		
		public void setCurrentAirport(String airportCode){
			this.currentAirport = airportCache.get(airportCode);
		}
	}
	
	/**
	 * Build list of itineraries with specified departure airport, destination airport, 
	 * departure date and max stop over. 
	 * 
	 * @param startAirport
	 * @param destination
	 * @param depDate
	 * @param maxStop
	 * @return
	 */
	public List<Schedule> itineraryBuilder(Airport startAirport, Airport destination, Calendar depDate, int maxStop){
		Queue<Schedule> scheduleQueue = new LinkedList<Schedule>();
		List<Schedule> scheduleList = new ArrayList<>();
		Schedule start = new Schedule(startAirport);
		scheduleQueue.add(start);
		
		while(!scheduleQueue.isEmpty()){
			Schedule currentStop = scheduleQueue.poll();
			currentStop.enterAirport();
			Airport currentAirport;
			String depDateString;
			if(currentStop.getStopCounter()>0){
				
				currentStop.setCurrentAirport(currentStop.getVoyoage().get(currentStop.getStopCounter()-1).getArrivalCode());
				currentAirport = currentStop.getCurrentAirport();
				Calendar gmtCal = myTime.localToGmt(depDate, currentAirport);
				SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
				depDateString = format.format(gmtCal.getTime());
			}else{
				currentAirport = currentStop.getCurrentAirport();
				String tempDateString = currentStop.getVoyoage().get(currentStop.getStopCounter()-1).getArrivalTime();
				Calendar gmtCal = myTime.StringToCalendar(tempDateString, "GMT");
				SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
				depDateString = format.format(gmtCal.getTime());
			}
			
			FlightParser parser = new FlightParser();
			parser.start(currentAirport.getCode(), depDateString);
			List<Flight> flights = parser.flightList;
			for(Flight flightTo: flights){
				if(currentStop.getStopCounter()>0){
					Flight flightFrom = currentStop.getVoyoage().get(currentStop.getStopCounter()-1);
					if(!layoverChecker(flightFrom,flightTo)){
						continue;
					}
				}
				currentStop.getVoyoage().add(flightTo);
				Schedule newSchedule = new Schedule(currentStop);
				if(newSchedule.getVoyoage().get(newSchedule.getStopCounter()).getArrivalCode().equals(destination.getCode())){
					scheduleList.add(newSchedule);
					continue;
				}else if(newSchedule.getStopCounter() < maxStop){
					scheduleQueue.add(newSchedule);
				}	
			}
		}	
		return scheduleList;
	}
	
}
