package view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import model.Itinerary;
import model.Itinerary.RoundTripItinerary;

public class ItinerarySorter {
	
	public ArrayList<Itinerary> sortByPrice(ArrayList<Itinerary> il) {
		int size = il.size();
		for (int i = (il.size()-1)/2; i >= 0; i--) {
			maxPrice(il, size, i);
		}
		for (int i = il.size() - 1; i >= 1; i--) {
			Collections.swap(il, i, 0);
			size = size - 1;
			maxPrice(il, size, 0);
		}
		return il;
	}
	
	public ArrayList<Itinerary> sortByDuration(ArrayList<Itinerary> il) {
		int size = il.size();
		for (int i = (il.size()-1)/2; i >= 0; i--) {
			maxDuration(il, size, i);
		}
		for (int i = il.size()-1; i >= 1; i--) {
			Collections.swap(il, i, 0);
			size = size - 1;
			maxDuration(il, size, 0);
		}
		return il;
	}
	
	public void maxPrice(ArrayList<Itinerary> il, int size, int i) {
		int largest = 0;
		int left = 2*i + 1;
		int right = 2*i + 2;
		if (left < size && Double.compare(il.get(left).getTotalPrice(), il.get(i).getTotalPrice()) > 0) {
			largest = left;
		} else {
			largest = i;
		}
		if (right < size && Double.compare(il.get(right).getTotalPrice(), il.get(largest).getTotalPrice()) > 0) {
			largest = right;
		}
		if (largest != i) {
			Collections.swap(il, i, largest);
			maxPrice(il, size, largest);
		}
	}
	
	public void maxDuration(ArrayList<Itinerary> il, int size, int i) {
		int largest = 0;
		int left = 2*i + 1;
		int right = 2*i + 2;
		if (left < size && Double.compare(il.get(left).getDuration(), il.get(i).getDuration()) > 0) {
			largest = left;
		} else {
			largest = i;
		}
		if (right < size && Double.compare(il.get(right).getDuration(), il.get(largest).getDuration()) > 0) {
			largest = right;
		}
		if (largest != i) {
			Collections.swap(il, i, largest);
			maxDuration(il, size, largest);
		}
	}
	
	// Sort RoundTripList
	public ArrayList<RoundTripItinerary> sortByprice(ArrayList<RoundTripItinerary> il) {
		int size = il.size();
		for (int i = (il.size()-1)/2; i >= 0; i--) {
			maxprice(il, size, i);
		}
		for (int i = il.size() - 1; i >= 1; i--) {
			Collections.swap(il, i, 0);
			size = size - 1;
			maxprice(il, size, 0);
		}
		return il;
	}
	
	public ArrayList<RoundTripItinerary> sortByduration(ArrayList<RoundTripItinerary> il) {
		int size = il.size();
		for (int i = (il.size()-1)/2; i >= 0; i--) {
			maxduration(il, size, i);
		}
		for (int i = il.size()-1; i >= 1; i--) {
			Collections.swap(il, i, 0);
			size = size - 1;
			maxduration(il, size, 0);
		}
		return il;
	}
	
	public void maxprice(ArrayList<RoundTripItinerary> il, int size, int i) {
		int largest = 0;
		int left = 2*i + 1;
		int right = 2*i + 2;
		if (left < size && Double.compare(il.get(left).getTotalPrice(), il.get(i).getTotalPrice()) > 0) {
			largest = left;
		} else {
			largest = i;
		}
		if (right < size && Double.compare(il.get(right).getTotalPrice(), il.get(largest).getTotalPrice()) > 0) {
			largest = right;
		}
		if (largest != i) {
			Collections.swap(il, i, largest);
			maxprice(il, size, largest);
		}
	}
	
	public void maxduration(ArrayList<RoundTripItinerary> il, int size, int i) {
		int largest = 0;
		int left = 2*i + 1;
		int right = 2*i + 2;
		if (left < size && Double.compare(il.get(left).getDuration(), il.get(i).getDuration()) > 0) {
			largest = left;
		} else {
			largest = i;
		}
		if (right < size && Double.compare(il.get(right).getDuration(), il.get(largest).getDuration()) > 0) {
			largest = right;
		}
		if (largest != i) {
			Collections.swap(il, i, largest);
			maxduration(il, size, largest);
		}
	}
	
	/*public static void main(String[] args) throws ParseException {
		ArrayList<Itinerary> iList = new ArrayList<Itinerary>();
		Itinerary itinerary = new Itinerary();
		iList = itinerary.getItineraryList("BOS", "SFO", "2016-05-12", true, "coach");
		for (int i = 0; i < iList.size(); i++) {
			//System.out.println("Price: " + iList.get(i).getTotalPrice());
			System.out.println("Duration: " + iList.get(i).getDuration());
		}
		System.out.println("--------------------------");
		System.out.println("After SortByPrice");
		//sortByPrice(iList);
		sortByDuration(iList);
		
		for (int i = 0; i < iList.size(); i++) {
			//System.out.println("Price: " + iList.get(i).getTotalPrice());
			System.out.println("Duration: " + iList.get(i).getDuration());
		}
		
	}*/
}
