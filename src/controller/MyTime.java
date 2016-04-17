package controller;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import model.Airport;

/**
 * Convert between local and GMT.
 * Calculate time intervals.
 */

public class MyTime{
	// Google API Key
	private static final String KEY = "&key=AIzaSyAHlM31VslBaSQnxxt6wvDuVll0vpgZvvs";
	// Base query for timeZone service
	private static final String baseUrl = "https://maps.googleapis.com/maps/api/timezone/xml?";
	// cache for storing time zone information
	private HashMap<Airport,String> timeZoneCache;
	
	private TimeZonePool runner;
	private Thread thread;
	
	/**
	 * MyTime constructor
	 */
	public MyTime(){
		timeZoneCache = new HashMap<Airport,String>();
		this.runner = new TimeZonePool();
		this.thread = new Thread(runner);
		thread.start();
	}
	
	public HashMap<Airport,String> getTimeZones(){
		return this.timeZoneCache;
	}
	/**
	 * Stop background thread.
	 */
	public void stop(){
		if(runner.isFinished()){
			try {
				thread.join();
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		} else {
			System.out.println("The process is not finished yet...");
		}
	}
	
	// Background thread
	private class TimeZonePool implements Runnable{
		private List<Airport> airportList;
		private int counter;
		
		// Called by other to know if background thread is finished.
		public boolean isFinished(){
			if(counter==airportList.size()){
				return true;
			} else{
				return false;
			}
		}
		
		@Override
		public void run(){
			//mark time starts...
			long start = System.nanoTime();

			AirportParser parser = new AirportParser();
			this.airportList = parser.start();
			for(Airport airport: airportList){
				timeZoneCache.put(airport, null);
			}
			this.counter = 0;
			while(counter<timeZoneCache.size()){
				for(Airport airport: timeZoneCache.keySet()){
					if(timeZoneCache.get(airport)==null){
						String zone = timeZoneForAirport(airport);
						timeZoneCache.put(airport, zone);
					}else{
						counter++;
					}
				}
				try {
					Thread.sleep(500);
					System.out.println("runner is sleeping for 0.5 sec...");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// mark time ends
			long end = System.nanoTime();
			long used = end - start;
			System.out.println("used:" + TimeUnit.NANOSECONDS.toMillis(used) + " ms");	
			System.out.println("Result check: HashMap has "+timeZoneCache.size()+" time zones.");
			for(Airport airport: timeZoneCache.keySet()){
				System.out.println("Key: "+airport.getCode()+" Value: "+timeZoneCache.get(airport));
			}
		}
		
		/**
		 * Get a timeZone string with latitude and longitude of an airport
		 * 
		 * Call Google TimeZone API to xml, parse this xml to get timezone string
		 * 
		 * @param airport
		 * @return
		 */
		public String timeZoneForAirport(Airport airport){
			double latitude = airport.getLatitude();
			double longitude = airport.getLongitude();
			StringBuffer response = getResponse(latitude, longitude);
			
			InputStream input = null;
		    try
		    {
		    	byte[] bytes = response.toString().getBytes();
		    	input = new ByteArrayInputStream(bytes);
		    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();                         
		    	DocumentBuilder builder = factory.newDocumentBuilder();
		    	Document doc = builder.parse(input);
		    	NodeList node = doc.getElementsByTagName("TimeZoneResponse");     
		    	Element element = (Element) node.item(0);
		    	String timeZoneId = element.getElementsByTagName("time_zone_id").item(0).getTextContent();
		    	return timeZoneId;     
		    }
		    catch (Exception ex)
		    {
		       ex.getStackTrace();
		       return null;
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
		          ex.getStackTrace();
		       }
		    }
		}
		
		/**
		 * Get response from Google API with a location
		 * 
		 * @param latitude
		 * @param longitude
		 * @return
		 */
		private StringBuffer getResponse(double latitude, double longitude){
			// Build query parameters, location and default timestamp
			String locationPar = "location=" + String.valueOf(latitude) + "," + String.valueOf(longitude);
			String timeStampPar = "&timestamp=1331161200";
			StringBuffer response = new StringBuffer();
			
			try{
				URL url = new URL(baseUrl+locationPar+timeStampPar+KEY);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				//connection.setRequestMethod("GET");
				int responseCode = connection.getResponseCode();
				
				if(responseCode == HttpURLConnection.HTTP_OK){
					InputStream stream = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
					String buffer;
					
					while((buffer=reader.readLine())!=null){
						response.append(buffer);
					}
					reader.close();
					//System.out.println(result.toString());
					return response;
				}		
				return null;	
			} catch(IOException ioe){
				ioe.printStackTrace();
				return null;
			}
		}
	}
	
	/**
	 * Convert a GMT to a local time of an airport
	 * 
	 * @param gmtCal
	 * @param airport
	 * @return
	 */
	public Calendar gmtToLocal(Calendar gmtCal, Airport airport){	
		if(!timeZoneCache.containsKey(airport.getCode())){
//			String timeZone = timeZoneForAirport(airport);
//			timeZoneCache.put(airport.getCode(), timeZone);
		}
		Calendar cal = (Calendar) gmtCal.clone();
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal.setTimeZone(TimeZone.getTimeZone(timeZoneCache.get(airport.getCode())));
		return cal;
	}
	/**
	 * Convert a local time of an airport to GMT
	 * 
	 * @param localCal
	 * @param airport
	 * @return
	 */
	public Calendar localToGmt(Calendar localCal, Airport airport){
		if(!timeZoneCache.containsKey(airport.getCode())){
//			String timeZone = timeZoneForAirport(airport);
//			timeZoneCache.put(airport.getCode(), timeZone);
		}
		Calendar cal = (Calendar) localCal.clone();
		cal.setTimeZone(TimeZone.getTimeZone(timeZoneCache.get(airport.getCode())));
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		return cal;
	}
	
	/**
	 * Calculate the interval between two GMT time
	 * 
	 * @param calFir
	 * @param calSec
	 * @return
	 */
	public static double getInterval(Calendar calFir, Calendar calSec){
		final int SECOND = 1000;
		final int MINUTE = 60 * SECOND;
		final int HOUR = 60 * MINUTE;
		long timeFir = calFir.getTimeInMillis();
		long timeSec = calSec.getTimeInMillis();
		double interval = Math.abs((double)(timeFir-timeSec)/HOUR);
		return interval;
	}
	
	
	/**
	 * Convert String to calendar form "yyyy MMM dd HH:mm z"
	 * 
	 * @param dateString
	 * @return
	 */
	public Calendar StringToCalendar(String dateString, String timeZone){
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(timeZone));
		SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd HH:mm z",Locale.ENGLISH);
		try {
			cal.setTime(format.parse(dateString));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return cal;			
	}
	

	public String CalendarToString(Calendar cal){
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String month = String.valueOf(cal.get(Calendar.MONTH)+1);
		String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		String hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		String minute = String.valueOf(cal.get(Calendar.MINUTE));
		String timeZone = cal.getTimeZone().getDisplayName();
		String time = year+" "+month+" "+day+" "+hour+":"+minute+" "+timeZone;
		return time;
	}
	


	public Calendar stringToCalendar(String input) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd HH:mm z");
		Calendar cal = Calendar.getInstance();
		cal.setTime(format.parse(input));
		return cal;
	}
	
	public String calendarToString(Calendar cal){
      String year = String.valueOf(cal.get(Calendar.YEAR));
      String month;
      switch (cal.get(Calendar.MONTH)+1) {
		case 1: month = "JAN";
				break;
		case 2: month = "FEB";
				break;
		case 3: month = "MAR";
				break;
		case 4: month = "APR";
				break;
		case 5: month = "MAY";
				break;
		case 6: month = "JUN";
				break;
		case 7: month = "JUL";
				break;
		case 8: month = "AUG";
				break;
		case 9: month = "SEP";
				break;
		case 10: month = "OCT";
				break;
		case 11: month = "NOV";
				break;
		case 12: month = "DEC";
				break;
		default: month = "Invalid";
				break;
	}
      String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
      String hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
      String minute = String.valueOf(cal.get(Calendar.MINUTE));
      String time = month + " " + day + ", " + year + " " + hour + ":" + minute;
      return time;
	}
	
}

