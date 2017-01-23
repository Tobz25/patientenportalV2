package patientenportal.service;

import java.util.List;

import patientenportal.dao.PatientDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.Patient;

public class PatientService {
	
	public List<Patient> getPatients(){
		PatientDAOImpl pdi = new PatientDAOImpl();
		List<Patient> patients = pdi.getAll();
		if(patients.size()>0){
			return patients;
		}
		throw new DataNotFoundException("No patients found");

	}
	
	public Patient getPatientById(long patientId){
		PatientDAOImpl pdi = new PatientDAOImpl();
		List<Patient> patients = pdi.getAll();
		for (Patient p : patients) {
			if (p.getId() == patientId){
				return p;
			}
		}
		throw new DataNotFoundException("Patient with id " + patientId + " not found");
	}	
	
}
