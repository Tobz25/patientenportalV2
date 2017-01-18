package patientenportal.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import patientenportal.helper.Secured;
import patientenportal.model.CaseFile;
import patientenportal.service.CaseFileService;


@Secured
@Produces(MediaType.APPLICATION_JSON)
public class CaseFileEndpoint {
	@Context
	SecurityContext securityContext;
	CaseFileService CaseFileService = new CaseFileService();
	
	@GET
	public List<CaseFile> getCaseFiles(){
		return CaseFileService.getCaseFiles();
	}
		
	@GET
	@Path("/{caseFileId}")
	public CaseFile getCaseFile(@PathParam("caseFileId") long caseFileId) {
		return CaseFileService.getCaseFileById(caseFileId);
	}
	
	@Path("/{CaseFileId}/treatments")
	public TreatmentEndpoint getTreatmentsEndpoint(){
		return new TreatmentEndpoint();
	}

}
