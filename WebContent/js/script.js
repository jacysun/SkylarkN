$(function() {
var airportList = [ "ATL",
                    "ANC",
                    "BWI",
                    "BOS",
                    "CLT",
                    "MDW",
                    "ORD",
                    "CVG",
                    "CLE",
                    "CMH",
                    "DFW",
                    "DEN",
                    "DTW",
                    "FLL",
                    "RSW",
                    "BDL",
                    "HNL",
                    "IAH",
                    "HOU",
                    "IND",
                    "MCI",
              	  "LAS",
              	  "LAX",
              	  "MEM",
              	  "MIA",
              	  "MSP",
              	  "BNA",
              	  "MSY",
              	  "JFK",
              	  "LGA",
              	  "EWR",
              	  "OAK",
              	  "ONT",
              	  "MCO",
              	  "PHL",
              	  "PHX",
              	  "PIT",
              	  "PDX",
              	  "RDU",
              	  "SMF",
              	  "SLC",
              	  "SAT",
              	  "SAN",
              	  "SFO",
              	  "SJC",
              	  "SNA",
              	  "SEA",
              	  "STL",
              	  "TPA",
              	  "IAD",
              	  "DCA"];
		
		    $( "#from" ).autocomplete({
				source: airportList
		    });
			
		    $( "#to" ).autocomplete({
				source: airportList
		    });
			
		  });
		

function checkReturn() {
  if (document.getElementById('roundTrip').checked) {
  	document.getElementById('return').setAttribute('required', true);
  }
  else {
  	document.getElementById('return').removeAttribute('required');
  }
}

$(document).ready(function() {
	
	$(".pagination").customPaginate({
		itemsToPaginate: ".itinerary",
		itemsPerPage: 4
	});
});

