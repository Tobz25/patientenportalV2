package patientenportal.dao;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

public interface GenericDAO<T, ID extends Serializable> {  
		  
	public List<T> findAll();  
	
	public List<T> getAll();
	
	public List<T> findByCriteria(Criterion... criterion);
	  
	public List<T> findByDetachedCriteria(DetachedCriteria crit);
	
	public void addEntity(T entity);
	
	public T addEntityAndReturn(T entity);
	
	public void updateEntity(T entity);
	  
	public void deleteEntity(T entity);  
	
	

}  