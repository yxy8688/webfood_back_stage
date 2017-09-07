package com.yc.foods.biz.Impl;



import java.util.ArrayList;
import java.util.List;
import com.yc.bean.Resorder;

import com.yc.foods.biz.IResorder;
import com.yc.foods.dao.DBHelper;

public class ResorderBizImpl implements IResorder {

	@Override
	public List<Resorder> findAll(Integer page, Integer rows) throws Exception {
		String sql=null;
		List<Object> params=new ArrayList<>();
		if(page!=null){

			sql="select * from resorder limit ?,?";
			params.add((page-1)*rows);
			params.add(rows);
		}else{
			sql="select * from resorder";
		}
		DBHelper db =new DBHelper();
		return db.find(sql,Resorder.class, params);
	}

	@Override
	public int getTotal(Integer roid) {
		String sql=null;
		DBHelper db=new DBHelper();
		List<String> list;
		if (roid!=null) {
			sql="select count(roid) from resorder where roid=?";
			list=db.find(sql, roid);
		}else{
			sql="select count(roid) from resorder";
			list=db.find(sql);
		}
		if (list!=null && list.size()>0) {
			return Integer.parseInt(list.get(0));
		}else{
			return 0;
		}
	}

	
	public int delete(String roid) {
		DBHelper db=new DBHelper();
		String sql=null;
		String sql2=null;
		if(roid.contains(",") && !roid.contains(" or ")){
			sql="delete from resorder where roid in ("+roid+")";
			sql2="delete from resorderitem where roid in ("+roid+")";
			db.update(sql2);
			return db.update(sql);
		}else{
			sql="delete from resorder where roid=?";
			sql2="delete from resorderitem where roid=?";
			db.update(sql2, roid);
			return db.update(sql, roid);
		}
		
	}

	@Override
	public 	Integer findOne(Integer roid) throws Exception {
		
		DBHelper db=new DBHelper();
		String sql="select userid from resorder where roid=?";
		List<String> list=db.find(sql, roid);
	
		return Integer.parseInt(list.get(0));
	}

}
