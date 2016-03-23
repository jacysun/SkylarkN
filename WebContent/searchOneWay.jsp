<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.FlightParser" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import= "org.w3c.dom.*" %>
<%@ page import="model.Flight" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Skylark</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet" type="text/css" media="screen">
<link href='https://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>

</head>
<body>
<header>
    <nav id="header-nav" class="navbar navbar-default" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div class="navbar-brand">
            <a href="main.html"><h1>Skylark</h1></a>
          </div>

          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#collapsable-nav" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>

        <div id="collapsable-nav" class="collapse navbar-collapse">
          <ul id="nav-list" class="nav navbar-nav navbar-right">
            <li><a href="index.html">Home</a></li>
            <li><a href="href/about.html">About Skylark</a></li>
            <li><a href="href/policy.html">Policy</a></li>
            <li><a href="href/contact.html">Contact Us</a></li>
          </ul>
        </div>
      </div>
    </nav>
</header>

<div id="main-content" class="container">
<%
String departure = (String) request.getAttribute("departure");
String arrival = (String) request.getAttribute("arrival");
String depDate = (String) request.getAttribute("depDate");
String seattype = (String) request.getAttribute("seat");
//out.println("You searched for one-way flight from " + departure + " to " + arrival + " on " + depDate + " with " + seattype);
		
        //String departDate = "20" + depDate.substring(6,8) + "_" + depDate.substring(0,2) + "_" + depDate.substring(3,5); // IDE test
        String departDate = depDate.substring(0,4) + "_" + depDate.substring(5,7) + "_" + depDate.substring(8,10);
		FlightParser fp = new FlightParser();
		ArrayList<Flight> flightList = new ArrayList<Flight>();
		fp.start(departure, departDate);
		for (int i = 0; i < fp.flightList.size(); i++) {
			
 	       %><p><%out.println("Airplane: " + fp.flightList.get(i).getAirplane());%></p>
 	         <p><%out.println("Flight Number: " + fp.flightList.get(i).getNumber());%></p>
 	         <p><%out.println("Duration: " + fp.flightList.get(i).getFlightTime());%></p>
 	         <p><%out.println("Departure: " + fp.flightList.get(i).getDepartCode() + " - " + fp.flightList.get(i).getDepartTime());%></p>
 	         <p><%out.println("Arrival: " + fp.flightList.get(i).getArrivalCode() + " - " + fp.flightList.get(i).getArrivalTime());%></p>
 	         <p><%out.println("Coach Seats: " + fp.flightList.get(i).getCoachSeats() + " - " + fp.flightList.get(i).getCoachPrice());%></p>
 	         <p><%out.println("First Class Seats: " + fp.flightList.get(i).getFirstClassSeats() + " - " + fp.flightList.get(i).getFirstClassPrice());%></p>
 	         <p><%out.println("--------------------------------------");%></p>
      <%}
%>
</div>

<footer class="panel-footer">
    <div class="container">
      <div class="row">
        <section id="hours" class="col-sm-4">
          <span>Contact Hours:</span><br>
          Monday-Friday: 8am - 6pm<br>
          Saturday-Sunday: 9am - 5pm<br>
          <hr class="visible-xs">
        </section>
        <section id="address" class="col-sm-4">
          <span>Address:</span><br>
          55 North Lake Ave<br>
          Worcester, MA 01605
          <p>* </p>
          <hr class="visible-xs">
        </section>
        <section id="other" class="col-sm-4">
          <p>"xxx"</p>
          <p>""</p>
        </section>
      </div>
      <div class="text-center">&copy; 2016 All Rights Reserved. Skylark is a registered trademark of WPI, Inc.</div>
    </div>
  </footer>

  <script src="js/jquery-2.1.4.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/script.js"></script>
</body>
</html>