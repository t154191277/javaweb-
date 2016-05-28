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
					"�Բ����û������������󣡣������µ�¼��1���Ϊ���Զ�������¼ҳ�棡<meta http-equiv='refresh' content='1;url=%s'>",
					request.getContextPath() + "/index.html");
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("user", user);
		String message = String.format("��ϲ��%s,��½�ɹ�����ҳ����1���������ҳ����<meta http-equiv='refresh' content='1;url=%s'>", 
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
