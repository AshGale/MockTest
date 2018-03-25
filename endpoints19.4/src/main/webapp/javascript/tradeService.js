$(document).ready(function() {
	
	$('#submitJson').click(function() {
		$("#responce").html("Loading . . .");
		
		var jsonText = $("textarea#jsonText").val();
		var url = "webapi/jsonTradeEndpoint/";
		var operation = $('#operation').val();
		
		if(operation == 'GET')
		{
			jsonText = "";
		}
		
		$.ajax({
			method : operation,
			url : url,
			contentType: "application/json; charset=utf-8",
			data : jsonText,
			
			success : function(data) {				
				//console.log("Responce body from Server: \n"+JSON.stringify(data));
				$("#responceCounter").text(data.length +'Message From Server');
				$("#responce").html(JSON.stringify(data, null, 4));
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log('Error: '+ errorThrown);
				$("#responce").html("errorThrown");
            }
		});
	});//END $('#submitJson').click(function() {
	
	//sendRequestfilter
	$('#submitForm').click(function() {
		$("#responce").html("Loading . . .");
		
		var operation = $('#operation').val();	
		var url = "webapi/jsonTradeEndpoint";		
		var myForm = document.getElementById('sendRequestfilter');
		var data = null;
		
		if(operation == 'GET'){
			//get
			var allInputs = myForm.getElementsByTagName('input');
			url += '?';
			
		    for (var i = 0; i < allInputs.length; i++) {
		        var input = allInputs[i];
		        if (input.value != "" && input.name != "submitForm") {
		        	
		        	url += input.name +'='+input.value+'&';
		        }		        
		    }
		    console.log(url);//TODO tidy last &
		}
		else if(operation == 'POST'){
			
			var tradeMessage = new Object();			
			tradeMessage.primaryKey=$('#primaryKey').val(); 
			tradeMessage.userId=$('#userId').val(); 
			tradeMessage.currencyFrom=$('#currencyFrom').val(); 
			tradeMessage.currencyTo=$('#currencyTo').val(); 
			tradeMessage.amountSell=$('#amountSell').val(); 
			tradeMessage.amountBuy=$('#amountBuy').val(); 
			tradeMessage.rate=$('#rate').val(); 
			tradeMessage.timePlaced=$('#timePlaced').val(); 
			tradeMessage.originatingCountry=$('#originatingCountry').val();
			
			data = JSON.stringify(tradeMessage);

		}
		else if(operation == 'PUT'){
			var tradeMessage = new Object();			
			tradeMessage.primaryKey=$('#primaryKey').val(); 
			tradeMessage.userId=$('#userId').val(); 
			tradeMessage.currencyFrom=$('#currencyFrom').val(); 
			tradeMessage.currencyTo=$('#currencyTo').val(); 
			tradeMessage.amountSell=$('#amountSell').val(); 
			tradeMessage.amountBuy=$('#amountBuy').val(); 
			tradeMessage.rate=$('#rate').val(); 
			tradeMessage.timePlaced=$('#timePlaced').val(); 
			tradeMessage.originatingCountry=$('#originatingCountry').val();
			
			data = JSON.stringify(tradeMessage);		
			
		}
		else if(operation == 'DELETE'){
			
		}
		
		
		
		$.ajax({
			method : operation,
			url : url,
			contentType: "application/json; charset=utf-8",
			data : data,
			success : function(data) {
				//console.log("Responce body from Server: \n" + JSON.stringify(data));
				$("#responceCounter").text(data.length +' Message From Server');
				$("#responce").html(JSON.stringify(data, null, 4));
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log('Error: ' + errorThrown);
				$("#responce").html("errorThrown");
			}
		});
	});//END $('#submitForm').click(function() {
});