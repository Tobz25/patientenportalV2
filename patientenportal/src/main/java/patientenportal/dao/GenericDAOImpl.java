package patientenportal.dao;

import java.io.Serializable;
import java.util.List;


import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import patientenportal.hibernate.SessionUtil;

public abstract class GenericDAOImpl<T, ID extends Serializable>  
    implements GenericDAO<T, ID> {  

	private Class<T> persistentClass;  
	private Session session;  
	
	public GenericDAOImpl() {  
		 this.persistentClass = (Class<T>) ((ParameterizedType) getClass()  
                 .getGenericSuperclass()).getActualTypeArguments()[0]; 
	}
	
	public void setSession(Session s) {  
	    this.session = s;  
	}  
	
	protected Session getSession() {  
	    if (session == null)  
	        throw new IllegalStateException("Session has not been set on DAO before usage");  
	    return session;  
	}  
	
	public Class<T> getPersistentClass() {  
	    return persistentClass;  
	}  
	
	
	public List<T> findAll() {  
	    return findByCriteria();  
	}  
	
	
	/*
	 * Muss überschrieben werden;
	 */
	public T addEntityIntern(T entity) {
		return entity;
	}
	
	
	public void addEntity(T entity) {
		Session session = SessionUtil.getSession();
		Transaction tx = startTA(session);
		session.save(addEntityIntern(entity));
		tx.commit();
		session.close();
	}
	
	public void updateEntity(T entity) {
		Session session = SessionUtil.getSession();
		Transaction tx = startTA(session);
	    session.saveOrUpdate(entity); 
	    tx.commit();
	    session.close(); 
	}  
	
	public void deleteEntity(T entity) { 
		Session session = SessionUtil.getSession();
		Transaction tx = startTA(session);
	    session.delete(entity);  
	    tx.commit();
	    session.close();
	}  
	
	public Transaction startTA(Session session) {
		return session.beginTransaction();
	}
	
	/** 
	 * Use this inside subclasses as a convenience method. 
	 */  
	public List<T> findByCriteria(Criterion... criterion) {
		Session session = SessionUtil.getSession();
		Transaction tx = startTA(session);
		
	    Criteria crit = session.createCriteria(getPersistentClass());  
	    for (Criterion c : criterion) {  
	        crit.add(c);  
	    }  
	    List<T> entityList = (List<T>) crit.list();
	    
	    tx.commit();
	    session.close();
	    
	    return entityList;  
	}  
	
	public List<T> getAll() {
		Session session = SessionUtil.getSession();
		Transaction tx = startTA(session);
		
	    List<T> entityList = session.createCriteria(persistentClass).list();
	    
	    tx.commit();
	    session.close();
	    
	    return entityList;
	}

}