package com.xyt.service.impl;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyt.dao.BaseDaoI;
import com.xyt.model.Messagetbl;
import com.xyt.model.Topictbl;
import com.xyt.model.Usertbl;

import com.xyt.pageModel.TopicMessage;
import com.xyt.service.DynamicServiceI;

@Service("dynamicService")
public class DynamicServiceImpl implements DynamicServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(DynamicServiceImpl.class);
	
	
	private BaseDaoI<Messagetbl> messageDao;
	
	private BaseDaoI<Topictbl> topicDao;
	
	private BaseDaoI<Usertbl> userDao;
	
	public BaseDaoI<Messagetbl> getMessageDao() {
		return messageDao;
	}

	@Autowired
	public void setMessageDao(BaseDaoI<Messagetbl> messageDao) {
		this.messageDao = messageDao;
	}


	public BaseDaoI<Topictbl> getTopicDao() {
		return topicDao;
	}

	@Autowired
	public void setTopicDao(BaseDaoI<Topictbl> topicDao) {
		this.topicDao = topicDao;
	}
	
	
	
	public BaseDaoI<Usertbl> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Usertbl> userDao) {
		this.userDao = userDao;
	}

	//根据话题ID找对应的消息
	@Transactional
	public List<Messagetbl> getMessageService(String topicid) {
		// TODO Auto-generated method stub
		
		String hql = null;
		
		hql="SELECT messagetbl.* from messagetbl WHERE messagetbl.topicid = "+topicid;
	/*	Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", "1");
*/
		//已经测试过，能够查找到数据库中的数据
		List<Messagetbl> gt = (List<Messagetbl>) messageDao.findmessagesql(hql);
		return gt;
	}
	
	@Transactional
	public List<Messagetbl> getAllMessageService() {
		// TODO Auto-generated method stub
		
		String hql = null;
		
		hql="SELECT messagetbl.* from messagetbl";
	/*	Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", "1");
*/
		//已经测试过，能够查找到数据库中的数据
		List<Messagetbl> gt = (List<Messagetbl>) messageDao.findmessagesql(hql);
		return gt;
	}
	
	@Transactional
	public List<Messagetbl> getMylMessageService(String userid) {
		// TODO Auto-generated method stub
		
		String hql = null;
		
		hql="SELECT messagetbl.* from messagetbl WHERE messagetbl.userid = "+userid;
	/*	Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", "1");
*/
		//已经测试过，能够查找到数据库中的数据
		List<Messagetbl> gt = (List<Messagetbl>) messageDao.findmessagesql(hql);
		return gt;
	}
	

	
	@Transactional
	public List<Messagetbl> getUserMessageService(String userid) {
		// TODO Auto-generated method stub
		
		String hql = null;
		
		hql="SELECT messagetbl.* from messagetbl,usermessagetbl WHERE usermessagetbl.messageid = messagetbl.messageid and usermessagetbl.userid ="+userid;
	/*	Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", "1");
*/
		//已经测试过，能够查找到数据库中的数据
		List<Messagetbl> gt = (List<Messagetbl>) messageDao.findmessagesql(hql);
		return gt;
	}

	@Transactional
	public Serializable save(TopicMessage f) {
		// TODO Auto-generated method stub
		
		Messagetbl t = new Messagetbl();
		t.setMessageid(UUID.randomUUID().toString());
		
		Topictbl  topic = new Topictbl();
		
		
		t.setMassage(f.getMessage());
		
		String hql = "select topictbl.* from topictbl	where topictbl.topicid = "+f.getTopicid(); //默认将name存储为id
		Topictbl topictbl = topicDao.findtopicsql(hql).get(0);
		
		t.setTopictbl(topictbl);
		
		logger.info(f.getUserid());
		//因为消息表里面有topic和userid作为外键，所以插入的应该是表的结构
		 hql = "select usertbl.* from usertbl	where usertbl.userId = "+f.getUserid(); //默认将name存储为id
		 Usertbl usertbl = userDao.findfriendsql(hql).get(0);
		//t.setUsermessagetbls(UUID.randomUUID().toString());
	//	t.setLoastandfindId(UUID.randomUUID().toString());
		 t.setUsertbl(usertbl);
		 logger.info(f.getCreatetime());
		 t.setCreatetime(f.getCreatetime());
	//	 Timestamp time = new Timestamp();
		 
	//	BeanUtils.copyProperties(findAndLost, t);
		//将获取到的数据转化成合适的格式，然后插入到数据库中
		return messageDao.save(t);
	}
	
	
	
	//这个部分可以在action里面写，转发，评论的时候可以做相应的修改
	@Transactional
	public void saveOrUpdate(Messagetbl t){
		
		messageDao.saveOrUpdate(t);
	}
	

}
