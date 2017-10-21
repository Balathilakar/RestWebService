package com.service.java.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.service.java.modal.ErrorMessage;

@Provider
public class DataNotfoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	public Response toResponse(DataNotFoundException dataException) {
		ErrorMessage em = new ErrorMessage(dataException.getMessage(),404,"https:thilak/RestWebservice/Message");
		return Response.status(Status.NOT_FOUND).entity(em).build();
	}
	
	
	
}
