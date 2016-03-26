package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SortFlight
 * This class interacts with UI to sort itineraries and filter out itineraries by stop numbers.
 */
@WebServlet("/SortFlight")
public class SortFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sortBy = request.getParameter("sortBy");
		if (sortBy == "price ascending") {
			sortByPriceAscending(itineraryList);
		} else if (sortBy == "price descending") {
			sortByPriceDescending(itineraryList);
		} else if (sortBy == "duration ascending") {
			sortByDurationAscending(itineraryList);
		} else if (sortBy == "duration descending") {
			sortByDurationDescending(itineraryList);
		}
		request.setAttribute("sortedItinerary", sortedItinerary);
		RequestDispatcher rd = request.getRequestDispatcher("sortResult.jsp");
		rd.forward(request, response);
	}
	
	public void sortByPriceAscending(ArrayList<Itinerary> itinerary) {
		
	}

    public void sortByPriceDescending(ArrayList<Itinerary> itinerary) {
		
	}
    
    public void sortByDurationAscending(ArrayList<Itinerary> itinerary) {
	
    }
    
    public void sortByDurationDescending(ArrayList<Itinerary> itinerary) {
	
    }
}
