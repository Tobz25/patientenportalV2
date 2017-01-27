package patientenportal.resource;

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
import patientenportal.model.Treatment;
import patientenportal.model.User;
import patientenportal.service.PermissionService;
import patientenportal.service.TreatmentService;

@Secured
@Produces(MediaType.APPLICATION_JSON)
public class TreatmentEndpoint {
	@Context
	SecurityContext securityContext;
	TreatmentService treatmentService = new TreatmentService();
	PermissionService permissionService = new PermissionService();
	
	@GET
	public Set<Treatment> getAllTreatments(@PathParam ("caseFileId") long caseFileId){
		return treatmentService.getTreatments(caseFileId);
	}
	
	@GET
	@Path("/{treatmentId}")
	public Treatment getTreatment(@PathParam("treatmentId") long treatmentId){
		return treatmentService.getTreatmentById(treatmentId);
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Treatment addTreatment(Treatment treatment, @Context SecurityContext securityContext){
		MySecurityContext context = (MySecurityContext) securityContext;
		return null;
		//if (permissionService.checkWritePermission(context.getUserId(), treatment.getCaseFile().getId()))
			//return treatmentService.addTreatment(treatment);
		//else throw new UnauthorizedException("User has no permission to add a treatment");
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
