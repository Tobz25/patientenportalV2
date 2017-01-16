package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.Medication;
import patientenportal.model.Treatment;
import patientenportal.model.User;
import patientenportal.model.VitalDate;

public class MedicationDAOImpl extends GenericDAOImpl<Medication, Long>{

	@Override
	public Medication addEntityIntern(Medication entity) {
		Medication newMedication = new Medication();
        
		newMedication.setDescription(entity.getDescription());
		newMedication.setDrug(entity.getDrug());
		newMedication.setPrescriptions(entity.getPrescriptions());
		newMedication.setWritingDateTime(entity.getWritingDateTime());
		return newMedication; 
	}

}

