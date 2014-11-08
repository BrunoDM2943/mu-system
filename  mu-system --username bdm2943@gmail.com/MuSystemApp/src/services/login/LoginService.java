package services.login;

import dao.implementation.UserDao;
import dao.implementation.UserDaoImpl;


public class LoginService {
	
	public void doLogin(String nome, String pw) throws Exception{
		UserDao dao = new UserDaoImpl();
		dao.selectUser(nome, pw);
		
	}

	
	
}
