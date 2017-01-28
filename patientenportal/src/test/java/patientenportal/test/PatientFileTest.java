package patientenportal.test;

import static org.junit.Assert.*;


import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import patientenportal.model.PatientFile;
import patientenportal.resource.AuthenticationEndpoint;
import patientenportal.resource.PatientFileEndpoint;

public class PatientFileTest extends JerseyTest{
	
	private String token="";
	private long patientID=9;
	private long patientFileID=10;
	
	@Override
	protected Application configure(){
		return new ResourceConfig(PatientFileEndpoint.class, AuthenticationEndpoint.class);
	}
	
	@Before
	public void doBefore(){
		Form form = new Form();
		form.param("username", "max");
		form.param("password", "max");			
		Response response = target("authentication/login").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
	    token = response.readEntity(String.class);
	    System.out.println("Used Token: "+ token);
	}

	@Test
	public void testGetPatientFile() {
		Response response = target("/patients/"+patientID+"/patientFile")
								.request()
								.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
								.get();
		PatientFile file = response.readEntity(PatientFile.class);
		assertTrue(file.getId()==patientFileID);
		String answer = file.toString();
		System.out.println("Antwort: " + answer);
	}

}
