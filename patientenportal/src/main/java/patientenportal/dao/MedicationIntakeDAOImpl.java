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

public class MedicationIntakeDAOImpl extends GenericDAOImpl<MedicationIntake, Long>{

	@Override
	public MedicationIntake addEntityIntern(MedicationIntake entity) {
		MedicationIntake newMI = new MedicationIntake();
        
		newMI.setDescription(entity.getDescription());
		newMI.setDateTime(entity.getDateTime());
		newMI.setDrug(newMI.getDrug());
		newMI.setPermissions(newMI.getPermissions());
		newMI.setPrescription(entity.getPrescription());
		newMI.setTreatment(entity.getTreatment());
		return newMI; 
	}

}

