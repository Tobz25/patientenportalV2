package patientenportal.service;

import java.util.List;

import patientenportal.dao.PatientDAOImpl;
import patientenportal.dao.RelativeDAOImpl;
import patientenportal.dao.UserDAOImpl;
import patientenportal.dao.UserGroupDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.helper.Secured;
import patientenportal.model.Patient;
import patientenportal.model.Relative;
import patientenportal.model.Role;
import patientenportal.model.User;
import patientenportal.model.UserGroup;

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
		
		public UserGroup addRole(long userId, String roleString){
			Role role = getRole(roleString);
			UserDAOImpl udi = new UserDAOImpl();
			RelativeDAOImpl rdi = new RelativeDAOImpl();
			User user = udi.findById(userId);
			try{
				if(role.equals(Role.Relative)){
					Relative newrelative = rdi.addEntityAndReturn(new Relative());
					newrelative.setUser(user);
					user.addUserRole(newrelative);
					rdi.updateEntity(newrelative);
					return newrelative;
				}
				return null;
			}
			catch(Exception e){
				throw new DataNotFoundException("Role could not be assigned to user "+ userId);
			}
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
		
		private Role getRole(String r){
			
			switch(r){
				case "Patient": r.equals("Patient");
				return Role.Patient;
				case "Relative": r.equals("Relative");
				return Role.Relative;
				case "Doctor": r.equals("Doctor");
				return Role.Doctor;
				case "MedicalStaff": r.equals("MedicalStaff");
				return Role.MedicalStaff;
			}			
			return null;
		}

}
