package com.service.java.service;

import com.service.java.database.DatabaseClass;

import java.util.Map;

import com.service.java.modal.ErrorMessage;
import com.service.java.modal.Message;
import com.service.java.modal.Comment;
import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class CommentService {
		
		private Map<Long,Message> messages = DatabaseClass.getMessages();
		
		public List<Comment> getAllComments(long messageId){
				Map<Long,Comment> comments = messages.get(messageId).getComments();
				return new ArrayList<Comment>(comments.values());
		}
		
		public Comment getComment(long messageId, long commentId){
			Message message = messages.get(messageId);
			
			ErrorMessage errMessage = new ErrorMessage("Not Found",404,"https:thilak/RestWebservice/Message");			
			Response response = Response.status(Status.NOT_FOUND).entity(errMessage).build();
			if(message == null){
				throw new WebApplicationException(response);
			}
			Map<Long,Comment> comments = message.getComments();
			Comment comment = comments.get(commentId);
			if(comment ==null){
				throw new NotFoundException(response);
			}
			return comment;
		}
		
		public Comment addComment(Long messageId, Comment comment){
			Map<Long,Comment> comments = messages.get(messageId).getComments();
			comment.setId(comments.size()+1);
			comments.put(comment.getId(),comment);
			return comment;
		}
		
		public Comment updateComment(long messageId, Comment comment){
			Map<Long, Comment> comments = messages.get(messageId).getComments();
			if(comment.getId() <= 0){
				return null;
			}
			comments.put(comment.getId(), comment);
			return comment;
		}
		
		public void removeComment(long messageId, long commentId){
			Map<Long,Comment> comments = messages.get(messageId).getComments();
			 comments.remove(commentId);
		}
		
		
		
		
		
		
 }
