package patientenportal.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import patientenportal.dao.WebSessionDAOImpl;
import patientenportal.model.User;
import patientenportal.model.WebSession;

public class SessionService {

	public String createSessionToken(User user){
		WebSessionDAOImpl wsdi = new WebSessionDAOImpl();
		WebSession wss = new WebSession();
		wss.setUser(user);
		wss.setToken(getNewToken());
		
		return (wsdi.addEntityAndReturn(wss)).getToken();
	}
	
	public String getNewToken() {
		Random random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

}
