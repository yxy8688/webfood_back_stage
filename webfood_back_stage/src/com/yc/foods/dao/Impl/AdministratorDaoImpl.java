package com.yc.foods.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Administrator;

import com.yc.foods.dao.DBHelper;
import com.yc.foods.dao.IAdministratorDao;



public class AdministratorDaoImpl implements IAdministratorDao {

	@Override
	public List<Administrator> findInfo(Integer pageNo, Integer pageSize) throws Exception {
		String sql=null;
		List<Object> params=new ArrayList<>();
		if(pageNo!=null){

			sql="select * from administrator limit ?,?";
			params.add((pageNo-1)*pageSize);
			params.add(pageSize);
		}else{
			sql="select * from administrator";
		}
		DBHelper db =new DBHelper();
		return db.find(sql,Administrator.class, params);
	}

	@Override
	public int getTotal(Integer adminid) {
		String sql=null;
		DBHelper db=new DBHelper();
		List<String> list;
		if (adminid!=null) {
			sql="select count(adminid) from administrator where adminid=?";
			list=db.find(sql, adminid);
		}else{
			sql="select count(adminid) from administrator";
			list=db.find(sql);
		}
		if (list!=null && list.size()>0) {
			return Integer.parseInt(list.get(0));
		}else{
			return 0;
		}
	}

	@Override
	public int updatePersonalInfo( String adminpwd,Integer adminid) {
		DBHelper db=new DBHelper();
		String sql="update administrator set adminpwd=? where adminid=?";
		
		return db.update(sql, adminpwd,adminid);
	}



	
	

}
