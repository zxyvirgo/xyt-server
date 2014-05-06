package com.xyt.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletInputStream;

import org.apache.log4j.Logger;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;
import com.xyt.model.Usertbl;
import com.xyt.pageModel.Json;
import com.xyt.pageModel.Test;
import com.xyt.pageModel.User;
import com.xyt.service.UserService;

@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "userAction")
/*
 * 登陆注册模块
 */
public class UserAction extends BaseAction implements ModelDriven<User> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserAction.class);

	/**
	 * Logger for this class
	 */
	private User user = new User();

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// private static final Logger logger = Logger.getLogger(UserAction.class);
	private UserService userService;

	private UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 用户注册
	public void reg() {
		logger.info("测试测试");

		try {
			BufferedReader stdin = ServletActionContext.getRequest()
					.getReader();
			String s;
			while ((s = stdin.readLine()) != null && s.length() != 0) {
				logger.info(s);
			//	System.out.println("json s = " + s.toString());
				User u2 = JSON.parseObject(s.toString(), User.class);
				
				Usertbl reguser = new Usertbl();
				
				//String userid = user
				reguser.setUserId(UUID.randomUUID().toString());
				logger.info(u2.getGrade());
				
				reguser.setGrade(u2.getGrade());
				reguser.setHometown(u2.getHometown());
				reguser.setMail(u2.getMail());
				reguser.setName(u2.getName());
				reguser.setCollege(u2.getCollege());
				reguser.setQq(u2.getQq());
				reguser.setSex(u2.getSex());
				reguser.setTel(u2.getTel());
				reguser.setTdrId(u2.getTdrId());
				reguser.setMajor(u2.getMajor());
				
				userService.save(reguser);
				
				//logger.info("name2=" + u2.getName());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Json j = new Json();
		try {

			// userService.insertUser(user);

			j.setSuccess("true");
			j.setMsg("注册成功！");

			// 用户注册成功，则进入主界面

		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(e.getMessage());

		}
		super.writeJson(j);

	}

	public void register() {
		logger.info("测试测试");
		// String name = ServletActionContext.getRequest().getParameter("pwd");
		// String pwd = ServletActionContext.getRequest().getParameter("pwd");
		// Map<String, Object> m = new HashMap<String, Object>();
		logger.info(user.getName());

		Json j = new Json();
		try {

			// userService.insertUser(user);
			j.setSuccess("true");
			j.setMsg("注册成功！");

			// 用户注册成功，则进入主界面

		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(e.getMessage());

		}
		super.writeJson(j);

	}
	
	public void login() {
		
		String userid = ServletActionContext.getRequest().getParameter("userid");
	

		logger.info(userid);
		Json j = new Json();
		//如果id存在则返回true
		if (userService.login(userid)) {
			j.setSuccess("true");
		//	j.setMsg("已经注册过！");
		} else {
	//		j.setMsg("未注册过！");
		}

		super.writeJson(j);
	}

}
