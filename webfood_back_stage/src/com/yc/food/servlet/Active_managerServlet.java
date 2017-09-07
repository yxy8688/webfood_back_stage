package com.yc.food.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Active;
import com.yc.foods.biz.IActive;
import com.yc.foods.biz.Impl.ActiveBizImpl;


public class Active_managerServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		
		try {
			if("showActiveContent".equals(op)){
				showActiveContent(request,response);
			}else if("updateActive".equals(op)){
				updateActive(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateActive(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Double activeLimit=Double.parseDouble(request.getParameter("activeLimit"));
		Double activeReduce=Double.parseDouble(request.getParameter("activeReduce"));
		Integer active_id=Integer.parseInt(request.getParameter("active_id"));
		
		IActive active=new ActiveBizImpl();
		int result=active.updateActive(activeLimit, activeReduce, active_id);
		
		this.updateRedis();
		
		
		this.out(response, result);
		
	}

	private void showActiveContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		IActive active=new ActiveBizImpl();
		
		List<Active> list;
		list=active.findAll();
		
		this.out(response, list);
	}

}
