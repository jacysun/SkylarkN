package controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class PlaneParser
{
    public static void main(String[] args) 
    {
        try {
            new PlaneParser().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() {
    	 InputStream inputXml = null;
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
    	          String cs = plane.getLastChild().getTextContent();
    	          System.out.println("Manufacturer: " + manu);
    	          System.out.println("Model: " + mod);
    	          System.out.println("First Class Seats: " + fs);
    	          System.out.println("Coach Seats: " + cs);
    	          System.out.println("-------------------------");
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
//    private void start() throws Exception
//    
//    {
//        URL url = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team06&action=list&list_type=airports");
//        URLConnection connection = url.openConnection().getInputStream();
//
//        Document doc = parseXML(connection);
//        NodeList descNodes = doc.getElementsByTagName("description");
//
//        for(int i=0; i<descNodes.getLength();i++)
//        {
//            System.out.println(descNodes.item(i).getTextContent());
//        }
//    }

//    private Document parseXML(InputStream stream)
//    throws Exception
//    {
//        DocumentBuilderFactory objDocumentBuilderFactory = null;
//        DocumentBuilder objDocumentBuilder = null;
//        Document doc = null;
//        try
//        {
//            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
//            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
//
//            doc = objDocumentBuilder.parse(stream);
//        }
//        catch(Exception ex)
//        {
//            throw ex;
//        }       
//
//        return doc;
//    }
//}