package patientenportal.test;

import static org.junit.Assert.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import patientenportal.model.User;
import patientenportal.resource.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HybernateTest extends JerseyTest{


	private String token;
	private long userId=2;
	
	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, UserEndpoint.class);
	}
	
	
	/*
	 * Überprüft, ob Token zurückgegeben wird.
	 */
	
	@Before
	public void doBefore() {
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
	
	
	//test if given user matches self-created user
	@Test
	public void testAccessUser(){
		Response response = target("users/"+userId)
								.request()
								.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
								.get();
		User temp = response.readEntity(User.class);
		assertTrue(temp.getId()==userId);
		System.out.println("User: "+temp.toString());
		
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
