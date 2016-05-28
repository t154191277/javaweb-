package service.impl;

import service.IUserService;
import dao.IUserDao;
import dao.impl.UserDaoImpl;
import domain.User;

public class UserServiceImpl implements IUserService{
	
	private IUserDao userDao = new UserDaoImpl();

	public UserServiceImpl() {
	}

	@Override
	public void registerUser(User user){
		userDao.add(user);
		userDao = new UserDaoImpl();
		int i = userDao.addTable(user.getUserName());
		if(i==-1)System.out.print("创建用户表失败！");
	}

	@Override
	public User loginUser(String userName, String userPwd) {
		return userDao.find(userName,userPwd);
	}

	@Override
	public User logoutUser() {
		return null;
	}

	
}
