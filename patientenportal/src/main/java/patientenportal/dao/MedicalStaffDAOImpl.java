package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.MedicalStaff;
import patientenportal.model.Patient;
import patientenportal.model.Relative;
import patientenportal.model.User;

public class MedicalStaffDAOImpl extends GenericDAOImpl<MedicalStaff, Long>{

	@Override
	public MedicalStaff addEntityIntern(MedicalStaff entity) {
		MedicalStaff newMedicalStaff = new MedicalStaff();
        
        newMedicalStaff.setUser(entity.getUser());
        newMedicalStaff.setLinkedToPatients(entity.getLinkedToPatients());
        return newMedicalStaff; 
	}

}

