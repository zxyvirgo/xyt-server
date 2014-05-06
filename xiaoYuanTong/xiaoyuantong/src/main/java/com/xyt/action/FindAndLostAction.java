package com.xyt.action;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.xyt.model.Lostandfindtbl;
import com.xyt.model.Usertbl;
import com.xyt.pageModel.FindAndLost;
import com.xyt.pageModel.Json;
import com.xyt.pageModel.User;
import com.xyt.service.FindAndLostServiceI;

@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "findandlostAction")
public class FindAndLostAction	extends BaseAction implements ModelDriven<FindAndLost>{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(FindAndLostAction.class);

	private	FindAndLost findandlost = new FindAndLost();
	
	@Override
	public FindAndLost getModel() {
		// TODO Auto-generated method stub
		return findandlost;
	}
	
	
	public FindAndLost getFindandlost() {
		return findandlost;
	}

	public void setFindandlost(FindAndLost findandlost) {
		this.findandlost = findandlost;
	}

	//三类不同的service添加注入
	
	private FindAndLostServiceI findAndLostServiceI;
	
	
	public FindAndLostServiceI getFindAndLostServiceI() {
		return findAndLostServiceI;
	}
	
	@Autowired
	public void setFindAndLostServiceI(FindAndLostServiceI findAndLostServiceI) {
		this.findAndLostServiceI = findAndLostServiceI;
	}

	//失物招领信息的添加
	
	public void addFindAndLost(){
		Json j= new Json();
		try {
			
			BufferedReader stdin = ServletActionContext.getRequest()
					.getReader();
			String s;
			while ((s = stdin.readLine()) != null && s.length() != 0) {
				logger.info(s);
				FindAndLost f = JSON.parseObject(s.toString(), FindAndLost.class);
				
				/*//
				 map.put("ItemTitle", category+":"+state+":"+flag);
			     map.put("ItemText", content);*/
				logger.info(f.getCategory());
				logger.info(f.getUserid());
				if (f != null && f.getContent() != null && !f.getContent().equals("")
						&& f.getCategory() != null && !f.getCategory().equals("")
						&& f.getFlag()!= null && !f.getFlag().equals("")
						&& f.getState()!= null && !f.getState().equals("")){
				findAndLostServiceI.save(f);
				j.setSuccess("true");
				j.setMsg("发表成功！");
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
	
	public void getFindAndLostAll(){
		
			Map<String, Object> params = new HashMap<String, Object>();
		
			List<Lostandfindtbl> ll =findAndLostServiceI.getFindAndLostService();
			
			//想要返还给前台的列表
			List<FindAndLost> fi=new ArrayList<FindAndLost>();
			
			for (int i=0; i<ll.size(); i++){
				
				FindAndLost finfo = new FindAndLost();
				
				finfo.setLoastandfindId(ll.get(i).getLoastandfindId());
				finfo.setName(ll.get(i).getUsertbl().getName());
				finfo.setContent(ll.get(i).getContent());
				finfo.setCategory(ll.get(i).getCategory());
				finfo.setCreateTime(ll.get(i).getCreateTime());
				finfo.setFlag(ll.get(i).getFlag());
				finfo.setState(ll.get(i).getFlag());
				
				
				//BeanUtils.copyProperties(ll.get(i), finfo);
	
				fi.add(finfo);
			}
			
			params.put("findandlost", fi);
			
			super.writeJson(params);
		
	}
	
	public void getMyFindAndLost(){
		
		String userid = ServletActionContext.getRequest().getParameter("userid");
		Map<String, Object> params = new HashMap<String, Object>();
	
		List<Lostandfindtbl> ll =findAndLostServiceI.getMyFindLostService(userid);
		
		//想要返还给前台的列表
		List<FindAndLost> fi=new ArrayList<FindAndLost>();
		
		for (int i=0; i<ll.size(); i++){
			
			FindAndLost finfo = new FindAndLost();
			
			finfo.setLoastandfindId(ll.get(i).getLoastandfindId());
			finfo.setName(ll.get(i).getUsertbl().getName());
			finfo.setContent(ll.get(i).getContent());
			finfo.setCategory(ll.get(i).getCategory());
			finfo.setCreateTime(ll.get(i).getCreateTime());
			finfo.setFlag(ll.get(i).getFlag());
			finfo.setState(ll.get(i).getState());
			
			
			//BeanUtils.copyProperties(ll.get(i), finfo);

			fi.add(finfo);
		}
		
		params.put("findandlost", fi);
		
		super.writeJson(params);
	
}

}
