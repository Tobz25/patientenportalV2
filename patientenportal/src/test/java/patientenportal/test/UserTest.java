package patientenportal.test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import patientenportal.MyResource;
import patientenportal.resource.AuthenticationEndpoint;


public class UserTest extends JerseyTest {

	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, MyResource.class);
	}
	
	
	/*
	 * Überprüft, ob Token zurückgegeben wird.
	 */
	
	@Test
	public void test() {
		Form form = new Form();
		form.param("username", "haku");
		form.param("password", "haku");
		
		Response response = target("authentication").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        String output = response.readEntity(String.class);
		System.out.println("Test erg:" + response.toString());
		System.out.println("Token: " + output + " - " + output.length());
        assertNotNull(response);
        assertTrue(output.length() == 26);
	}
	
	
	@Test
	public void  testAccessMyResource() {
		Response response = target("myresource").request().get();
		//String x = String.valueOf(response);
		String answer = response.readEntity(String.class);
		//System.out.println("Antwort:" + answer + " - " + x);
		System.out.println("Status: " + response.getStatus());
		//Erwartet: Status 404, da SecurityFilter blockt
		
		assertFalse(answer == "Got it!");
		
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
