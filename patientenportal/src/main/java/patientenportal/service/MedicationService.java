package patientenportal.service;

import java.util.List;
import java.util.Set;

import org.eclipse.persistence.internal.oxm.schema.model.Restriction;
import org.hibernate.criterion.Restrictions;

import patientenportal.dao.MedicationDAOImpl;
import patientenportal.dao.MedicationPrescriptionDAOImpl;
import patientenportal.dao.TreatmentDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.Medication;
import patientenportal.model.MedicationPrescription;
import patientenportal.model.Treatment;

public class MedicationService {
	
	public Set<Medication> getMedications(long prescriptionId){
		MedicationPrescriptionDAOImpl mpdi = new MedicationPrescriptionDAOImpl();
		List<MedicationPrescription> prescriptions = mpdi.findByCriteria(Restrictions.eq("id", prescriptionId));
		if(prescriptions.size() == 0)
			throw new DataNotFoundException("No medication for prescription with id "+ prescriptionId + " found");
		return prescriptions.get(0).getMedications();

	}
	
	public Medication getMedication(long medicationId){
		MedicationDAOImpl mdi = new MedicationDAOImpl();
		List<Medication> medications = mdi.findByCriteria(Restrictions.eq("id", medicationId));
		if(medications.size()==0)
			throw new DataNotFoundException("No medication with id "+ medicationId + " found");
		return medications.get(0);
	}

}
