package patientenportal.dao;

import java.io.Serializable;
import java.util.List;


import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;

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
	
	public T findById(long Id) {
		Session session = SessionUtil.getSession();
	    return session.load(getPersistentClass(), Id);  
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
	
	public T addEntityAndReturn(T entity) {
		Session session = SessionUtil.getSession();
		Transaction tx = startTA(session);
		long id = (long)session.save(addEntityIntern(entity));
		tx.commit();
		session.close();
		
		Criterion cr = Restrictions.eq("id", id);
		return findByCriteria(cr).get(0);
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
	
	public void deleteEntity(long id) {
		Session session = SessionUtil.getSession();
		Transaction tx = startTA(session);
	    session.delete(session.load(persistentClass, id));  
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
	
	
	public List<T> findByDetachedCriteria(DetachedCriteria crit) {
		Session session = SessionUtil.getSession();
		Transaction tx = startTA(session);
		
		List<T> entityList = crit.getExecutableCriteria(session).list();
		
		tx.commit();
		session.close();
		    
		return entityList;  
	}
 
}