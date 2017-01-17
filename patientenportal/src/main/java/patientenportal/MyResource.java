package patientenportal;

import java.security.Principal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import patientenportal.helper.Secured;
import javax.ws.rs.core.SecurityContext;

/**
 * Root resource (exposed at "myresource" path)
 */
@Secured
@Path("myresource")
public class MyResource {
	@Context
	SecurityContext securityContext;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	Principal user = securityContext.getUserPrincipal();
    	String username = user.getName();
    	System.out.println(username);
        return "Got it!";
    }
}
