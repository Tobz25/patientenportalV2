package patientenportal.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import patientenportal.MyResource;
import patientenportal.model.CaseFile;
import patientenportal.model.Patient;
import patientenportal.model.PatientFile;
import patientenportal.model.User;
import patientenportal.resource.AuthenticationEndpoint;
import patientenportal.resource.HibernateEndpoint;
import patientenportal.resource.UserEndpoint;

public class SetUpTest extends JerseyTest {
	
	private long userId;
	private long patientId;
	private long patientFileId;
	private User testUser;
	private Patient testPatient;
	private PatientFile testPatientFile;
	
	@Override
	protected Application configure() {
	    return new ResourceConfig(AuthenticationEndpoint.class, HibernateEndpoint.class, UserEndpoint.class);
	}
	
	@Before
	public void doBefore(){
		/*//create new User and store UserId
        User user = new User();
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
        System.out.println("new User: "+testUser.toString());
        
        //create new Patient ans casefile for created User
        Patient patient = new Patient();
	    patient.setUser(testUser);
	    
        Response responsePatient = target("patients")
        							.request()
        							.post(Entity.entity(user, MediaType.APPLICATION_FORM_URLENCODED));
        testPatient=responsePatient.readEntity(Patient.class);        
         add caseFile to PatientFile
         * testPatientFile=testPatient.getPatientFile();
        Set caseFiles = new HashSet();
	    caseFiles.add(new CaseFile());
	    testPatientFile.setCaseFiles(caseFiles);
        patientId=testPatient.getId();
        System.out.println("new Patient: "+testPatient.toString());
        */

		//call Test Set-up on HibernateEndpoint
		Response response = target("/hibernate/testdaten")
				.request()
				.get();
		System.out.println(response.toString());
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
