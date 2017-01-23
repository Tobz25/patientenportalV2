package patientenportal.service;

import java.util.List;

import patientenportal.dao.PatientDAOImpl;
import patientenportal.dao.PatientFileDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.Patient;
import patientenportal.model.PatientFile;

public class PatientFileService {
	
	/* Patient liefert keine Methode, um die Patientenakte zu erhalten
	 * 
	public PatientFile getPatientFile(long patientId){
		PatientDAOImpl pdi = new PatientDAOImpl();
		List<Patient> patients = pdi.getAll();
		for (Patient p : patients) {
			if (p.getId() == patientId){
				return p.;
			}
		}
		throw new DataNotFoundException("No patient file found for id " + patientFileId);
	}	*/
	
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
