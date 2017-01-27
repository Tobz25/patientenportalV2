package patientenportal.resource;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import patientenportal.dao.UserDAOImpl;
import patientenportal.model.User;
import patientenportal.service.AuthenticationService;
import patientenportal.service.FirstTableCreationService;
import patientenportal.service.HibernateAccessService;
import patientenportal.service.SessionService;

@Path("/hibernate")
public class HibernateEndpoint {
	
	HibernateAccessService hibernateService;
	
	public HibernateEndpoint() {
		hibernateService = new HibernateAccessService();
	}
	
    @GET
    @Path("/access")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTableNames() {
    	
    	List<String>  erg = hibernateService.getAllTables();
    	return Response.ok(erg.toString()).build();
    }
    
    @GET
    @Path("/testdaten")
    @Produces(MediaType.TEXT_PLAIN)
    public Response testdaten() {
    	FirstTableCreationService ftcs = new FirstTableCreationService();
    	return Response.status(Status.OK).entity(String.valueOf(ftcs.FirstAttempt())).build();
    }

}