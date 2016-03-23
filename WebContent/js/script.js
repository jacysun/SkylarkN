/*function getRequestObject() {
  if (window.XMLHttpRequest) {
    return (new XMLHttpRequest());
  } 
  else if (window.ActiveXObject) {
    return (new ActiveXObject("Microsoft.XMLHTTP"));
  } 
  else {
    global.alert("Ajax is not supported!");
    return(null); 
  }
}  

function searchFlight() {
	var trip = document.getElementById("roundTrip");
	  if (trip.checked) {
	    searchRoundTrip();
	  } else {
	    searchOneWay();
	  }
}

function searchOneWay() {
     //var xmlhttp = getRequestObject();
      //xml.onreadystatechange=function() {
    	//  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
    	//	  var text = xmlhttp.responseText;
    		//  document.getElementById("result").innerHTML = text;
    	  //}
      //}
      var dep = document.getElementById("from").value;
	  var arr = document.getElementById("to").value;
	  var depDate = document.getElementById("depart").value;
	  var seat = document.getElementById("seat").value;
	  document.getElementById("result").innerHTML = "You searched for one-way trip from " + dep + " to " + arr + " on " + depDate + " with " + seat;
      //xmlhttp.open("GET", "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=team06" + "&action=list&list_type=airplanes", true);
      //xmlhttp.send(null);
}

function searchRoundTrip() {
	//var xmlhttp = getRequestObject();
    //xml.onreadystatechange=function() {
  	 // if (xmlhttp.readyState==4 && xmlhttp.status==200) {
  		//  var text = xmlhttp.responseText;
  		  //document.getElementById("result").innerHTML = text;
  	  //}
    //}
    var dep = document.getElementById("from").value;
	var arr = document.getElementById("to").value;
    var depDate = document.getElementById("depart").value;
    var retDate = document.getElementById("return").value;
	var seat = document.getElementById("seat").value;
	//document.getElementById("result").innerHTML = "You searched for round-trip trip from " + dep + " to " + arr + " departing on " + depDate + " and arriving on " + retDate + " with " + seat;
    xmlhttp.open("GET", "searchOneWay.jsp?departure=dep&arrival=arr&depatureDate=depDate&returnDate=retDate&seatType=seat", true);
    xmlhttp.send(null);
}*/

function check() {

}

function checkReturn() {
  if (document.getElementById('roundTrip').checked) {
  	document.getElementById('return').setAttribute('required', true);
  }
  else {
  	document.getElementById('return').removeAttribute('required');
  }
}

