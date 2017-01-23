package patientenportal.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import patientenportal.helper.Secured;
import patientenportal.model.CaseFile;
import patientenportal.model.PatientFile;
import patientenportal.service.PatientFileService;
import patientenportal.service.PatientService;

@Secured
@Produces(MediaType.APPLICATION_JSON)
public class PatientFileEndpoint {
	@Context
	SecurityContext securityContext;
	PatientFileService patientFileService = new PatientFileService();
	
	
	@GET
	@Path("/")
	public PatientFile getPatientFile(@PathParam("patientId") long patientId){
		return patientFileService.getPatientFileById(patientId);
	}
	
	@GET
	@Path("/{patientFileId}")
	public PatientFile getPatientFileById(@PathParam("patientFileId") long patientFileId){
		return patientFileService.getPatientFileById(patientFileId);
	}
	
	@Path("/{patientFileId}/caseFiles")
	public CaseFileEndpoint getCaseFileEndpoint(){
		return new CaseFileEndpoint();
	}

}
