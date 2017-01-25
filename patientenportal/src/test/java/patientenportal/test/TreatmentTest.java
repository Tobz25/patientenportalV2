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
import patientenportal.service.TreatmentService;
import patientenportal.MyResource;
import patientenportal.model.Treatment;
import patientenportal.resource.AuthenticationEndpoint;
import patientenportal.resource.TreatmentEndpoint;

public class TreatmentTest extends JerseyTest{
	
	private TreatmentService treatmentService;
	private Treatment treatment;
	private long ID;
	private String token;
	
	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, TreatmentEndpoint.class);
	}

	@Before
	public void doBefore(){
		Form form = new Form();
		form.param("username", "haku");
		form.param("password", "haku");
		
		Response response = target("authentication").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        token = response.readEntity(String.class);
		System.out.println("Test erg:" + response.toString());
		System.out.println("Token: " + token + " - " + token.length());
        
        treatmentService = new TreatmentService();
        treatment = treatmentService.addTreatment(new Treatment());
        //ID = treatment.getId();
        ID=0;
        System.out.println(""+ID);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test 
	public void testPostTreatment(){
		Treatment treatmentTemp;
		treatmentService = new TreatmentService();
        treatment = treatmentService.addTreatment(treatmentTemp = new Treatment());
        assertTrue(treatment == treatmentTemp);
	}

	@Test
	public void testGetTreatment() {
		Response response = target("patients/1/patientFile/caseFiles/1/treatments/"+ID)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		assertTrue(treatment==response.getEntity());
	}

}
