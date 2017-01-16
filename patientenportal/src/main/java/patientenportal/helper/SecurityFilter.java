package patientenportal.helper;

import java.io.IOException;
import java.security.Principal;

import javax.ws.rs.Priorities;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import patientenportal.helper.Secured;
import patientenportal.service.AuthenticationService;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter{
	
	AuthenticationService authService = new AuthenticationService();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {			
			// Get the HTTP Authorization header from the request
	        String authorizationHeader = 
	            requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
	        
	     // Check if the HTTP Authorization header is present and formatted correctly
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	        	// Extract the token from the HTTP Authorization header
	        	String token = authorizationHeader.substring("Bearer".length()).trim();
	        	if (validateToken(token) == true){
	        		return;
	        	}
	        }
	        
/*	        requestContext.setSecurityContext(new SecurityContext() {

	            @Override
	            public Principal getUserPrincipal() {

	                return new Principal() {

	                    @Override
	                    public String getName() {
	                        return username;
	                    }
	                };
	            }

	            @Override
	            public boolean isUserInRole(String role) {
	                return true;
	            }

	            @Override
	            public boolean isSecure() {
	                return false;
	            }

	            @Override
	            public String getAuthenticationScheme() {
	                return null;
	            }
	        });*/
	        
	        Response unauthorizedStatus = Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("User cannot access the resource.")
					.build();

	        requestContext.abortWith(unauthorizedStatus);
	        
		
	}
	
	private boolean validateToken(String token) {
		boolean authenticated = authService.authenticateToken(token);
		if (authenticated == false){
    		return false;
    	}
		else return true;
	}

}