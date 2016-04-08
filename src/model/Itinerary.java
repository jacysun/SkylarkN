package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import controller.ItineraryBuilder;
import controller.ItineraryBuilder.Schedule;
import controller.MyTime;

/**
 * This class represents an itinerary.
 *
 */
public class Itinerary {

	private List<Flight> flights = new ArrayList<Flight>();
	private double totalPrice;
	private double duration;
	private int stopNum;

	public Itinerary() {
		
	}
	
	public Itinerary(int stopNum, double duration, double totalPrice, List<Flight> flights) {
		this.stopNum = stopNum;
		this.duration = duration;
		this.totalPrice = totalPrice;
		this.flights = flights;
	}
	
	public int getStopNum() {
		return stopNum;
	}
	
	public void addFlight(Flight flight) {
		flights.add(flight);
	}
	
	public List<Flight> getFlights() {
		return flights;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public double getDuration() {
		return duration;
	}

	public ArrayList<Itinerary> getItineraryList (String depCode, String arrCode, String depDate, boolean oneWay, String seatType) throws ParseException {
		ArrayList<Itinerary> itineraryList = new ArrayList<Itinerary>(); 
		Itinerary itinerary = new Itinerary();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar depart = Calendar.getInstance();
        depart.setTime(formatter.parse(depDate));
        
        Airport departure = new Airport();
		departure = departure.getAirport(depCode);
		Airport arrival = new Airport();
		arrival = arrival.getAirport(arrCode);
		if (departure == null || arrival == null) {
			return null;
		} else {
			ItineraryBuilder ib = new ItineraryBuilder();
			List<Schedule> scheduleList = new ArrayList<Schedule>();
			if (oneWay) {
				if (seatType.equals("coach")) {
					scheduleList = ib.oneWayTrip(departure, arrival, depart, 2, true);
				} else {
					scheduleList = ib.oneWayTrip(departure, arrival, depart, 2, false);
				}
//			} else {
//				if (seatType.equals("coach")) {
//					scheduleList = ib.rouneWayTrip(departure, arrival, depart, 2, true);
//				} else {
//					scheduleList = ib.roundWayTrip(departure, arrival, depart, 2, false);
//				}
			}
			for (int i = 0; i < scheduleList.size(); i++) {
				 List<Flight> flights = scheduleList.get(i).getVoyoage();
				 int stopNum = scheduleList.get(i).getStopCounter(); 
				 double duration = 0;
				 double totalPrice = 0;
				for (int j = 0; j < flights.size(); j++) {
					duration += flights.get(j).getDuration();
					if (seatType.equals("coach")) {
						totalPrice += flights.get(j).getCoachPrice();
					} else {
						totalPrice += flights.get(j).getFirstClassPrice();
					}
					totalPrice = Math.round(totalPrice*100)/100;
				}
				double interval = 0;
				for (int k = 0; k < stopNum; k++) {
					MyTime myTime = new MyTime();
					Calendar arr = myTime.StringToCalendar(flights.get(k).getArrivalTime(),"GMT");
					Calendar dep = myTime.StringToCalendar(flights.get(k+1).getDepartTime(),"GMT");
					interval += MyTime.getInterval(arr, dep);
				}
				duration = duration + interval;
				duration = Math.round(duration*100)/100;

				itinerary = new Itinerary(stopNum, duration, totalPrice, flights);
				itineraryList.add(itinerary);
			}
		
		}
		return itineraryList;
	}
}


