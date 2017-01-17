package patientenportal.service;

import java.util.List;

import patientenportal.dao.PatientFileDAOImpl;
import patientenportal.model.PatientFile;

public class PatientFileService {
	
	public PatientFile getPatientFileById(long patientFileId){
		PatientFileDAOImpl pfdi = new PatientFileDAOImpl();
		List<PatientFile> patientFiles = pfdi.getAll();
		for (PatientFile pf : patientFiles) {
			if (pf.getId() == patientFileId){
				return pf;
			}
		}
		return null;
	}	

}
