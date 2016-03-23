package controller;

/**
 * Build Queries.
 */

public class QueryFactory {
	
	/**
	 * Return query parameter to get airport from database.
	 * 
	 * @param team 
	 * @return 
	 */
	public static String getAirports(String team) {
		return "?team=" + team + "&action=list&list_type=airports";
	}
	
	/**
	 * Return
	 * 
	 * @param team
	 * @return
	 */
	
	public static String getAirplanes(String team) {
		return "?team=" + team + "&action=list&list_type=airplanes";
	}
	
	/**
	 * Return
	 * 
	 * @param team
	 * @param code
	 * @param day
	 * @return
	 */
	public static String getDepartingFlights(String team, String code, String day) {
		return "?team=" + team + "&action=list&list_type=departing&airport=" + code + "&day=" + day;
	}
	
	/**
	 * Return
	 * 
	 * @param team
	 * @param code
	 * @param day
	 * @return
	 */
	/*public static String getArrivingFlights(String team, String code, String day) {
		return "?team=" + team + "&action=list&list_type=arriving&airport=" + code + "&day=" + day;
	}*/
	
	/**
	 * Return
	 * 
	 * @param team
	 * @return
	 */
	public static String lock(String team) {
		return "team=" + team + "&action=lockDB";
	}
	
	/**
	 * Return
	 * 
	 * @param team
	 * @return
	 */
	public static String unlock(String team) {
		return "team=" + team + "&action=unlockDB";
	}
	
	/**
	 * Return
	 * 
	 * @param team
	 * @param xmlFlights
	 * @return
	 */
	public static String reserve(String team, String xmlFlights) {
		return "team=" + team + "&action=buyTickets&flightData=" + xmlFlights;
	}
	
	
	

}
