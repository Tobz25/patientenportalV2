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
import patientenportal.model.Doctor;
import patientenportal.service.DoctorService;


@Secured
@Path("doctors")
@Produces(MediaType.APPLICATION_JSON)
public class DoctorEndpoint {
	@Context
	SecurityContext securityContext;
	DoctorService docotrService = new DoctorService();
	
	
	@GET 
	List<Doctor> getDoctors(){
		return docotrService.getDoctors();
	}
		
	@GET
	@Path("/{doctorId}")
	public Doctor getDoctor(@PathParam("doctorId") long doctorId) {
		return docotrService.getDoctorById(doctorId);
	}
}
