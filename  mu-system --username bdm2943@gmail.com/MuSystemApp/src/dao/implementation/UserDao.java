package dao.implementation;

import services.login.User;
import dao.DataAccessObject;

public interface UserDao extends DataAccessObject<User> {
	
	public User selectUser(String nome, String pw) throws Exception;
	
}
