package controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Airplane;
import model.Airport;
import model.Flight;

public class DataRetriever {
	
	/**
     * Populates the flight list with flights on a specific date departing from a specific airport
     * 
     * Directly access the URL, bypassing the retrieval of XML into local variables and parse the source 
     * material from the URL.
     * 
     * @param depCode	Code of the airport to depart from
     * @param depDate	Date of the departure flight
     */
    public List<Flight> getFlights(String depCode, String depDate) {
    	 List<Flight> flightList = new ArrayList<Flight>();
    	 InputStream inputXml = null;
    	 try {
    	     inputXml  = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team06&action=list&list_type=departing&airport=" + depCode + "&day=" + depDate).openConnection().getInputStream();
    	     DocumentBuilderFactory factory = DocumentBuilderFactory.
    	                                        newInstance();
    	     DocumentBuilder builder = factory.newDocumentBuilder();
    	     Document doc = builder.parse(inputXml);
    	     NodeList flights = doc.getElementsByTagName("Flight");

    	     for(int i=0; i<flights.getLength();i++) {
    	        Element flight = (Element)flights.item(i);
    	        String plane = flight.getAttribute("Airplane");
    	        double ftime = Double.parseDouble(flight.getAttribute("FlightTime"));
    	        String num = flight.getAttribute("Number");
    	        Node dep = flight.getFirstChild();
    	        String dcode = dep.getFirstChild().getTextContent();
    	        String dtime = dep.getLastChild().getTextContent();
    	        Node arr = dep.getNextSibling();
    	        String acode = arr.getFirstChild().getTextContent();
    	        String atime = arr.getLastChild().getTextContent();
    	        Node seat = arr.getNextSibling();
    	        int fcn = Integer.parseInt(seat.getFirstChild().getTextContent());
    	        double fcp = Double.parseDouble(((Element) seat.getFirstChild()).getAttribute("Price").replaceAll("[^\\d.]+", ""));
    	        int cn = Integer.parseInt(seat.getLastChild().getTextContent());
    	        double cp = Double.parseDouble(((Element) seat.getLastChild()).getAttribute("Price").replaceAll("[^\\d.]+", ""));
    	        Flight fl = new Flight(plane, num, ftime, dcode, dtime, acode, atime, fcp, cp, fcn, cn);
    	        flightList.add(fl);  	          
    	      }
    	  }
    	  catch (Exception ex) {
    	       System.out.println(ex.getMessage());
    	  }
    	  finally {
    		  try {
    			  if (inputXml != null)
    	          inputXml.close();
    		  }
    	       catch (IOException ex) {
    	          System.out.println(ex.getMessage());
    	      }
    	  }
    	 return flightList;
    }
    
    public List<Airport> getAirports() {
		List<Airport> airportList = new ArrayList<Airport>();
		try {
			/**
			 * Create an HTTP connection to the server for a GET
			 */
			URL url;
			HttpURLConnection connection;
			BufferedReader reader;
			String line;
			StringBuffer result = new StringBuffer();

			url = new URL(
					"http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team06&action=list&list_type=airports");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "team06");

			/**
			 * If response code of SUCCESS read the XML string returned line by
			 * line to build the full return string
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
				//System.out.println(result.toString());

				InputStream input = null;
				try {
					byte[] bytes = result.toString().getBytes();
					input = new ByteArrayInputStream(bytes);
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(input);
					NodeList airports = doc.getElementsByTagName("Airport");

					for (int i = 0; i < airports.getLength(); i++) {
						Element airport = (Element) airports.item(i);
						String code = airport.getAttribute("Code");
						String name = airport.getAttribute("Name");
						String latString = airport.getFirstChild().getTextContent();
						double lat = Double.parseDouble(latString.trim());
						String lonString = airport.getLastChild().getTextContent();
						double lon = Double.parseDouble(lonString.trim());
						Airport ap = new Airport(name, code, lat, lon);
						airportList.add(ap);
						// System.out.println("Code: " + code);
						// System.out.println("Name: " + name);
						// System.out.println("Latitude: " + lat);
						// System.out.println("Longitude: " + lon);
						// System.out.println("--------------------");
					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					return null;
				} finally {
					try {
						if (input != null)
							input.close();
					} catch (IOException ex) {
						System.out.println(ex.getMessage());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return airportList;
    }
    
    public List<Airplane> getAirplanes() {
   	 InputStream inputXml = null;
   	 List<Airplane> planeList = new ArrayList<Airplane>();
   	    try
   	    {
   	       inputXml  = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team06&action=list&list_type=airplanes").openConnection().getInputStream();
   	       DocumentBuilderFactory factory = DocumentBuilderFactory.
   	                                        newInstance();
   	       DocumentBuilder builder = factory.newDocumentBuilder();
   	       Document doc = builder.parse(inputXml);
   	       NodeList planes = doc.getElementsByTagName("Airplane");
   	       

   	       for(int i=0; i<planes.getLength();i++)
   	       {
   	          Element plane = (Element)planes.item(i);
   	          String manu = plane.getAttribute("Manufacturer");
   	          String mod = plane.getAttribute("Model");
   	          String fs = plane.getFirstChild().getTextContent();
   	          int firstSeat = Integer.parseInt(fs);
   	          String cs = plane.getLastChild().getTextContent();
   	          int coachSeat = Integer.parseInt(cs);
   	          Airplane airplane = new Airplane(mod,manu,firstSeat,coachSeat);
   	          planeList.add(airplane);
//   	          System.out.println("Manufacturer: " + manu);
//   	          System.out.println("Model: " + mod);
//   	          System.out.println("First Class Seats: " + fs);
//   	          System.out.println("Coach Seats: " + cs);
//   	          System.out.println("-------------------------");
   	        }
   	       
   	    }
   	    catch (Exception ex)
   	    {
   	       System.out.println(ex.getMessage());
   	    }
   	    finally
   	    {
   	       try
   	       {
   	          if (inputXml != null)
   	          inputXml.close();
   	       }
   	       catch (IOException ex)
   	       {
   	          System.out.println(ex.getMessage());
   	       }
   	    }
   	    return planeList;
   	 }
}
