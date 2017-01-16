package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.Medication;
import patientenportal.model.MedicationIntake;
import patientenportal.model.MedicationPrescription;
import patientenportal.model.Treatment;
import patientenportal.model.User;
import patientenportal.model.VitalDate;

public class MedicationPrescriptionDAOImpl extends GenericDAOImpl<MedicationIntake, Long>{

	@Override
	public MedicationIntake addEntityIntern(MedicationIntake entity) {
		MedicationIntake newI = new MedicationIntake();
        
		newI.setDescription(entity.getDescription());
		newI.setDateTime(entity.getDateTime());
		newI.setDrug(entity.getDrug());
		newI.setPrescription(entity.getPrescription());
		newI.setTreatment(entity.getTreatment());
		return newI; 
	}

}

