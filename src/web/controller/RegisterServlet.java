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
			String message = String.format("�û����ظ���1���ص���¼���棡<meta http-equiv='refresh' content='1;url=%s'>",request.getContextPath() + "/login.html");
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
		String message = String.format("ע��ɹ���1��������ҳ��<meta http-equiv='refresh' content='1;url=%s'>",request.getContextPath() + "/index.jsp");
		request.setAttribute("message", message);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
