package com.yc.food.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Administrator;
import com.yc.foods.biz.IAdministrator;
import com.yc.foods.biz.Impl.AdministratorBizImpl;
import com.yc.foods.utils.Encrypt;


public class ManagerInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		try {
			if("personalInfoByPage".equals(op)){
				personalInfoByPageOp(request,response);
			}else if("updatePersonalInfo".equals(op)){
				updatePersonalInfoOp(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 修改管理员密码
	 * @param request
	 * @param response
	 */
	private void updatePersonalInfoOp(HttpServletRequest request, HttpServletResponse response) {
		Integer adminid=Integer.parseInt(request.getParameter("adminid"));
		String adminpwd=request.getParameter("adminpwd");
	
		
		adminpwd=Encrypt.md5(adminpwd);  //加密处理
		
		IAdministrator adminBiz=new AdministratorBizImpl();
		
		int result=adminBiz.updatePersonalInfo(adminpwd, adminid);
		this.updateRedis();
		this.out(response, String.valueOf(result));
		
	}

	/**
	 * 查询所有的管理员信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void personalInfoByPageOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer page = Integer.parseInt(request.getParameter("page"));

		Integer rows = Integer.parseInt(request.getParameter("rows"));

		IAdministrator adminBiz=new AdministratorBizImpl();
		List<Administrator> list = adminBiz.findInfo(page,rows);
	
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("total", adminBiz.getTotal(null));
		map.put("rows", list);
		
	
		response.setContentType("application/json");
		this.updateRedis();
		this.outPage(response, map);
	}

}
