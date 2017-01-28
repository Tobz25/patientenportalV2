package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.User;
import patientenportal.model.UserGroup;

public class UserGroupDAOImpl extends GenericDAOImpl<UserGroup, Long>{

	@Override
	public UserGroup addEntityIntern(UserGroup entity) {
		UserGroup newUserGroup = new UserGroup();
        
		newUserGroup.setUser(entity.getUser());
		newUserGroup.setPermissions(entity.getPermissions());

		return newUserGroup; 
	}

}

