package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IUserService;
import service.impl.UserServiceImpl;
import domain.User;

public class LoginServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("name");
		String userPwd = request.getParameter("pwd");
		IUserService service = new UserServiceImpl();
		User user = service.loginUser(userName, userPwd);
		if(user==null){
			String message = String.format(
					"对不起，用户名或密码有误！！请重新登录！1秒后为您自动跳到登录页面！<meta http-equiv='refresh' content='1;url=%s'>",
					request.getContextPath() + "/index.html");
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("user", user);
		String message = String.format("恭喜：%s,登陆成功！本页将在1秒后跳到首页！！<meta http-equiv='refresh' content='1;url=%s'>", 
						user.getUserName(),request.getContextPath() + "/index.jsp");
		request.setAttribute("message", message);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
