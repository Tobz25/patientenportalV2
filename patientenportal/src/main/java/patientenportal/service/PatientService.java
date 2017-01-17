package patientenportal.service;

import java.util.List;

import patientenportal.dao.PatientDAOImpl;
import patientenportal.model.Patient;

public class PatientService {
	
	public Patient getPatientById(long patientId){
		PatientDAOImpl pdi = new PatientDAOImpl();
		List<Patient> patients = pdi.getAll();
		for (Patient p : patients) {
			if (p.getId() == patientId){
				return p;
			}
		}
		return null;
	}	
	
}
