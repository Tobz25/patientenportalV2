package patientenportal.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import patientenportal.helper.Secured;
import patientenportal.model.Patient;
import patientenportal.service.PatientService;

@Secured
@Path("patients")
@Produces(MediaType.APPLICATION_JSON)
public class PatientEndpoint {
	@Context
	SecurityContext securityContext;
	PatientService patientService = new PatientService();
	
	
	@GET 
	List<Patient> getPatients(){
		return patientService.getPatients();
	}
		
	@GET
	@Path("/{patientId}")
	public Patient getPatient(@PathParam("patientId") long patientId) {
		return patientService.getPatientById(patientId);
	}
	
	@GET
	@Path("/{patientId}/patientFile")
	public PatientFileEndpoint getPatientFileEndpoint(){
		return new PatientFileEndpoint();
	}
	

}
