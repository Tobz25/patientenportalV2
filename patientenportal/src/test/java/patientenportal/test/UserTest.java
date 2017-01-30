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

import patientenportal.model.User;
import patientenportal.resource.AuthenticationEndpoint;
import patientenportal.resource.UserEndpoint;


public class UserTest extends JerseyTest {

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
		form.param("username", "haku");
		form.param("password", "haku");
		
		Response response = target("authentication/login")
				.request()
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        token = response.readEntity(String.class);
		System.out.println("Test erg:" + response.toString());
		System.out.println("Token: " + token + " - " + token.length());
        assertNotNull(response);
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
	
	@Test
	public void testFalse(){
		Response response = target("users/"+userId+1)
								.request()
								.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
								.get();
		User temp = response.readEntity(User.class);
		assertNull(temp);
		
	}
	
	@After
	public void tearDownChild(){
		//logout
		Response logoutResponse = target("authentication/logout")
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.get();
		System.out.println(logoutResponse.toString());
	}
	

}
