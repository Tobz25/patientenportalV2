package patientenportal;

import java.security.Principal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import patientenportal.helper.MySecurityContext;
import patientenportal.helper.Secured;
import patientenportal.service.DoctorService;
import patientenportal.service.FirstTableCreationService;

import javax.ws.rs.core.SecurityContext;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	FirstTableCreationService s = new FirstTableCreationService();
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	//Principal user = ((MySecurityContext)securityContext).getUserPrincipal();
    	String username = "Hagen"; //user.getName();
    	System.out.println("Nutzername: " + username);
    	StringBuilder result = new StringBuilder();
    	result.append("MyResource aufgerufen.\n ");
    	result.append("Bearer für Nutzer: " + username + "\n");
    	result.append("----------------------" + "\n");
    	result.append("Got it!");
        result.append("Ergebnis anlegen: " + String.valueOf(s.FirstAttempt()));
    	return result.toString();
    }
}
