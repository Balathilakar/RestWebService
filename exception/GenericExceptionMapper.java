package com.service.java.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.service.java.modal.ErrorMessage;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{
	
	public Response toResponse(Throwable ags) {
		ErrorMessage em = new ErrorMessage("Generic error",500,"https://thilak.org/RestWebservice/Message");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(em).build();

	}
	
	
	
}
