package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.Medication;
import patientenportal.model.MedicationPrescription;
import patientenportal.model.Treatment;
import patientenportal.model.User;
import patientenportal.model.VitalDate;

public class MedicationIntakeDAOImpl extends GenericDAOImpl<MedicationPrescription, Long>{

	@Override
	public MedicationPrescription addEntityIntern(MedicationPrescription entity) {
		MedicationPrescription newMP = new MedicationPrescription();
        
		newMP.setDescription(entity.getDescription());
		newMP.setDoctor(entity.getResponsibleDoctor());
		newMP.setMedication(entity.getMedications());
		newMP.setTreatment(entity.getTreatment());
		newMP.setWritingDateTime(entity.getWritingDateTime());
		return newMP; 
	}

}

