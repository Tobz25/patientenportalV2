package patientenportal.helper;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import patientenportal.model.Doctor;
import patientenportal.model.Role;
import patientenportal.model.User;
import patientenportal.model.UserGroup;

public class MySecurityContext implements SecurityContext{
	private final User user;

    public MySecurityContext(User user) {
        this.user = user;
    }

    @Override
    public Principal getUserPrincipal() {
    	return this.user;
    }
    
    public long getUserId(){
    	return user.getId();
    }

	@Override
	public String getAuthenticationScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUserInRole(String arg0) {
		Role userrole;
		UserGroup group = user.getActiveUserRole();
		if(group instanceof Doctor) {
			userrole = Role.Doctor;
		}
		// TODO Auto-generated method stub
		return false;
	}
}