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

import patientenportal.resource.AuthenticationEndpoint;
import patientenportal.resource.MedicalDocumentEndpoint;

public class MedicalDocumentTest extends JerseyTest{
	private String token;
	
	
	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, MedicalDocumentEndpoint.class);
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

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
	//logout
			Response logoutResponse = target("authentication/logout")
					.request()
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
					.get();
			System.out.println(logoutResponse.toString());
	}

}
