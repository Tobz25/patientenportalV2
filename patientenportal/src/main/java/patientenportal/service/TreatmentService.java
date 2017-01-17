package patientenportal.service;

import java.util.List;

import patientenportal.dao.TreatmentDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.Treatment;

public class TreatmentService {
	
	public List<Treatment> getTreatments(long treatmentCaseId){
		TreatmentDAOImpl tdi = new TreatmentDAOImpl();
		List<Treatment> treatments = tdi.getAll();
		return treatments;
	}
	
	public Treatment getTreatmentById(long treatmentId){
		TreatmentDAOImpl tdi = new TreatmentDAOImpl();
		List<Treatment> treatments = tdi.getAll();
		for (Treatment t : treatments) {
			if (t.getId() == treatmentId){
				return t;
			}
		}
		throw new DataNotFoundException("Treatment with id " + treatmentId + " not found");
	}	

}
