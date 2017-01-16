package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.Patient;
import patientenportal.model.User;

public class PatientDAOImpl extends GenericDAOImpl<Patient, Long>{

	@Override
	public Patient addEntityIntern(Patient entity) {
		Patient newPatient = new Patient();
        
        newPatient.setUser(entity.getUser());
        return newPatient; 
	}

}

