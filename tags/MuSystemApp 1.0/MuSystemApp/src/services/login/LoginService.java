package services.login;

import dao.implementation.UserDaoImpl;
import dao.interfaces.UserDao;


public class LoginService {
	
	public void doLogin(String nome, String pw) throws Exception{
		UserDao dao = new UserDaoImpl();
		dao.selectUser(nome, pw);
		
	}

	
	
}
