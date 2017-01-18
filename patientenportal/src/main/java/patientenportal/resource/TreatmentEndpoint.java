package patientenportal.resource;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import patientenportal.model.Treatment;
import patientenportal.service.TreatmentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TreatmentEndpoint {
	@Context
	SecurityContext securityContext;
	TreatmentService treatmentService = new TreatmentService();
	
	@GET
	public Set<Treatment> getAllTreatments(@PathParam ("caseFileId") long caseFileId){
		return treatmentService.getTreatments(caseFileId);
	}

}
