package com.yc.foods.biz.Impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Administrator;
import com.yc.bean.Resuser;

import com.yc.foods.biz.IAdministrator;
import com.yc.foods.dao.DBHelper;
import com.yc.foods.dao.IAdministratorDao;
import com.yc.foods.dao.Impl.AdministratorDaoImpl;
import com.yc.foods.utils.Encrypt;

import redis.clients.jedis.Jedis;


public class AdministratorBizImpl implements IAdministrator {
	
	private DBHelper db=new DBHelper(); 
	//private Jedis jedis=new Jedis(YcConstant.REDIS_URL);
	@Override
	public List<Administrator> login(String uname,String pwd) throws Exception {
		
		String sql="select * from administrator where adminname=? and adminpwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(uname);
		params.add(pwd);
		
		return db.find(sql,Administrator.class ,params);
	}
	@Override
	public List<Administrator> findInfo(Integer pageNo, Integer pageSize) throws Exception {
		IAdministratorDao admin= new AdministratorDaoImpl();
		return admin.findInfo(pageNo, pageSize);
	}
	@Override
	public int getTotal(Integer adminid) {
		IAdministratorDao admin= new AdministratorDaoImpl();
		
		return admin.getTotal(adminid);
	}
	
	
	
	
	@Override
	public int updatePersonalInfo(String adminpwd, Integer adminid) {
		IAdministratorDao admin= new AdministratorDaoImpl();
		
		if(adminpwd==null || "".equals(adminpwd)){
			return 0;
		}else{
			return admin.updatePersonalInfo(adminpwd, adminid);
		}
		
	}
	
}
