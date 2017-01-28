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
import patientenportal.model.User;
import patientenportal.resource.AuthenticationEndpoint;
import patientenportal.resource.HibernateEndpoint;
import patientenportal.resource.UserEndpoint;


public class UserTest extends JerseyTest {

	private String token;
	private User testUser;
	private long userId;
	
	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, MyResource.class, HibernateEndpoint.class, UserEndpoint.class);
	}
	
	
	/*
	 * Überprüft, ob Token zurückgegeben wird.
	 */
	
	@Before
	public void doBefore() {
		
		 /*
		  * --> create new user through HibernateEndpoint
		  * User user = new User();
	        user.setUsername("haku");
	        user.setPassword("haku");
	        user.setEmailaddress("test.test@test.de");
	        user.setFirstname("Hagen");
	        user.setLastname("Kuhn");
	        user.setSalutation("Herr");
	        Response responseUser = target("/hibernate/setuser")
	        							.request()
	        							.post(Entity.entity(user, MediaType.APPLICATION_FORM_URLENCODED));
	        testUser = responseUser.readEntity(User.class);
	        userId=testUser.getId();
	        System.out.println("new User: "+testUser.toString());*/
		
		
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
        
        //create new user with existing user and securitytoken
        User user = new User();
        user.setUsername("haku1");
        user.setPassword("haku1");
        user.setEmailaddress("test.test@test.de");
        user.setFirstname("Hagen");
        user.setLastname("Kuhn");
        user.setSalutation("Herr");
        Response responseUser = target("users")
        							.request()
        							.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        							.post(Entity.entity(user, MediaType.APPLICATION_JSON));
        testUser = responseUser.readEntity(User.class);
        userId=testUser.getId();
        System.out.println("new User: "+testUser.toString());
        
	}
	
	
	//test if given user matches self-created user
	@Test
	public void testAccessUser(){
		Response response = target("user/"+userId)
								.request()
								.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
								.get();
		System.out.println(response.readEntity(User.class).toString());
		assertTrue(response.readEntity(User.class).getId()==userId);
	}
	

}
