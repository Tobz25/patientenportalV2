package patientenportal.resource;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;


import patientenportal.helper.Secured;
import patientenportal.model.MedicationPrescription;
import patientenportal.service.MedicationPrescriptionService;

@Secured
@Produces(MediaType.APPLICATION_JSON)
public class MedicationPrescriptionEndpoint {
	@Context
	SecurityContext securityContext;
	MedicationPrescriptionService medicationPrescriptionService = new MedicationPrescriptionService();
	
	@GET	
	public Set<MedicationPrescription> getMedicationPrescription(@PathParam("treatmentId") long treatmentId){
		return medicationPrescriptionService.getMedicationPrescription(treatmentId);
	}

}
