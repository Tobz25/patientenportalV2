package patientenportal.service;

/*
 * Service-Implementierung /Anwendungslogik, welche alle Methoden, zur Verwaltung einer Session enthält
 * 
 */

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
		
		return (wsdi.addEntityAndReturn(wss)).getToken();
	}
	
	public String getNewToken() {
		Random random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	public void deleteInvalidTokens() {
		WebSessionDAOImpl wsdi = new WebSessionDAOImpl();
		List<WebSession> invalidSessions = wsdi.findByCriteria(Restrictions.or(
				Restrictions.isNull("validtill"),
				Restrictions.le("validtill", Calendar.getInstance().getTime())));
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
