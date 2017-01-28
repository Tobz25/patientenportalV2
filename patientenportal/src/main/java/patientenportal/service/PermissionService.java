package patientenportal.service;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.TypedValue;

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
		
		return pdao.checkReadPermission(loggedInUser, entity);
	}
	public boolean checkWritePermission(UserGroup loggedInUser, BaseClass entity) {
		PermissionDAOImpl pdao = new PermissionDAOImpl();
		
		return pdao.checkWritePermission(loggedInUser, entity);
	}
	
	
	public boolean checkReadPermission(long usergroupID, long classId) {
		UserGroupDAOImpl udao = new UserGroupDAOImpl();
		//List<UserGroup> users = udao.findByCriteria(Restrictions.eq("baseclass_id", usergroupID));
		UserGroup group = udao.findById(usergroupID);
		
		if (group == null) return false;//throw new Exception(hat den nutzer nicht gefunden);
		
		BaseClassDAOImpl bdao = new BaseClassDAOImpl();
		
		BaseClass classes = bdao.findById(classId);
		if (classes == null) return false; //throw new Exception(hat den baseclass nicht gefunden);
		
		return checkReadPermission(group, classes);
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
