package com.yc.foods.biz;

import java.util.List;

import com.yc.bean.Resorder;

public interface IResorder {
	/**
	 * 分页查询所有
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception 
	 */
	public List<Resorder> findAll(Integer page,Integer rows) throws Exception;
	
	/**
	 * 以指定键查询
	 * @throws Exception 
	 */
	public Integer findOne(Integer roid) throws Exception;
	
	/**
	 * 获取总数
	 */
	public int getTotal(Integer roid);
	
	/**
	 * 删除订单信息
	 */
	public int delete(String roid);
	

	

}
