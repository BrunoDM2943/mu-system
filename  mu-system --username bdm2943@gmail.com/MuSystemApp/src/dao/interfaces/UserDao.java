package dao.interfaces;

import services.login.User;

public interface UserDao extends DataAccessObject<User> {
	
	public User selectUser(String nome, String pw) throws Exception;
	
}
