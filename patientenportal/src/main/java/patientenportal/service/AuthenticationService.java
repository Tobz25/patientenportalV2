package patientenportal.service;
/*
 * Service-Implementierung /Anwendungslogik, welche Login, Logout sowie die Authentifizierung über Tokens enthält
 * 
 */

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.criterion.Restrictions;

import patientenportal.dao.UserDAOImpl;
import patientenportal.dao.WebSessionDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.User;
import patientenportal.model.WebSession;

public class AuthenticationService {
	
	SessionService sessionService = new SessionService();
	//public static DAOFactory daoFabrik = DAOFactory.instance(DAOFactory.HIBERNATE);
	//public UserDAO ndao = daoFabrik.getUserDAO();
	//public SessionDAO sdao;
	

	public User authenticateUser(String username, String password) throws Exception{
		UserDAOImpl udao = new UserDAOImpl();
		List<User> users = udao.getAll();
		for (User u : users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}
	
	public User getAuthenticatedUser(String username) {
		UserDAOImpl udao = new UserDAOImpl();
		List<User> users = udao.getAll();
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		throw new DataNotFoundException("User " + username + " not found");
	}
	
	public boolean authenticateToken(String token){
		WebSessionDAOImpl wsdi = new WebSessionDAOImpl();
		List<WebSession> sessions = wsdi.findByCriteria(Restrictions.eq("token", token));
		if (sessions.size() != 1) return false;
		sessionService.updateToken(sessions.get(0));
		return true;
	}
	
	public User getUserByToken(String token) {
		WebSessionDAOImpl wsdi = new WebSessionDAOImpl();
		List<WebSession> sessions = wsdi.findByCriteria(Restrictions.eq("token", token));
		if (sessions.size() != 1) throw new DataNotFoundException("No user found for token " + token);
		return sessions.get(0).getUser();
	}
	
	public Response logout(String token){
		WebSessionDAOImpl wsdi = new WebSessionDAOImpl();
		List<WebSession> sessions = wsdi.getAll();
		for (WebSession ws : sessions) {
			if (ws.getToken().equals(token)){
				wsdi.deleteEntity(ws);
				return Response.status(Status.OK)
						.entity("User logged out sucesfully")
						.build();
			}
		}
		throw new DataNotFoundException("No session found for token token " + token);
		
	}
}