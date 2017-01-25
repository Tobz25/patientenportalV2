package patientenportal.service;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import patientenportal.model.User;

public class PermissionService {
	
	public Response setPermission(Entity entity, long userId, String permission){
		return Response.status(Status.OK).build();
	}
	
	public String getPermission(Entity entity, long userId){
		return "permission";
	}

}
=======
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.criterion.Restrictions;

import patientenportal.dao.PermissionDAOImpl;
import patientenportal.dao.UserDAOImpl;
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
>>>>>>> 7087fc0d92f752685ee454cbf7aae1a8101a2f4e
