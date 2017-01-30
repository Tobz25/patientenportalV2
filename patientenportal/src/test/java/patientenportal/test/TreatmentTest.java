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
import patientenportal.model.Treatment;
import patientenportal.resource.AuthenticationEndpoint;
import patientenportal.resource.CaseFileEndpoint;
import patientenportal.resource.PatientEndpoint;
import patientenportal.resource.PatientFileEndpoint;
import patientenportal.resource.TreatmentEndpoint;

public class TreatmentTest extends JerseyTest{
	
	private long treatmentID=22;
	private long patientID=11;
	private long patientFileID=12;
	private long caseFileID=19;
	private String token;
	
	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, TreatmentEndpoint.class, PatientEndpoint.class, PatientFileEndpoint.class, CaseFileEndpoint.class);
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
	

	@Test
	public void testGetTreatment() {
		String pfad = "/patients/"+patientID+"/patientFile/"+patientFileID+"/caseFiles/"+caseFileID+"/treatments/"+treatmentID;
		System.out.println("Pfad: "+pfad);
		Response response = target(pfad)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		Treatment treatment = response.readEntity(Treatment.class);
		System.out.println("Serverantwort: "+response.toString());
		assertTrue(treatment.getId()==treatmentID);
	}
	
	@Test
	public void testFalse() {
		String pfad = "/patients/"+patientID+1+"/patientFile/"+patientFileID+"/caseFiles/"+caseFileID+"/treatments/"+treatmentID;
		System.out.println("Pfad: "+pfad);
		Response response = target(pfad)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		Treatment treatment = response.readEntity(Treatment.class);
		assertNull(treatment);
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
