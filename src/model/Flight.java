package model;

/**
 * This class holds values pertaining to a single flight from one airport to another. 
 * Class member attributes are the same as defined by the CS509 server API and store 
 * values after conversion from XML received from the server to Java primitives. 
 * Attributes are accessed via getter and setter methods.
 */

public class Flight {
	
	/**
	 * Airport attributes as defined by the CS509 server interface XML
	 */
	private String airplane;
	private String number;
	private String flightTime;
	private String departCode;
	private String departTime;
	private String arrivalCode;
	private String arrivalTime;
	private String firstClassPrice;
	private String coachPrice;
	private int firstClassSeats;
	private int coachSeats;
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with input values.
	 * @param airplane The airplane of the flight
	 * @param number The flight number
	 * @param flightTime The duration of the flight
	 * @param departCode The code of the departure airport
	 * @param departTime The departure time
	 * @param arrivalCode The code of the arrival airport
	 * @param arrivalTime The arrival time
	 * @param firstClassPrice The price of the first class seat on the flight
	 * @param coachPrice The price of the coach seat of the flight
	 * @param firstClassSeats The number of available first class flight on the flight
	 * @param coachSeats The number of available coach seat on the flight
	 */
	public Flight (String airplane, String number, String flightTime, String departCode, String departTime, String arrivalCode,
			       String arrivalTime, String firstClassPrice, String coachPrice, int firstClassSeats, int coachSeats) {
		
		this.airplane = airplane;
		this.number = number;
		this.flightTime = flightTime;
		this.departCode = departCode;
		this.departTime = departTime;
		this.arrivalCode = arrivalCode;
		this.arrivalTime = arrivalTime;
		this.firstClassPrice = firstClassPrice;
		this.coachPrice = coachPrice;
		this.firstClassSeats = firstClassSeats;
		this.coachSeats = coachSeats;
	}

	/**
	 * Get the airplane of the flight
	 * 
	 * @return the airplane
	 */
	public String getAirplane() {
		return airplane;
	}

	/**
	 * Set the airplane of the flight
	 * 
	 * @param airplane the airplane to set
	 */
	public void setAirplane(String airplane) {
		this.airplane = airplane;
	}

	/**
	 * Get the flight number
	 * 
	 * @return the flight number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Set the flight number
	 * 
	 * @param number the flight number
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * Get the duration of the flight
	 * 
	 * @return the flightTime
	 */
	public String getFlightTime() {
		return flightTime;
	}

	/**
	 * Set the duration of the flight
	 * 
	 * @param flightTime the flightTime to set
	 */
	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	/**
	 * Get the code of the departure airport
	 * 
	 * @return the departCode
	 */
	public String getDepartCode() {
		return departCode;
	}

	/**
	 * Set the code of the departure airport
	 * 
	 * @param departCode the departCode to set
	 */
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	/**
	 * Get the departure time of the flight
	 * 
	 * @return the departTime
	 */
	public String getDepartTime() {
		return departTime;
	}

	/**
	 * Set the departure time of the flight
	 * 
	 * @param departTime the departTime to set
	 */
	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}

	/**
	 * Get the code of the arrival airport
	 * 
	 * @return the arrivalCode
	 */
	public String getArrivalCode() {
		return arrivalCode;
	}

	/**
	 * Set the code of the arrival airport
	 * 
	 * @param arrivalCode the arrivalCode to set
	 */
	public void setArrivalCode(String arrivalCode) {
		this.arrivalCode = arrivalCode;
	}

	/**
	 * Get the arrival time of the flight
	 * 
	 * @return the arrivalTime
	 */
	public String getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Set the arrival time of the flight
	 * 
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Get the price of first class seat on the flight
	 * 
	 * @return the firstClassPrice
	 */
	public String getFirstClassPrice() {
		return firstClassPrice;
	}

	/**
	 * Set the price of first class seat on the flight
	 * 
	 * @param firstClassPrice the firstClassPrice to set
	 */
	public void setFirstClassPrice(String firstClassPrice) {
		this.firstClassPrice = firstClassPrice;
	}

	/**
	 * Get the price of coach seat on the flight
	 * 
	 * @return the coachPrice
	 */
	public String getCoachPrice() {
		return coachPrice;
	}

	/**
	 * Set the price of coach seat on the flight
	 * 
	 * @param coachPrice the coachPrice to set
	 */
	public void setCoachPrice(String coachPrice) {
		this.coachPrice = coachPrice;
	}

	/**
	 * Get the number of available first class seats on the flight
	 * 
	 * @return the firstClassSeats
	 */
	public int getFirstClassSeats() {
		return firstClassSeats;
	}

	/**
	 * Set the number of available first class seats on the flight
	 * 
	 * @param firstClassSeats the firstClassSeats to set
	 */
	public void setFirstClassSeats(int firstClassSeats) {
		this.firstClassSeats = firstClassSeats;
	}

	/**
	 * Get the number of available coach seats on the flight
	 * 
	 * @return the coachSeats
	 */
	public int getCoachSeats() {
		return coachSeats;
	}

	/**
	 * Set the number of available coach seats on the flight
	 * 
	 * @param coachSeats the coachSeats to set
	 */
	public void setCoachSeats(int coachSeats) {
		this.coachSeats = coachSeats;
	}
}
