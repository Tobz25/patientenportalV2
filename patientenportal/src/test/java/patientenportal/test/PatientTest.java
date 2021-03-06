package patientenportal.test;
import patientenportal.resource.AuthenticationEndpoint;

import static org.junit.Assert.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import patientenportal.model.Patient;
import patientenportal.resource.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PatientTest extends JerseyTest{
	
	String token;
	private long patientId=11;

	@Override
	protected Application configure(){
		return new ResourceConfig(PatientEndpoint.class, AuthenticationEndpoint.class);
	}
	
	@Before
	public void doBefore(){
	//anmelden
		Form form = new Form();
		form.param("username", "max");
		form.param("password", "max");
		Response response = target("authentication/login").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
	    token = response.readEntity(String.class);
	    System.out.println("Used Token: "+ token);
	}
	
	@Test
	public void  testGetPatient() {
		Response response = target("patients/"+patientId)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		
		Patient patient = response.readEntity(Patient.class); 
		String answer = patient.getUser().toString();
		System.out.println("User "+ answer);
		assertTrue(patient.getId()==patientId);
	}
	
	@Test
	public void  testFalse() {
		Response response = target("patients/"+patientId+1)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		
		Patient patient = response.readEntity(Patient.class); 
		assertNull(patient);
	}
	
	@After
	public void tearDown(){
		//logout
		Response logoutResponse = target("authentication/logout")
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		System.out.println(logoutResponse.toString());
	}
	
}
