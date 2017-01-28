package patientenportal.service;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import patientenportal.dao.AdministratorDAOImpl;
import patientenportal.dao.CaseFileDAOImpl;
import patientenportal.dao.DoctorDAOImpl;
import patientenportal.dao.MedicationDAOImpl;
import patientenportal.dao.MedicationIntakeDAOImpl;
import patientenportal.dao.MedicationPrescriptionDAOImpl;
import patientenportal.dao.PatientDAOImpl;
import patientenportal.dao.PatientFileDAOImpl;
import patientenportal.dao.PermissionDAOImpl;
import patientenportal.dao.RelativeDAOImpl;
import patientenportal.dao.TreatmentDAOImpl;
import patientenportal.dao.UserDAOImpl;
import patientenportal.dao.WebSessionDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.Administrator;
import patientenportal.model.CaseFile;
import patientenportal.model.Doctor;
import patientenportal.model.Medication;
import patientenportal.model.MedicationIntake;
import patientenportal.model.MedicationPrescription;
import patientenportal.model.Patient;
import patientenportal.model.PatientFile;
import patientenportal.model.Permission;
import patientenportal.model.PermissionType;
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
			AdministratorDAOImpl adao = new AdministratorDAOImpl();
			PatientDAOImpl  pdao = new PatientDAOImpl();
			PatientFileDAOImpl pfdao = new PatientFileDAOImpl();
			CaseFileDAOImpl cdao = new CaseFileDAOImpl();
			RelativeDAOImpl rdao = new RelativeDAOImpl();
			DoctorDAOImpl drdao = new DoctorDAOImpl();
			TreatmentDAOImpl tdao = new TreatmentDAOImpl();
			MedicationPrescriptionDAOImpl mpdao = new MedicationPrescriptionDAOImpl();
			MedicationDAOImpl mdao = new MedicationDAOImpl();
			MedicationIntakeDAOImpl mindao = new MedicationIntakeDAOImpl();
			PermissionDAOImpl permdao = new PermissionDAOImpl();
			
			User admin = userdao.addEntityAndReturn(newUser("Admin", "Admin", "Herr", "admin@admin.de", "admin", "admin"));
			User haku = userdao.addEntityAndReturn(newUser("haku", "haku", "Herr", "haku@haku.de", "haku", "haku"));
			User maxmustermann = userdao.addEntityAndReturn(newUser("Max", "Mustermann", "Herr", "m.m@test.de", "max", "max"));
			User miamusterfrau = userdao.addEntityAndReturn(newUser("Mia", "Musterfrau", "Frau", "mia.m@test.de", "mia", "mia"));
			User drcox = userdao.addEntityAndReturn(newUser("Percival", "Cox", "Herr Dr", "p@cox.de", "pcox", "flachzange"));
			User drhouse = userdao.addEntityAndReturn(newUser("Gregory", "House", "Herr Dr", "g.house@test.de", "ghouse", "123456789"));
			User drfrankenstein = userdao.addEntityAndReturn(newUser("Michael", "Frankenstein", "Herr Dr", "m@frankenstein.de", "mfrankenstein", "password"));
			User omaerna = userdao.addEntityAndReturn(newUser("Erna", "Müller", "Frau Oma", "erna@oma.de", "omaerna", "meinpassword"));
			
			//Administrator anlegen
			Administrator adminadmin = adao.addEntityAndReturn(new Administrator());
			adminadmin.setUser(admin);
			admin.addUserRole(adminadmin);
			admin.setActiveUserRole(adminadmin);
			adao.updateEntity(adminadmin);
			userdao.updateEntity(admin);
			
			//haku
			Administrator hakuadmin = adao.addEntityAndReturn(new Administrator());
			hakuadmin.setUser(haku);
			haku.addUserRole(hakuadmin);
			haku.setActiveUserRole(hakuadmin);
			adao.updateEntity(hakuadmin);
			userdao.updateEntity(haku);
			
			
			//Patienten anlegen
			Patient pmax = pdao.addEntityAndReturn(new Patient());
			PatientFile pfmax = pfdao.addEntityAndReturn(new PatientFile());
			pmax.setPatientFile(pfmax);
			pfmax.setPatient(pmax);
			pfdao.updateEntity(pfmax);
			pmax.setUser(maxmustermann);
			pdao.updateEntity(pmax);
			maxmustermann.addUserRole(pmax);
			maxmustermann.setActiveUserRole(pmax);
			
			Patient pmia = pdao.addEntityAndReturn(new Patient());
			PatientFile pfmia = pfdao.addEntityAndReturn(new PatientFile());
			pmia.setPatientFile(pfmia);
			pfmia.setPatient(pmia);
			pfdao.updateEntity(pfmia);
			pmia.setUser(miamusterfrau);
			pdao.updateEntity(pmia);
			miamusterfrau.addUserRole(pmia);
			miamusterfrau.setActiveUserRole(pmia);
			
			
			//Doktoren anlegen
			Doctor drcoxdoctor = drdao.addEntityAndReturn(new Doctor()); 
			drcox.addUserRole(drcoxdoctor);
			drcox.setActiveUserRole(drcoxdoctor);
			pmia.addLinkedDoctor(drcoxdoctor);
			drdao.updateEntity(drcoxdoctor);
			
			Doctor drhousedoctor = drdao.addEntityAndReturn(new Doctor());
			drhouse.addUserRole(drhousedoctor);
			drhouse.setActiveUserRole(drhousedoctor);
			pmax.addLinkedDoctor(drhousedoctor);
			pdao.updateEntity(pmax);
			drdao.updateEntity(drhousedoctor);
			
			Doctor drfrankensteindoctor = drdao.addEntityAndReturn(new Doctor());
			drfrankenstein.addUserRole(drfrankensteindoctor);
			drfrankenstein.setActiveUserRole(drfrankensteindoctor);
			pmia.addLinkedDoctor(drfrankensteindoctor);
			pdao.updateEntity(pmia);
			drdao.updateEntity(drfrankensteindoctor);
			
			//Oma erna ist Angehörige
			Relative romaerna = rdao.addEntityAndReturn(new Relative());
			omaerna.addUserRole(romaerna);
			omaerna.setActiveUserRole(romaerna);
			pmax.addLinkedRelative(romaerna);
			
			rdao.updateEntity(romaerna);
			pdao.updateEntity(pmax);
			
			//Behandlungsfälle anlegen
			CaseFile c1 = cdao.addEntityAndReturn(new CaseFile());
			c1.setDiagnose("Tumor im Hirn");
			cdao.updateEntity(c1);
			pfmax.addCaseFile(c1);
			pfdao.updateEntity(pfmax);
			
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
				//Treatment zu c1
			Treatment tstrahlung = tdao.addEntityAndReturn(new Treatment());
			tstrahlung.setDescription("Chemotherapie");
			tstrahlung.setDoctor(drhousedoctor);
			tstrahlung.setCaseFile(c1);
			c1.addTreatment(tstrahlung);
			
			tdao.updateEntity(tstrahlung);
			cdao.updateEntity(c1);
				//Treatment zu c2
			Treatment tinsulin = tdao.addEntityAndReturn(new Treatment());
			tinsulin.setDescription("Insulinbehandlung");
			tinsulin.setDoctor(drhousedoctor);
			Calendar caltins = Calendar.getInstance();
			caltins.add(Calendar.DATE, -12);
			tinsulin.setStartDateTime(caltins.getTime());
			tinsulin.setCaseFile(c2);
			c2.addTreatment(tinsulin);
			
			tdao.updateEntity(tinsulin);
			cdao.updateEntity(c2);
				//Treatment  zu c3
			Treatment tkeuchhusten = tdao.addEntityAndReturn(new Treatment());
			tkeuchhusten.setDescription("Hormonschlucktherapie");
			tkeuchhusten.setDoctor(drcoxdoctor);
			tkeuchhusten.setCaseFile(c3);
			c3.addTreatment(tkeuchhusten);
			tdao.updateEntity(tkeuchhusten);
			cdao.updateEntity(c3);
			
			
			//Medikamentenverschreibung Insulin
				//Medikation
				Medication insulin = mdao.addEntityAndReturn(new Medication());
				insulin.setDrug("Insulin");
				insulin.setDescription("Insulin; Hergestellt von Bayer");
				mdao.updateEntity(insulin);
			
			MedicationPrescription mp = mpdao.addEntityAndReturn(new MedicationPrescription());
			mp.setDoctor(drhousedoctor);
			mp.setDescription("2x Insulin pro Woche");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			mp.setWritingDateTime(cal.getTime());
			mp.addMedication(insulin);
			insulin.addPrescription(mp);
			tinsulin.addPrescription(mp);
			mpdao.updateEntity(mp);
			tdao.updateEntity(tinsulin);
			mdao.updateEntity(insulin);
			
			MedicationIntake min = mindao.addEntityAndReturn(new MedicationIntake());
			Calendar cal2 = Calendar.getInstance();
			cal2.add(Calendar.DATE, -3);
			min.setDateTime(cal2.getTime());
			min.setDescription("Einnahme von vorvorgestern. 3x genommen, Blutzucker nicht schlecht");
			min.setDrug("Insulin");
			min.setPrescription(mp);
			
			tinsulin.addMedicationIntake(min);
			mindao.updateEntity(min);
			tdao.updateEntity(tinsulin);
			
			//Zugriffsberechtigungen 
				//für Nutzer Max an sich selbst WRITE
			Permission p1 = permdao.addEntityAndReturn(new Permission());
			p1.setPermissionType(PermissionType.WRITE);
			p1.addUserGroup(pmax);
			p1.addElements(pfmax);
			p1.addElements(c1);
			p1.addElements(c2);
			p1.addElements(insulin);
			p1.addElements(tinsulin);
			p1.addElements(min);
			p1.addElements(mp);
			p1.addElements(tstrahlung);

			pdao.updateEntity(pmax);
			pfdao.updateEntity(pfmax);
			cdao.updateEntity(c1);
			cdao.updateEntity(c2);
			mdao.updateEntity(insulin);
			tdao.updateEntity(tstrahlung);
			tdao.updateEntity(tinsulin);
			mindao.updateEntity(min);
			mpdao.updateEntity(mp);
			permdao.updateEntity(p1);
			
				//für Nutzer Mia
			Permission p2 = permdao.addEntityAndReturn(new Permission());
			p2.setPermissionType(PermissionType.WRITE);
			p2.addUserGroup(pmia);
			p2.addElements(pfmia);
			p2.addElements(c3);
			p2.addElements(tinsulin);

			pdao.updateEntity(pmax);
			pfdao.updateEntity(pfmax);
			cdao.updateEntity(c3);
			tdao.updateEntity(tinsulin);
			permdao.updateEntity(p2);

				//für drhouse
			
			Permission p3 = permdao.addEntityAndReturn(new Permission());
			p3.setPermissionType(PermissionType.WRITE);
			p3.addUserGroup(drhousedoctor);
			p3.addElements(pmax);
			p3.addElements(pfmax);
			p3.addElements(c1);	//Tumor --> Chemo
			p3.addElements(c2); //Diabetis --> 
			p3.addElements(tstrahlung);
			p3.addElements(tinsulin);

			pdao.updateEntity(pmax);
			pfdao.updateEntity(pfmax);
			cdao.updateEntity(c1);
			cdao.updateEntity(c2);

			tdao.updateEntity(tinsulin);
			permdao.updateEntity(p3);
			
			
			
			
			
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