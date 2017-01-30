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

import patientenportal.model.Patient;
import patientenportal.model.VitalDate;
import patientenportal.resource.AuthenticationEndpoint;
import patientenportal.resource.VitalDataEndpoint;

public class VitalDataTest extends JerseyTest{
	
	private String token;
	private String patientId="0";
	private String caseFileId="0";
	private long vitalDateId=0;
	
	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, VitalDataEndpoint.class);
	}
	
	@Before
	public void doBefore(){
		Form form = new Form();
		form.param("username", "haku");
		form.param("password", "haku");
		
		Response response = target("authentication")
				.request()
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        token = response.readEntity(String.class);
		System.out.println("Test erg:" + response.toString());
		System.out.println("Token: " + token + " - " + token.length());
	}

	@After
	public void tearDown() throws Exception {
		//logout
				Response logoutResponse = target("authentication/logout")
						.request()
						.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
						.get();
				System.out.println(logoutResponse.toString());
	}

	@Test
	public void testGetVitalDate() {
		Response response = target("patients/"+patientId+"/patientFile/caseFiles/"+caseFileId+"/vitalData/"+vitalDateId)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		
		VitalDate date = response.readEntity(VitalDate.class); 
		long answer = date.getId();
		System.out.println("Vitaldate: "+ answer);
		assertTrue(answer==vitalDateId);
	}
	
	@Test
	public void testFalse() {
		Response response = target("patients/"+patientId+1+"/patientFile/caseFiles/"+caseFileId+"/vitalData/"+vitalDateId)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		
		VitalDate date = response.readEntity(VitalDate.class); 
		assertNull(date);
	}

}
