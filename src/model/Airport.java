package model;

/**
 * This class holds values pertaining to a single Airport. Class member attributes are
 * the same as defined by the CS509 server API and store values after conversion from 
 * XML received from the server to Java primitives. Attributes are accessed via getter 
 * and setter methods.
 */
public class Airport {
	
	/**
	 * Constant values used for latitude and longitude range validation
	 */
	static final double MAX_LATITUDE = 90.0;
	static final double MIN_LATITUDE = -90.0;
	static final double MAX_LONGITUDE = 180.0;
	static final double MIN_LONGITUDE = -180.0;
	
	/**
	 * Airport attributes as defined by the CS509 server interface XML
	 */
	private String name;         // Full name of the airport
	private String code;         // Three character code of the airport
	private double latitude;     // Latitude of the airport in decimal format
	private double longitude;    // Longitude of the airport in decimal format
	
	/**
	 * Default constructor
	 * 
	 * Constructor without params. Requires object fields to be explicitly set using setter methods.
	 * 
	 * @precondition None
	 * @postcondition member attributes are initialized to invalid default values
	 */
	public Airport () {
		name = "";
		code = "";
		latitude = Double.MAX_VALUE;
		longitude = Double.MAX_VALUE;
	}
	
	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with input values.
	 * 
	 * @param name The name of the airport
	 * @param code The 3 letter code for the airport
	 * @param latitude The north/south coordinate of the airport
	 * @param longitude The east/west coordinate of the airport
	 * 
	 * @preconditions code is a 3 character string, latitude and longitude are valid values
	 * @postconditions member attributes are initialized with input parameter values
	 */
	public Airport (String name, String code, double latitude, double longitude) {
		this.name = name;
		this.code = code;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Get the airport name
	 * 
	 * @return Airport name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the airport name
	 * 
	 * @param name The name of the airport
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the 3 letter airport code
	 * 
	 * @return The 3 letter airport code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * Set the 3 letter airport code
	 * 
	 * @param code The 3 letter airport code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Get the latitude for the airport
	 * 
	 * @return The north/south coordinate of the airport
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * Set the latitude for the airport
	 * 
	 * @param latitude The north/south coordinate of the airport
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * Get the longitude for the airport
	 * 
	 * @return The east/west coordinate of the airport
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * Set the longitude for the airport
	 * 
	 * @param longitude The east/west coordinate of the airport
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/*public boolean isReasonableStop(Airport depart, Airport arrival) {
		long distance1 = inMiles (depart.latitude, depart.longitude, arrival.latitude, arrival.longitude);
		long distance2 = inMiles (depart.latitude, depart.longitude, this.latitude, this.longitude);
		if (distance2/distance1 > 10) {
			return false;
		} else {
			return true;
		}
	}
	
	public static long inMiles (double lat1, double lon1, double lat2, double lon2) {
		double dMiles = distance (lat1, lon1, lat2, lon2, "M");
		return Math.round(dMiles); 
	}
	
	private static double distance (double lat1, double lon1, double lat2, double lon2, String sr) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (sr.equals("K")) {
          dist = dist * 1.609344;
        } else if (sr.equals("N")) {
          dist = dist * 0.8684;
          }
        return (dist);
      }
  private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
      }
  private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
      } */
}
