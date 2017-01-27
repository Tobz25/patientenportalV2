package patientenportal.service;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import patientenportal.dao.CaseFileDAOImpl;
import patientenportal.dao.DoctorDAOImpl;
import patientenportal.dao.PatientDAOImpl;
import patientenportal.dao.PatientFileDAOImpl;
import patientenportal.dao.RelativeDAOImpl;
import patientenportal.dao.TreatmentDAOImpl;
import patientenportal.dao.UserDAOImpl;
import patientenportal.dao.WebSessionDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.CaseFile;
import patientenportal.model.Doctor;
import patientenportal.model.Patient;
import patientenportal.model.PatientFile;
import patientenportal.model.Relative;
import patientenportal.model.Treatment;
import patientenportal.model.User;
import patientenportal.model.WebSession;

/*
 * Ziel ist es, Grundinhalte in Hibernate Tabellen anzulegen.
 * */

public class FirstTableCreationService {
	
	//SessionService sessionService = new SessionService();
	//public static DAOFactory daoFabrik = DAOFactory.instance(DAOFactory.HIBERNATE);
	//public UserDAO ndao = daoFabrik.getUserDAO();
	//public SessionDAO sdao;
	

	public boolean FirstAttempt() {
		try 
		{
			UserDAOImpl userdao = new UserDAOImpl();
			PatientDAOImpl  pdao = new PatientDAOImpl();
			PatientFileDAOImpl pfdao = new PatientFileDAOImpl();
			CaseFileDAOImpl cdao = new CaseFileDAOImpl();
			RelativeDAOImpl rdao = new RelativeDAOImpl();
			DoctorDAOImpl drdao = new DoctorDAOImpl();
			TreatmentDAOImpl tdao = new TreatmentDAOImpl();
			
			
			User admin = userdao.addEntityAndReturn(newUser("Admin", "Admin", "Herr", "admin@admin.de", "admin", "admin"));
			User haku = userdao.addEntityAndReturn(newUser("haku", "haku", "Herr", "haku@haku.de", "haku", "haku"));
			User maxmustermann = userdao.addEntityAndReturn(newUser("Max", "Mustermann", "Herr", "m.m@test.de", "max", "max"));
			User miamusterfrau = userdao.addEntityAndReturn(newUser("Mia", "Musterfrau", "Frau", "mia.m@test.de", "mia", "mia"));
			User drcox = userdao.addEntityAndReturn(newUser("Percival", "Cox", "Herr Dr", "p@cox.de", "pcox", "flachzange"));
			User drhouse = userdao.addEntityAndReturn(newUser("Gregory", "House", "Herr Dr", "g.house@test.de", "ghouse", "1234456789"));
			User drfrankenstein = userdao.addEntityAndReturn(newUser("Michael", "Frankenstein", "Herr Dr", "m@frankenstein.de", "mfrankenstein", "password"));
			User omaerna = userdao.addEntityAndReturn(newUser("Erna", "Müller", "Frau Oma", "erna@oma.de", "omaerna", "meinpassword"));
			
			//Patienten anlegen
			Patient pmax = pdao.addEntityAndReturn(new Patient());
			PatientFile pfmax = pfdao.addEntityAndReturn(new PatientFile());
			pmax.setPatientFile(pfmax);
			pfmax.setPatient(pmax);
			pfdao.updateEntity(pfmax);
			maxmustermann.addUserRole(pmax);
			maxmustermann.setActiveUserRole(pmax);
			
			Patient pmia = pdao.addEntityAndReturn(new Patient());
			PatientFile pfmia = pfdao.addEntityAndReturn(new PatientFile());
			pmia.setPatientFile(pfmia);
			pfmia.setPatient(pmia);
			pfdao.updateEntity(pfmia);
			miamusterfrau.addUserRole(pmia);
			miamusterfrau.setActiveUserRole(pmia);
			
			
			//Doktoren anlegen
			Doctor drcoxdoctor = drdao.addEntityAndReturn(new Doctor()); 
			drcox.addUserRole(drcoxdoctor);
			drcox.setActiveUserRole(drcoxdoctor);
			Doctor drhousedoctor = drdao.addEntityAndReturn(new Doctor());
			drhouse.addUserRole(drhousedoctor);
			drhouse.setActiveUserRole(drhousedoctor);
			
			Doctor drfrankensteindoctor = drdao.addEntityAndReturn(new Doctor());
			drfrankenstein.addUserRole(drfrankensteindoctor);
			drfrankenstein.setActiveUserRole(drfrankensteindoctor);
			
			//Oma erna ist Angehörige
			Relative romaerna = rdao.addEntityAndReturn(new Relative());
			omaerna.addUserRole(romaerna);
			omaerna.setActiveUserRole(romaerna);
			
			//Behandlungsfälle anlegen
			CaseFile c1 = cdao.addEntityAndReturn(new CaseFile());
			c1.setDiagnose("Tumor im Hirn");
			cdao.updateEntity(c1);
			pfmax.addCaseFile(c1);
			
			CaseFile c2 = cdao.addEntityAndReturn(new CaseFile());
			c2.setDiagnose("Diabetis");
			cdao.updateEntity(c2);
			pfmax.addCaseFile(c2);
			pfdao.updateEntity(pfmax);
			
			CaseFile c3 = cdao.addEntityAndReturn(new CaseFile());
			c3.setDiagnose("Keuchhusten");
			cdao.updateEntity(c3);
			pfmia.addCaseFile(c3);
			pfdao.updateEntity(pfmia);
			
			//Behandlungen anlegen
			Treatment tkeuchhusten = tdao.addEntityAndReturn(new Treatment());
			tkeuchhusten.setDescription("Hormonschlucktherapie");
			tkeuchhusten.setDoctor(drcoxdoctor);
			tdao.updateEntity(tkeuchhusten);
			
			c3.addTreatment(tkeuchhusten);
			cdao.updateEntity(c3);
			
			
			userdao.updateEntity(maxmustermann);
			userdao.updateEntity(miamusterfrau);
			userdao.updateEntity(drcox);
			userdao.updateEntity(drhouse);
			userdao.updateEntity(drfrankenstein);
			userdao.updateEntity(omaerna);
		}
		catch(Exception ex) {
			System.out.println("Fehler beim Anlegen von Testdaten: " + ex.getMessage());
			return false;
		}
		return true;
	}
	
	public User newUser(String firstname, String lastname, String salutation, 
			String email, String username, String pw) throws Exception{
		User u = new User();
		u.setFirstname(firstname);
		u.setLastname(lastname);
		u.setSalutation(salutation);
		u.setEmailaddress(email);
		u.setPassword(pw);
		u.setUsername(username);
		return u;
	}
	
	
}