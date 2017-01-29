package patientenportal.resource;
/*
 * Service-Schnittstelle, um einem Patienten Angehörige hinzuzufügen und die hinzugefügten Angehörigen auszugeben
 */

import java.util.Set;

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
import patientenportal.model.Role;
import patientenportal.model.User;
import patientenportal.service.LinkedRelativesService;
import patientenportal.service.PatientService;


@Secured
@Produces(MediaType.APPLICATION_JSON)
public class LinkedRelativesEndpoint {
	PatientService patientService = new PatientService();
	LinkedRelativesService linkedRelativesService = new LinkedRelativesService();
	
	
	@GET
	@Path("/")
	public Set<Relative> getRelatives(@PathParam("patientId") long patientId,
									  @Context SecurityContext securityContext){

		return linkedRelativesService.getRelatives(patientId);		
	}
	
	@POST
	@Path("/linkRelative/{relativeId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured({Role.Patient})
	public Relative linkRelative(@Context SecurityContext securityContext, 
								 @PathParam("patientId") long patientId, 
								 @PathParam("relativeId") long relativeId){
		User user = (User) securityContext.getUserPrincipal();
		
		
		Patient patient = patientService.getPatientById(patientId);
		if (user.getActiveUserRole().getId() == patientId){
			Patient clientPatient = (Patient) user.getActiveUserRole();
			return linkedRelativesService.linkRelative(clientPatient, relativeId);
		}
		else{
			throw new UnauthorizedException("User does not have access to the requested ressource");
		}
	}

}
