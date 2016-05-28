package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IShoeService;
import service.impl.ShoeServiceImpl;
import domain.User;

public class ShoeDetailServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			response.setContentType("text/html;charset=utf-8");
			request.setAttribute("message", "���ȵ�¼!");
			request.getRequestDispatcher("/servlet/LoginUIServlet").forward(request, response);
			return;
		}
		String name = user.getUserName();
		String productID = request.getParameter("productID");
		int num = Integer.valueOf(request.getParameter("num"));
		IShoeService shoeService = new ShoeServiceImpl();
		int i = shoeService.add2Cart(name, productID, num);
		if(i==1){
			String message = String.format("���빺�ﳵ�ɹ���2�����ת����ҳ��" + 
					"<meta http-equiv='refresh' content='2;url=%s'>", request.getContextPath() + "/servlet/ShoeUIServlet");
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		System.out.print("���빺�ﳵʧ�ܣ�");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
