package com.yc.foods.biz;

import java.util.List;

import com.yc.bean.Administrator;

public interface IAdministrator {
	/*
	 * 管理员登陆
	 */
	public List<Administrator> login(String uname, String pwd) throws Exception;
	
	/**
	 *  分页查询所有的管理员信息
	
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
	public int getTotal(Integer amindid);
	/**
	 * 修改个人信息
	 * @return
	 */
	public int updatePersonalInfo(String adminpwd,Integer adminid); 
}
