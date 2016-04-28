package view;

import java.util.ArrayList;

import model.Itinerary;
import model.Itinerary.RoundTripItinerary;

/**
 * A class to apply filter to list of itineraries
 * 
 * @author team 6
 *
 */
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
		ArrayList<RoundTripItinerary> nonone = new ArrayList<RoundTripItinerary>();
		ArrayList<RoundTripItinerary> onetwo = new ArrayList<RoundTripItinerary>();
		ArrayList<RoundTripItinerary> nontwo = new ArrayList<RoundTripItinerary>();
		
		for (int i = 0; i < il.size(); i++) {
			int depStop = il.get(i).getDepItinerary().getStopNum();
			int retStop = il. get(i).getRetItinerary().getStopNum();
			if (depStop == 0 && retStop == 0) {
				non.add(il.get(i));
			} else if (depStop == 1 && retStop == 1) {
				one.add(il.get(i));
			} else if (depStop == 2 && retStop == 2) {
				two.add(il.get(i));
			} else if ((depStop == 0 && retStop == 1) || (depStop == 1 && retStop == 0)) {
				nonone.add(il.get(i));
			} else if ((depStop == 1 && retStop == 2) || (depStop == 2 && retStop == 1)) {
				onetwo.add(il.get(i));
			} else if ((depStop == 0 && retStop == 2) || (depStop == 2 && retStop == 0)) {
				nontwo.add(il.get(i));
			}
		}
		nonone.addAll(non);
		nonone.addAll(one);
		onetwo.addAll(one);
		onetwo.addAll(two);
		nontwo.addAll(non);
		nontwo.addAll(two);
		
		if(nonstop == true && onestop == true && twostop == true) {
			return il;
		} else { 
			if (nonstop == true && onestop == true) {
			   fil = nonone;
		    } else if (nonstop == true && twostop == true) {
			   fil = nontwo;
		    } else if (onestop == true && twostop == true) {
			   fil = onetwo;
		    } else {
		    	if (nonstop == true) {
			       fil = non;
		      } else if (onestop == true) {
			       fil = one;
		      } else if (twostop == true) {
			       fil = two;
		      }
		    }
		    return fil;	
		}
	}
}

