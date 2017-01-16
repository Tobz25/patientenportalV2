package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.MedicalDocument;
import patientenportal.model.Medication;
import patientenportal.model.Treatment;
import patientenportal.model.User;
import patientenportal.model.VitalDate;

public class MedicalDocumentDAOImpl extends GenericDAOImpl<MedicalDocument, Long>{

	@Override
	public MedicalDocument addEntityIntern(MedicalDocument entity) {
		MedicalDocument newMDoc = new MedicalDocument();
        
		newMDoc.setCaseFile(entity.getCaseFile());
		newMDoc.setPatientFile(entity.getPatientFile());
		newMDoc.setDateTimeOfAdded(entity.getDateTimeofAdded());
		newMDoc.setDescription(entity.getDescription());
		newMDoc.setDoctorAdded(entity.getDoctorAdded());
		return newMDoc; 
	}

}

