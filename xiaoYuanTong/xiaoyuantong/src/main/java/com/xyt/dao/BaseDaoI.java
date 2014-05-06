package com.xyt.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xyt.model.Grouptbl;
import com.xyt.model.Lostandfindtbl;
import com.xyt.model.Messagetbl;
import com.xyt.model.Topictbl;
import com.xyt.model.Usertbl;
//import com.xyt.pageModel.FindLostAll;

public interface BaseDaoI<T> {
	public Serializable save(T o);
	
	public void delete(T o);
	
	public void update(T o);
	
	public void saveOrUpdate(T o);
	
	public T get(String hql);
	
	public T get(String hql,Object[] params);
	
	public T get(String hql,Map<String, Object> params);
	
	public List<T> find(String hql); 
	
	
	//采用原生的sql语句
	public List<Grouptbl> findsql(String hql); 
	
	public List<Usertbl> findfriendsql(String hql);
	
	public List<Lostandfindtbl> findandlostsql(String hql); 
	 //查找消息列表，根据类别id
	public List<Messagetbl> findmessagesql(String hql);
	
	public List<Topictbl> findtopicsql(String hql);
	//public List<FindLostAll> findallsql(String hql);
	
	public List<T> find(String hql, Map<String, Object> params);
	
	public List<T> find(String hql, int page, int rows); 
	
	public List<T> find(String hql, Map<String, Object> params, int page, int rows);
	
	public Long count(String hql);
	
	public Long count(String hql,Map<String, Object> params);
}
