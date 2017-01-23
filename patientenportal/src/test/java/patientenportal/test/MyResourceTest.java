package patientenportal.test;

import static org.junit.Assert.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import patientenportal.MyResource;
import patientenportal.resource.AuthenticationEndpoint;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MyResourceTest extends JerseyTest{

	String rightToken = ""; //Token with right credentials
	
	@Override
	protected Application configure(){
		return new ResourceConfig(MyResource.class, AuthenticationEndpoint.class);
	}
	
	
	@Before
	public void doBefore() {
		//anmelden
		Form form = new Form();
		form.param("username", "haku");
		form.param("password", "haku");
		
		Response response = target("authentication").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
        String output = response.readEntity(String.class);
		System.out.println("Verwendeter Token:" + output);
		rightToken = output;
	}
	
	@Test
	public void  testAccessMyResourceRight() {
		assertFalse(rightToken == "");
		
		Response response = target("myresource")
				.request()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + rightToken)
				.get();
		String answer = response.readEntity(String.class);
		System.out.println("Antwort: " + answer);
		System.out.println("Response: " + response.toString());
		
		Assert.assertTrue(answer.equals("Got it!"));

	}
	
	@Test
	public void  testAccessMyResourceWrong() {
		assertFalse(rightToken == "");
		
		Response response = target("myresource").request().header(HttpHeaders.AUTHORIZATION, "Bearer " + rightToken).get();
		String answer = response.readEntity(String.class);
		System.out.println("Antwort: " + answer);
		System.out.println("Response: " + response.toString());
		
		Assert.assertTrue(answer.equals("Got it!"));

	}
	@After
	public void doAfter() {
		//abmelden
	}
	
}
