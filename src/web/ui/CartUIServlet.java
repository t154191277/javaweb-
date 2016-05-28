package web.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ICartDao;
import dao.impl.CartDaoImpl;
import domain.Cart;
import domain.User;

public class CartUIServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			response.setContentType("text/html;charset=utf-8");
			request.setAttribute("message", "请先登录!");
			request.getRequestDispatcher("/servlet/LoginUIServlet").forward(request, response);
			return;
		}
		String userName = user.getUserName();
		ICartDao cartDao = new CartDaoImpl();
		List<Cart> cartList = cartDao.get(userName);
		if(cartList==null){
			String message = String.format(
					"你的购物车里没有商品哦！1秒后回到主页！<meta http-equiv='refresh' content='1;url=%s'>",
					request.getContextPath()+"/servlet/ShoeUIServlet");
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("cart", cartList);
		cartDao = new CartDaoImpl();
		double totalPrice = cartDao.getTotalPrice(userName);
		request.getSession().setAttribute("total",totalPrice);
		System.out.print("totalPrice"+totalPrice);
		request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
