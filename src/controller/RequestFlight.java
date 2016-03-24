package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestFlight 
 * This class gets search parameters from UI and redirect to searchOneWay.jsp or searchRoundTrip.jsp to display the itinerary results.
 */
@WebServlet("/RequestFlight")
public class RequestFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String trip = request.getParameter("trip");
		String departure = request.getParameter("from");
		String arrival = request.getParameter("to");
		String depDate = request.getParameter("depart");
		String retDate = request.getParameter("return");
		String seat = request.getParameter("seat type");	
      
        request.setAttribute("departure", departure);
        request.setAttribute("arrival", arrival);
        request.setAttribute("depDate", depDate);
        request.setAttribute("retDate", retDate);
        request.setAttribute("seat", seat);
		
		if (trip.equals("one-way")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("searchOneWay.jsp");
			dispatcher.forward(request, response);
			return;
		} else if (trip.equals("round-trip")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("searchRoundTrip.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}
}
