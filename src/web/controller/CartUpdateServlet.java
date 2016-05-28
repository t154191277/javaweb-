package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ICartDao;
import dao.impl.CartDaoImpl;
import domain.User;

public class CartUpdateServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		if(user==null){
			response.setContentType("text/html;charset=utf-8");
			request.setAttribute("message", "请先登录!");
			request.getRequestDispatcher("index.html").forward(request, response);
			return;
		}
		ICartDao dao = new CartDaoImpl();
		String shoeID = request.getParameter("shoeID");
		int num = Integer.valueOf(request.getParameter("num"));
		String name = user.getUserName();
		int i = dao.updateNum(name, shoeID, num);
		if(i==-1){
			System.out.print("更改鞋子数量失败！");
			return;
		}
		request.getRequestDispatcher("/servlet/CartUIServlet").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
