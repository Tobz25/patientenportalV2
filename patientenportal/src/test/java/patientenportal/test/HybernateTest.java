package patientenportal.test;

import static org.junit.Assert.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import patientenportal.resource.*;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

public class HybernateTest extends JerseyTest{

	@Override
	protected Application configure(){
		return new ResourceConfig(HibernateEndpoint.class);
	}
	
	
	@Test
	public void testAccess() {
		Response response = target("hibernate/access").request().get();
		String answer = response.readEntity(String.class);
		System.out.println("Antwort:" + answer);
		assertNull(answer);
	}

}
