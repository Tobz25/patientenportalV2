package patientenportal.resource;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import patientenportal.service.TreatmentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TreatmentEndpoint {
	@Context
	SecurityContext securityContext;
	TreatmentService treatmentService = new TreatmentService();
	
	//public List<Treatments>

}
