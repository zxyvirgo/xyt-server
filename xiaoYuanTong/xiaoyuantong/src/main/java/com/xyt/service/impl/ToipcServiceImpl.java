package com.xyt.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyt.dao.BaseDaoI;
import com.xyt.model.Messagetbl;
import com.xyt.model.Topictbl;
import com.xyt.model.Usertbl;
import com.xyt.pageModel.Topic;
import com.xyt.pageModel.TopicMessage;
import com.xyt.service.TopicServiceI;

@Service("topicService")
public class ToipcServiceImpl implements TopicServiceI {
	
	private BaseDaoI<Topictbl> topicDao;
	
	

	public BaseDaoI<Topictbl> getTopicDao() {
		return topicDao;
	}

	//必不可少注入
	@Autowired
	public void setTopicDao(BaseDaoI<Topictbl> topicDao) {
		this.topicDao = topicDao;
	}


	//需要添加的事物，不然会读取不到数据
	@Transactional
	public List<Topictbl> getTopicService(String userid) {
		// TODO Auto-generated method stub
		
		String hql = null;
		
		hql="select topictbl.* from topictbl ";
		//已经测试过，能够查找到数据库中的数据
		List<Topictbl> gt = (List<Topictbl>) topicDao.findtopicsql(hql);
		return gt;
	}
	
	@Transactional
	public List<Topictbl> getTopicAllService() {
		// TODO Auto-generated method stub
		
		String hql = null;
		
		hql="select topictbl.* from topictbl";
		//已经测试过，能够查找到数据库中的数据
		List<Topictbl> gt = (List<Topictbl>) topicDao.findtopicsql(hql);
		return gt;
	}

	
	//好像暂时不需要这个
	@Override
	public List<Messagetbl> getMessageService(String topicid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertTopic(Topic topic) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public Serializable save(Topic t) {
		// TODO Auto-generated method stub
	//如果在某个话题下发表消息，应该是直接发布消息，然后知道当前的话题id，然后就获取到对呀的话题记录，然后插入到数据库中
		//所以显示所有话题列表的时候，应该带着话题的ID，这样进入某一个话题的时候，会知道其ID
		//但是第一次创建话题的时候，是不会自动创建ID的
		Topictbl  topic = new Topictbl();
		topic.setTopicid(UUID.randomUUID().toString());
		topic.setTopicname(t.getTopicname());
//		topic.setTopicid(topicid)
		
		topic.setCreateTime(t.getCreateTime());
		
		return topicDao.save(topic);
	}
}
