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
//		long millSec = 0;
//		Calendar localCal = Calendar.getInstance();
//		localCal.set(Calendar.HOUR_OF_DAY, 20);
//		Calendar gmtCal = Calendar.getInstance();
//		if(localCal.get(Calendar.HOUR_OF_DAY)>19){
//			millSec = gmtCal.getTimeInMillis()+86400000;
//		}
//		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
//		String depDateString = format.format(millSec);
//		
//		System.out.println("local: "+ localCal.getTime());
//		System.out.println("gmt: "+ depDateString);
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
		long start = System.nanoTime();
		ItineraryBuilder builder = new ItineraryBuilder(m);
		Flight flight = new Flight();
		flight.setAirplane("A310");
		flight.setCoachSeats(200);
		long start1 = System.nanoTime();
		System.out.println(builder.coachSeatChecker(flight));
		long end1 = System.nanoTime();
		long used1 = end1 - start1;
		System.out.println("function used:" + TimeUnit.NANOSECONDS.toMillis(used1) + " ms");
		long end = System.nanoTime();
		long used = end - start;
		System.out.println("total used:" + TimeUnit.NANOSECONDS.toMillis(used) + " ms");
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
