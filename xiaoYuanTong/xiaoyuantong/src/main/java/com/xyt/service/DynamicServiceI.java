package com.xyt.service;

import java.io.Serializable;
import java.util.List;

import com.xyt.model.Messagetbl;
import com.xyt.pageModel.Message;
import com.xyt.pageModel.TopicMessage;

public interface DynamicServiceI {
	
	public List<Messagetbl> getMessageService(String topicid);
	
	public List<Messagetbl> getUserMessageService(String userid);

	public Serializable save(TopicMessage f);
	
	public List<Messagetbl> getAllMessageService();
	
	public List<Messagetbl> getMylMessageService(String userid);

}
