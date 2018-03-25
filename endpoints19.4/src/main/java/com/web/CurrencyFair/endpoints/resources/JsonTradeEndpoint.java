
package com.web.CurrencyFair.endpoints.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.internal.guava.Lists;

import com.web.CurrencyFair.endpoints.modules.TradeMessage;
import com.web.CurrencyFair.endpoints.resources.beans.TradeMessageFilterBean;
import com.web.CurrencyFair.endpoints.service.TradeService;

//import jersey.repackaged.com.google.common.collect.Lists;

@Path("/jsonTradeEndpoint")
@Produces("application/json")
@Consumes("application/json")
public class JsonTradeEndpoint {

	TradeService tradeService = new TradeService();

	@GET
	@Produces({"application/json"})
	@Consumes({"application/json",MediaType.APPLICATION_FORM_URLENCODED})
	public Response getTradeMessages(@BeanParam TradeMessageFilterBean filterBean) {

		System.out.println(filterBean.toString());
		List<TradeMessage> getResult = tradeService.getTradeMessages(filterBean);
		GenericEntity<List<TradeMessage>> entity = new GenericEntity<List<TradeMessage>>(
				Lists.newArrayList(getResult)) {
		};
		// return Response.status(Status.FOUND).entity(entity).build();//ajax can't
		// support !200
		return Response.ok().entity(getResult).build();
	}

	@POST
	public Response createTradeMessages(List<TradeMessage> tradeMessages, @Context UriInfo uriInfo) {
		List<TradeMessage> createdTradeMessages = tradeService.createTradeMessages(tradeMessages);

		GenericEntity<List<TradeMessage>> entity = new GenericEntity<List<TradeMessage>>(
				Lists.newArrayList(createdTradeMessages)) {
		};

		return Response.ok().entity(entity).build();
	}

	@PUT
	public Response updateTradeMessages(List<TradeMessage> tradeMessages) {

		List<TradeMessage> updatedTradeMessages = tradeService.updateTradeMessages(tradeMessages);
		GenericEntity<List<TradeMessage>> entity = new GenericEntity<List<TradeMessage>>(
				Lists.newArrayList(updatedTradeMessages)) {
		};
		return Response.ok().entity(entity).build();
	}

	@DELETE
	public Response deleteTradeMessages(List<TradeMessage> tradeMessages) {

		tradeService.deleteTradeMessages(tradeMessages);
		return Response.ok().build();
	}

	@GET
	@Path("/{primaryKey}")
	public Response getUserTradeMessages(@PathParam("primaryKey") int primaryKey) {
		TradeMessage trademessage = tradeService.getTradeMessage(primaryKey);
		ArrayList<TradeMessage> list = new ArrayList<TradeMessage>();
		list.add(trademessage);
		System.out.println(list.toString());
		GenericEntity<List<TradeMessage>> entity = new GenericEntity<List<TradeMessage>>(list) {
		};

		// ajax can't support other status responces.
		return Response.ok().entity(entity)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("utf-8")).build();
	}

	@DELETE
	@Path("/{primaryKey}")
	public Response removeTradeMessage(@PathParam("primaryKey") int primaryKey) {
		tradeService.deleteTradeMessage(primaryKey);
		return Response.status(Status.NO_CONTENT).build();
	}

	@GET
	@Path("/create/{number}")
	public Response createTradeMessages(@PathParam("number") int create) {

		GenericEntity<List<TradeMessage>> entity = new GenericEntity<List<TradeMessage>>(
				Lists.newArrayList(tradeService.generateTradeMessages(create))) {
		};

		return Response.status(Status.CREATED).entity(entity).build();
	}

	@GET
	@Path("/api")
	public Response dispalyApi() {
		// TODO make this a Simeple HTML site
		String api = "";
		api += "This is the API documintaion for http://localhost:8080/endpoints/webapi/jsonTradeEndpoint/" + "\n";
		api += "This is a list of the API URI's:" + "\n";
		api += "\n*Please ensuer all / json body message have surounding [] annotation" + "\n";
		api += "/ 			CRUD opperations consume and produce Content-Type = application/json in format [TradeMessage]"
				+ "\n";
		api += "/{primaryKey} 		RD will do the respective opperation of the instance in the DB" + "\n";
		api += "/create/{number}	Creates $ records on the DB of random values in EUR market" + "\n";
		api += "\nfilterParmiters:";
		api += "\nprimaryKey=0&start=0&size=0&userId=0&year=0&originatingCountry=0&rate=0&currencyFrom=0&currencyTo=0&amountSell=0&amountBuy=0&timePlaced=0\n";
		api += "\nSample TradeMessage:" + "\n";
		api += "[\r\n" + "    {\r\n" + "        \"amountBuy\": 7976,\r\n" + "        \"amountSell\": 5140,\r\n"
				+ "        \"currencyFrom\": \"EUR\",\r\n" + "        \"currencyTo\": \"EUR\",\r\n"
				+ "        \"originatingCountry\": \"IE\",\r\n" + "        \"primaryKey\": 1,\r\n"
				+ "        \"rate\": 52.462395688840104,\r\n"
				+ "        \"timePlaced\": \"11-NOV-05 09:16:48\",\r\n" + "        \"userId\": 93522\r\n"
				+ "    }\r\n" + "]" + "\n";

		return Response.status(Status.TEMPORARY_REDIRECT).entity(api).build();
	}
}
