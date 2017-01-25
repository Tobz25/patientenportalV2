package patientenportal.service;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;

import patientenportal.dao.MedicationPrescriptionDAOImpl;
import patientenportal.dao.TreatmentDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.MedicationPrescription;
import patientenportal.model.Treatment;

public class MedicationPrescriptionService {
	
	public Set<MedicationPrescription> getMedicationPrescriptions(long treatmentId){
		TreatmentDAOImpl tdi = new TreatmentDAOImpl();
		List<Treatment> treatments = tdi.getAll();
		for (Treatment t : treatments){
			if(t.getId()== treatmentId){
				return t.getPrescriptions();
			}
		}
		throw new DataNotFoundException("No medication prescriptions for treatment file with id "+ treatmentId + " found");
	}
	
	public MedicationPrescription getMedicationPrescriptionById(long prescriptionId){
		MedicationPrescriptionDAOImpl mpdi = new MedicationPrescriptionDAOImpl();
		List<MedicationPrescription> prescriptions = mpdi.findByCriteria(Restrictions.eq("id", prescriptionId));
		if (prescriptions.size() == 0) 
			throw new DataNotFoundException("No medication prescription found for id " + prescriptionId);
		
		return prescriptions.get(0);
	}

}
