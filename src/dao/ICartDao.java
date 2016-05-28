package dao;

import java.util.List;

import domain.Cart;

public interface ICartDao {
	
	List<Cart> get(String name);
	
	double getTotalPrice(String name);
	
	int add2Cart(String name, String productID, int num);
	
	int updateNum(String name,String productID,int num);
	
	int del(String name,String shoeID);
}
