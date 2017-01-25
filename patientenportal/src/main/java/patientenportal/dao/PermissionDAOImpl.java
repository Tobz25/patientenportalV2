package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.CaseFile;
import patientenportal.model.Doctor;
import patientenportal.model.Patient;
import patientenportal.model.PatientFile;
import patientenportal.model.Permission;
import patientenportal.model.Relative;
import patientenportal.model.User;

public class PermissionDAOImpl extends GenericDAOImpl<Permission, Long>{

	@Override
	public Permission addEntityIntern(Permission entity) {
		Permission newPermission = new Permission();
        
		newPermission.setPatient(entity.getPatient());
		newPermission.setPermissionType(entity.getPermissionType());
		newPermission.setUserGroups(entity.getUserGroup());
		newPermission.setElements(entity.getElements());
		
        return newPermission; 
	}

}

