package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.User;

public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

	@Override
	public User addEntityIntern(User entity) {
		User newUser = new User();
        
        newUser.setFirstname(entity.getFirstname());
        newUser.setLastname(entity.getLastname());
        newUser.setUsername(entity.getUsername());
        newUser.setPassword(entity.getPassword());
        newUser.setEmailaddress(entity.getEmailaddress());
        newUser.setSalutation(entity.getSalutation());
        return newUser; 
	}

}

