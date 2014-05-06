	package com.xyt.action;

import org.apache.log4j.Logger;
	
import java.util.ArrayList;
import java.util.HashMap;
	import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
	import org.apache.struts2.convention.annotation.Namespace;
	import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
	
	import com.opensymphony.xwork2.ModelDriven;
import com.xyt.model.Grouptbl;
import com.xyt.model.Usertbl;
import com.xyt.pageModel.FriendInfo;
	import com.xyt.pageModel.Group;
import com.xyt.service.GroupServiceI;
	
	@ParentPackage("basePackage")
	@Namespace("/")
	@Action(value = "groupAction")
	public class GroupAction extends BaseAction implements ModelDriven<Group>{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GroupAction.class);
	
	private Group group = new Group(); //模型驱动
	
		@Override
		public Group getModel() {
			return group;
		}
	
	private GroupServiceI groupService;
	
	
	public Group getGroup() {
		return group;
	}



	public void setGroup(Group group) {
		this.group = group;
	}


	@Autowired
	public void setGroupService(GroupServiceI groupService) {
		this.groupService = groupService;
	}



	public GroupServiceI getGroupService() {
			return groupService;
		}
	
		
	
	//有点疑惑，关于从前天获取要请求组的消息 竟然和北京书记有联系
	
	public void GroupList() {
		
		
	String userid = ServletActionContext.getRequest().getParameter("id");	
	String groupid;
	List<Grouptbl>	ll =groupService.getGroupService(userid);
	//List<Usertbl>	ul;	
		Map<String, Object> params = new HashMap<String, Object>();
		List<FriendInfo> fi=new ArrayList<FriendInfo>();
		for (int i=0; i<ll.size(); i++){
			
			FriendInfo finfo = new FriendInfo();
			finfo.setGroupId( ll.get(i).getGroupId());
			finfo.setGroupName(ll.get(i).getGroupName());
			
			//取出这个组下面的成员
			groupid =ll.get(i).getGroupId();
			logger.info(groupid);
			List<Usertbl>	ul =groupService.getFriendsService(groupid);
			
			List<String> grouplist = new ArrayList<String>();
			for(int j=0; j<ul.size(); j++){	
				
				logger.info(ul.get(j).getName());
				grouplist.add(ul.get(j).getName());	
			}
		
			finfo.setGrouplist(grouplist);
			
			fi.add(finfo);
		}
		
		params.put("data", fi);
		
		super.writeJson(params);
	}
	
	public void getFriendsService(String groupid){
		groupService.getFriendsService(groupid);
		
	}
	
		
	
	public void deleteFriends(String groupid,String userid){//删除某个组所拥有的好友
		
		groupService.deleteFriendsService(groupid, userid);
		
	}
	    
	public void findMessage(String userid){
	//调用service并且返还json数据
		 //
		groupService.findMessageService(userid);
	}
	   //后面的方法根据service添加
	}
	
