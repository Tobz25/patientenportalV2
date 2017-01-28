package patientenportal.resource;

import java.util.List;
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
import patientenportal.model.CaseFile;
import patientenportal.model.PatientFile;
import patientenportal.model.Role;
import patientenportal.model.User;
import patientenportal.service.CaseFileService;
import patientenportal.service.PermissionService;


@Secured
@Produces(MediaType.APPLICATION_JSON)
public class CaseFileEndpoint {
	CaseFileService CaseFileService = new CaseFileService();
	PermissionService permissionService = new PermissionService();
	
	@GET
	@Path("/")
	@Secured({Role.Patient})
	public Set<CaseFile> getCaseFiles(@PathParam("patientFileId") long patientFileId, @Context SecurityContext securityContext){
		User user = (User) securityContext.getUserPrincipal();
		Set<CaseFile> caseFiles = CaseFileService.getCaseFiles(patientFileId);
		for(CaseFile cs: caseFiles) {
			if (!permissionService.checkReadPermission(user.getActiveUserRole().getId(), cs.getId())) {
				throw new UnauthorizedException("User does not have access to the requested ressource");
			}
		}
		return caseFiles;
	}
		
			
	@GET
	@Path("/{caseFileId}")
	public CaseFile getCaseFile(@PathParam("caseFileId") long caseFileId, @Context SecurityContext securityContext) {
		User user = (User) securityContext.getUserPrincipal();
		
		if (permissionService.checkReadPermission(user.getActiveUserRole().getId(), caseFileId)){
			return CaseFileService.getCaseFileById(caseFileId);
		}
		else{
			throw new UnauthorizedException("User does not have access to the requested ressource");
		}
	}
	
	@POST
	@Path("/")
	@Secured({Role.Doctor})
	@Consumes(MediaType.APPLICATION_JSON)
	public CaseFile createCaseFile(CaseFile caseFile, @PathParam("patientFileId") long patientFileId){
		return CaseFileService.createCaseFile(caseFile);
	}
	
	@Path("/{caseFileId}/treatments")
	public TreatmentEndpoint getTreatmentsEndpoint(){
		return new TreatmentEndpoint();
	}
	
	
	@Path("/{caseFileId}/vitalData")
	public VitalDataEndpoint getVitalData(){
		return new VitalDataEndpoint();
	}
	
	@Path("/{CaseFileId}/documents")
	public MedicalDocumentEndpoint getMedicalDocumentEndpoint(){
		return new MedicalDocumentEndpoint();
	}
}
