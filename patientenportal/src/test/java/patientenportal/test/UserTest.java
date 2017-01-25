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

import patientenportal.MyResource;
import patientenportal.resource.AuthenticationEndpoint;


public class UserTest extends JerseyTest {

	private String token;
	
	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, MyResource.class);
	}
	
	
	/*
	 * Überprüft, ob Token zurückgegeben wird.
	 */
	
	@Before
	public void test() {
		Form form = new Form();
		form.param("username", "haku");
		form.param("password", "haku");
		
		Response response = target("authentication").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        token = response.readEntity(String.class);
		System.out.println("Test erg:" + response.toString());
		System.out.println("Token: " + token + " - " + token.length());
        assertNotNull(response);
        assertTrue(token.length() == 26);
	}
	
	
	@Test
	public void  testAccessMyResource() {
		Response response = target("myresource")
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		//String x = String.valueOf(response);
		String answer = response.readEntity(String.class);
		System.out.println("answer: "+ answer);
		//System.out.println("Antwort:" + answer + " - " + x);
		System.out.println("Status: " + response.getStatus());
		//Erwartet: Status 404, da SecurityFilter blockt
		
		assertTrue(answer == "Got it!");
		
	}
	
	/*
	@Test
	public void testHibernateAccess() {
		Response response = target("hibernate/access").request().get();
		String answer = response.readEntity(String.class);
		System.out.println("Antwort:" + answer);
		assertNull(answer);
	}*/

}
