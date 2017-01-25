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
	
	private String rightToken="";
	
	@Override
	protected Application configure(){
		return new ResourceConfig(PatientFileEndpoint.class, AuthenticationEndpoint.class);
	}
	
	@Before
	public void doBefore(){
		Form form = new Form();
		form.param("username", "haku");
		form.param("password", "haku");			
		Response response = target("authentication").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
	    rightToken = response.readEntity(String.class);
	    System.out.println("Used Token: "+ rightToken);
	}

	@Test
	public void test() {
		Response response = target("/patients/0/patientFile/0")
								.request()
								.header(HttpHeaders.AUTHORIZATION, "Bearer " + rightToken)
								.get();
		PatientFile file = response.readEntity(PatientFile.class);
		String answer = file.toString();
		System.out.println("Antwort: " + answer);
		
		
	}

}
