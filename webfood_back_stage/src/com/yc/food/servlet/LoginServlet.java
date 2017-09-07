package com.yc.food.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.Administrator;
import com.yc.foods.biz.IAdministrator;
import com.yc.foods.biz.Impl.AdministratorBizImpl;
import com.yc.foods.utils.Encrypt;


public class LoginServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String uname=request.getParameter("uname");
		String pwd=Encrypt.md5(request.getParameter("pwd"));
		PrintWriter out=response.getWriter();
		
		if(uname==null || "".equals(uname)){
			
			out.print("<script>alert('用户名不能为空...');location.href='login.html'</script>");
		}
		if(pwd==null || "".equals(pwd)){
			
			out.print("<script>alert('密码不能为空...');location.href='login.html'</script>");

		}
		
		IAdministrator admin=new AdministratorBizImpl();
		
		List<Administrator> result = null;
		try {
			result = admin.login(uname,pwd);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if(result.size()==0 ||result.isEmpty() ){
			
			out.print("<script>alert('密码不能为空...');location.href='login.html'</script>");

			this.out(response, "0");
		}else{
			HttpSession session=request.getSession();
			session.setAttribute("currentUserName",uname);
			
			this.out(response, "1");
		
		}
		
		
		
	}

}
