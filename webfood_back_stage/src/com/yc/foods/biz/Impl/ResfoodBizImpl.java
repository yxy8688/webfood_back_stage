package com.yc.foods.biz.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.yc.bean.Resfood;
import com.yc.foods.biz.IResfoodBiz;
import com.yc.foods.dao.DBHelper;
import com.yc.foods.dao.IResfoodDao;
import com.yc.foods.dao.Impl.ResfoodDaoImpl;



public class ResfoodBizImpl implements IResfoodBiz {

	/**
	 * 添加食品
	 */
	@Override
	public int addFoods(String fname, Double normprice, Double realprice, String detail, String fphoto) {
		IResfoodDao resfood=new ResfoodDaoImpl();
		if(fname==null || "".equals(fname)){
			return 0;
		}else{
			return resfood.addFoods( fname,normprice,realprice,detail,fphoto);
		}
	}
	/**
	 * 分页显示
	 */
	@Override
	public List<Resfood> findResfood(Integer pageNo, Integer pageSize) throws Exception {
		IResfoodDao resfood=new ResfoodDaoImpl();
			return resfood.findfoods(pageNo, pageSize);
	}
	/**
	 * 获取总数
	 * @param fid
	 * @return
	 */
	@Override
	public int getTotal(Integer fid) {
		IResfoodDao resfood=new ResfoodDaoImpl();
		return resfood.getTotal(fid);
	}
	
	/**
	 * 修改食品信息
	 */
	@Override
	public int updateFoods(String fname, Double normprice, Double realprice, String detail, String fphoto,Integer fid) {
		IResfoodDao resfood=new ResfoodDaoImpl();
		if(fname==null || "".equals(fname)){
			return 0;
		}else if(normprice==null || "".equals(normprice)){
			return 0;
		}else{
			return resfood.updateFoods(fname, normprice,realprice,detail,fphoto,fid);
		}
	}
	@Override
	public int deleteFoods(String fid) {
		IResfoodDao resfood=new ResfoodDaoImpl();
		if(fid==null || "".equals(fid)){
			return 0;
		}else{
			return resfood.deleteFoods(fid);
		}
	}
	/**
	 * 通过搜索框查询
	 */
	@Override
	public List<Resfood> findFoods(Map<String, String> params, Integer pageNo, Integer pageSize) throws Exception {
		IResfoodDao resfood=new ResfoodDaoImpl();
		
		return resfood.findFoods(params, pageNo, pageSize);
	}
	/**
	 * 通过id查询菜名
	 */
	@Override
	public String find(Integer fid) throws Exception {
		String sql="select fname from resfood where fid=?";
		DBHelper db=new DBHelper();
		List<String> list=db.find(sql, fid);
		return String.valueOf(list.get(0));
	}
	@Override
	public List<String> findFid() {
		DBHelper dbHelper=new DBHelper();
		String sql="select fid from resfood";
		
	
		List<String> list=dbHelper.find(sql);
		
		return list;
	}


}
