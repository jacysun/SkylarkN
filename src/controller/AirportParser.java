package controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AirportParser
{
    public static void main(String[] args) 
    {
        try {
            new AirportParser().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() {
    	try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 */
    		URL url;
    		HttpURLConnection connection;
    		BufferedReader reader;
    		String line;
    		StringBuffer result = new StringBuffer();
    		
    		
			url = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team06&action=list&list_type=airports");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "team06");

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
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
				System.out.println(result.toString());

				InputStream input = null;
		    	    try
		    	    {
		    	    	byte[] bytes = result.toString().getBytes();
		    	    	input = new ByteArrayInputStream(bytes);
		    	       DocumentBuilderFactory factory = DocumentBuilderFactory.
		    	                                        newInstance();
		    	       DocumentBuilder builder = factory.newDocumentBuilder();
		    	       Document doc = builder.parse(input);
		    	       NodeList airports = doc.getElementsByTagName("Airport");

		    	       for(int i=0; i<airports.getLength();i++)
		    	       {
		    	          Element airport = (Element)airports.item(i);
		    	          String code = airport.getAttribute("Code");
		    	          String name = airport.getAttribute("Name");
		    	          String lat = airport.getFirstChild().getTextContent();
		    	          String lon = airport.getLastChild().getTextContent();
		    	          System.out.println("Code: " + code);
		    	          System.out.println("Name: " + name);
		    	          System.out.println("Latitude: " + lat);
		    	          System.out.println("Longitude: " + lon);
		    	          System.out.println("--------------------");
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
		    	          if (input != null)
		    	          input.close();
		    	       }
		    	       catch (IOException ex)
		    	       {
		    	          System.out.println(ex.getMessage());
		    	       }
		    	    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
//    	 InputStream inputXml = null;
//    	    try
//    	    {
//    	       inputXml  = new URL("http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team06&action=list&list_type=airports").openConnection().getInputStream();
//    	       DocumentBuilderFactory factory = DocumentBuilderFactory.
//    	                                        newInstance();
//    	       DocumentBuilder builder = factory.newDocumentBuilder();
//    	       Document doc = builder.parse(inputXml);
//    	       NodeList nodi = doc.getElementsByTagName("Airport");
//
//    	       for(int i=0; i<nodi.getLength();i++)
//    	       {
//    	          Element nodo = (Element)nodi.item(i);
//    	          String code = nodo.getAttribute("Code");
//    	          String name = nodo.getAttribute("Name");
//    	          System.out.println("Code: " + code);
//    	          System.out.println("Name: " + name);
//
//    	        }
//    	    }
//    	    catch (Exception ex)
//    	    {
//    	       System.out.println(ex.getMessage());
//    	    }
//    	    finally
//    	    {
//    	       try
//    	       {
//    	          if (inputXml != null)
//    	          inputXml.close();
//    	       }
//    	       catch (IOException ex)
//    	       {
//    	          System.out.println(ex.getMessage());
//    	       }
//    	    }
    	 }
    }
