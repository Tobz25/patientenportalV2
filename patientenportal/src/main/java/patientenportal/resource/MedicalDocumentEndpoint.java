package patientenportal.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import patientenportal.helper.Secured;
import patientenportal.model.MedicalDocument;
import patientenportal.model.VitalDate;
import patientenportal.service.GenericDeleteService;
import patientenportal.service.MedicalDocumentService;


@Secured
@Produces(MediaType.APPLICATION_JSON)
public class MedicalDocumentEndpoint {
	@Context
	SecurityContext securityContext;
	MedicalDocumentService MedicalDocumentService = new MedicalDocumentService();
	
	@GET
	public List<MedicalDocument> MedicalDocumentFiles(){
		return MedicalDocumentService.getMedicalDocument();
	}
	
	@GET
	@Path("/{MedicalDocumentId}")
	public MedicalDocument getMedicalDocumentById(@PathParam("MedicalDocumentId") long MedicalDocumentId) {
		return MedicalDocumentService.getMedicalDocumentById(MedicalDocumentId);
	}
	
	@POST
	public MedicalDocument addMedicalDocument(MedicalDocument MedicalDocument) {
		return MedicalDocumentService.addMedicalDocument(MedicalDocument);
	}
	
	@DELETE
	public void deleteMedicalDocumentById(@PathParam("MedicalDocumentId") long MedicalDocumentId){
		GenericDeleteService.genericDelete(MedicalDocumentId);
	}
}
