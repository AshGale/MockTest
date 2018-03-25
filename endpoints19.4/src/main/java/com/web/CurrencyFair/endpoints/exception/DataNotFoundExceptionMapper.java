package com.web.CurrencyFair.endpoints.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.web.CurrencyFair.endpoints.modules.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<TradeMessageException>{

	@Override
	public Response toResponse(TradeMessageException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "https://docs.google.com/document/d/1st5i5OpravXYhXBZry8qZwP-ish-ZfQQcSOBduKf73k/edit#");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}


}
