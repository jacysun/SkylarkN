import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import controller.AirportParser;
import controller.FlightParser;
import controller.MyTime;
import model.Airport;
import model.Flight;

public class Test {

	public static void main(String[] args) {
		
		long start = System.nanoTime();
		
		for (int j = 0; j < 100; j++) {
			for (int i = 0; i < 100; i++) {
				Airport airport = new Airport();
				if (i < 11) {
					continue;
				}
				if (i > 23 && i < 55) {
					continue;
				}
				if (i + 14 > 98) {
					continue;
				}
			}
		}
		long end = System.nanoTime();
	    long used = end-start;
		System.out.println("used:"+TimeUnit.NANOSECONDS.toMillis(used)+" ms");
	}
	
}
