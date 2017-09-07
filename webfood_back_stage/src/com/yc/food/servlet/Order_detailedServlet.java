package com.yc.food.servlet;

import java.io.IOException;
import java.sql.RowId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Administrator;
import com.yc.bean.Resorder;
import com.yc.bean.Resorderitem;
import com.yc.foods.biz.IResfoodBiz;
import com.yc.foods.biz.IResorder;
import com.yc.foods.biz.IResorderitem;
import com.yc.foods.biz.Impl.ResfoodBizImpl;
import com.yc.foods.biz.Impl.ResorderBizImpl;
import com.yc.foods.biz.Impl.ResorderItemBizImpl;
import com.yc.foods.dao.IResfoodDao;
import com.yc.foods.dao.Impl.ResfoodDaoImpl;


public class Order_detailedServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		
		try {
			if("showOrderInfoByPage".equals(op)){
				showOrderInfoByPage(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	private void showOrderInfoByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer page=Integer.parseInt(request.getParameter("page"));
		Integer rows=Integer.parseInt(request.getParameter("rows"));
		
		IResorderitem resorderitem=new ResorderItemBizImpl();
		List<Resorderitem> list = resorderitem.findAll(page,rows);
	
		for(int i=0;i<list.size();i++){
			
			Integer roid=list.get(i).getRoid();
			Integer fid=list.get(i).getFid();
			
			IResorder order=new ResorderBizImpl();
			
			Integer userid=order.findOne(roid);
			
			IResfoodBiz resfood=new ResfoodBizImpl();
			String fname=resfood.find(fid);
			
			list.get(i).setFname(fname);
			list.get(i).setUserid(userid);
		}
	
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("total", resorderitem.getTotal(null));
		map.put("rows", list);
		
		this.updateRedis();
		this.outPage(response, map);
		
		
	}

}
