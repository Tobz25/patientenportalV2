package patientenportal.service;

import java.util.List;
import java.util.Set;

import patientenportal.dao.CaseFileDAOImpl;
import patientenportal.dao.TreatmentDAOImpl;
import patientenportal.dao.WebSessionDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.CaseFile;
import patientenportal.model.Treatment;
import patientenportal.model.WebSession;

public class TreatmentService {
	
	public Set<Treatment> getTreatments(long caseFileId){
		CaseFileDAOImpl cfdi = new CaseFileDAOImpl();
		List<CaseFile> casefiles = cfdi.getAll();
		for (CaseFile cf : casefiles){
			if(cf.getId()== caseFileId){
				return cf.getTreatments();
			}
		}
		throw new DataNotFoundException("No treatments for case file with id "+ caseFileId + " found");
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
	

	public Treatment addTreatment(Treatment treatment){
		TreatmentDAOImpl tdi = new TreatmentDAOImpl();
		Treatment createdTreatment = tdi.addEntityAndReturn(treatment);
		return createdTreatment;
	}
	
	public Treatment updateTreatment(Treatment treatment){
		TreatmentDAOImpl tdi = new TreatmentDAOImpl();
		tdi.updateEntity(treatment);
		return treatment;
	}
	

}
