package patientenportal.dao;

import patientenportal.model.WebSession;

public class WebSessionDAOImpl extends GenericDAOImpl<WebSession, Long> {
	
	@Override
	public WebSession addEntityIntern(WebSession entity) {
		WebSession ws = new WebSession();
		ws.setUser(entity.getUser());
		ws.setToken(entity.getToken());
		return ws;
	}
}
