package com.yc.foods.dao.Impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yc.bean.Administrator;
import com.yc.bean.Resfood;
import com.yc.foods.dao.DBHelper;
import com.yc.foods.dao.IResfoodDao;




public class ResfoodDaoImpl implements IResfoodDao {

	/**
	 * 添加食品
	 */
	@Override
	public int addFoods(String fname, Double normprice, Double realprice, String detail, String fphoto) {
		String sql="insert into resfood (fname,normprice,realprice,detail,fphoto) values(?,?,?,?,?)";
		DBHelper db=new DBHelper();

		return db.update(sql, fname,normprice,realprice,detail,fphoto);
	}

	/**
	 * 分页查询

	 */
	@Override
	public List<Resfood> findfoods(Integer pageNo, Integer pageSize) throws Exception {

		String sql=null;
		List<Object> params=new ArrayList<>();
		if(pageNo!=null){

			sql="select * from resfood limit ?,?";
			params.add((pageNo-1)*pageSize);
			params.add(pageSize);
		}else{
			sql="select * from resfood";
		}
		DBHelper db =new DBHelper();
		return db.find(sql,Resfood.class, params);
	}
	/**
	 * 获取总数
	 */
	@Override
	public int getTotal(Integer fid) {

		String sql=null;
		DBHelper db=new DBHelper();
		List<String> list;
		if (fid!=null) {
			sql="select count(fid) from resfood where fid=?";
			list=db.find(sql, fid);
		}else{
			sql="select count(fid) from resfood";
			list=db.find(sql);
		}
		if (list!=null && list.size()>0) {
			return Integer.parseInt(list.get(0));
		}else{
			return 0;
		}

	}

	/**
	 * 修改操作
	 */
	@Override
	public int updateFoods(String fname, Double normprice, Double realprice, String detail,String fphoto,Integer fid) {
		String sql=null;
		DBHelper db=new DBHelper();
		if(fphoto==null || "".equals(fphoto)){
			 sql="update resfood set fname=?,normprice=?,realprice=?,detail=? where fid=?";
			 return db.update(sql,fname,normprice,realprice,detail,fid);
		}else{
			sql="update resfood set fname=?,normprice=?,realprice=?,detail=?,fphoto=? where fid=?";
			return db.update(sql,fname,normprice,realprice,detail,fphoto,fid);
		}
		
	
		
	}
	
	

	/**
	 * 删除操作
	 */
	@Override
	public int deleteFoods(String fid) {
		
		String sql=null;
		DBHelper db=new DBHelper();
		if(fid.contains(",") && !fid.contains(" or")){
			sql="delete from resfood where fid in("+fid+")";
			return db.update(sql);
		}else{
			sql="delete from resfood where fid=?";
			return db.update(sql, fid);
		}
	}

	@Override
	public List<Resfood> findFoods(Map<String, String> params, Integer pageNo, Integer pageSize) throws Exception {
		String sql="select * from resfood";
		List<Object>param=new ArrayList<Object>();
		if(params!=null){
			Set<String>keys=params.keySet();
			for(String key:keys){
				sql+=" where "+key+"?";
				param.add(params.get(key));
			}
		}
		sql+=" limit ?,?";
	
		param.add((pageNo-1)*pageSize);
		param.add(pageSize);
	
		DBHelper db=new DBHelper();
		return db.find(sql, Resfood.class,param);
	}

	


}
