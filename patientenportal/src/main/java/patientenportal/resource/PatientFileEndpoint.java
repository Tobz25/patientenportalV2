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
import patientenportal.model.CaseFile;
import patientenportal.model.MedicalDocument;
import patientenportal.model.PatientFile;
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
	@Path("/")
	public PatientFile getPatientFile(@PathParam("patientId") long patientId, @Context UriInfo uriInfo){
		PatientFile patientFile = patientFileService.getPatientFileById(patientId);
		
		/*String uri = getUriForSelf(uriInfo, patientFile);
		patientFile.addLink(uri, "self");*/
		return patientFile;
	}
	
	@GET
	@Path("/{patientFileId}")
	public PatientFile getPatientFileById(@PathParam("patientFileId") long patientFileId,
										  @Context SecurityContext securityContext){
		MySecurityContext context = (MySecurityContext) securityContext;
		long userId = context.getUserId();
		if (permissionService.checkReadPermission(loggedInUser, entity))
		Permission hasPermission= permissionService.getPermission();
		if(hasPermission == ""){
			return patientFileService.getPatientFileById(patientFileId);
		}
		else{
			Response unauthorizedStatus = Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("Login failed. Username or password are incorrect.")
					.build();
            return unauthorizedStatus;
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
		return permissionService.setPermission(patientFile, userId, permission);
	}

	@Path("/{patientFileId}/documents")
	public MedicalDocumentEndpoint getMedicalDocumentEndpoint(){
		return new MedicalDocumentEndpoint();
	}
}
