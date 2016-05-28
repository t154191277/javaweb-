package web.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IShoeDao;
import dao.impl.ShoeDaoImpl;
import domain.Shoe;

public class ShoeDetailUIServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productID = request.getParameter("shoeID");
		IShoeDao shoeDao = new ShoeDaoImpl();
		List<String> imgList = shoeDao.findDetailImg(productID);
		request.getSession().setAttribute("imgList",imgList);
		shoeDao = new ShoeDaoImpl();
		Shoe shoe = shoeDao.find(productID);
		request.getSession().setAttribute("shoe", shoe);
		request.getRequestDispatcher("/WEB-INF/pages/shoe.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
