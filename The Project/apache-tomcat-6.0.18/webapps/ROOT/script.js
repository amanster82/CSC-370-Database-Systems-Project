$(document).ready(function() {
	
	var sendPost = function(url, data, successDiv) {
		
		$.ajax({
			type: "POST",
			url: url,
			data: data,
			success: function(responseData) {
				$(successDiv).html(responseData);
			}
		});
	};
	
	// Airline submit
	$('#submit-stuff').click(function() {

	// Must match getParameter function call in Java classes
		sendPost("http://localhost:9000/servlet/InsertFlightInfo", {
			'name': $('#name').val(),  
			'website': $('#website').val(),
			'acode': $('#acode').val()
		}, "#airline-results");
	});
	
	// Route submit
	$('#rsubmit-stuff').click(function() {

		sendPost("http://localhost:9000/servlet/InsertRoutesInfo", {
			'rnum': $('#rnum').val(),  
			'planeModel': $('#rplanemodel').val(),
			'racode': $('#racode').val()
		}, "#route-results");
	});
	
	
	//Outgoingroutes 
	$('#orsubmit-stuff').click(function() {
		
		sendPost("http://localhost:9000/servlet/InsertOutgoingRoutes", {
			'acode': $('#ocode').val(),  
			'route_number': $('#oroute').val(),
			'destination': $('#odestination').val(),
			'out_time': $('#odate').val(),
		}, "#outgoing-results");
	});
	
	//IncomingRoutes 
	$('#irsubmit-stuff').click(function() {	
		sendPost("http://localhost:9000/servlet/InsertIncomingRoutes", {
			'acode': $('#icode').val(),  
			'route_number': $('#iroute').val(),
			'source': $('#isource').val(),
			'in_time': $('#indate').val()
		}, "#incoming-results");
	});

	//Gate 
	$('#Gatesubmit-stuff').click(function() {	
	
		sendPost("http://localhost:9000/servlet/InsertGate", {
			'gate': $('#gate').val(),
			'status': $('#gstat').val()
		}, "#gate-results");
	});	
	
	//Departures
	$('#DepartureSubmit-stuff').click(function() {

		var data = {
			'code': $('#dcode').val(),
			'route_number': $('#dnum').val(),
			'dep_ID': $('#dID').val(),
			'Gate': $('#dgate').val(),
			'd_time': $('#ddate').val()
		};
		
		console.log(data);
	
		sendPost("http://localhost:9000/servlet/InsertDepartures", data, "#departure-results");
	});	
	
		//Arrivals
	$('#ArrivalsSubmit-stuff').click(function() {

		var data = {
			'code': $('#arcode').val(),
			'route_number': $('#anum').val(),
			'dep_ID': $('#aID').val(),
			'Gate': $('#agate').val(),
			'a_time': $('#adate').val()
		};
		
		console.log(data);
	
		sendPost("http://localhost:9000/servlet/InsertArrivals", data, "#arrival-results");
	});	
	
	
	//Passengers
	
	$('#PassengersSubmit-stuff').click(function(){

		var data = { 
		
		pfile: $('#pfile').val().replace("C:\\fakepath\\","")
		
		};
		
		console.log(data);
		
	
		sendPost("http://localhost:9000/servlet/InsertPassengers", data, "#passenger-results");
	});	
	
	//Baggages
	
	$('#BaggagesSubmit-stuff').click(function() {

		var data = {
			bfile: $('#bfile').val().replace("C:\\fakepath\\","")
		
		};
		
		console.log(data);
	
		sendPost("http://localhost:9000/servlet/InsertBaggages", data, "#baggage-results");
	});	

	
	
	
	//Deletion
		$('#Deletionsubmit-stuff').click(function() {

		var data = {
			'delete': $('#routedel').val()
		};
		
		console.log(data);
	
		sendPost("http://localhost:9000/servlet/routeDeletion", data, "#RouteDeletion-results");
	});


/**EXTRACTING QUERIES QUESTON 5**/
	
	//a. Given an airline find all the routes it operates
	$('#findRsubmit-stuff').click(function() {

		var data = {
			'find': $('#findroute').val()
		};
		
		console.log(data);
	
		sendPost("http://localhost:9000/servlet/FindRoutes", data, "#findR-results");
	});
	
	
	//b. Given a place (e.g. Toronto) find all the routes from and to that place. 
		$('#findRcsubmit-stuff').click(function() {

		var data = {
			'findc': $('#findroutec').val()
		};
		
		console.log(data);
	
		sendPost("http://localhost:9000/servlet/RoutesPlace", data, "#findRc-results");
	});
	
	//c. Given a time of the day find all the arrivals and departures around that time and print their status. 
		$('#flightStatsubmit-stuff').click(function() {
		
		var data = {
			'date_flight': $('#fd').val()
		};
		
		console.log(data);
	
		sendPost("http://localhost:9000/servlet/FlightStat", data, "#flightStat-results");
	});
	
	
	//d. Given a departure or arrival find all the passengers recorded for it. Print all the information about these passengers. 
		$('#adIDsubmit-stuff').click(function() {
		
		var data = {
			'arr/depid': $('#adID').val()
		};
		
		console.log(data);
	
		sendPost("http://localhost:9000/servlet/FindPassengers", data, "#adID-results");
	});
	
	//e. For a given passenger in a flight find his/her baggage.
		$('#bagsubmit-stuff').click(function() {
		
		var data = {
			'passenger': $('#bagID').val()
		};
		
		console.log(data);
	
		sendPost("http://localhost:9000/servlet/FindBaggage", data, "#bag-results");
	});
	
	//f. Find a free gate for a flight (arrival or departure).
		$('#freesubmit-stuff').click(function() {
		
		var data = {
			'arrID/depID': $('#free').val()
		};
		
		console.log(data);
	
		sendPost("http://localhost:9000/servlet/FreeGate", data, "#free-results");
	});


	//g. Assign a flight as "done" and return the gate
		$('#Assignsubmit-stuff').click(function() {
		
		
		if($('input[value=free]').prop("checked") ){
			var x = $('#isFree').val();
		}
			else{
				var y = $('#NotFree').val();
			}
			
		var data = {
			'assigncode': $('#assigncode').val(),
			'isFree': x,
			'notFree': y
		};
		
		console.log(data);
	
		sendPost("http://localhost:9000/servlet/AssignFlight", data, "#Assign-results");
	});
	
	
	
	
	
});