package model;

/**
 * This class holds values pertaining to a single Airplane. Class member attributes are
 * the same as defined by the CS509 server API and store values after conversion from 
 * XML received from the server to Java primitives. Attributes are accessed via getter 
 * and setter methods.
 */

public class Airplane {
	
	/**
	 * Airport attributes as defined by the CS509 server interface XML
	 */
	private String model;                     // Model of the airplane
	private String manufacturer;              // Manufacturer of the airplane
	private int firstClassSeats;              // Number of first class seats on the airplane
	private int coachSeats;                   // Number of coach seats on the airplane
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with input values.
	 * 
	 * @param model The model of the airplane
	 * @param manufacturer The manufacturer of the airplane
	 * @param firstClassSeats The number of first class seats on the airplane
	 * @param coachSeats The number of coach seats on the airplane
	 */
	public Airplane (String model, String manufacturer, int firstClassSeats, int coachSeats) {
		this.model = model;                            
		this. manufacturer = manufacturer;           
		this.firstClassSeats = firstClassSeats;        
		this.coachSeats = coachSeats;                  
	}

	/**
	 * Get the airplane model
	 * 
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Set the airplane model
	 * 
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Get the airplane manufacturer
	 * 
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Set the airplane manufacturer
	 * 
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Get the number of first class seats on the airplane
	 * 
	 * @return the firstClassSeats
	 */
	public int getFirstClassSeats() {
		return firstClassSeats;
	}

	/**
	 * Set the number of first class seats on the airplane
	 * 
	 * @param firstClassSeats the firstClassSeats to set
	 */
	public void setFirstClassSeats(int firstClassSeats) {
		this.firstClassSeats = firstClassSeats;
	}

	/**
	 * Get the number of coach seats on the airplane
	 * 
	 * @return the coachSeats
	 */
	public int getCoachSeats() {
		return coachSeats;
	}

	/**
	 * Set the number of coach seats on the airplane
	 * 
	 * @param coachSeats the coachSeats to set
	 */
	public void setCoachSeats(int coachSeats) {
		this.coachSeats = coachSeats;
	}
}
