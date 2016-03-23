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

public class FlightParser
{
	
	public ArrayList<Flight> flightList = new ArrayList<Flight>();
	
    public static void main(String[] args, String depCode, String depDate) 
    {
        try {
            new FlightParser().start(depCode, depDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(String depCode, String depDate) {
    	 InputStream inputXml = null;
    	    try
    	    {
    	       inputXml  = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team06&action=list&list_type=departing&airport=" + depCode + "&day=" + depDate).openConnection().getInputStream();
    	       DocumentBuilderFactory factory = DocumentBuilderFactory.
    	                                        newInstance();
    	       DocumentBuilder builder = factory.newDocumentBuilder();
    	       Document doc = builder.parse(inputXml);
    	       NodeList flights = doc.getElementsByTagName("Flight");

    	       for(int i=0; i<flights.getLength();i++)
    	       {
    	          Element flight = (Element)flights.item(i);
    	          String plane = flight.getAttribute("Airplane");
    	          String ftime = flight.getAttribute("FlightTime");
    	          String num = flight.getAttribute("Number");
    	          Node dep = flight.getFirstChild();
    	          String dcode = dep.getFirstChild().getTextContent();
    	          String dtime = dep.getLastChild().getTextContent();
    	          Node arr = dep.getNextSibling();
    	          String acode = arr.getFirstChild().getTextContent();
    	          String atime = arr.getLastChild().getTextContent();
    	          Node seat = arr.getNextSibling();
    	          int fcn = Integer.parseInt(seat.getFirstChild().getTextContent());
    	          String fcp = ((Element) seat.getFirstChild()).getAttribute("Price");
    	          int cn = Integer.parseInt(seat.getLastChild().getTextContent());
    	          String cp = ((Element) seat.getLastChild()).getAttribute("Price");
    	          String fcn2 = Integer.toString(fcn);
    	          String cn2 = Integer.toString(cn);
    	          Flight fl = new Flight(plane, num, ftime, dcode, dtime, acode, atime, fcp, cp, fcn, cn);
    	          flightList.add(fl);  	          
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
    	 }
    }
