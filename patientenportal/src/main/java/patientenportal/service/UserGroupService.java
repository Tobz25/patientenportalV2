package patientenportal.service;

import java.util.List;

import patientenportal.dao.DoctorDAOImpl;
import patientenportal.dao.UserGroupDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.Doctor;
import patientenportal.model.UserGroup;


public class UserGroupService {
	
	public UserGroup getUserGroupById(long userGroupId){
		UserGroupDAOImpl ddi = new UserGroupDAOImpl();
		UserGroup groups = ddi.findById(userGroupId);
		if (groups == null)	throw new DataNotFoundException("Doctor with id " + userGroupId + " not found");
		return groups;
	}	

}
