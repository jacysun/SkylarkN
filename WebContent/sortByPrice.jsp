<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Itinerary" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="controller.MyTime" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="model.Itinerary.RoundTripItinerary" %>
<%@ page import="view.ItinerarySorter" %>
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

<%
ArrayList<Itinerary> ol = (ArrayList<Itinerary>) session.getAttribute("oneWayList");
ArrayList<RoundTripItinerary> rl = (ArrayList<RoundTripItinerary>) session.getAttribute("roundTripList");
String seatType = (String) session.getAttribute("seatType");
MyTime myTime = new MyTime();
ItinerarySorter sorter = new ItinerarySorter();
ArrayList<Itinerary> olp = new ArrayList<Itinerary>();
ArrayList<RoundTripItinerary> rlp = new ArrayList<RoundTripItinerary>();
if (ol != null) {
	olp = sorter.sortByPrice(ol);
} else if (rl != null) {
	rlp = sorter.sortByprice(rl);
}

%>

<div id="result" class="container">
<div class="row">
  <div class="col-xs-12 col-sm-2">
   <p>Stops</p>
      <form id="stop" name="stop_form" method="post" action="filterResult.jsp">
        <input type="checkbox" name="nonstop" value="nonstop" checked> nonstop<br>
        <input type="checkbox" name="onestop" value="onestop" checked> 1 stop<br>
        <input type="checkbox" name="twostops" value="twostops" checked> 2 stops<br>
        <input type="submit" value="Update" style="position:relative; left:50px; top:30px;">
      </form>
   </div> 
   <%if(ol != null) { %>
   <div class="col-xs-12 col-sm-10">
     <div id="sort">
       <p>Sort By: <span><a href="sortByPrice.jsp" style="position:relative; left: 25px;">Price</a></span><span><a href="sortByDuration.jsp" style="position:relative; left: 50px;">Duration</a></span></p>
     </div>
     <div class="itinerary-list">
         <% for (int i = 0; i < olp.size(); i++) { %>
        	   <div class="itinerary" style="border: 1px solid #a0a0a0; padding-top:20px; padding-bottom:20px;">
        	     <div class="row">
        	       <div class="col-xs-12 col-sm-4">
        	     <table width="100%" id="price" style="display:inline-block; float:left; padding: 5px;">
                  <tr><td width="100%" align="left" valign="middle"><span>Price: $<%=olp.get(i).getTotalPrice() %></span></td></tr>
                  <tr><td width="100%" align="left" valign="middle"><span>Stop: <%=olp.get(i).getStopNum() %></span></td></tr>
                  <tr><td width="100%" align="left" valign="middle"><span>Duration: <%=olp.get(i).getDuration() %>hours</span></td></tr>
                  <tr><td width="100%" align="left" valign="middle"><span>Seat Type: <%=seatType %></span></td></tr>
                </table>
                </div>
                <div class="col-xs-12 col-sm-8">
        	    <table width="100%" id="flight" style="display: inline-block; padding: 5px;">
        	    <% for (int j = 0; j < olp.get(i).getFlights().size(); j++) { %>
                  <tr><td width="50%" align="right" valign="middle"><span><%=olp.get(i).getFlights().get(j).getDepLocal() %> <%=olp.get(i).getFlights().get(j).getDepartCode() %> </span></td>
                      <td width="50%" align="left" valign="middle"><span> ---> <%=olp.get(i).getFlights().get(j).getArrLocal() %> <%=olp.get(i).getFlights().get(j).getArrivalCode() %></span></td></tr>
                 <% } %>
                </table>
                </div>
              </div>
              <details>
                  <summary>Itinerary Details</summary>
                  <p>Depart --- WWW, MMM dd (placeholder)</p><hr>
                  <% for (int j = 0; j < olp.get(i).getFlights().size(); j++) { %>
                  <p><%=olp.get(i).getFlights().get(j).getDepLocal() %> --- <%=olp.get(i).getFlights().get(j).getArrLocal() %></p>
                  <p><%=olp.get(i).getFlights().get(j).getDepartCode() %> --- <%=olp.get(i).getFlights().get(j).getArrivalCode() %> (<%=olp.get(i).getFlights().get(j).getDuration() %> hr)</p>
                  <p>Airplane:<%=olp.get(i).getFlights().get(j).getAirplane() %><span style="position:relative; left: 50px;">Flight No:<%=olp.get(i).getFlights().get(j).getNumber() %></span></p>
                  <% double interval = 0;
                    if (j< olp.get(i).getFlights().size() -1) {
                    Calendar arr = myTime.StringToCalendar(olp.get(i).getFlights().get(j).getArrivalTime(),"GMT");
					Calendar dep = myTime.StringToCalendar(olp.get(i).getFlights().get(j+1).getDepartTime(),"GMT");
					interval = myTime.getInterval(arr, dep); 
					interval = Math.floor(interval*100)/100;%>
                  <hr><p>Change Plane <%=interval %> hr</p><hr> <%} %>
                 <%} %>
                 <form id="reservation" name="reservation" method="post" action="reservation.jsp">
                  <input type="hidden">
                  <input type="submit" value="Select" style="position:relative; left: 500px;">
                  </form>
                </details>
               
              </div>
        <% } %> 
  </div>
   <div class="pagination">
 </div>
 </div>
 <%} else if (rl != null) {%>
 <div class="col-xs-12 col-sm-10">
     <div id="sort">
       <p>Sort By: <span><a href="sortByPrice.jsp" style="position:relative; left: 25px;">Price</a></span><span><a href="sortByDuration.jsp" style="position:relative; left: 50px;">Duration</a></span></p>
     </div>
     <div class="itinerary-list">
         <% for (int i = 0; i < rlp.size(); i++) { %>
        	   <div class="itinerary" style="border: 1px solid #a0a0a0; padding-top:20px; padding-bottom:20px;">
        	     <div class="row">
        	       <div class="col-xs-12 col-sm-4">
        	     <table width="100%" id="price" style="display:inline-block; float:left; padding: 5px;">
                  <tr><td width="100%" align="left" valign="middle"><span>Price: $<%=rlp.get(i).getTotalPrice() %></span></td></tr>
                  <tr><td width="100%" align="left" valign="middle"><span>Duration: <%=rlp.get(i).getDuration() %>hours</span></td></tr>
                  <tr><td width="100%" align="left" valign="middle"><span>Seat Type: <%=seatType %></span></td></tr>
                </table>
                </div>
                <div class="col-xs-12 col-sm-8">
        	    <table width="100%" id="flight" style="display: inline-block; padding: 5px;">
        	    <% int depSize = rlp.get(i).getDepItinerary().getFlights().size();
        	       int retSize = rlp.get(i).getRetItinerary().getFlights().size(); %>
                  <tr><td width="50%" align="right" valign="middle"><span><%=rlp.get(i).getDepItinerary().getFlights().get(0).getDepLocal() %> <%=rlp.get(i).getDepItinerary().getFlights().get(0).getDepartCode() %> </span></td>
                      <td width="50%" align="left" valign="middle"><span> ---> <%=rlp.get(i).getDepItinerary().getFlights().get(depSize-1).getArrLocal() %> <%=rlp.get(i).getDepItinerary().getFlights().get(depSize-1).getArrivalCode() %></span></td></tr>
                  <tr><td width="50%" align="right" valign="middle"><span><%=rlp.get(i).getRetItinerary().getFlights().get(0).getDepLocal() %> <%=rlp.get(i).getRetItinerary().getFlights().get(0).getDepartCode() %> </span></td>
                      <td width="50%" align="left" valign="middle"><span> ---> <%=rlp.get(i).getRetItinerary().getFlights().get(retSize-1).getArrLocal() %> <%=rlp.get(i).getRetItinerary().getFlights().get(retSize-1).getArrivalCode() %></span></td></tr>
                </table>
                </div>
              </div>
              <details>
                  <summary>Itinerary Details</summary>
                  <p>Depart --- WWW, MMM dd (placeholder)</p><hr>
                  <% for (int j = 0; j < rlp.get(i).getDepItinerary().getFlights().size(); j++) { %>
                  <p><%=rlp.get(i).getDepItinerary().getFlights().get(j).getDepLocal() %> --- <%=rlp.get(i).getDepItinerary().getFlights().get(j).getArrLocal() %></p>
                  <p><%=rlp.get(i).getDepItinerary().getFlights().get(j).getDepartCode() %> --- <%=rlp.get(i).getDepItinerary().getFlights().get(j).getArrivalCode() %> (<%=rlp.get(i).getDepItinerary().getFlights().get(j).getDuration() %> hr)</p>
                  <p>Airplane:<%=rlp.get(i).getDepItinerary().getFlights().get(j).getAirplane() %><span style="position:relative; left: 50px;">Flight No:<%=rlp.get(i).getDepItinerary().getFlights().get(j).getNumber() %></span></p>
                  <% double interval = 0;
                    if (j< rlp.get(i).getDepItinerary().getFlights().size() -1) {
                    Calendar arr = myTime.StringToCalendar(rlp.get(i).getDepItinerary().getFlights().get(j).getArrivalTime(),"GMT");
					Calendar dep = myTime.StringToCalendar(rlp.get(i).getDepItinerary().getFlights().get(j+1).getDepartTime(),"GMT");
					interval = myTime.getInterval(arr, dep); 
					interval = Math.floor(interval*100)/100;%>
                  <hr><p>Change Plane <%=interval %> hr</p><hr> <%} %>
                 <%} %>
                 <hr><p>Return --- WWW, MMM dd (placeholder)</p><hr>
                  <% for (int j = 0; j < rlp.get(i).getRetItinerary().getFlights().size(); j++) { %>
                  <p><%=rlp.get(i).getRetItinerary().getFlights().get(j).getDepLocal() %> --- <%=rlp.get(i).getRetItinerary().getFlights().get(j).getArrLocal() %></p>
                  <p><%=rlp.get(i).getRetItinerary().getFlights().get(j).getDepartCode() %> --- <%=rlp.get(i).getRetItinerary().getFlights().get(j).getArrivalCode() %> (<%=rlp.get(i).getRetItinerary().getFlights().get(j).getDuration() %> hr)</p>
                  <p>Airplane:<%=rlp.get(i).getRetItinerary().getFlights().get(j).getAirplane() %><span style="position:relative; left: 50px;">Flight No:<%=rlp.get(i).getRetItinerary().getFlights().get(j).getNumber() %></span></p>
                  <% double interval = 0;
                    if (j< rlp.get(i).getRetItinerary().getFlights().size() -1) {
                    Calendar arr = myTime.StringToCalendar(rlp.get(i).getRetItinerary().getFlights().get(j).getArrivalTime(),"GMT");
					Calendar dep = myTime.StringToCalendar(rlp.get(i).getRetItinerary().getFlights().get(j+1).getDepartTime(),"GMT");
					interval = myTime.getInterval(arr, dep); 
					interval = Math.floor(interval*100)/100;%>
                  <hr><p>Change Plane <%=interval %> hr</p><hr> <%} %>
                 <%} %>
                 <form id="reservation" name="reservation" method="post" action="reservation.jsp">
                  <input type="hidden">
                  <input type="submit" value="Select" style="position:relative; left: 500px;">
                  </form>
                </details>
               
              </div>
        <% } %> 
  </div>
   <div class="pagination">
 </div>
 </div>
<%} %>
 </div>
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