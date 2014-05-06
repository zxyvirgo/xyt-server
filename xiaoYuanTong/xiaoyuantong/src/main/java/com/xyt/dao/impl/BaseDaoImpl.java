package com.xyt.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xyt.dao.BaseDaoI;
import com.xyt.model.Grouptbl;
import com.xyt.model.Lostandfindtbl;
import com.xyt.model.Messagetbl;
import com.xyt.model.Topictbl;
import com.xyt.model.Usertbl;
//import com.xyt.pageModel.FindLostAll;



@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDaoI<T> {

	@Override
	public Serializable save(T o) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().save(o);
	}
	

	private SessionFactory sessionFactory;
/*	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
*/
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public T get(String hql){
		
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if(l!=null&&l.size()>0){
			return l.get(0);
		}
		return null;
		
	}
	
	
	@Override	
	public T get(String hql,Object[] params){
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&params.length>0){
			for(int i=0; i<params.length; i++){
				q.setParameter(i, params[i]);
			}
		}
		List<T> l = q.list();
		if(l!=null&&l.size()>0){
			return l.get(0);
		}
		return null;
		
	}
	
	@Override
	public T get(String hql,Map<String, Object> params){
		
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&!params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if(l!=null&&l.size()>0){
			return l.get(0);
		}
		return null;
	}

	@Override
	public void delete(T o) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(o);
	}

	@Override
	public void update(T o) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(o);
	}

	@Override
	public void saveOrUpdate(T o) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().saveOrUpdate(o);
	}

	@Override
	public List<T> find(String hql) {
		// TODO Auto-generated method stub
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
	//	int first = (page-1)*rows;
		return q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}

	@Override
	public List<T> find(String hql, int page, int rows) {
		// TODO Auto-generated method stub
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);		
		return q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}

	@Override
	public Long count(String hql) {
		// TODO Auto-generated method stub
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);		
		return (Long)q.uniqueResult();
	}
	
	@Override
	public Long count(String hql,Map<String, Object> params){
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		return (Long)q.uniqueResult();
	}

	@Override
	public List<Grouptbl> findsql(String hql) {
		// TODO Auto-generated method stub
		Query q = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Grouptbl.class);
		return q.list();
	}

	@Override
	public List<Usertbl> findfriendsql(String hql) {
		// TODO Auto-generated method stub
		Query q = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Usertbl.class);
		return q.list();
	}

	@Override
	public List<Lostandfindtbl> findandlostsql(String hql) {
		// TODO Auto-generated method stub
		Query q = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Lostandfindtbl.class);
		return q.list();
	}

	/*@Override
	public List<FindLostAll> findallsql(String hql) {
		// TODO Auto-generated method stub
		Query q = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(FindLostAll.class);
		return q.list();
	}*/

	@Override
	public List<Messagetbl> findmessagesql(String hql) {
		// TODO Auto-generated method stub
		Query q = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Messagetbl.class);
		return q.list();
	}
	
	
	
	@Override
	public List<Topictbl> findtopicsql(String hql) {
		// TODO Auto-generated method stub
		Query q = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Topictbl.class);
		return q.list();
	}
}

