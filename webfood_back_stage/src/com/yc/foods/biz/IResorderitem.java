package com.yc.foods.biz;

import java.util.List;

import com.yc.bean.Resorderitem;



public interface IResorderitem {
	/**
	 * 分页查询
	 * @throws Exception 
	 */
	public List<Resorderitem> findAll(Integer page,Integer rows) throws Exception; 
	/**
	 * 获取总数
	 */
	public int getTotal(Integer roiid);
	/**
	 * 获取指定食品的下单数
	 * @param fid
	 * @return
	 */
	public String getNum(Integer fid);

}
