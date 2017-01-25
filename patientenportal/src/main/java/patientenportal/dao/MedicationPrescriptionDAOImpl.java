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

public class MedicationPrescriptionDAOImpl extends GenericDAOImpl<MedicationPrescription, Long>{

	@Override
	public MedicationPrescription addEntityIntern(MedicationPrescription entity) {
		MedicationPrescription newI = new MedicationPrescription();
        
		newI.setDescription(entity.getDescription());
		newI.setDoctor(entity.getResponsibleDoctor());
		newI.setMedication(entity.getMedications());
		newI.setTreatment(entity.getTreatment());
		newI.setWritingDateTime(entity.getWritingDateTime());
		return newI; 
	}

}

