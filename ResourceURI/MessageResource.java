package com.service.java;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;

import com.service.java.exception.DataNotFoundException;
import com.service.java.modal.Message;
import com.service.java.modal.ResponseMessages;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;

import com.service.java.service.MessageService;

@Path("/messages")
@Produces("application/json")
public class MessageResource {
	
	MessageService ms = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getmessages(@QueryParam("year") int year, 
			@QueryParam("start") int start, 
			@QueryParam("size") int size){
		if(year>0){
			return ms.getAllMessagesForYear(year);
			}
		if(start>0 && size>0){
			return ms.getAllMessagePaginated(start, size);
		}
		return  ms.getAllMessage();
		}
	
	@POST
	@Produces("application/xml")
	@Consumes("application/xml")
	public List<Message> addAllMessages(ResponseMessages messages){
		return ms.addAllMessages(messages.getMessage());
	}
	
	@GET
	@Produces("application/json")
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo){
		Message msg = ms.getMessage(id);
		msg.addLink(getURIForSelf(uriInfo, msg), "self");
		msg.addLink(getURIForProfile(uriInfo, msg), "Profile");
		msg.addLink(getURIForComments(uriInfo,msg), "Comments");
		if(msg == null){
			throw new DataNotFoundException("Message with id "+id+" not found");
		}
		return msg;
	}

	private String getURIForComments(UriInfo uriInfo, Message msg) {
		URI uri = uriInfo.getBaseUriBuilder()
		.path(MessageResource.class)
		//.path(MessageResource.class,"getComments")
		//.path(CommentResource.class)
		//.resolveTemplate("messageId",msg.getId())
		.build();
		return uri.toString();
	}

	private String getURIForProfile(UriInfo uriInfo, Message msg) {
		String uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(String.valueOf(msg.getId())).build().toString();
		return uri;
	}

	private String getURIForSelf(UriInfo uriInfo, Message msg) {
		String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class).path(String.valueOf(msg.getId())).build().toString();
		return uri;
	}
	
	@POST
	@Path("/message")
	@Consumes("application/xml")
	@Produces("application/json")
	public Response addMessage(Message amsg, @Context UriInfo uriInfo ) throws URISyntaxException{
		System.out.println(uriInfo.getAbsolutePath());
		Message msg = ms.addMessage(amsg);
		String newId = String.valueOf(msg.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).status(Status.CREATED).entity(msg).build();
		//return msg;
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes("application/xml")
	@Produces("application/xml")
	public Message updateMessage(Message umsg,@PathParam("messageId") long messageId){
		umsg.setId(messageId);
		return ms.updateMessage(umsg);
	}
	
	@DELETE
	@Path("/{Identifier}")
	public void deleteMessage(@PathParam("Identifier") long identifier){
		ms.removeMessage(identifier);
	}
	
	@Path("{messageId}/comments")
	public CommentResource getComments(){
		return new CommentResource();
	}
	
	
}
