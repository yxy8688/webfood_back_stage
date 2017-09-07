package com.yc.food.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;


public class BasicServlet extends HttpServlet {

	private Jedis jedis=new Jedis("127.0.0.1");
	private static final long serialVersionUID = 1L;

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doPost(req,resp);
		}
		protected void outJson( Object obj,HttpServletResponse response) throws IOException{
			//以json格式返回给客户端
			Gson gson=new Gson();
			String jsonString=gson.toJson(obj);
			//以流的方式写出客户端
			//取流response.getWriter()
			//设定回传的数据类型 json  contentType
			response.setContentType("text/json;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.print(jsonString);
			out.close();
		}
	/**
	 * 返回一个状态值	
	 * @param response
	 * @param status
	 */
	protected void out(HttpServletResponse response,String status){
		PrintWriter out=null;
		
		try {
			out=response.getWriter();
			out.print(status);
			out.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}		
	
	/**
	 * 返回单个对象的
	 * @param response
	 * @param obj
	 */
	protected void out(HttpServletResponse response,Object obj){
		PrintWriter out=null;
		
		try {
			out=response.getWriter();
			out.print(obj);
			out.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	
	/**
	 * 返回一个集合对象的
	 * @param response
	 * @param objs
	 */
	protected <T> void out(HttpServletResponse response,List<T> objs){
		PrintWriter out=null;
		
		try {
			Gson gson=new Gson();
			out=response.getWriter();
			out.print(gson.toJson(objs));
			out.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}

	/**
	 * 返回多个集合对象的
	 * @param response
	 * @param objs
	 */
	protected <T> void out(HttpServletResponse response,Map<String,List<T>> map){
		PrintWriter out=null;
		
		try {
			Gson gson=new Gson();
			out=response.getWriter();
			out.print(gson.toJson(map));
			out.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
		
			}
		}
	}
	/**
	 * 针对easyui中的分页请求
	 * @param response
	 * @param objs
	 */
	protected <T> void outPage(HttpServletResponse response,Map<String,Object> map){
		response.setContentType("application/json");
		PrintWriter out=null;
		try {
			Gson gson=new Gson();
			out=response.getWriter();
			out.print(gson.toJson(map));
			out.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
		
			}
		}
	}
	/**
	 * 清空redis
	 */
	public void updateRedis(){
		Set<String> set=jedis.keys("*");
		for(String a:set){
			jedis.del(a);
		}
	}
	
}
