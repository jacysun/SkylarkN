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
import model.Itinerary.RoundTripItinerary;

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
		
		ArrayList<Itinerary> oneWayList = new ArrayList<Itinerary>();
		ArrayList<RoundTripItinerary> roundTripList = new ArrayList<RoundTripItinerary>();
		Itinerary itinerary = new Itinerary();
		if (trip.equals("one-way")) {
			try {
				oneWayList = itinerary.getOneWayList(from, to, depart, seatType);
				roundTripList = null;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else if (trip.equals("round-trip")) {
			try {
				String retur = request.getParameter("return");
				roundTripList = itinerary.getRoundTripList(from, to, depart, seatType, retur);
				oneWayList = null;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
				
		if (oneWayList == null && roundTripList == null) {
			dispatcher = request.getRequestDispatcher("inputError.jsp");
			dispatcher.forward(request, response);
		} else {
			request.getSession().setAttribute("oneWayList", oneWayList);
			request.getSession().setAttribute("roundTripList", roundTripList);
			dispatcher = request.getRequestDispatcher("searchResult.jsp");
			dispatcher.forward(request, response);
		}

	}
}

