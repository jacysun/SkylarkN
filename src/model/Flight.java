package model;

import java.text.ParseException;
import java.util.Calendar;

import controller.MyTime;

/**
 * This class holds values pertaining to a single flight from one airport to another. 
 * Class member attributes are the same as defined by the CS509 server API and store 
 * values after conversion from XML received from the server to Java primitives. 
 * Attributes are accessed via getter and setter methods.
 */

public class Flight {
	
	MyTime myTime = new MyTime();
	
	private String airplane;
	private String number;
	private double duration;
	private String departCode;
	private String departTime;
	private String arrivalCode;
	private String arrivalTime;
	private double firstClassPrice;
	private double coachPrice;
	private int firstClassSeats;
	private int coachSeats;
	
	public Flight() {
		
	}
	
	public Flight (String airplane, String number, double duration, String departCode, String departTime, String arrivalCode,
			       String arrivalTime, double firstClassPrice, double coachPrice, int firstClassSeats, int coachSeats) {
		
		this.airplane = airplane;
		this.number = number;
		this.duration = duration;
		this.departCode = departCode;
		this.departTime = departTime;
		this.arrivalCode = arrivalCode;
		this.arrivalTime = arrivalTime;
		this.firstClassPrice = firstClassPrice;
		this.coachPrice = coachPrice;
		this.firstClassSeats = firstClassSeats;
		this.coachSeats = coachSeats;
	}

	public String getDepLocal() throws ParseException {
        
		Airport departure = new Airport();
		departure = departure.getAirport(this.departCode);
		Calendar depCal = myTime.StringToCalendar(this.departTime, "GMT");
		Calendar depLocalCal = myTime.gmtToLocal(depCal, departure);
		String depLocal = myTime.calendarToString(depLocalCal);	
		return depLocal;
	}
	
	public String getArrLocal() throws ParseException {
		Airport arrival = new Airport();
		arrival = arrival.getAirport(this.arrivalCode);
		Calendar arrCal = myTime.StringToCalendar(this.arrivalTime, "GMT");
		Calendar arrLocalCal = myTime.gmtToLocal(arrCal, arrival);
		String arrLocal = myTime.calendarToString(arrLocalCal);
		return arrLocal;
	}
	
	/**
	 * @return the airplane
	 */
	public String getAirplane() {
		return airplane;
	}

	/**
	 * @param airplane the airplane to set
	 */
	public void setAirplane(String airplane) {
		this.airplane = airplane;
	}

	/**
	 * @return the flight number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the flight number
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the flightTime
	 */
	public double getDuration() {
		double dur = duration/60;
		dur = Math.floor(dur*100)/100;
		return dur;
	}

	/**
	 * @param flightTime the flightTime to set
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}

	/**
	 * @return the departCode
	 */
	public String getDepartCode() {
		return departCode;
	}

	/**
	 * @param departCode the departCode to set
	 */
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	/**
	 * @return the departTime
	 */
	public String getDepartTime() {
		return departTime;
	}

	/**
	 * @param departTime the departTime to set
	 */
	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}

	/**
	 * @return the arrivalCode
	 */
	public String getArrivalCode() {
		return arrivalCode;
	}

	/**
	 * @param arrivalCode the arrivalCode to set
	 */
	public void setArrivalCode(String arrivalCode) {
		this.arrivalCode = arrivalCode;
	}

	/**
	 * @return the arrivalTime
	 */
	public String getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * @return the firstClassPrice
	 */
	public double getFirstClassPrice() {
		return firstClassPrice;
	}

	/**
	 * @param firstClassPrice the firstClassPrice to set
	 */
	public void setFirstClassPrice(double firstClassPrice) {
		this.firstClassPrice = firstClassPrice;
	}

	/**
	 * @return the coachPrice
	 */
	public double getCoachPrice() {
		return coachPrice;
	}

	/**
	 * @param coachPrice the coachPrice to set
	 */
	public void setCoachPrice(double coachPrice) {
		this.coachPrice = coachPrice;
	}

	/**
	 * @return the firstClassSeats
	 */
	public int getFirstClassSeats() {
		return firstClassSeats;
	}

	/**
	 * @param firstClassSeats the firstClassSeats to set
	 */
	public void setFirstClassSeats(int firstClassSeats) {
		this.firstClassSeats = firstClassSeats;
	}

	/**
	 * @return the coachSeats
	 */
	public int getCoachSeats() {
		return coachSeats;
	}

	/**
	 * @param coachSeats the coachSeats to set
	 */
	public void setCoachSeats(int coachSeats) {
		this.coachSeats = coachSeats;
	}
	
}

