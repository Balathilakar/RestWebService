package com.service.java;

import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

@Path("/injectDemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemo {
	
	@GET
	@Path("annotations")
	public String getParamUsingAnnotation(@MatrixParam("param") String value,
						@HeaderParam("customHeaderValue") int headValue){
		return "Matrix param: "+value+" Header Param: "+headValue;
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
		String uri = uriInfo.getAbsolutePath().toString();
		String cookies = headers.getCookies().toString();
		return "Path: " + uri +" Cookies "+cookies;		
	}
	
}
