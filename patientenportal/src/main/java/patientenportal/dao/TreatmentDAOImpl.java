package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.Treatment;
import patientenportal.model.User;
import patientenportal.model.VitalDate;

public class TreatmentDAOImpl extends GenericDAOImpl<Treatment, Long>{

	@Override
	public Treatment addEntityIntern(Treatment entity) {
		Treatment newTreatment = new Treatment();
        
		newTreatment.setCaseFile(entity.getCaseFile());
		newTreatment.setDescription(entity.getDescription());
		newTreatment.setDoctor(entity.getResponsibleDoctor());
		newTreatment.setFinished(entity.getFinished());
		newTreatment.setMedicationIntakes(entity.getMedicationIntakes());
		newTreatment.setPrescriptions(entity.getPrescriptions());
		newTreatment.setStartDateTime(entity.getStartDateTime());
		return newTreatment; 
	}

}

