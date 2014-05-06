package com.xyt.test;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xyt.model.Grouptbl;
import com.xyt.model.Lostandfindtbl;
import com.xyt.model.Messagetbl;
import com.xyt.pageModel.FindAndLost;
import com.xyt.pageModel.Group;
import com.xyt.pageModel.TopicMessage;
import com.xyt.service.DynamicServiceI;
import com.xyt.service.FindAndLostServiceI;
import com.xyt.service.GroupServiceI;
import com.xyt.service.TdrServiceI;
import com.xyt.service.UserService;
import com.xyt.service.impl.GroupServiceImpl;



public class TestHibernate {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestHibernate.class);
	

	
	
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml","applicationContext.xml"});
	
		DynamicServiceI dynamicService = (DynamicServiceI)ac.getBean("dynamicService");
		TopicMessage topicmessage = new TopicMessage();
		topicmessage.setTopicid("1");
		topicmessage.setUserid("1109030121");
		Date date = new Date();
		Timestamp createtime = new Timestamp(date.getTime());
	
		topicmessage.setCreatetime(createtime);
		System.out.print(topicmessage.getCreatetime());
		topicmessage.setMessage("hello world!");
		
		dynamicService.save(topicmessage);
		//	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	/*	UserService userService = (UserService)ac.getBean("userService");
		Usertbl t = new Usertbl();
	   t.setUserId(UUID.randomUUID().toString());
	   t.setName("g");
		
		userService.save(t);*/
		
		
	/*	GroupServiceI groupservice = (GroupServiceI)ac.getBean("groupService");
		List<Grouptbl>	ll =groupservice.getGroupService("1");
		
		logger.info(ll.get(0).getGroupName());
		logger.info(ll.get(1).getGroupName());
		logger.info(ll.get(2).getGroupName());*/
		/*
		FindAndLostServiceI findandlostService = (FindAndLostServiceI)ac.getBean("findandlostService");
		List<Lostandfindtbl>	ll =findandlostService.getFindAndLostService();
		
		logger.info(ll.get(0).getLoastandfindId());
		logger.info(ll.get(1).getLoastandfindId());
		logger.info(ll.get(2).getLoastandfindId());*/
		

		/*DynamicServiceI messageService = (DynamicServiceI)ac.getBean("dynamicService");
		List<Messagetbl> ll =messageService.getUserMessageService("01");
		
		logger.info(ll.get(0).getMessageid());
		logger.info(ll.get(1).getMessageid());
		logger.info(ll.get(2).getMessageid());*/
	/*	TdrServiceI tdrService = (TdrServiceI)ac.getBean("tdrService");*/
		/*List<Usertbl> ll =tdrService.getTdrService("1");
		
		logger.info(ll.get(0).getTdrId());
		logger.info(ll.get(1).getTdrId());
		logger.info(ll.get(2).getTdrId());*/
		
		
	/*	FindAndLostServiceI findandlostService = (FindAndLostServiceI)ac.getBean("findandlostService");
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		
		FindAndLost m = new FindAndLost();
		m.setName("1109030121");
		m.setCategory("AA");
		m.setContent("BB");
	//	m.setCreateTime();
		m.setFlag("cc");
		m.setState("dd");
		findandlostService.save(m);*/
	}
	


}
