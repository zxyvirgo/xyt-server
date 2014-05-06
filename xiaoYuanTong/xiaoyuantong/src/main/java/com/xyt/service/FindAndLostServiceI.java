package com.xyt.service;

import java.io.Serializable;
import java.util.List;

import com.xyt.model.Lostandfindtbl;
import com.xyt.pageModel.FindAndLost;

public interface FindAndLostServiceI {
	
	//可能使用这个列表，也可能不使用这个直接读取的列表
	
	public List<Lostandfindtbl> getFindAndLostService(); 
	
/*	public boolean insertFind(Lostandfindtbl find);
	
	public boolean insertLost(Lostandfindtbl lost);*/
	public List<Lostandfindtbl> getMyFindLostService(String userid);
	
	public Serializable save(FindAndLost t);
}
