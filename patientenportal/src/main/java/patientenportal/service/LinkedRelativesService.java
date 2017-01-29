package patientenportal.service;

/*
 * Service-Implementierung /Anwendungslogik, um Patienten Angehörige hinzuzufügen und diese auszugeben
 * 
 */

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;

import patientenportal.dao.PatientDAOImpl;
import patientenportal.dao.RelativeDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.helper.GenericException;
import patientenportal.model.Patient;
import patientenportal.model.Relative;

public class LinkedRelativesService {
	
	public Set<Relative> getRelatives(long patientId){
		RelativeDAOImpl rdi = new RelativeDAOImpl();
		PatientDAOImpl pdi = new PatientDAOImpl();
		
		Patient patient = pdi.findById(patientId);
		return patient.getLinkedRelatives();
	}
	
	public Relative linkRelative(Patient patient, long relativeId){
		PatientDAOImpl pdi = new PatientDAOImpl();
		RelativeDAOImpl rdi = new RelativeDAOImpl();
		
		Relative r = rdi.findById(relativeId);
		if (r == null) 
			throw new DataNotFoundException("No relative file found with id " + relativeId);
		
		Set<Relative> relatives = getRelatives(patient.getId());
		for(Relative rel : relatives){
			if(rel.equals(r))
				throw new GenericException("The patient already has the selected relative assigned");
		}
		patient.addLinkedRelative(r);
		pdi.updateEntity(patient);
		return r;
	}
}
