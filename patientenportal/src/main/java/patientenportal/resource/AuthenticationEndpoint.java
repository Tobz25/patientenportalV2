package patientenportal.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import patientenportal.dao.UserDAOImpl;
import patientenportal.model.User;
import patientenportal.service.AuthenticationService;
import patientenportal.service.SessionService;

@Path("/authentication")
public class AuthenticationEndpoint {
	
	AuthenticationService authService;
	
	SessionService sessionService;
	
	public AuthenticationEndpoint() {
		authService = new AuthenticationService();
		sessionService = new SessionService();
	}
	
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticateUser(@FormParam("username") String username, 
                                     @FormParam("password") String password) {

        try {

            // Authenticate the user using the credentials provided
            //authenticate(username, password);

            // Issue a token for the user
            String token = authenticate(username, password); 

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
        	System.out.println("Fehler: " + e.getMessage());
        	Response unauthorizedStatus = Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("Login failed. Username or password are incorrect.")
					.build();
            return unauthorizedStatus;
        }      
    }

    private String authenticate(String username, String password) throws Exception {
    	User authenticatedUser = authService.authenticateUser(username, password);
    	if (authenticatedUser == null){
    		throw new Exception();
    	}
    	else return issueToken(authenticatedUser);
    }

    private String issueToken(User user) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    	String token = sessionService.createSessionToken(user);
    	return token;
    }
    
    @GET
    @Path("/logout")
    public Response logout(ContainerRequestContext requestContext){
    	
    	
	   try {
		    String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
	    	String token = authorizationHeader.substring("Bearer".length()).trim();
	    	return authService.logout(token);
	   }
	   catch(Exception e) {
	    	return Response.status(Status.NOT_ACCEPTABLE)
	    			.entity("Logout is not possible. No or unvalid session token transmitted")
				.build();
	   }
    }
    
    @POST
    @Path("/create")
    @Consumes("application/x-www-form-urlencoded")
    public Response addUser(@FormParam("username") String username, 
            @FormParam("password") String password){

    	User user = new User();

    	user.setPassword(password);
        user.setUsername(username);
                
        UserDAOImpl dao = new UserDAOImpl();
        dao.addEntity(user);
        
        return Response.ok().build();
    }
}