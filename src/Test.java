import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;



import controller.ItineraryBuilder;
import controller.MyTime;
import model.Airplane;
import model.Airport;
import model.Flight;

public class Test {

	public static void main(String[] args) {
//	
		MyTime m = new MyTime();
//		ItineraryBuilder builder = new ItineraryBuilder(m); 
//		try {
//			TimeUnit.SECONDS.sleep(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		for(String code: m.getTimeZones().keySet()){
//			System.out.println("Key: "+code+" Value: "+m.getTimeZones().get(code));
//		}
//		m.stop();
//		AirportParser parser = new AirportParser();
//		List<Airport> airports = parser.start();
//		Airport startAirport = airports.get(8);
		//System.out.println("Key: " + startAirport.getCode()+" time Zone: "+ m.timeZoneCache.get(startAirport.getCode()));
//		PlaneParser parser = new PlaneParser();
//		List<Airplane> planes = parser.start();
		//planePrinter(planes);
//		String tempDateString = flightFrom.getArrivalTime();
		Airport currentAirport = new Airport("Fake","LGA",12.3,14.5);
		String tempDateString = "2016 May 10 23:30 GMT";
		Calendar gmtCal = m.StringToCalendar(tempDateString, "GMT");
		Calendar localCal = m.gmtToLocal(gmtCal, currentAirport);
		System.out.println("day: "+localCal.get(Calendar.DAY_OF_MONTH));
		System.out.println("hour: "+localCal.get(Calendar.HOUR_OF_DAY));
		long millSec;
		// If flight arrives at late night, 12:00-5 hours
		if(localCal.get(Calendar.HOUR_OF_DAY)>19||localCal.get(Calendar.HOUR_OF_DAY)==19){
			// Move to next day
			millSec = gmtCal.getTimeInMillis()+86400000;
		}else{
			millSec = gmtCal.getTimeInMillis();
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
		String depDateString = format.format(millSec);
		System.out.println(depDateString);

		
	}
	
	public static void planePrinter(List<Airplane> planes){
		for(Airplane plane: planes){
	          System.out.println("Manufacturer: " + plane.getManufacturer());
	          System.out.println("Model: " + plane.getModel());
	          System.out.println("First Class Seats: " + plane.getFirstClassSeats());
	          System.out.println("Coach Seats: " + plane.getCoachSeats());
	          System.out.println("-------------------------");
		}
	}

}
