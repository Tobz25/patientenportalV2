package patientenportal.service;

/*
 * Service-Implementierung /Anwendungslogik, welche alle Methoden, die Angehörige betreffen, enthält
 * 
 */

import java.util.List;

import patientenportal.dao.DoctorDAOImpl;
import patientenportal.dao.RelativeDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.Doctor;
import patientenportal.model.Relative;

public class RelativeService {
	
	public List<Relative> getRelatives(){
		RelativeDAOImpl rdi = new RelativeDAOImpl();
		List<Relative> relatives = rdi.getAll();
		return relatives;
	}
	public Relative getRelativeById(long relativeId){
		RelativeDAOImpl rdi = new RelativeDAOImpl();
		Relative r = rdi.findById(relativeId);
		if (r == null) 
			throw new DataNotFoundException("No relative file found with id " + relativeId);
		return r;
	}	
}
