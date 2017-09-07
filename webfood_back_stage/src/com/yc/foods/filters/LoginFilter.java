package com.yc.foods.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

		private String errorPage="login.html";

		@Override
		public void destroy() {
			
			
		}
//过滤器的作用时间：发送请求，服务器在servlet调用之前；
		@Override
		public void doFilter(ServletRequest req, ServletResponse resp, FilterChain arg2) throws IOException, ServletException {
			//判断用户是否已经登录，我们只需要查看session中的属性currentUserName是否为空
			//因为用户在成功登录后，我们会将当前成功登录的用户的用户信息存到session的这个属性中
			
			HttpServletRequest request=(HttpServletRequest) req;
			HttpServletResponse response=(HttpServletResponse) resp;
			
			HttpSession session=request.getSession();
			//Scheme  [ski:m]  获取服务器的协议

			String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
			if(session.getAttribute("currentUserName")==null){//说明用户没有登录
				response.setContentType("text/html;charset=utf-8");
				//将当前请求截断，立即回送一个请求登录的信息给客户端
				PrintWriter out=response.getWriter();
				out.println("<script>alert('请先登录...');location.href='"+basePath+errorPage+"'</script>");

				out.flush();
				out.close();
			}else{
				
				arg2.doFilter(req, resp);
			}
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
			if(arg0.getInitParameter("errorPage")!=null){
				errorPage=arg0.getInitParameter("errorPage");
				
			}else if(arg0.getServletContext().getInitParameter("errorPage")!=null){
				errorPage=arg0.getServletContext().getInitParameter("errorPage");
			}
			
		}
	
		
		
	

}
