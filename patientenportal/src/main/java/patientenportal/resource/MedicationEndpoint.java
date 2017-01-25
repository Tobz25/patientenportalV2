package patientenportal.resource;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import patientenportal.helper.Secured;
import patientenportal.model.Medication;
import patientenportal.service.MedicationService;

@Secured
@Produces(MediaType.APPLICATION_JSON)
public class MedicationEndpoint {
	MedicationService medicationService = new MedicationService();
	
	@GET
	public Set<Medication> getMedications(@PathParam("medicationPrescriptionId") long medicationPrescriptionId){
		return medicationService.getMedications(medicationPrescriptionId);
	}
	
	@GET
	@Path("/{medicationId}")
	public Medication getMedication(@PathParam("medicationId") long medicationId){
		return medicationService.getMedication(medicationId);
	}
	

}
