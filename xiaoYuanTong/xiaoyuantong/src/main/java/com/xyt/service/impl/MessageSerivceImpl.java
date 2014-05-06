package com.xyt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyt.dao.BaseDaoI;
import com.xyt.model.Committbl;
import com.xyt.model.Messagetbl;
import com.xyt.pageModel.Commit;
import com.xyt.pageModel.Message;
import com.xyt.service.MessageServiceI;

@Service("messageService")
public class MessageSerivceImpl implements MessageServiceI {
	
	private BaseDaoI<Messagetbl> messageDao;
	


	public BaseDaoI<Messagetbl> getMessageDao() {
		return messageDao;
	}

	@Autowired
	public void setMessageDao(BaseDaoI<Messagetbl> messageDao) {
		this.messageDao = messageDao;
	}



	@Override
	public List<Messagetbl> getMessageService(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Committbl> getCommitService(String messageid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void insertMessage(Message message,String userid) {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void insertCommit(Commit commit) {
		// TODO Auto-generated method stub

	}

}
