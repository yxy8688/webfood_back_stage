package com.yc.foods.dao;

import java.util.List;

import com.yc.bean.Administrator;


public interface IAdministratorDao {
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Administrator> findInfo(Integer pageNo, Integer pageSize) throws Exception;
	

	/**
	 * 获取总数
	 * @param tid
	 * @return
	 */
	public int getTotal(Integer adminid);
	
	/**
	 * 修改个人信息
	 * @return
	 */
	public int updatePersonalInfo(String adminpwd,Integer adminid); 
	
	
}
