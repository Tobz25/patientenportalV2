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

import patientenportal.model.Doctor;
import patientenportal.model.MedicalDocument;
import patientenportal.resource.AuthenticationEndpoint;
import patientenportal.resource.MedicalDocumentEndpoint;

public class MedicalDocumentTest extends JerseyTest{
	private String token;
	private long patientID=11;
	private long patientFileID=12;
	private long documentsID=37;
	
	
	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, MedicalDocumentEndpoint.class);
	}
	
	@Before
	public void doBefore(){
	//login 
		Form form = new Form();
		form.param("username", "max");
		form.param("password", "max");
		Response response = target("authentication/login")
						.request()
						.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		token = response.readEntity(String.class);
		System.out.println("Test erg:" + response.toString());
		System.out.println("Token: " + token + " - " + token.length());
        assertNotNull(response);
        assertTrue(token.length() == 26); 		
		
	}

	@After
	public void tearDownChild() throws Exception {
		//logout
		Response logoutResponse = target("authentication/logout")
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		System.out.println(logoutResponse.toString());
	}

	@Test
	public void testGetDocument() {
		Response response = target("/patients/"+patientID+"/patientFile/"+patientFileID+"/documents/"+documentsID)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		assertTrue(response.readEntity(MedicalDocument.class).getId()==documentsID);
	
	}
	
	@Test
	public void testFalse() {
		Response response = target("/patients/"+patientID+1+"/patientFile/"+patientFileID+"/documents/"+documentsID)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		MedicalDocument temp = response.readEntity(MedicalDocument.class);
		assertNull(temp);
	}

}
