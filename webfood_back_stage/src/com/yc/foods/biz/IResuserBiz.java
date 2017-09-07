package com.yc.foods.biz;

import java.util.List;

import com.yc.bean.Resuser;

public interface IResuserBiz {
	
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @throws Exception 
	 */
	public List<Resuser> findUser(Integer page,Integer rows) throws Exception;
	/**
	 * 
	 */
	public int getTotal(Integer userid);
	/**
	 * 修改用户信息
	 * @param pwd
	 * @param email
	 * @param userid
	 * @return
	 */
	
	public int update(String username,String pwd,String email,int userid );
	
	/**
	 *添加用户
	 */
	public int add(String username,String pwd,String email);

}
