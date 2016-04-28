package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import controller.DataRetriever;
import controller.MyTime;
import model.Airplane;
import model.Airport;
import model.Flight;

import java.util.HashMap;
import java.util.LinkedList;

public class ItineraryBuilderTest {
	
	private HashMap<String, Airport> airportCache = new HashMap<String, Airport>();
	private MyTime myTime;
	private List<Airplane> planeList;
	
	
	/**
	 * Constructor, initialize airportCache and time converter
	 */
	public ItineraryBuilderTest(MyTime time){
		DataRetriever dr = new DataRetriever();
		List<Airport> airports = dr.getAirports();

		
		for(int i=0;i<airports.size();i++){
			this.airportCache.put(airports.get(i).getCode(), airports.get(i));
		}
		this.myTime = time;
		this.planeList = new ArrayList<Airplane>();
		this.planeList = dr.getAirplanes();
	}
	
	
	/**
	 * Check if the interval between two flights are 1~5 hour which is a qualified layover.
	 * 
	 * @param flightFrom
	 * @param flightTo
	 * @return boolean 
	 */
	public boolean layoverChecker(Flight flightFrom,Flight flightTo){
		if(!flightFrom.getArrivalCode().equals(flightTo.getDepartCode())){
//			System.out.print("These two flights are not in the same airport!!!");
			return false;
		}
		Calendar calFrom = myTime.StringToCalendar(flightFrom.getArrivalTime(),"GMT");
		Calendar calTo = myTime.StringToCalendar(flightTo.getDepartTime(),"GMT");
		if(calTo.before(calFrom)){
			return false;
		}
		double timeInterval = myTime.getInterval(calFrom, calTo);
		if(timeInterval>=1&&timeInterval<=5){
			return true;
		}else{
			return false;
		}	
	}
	
