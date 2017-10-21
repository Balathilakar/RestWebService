package com.service.java;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import com.service.java.modal.Comment;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Consumes;

import com.service.java.service.CommentService;
@Path("/")
public class CommentResource {

	CommentService cs = new CommentService();
	
	@GET
	public String test(){
		return "new sub resource";
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId")long messageId,@PathParam("commentId") long commentId){
		return  cs.getComment(messageId, commentId);
	}
	
	@POST
	@Consumes("application/xml")
	public Comment addComment(@PathParam("messageId")long messageId, Comment cm){
		return cs.addComment(messageId,cm);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateMessage(@PathParam("commentId")long commentId,@PathParam("messageId")long messageId,Comment comment){
		comment.setId(commentId);
		return cs.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("commentId")long commentId,@PathParam("messageId")long messageId){
		 cs.removeComment(messageId, commentId);
	}
}
