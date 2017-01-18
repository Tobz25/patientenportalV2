package patientenportal.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import patientenportal.helper.Secured;
import patientenportal.model.PatientFile;
import patientenportal.service.PatientFileService;
import patientenportal.service.PatientService;

@Secured
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class PatientFileEndpoint {
	@Context
	SecurityContext securityContext;
	PatientFileService patientFileService = new PatientFileService();
	
	
	@GET
	@Path("/{patientFileId}")
	public PatientFile getPatientFile(@PathParam("patientFileId") long patientFileId){
		return patientFileService.getPatientFileById(patientFileId);
	}

}
