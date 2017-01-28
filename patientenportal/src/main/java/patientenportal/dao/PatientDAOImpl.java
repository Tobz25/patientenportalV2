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
        newPatient.setInsuranceData(entity.getInsuranceData());
        newPatient.setLinkedDoctors(entity.getLinkedDoctors());
        newPatient.setLinkedMedicalStaff(entity.getLinkedMedicalStaff());
        newPatient.setLinkedRelatives(entity.getLinkedRelatives());
        return newPatient; 
	}

}

