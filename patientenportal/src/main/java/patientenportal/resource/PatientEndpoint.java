package patientenportal.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import patientenportal.helper.Secured;
import patientenportal.helper.UnauthorizedException;
import patientenportal.model.Patient;
import patientenportal.model.PatientFile;
import patientenportal.model.Relative;
import patientenportal.model.User;
import patientenportal.service.PatientService;
import patientenportal.service.PermissionService;

@Secured
@Path("patients")
@Produces(MediaType.APPLICATION_JSON)
public class PatientEndpoint {
	PatientService patientService = new PatientService();
	PermissionService permissionService = new PermissionService();
	
	
	@GET 
	public List<Patient> getPatients(){
		return patientService.getPatients();
	}
		
	@GET
	@Path("/{patientId}")
	public Patient getPatient(@PathParam("patientId") long patientId) {
		return patientService.getPatientById(patientId);
	}
	
	@Path("/{patientId}/patientFile")
	public PatientFileEndpoint getPatientFileEndpoint(){
		return new PatientFileEndpoint();
	}
	
	@Path("/{patientId}/relatives")
	public LinkedRelativesEndpoint getLinkedRelativeEndpoint(){
		return new LinkedRelativesEndpoint();
	}
	

}
