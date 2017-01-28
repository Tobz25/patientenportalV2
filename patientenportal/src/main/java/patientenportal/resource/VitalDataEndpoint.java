package patientenportal.resource;

import java.util.List;

import javax.ws.rs.Consumes;
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
import patientenportal.model.User;
import patientenportal.model.VitalDate;
import patientenportal.service.VitalDataService;

@Secured
@Produces(MediaType.APPLICATION_JSON)
public class VitalDataEndpoint {
	VitalDataService VitalDataService = new VitalDataService();
	
	@GET
	public List<VitalDate> getVitalData(){
		return VitalDataService.getVitalData();
	}
	
	@GET
	@Path("/{VitalDataId}")
	public VitalDate getCaseFile(@PathParam("VitalDataId") long VitalDataId) {
		return VitalDataService.getVitalDataById(VitalDataId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public VitalDate addVitalDate(VitalDate VitalDate){
		return VitalDataService.addVitalDate(VitalDate);		
	}
	
	@DELETE
	@Path("/{VitalDataId}")
	public void deleteVitalDateById(@PathParam("VitalDataId") long VitalDataId){
		VitalDataService.deleteVitalDate(VitalDataId);		
	}
}