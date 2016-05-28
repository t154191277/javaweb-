package dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import dao.DBDao;
import dao.ICartDao;
import domain.Cart;

public class CartDaoImpl implements ICartDao {
	
	private List valuesList = new ArrayList();
	
	private DBDao dbDao = new DBDao();

	@Override
	public List<Cart> get(String name) {
		String sql = "select * from " + name;
		dbDao.setSqlStr(sql);
		Result rs = dbDao.executeQuery();
		List<Cart> cartList = new ArrayList<Cart>();
		if(!isEmpty(rs)){
			for(SortedMap sm : rs.getRows()){
				Cart cart = new Cart();
				String productID = (String) sm.get("productID");
				String shoeName = (String) sm.get("name");
				String img = (String) sm.get("img");
				int num = (Integer) sm.get("num");
				double price = (Double) sm.get("price");
				double total = (Double) sm.get("total");
				cart.setProductID(productID);
				cart.setName(shoeName);
				cart.setImg(img);
				cart.setNum(num);
				cart.setPrice(price);
				cart.setTotal(total);
				cartList.add(cart);
			}
			return cartList;
		}
		return null;
	}

	@Override
	public double getTotalPrice(String name) {
		String sql = "select sum(total) as total from " + name;
		dbDao.setSqlStr(sql);
		Result rs = dbDao.executeQuery();
		double total = 0;
		Object[][] x = rs.getRowsByIndex();
		if(!isEmpty(rs)){
			for(SortedMap sm : rs.getRows()){
				total = (Double)sm.get("total");
			}
			return total;
		}
		return total;
	}
	
	private boolean isEmpty(Result rs){
		Object[][] x = rs.getRowsByIndex();
		if(rs.getRowCount()==0) return true;
		return (x[0][0].equals("") || x[0][0]==null);
	}

	@Override
	public int add2Cart(String name, String productID, int num) {
		String sql = "insert into " + name + " values(?,?,?,?,?,?)";
		Map content = getTableContent(productID);
		if (content.isEmpty()) return -1;
		double price = (Double) content.get("price");
		String shoeName = (String) content.get("name");
		String img = (String) content.get("img");
		double total = num * price;
		dbDao = new DBDao();
		valuesList.clear();
		valuesList.add(productID);
		valuesList.add(shoeName);
		valuesList.add(img);
		valuesList.add(num);
		valuesList.add(price);
		valuesList.add(total);
		dbDao.setSqlStr(sql);
		dbDao.setValuesList(valuesList);
		int i = dbDao.executeUpdate();
		return i;
	}
	
	
	private Map getTableContent(String productID){
		String sql1 = "select name,img,price,discount_price from shoe where productID = (?)";
		valuesList.clear();
		valuesList.add(productID);
		dbDao.setSqlStr(sql1);
		dbDao.setValuesList(valuesList);
		Result rs = dbDao.executeQuery();
		Map map = new HashMap();
		for(SortedMap sm : rs.getRows()){
			String name = (String) sm.get("name");
			String img = (String) sm.get("img");
			double price = (Double) sm.get("price");
			double discount_price = (Double) sm.get("discount_price");
			price = discount_price==0 ? price : discount_price;
			map.put("name", name);
			map.put("img", img);
			map.put("price", price);
		}
		return map;
	}

	@Override
	public int updateNum(String name, String productID, int num) {
		double price = getPrice(name, productID, num);
		double total = price * num;
		dbDao = new DBDao();
		String sql = "update " + name +" set num = " +num + ",total = " + total + " where productID = (?)";
		valuesList.clear();
		valuesList.add(productID);
		dbDao.setSqlStr(sql);
		dbDao.setValuesList(valuesList);
		int i = dbDao.executeUpdate();
		return i;
	}

	private double getPrice(String name,String productID, int num){
		String sql = "select price from " + name + " where productID = (?)";
		valuesList.clear();
		valuesList.add(productID);
		dbDao.setSqlStr(sql);
		dbDao.setValuesList(valuesList);
		Result rs = dbDao.executeQuery();
		System.out.print("getRowCount\n"+rs.getRowCount());
		if(!isEmpty(rs)){
			double price = 0;
			for(SortedMap sm : rs.getRows()){
				price = (Double) sm.get("price");
			}
			return price;
		}
		return 0;
	}

	@Override
	public int del(String name, String shoeID) {
		String sql = "delete from " + name + " where productID = (?)";
		valuesList.clear();
		valuesList.add(shoeID);
		dbDao.setSqlStr(sql);
		dbDao.setValuesList(valuesList);
		int i = dbDao.executeUpdate();
		return 0;
	}

}
