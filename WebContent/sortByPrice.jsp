<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Itinerary" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
ArrayList<Itinerary> il = (ArrayList<Itinerary>) session.getAttribute("itineraryList");
ItinerarySorter sorter = new ItinerarySorter();
ArrayList<Itinerary> ilp = new ArrayList<Itinerary>();
ilp = sorter.sortByPrice(il);

String seatType = (String) session.getAttribute("seatType");
%>

<div id="result" class="container">
<div class="row">
  <div class="col-xs-12 col-sm-2">
   <p>Stops</p>
      <form id="stop" name="stop_form" method="post" action="filterResult.jsp">
        <input type="checkbox" name="nonstop" value="nonstop" checked> nonstop<br>
        <input type="checkbox" name="onestop" value="onestop" checked> 1 stop<br>
        <input type="checkbox" name="twostops" value="twostops" checked> 2 stops<br>
      </form>
   </div> 
   
   <div id="itinerary" class="col-xs-12 col-sm-10">
      <table width="100%" border=1 cellpadding=3 cellspacing=1 rules=rows frame=box>
      <tr>
       <th>Sort By: <span><a href="sortByPrice.jsp" style="position:relative; left: 25px;">Price</a></span><span><a href="sortByDuration.jsp" style="position:relative; left: 50px;">Duration</a></span></th>
      </tr>
         <% for (int i = 0; i < ilp.size(); i++) { %>
        	   <tr style="padding: 5px;"><td style="padding: 5px;">
        	     <table width="100%" id="price" style="display:inline-block; float:left; padding: 5px;">
                  <tr><td width="100%" align="left" valign="middle"><span>Price:$<%=ilp.get(i).getTotalPrice() %></span></td></tr>
                  <tr><td width="100%" align="left" valign="middle"><span>Stop:<%=ilp.get(i).getStopNum() %></span></td></tr>
                  <tr><td width="100%" align="left" valign="middle"><span>Duration:<%=ilp.get(i).getDuration() %>hours</span></td></tr>
                </table></td>
                <td style="padding: 5px;">
        	    <table width="100%" id="flight" style="display: inline-block; padding: 5px;">
        	    <% for (int j = 0; j < il.get(i).getFlights().size(); j++) { %>
                  <tr><td width="50%" align="right" valign="middle"><span>Airplane:<%=ilp.get(i).getFlights().get(j).getAirplane() %></span></td>
                      <td width="50%" align="left" valign="middle"><span>Flight No:<%=ilp.get(i).getFlights().get(j).getNumber() %></span></td></tr>
                  <tr><td width="50%" align="right" valign="middle"><span><%=ilp.get(i).getFlights().get(j).getDepartTime() %><%=ilp.get(i).getFlights().get(j).getDepartCode() %></span></td>
                      <td width="50%" align="left" valign="middle"><span>---><%=ilp.get(i).getFlights().get(j).getArrivalTime() %><%=ilp.get(i).getFlights().get(j).getArrivalCode() %></span></td></tr>
                <% } %>
                </table></td></tr>
        <% } %> 
      </table>
  </div>
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
  <script src="js/bootstrap.min.js"></script>
  <script src="js/script.js"></script>
  
</body>
</html>