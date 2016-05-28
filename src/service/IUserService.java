package service;

import domain.User;

public interface IUserService {
	
	void registerUser(User user);
	
	User loginUser(String userName,String userPwd);
	
	User logoutUser();
}
