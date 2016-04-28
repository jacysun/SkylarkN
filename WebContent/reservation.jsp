<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Itinerary" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="controller.MyTime" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="model.Itinerary.RoundTripItinerary" %>
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
<link href="css/pagination.css" rel="stylesheet" type="text/css" >
<link rel="stylesheet" href="css/jquery-ui.min.css">
<link href='https://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>

</head>
<body>
<header>
    <nav id="header-nav" class="navbar navbar-default" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div class="navbar-brand">
            <a href="index.html"><h1>Skylark</h1></a>
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

<%
String depDate = (String) session.getAttribute("depDate");
String retDate = (String) session.getAttribute("retDate");
ArrayList<Itinerary> fol = (ArrayList<Itinerary>) session.getAttribute("oneWayList");
ArrayList<RoundTripItinerary> frl = (ArrayList<RoundTripItinerary>) session.getAttribute("roundTripList");
String seatType = (String) session.getAttribute("seatType");
String trip = request.getParameter("trip");
%>

<div id="result" class="container">
<%
if (trip.equals("one-way")) {
int stop = Integer.parseInt(request.getParameter("stop"));
String[] numbers = new String[3];
%>
<div class="row">
  <div class="col-xs-12 col-sm-9">
    <p>Depart --- <%=depDate %> (<%=stop %> stop)</p><hr>
    <%for(int i = 0; i < stop + 1; i++) { 
    numbers[i] = request.getParameter("number" + i); %>
      <p><%=request.getParameter("depLocal" + i) %> --- <%=request.getParameter("arrLocal" + i) %></p>
      <p><%=request.getParameter("depCode" + i) %> --- <%=request.getParameter("arrCode" + i) %> (<%=request.getParameter("duration" + i) %> hr)</p>
      <p>Airplane : <%=request.getParameter("airplane" + i) %><span style="position:relative; left: 50px;">Flight Number : <%= numbers[i] %></span></p>
      <%if(seatType.equals("Coach")){ %>
      <p>CoachSeatReserved : <%=request.getParameter("cseat" + i) %></p>
      <%} else if (seatType.equals("FirstClass")) { %>
      <p>FirstClassSeatReserved : <%=request.getParameter("fcseat" + i) %></p>
      <%} if (i < stop) { %>
        <hr><p><%=request.getParameter("interval" + i) %></p><hr>
    <%}} %>
  </div>
  <div class="col-xs-12 col-sm-3">
    <p><%=request.getParameter("price") %></p>
    <p><%=request.getParameter("duration") %></p>
    <form id="reserve" name="reserve" method="post" action="ReservationManager">
      <input type="hidden" name="seatType" value="<%=seatType %>">
      <input type="hidden" name="trip" value="one-way">
      <input type="hidden" name="stop" value="<%=stop %>">
      <%for(int j = 0; j < stop + 1; j++) {%>
      <input type="hidden" name="number<%=j%>" value="<%=numbers[j]%>">
      <%} %>
      <input type="submit" value="Make Reservation" style="position:relative; top:50px;">
    </form>
 </div>
</div>
<%} else if (trip.equals("round-trip")) { 
int dstop = Integer.parseInt(request.getParameter("dstop"));
int rstop = Integer.parseInt(request.getParameter("rstop"));
String[] dnumbers = new String[3];
String[] rnumbers = new String[3];
%>
<div class="row">
  <div class="col-xs-12 col-sm-9">
  <p>Depart --- <%=depDate %> (<%=dstop %> stop)</p><hr>
    <%for(int i = 0; i < dstop + 1; i++) { 
      dnumbers[i] = request.getParameter("dnumber" + i); %>
      <p><%=request.getParameter("ddepLocal" + i) %> --- <%=request.getParameter("darrLocal" + i) %></p>
      <p><%=request.getParameter("ddepCode" + i) %> --- <%=request.getParameter("darrCode" + i) %> (<%=request.getParameter("dduration" + i) %> hr)</p>
      <p>Airplane: <%=request.getParameter("dairplane" + i) %><span style="position:relative; left: 50px;">Flight Number : <%=dnumbers[i] %></span></p>
      <%if(seatType.equals("Coach")){ %>
      <p>CoachSeatReserved : <%=request.getParameter("dcseat" + i) %></p>
      <%} else if (seatType.equals("FirstClass")) { %>
      <p>FirstClassSeatReserved : <%=request.getParameter("dfcseat" + i) %></p>
      <%} if (i < dstop) { %>
        <hr><p><%=request.getParameter("dinterval" + i) %></p><hr>
    <%}} %>
  <hr><p>Return --- <%=retDate %> (<%=rstop %> stop)</p><hr>
    <%for(int i = 0; i < rstop + 1; i++) { 
      rnumbers[i] = request.getParameter("rnumber" + i); %>
      <p><%=request.getParameter("rdepLocal" + i) %> --- <%=request.getParameter("rarrLocal" + i) %></p>
      <p><%=request.getParameter("rdepCode" + i) %> --- <%=request.getParameter("rarrCode" + i) %> (<%=request.getParameter("rduration" + i) %> hr)</p>
      <p>Airplane : <%=request.getParameter("rairplane" + i) %><span style="position:relative; left: 50px;">Flight Number : <%=rnumbers[i] %></span></p>
      <%if(seatType.equals("Coach")){ %>
      <p>CoachSeatReserved : <%=request.getParameter("rcseat" + i) %></p>
      <%} else if (seatType.equals("FirstClass")) { %>
      <p>FirstClassSeatReserved : <%=request.getParameter("rfcseat" + i) %></p>
      <%} if (i < dstop) { %>
        <hr><p><%=request.getParameter("rinterval" + i) %></p><hr>
    <%}} %>
    </div>
    <div class="col-xs-12 col-sm-3">
    <p><%=request.getParameter("price") %></p>
    <p><%=request.getParameter("duration") %></p>
    <form id="reserve" name="reserve" method="post" action="ReservationManager">
      <input type="hidden" name="seatType" value="<%=seatType %>">
      <input type="hidden" name="trip" value="round-trip">
      <input type="hidden" name="dstop" value="<%=dstop %>">
      <input type="hidden" name="rstop" value="<%=rstop %>">
      <%for(int j = 0; j < dstop + 1; j++) {%>
      <input type="hidden" name="dnumber<%=j%>" value="<%=dnumbers[j]%>">
      <%}
      for(int j = 0; j < rstop + 1; j++) {%>
      <input type="hidden" name="rnumber<%=j%>" value="<%=rnumbers[j]%>">
      <%}%>
      <input type="submit" value="Make Reservation" style="position:relative; top:50px;">
    </form>
 </div>
</div>
<%} %>
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
  <script src="js/jquery-ui.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/script.js"></script>
  <script src="js/pagination.js"></script>
  
</body>
</html>