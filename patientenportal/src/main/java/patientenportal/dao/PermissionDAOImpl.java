package patientenportal.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.BaseClass;
import patientenportal.model.CaseFile;
import patientenportal.model.Doctor;
import patientenportal.model.Patient;
import patientenportal.model.PatientFile;
import patientenportal.model.Permission;
import patientenportal.model.PermissionType;
import patientenportal.model.Relative;
import patientenportal.model.User;
import patientenportal.model.UserGroup;

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
	
	public boolean checkReadPermission(UserGroup user, BaseClass entity) {
		DetachedCriteria c = DetachedCriteria.forClass(Permission.class);
		c.createAlias("elements", "e");
		c.add(Restrictions.eq("e.id", entity.getId()));
		c.createAlias("usergroups", "u");
		c.add(Restrictions.eq("u.id", user.getId()));
		c.add(Restrictions.eq("permissionType", PermissionType.READ));
		List<Permission> perm = findByDetachedCriteria(c);
		
		return perm.size() == 1;
	}
	
	public boolean checkWritePermission(UserGroup user, BaseClass entity) {
		DetachedCriteria c = DetachedCriteria.forClass(Permission.class);
		c.createAlias("elements", "e");
		c.add(Restrictions.eq("e.id", entity.getId()));
		c.createAlias("usergroups", "u");
		c.add(Restrictions.eq("baseclass_id", user.getId()));
		c.add(Restrictions.eq("permissiontype", PermissionType.WRITE));
		List<Permission> perm = findByDetachedCriteria(c);
		
		return perm.size() == 1;
	}

}

