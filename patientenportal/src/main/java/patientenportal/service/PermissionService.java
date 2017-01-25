package patientenportal.service;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.criterion.Restrictions;

import patientenportal.dao.BaseClassDAOImpl;
import patientenportal.dao.PermissionDAOImpl;
import patientenportal.dao.UserDAOImpl;
import patientenportal.dao.UserGroupDAOImpl;
import patientenportal.dao.WebSessionDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.BaseClass;
import patientenportal.model.Patient;
import patientenportal.model.Permission;
import patientenportal.model.PermissionType;
import patientenportal.model.User;
import patientenportal.model.UserGroup;
import patientenportal.model.WebSession;

public class PermissionService {
	
	public boolean checkReadPermission(UserGroup loggedInUser, BaseClass entity){
		PermissionDAOImpl pdao = new PermissionDAOImpl();
		List<Permission> perm = pdao.findByCriteria(Restrictions.and(Restrictions.eq("usergroup_id", loggedInUser.getId()),
				Restrictions.and(Restrictions.eq("element_id", entity.getId())), Restrictions.eq("permissiontype", PermissionType.READ.toString())));
		
		return perm.size() == 1;
	}
	public boolean checkWritePermission(UserGroup loggedInUser, BaseClass entity) {
		PermissionDAOImpl pdao = new PermissionDAOImpl();
		List<Permission> perm = pdao.findByCriteria(Restrictions.and(Restrictions.eq("usergroup_id", loggedInUser.getId()),
				Restrictions.and(Restrictions.eq("element_id", entity.getId())), Restrictions.eq("permissiontype", PermissionType.WRITE.toString())));
		
		return perm.size() == 1;
	}
	
	
	public boolean checkReadPermission(long usergroupID, long classId) {
		UserGroupDAOImpl udao = new UserGroupDAOImpl();
		List<UserGroup> users = udao.findByCriteria(Restrictions.eq("baseclass_id", usergroupID));
		if (users.size() != 1) return false;//throw new Exception(hat den nutzer nicht gefunden);
		
		BaseClassDAOImpl bdao = new BaseClassDAOImpl();
		List<BaseClass> classes = bdao.findByCriteria(Restrictions.eq("id", classId));
		if (classes.size() != 1) return false; //throw new Exception(hat den baseclass nicht gefunden);
		
		return checkReadPermission(users.get(0), classes.get(0));
	}
	
	public Response addPermission(BaseClass entity, Patient patient, UserGroup userGroup, PermissionType type) {
		PermissionDAOImpl pdao = new PermissionDAOImpl();
		Permission p = new Permission();
		p.setPatient(patient);
		p.addElements(entity);
		p.addUserGroup(userGroup);
		pdao.addEntity(p);
		return Response.status(Status.OK).build();
	}

}
