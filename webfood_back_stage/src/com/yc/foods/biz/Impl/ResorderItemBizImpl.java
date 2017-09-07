package com.yc.foods.biz.Impl;


import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Resorderitem;
import com.yc.foods.biz.IResorderitem;
import com.yc.foods.dao.DBHelper;

public class ResorderItemBizImpl implements IResorderitem {

	@Override
	public List<Resorderitem> findAll(Integer page, Integer rows) throws Exception {
		String sql=null;
		List<Object> params=new ArrayList<>();
		if(page!=null){

			sql="select * from resorderitem limit ?,?";
			params.add((page-1)*rows);
			params.add(rows);
		}else{
			sql="select * from resorderitem";
		}
		DBHelper db =new DBHelper();
		return db.find(sql,Resorderitem.class, params);
	}

	@Override
	public int getTotal(Integer roiid) {
		String sql=null;
		DBHelper db=new DBHelper();
		List<String> list;
		if (roiid!=null) {
			sql="select count(roiid) from resorderitem where roiid=?";
			list=db.find(sql, roiid);
		}else{
			sql="select count(roiid) from resorderitem";
			list=db.find(sql);
		}
		if (list!=null && list.size()>0) {
			return Integer.parseInt(list.get(0));
		}else{
			return 0;
		}
	}

	@Override
	public String getNum(Integer fid) {
		String sql=null;
		DBHelper db=new DBHelper();
		List<String> list;
		if (fid!=null) {
			sql="select sum(num) from resorderitem where fid=?";
			list=db.find(sql, fid);
		}else{
			return "0";
		}
		if (list.size()>0 && list!=null) {
			return list.get(0);
		}else{
			return "0";
		
		}
		
	}

}
