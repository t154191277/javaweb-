package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ICartDao;
import dao.impl.CartDaoImpl;
import domain.User;

public class CartDeleteServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			response.setContentType("text/html;charset=utf-8");
			request.setAttribute("message", "ÇëÏÈµÇÂ¼!");
			request.getRequestDispatcher("/servlet/LoginUIServlet").forward(request, response);
			return;
		}
		String userName = user.getUserName();
		String id = request.getParameter("shoeID");
		ICartDao dao = new CartDaoImpl();
		int i = dao.del(userName,id);
		if(i==-1){
			System.out.println("É¾³ýÐ¬×ÓÊ§°Ü£¡");
		}
		request.getRequestDispatcher("/servlet/CartUIServlet").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
