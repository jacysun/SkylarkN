package model;

import java.util.ArrayList;

/**
 * This class represents an itinerary.
 *
 */
public class Itinerary {

	public ArrayList<Flight> flights = new ArrayList<Flight>();
	public int tp = 0;
	public String totalPrice = null;
	public String duration = null;
	public double dura = 0;
	public boolean oneWay;

	public Itinerary(boolean coach, boolean oneWay, ArrayList<Flight> flights) {
		this.oneWay = oneWay;
		this.flights = flights;
	}
	
	public void setType(boolean oneWay) {
		this.oneWay = oneWay;
	}
	
	public boolean getType(){
		return oneWay;
	}
	
	public void addFlight(Flight flight) {
		flights.add(flight);
	}
	
	public ArrayList<Flight> getList() {
		return flights;
	}
	
	public String getPrice(String seatType) {
		if (seatType.equals("coach seat")) {
			for (int i=0; i<flights.size(); i++) {
				tp += Integer.parseInt(flights.get(i).getCoachPrice());
			}
		}
		else {
			for (int i=0; i<flights.size(); i++) {
				tp += Integer.parseInt(flights.get(i).getFirstClassPrice());
			}
		}
		totalPrice = Integer.toString(tp);
		return totalPrice;
	}
	
	public String getDura() {
		for (int i=0; i<flights.size(); i++) {
			dura += Double.parseDouble(flights.get(i).getFlightTime());
		}
		duration = Double.toString(dura);
		return duration;
	}
}
