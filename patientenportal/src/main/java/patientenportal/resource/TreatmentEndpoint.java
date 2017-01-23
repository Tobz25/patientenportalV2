package patientenportal.resource;

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
import patientenportal.model.Treatment;
import patientenportal.service.TreatmentService;

@Secured
@Produces(MediaType.APPLICATION_JSON)
public class TreatmentEndpoint {
	@Context
	SecurityContext securityContext;
	TreatmentService treatmentService = new TreatmentService();
	
	@GET
	public Set<Treatment> getAllTreatments(@PathParam ("caseFileId") long caseFileId){
		return treatmentService.getTreatments(caseFileId);
	}
	
	@GET
	@Path("/{treatmentId}")
	public Treatment getTreatment(@PathParam("treatmentId") long treatmentId){
		return treatmentService.getTreatmentById(treatmentId);
	}
	
	
	//TODO: Funktioniert halt noch nicht, treatmentService.addTreatment gibt null zurück
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Treatment addTreatment(Treatment treatment){
		return treatmentService.addTreatment(treatment);
	}
	
	@Path("/{treatmentId}/medicationPrescription")
	public MedicationPrescriptionEndpoint getMedicationPrescription(){
		return new MedicationPrescriptionEndpoint();
	}

}
