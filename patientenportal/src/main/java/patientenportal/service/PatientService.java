package patientenportal.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import patientenportal.dao.PatientDAOImpl;
import patientenportal.dao.RelativeDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.Patient;
import patientenportal.model.Relative;

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
