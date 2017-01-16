package patientenportal.hibernate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		SessionUtil.getInstance();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		 SessionUtil.getSession().close();
	}

}
