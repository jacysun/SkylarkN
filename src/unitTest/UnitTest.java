package unitTest;

import java.util.ArrayList;
import java.util.List;

import controller.ItineraryBuilder;
import controller.MyTime;
import model.Flight;

public class UnitTest {

	public static void main(String[] args) {
//		// Flight arrive at DEN
//		Flight flightArrival = new Flight("A320", "3587", 227, "CLT", "2016 May 12 00:51 GMT", "DEN",
//				"2016 May 12 04:41 GMT", 198.00, 220.00, 20, 20);
//		// Flight depart from DEN
//		Flight flightDepart = new Flight("C120", "2681", 327, "DEN", "2016 May 12 05:41 GMT", "BOS",
//				"2016 May 12 21:38 GMT", 198.00, 220.00, 20, 20);
//		MyTime m = new MyTime();
//		ItineraryBuilder builder = new ItineraryBuilder(m);
//		boolean result = builder.layoverChecker(flightArrival, flightDepart);
//		System.out.println(result);
		
		List<String> h1 = new ArrayList<>();
		List<String> h2 = new ArrayList<>();
		h1.add("1");
		h1.add("1");
		h1.add("1");
		h1.add("1");
		h2.add("2");
		h2.add("2");
		h1.addAll(h2);
		for(String h: h1){
			System.out.println(h);
		}
	}

}
