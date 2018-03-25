<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>TradeMessage Server Administration</title>

<!-- JavaScriptScripts -->
<script src="javascript/jquery-3.2.1.js"></script>
<script src="javascript/tradeService.js" type="text/javascript"></script>

<!-- Css -->
<link rel="stylesheet" href="css/styles.css" type="text/css" />

</head>
<body>
	<h1>
		TradeMessage <a
			href="http://localhost:8080/endpoints/webapi/jsonTradeEndpoint/api">API</a>
	</h1>
	<div id="operationContainer">
		<select id='operation' name="operation">
			<option value="GET">GET</option>
			<option value="POST">POST</option>
			<option value="PUT">PUT</option>
			<option value="DELETE">DELETE</option>
		</select>
	</div>
	<div id="requestContainer">
		<div id="singleRequest">
			<h3>Single Request Form:</h3>
			<form id="sendRequestfilter">
				<table style="with: 50%">
					<tr>
						<td>primaryKey</td>
						<td><input type="text" id="primaryKey" name="primaryKey" />0</td>
					</tr>
					<tr>
						<td>userId</td>
						<td><input type="text" id="userId" name="userId" />0</td>
					</tr>
					<tr>
						<td>currencyFrom</td>
						<td><input type="text" id="currencyFrom" name="currencyFrom" />EUR</td>
					</tr>
					<tr>
						<td>currencyTo</td>
						<td><input type="text" id="currencyTo" name="currencyTo" />EUR</td>
					</tr>
					<tr>
						<td>amountSell</td>
						<td><input type="text" id="amountSell" name="amountSell" />0</td>
					</tr>
					<tr>
						<td>amountBuy</td>
						<td><input type="text" id="amountBuy" name="amountBuy" />0</td>
					</tr>
					<tr>
						<td>rate</td>
						<td><input type="text" id="rate" name="rate" />0</td>
					</tr>
					<tr>
						<td>timePlaced</td>
						<td><input type="text" id="timePlaced" name="timePlaced" />01-JAN-00 00:00:00</td>
					</tr>
					<tr>
						<td>originatingCountry</td>
						<td><input type="text" id="originatingCountry"
							name="originatingCountry" />IE</td>
					</tr>
					<tr>
						<td>start</td>
						<td><input type="text" id="start" name="start" />0</td>
					</tr>
					<tr>
						<td>size</td>
						<td><input type="text" id="size" name="size" value="10" />0</td>
					</tr>

				</table>
				<input id="submitForm" type="button" name="submitForm"
					value="Send Single Request" autocomplete="off" />
			</form>
		</div>
		<div id="rawJson">
			<h3>RawJson:</h3>
			<textarea id="jsonText" placeholder="See API for example JSON"></textarea>
			<input id="submitJson" type="button" value="Send Json Request"
				autocomplete="off" />
		</div>
	</div>
	<div id=responceContainer>
		<h3 id="responceCounter">Message From server:</h3>
		<pre class="responce" id="responce"></pre>
	</div>
</body>

</html>
