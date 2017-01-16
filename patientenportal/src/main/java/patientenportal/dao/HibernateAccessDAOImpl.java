package patientenportal.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import patientenportal.hibernate.SessionUtil;


public class HibernateAccessDAOImpl {

	
	public List<String> getAllClassNames() {
		List<String> erg = new ArrayList<String>();
		
		Session session = SessionUtil.getSession();
		
		Transaction tx = session.beginTransaction();
		erg = (List<String>)session.createQuery("from java.lang.Object").list();
		tx.commit();
		session.close();
		
		return erg;
	}
}
