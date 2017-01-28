package patientenportal.helper;

import java.io.IOException;
import java.security.Principal;

import javax.ws.rs.Priorities;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import patientenportal.helper.Secured;
import patientenportal.model.User;
import patientenportal.service.AuthenticationService;
import patientenportal.service.SessionService;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter{
	
	AuthenticationService authService = new AuthenticationService();
	SessionService sservice = new SessionService();
	
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {			
			// Get the HTTP Authorization header from the request
	        String authorizationHeader = 
	            requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
	        
	     // Check if the HTTP Authorization header is present and formatted correctly
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	        	
	        	//abgelaufene Tokens werden gelöscht
	        	sservice.deleteInvalidTokens();
	        	
	        	// Extract the token from the HTTP Authorization header
	        	String token = authorizationHeader.substring("Bearer".length()).trim();
	        	if (validateToken(token) == true){
	        		
	        		//Get the user by this token
	    	        User user = authService.getUserByToken(token);
	    	        System.out.println("Nutzer: " + user.toString());
	    	        requestContext.setSecurityContext(new MySecurityContext(user));
	    	        return;
	        	}
	        }
	        
	        throw new UnauthorizedException("Client has to be logged in to access the ressource");
	        
		
	}
	
	private boolean validateToken(String token) {
		boolean authenticated = authService.authenticateToken(token);
		if (authenticated == false){
    		return false;
    	}
		else {
			return true;
		}
	}

}