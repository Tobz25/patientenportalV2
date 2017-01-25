package patientenportal.service;

import java.util.List;

import patientenportal.dao.PatientDAOImpl;
import patientenportal.dao.UserDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.Patient;
import patientenportal.model.User;

public class UserService {
	
	public List<User> getAllUser(){
		UserDAOImpl udi = new UserDAOImpl();
		List<User> user = udi.getAll();
		if(user.size()>0){
			return user;
		}
		throw new DataNotFoundException("No user found");

	}
	
	public User getUserById(long userId){
		UserDAOImpl udi = new UserDAOImpl();
		List<User> user = udi.getAll();
		for (User u : user) {
			if (u.getId() == userId){
				return u;
			}
		}
		throw new DataNotFoundException("User with id " + userId + " not found");
	}	
	
	public User addUser(User user){
		UserDAOImpl udi = new UserDAOImpl();
		User createdUser = udi.addEntityAndReturn(user);
		return createdUser;
	}
	
	//TODO: ID mitgeben. Bisher wird immer ein neuer User angelegt!
		public User updateUser(User user){
			UserDAOImpl udi = new UserDAOImpl();
			udi.updateEntity(user);
			return user;	
		}
		
		public void deleteUser(long userId){
			
			//TODO:Rollen (also Patient, Angehöriger, Arzt, löschen wenn zu diesem User existierend
			
			UserDAOImpl udi = new UserDAOImpl();
			List<User> allUser = udi.getAll();
			User user;
			for (User u : allUser) {
				if (u.getId() == userId){
					udi.deleteEntity(u);
				}
			}
		}

}
