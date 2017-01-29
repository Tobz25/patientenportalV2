package patientenportal.resource;
/*
 * Service-Schnittstelle für alle Operationen, die eine Patientenakte betreffen
 */
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
import patientenportal.model.Patient;
import patientenportal.model.PatientFile;
import patientenportal.model.PermissionType;
import patientenportal.model.Role;
import patientenportal.model.User;
import patientenportal.model.UserGroup;
import patientenportal.service.PatientFileService;
import patientenportal.service.PatientService;
import patientenportal.service.PermissionService;
import patientenportal.service.UserGroupService;

@Secured
@Produces(MediaType.APPLICATION_JSON)
public class PatientFileEndpoint {
	PatientFileService patientFileService = new PatientFileService();
	PermissionService permissionService = new PermissionService();
	UserGroupService usergroupService = new UserGroupService();
	
	
	@GET
	@Secured({Role.Patient})
	@Path("/")
	public PatientFile getPatientFile(@PathParam("patientId") long patientId, @Context SecurityContext securityContext){
		User user = (User) securityContext.getUserPrincipal();
		PatientFile patientFile = patientFileService.getPatientFile(patientId);
		if (permissionService.checkReadPermission(user.getActiveUserRole().getId(), patientFile.getId())){
			return patientFile;
		}
		else{
			throw new UnauthorizedException("User does not have access to the requested ressource");
		}
	}
	
	@GET
	@Path("/{patientFileId}")
	public PatientFile getPatientFileById(@PathParam("patientFileId") long patientFileId,
										  @Context SecurityContext securityContext){
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
	
	@POST
	@Path("/{patientFileId}/setPermission")
	@Secured({Role.Patient})
	public Response setPermission(@PathParam("patientFileId") long patientFileId,
								  @QueryParam("user") long userGroupId,
								  @QueryParam("permission") PermissionType permission,
								  @Context SecurityContext securityContext){
		User user = (User) securityContext.getUserPrincipal();
		UserGroup uGroup = usergroupService.getUserGroupById(userGroupId);
		PatientFile patientFile = patientFileService.getPatientFileById(patientFileId);
		return permissionService.addPermission(patientFile, (Patient)user.getActiveUserRole(), uGroup, permission);
	}

	@Path("/{patientFileId}/documents")
	public MedicalDocumentEndpoint getMedicalDocumentEndpoint(){
		return new MedicalDocumentEndpoint();
	}
}
