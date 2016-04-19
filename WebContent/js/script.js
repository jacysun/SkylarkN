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

function checkFrom() {
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
	var from = document.getElementById('from').value;
	var valid = airportList.indexOf(from);
	if (valid < 0) {
		alert("Departure Airport Code is invalid! Please input a valid code.")
	}
}

function checkTo() {
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
	var to = document.getElementById('to').value;
	var valid = airportList.indexOf(to);
	if (valid < 0) {
		alert("Arrival Airport Code is invalid! Please input a valid code.")
	}
}

(function($){

    $.fn.customPaginate = function(options)
    {
        var paginationContainer = this;
        var itemsToPaginate;
        
         
        var defaults = {
    
             itemsPerPage : 5
        
        };
     
        var settings = {};
     
        $.extend(settings, defaults, options);
     
        itemsToPaginate = $(settings.itemsToPaginate);
        
        var numberOfPaginationLinks = Math.ceil((itemsToPaginate.length / settings.itemsPerPage));
     
        $("<ul></ul>").prependTo(paginationContainer);
        
        for(var index = 0; index < numberOfPaginationLinks; index++)
        {
             paginationContainer.find("ul").append("<li>"+ (index+1) + "</li>");
        }
        
        itemsToPaginate.filter(":gt(" + (settings.itemsPerPage - 1)  + ")").hide();
        
        paginationContainer.find("ul li").first().addClass(settings.activeClass).end().on('click', function(){
			   
			   var $this = $(this);
			   
			   $this.addClass(settings.activeClass);
			   
			   $this.siblings().removeClass(settings.activeClass);
        
            var linkNumber = $this.text();
            
             var itemsToHide = itemsToPaginate.filter(":lt(" + ((linkNumber-1) * settings.itemsPerPage)  + ")");
             $.merge(itemsToHide, itemsToPaginate.filter(":gt(" + ((linkNumber * settings.itemsPerPage) - 1)  + ")"));
             
             var itemsToShow = itemsToPaginate.not(itemsToHide);

             $("html,body").animate({scrollTop:"0px"}, function(){
               itemsToHide.hide();
               itemsToShow.show();
             });
        });
        
    }
         
}(jQuery));

(function($){
    
    $(document).ready(function(){
    
        $(".pagination").customPaginate({
        
            itemsToPaginate : ".itinerary",
            itemsPerPage: 8,
            activeClass: "active-class"
        
        });
    });
    
})(jQuery);


