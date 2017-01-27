package patientenportal.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.hibernate.criterion.Restrictions;

import patientenportal.dao.WebSessionDAOImpl;
import patientenportal.model.User;
import patientenportal.model.WebSession;

public class SessionService {

	public String createSessionToken(User user){
		WebSessionDAOImpl wsdi = new WebSessionDAOImpl();
		WebSession wss = new WebSession();
		wss.setUser(user);
		wss.setToken(getNewToken());
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 10);
		wss.setValidTill(c.getTime());
		
		wsdi.addEntity(wss);
		
		List<WebSession> sessions = wsdi.findAll();
		for (WebSession ws : sessions) {
			if (ws.getUser().equals(user)) return ws.getToken();
		}
		
		return null;
	}
	
	public String getNewToken() {
		Random random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	public void deleteUnvalidTokens() {
		WebSessionDAOImpl wsdi = new WebSessionDAOImpl();
		List<WebSession> invalidSessions = wsdi.findByCriteria(
				Restrictions.ge("validtill", Calendar.getInstance().getTime()));
		for(WebSession ws: invalidSessions) {
			wsdi.deleteEntity(ws);
		}
	}
	
	public void updateToken(WebSession ws) {
		WebSessionDAOImpl wsdi = new WebSessionDAOImpl();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 10);
		ws.setValidTill(c.getTime());
		wsdi.updateEntity(ws);
	}
	
}
