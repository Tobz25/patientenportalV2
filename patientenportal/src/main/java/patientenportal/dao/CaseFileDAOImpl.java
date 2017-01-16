package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.CaseFile;
import patientenportal.model.Doctor;
import patientenportal.model.Patient;
import patientenportal.model.Relative;
import patientenportal.model.User;

public class CaseFileDAOImpl extends GenericDAOImpl<CaseFile, Long>{

	@Override
	public CaseFile addEntityIntern(CaseFile entity) {
		CaseFile newCaseFile = new CaseFile();
        
		
		newCaseFile.setDiagnose(entity.getDiagnose());
		newCaseFile.setMedicalDocuments(entity.getMedicalDocuments());
		newCaseFile.setPatientCase(entity.getPatientCase());
		newCaseFile.setTreatments(entity.getTreatments());
		newCaseFile.setVitalData(entity.getVitalData());
        return newCaseFile; 
	}

}

