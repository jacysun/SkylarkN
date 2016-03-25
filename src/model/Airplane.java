package model;

/**
 * This class contains the information of the airplane.
 */

public class Airplane {
	
	private String model;
	private String manufacturer;
	private int firstClassSeats;
	private int coachSeats;
	
	public Airplane (String model, String manufacturer, int firstClassSeats, int coachSeats) {
		this.model = model;
		this. manufacturer = manufacturer;
		this.firstClassSeats = firstClassSeats;
		this.coachSeats = coachSeats;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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
