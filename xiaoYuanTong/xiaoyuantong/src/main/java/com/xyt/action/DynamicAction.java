package com.xyt.action;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;

import com.xyt.model.Messagetbl;
import com.xyt.pageModel.Commit;
import com.xyt.pageModel.Json;
import com.xyt.pageModel.Message;
import com.xyt.pageModel.TopicMessage;
import com.xyt.pageModel.UserMassage;
import com.xyt.service.DynamicServiceI;
/*
 * 交友平台中的动态模块
 */

@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "dynamicAction")
public class DynamicAction extends BaseAction implements ModelDriven<Message>{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DynamicAction.class);

	private Message message = new Message (); //模型驱动

//	@Override
	
	public Message getModel() {
		return message;
	}

	private DynamicServiceI dynamicService;

	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public DynamicServiceI getDynamicService() {
		return dynamicService;
	}

	@Autowired
	public void setDynamicService(DynamicServiceI dynamicService) {
		this.dynamicService = dynamicService;
	}

	//可以显示所有的消息
	
	public void dynamicList(){
//		String topicid = ServletActionContext.getRequest().getParameter("topicid");
		
			Map<String, Object> params = new HashMap<String, Object>();
			
			List<Messagetbl> ll =dynamicService.getAllMessageService();
			
			//想要返还给前台的列表
			List<UserMassage> fi=new ArrayList<UserMassage>();
			
			for (int i=0; i<ll.size(); i++){
				
				UserMassage finfo = new UserMassage();
				
				finfo.setUsername(ll.get(i).getUsertbl().getName());
				finfo.setMassage(ll.get(i).getMassage());
				finfo.setCommittime(ll.get(i).getCommittime());
				finfo.setCreatetime(ll.get(i).getCreatetime());
				finfo.setTopicname(ll.get(i).getTopictbl().getTopicname());
				finfo.setTransmit(ll.get(i).getTransmit());
				
				//BeanUtils.copyProperties(ll.get(i), finfo);

				fi.add(finfo);
			}
			
			params.put("dynamic", fi);
			
			super.writeJson(params);
		
	}
	
	//显示所有的话题消息，根据话题的ID，然后查找出来的消息
	public void myMessgeList(){
		String topicid = ServletActionContext.getRequest().getParameter("topicid");
		
		
	}
	
	
	public void messageList() {
		
	//	String topicid = ServletActionContext.getRequest().getParameter("topicid");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		List<Messagetbl> ll =dynamicService.getMessageService("1");
		
		//想要返还给前台的列表
		List<Message> fi=new ArrayList<Message>();
		
		for (int i=0; i<ll.size(); i++){
			
			Message finfo = new Message();
			
			finfo.setMessageid(ll.get(i).getMessageid());
			finfo.setMassage(ll.get(i).getMassage());
			finfo.setCommittime(ll.get(i).getCommittime());
			finfo.setCreatetime(ll.get(i).getCreatetime());
			finfo.setTopicname(ll.get(i).getTopictbl().getTopicname());
			finfo.setTransmit(ll.get(i).getTransmit());
			
			
			//BeanUtils.copyProperties(ll.get(i), finfo);

			fi.add(finfo);
		}
		
		params.put("dynamic", fi);
		
		super.writeJson(params);
		
	}//将得到的类转换成json格式，传递给前台
	
	
	//发表消息的这里，我是真的纠结了
	
	public void insertMessage(){
		
		Json j= new Json();
try {
	     //这样修改是因为topicmessage 里面有topicid ，这样是根据前台的获取而来的
			
			BufferedReader stdin = ServletActionContext.getRequest()
					.getReader();
			String s;
			while ((s = stdin.readLine()) != null && s.length() != 0) {
				logger.info(s);
				TopicMessage f = JSON.parseObject(s.toString(), TopicMessage.class);
				Date date = new Date();
				Timestamp createtime = new Timestamp(date.getTime());
				f.setCreatetime(createtime);
				logger.info(f.getMessage());
				//这些为空的字段插入，就会导致前台读取的异常,其实更安全的方式也可以是在前端做对应的读取控制
				if (f != null && f.getMessage() != null && !f.getMessage().equals("")
						&& f.getCreatetime()!= null && !f.getCreatetime().equals("")
								&& f.getTopicid() != null && !f.getTopicid().equals("")
										&& f.getUserid() != null && !f.getUserid().equals("")){
					dynamicService.save(f);
					j.setSuccess("true");
					j.setMsg("发表成功！");
				}else{
					j.setMsg("插入的数据不符合要求");
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			j.setMsg(e1.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(e.getMessage());

		}
	
		super.writeJson(j);

		
	}

	
	public void insertCommit(Commit  commit){   //针对某个消息进行评论
	}
	
	
	public void getCommit(String messageid){   //找到针对某个消息的全部评论 
	
	}
	
	public void getTransmit(){   //获取转发的信息
	
	}
	
	
	}
	
