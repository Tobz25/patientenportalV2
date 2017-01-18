package patientenportal.service;

import java.util.List;
import java.util.Set;

import patientenportal.dao.TreatmentDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.MedicationPrescription;
import patientenportal.model.Treatment;

public class MedicationPrescriptionService {
	
	public Set<MedicationPrescription> getMedicationPrescription(long treatmentId){
		TreatmentDAOImpl tdi = new TreatmentDAOImpl();
		List<Treatment> treatments = tdi.getAll();
		for (Treatment t : treatments){
			if(t.getId()== treatmentId){
				return t.getPrescriptions();
			}
		}
		throw new DataNotFoundException("No medication prescriptions for treatment file with id "+ treatmentId + " found");
	}

}
