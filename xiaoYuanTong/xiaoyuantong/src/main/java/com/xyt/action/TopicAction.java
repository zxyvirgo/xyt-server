package com.xyt.action;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.xyt.pageModel.Json;
import com.xyt.pageModel.Topic;
import com.xyt.pageModel.TopicMessage;
import com.xyt.service.TopicServiceI;


@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "topicAction")
/*
 * 交友平台中的话题模块
 */
public class TopicAction extends BaseAction implements ModelDriven<Topic>{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TopicAction.class);

	private Topic topic = new Topic (); //模型驱动

	@Override
	public Topic getModel() {
		return topic;//这句不能少，因为是注入pagemodel的需要
	}

	private TopicServiceI topicService;

	public TopicServiceI getTopicService() {
		return topicService;
	}

	public void topicList(String topicid) {
		super.writeJson(topicService.getTopicService(topicid));
	}//将得到的类转换成json格式，传递给前台

	
	public void topicAllList() {
		super.writeJson(topicService.getTopicAllService());
	}//将得到的类转换成json格式，传递给前台
	
	
	//在前端是通过获取，然后解析
   public void  insertTopic(){//创建一个话题
	 
	   Json j= new Json();
	   try {
		     //这样修改是因为topicmessage 里面有topicid ，这样是根据前台的获取而来的
			
				BufferedReader stdin = ServletActionContext.getRequest()
						.getReader();
				String s;
				while ((s = stdin.readLine()) != null && s.length() != 0) {
					logger.info(s);
					Topic t = JSON.parseObject(s.toString(), Topic.class);
					Date date = new Date();
					Timestamp createTime = new Timestamp(date.getTime());
					t.setCreateTime(createTime);
					//这些为空的字段插入，就会导致前台读取的异常,其实更安全的方式也可以是在前端做对应的读取控制
					if (t!= null && t.getTopicname() != null && !t.getTopicname().equals("")){				   
						   topicService.save(topic);
						   j.setSuccess("true");
						   j.setMsg("发表成功！");
					}else{
						  j.setMsg("插入的数据不喝要求！");
					}
				
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				//如果插入成功，就会返回插入成功的消息，如果没有就会返回异常
				j.setMsg(e1.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		super.writeJson(j);
		

   }
}
