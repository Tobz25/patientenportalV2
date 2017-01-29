package patientenportal.resource;
/*
 * Service-Schnittstelle für alle Operationen, die eine Behandlung betreffen
 */

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import patientenportal.helper.MySecurityContext;
import patientenportal.helper.Secured;
import patientenportal.helper.UnauthorizedException;
import patientenportal.model.CaseFile;
import patientenportal.model.Role;
import patientenportal.model.Treatment;
import patientenportal.model.User;
import patientenportal.service.PermissionService;
import patientenportal.service.TreatmentService;

@Secured
@Produces(MediaType.APPLICATION_JSON)
public class TreatmentEndpoint {
	TreatmentService treatmentService = new TreatmentService();
	PermissionService permissionService = new PermissionService();
	
	
	@GET
	@Path("/")
	public Set<Treatment> getAllTreatments(@PathParam ("caseFileId") long caseFileId, @Context SecurityContext securityContext){
		User user = (User) securityContext.getUserPrincipal();
		Set<Treatment> treatments = treatmentService.getTreatments(caseFileId);;
		for(Treatment tm: treatments) {
			if (!permissionService.checkReadPermission(user.getActiveUserRole().getId(), tm.getId())) {
				throw new UnauthorizedException("User does not have access to the requested ressource");
			}
		}
		return treatments;
	}
	
	@GET
	@Path("/{treatmentId}")
	public Treatment getTreatment(@PathParam("treatmentId") long treatmentId, @Context SecurityContext securityContext){
		User user = (User) securityContext.getUserPrincipal();		
		if (permissionService.checkReadPermission(user.getActiveUserRole().getId(), treatmentId)){
			return  treatmentService.getTreatmentById(treatmentId);
		}
		else{
			throw new UnauthorizedException("User does not have access to the requested ressource");
		}
	}
	
	
	@POST
	@Path("/")
	@Secured({Role.Doctor})
	@Consumes(MediaType.APPLICATION_JSON)
	public Treatment addTreatment(Treatment treatment, @PathParam("caseFileId") long caseFileId, @Context SecurityContext securityContext){
		User user = (User) securityContext.getUserPrincipal();
		if(permissionService.checkWritePermission(user.getActiveUserRole().getId(), caseFileId)){
			return treatmentService.addTreatment(treatment);
		}
			
		return null;
	}
	
	@PUT
	@Path("/")
	@Secured({Role.Doctor})
	@Consumes(MediaType.APPLICATION_JSON)
	public Treatment updateTreatment(Treatment treatment, @PathParam("caseFileId") long caseFileId, @Context SecurityContext securityContext){
		User user = (User) securityContext.getUserPrincipal();
		if(permissionService.checkWritePermission(user.getActiveUserRole().getId(), caseFileId)){
			return treatmentService.updateTreatment(treatment);
		}
			
		return null;
	}
	
	@PUT
	@Path("/{treatmentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Treatment updateTreatment(Treatment treatment){
		return treatmentService.updateTreatment(treatment);
	}
	
	@Path("/{treatmentId}/medicationPrescription")
	public MedicationPrescriptionEndpoint getMedicationPrescription(){
		return new MedicationPrescriptionEndpoint();
	}
}
