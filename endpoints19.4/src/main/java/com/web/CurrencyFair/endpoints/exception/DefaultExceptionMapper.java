package com.web.CurrencyFair.endpoints.exception;

import java.sql.SQLDataException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.web.CurrencyFair.endpoints.modules.ErrorMessage;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<SQLDataException>{

	//replace sqldataexception with TROWABLE
//	@Override
//	public Response toResponse(Throwable ex) {
//		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500, "https://docs.google.com/document/d/1st5i5OpravXYhXBZry8qZwP-ish-ZfQQcSOBduKf73k/edit#");
//		return Response.status(Status.INTERNAL_SERVER_ERROR)
//				.entity(errorMessage)
//				.build();
//	}

	@Override
	public Response toResponse(SQLDataException arg0) {
		// TODO Auto-generated method stub
		return null;
	}


}
