package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class contains methods for retrieving data from database.
 */
public class RetrieveData {
	private final String mUrlBase = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";

	/**
	 * Return an XML list of all the airports
	 * 
	 * Retrieve the list of airports available to the specified ticketAgency via HTTPGet of the server
	 * 
	 * @param team identifies the ticket agency requesting the information
	 * @return xml string listing all airports
	 */
	public String getAirports (String team) {

		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 */
			url = new URL(mUrlBase + QueryFactory.getAirports(team));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", team);

			/**
			 * If response code of SUCCESS read the XML string returned line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if ((responseCode >= 200) && (responseCode <= 299)) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "URF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}
	
	/**
	 * Return an XML list of all the flights
	 * 
	 * Retrieve the list of flights available to the specified ticketAgency, departure airport and departure date via HTTPGet of the server
	 * 
	 * @param team identifies the ticket agency requesting the information
	 * @param departCode identifies the departure airport
	 * @param date identifies the date of departure
	 * @return xml string listing all departure flights
	 */
	public String getDepartureFlights (String team, String departCode, String date) {
		
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 */
			url = new URL(mUrlBase + QueryFactory.getDepartingFlights(team, departCode, date));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", team);

			/**
			 * If response code of SUCCESS read the XML string return line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if ((responseCode >= 200) && (responseCode <= 299)) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "URF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}
	
	/**
	 * Return an XML list of all the airplanes
	 * 
	 * Retrieve the list of airports available to the specified ticketAgency via HTTPGet of the server
	 * 
	 * @param team identifies the ticket agency requesting the information
	 * @return xml string listing all the airplanes
	 */
	public String getAirplanes (String team) {
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
	      try {
	          url = new URL(mUrlBase + QueryFactory.getAirplanes(team));
	          connection = (HttpURLConnection) url.openConnection();
	          connection.setRequestMethod("GET");
	          connection.setRequestProperty("User-Agent", team);
	          
	          int responseCode = connection.getResponseCode();
	          if ((responseCode >= 200) && (responseCode <= 299)) {
		          InputStream inputStream = connection.getInputStream();
		          String encoding = connection.getContentEncoding();
		          encoding = (encoding == null ? "URF-8" : encoding);
		          
		          reader = new BufferedReader(new InputStreamReader(inputStream));
		          while ((line = reader.readLine()) != null) {
		             result.append(line);
		          }
		          reader.close();
	          }
	       } catch (IOException e) {
	          e.printStackTrace();
	       } catch (Exception e) {
	          e.printStackTrace();
	       }
	       return result.toString();	
	}
}

