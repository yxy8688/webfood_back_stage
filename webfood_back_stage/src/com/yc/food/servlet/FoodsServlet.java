package com.yc.food.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.yc.bean.Resfood;
import com.yc.foods.biz.IResfoodBiz;
import com.yc.foods.biz.IResorderitem;
import com.yc.foods.biz.Impl.ResfoodBizImpl;
import com.yc.foods.biz.Impl.ResorderItemBizImpl;
import com.yc.foods.utils.FileUploadUtil;


public class FoodsServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 为了在一个servlet处理多个请求,那么在每个servlet中加一个op标示符
		String op = request.getParameter("op");

		try {
			if ("addFoods".equals(op)) {
				addFoodsOp(request, response);
			} else if ("findfoodsInfoByPage".equals(op)) {

				findfoodsInfoByPageOp(request, response);

			} else if ("updateFoods".equals(op)) {
				updateFoodsOp(request, response);
			}else if("deleteFoods".equals(op)){
				deleteFoodsOp(request,response);
			}else if("foodsInfoSearch".equals(op)){

				foodsInfoSearchOp(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过搜索框查询
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	
	private void foodsInfoSearchOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fname=request.getParameter("fname");

		Integer page=Integer.parseInt(request.getParameter("page"));
		Integer rows=Integer.parseInt(request.getParameter("rows"));

		IResfoodBiz resfoodBiz = new ResfoodBizImpl();
		Map<String,String> map=new HashMap<String,String>();

		if(fname!=null && !"".equals(fname)){
			map.put("fname like","%"+fname+"%" );
		}
		this.updateRedis();
		this.out(response, resfoodBiz.findFoods(map,page,rows));
	}

	//删除食品
	private void deleteFoodsOp(HttpServletRequest request, HttpServletResponse response) {
		String fid=request.getParameter("fid");
		IResfoodBiz resfoodBiz = new ResfoodBizImpl();
		int result=resfoodBiz.deleteFoods(fid);
		this.updateRedis();
		this.out(response, String.valueOf(result));

	}

	// 修改食品信息
	private void updateFoodsOp(HttpServletRequest request, HttpServletResponse response) {
		FileUploadUtil upload = new FileUploadUtil();

		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true,
				1024, true); // ????

		Map<String, String> map = upload.fileUpload(pageContext);



		IResfoodBiz resfoodBiz = new ResfoodBizImpl();
System.out.println("map的值是"+map);
		int result = resfoodBiz.updateFoods(map.get("fname"), Double.parseDouble(map.get("normprice")),
				Double.parseDouble(map.get("realprice")), map.get("detail"), map.get("update_fphoto"),
				Integer.parseInt(map.get("fid")));
	
		this.updateRedis();
		this.out(response, String.valueOf(result));

	}

	// 以分页的形式显示所有的食品
	private void findfoodsInfoByPageOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer page = Integer.parseInt(request.getParameter("page"));

		Integer rows = Integer.parseInt(request.getParameter("rows"));

		IResfoodBiz resfoodBiz = new ResfoodBizImpl();
		List<Resfood> list = resfoodBiz.findResfood(page, rows);
		
		for(int i=0;i<list.size();i++){
			int fid=list.get(i).getFid();
			int num=0;
			
			IResorderitem resorderitem=new ResorderItemBizImpl();
			String getnum=resorderitem.getNum(fid);
			
			if(getnum!=null){
				num=Integer.parseInt(resorderitem.getNum(fid));
			}
			list.get(i).setNum(num);
			
		}
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("total", resfoodBiz.getTotal(null));
		map.put("rows", list);
		this.updateRedis();
		this.outPage(response, map);
	}

	// 添加食品
	private void addFoodsOp(HttpServletRequest request, HttpServletResponse response) {
		FileUploadUtil upload = new FileUploadUtil();

		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true,
				1024, true); // ????

		Map<String, String> map = upload.fileUpload(pageContext);



		IResfoodBiz resfoodBiz = new ResfoodBizImpl();

		int result = resfoodBiz.addFoods(map.get("fname"), Double.parseDouble(map.get("normprice")),
				Double.parseDouble(map.get("realprice")), map.get("detail"), map.get("fphoto"));

		this.updateRedis();
		this.out(response, String.valueOf(result));
	}

}
