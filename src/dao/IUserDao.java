package dao;

import domain.User;

public interface IUserDao {
	
	int add(User user);
	
	User find(String name);
	
	User find(String name,String pwd);
	
	int addTable(String name);
}
