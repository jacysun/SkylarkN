package controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import model.Flight;

/**
 * This class contains method to parse the fetched XML string with the information of departure flights.
 * 
 * Also create a list containing all the flights according to searching inputs.
 */

public class FlightParser {


    /**
     * Populates the flight list with flights on a specific date departing from a specific airport
     * 
     * Directly access the URL, bypassing the retrieval of XML into local variables and parse the source 
     * material from the URL.
     * 
     * @param depCode	Code of the airport to depart from
     * @param depDate	Date of the departure flight
     */
    public List<Flight> start(String depCode, String depDate) {
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
    
    /*public static void main(String[] args) {
    	List<Flight> flights = new ArrayList<Flight>();
    	FlightParser fp = new FlightParser();
    	flights = fp.start("CVG", "2016_05_12");
    	for (int i = 0; i < flights.size(); i++) {
    		System.out.println(flights.get(i).getAirplane());
    		System.out.println(flights.get(i).getNumber());
    		System.out.println(flights.get(i).getDepartCode());
    		System.out.println(flights.get(i).getArrivalCode());
    		System.out.println(flights.get(i).getDepartTime());
    		System.out.println(flights.get(i).getArrivalTime());
    		System.out.println(flights.get(i).getDuration());
    		System.out.println(flights.get(i).getCoachSeats());
    		System.out.println(flights.get(i).getCoachPrice());
    		System.out.println(flights.get(i).getFirstClassSeats());
    		System.out.println(flights.get(i).getFirstClassPrice());   	
    		System.out.println("-----------------------------");	
    	}
    }*/
   
}

