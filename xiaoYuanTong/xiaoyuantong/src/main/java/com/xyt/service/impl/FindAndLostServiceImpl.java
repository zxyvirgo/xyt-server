package com.xyt.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyt.dao.BaseDaoI;
import com.xyt.model.Grouptbl;
import com.xyt.model.Lostandfindtbl;
import com.xyt.model.Usertbl;
import com.xyt.pageModel.FindAndLost;
import com.xyt.service.FindAndLostServiceI;
/*
 *因为是对表的直接映射，所以可以作为发表消息时候的插入，说明是寻物消息还是招领消息，然后存储到后台，
 */
@Service("findandlostService")
public class FindAndLostServiceImpl implements FindAndLostServiceI {
	
	private BaseDaoI<Lostandfindtbl> lostandfindDao;
	
	private BaseDaoI<Usertbl> userDao;
	
	

	public BaseDaoI<Lostandfindtbl> getLostandfindDao() {
		return lostandfindDao;
	}


	@Autowired
	public void setLostandfindDao(BaseDaoI<Lostandfindtbl> lostandfindDao) {
		this.lostandfindDao = lostandfindDao;
	}

	

	public BaseDaoI<Usertbl> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Usertbl> userDao) {
		this.userDao = userDao;
	}


	@Transactional
	public List<Lostandfindtbl> getFindAndLostService() {
		// TODO Auto-generated method stub
		
		String hql = null;
		
		hql="SELECT lostandfindtbl.* from lostandfindtbl";
	/*	Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", "1");
*/
		//已经测试过，能够查找到数据库中的数据
		List<Lostandfindtbl> gt = (List<Lostandfindtbl>) lostandfindDao.findandlostsql(hql);
		return gt;
	}


	//用在用户发送消息这一块，发送的不同消息，会自动设定标记，然后保存在数据库中
	@Transactional
	public Serializable save(FindAndLost findAndLost){
		
		Lostandfindtbl t = new Lostandfindtbl();
		
		t.setLoastandfindId(UUID.randomUUID().toString());
		
		String hql = "select usertbl.* from usertbl	where usertbl.userId = "+findAndLost.getUserid(); //默认将name存储为id
		Usertbl usertbl = userDao.findfriendsql(hql).get(0);
		t.setUsertbl(usertbl);
		
		t.setCategory(findAndLost.getCategory());
		t.setContent(findAndLost.getContent());
		t.setCreateTime(findAndLost.getCreateTime());
		t.setFlag(findAndLost.getFlag());
		t.setState(findAndLost.getState());
		
	//	BeanUtils.copyProperties(findAndLost, t);
		//将获取到的数据转化成合适的格式，然后插入到数据库中；
		return lostandfindDao.save(t);
	}
	
	@Transactional
	public void saveOrUpdate(Lostandfindtbl t){
		
		lostandfindDao.saveOrUpdate(t);
	}


	@Transactional
	public List<Lostandfindtbl> getMyFindLostService(String userid) {
		// TODO Auto-generated method stub
		String hql = null;
		
		hql="SELECT lostandfindtbl.* from lostandfindtbl where lostandfindtbl.userId = "+userid;
	/*	Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", "1");
*/
		//已经测试过，能够查找到数据库中的数据
		List<Lostandfindtbl> gt = (List<Lostandfindtbl>) lostandfindDao.findandlostsql(hql);
		return gt;
	}

}
