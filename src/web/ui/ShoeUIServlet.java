package web.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IShoeDao;
import dao.impl.ShoeDaoImpl;
import domain.Shoe;
import domain.User;

public class ShoeUIServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		System.out.println(user);
		if(user==null){
			response.setContentType("text/html;charset=utf-8");
			request.getRequestDispatcher("/servlet/LoginUIServlet").forward(request, response);
			return;
		}
		List<Shoe> shoeList = new ArrayList<Shoe>();
		IShoeDao shoeDao = new ShoeDaoImpl();
		shoeList = shoeDao.findTeamShoe();
		request.getSession().setAttribute("shoeList", shoeList);
		request.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
