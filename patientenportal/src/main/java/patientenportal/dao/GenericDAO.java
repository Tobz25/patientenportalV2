package patientenportal.dao;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

public interface GenericDAO<T, ID extends Serializable> {  
		  
	public List<T> findAll();  
	
	public List<T> getAll();
	
	public List<T> findByCriteria(Criterion... criterion);
	  
	public void addEntity(T entity);
	
	public void updateEntity(T entity);
	  
	public void deleteEntity(T entity);  
}  