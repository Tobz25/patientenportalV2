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

import patientenportal.model.MedicationPrescription;
import patientenportal.model.User;
import patientenportal.resource.AuthenticationEndpoint;
import patientenportal.resource.MedicationPrescriptionEndpoint;

public class MedicationPrescriptionTest extends JerseyTest{
	
	private String token;
	private long patientID=11;
	private long patientFileID=12;
	private long caseFileID=19;
	private long treatmentID=22;
	private long medicationPrescriptionID=24;
	
	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, MedicationPrescriptionEndpoint.class);
	}
	
	@Before
	public void doBefore(){
	//login 
		Form form = new Form();
		form.param("username", "mia");
		form.param("password", "mia");		
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
	public void testGetMedicationPrescription() {
		Response response = target("/patients/"+patientID+"/patientFile/"+patientFileID+"/caseFiles/"+caseFileID+"/treatments/"+treatmentID+"/medcationPrescription/"+medicationPrescriptionID)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		MedicationPrescription temp = response.readEntity(MedicationPrescription.class);
		assertTrue(temp.getId()==medicationPrescriptionID);
		System.out.println("Medication Prescription: "+temp.toString());
	}
	
	@Test
	public void testFalse() {
		Response response = target("/patients/"+patientID+1+"/patientFile/"+patientFileID+"/caseFiles/"+caseFileID+"/treatments/"+treatmentID+"/medcationPrescription/"+medicationPrescriptionID)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		MedicationPrescription temp = response.readEntity(MedicationPrescription.class);
		assertNull(temp);
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
