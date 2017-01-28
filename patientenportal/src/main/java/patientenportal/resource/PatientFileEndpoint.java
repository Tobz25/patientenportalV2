package patientenportal.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import patientenportal.helper.MySecurityContext;
import patientenportal.helper.Secured;
import patientenportal.helper.UnauthorizedException;
import patientenportal.model.CaseFile;
import patientenportal.model.MedicalDocument;
import patientenportal.model.PatientFile;
import patientenportal.model.Role;
import patientenportal.model.User;
import patientenportal.service.PatientFileService;
import patientenportal.service.PatientService;
import patientenportal.service.PermissionService;

@Secured
@Produces(MediaType.APPLICATION_JSON)
public class PatientFileEndpoint {
	@Context
	MySecurityContext securityContext;
	PatientFileService patientFileService = new PatientFileService();
	PermissionService permissionService = new PermissionService();
	
	
	@GET
	@Secured({Role.Patient})
	@Path("/")
	public PatientFile getPatientFile(@PathParam("patientId") long patientId, @Context UriInfo uriInfo){
		PatientFile patientFile = patientFileService.getPatientFile(patientId);
		
		/*String uri = getUriForSelf(uriInfo, patientFile);
		patientFile.addLink(uri, "self");*/
		return patientFile;
	}
	
	@GET
	@Path("/{patientFileId}")
	public PatientFile getPatientFileById(@PathParam("patientFileId") long patientFileId,
										  @Context SecurityContext securityContext){
		//MySecurityContext context = (MySecurityContext) securityContext;
		User user = (User) securityContext.getUserPrincipal();
		if (permissionService.checkReadPermission(user.getActiveUserRole().getId(), patientFileId)){
			return patientFileService.getPatientFileById(patientFileId);
		}
		else{
			throw new UnauthorizedException("User does not have access to the requested ressource");
		}

	}
	
	@Path("/{patientFileId}/caseFiles")
	public CaseFileEndpoint getCaseFileEndpoint(){
		return new CaseFileEndpoint();
	}
	 /*
	private String getUriForSelf(UriInfo uriInfo, PatientFile patientFile){
		String uri = uriInfo.getBaseUriBuilder()
				.path(PatientFile.class)
				.path(Long.toString(patientFile.getId()))
				.build()
				.toString();
		return uri;
	}*/
	
	@POST
	@Path("/{patientFileId}/setPermission")
	public Response setPermission(@PathParam("patientFileId") long patientFileId,
								  @QueryParam("user") long userId,
								  @QueryParam("permission") String permission){
		PatientFile patientFile = patientFileService.getPatientFileById(patientFileId);
		return Response.ok().build();//permissionService.addPermission(patientFile, userId, permission);
	}

	@Path("/{patientFileId}/documents")
	public MedicalDocumentEndpoint getMedicalDocumentEndpoint(){
		return new MedicalDocumentEndpoint();
	}
}