	/**
	 * 
	 * 
	 * @param flight
	 * @param returnCal
	 * @return
	 */
	public boolean returnDateChecker(Flight flight, Calendar returnCal, Airport destination){
		Calendar gmtReturnCal = myTime.localToGmt(returnCal, destination);
		Calendar gmtFlightToArrival = null;
		try {
			gmtFlightToArrival = myTime.stringToCalendar(flight.getArrivalTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(gmtFlightToArrival.get(Calendar.MONTH)==gmtReturnCal.get(Calendar.MONTH)){
			if(gmtFlightToArrival.get(Calendar.DAY_OF_MONTH)>gmtReturnCal.get(Calendar.DAY_OF_MONTH)){
				return false;
			}else{
				return true;
			}
		}else{
			if(gmtFlightToArrival.get(Calendar.MONTH)>gmtReturnCal.get(Calendar.MONTH)){
				return false;
			}else{
				return true;
			}
		}
	}
	
	/**
	 * Check if coach seat is available on this plane  
	 * 
	 * @param planeList
	 * @param flight
	 * @return return true available
	 */
	public boolean coachSeatChecker(Flight flight){
		
		for(Airplane plane: this.planeList){
			if(plane.getModel().equals(flight.getAirplane())){
				if(plane.getCoachSeats()==flight.getCoachSeats()){
					return false;
				}else{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check if firstclass seat is available on this plane  
	 * 
	 * @param planeList
	 * @param flight
	 * @return return true available
	 */
	public boolean firstClassSeatChecker(Flight flight){
		
		for(Airplane plane: this.planeList){
			if(plane.getModel().equals(flight.getAirplane())){
				if(plane.getFirstClassSeats()==flight.getFirstClassSeats()){
					return false;
				}else{
					return true;
				}
			}
		}
		return false;
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
		
		public Schedule(){}
		
		// create a new schedule from a start airport
		public Schedule(Airport airport){
			this.stopCounter = -1;
			this.currentAirport = airport;
		}
		
		// create new schedule from previous one
		public Schedule(Schedule preStop){
			this.stopCounter = preStop.getStopCounter();
			for(Flight flight: preStop.getVoyoage()){
				this.voyoage.add(flight);
			}
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
	 * @param startAirport user inputs departure airport
	 * @param destination  user inputs arrival airport
	 * @param depDate	   user inputs departure date
	 * @param maxStop      user inputs number of stops, optional, if user does not
	 * 							specify stop, default value is 2.
	 * @return List of schedules
	 */
	public List<Schedule> itineraryBuilder(Airport startAirport, Airport destination, Calendar depDate, 
			int maxStop, boolean requestCoach, Calendar returnDate){
		// Task Queue
		Queue<Schedule> scheduleQueue = new LinkedList<Schedule>();
		// Expected return list of schedules
		List<Schedule> scheduleList = new ArrayList<>();
		// Empty schedule contains startAirport
		Schedule start = new Schedule(startAirport);
		// Enqueue
		scheduleQueue.add(start);
		List<Flight> extraFlights = new ArrayList<>();
		
		DataRetrieverTest drt = new DataRetrieverTest();
		
		while(!scheduleQueue.isEmpty()){
			Schedule currentStop = scheduleQueue.poll();
			// stopCounter++
			currentStop.enterAirport();
			Airport currentAirport;
			String depDateString;
			Flight flightFrom;
			if(currentStop.getStopCounter()>0){
				// We have left stop over airport
				// Get the flight take you to current airport
				flightFrom = currentStop.getVoyoage().get(currentStop.getStopCounter()-1);
				currentStop.setCurrentAirport(flightFrom.getArrivalCode());				
				currentAirport = currentStop.getCurrentAirport();
				// Get time arrived in current airport 
				String tempDateString = flightFrom.getArrivalTime();
				Calendar gmtCal = myTime.StringToCalendar(tempDateString, "GMT");				
				SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
				depDateString = format.format(gmtCal.getTime());
				
				// If flight arrives at late night, 00:00-5 hours
				Calendar localCal = myTime.gmtToLocal(gmtCal, currentAirport);
				// If flight arrives at late night, 12:00-5 hours
				if(localCal.get(Calendar.HOUR_OF_DAY)>19||localCal.get(Calendar.HOUR_OF_DAY)==19){
					long nextMillSec = gmtCal.getTimeInMillis()+86400000;
					SimpleDateFormat nextFormat = new SimpleDateFormat("yyyy_MM_dd");
					String NextDepDateString = nextFormat.format(nextMillSec);
					extraFlights = drt.getFlights(currentAirport.getCode(), NextDepDateString);
				}
			}else{
				// We are at the start airport
				currentAirport = currentStop.getCurrentAirport();
				Calendar gmtCal = myTime.localToGmt(depDate, currentAirport);
				SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
				depDateString = format.format(gmtCal.getTime());
			}
			// Get flights depart from current airport
						
			List<Flight> flights = drt.getFlights(currentAirport.getCode(), depDateString);
			// Overnight condition exists, add extraFlights to flights 
			if(!extraFlights.isEmpty()){
				flights.addAll(extraFlights);
			}
			for(Flight flightTo: flights){
				if(currentStop.getStopCounter()>0){
					flightFrom = currentStop.getVoyoage().get(currentStop.getStopCounter()-1);
					// Layover time Check
					if(!layoverChecker(flightFrom,flightTo)){
						continue;
					}
					// Backtrack is forbidden
					if(flightTo.getArrivalCode().equals(startAirport.getCode())){
						continue;
					}
					// Selected coach seat but not available
					if(requestCoach&&!coachSeatChecker(flightTo)){
						continue;
					}
					if(!requestCoach&&!firstClassSeatChecker(flightTo)){
						continue;
					}
					// Exclude flights arrive later than return date
					if(!returnDateChecker(flightTo,returnDate,destination)){
						continue;
					}								
				}
				// Find destination
				if(flightTo.getArrivalCode().equals(destination.getCode())){
					// Start new schedule from previous one
					Schedule newSchedule = new Schedule(currentStop);
					// Add qualified departing flight to schedule.voyoage
					newSchedule.getVoyoage().add(flightTo);
					scheduleList.add(newSchedule);
					continue;
				}
				// Continue to build next stop
				if(currentStop.getStopCounter() < maxStop){
					// Start new schedule from previous one
					Schedule newSchedule = new Schedule(currentStop);
					// Add qualified departing flight to schedule.voyoage
					newSchedule.getVoyoage().add(flightTo);
					scheduleQueue.add(newSchedule);
				}	
			}
		}	
		return scheduleList;
	}
	
	/**
	 * 
	 * @param depAirport departure airport
	 * @param destination	destination airport
	 * @param depDate	departure date
	 * @param maxStop	maximum stops can be specified by user
	 * @param coach		if request coach seat
	 * @return
	 */
	public List<Schedule> oneWayTrip(Airport depAirport, Airport destination,
			Calendar depDate, int maxStop, boolean coach){
		// Maximum Date
		Date maxDate = new Date(Long.MAX_VALUE);
		Calendar maxCal = Calendar.getInstance();
		maxCal.setTime(maxDate);
		return itineraryBuilder(depAirport,destination,depDate, maxStop, coach, maxCal);
	}
	
	public class RoundTrip{
		private Schedule leaveSchedule;
		private Schedule returnSchedule;
		
		public void setOutBound(Schedule schedule){
			this.leaveSchedule = schedule;
		}
		
		public Schedule getOutBound(){
			return this.leaveSchedule;
		}
		
		public void setInBound(Schedule schedule){
			this.returnSchedule = schedule;
		}
		
		public Schedule getInBound(){
			return this.returnSchedule;
		}
	}
	/**
	 * Round trip builder
	 * 
	 * @param depAirport
	 * @param destination
	 * @param depDate
	 * @param maxStop
	 * @param coach
	 * @param returnDate
	 * @return
	 */
	public List<RoundTrip> roundTrip(Airport depAirport, Airport destination,
			Calendar depDate, int maxStop, boolean coach, Calendar returnDate){
		List<Schedule> outBound = itineraryBuilder(depAirport,destination,depDate,maxStop,coach,returnDate);
		List<RoundTrip> result = new ArrayList<>();
		if(outBound.size()==0){
			// No flights to destination
			return null;
		}else{
			Date maxDate = new Date(Long.MAX_VALUE);
			Calendar maxCal = Calendar.getInstance();
			maxCal.setTime(maxDate);
			List<Schedule> inBound = itineraryBuilder(destination,depAirport,returnDate,maxStop,coach,maxCal);
			result = schedulePicker(outBound,inBound);
			return result;
		}
	}
	
	public List<RoundTrip> schedulePicker(List<Schedule> outBound,List<Schedule> inBound){
		List<RoundTrip> verifiedRoundTrips = new ArrayList<>();
		
		for(Schedule inSchedule: inBound){
			for(Schedule outSchedule: outBound){
				Flight outFlight = outSchedule.getVoyoage().get(outSchedule.getStopCounter());
				Flight inFlight = inSchedule.getVoyoage().get(0);
				Calendar outCal = null;
				Calendar inCal = null;
				outCal = myTime.StringToCalendar(outFlight.getArrivalTime(),"GMT");
				inCal = myTime.StringToCalendar(inFlight.getDepartTime(),"GMT");
				
				// Exclude outbound schedule arrives later than the inbound schedule departures from destination
				if(outCal.compareTo(inCal)==1){
					continue;
				}
				// Exclude inbound schedule leaves destination after outbound schedule arrives in less than 1 hour
				if(outCal.get(Calendar.DAY_OF_MONTH)==inCal.get(Calendar.DAY_OF_MONTH)&&
						myTime.getInterval(outCal, inCal)<1){
					continue;
				}
				RoundTrip newRoundTrip = new RoundTrip();
				newRoundTrip.setOutBound(outSchedule);
				newRoundTrip.setInBound(inSchedule);
				verifiedRoundTrips.add(newRoundTrip);	
			}
		}		
		return verifiedRoundTrips;
	}
		
}

