 package service.impl;

import service.IShoeService;
import dao.ICartDao;
import dao.impl.CartDaoImpl;

public class ShoeServiceImpl implements IShoeService{
	
	@Override
	public int add2Cart(String name, String productID, int num) {
		ICartDao dao = new CartDaoImpl();
		int i = dao.add2Cart(name, productID, num);
		return i;
	}
}
