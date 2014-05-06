package com.xyt.service.impl;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyt.dao.BaseDaoI;
import com.xyt.model.Usertbl;
import com.xyt.pageModel.User;
import com.xyt.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UserServiceImpl.class);
	
	private BaseDaoI<Usertbl> userDao;
	

	public BaseDaoI<Usertbl> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Usertbl> userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public Serializable save(Usertbl t){
		return userDao.save(t);
	}

	//暂时没有用到
	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		Usertbl t = new Usertbl();
	
		BeanUtils.copyProperties(user, t);
		//将user的属性复制到t，但是刨除pass
		userDao.save(t);
		

	}

	@Transactional
	public boolean login(String userid) {
		String hql = null;
		hql="select usertbl.* from usertbl	where usertbl.userId = "+userid;
		
		logger.info(userid);

		List<Usertbl> gt = (List<Usertbl>) userDao.findfriendsql(hql);	
		if (gt != null) {
			return true;
		}
		return false;
	}

}
