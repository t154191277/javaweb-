package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IUserService;
import service.impl.UserServiceImpl;
import dao.IUserDao;
import dao.impl.UserDaoImpl;
import domain.User;

public class RegisterServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		IUserDao dao = new UserDaoImpl();
		User user = dao.find(name);
		if(user!=null){
			String message = String.format("用户名重复！1秒后回到登录界面！<meta http-equiv='refresh' content='1;url=%s'>",request.getContextPath() + "/login.html");
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		user = new User();
		user.setUserName(name);
		user.setUserPwd(pwd);
		user.setEmail(email);
		IUserService service = new UserServiceImpl();
		service.registerUser(user);
		request.getSession().setAttribute("user", user);
		String message = String.format("注册成功！1秒后进入首页！<meta http-equiv='refresh' content='1;url=%s'>",request.getContextPath() + "/index.jsp");
		request.setAttribute("message", message);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
