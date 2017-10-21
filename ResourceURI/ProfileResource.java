package com.service.java;


import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import com.service.java.modal.Profile;
import com.service.java.service.ProfileService;
import javax.ws.rs.Path;
import java.util.List;

@Path("/Profiles")
public class ProfileResource {
	
	ProfileService ps = new ProfileService();
	
	@GET
	@Produces("application/xml")
	public List<Profile> getProfiles(){
		return ps.getProfiles();
	}
	
}
