package com.yc.food.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Resuser;
import com.yc.foods.biz.IResuserBiz;
import com.yc.foods.biz.Impl.ResuserBizImpl;
import com.yc.foods.utils.Encrypt;


public class UserInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		
		try {
			if("showUserInfoByPage".equals(op)){
				showUserInfoByPage(request,response);
			}else if("updateUserInfo".equals(op)){
				updateUserInfo(request,response);
			}else if("adduser".equals(op)){
				adduser(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	/**
	 * 添加用户
	 * @param request
	 * @param response
	 */

	private void adduser(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("username");
		String pwd=Encrypt.md5(request.getParameter("pwd"));
		String email=request.getParameter("email");
		
		
		IResuserBiz resuser=new ResuserBizImpl();
		int result=resuser.add(username, pwd, email);
		this.updateRedis();

		this.out(response, String.valueOf(result));
		
		
	}

	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 */
	private void updateUserInfo(HttpServletRequest request, HttpServletResponse response) {
		
		String email=request.getParameter("email");
		System.out.println(request.getParameter("pwd"));
		String pwd=Encrypt.md5(request.getParameter("pwd"));
		System.out.println("加密后的"+request.getParameter("pwd"));
		String username=request.getParameter("username");
		int userid=Integer.parseInt(request.getParameter("userid"));
		
		IResuserBiz resuser=new ResuserBizImpl();
		int result=resuser.update(username,pwd, email, userid);
		this.updateRedis();
		this.out(response, String.valueOf(result));
		
		
	}
	
	/**
	 * 显示所有的用户
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void showUserInfoByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer page=Integer.parseInt(request.getParameter("page"));
		Integer rows=Integer.parseInt(request.getParameter("rows"));
		
		IResuserBiz resuser=new ResuserBizImpl();
		
		List<Resuser> list=resuser.findUser(page, rows);
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("total", resuser.getTotal(null));
		map.put("rows", list);
		this.updateRedis();
		this.outPage(response, map);
		
	}

}
