package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Itinerary;

/**
 * Servlet implementation class RequestFlight 
 * This class is used to interact with UI and return itinerary result.
 */
@WebServlet("/RequestFlight")
public class RequestFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = null;
		
		String trip = request.getParameter("trip");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String depart = request.getParameter("depart");
		String seatType = request.getParameter("seat type");
		
		request.getSession().setAttribute("seatType", seatType);
		
		ArrayList<Itinerary> itineraryList = new ArrayList<Itinerary>();
		Itinerary itinerary = new Itinerary();
		if (trip.equals("one-way")) {
			try {
				itineraryList = itinerary.getItineraryList(from, to, depart, true, seatType);
				if (itineraryList == null) {
					dispatcher = request.getRequestDispatcher("inputError.jsp");
					dispatcher.forward(request, response);
				} else {
					request.getSession().setAttribute("itineraryList", itineraryList);
					dispatcher = request.getRequestDispatcher("searchOneWay.jsp");
					dispatcher.forward(request, response);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			try {
				itineraryList = itinerary.getItineraryList(from, to, depart, false, seatType);
				if (itineraryList == null) {
					dispatcher = request.getRequestDispatcher("inputError.jsp");
					dispatcher.forward(request, response);
				} else {
					request.getSession().setAttribute("itineraryList", itineraryList);
					dispatcher = request.getRequestDispatcher("searchRoundTrip.jsp");
					dispatcher.forward(request, response);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		/*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar depDate = Calendar.getInstance();
		try {
			depDate.setTime(formatter.parse(depart));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Airport departure = new Airport();
		departure = departure.getAirport(from);
		Airport arrival = new Airport();
		arrival = arrival.getAirport(to);
		if (departure == null || arrival == null) {
			dispatcher = request.getRequestDispatcher("inputError.jsp");
			dispatcher.forward(request, response);
		}
		
		ItineraryBuilder ib = new ItineraryBuilder();
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		if (trip.equals("one-way")) {
			scheduleList = ib.oneWayTrip(departure, arrival, depDate, 2);
		//} else {
		//	scheduleList = ib.roundWayTrip(departure, arrival, depDate, 2);
		}
		
		request.getSession().setAttribute("scheduleList", scheduleList);
		dispatcher = request.getRequestDispatcher("searchResult.jsp");
		dispatcher.forward(request, response);*/
	}
}

