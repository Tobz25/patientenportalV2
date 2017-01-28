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

import patientenportal.model.Medication;
import patientenportal.resource.AuthenticationEndpoint;
import patientenportal.resource.MedicationEndpoint;

public class MedicationTest extends JerseyTest{
	private String token;
	private long patientID;
	private long caseFileID;
	private long treatmentID;
	private long medicationID;
	
	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, MedicationEndpoint.class);
	}

	@Before
	public void doBefore(){
	//login 
		Form form = new Form();
		form.param("username", "haku");
		form.param("password", "haku");
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
	public void testGetMedication() {
		Response response = target("/patients/"+patientID+"/patientFile/caseFiles/"+caseFileID+"/treatments/"+treatmentID+"/medicationPrescription/medication/"+medicationID)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		Medication temp = response.readEntity(Medication.class);
		assertTrue(temp.getId()==medicationID);
		System.out.println("Medication: "+temp.toString());
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
