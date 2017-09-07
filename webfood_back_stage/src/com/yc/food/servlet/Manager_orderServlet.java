package com.yc.food.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Resorder;

import com.yc.foods.biz.IResorder;

import com.yc.foods.biz.Impl.ResorderBizImpl;


public class Manager_orderServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		
		try {
			if("showOrderInfoByPage".equals(op)){
				showOrderInfoByPage(request,response);
			}else if("deleteOrder".equals(op)){
				deleteOrder(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) {
		String roid=request.getParameter("roid");
		
		IResorder order=new ResorderBizImpl();
		this.updateRedis();
		this.out(response, order.delete(roid));
		
		
	}

	private void showOrderInfoByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer page = Integer.parseInt(request.getParameter("page"));

		Integer rows = Integer.parseInt(request.getParameter("rows"));

		IResorder order=new ResorderBizImpl();
		List<Resorder> list = order.findAll(page,rows);
	
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("total", order.getTotal(null));
		map.put("rows", list);
		
		this.updateRedis();
		this.outPage(response, map);
		
	}

}
