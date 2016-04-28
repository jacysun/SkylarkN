package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReservationManager
 */
@WebServlet("/ReservationManager")
public class ReservationManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String trip = request.getParameter("trip");
		String seatType = request.getParameter("seatType");
		RequestDispatcher dispatcher = null;
		
		DBUpdater db = new DBUpdater();
		db.lock();
		if (trip.equals("one-way")) {
			int stop = Integer.parseInt(request.getParameter("stop"));
			for (int i = 0; i < stop + 1; i++) {
				if (db.reserveSeat(request.getParameter("number" + i), seatType) == false) {
					db.unlock();
					dispatcher = request.getRequestDispatcher("reservationError.jsp");
				    dispatcher.forward(request, response);
				}
			}
			db.unlock();
			dispatcher = request.getRequestDispatcher("reservationSuccess.jsp");
		    dispatcher.forward(request, response);
			
		} else if (trip.equals("round-trip")) {
			int dstop = Integer.parseInt(request.getParameter("dstop"));
			int rstop = Integer.parseInt(request.getParameter("rstop"));
			for (int i = 0; i < dstop + 1; i++) {
				if (db.reserveSeat(request.getParameter("dnumber" + i), seatType) == false) {
					db.unlock();
					dispatcher = request.getRequestDispatcher("reservationError.jsp");
				    dispatcher.forward(request, response);
				}
			}
			for (int j = 0; j < rstop + 1; j++) {
				if (db.reserveSeat(request.getParameter("rnumber" + j), seatType) == false) {
					db.unlock();
					dispatcher = request.getRequestDispatcher("reservationError.jsp");
				    dispatcher.forward(request, response);
				}
			}
			db.unlock();
			dispatcher = request.getRequestDispatcher("reservationSuccess.jsp");
		    dispatcher.forward(request, response);
		}
		
	}
}
