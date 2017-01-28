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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import patientenportal.model.CaseFile;
import patientenportal.resource.AuthenticationEndpoint;
import patientenportal.resource.CaseFileEndpoint;

public class CaseFileTest extends JerseyTest{

	private String token;
	long patientId = 9;
	long patientFileId = 10;
	long caseFileId = 17;
	
	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, CaseFileEndpoint.class);
	}
	
	
	/*
	 * Überprüft, ob Token zurückgegeben wird.
	 */
	
	@Before
	public void doBefore() {
		Form form = new Form();
		form.param("username", "max");
		form.param("password", "max");
		
		Response response = target("authentication/login").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        token = response.readEntity(String.class);
		System.out.println("Test erg:" + response.toString());
		System.out.println("Token: " + token + " - " + token.length());
        assertNotNull(response);
        //assertTrue(token.length() == 26);
	}
	
	
	
	@Test
	public void testGetCaseFile() {
		//test get: "Tumor im Hirn"
		Response response = target("/patients/"+patientId+"/patientFile/"+patientFileId+"/caseFiles/"+caseFileId)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		CaseFile file = response.readEntity(CaseFile.class);
		System.out.println("Serverantwort: "+response.toString());
		String answer = file.toString();
		System.out.println("Antwort: " + answer);
		assertTrue(file.getId()==caseFileId);
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
