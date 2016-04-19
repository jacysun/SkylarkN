package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import controller.ItineraryBuilder;
import controller.ItineraryBuilder.RoundTrip;
import controller.ItineraryBuilder.Schedule;
import controller.MyTime;

/**
 * This class represents an itinerary.
 *
 */
public class Itinerary {
	
	   MyTime myTime = new MyTime();
	
	   private List<Flight> flights = new ArrayList<Flight>();
	   private double totalPrice;
	   private double duration;
	   private int stopNum;
	   private String seatType;
	   
	   public Itinerary() {}
	   
	   public Itinerary(int stopNum, double duration, double totalPrice, List<Flight> flights, String seatType) {
		   this.stopNum = stopNum;
		   this.duration = duration;
		   this.totalPrice = totalPrice;
		   this.flights = flights;
		   this.seatType = seatType;
	   }
		   
		   public int getStopNum() {
			   return stopNum;
		   }
		
		   public List<Flight> getFlights() {
			   return flights;
		   }
		
		   public double getTotalPrice() {
			   return totalPrice;
		   }
		
		   public double getDuration() {
			   return duration;
		   }
		
	       public String getSeatType() {
			   return seatType;
		   }
	   
	   public ArrayList<Itinerary> getOneWayList(String depCode, String arrCode, String depDate, String seatType) throws ParseException {
			ArrayList<Itinerary> oneWayList = new ArrayList<Itinerary>(); 
			Itinerary itinerary = new Itinerary();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Calendar depart = Calendar.getInstance();
	        depart.setTime(formatter.parse(depDate));
	        
	        Airport departure = new Airport();
			departure = departure.getAirport(depCode);
			Airport arrival = new Airport();
			arrival = arrival.getAirport(arrCode);
			if (departure == null || arrival == null) {
				return null;
			} else {
				ItineraryBuilder ib = new ItineraryBuilder(myTime);
				List<Schedule> scheduleList = new ArrayList<Schedule>();
				if (seatType.equals("coach")) {
					scheduleList = ib.oneWayTrip(departure, arrival, depart, 2, true);
				} else if (seatType.equals("firstclass")){
					scheduleList = ib.oneWayTrip(departure, arrival, depart, 2, false);
				}
				for (int i = 0; i < scheduleList.size(); i++) {
					 List<Flight> flights = scheduleList.get(i).getVoyoage();
					 int stopNum = scheduleList.get(i).getStopCounter(); 
					 double duration = 0;
					 double totalPrice = 0;
					for (int j = 0; j < flights.size(); j++) {
						duration += flights.get(j).getDuration();
						if (seatType.equals("coach")) {
							totalPrice += flights.get(j).getCoachPrice();
							System.out.println((j+1) + "leg price: " + flights.get(j).getCoachPrice() + " duration: " + flights.get(j).getDuration());
						} else if (seatType.equals("firstclass")) {
							totalPrice += flights.get(j).getFirstClassPrice();
						}
					}
					totalPrice = Math.floor(totalPrice*100)/100;
					System.out.println("total Price: " + totalPrice);
					double interval = 0;
					MyTime myTime = new MyTime();
					for (int k = 0; k < stopNum; k++) {
						Calendar arr = myTime.StringToCalendar(flights.get(k).getArrivalTime(),"GMT");
						Calendar dep = myTime.StringToCalendar(flights.get(k+1).getDepartTime(),"GMT");
						interval += myTime.getInterval(arr, dep);
						System.out.println((k+1) + "interval: " + myTime.getInterval(arr, dep));
					}
					duration = duration + interval;
					duration = Math.floor(duration*100)/100;
					System.out.println("total duration: " + duration) ;
					System.out.println("---------------");

					itinerary = new Itinerary(stopNum, duration, totalPrice, flights, seatType);
					oneWayList.add(itinerary);
				}
			
			}
			return oneWayList;
		}
	   

	public class RoundTripItinerary {
		
	       private Itinerary depItinerary;
	       private Itinerary retItinerary;
	       private double totalPrice;
	       private double duration;
	       

		   public RoundTripItinerary() {}
		
		   public RoundTripItinerary(Itinerary depItinerary, Itinerary retItinerary, double totalPrice, double duration) {
			   this.depItinerary = depItinerary;
			   this.retItinerary = retItinerary;
			   this.totalPrice = totalPrice;
			   this.duration = duration;
		   }
		
		   public Itinerary getDepItinerary() {
			   return depItinerary;
		   }
		   
		   public Itinerary getRetItinerary() {
			   return retItinerary;
		   }
		   
		   public double getTotalPrice() {
			   return totalPrice;
		   }
		   
		   public double getDuration() {
			   return duration;
		   }
	}
	
