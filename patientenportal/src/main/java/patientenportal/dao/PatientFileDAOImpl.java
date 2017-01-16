package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.CaseFile;
import patientenportal.model.Doctor;
import patientenportal.model.Patient;
import patientenportal.model.PatientFile;
import patientenportal.model.Relative;
import patientenportal.model.User;

public class PatientFileDAOImpl extends GenericDAOImpl<PatientFile, Long>{

	@Override
	public PatientFile addEntityIntern(PatientFile entity) {
		PatientFile newPatientFile = new PatientFile();
        
		newPatientFile.setCaseFiles(entity.getCaseFiles());
		newPatientFile.setMedicalDocuments(entity.getMedicalDocuments());
		newPatientFile.setPatient(entity.getPatient());
        return newPatientFile; 
	}

}

