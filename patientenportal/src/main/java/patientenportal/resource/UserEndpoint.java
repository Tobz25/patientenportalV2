package patientenportal.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import patientenportal.helper.Secured;
import patientenportal.model.User;
import patientenportal.service.UserService;

@Secured
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserEndpoint {
	@Context
	SecurityContext securityContext;
	UserService userService = new UserService();
	
	@GET 
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
		
	@GET
	@Path("/{userId}")
	public User getUser(@PathParam("userId") long userId) {
		return userService.getUserById(userId);
	}
	
	
	//TODO: gibt bisher null zurück (DAO und Service noch anpassen)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public User addUser(User user){
		return userService.addUser(user);		
	}
	
	//TODO: gibt bisher null zurück (DAO und Service noch anpassen)
	@PUT
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateUser(User user){
		return userService.updateUser(user);
	}
	
	@DELETE
	@Path("/{userId}")
	public void deleteUser(@PathParam("userId") long userId){
		userService.deleteUser(userId);
	}


}