	public ArrayList<RoundTripItinerary> getRoundTripList(String depCode, String arrCode, String depDate, String seatType, String retDate) throws ParseException {
		ArrayList<RoundTripItinerary> roundTripList = new ArrayList<RoundTripItinerary>();
		RoundTripItinerary rtItinerary = new RoundTripItinerary();
		Itinerary depItinerary = new Itinerary();
		Itinerary retItinerary = new Itinerary();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar depart = Calendar.getInstance();
        depart.setTime(formatter.parse(depDate));
        Calendar retur = Calendar.getInstance();
        retur.setTime(formatter.parse(retDate));
        
        List<RoundTrip> roundList = new ArrayList<RoundTrip>();
        Airport departure = new Airport();
		departure = departure.getAirport(depCode);
		Airport arrival = new Airport();
		arrival = arrival.getAirport(arrCode);
		if (departure == null || arrival == null) {
			return null;
		} else {
			ItineraryBuilder ib = new ItineraryBuilder(myTime);
			if (seatType.equals("coach")) {
				roundList = ib.roundTrip(departure, arrival, depart, 2, true, retur);
			} else if (seatType.equals("firstclass")) {
				roundList = ib.roundTrip(departure, arrival, depart, 2, false, retur);				
			}
			for (int i = 0; i < roundList.size(); i++) {
				Schedule departSchedule = roundList.get(i).getOutBound();
			    Schedule returnSchedule = roundList.get(i).getInBound();
			    int depStopNum = departSchedule.getStopCounter();
			    int retStopNum = returnSchedule.getStopCounter();
			    List<Flight> depList = departSchedule.getVoyoage();
			    List<Flight> retList = returnSchedule.getVoyoage();
			    double depDuration = 0;
			    double depTotalPrice = 0;
			    double retDuration = 0;
			    double retTotalPrice = 0;
			    
			    // Departure Itinerary
			    System.out.println("Departure Itinerary:");
				for (int j = 0; j < depList.size(); j++) {
					depDuration += depList.get(j).getDuration();
					if (seatType.equals("coach")) {
						depTotalPrice += depList.get(j).getCoachPrice();
						System.out.println((j+1) + "leg price: " + depList.get(j).getCoachPrice() + " duration: " + depList.get(j).getDuration());
					} else if (seatType.equals("firstclass")) {
						depTotalPrice += depList.get(j).getFirstClassPrice();
					}
				}
				System.out.println("total Price: " + depTotalPrice);
				double depInterval = 0;
				MyTime myTime = new MyTime();
				for (int k = 0; k < depStopNum; k++) {
					Calendar arr = myTime.StringToCalendar(depList.get(k).getArrivalTime(),"GMT");
					Calendar dep = myTime.StringToCalendar(depList.get(k+1).getDepartTime(),"GMT");
					depInterval += myTime.getInterval(arr, dep);
					System.out.println((k+1) + "interval: " + myTime.getInterval(arr, dep));
				}
				depDuration = depDuration + depInterval;
				System.out.println("total duration: " + depDuration) ;
				System.out.println("---------------");
	            depItinerary = new Itinerary(depStopNum, depDuration, depTotalPrice, depList, seatType);
	            
	            // Return Itinerary
	            System.out.println("Return Itinerary:");
	            for (int j = 0; j < retList.size(); j++) {
					retDuration += retList.get(j).getDuration();
					if (seatType.equals("coach")) {
						retTotalPrice += retList.get(j).getCoachPrice();
						System.out.println((j+1) + "leg price: " + retList.get(j).getCoachPrice() + " duration: " + retList.get(j).getDuration());
					} else if (seatType.equals("firstclass")) {
						retTotalPrice += retList.get(j).getFirstClassPrice();
					}
				}
				System.out.println("total Price: " + retTotalPrice);
				double retInterval = 0;
				for (int k = 0; k < retStopNum; k++) {
					Calendar arr = myTime.StringToCalendar(retList.get(k).getArrivalTime(),"GMT");
					Calendar dep = myTime.StringToCalendar(retList.get(k+1).getDepartTime(),"GMT");
					depInterval += myTime.getInterval(arr, dep);
					System.out.println((k+1) + "interval: " + myTime.getInterval(arr, dep));
				}
				retDuration = retDuration + retInterval;
				System.out.println("total duration: " + retDuration) ;
				System.out.println("---------------");
	            retItinerary = new Itinerary(retStopNum, retDuration, retTotalPrice, retList, seatType);
	            double totalPrice = Math.floor((depTotalPrice + retTotalPrice)*100)/100;
	            double duration = Math.floor((depDuration + retDuration)*100)/100;
	            System.out.println("roundTrip total price: " + totalPrice);
	            System.out.println("roundTrip total duration: " + duration);
	            System.out.println("-----------------");
	            rtItinerary = new RoundTripItinerary(depItinerary, retItinerary, totalPrice, duration);
	            roundTripList.add(rtItinerary);
			}
		    return roundTripList;
		}
	}
 	public static void main(String[] args) throws ParseException {
		/*ArrayList<Itinerary> ol = new ArrayList<Itinerary>();
		Itinerary itinerary = new Itinerary();
		ol = itinerary.getOneWayList("BOS", "SFO", "2016-05-12", "coach");
		for (int i = 0; i < ol.size(); i++) {
			List<Flight> flights = ol.get(i).getFlights();
			for (int j = 0; j < flights.size(); j++) {
				System.out.println(flights.get(j).getDepartCode() + " --> " + flights.get(j).getArrivalCode());
				System.out.println(flights.get(j).getDepartTime() + " --> " + flights.get(j).getArrivalTime());
			}
		}*/
 		Itinerary itinerary = new Itinerary();
 		ArrayList<RoundTripItinerary> rl = new ArrayList<RoundTripItinerary>();
 		rl = itinerary.getRoundTripList("BOS", "SFO", "2016-05-12", "coach", "2016-05-13");
 		for (int i = 0; i < rl.size(); i++) {
 			List<Flight> depList = rl.get(i).getDepItinerary().getFlights();
 			List<Flight> retList = rl.get(i).getRetItinerary().getFlights();
 			System.out.println("Depart Itinerary:");
 			for (int j = 0; j < depList.size(); j++) {
 				System.out.println(depList.get(j).getDepartCode() + " --> " + depList.get(j).getArrivalCode());
				System.out.println(depList.get(j).getDepartTime() + " --> " + depList.get(j).getArrivalTime());
 			}
 			System.out.println("Return Itinerary:");
 			for (int k = 0; k < retList.size(); k++) {
 				System.out.println(retList.get(k).getDepartCode() + " --> " + retList.get(k).getArrivalCode());
				System.out.println(retList.get(k).getDepartTime() + " --> " + retList.get(k).getArrivalTime());
 			}
 		}
	
	}
}





