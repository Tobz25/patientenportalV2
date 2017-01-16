package patientenportal.service;

import java.util.List;

import patientenportal.dao.HibernateAccessDAOImpl;

public class HibernateAccessService{
	
	public List<String> getAllTables() {
		HibernateAccessDAOImpl hadaoi = new HibernateAccessDAOImpl();
		return hadaoi.getAllClassNames();
	}
}
