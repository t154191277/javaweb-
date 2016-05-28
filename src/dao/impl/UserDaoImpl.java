package dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import dao.DBDao;
import dao.IUserDao;
import domain.User;

public class UserDaoImpl implements IUserDao {
	
	private DBDao dbDao = new DBDao();
	
	private List listValues = new ArrayList();

	@Override
	public int add(User user) {
		String name = user.getUserName();
		String pwd = user.getUserPwd();
		String email = user.getEmail();
		String sqlStr = "insert into users values(?,?,?)";
		listValues.clear();
		listValues.add(name);
		listValues.add(pwd);
		listValues.add(email);
		dbDao.setSqlStr(sqlStr);
		dbDao.setValuesList(listValues);
		int i = dbDao.executeUpdate();
		return i;
	}
	
	private boolean isEmpty(Result rs){
		Object[][] x = rs.getRowsByIndex();
		if(rs.getRowCount()==0) return true;
		return (x[0][0].equals("") || x[0][0]==null);
	}

	@Override
	public User find(String name) {
		String sql = "select pwd from users where name = (?)";
		listValues.clear();
		listValues.add(name);
		dbDao.setSqlStr(sql);
		dbDao.setValuesList(listValues);
		Result rs = dbDao.executeQuery();
		if(!isEmpty(rs)){
			User user = new User();
			for(SortedMap sm : rs.getRows()){
				String pwd = (String) sm.get("pwd");
				String email = (String) sm.get("email");
				user.setUserName(name);
				user.setUserPwd(pwd);
				user.setEmail(email);
			}
			return user;
		}
		return null;
	}

	@Override
	public User find(String name, String pwd) {
		User user = new User();
		String sql = "select name,pwd from users where name = (?) and pwd = (?)";
		listValues.clear();
		listValues.add(name);
		listValues.add(pwd);
		dbDao.setSqlStr(sql);
		dbDao.setValuesList(listValues);
		Result rs = dbDao.executeQuery();
		if(!isEmpty(rs)){
			user.setUserName(name);
			user.setUserPwd(pwd);
			return user;
		}
		return null;
	}

	@Override
	public int addTable(String name) {
		String sql = "create table " + name + 
				"(productID varchar(20) primary key not null," +
				"name varchar(50) not null," +
				"img varchar(50) not null," +
				"num int not null," +
				"price float," +
				"total float " +
				"foreign key(productID) references shoe(productID)," +
				 ")";
		dbDao.setSqlStr(sql);
		int i = dbDao.executeUpdate();
		return i;
	}




}
