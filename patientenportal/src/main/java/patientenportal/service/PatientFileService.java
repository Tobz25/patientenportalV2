package patientenportal.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import patientenportal.dao.PatientDAOImpl;
import patientenportal.dao.PatientFileDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.Patient;
import patientenportal.model.PatientFile;

public class PatientFileService {
	
	/* Patient liefert keine Methode, um die Patientenakte zu erhalten
	 */ 
	public PatientFile getPatientFile(long patientId){
		PatientDAOImpl pdi = new PatientDAOImpl();
				
		List<Patient> patients = pdi.findByCriteria(Restrictions.eq("id", patientId));
		if (patients.size() == 0) throw new DataNotFoundException("No patient file found for user with id " + patientId);
		
		return patients.get(0).getPatientFile();
		/*List<Patient> patients = pdi.getAll();
		for (Patient p : patients) {
			if (p.getId() == patientId){
				return p.getPatientFile();
			}
		}*/
	}	
	
	public PatientFile getPatientFileById(long patientFileId){
		PatientFileDAOImpl pfdi = new PatientFileDAOImpl();
		List<PatientFile> patientFiles = pfdi.getAll();
		for (PatientFile pf : patientFiles) {
			if (pf.getId() == patientFileId){
				return pf;
			}
		}
		throw new DataNotFoundException("No patient file found for id " + patientFileId);
	}	

}
