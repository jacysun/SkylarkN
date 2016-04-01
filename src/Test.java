import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import controller.AirportParser;
import controller.FlightParser;
import controller.MyTime;
import model.Airport;
import model.Flight;

public class Test {

	public static void main(String[] args) {
		
		HashMap<String, Airport> airportCache = new HashMap<String, Airport>();
		AirportParser parser = new AirportParser();
		List<Airport> airports = parser.start();
		
		for(int i=0;i<airports.size();i++){
			airportCache.put(airports.get(i).getCode(), airports.get(i));
		}
		
		for(int i=0;i<airportCache.size();i++){
			Airport output = airportCache.get(airports.get(i));
			System.out.println(output.getName());
		}
	}

}
