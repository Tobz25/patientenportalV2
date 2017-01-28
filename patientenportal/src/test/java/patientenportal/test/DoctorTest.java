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
import patientenportal.resource.AuthenticationEndpoint;
import patientenportal.resource.DoctorEndpoint;

public class DoctorTest extends JerseyTest{
	
	private long testId=13;
	private String token;
	
	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, DoctorEndpoint.class);
	}
	
	@Before
	public void doBefore(){
		Form form = new Form();
		form.param("username", "pcox");
		form.param("password", "flachzange");
		
		Response response = target("authentication/login")
							.request()
							.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        token = response.readEntity(String.class);
		System.out.println("Test erg:" + response.toString());
		System.out.println("Token: " + token + " - " + token.length());
	}

	@Test
	public void testGetDoctor() {
		Response response = target("/doctors/"+testId)
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		assertTrue(response.readEntity(Doctor.class).getId()==testId);
	}
	
	@After
	public void doAfter(){
		//clean up
		Response logoutResponse = target("authentication/logout")
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		System.out.println(logoutResponse.toString());
	}

}
