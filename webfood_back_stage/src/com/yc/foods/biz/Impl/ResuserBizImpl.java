package com.yc.foods.biz.Impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Resuser;
import com.yc.foods.biz.IResuserBiz;
import com.yc.foods.dao.DBHelper;

public class ResuserBizImpl implements IResuserBiz {


	public List<Resuser> findUser(Integer page, Integer rows) throws Exception {

		String sql=null;
		List<Object> params=new ArrayList<>();
		if(page!=null){

			sql="select * from resuser limit ?,?";
			params.add((page-1)*rows);
			params.add(rows);
		}else{
			sql="select * from resuser";
		}
		DBHelper db =new DBHelper();
		return db.find(sql,Resuser.class, params);
	}



	@Override
	public int getTotal(Integer userid) {
		
		String sql=null;
		DBHelper db=new DBHelper();
		List<String> list;
		if (userid!=null) {
			sql="select count(userid) from resuser where userid=?";
			list=db.find(sql, userid);
		}else{
			sql="select count(userid) from resuser";
			list=db.find(sql);
		}
		if (list!=null && list.size()>0) {
			return Integer.parseInt(list.get(0));
		}else{
			return 0;
		}
	}



	@Override
	public int update(String username,String pwd, String email, int userid) {
		DBHelper db=new DBHelper();
		String sql="update resuser set username=? ,pwd=?,email=? where userid=?";
		
		return db.update(sql, username,pwd,email,userid);
	}



	@Override
	public int add(String username, String pwd, String email) {
		String sql="insert into resuser (username,pwd,email) values(?,?,?)";
		DBHelper db=new DBHelper();
		return db.update(sql, username,pwd,email);
	}


}
