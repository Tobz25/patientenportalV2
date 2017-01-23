package patientenportal.test;
import patientenportal.resource.AuthenticationEndpoint;

import static org.junit.Assert.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
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
	
	String rightToken = "";

	@Override
	protected Application configure(){
		return new ResourceConfig(PatientEndpoint.class, AuthenticationEndpoint.class);
	}
	
	@Before
	public void doBefore(){
	//anmelden
		Form form = new Form();
		form.param("username", "haku");
		form.param("password", "haku");			
		Response response = target("authentication").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
	    rightToken = response.readEntity(String.class);
	    System.out.println("Used Token: "+ rightToken);
	}
	
	@Test
	public void  testGetPatient() {
		Response response = target("patients/1")
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + rightToken)
				.get();
		
		Patient patient = response.readEntity(Patient.class); 
		String answer = patient.getUser().toString();
		System.out.println("User "+ answer);
		assertFalse(answer.equals(""));
	}
	
}
