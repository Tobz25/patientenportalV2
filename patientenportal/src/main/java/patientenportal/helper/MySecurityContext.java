package patientenportal.helper;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import patientenportal.model.User;

public class MySecurityContext implements SecurityContext{
	private final User user;

    public MySecurityContext(User user) {
        this.user = user;
    }

    @Override
    public Principal getUserPrincipal() {
        /*return new Principal() {
            @Override
            public String getName() {
                return user.getUsername();
            }
        	return this.user;
            
        };*/
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
		// TODO Auto-generated method stub
		return false;
	}
}