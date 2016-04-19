package view;

import java.util.ArrayList;

import model.Itinerary;
import model.Itinerary.RoundTripItinerary;

public class ItineraryFilter {
	
	public ArrayList<Itinerary> filterOneWay(ArrayList<Itinerary> il, boolean nonstop, boolean onestop, boolean twostop) {
		ArrayList<Itinerary> fil = new ArrayList<Itinerary>();
		ArrayList<Itinerary> non = new ArrayList<Itinerary>();
		ArrayList<Itinerary> one = new ArrayList<Itinerary>();
		ArrayList<Itinerary> two = new ArrayList<Itinerary>();
		for (int i = 0; i < il.size(); i++) {
			if (il.get(i).getStopNum() == 0) {
				non.add(il.get(i));
			} else if (il.get(i).getStopNum() == 1) {
				one.add(il.get(i));
			} else if (il.get(i).getStopNum() == 2) {
				two.add(il.get(i));
			}
		}
		if (nonstop) {
			fil.addAll(non);
		}
		if (onestop) {
			fil.addAll(one);
		}
		if (twostop) {
			fil.addAll(two);
		}
		return fil;	
	}
	
	public ArrayList<RoundTripItinerary> filterRoundTrip(ArrayList<RoundTripItinerary> il, boolean nonstop, boolean onestop, boolean twostop) {
		ArrayList<RoundTripItinerary> fil = new ArrayList<RoundTripItinerary>();
		ArrayList<RoundTripItinerary> non = new ArrayList<RoundTripItinerary>();
		ArrayList<RoundTripItinerary> one = new ArrayList<RoundTripItinerary>();
		ArrayList<RoundTripItinerary> two = new ArrayList<RoundTripItinerary>();
		for (int i = 0; i < il.size(); i++) {
			if (il.get(i).getDepItinerary().getStopNum() == 0 || il.get(i).getRetItinerary().getStopNum() == 0) {
				non.add(il.get(i));
			} else if (il.get(i).getDepItinerary().getStopNum() == 1 || il.get(i).getRetItinerary().getStopNum() == 1) {
				one.add(il.get(i));
			} else if (il.get(i).getDepItinerary().getStopNum() == 2 || il.get(i).getRetItinerary().getStopNum() == 2) {
				two.add(il.get(i));
			}
		}
		if (nonstop) {
			fil.addAll(non);
		}
		if (onestop) {
			fil.addAll(one);
		}
		if (twostop) {
			fil.addAll(two);
		}
		return fil;	
	}
}

