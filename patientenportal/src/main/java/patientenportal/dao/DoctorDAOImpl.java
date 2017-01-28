package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.Doctor;
import patientenportal.model.Patient;
import patientenportal.model.Relative;
import patientenportal.model.User;

public class DoctorDAOImpl extends GenericDAOImpl<Doctor, Long>{

	@Override
	public Doctor addEntityIntern(Doctor entity) {
		Doctor newDoctor = new Doctor();
        
        newDoctor.setUser(entity.getUser());
        newDoctor.setLinkedToPatients(entity.getLinkedToPatients());
        return newDoctor; 
	}

}

