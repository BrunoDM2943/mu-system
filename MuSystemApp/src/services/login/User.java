package services.login;

import dao.annotations.DataAccessClass;

@DataAccessClass(daoImpl = "dao.implementation.UserDaoImpl")
public class User {
	
	private int id_user;
		
	private String login;
	
	private String passaword;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws LoginException {
		if(login == null || "".equals(login))
			throw new LoginException("The login cannot be empty!");
		this.login = login;
	}

	public String getPassaword() {
		return passaword;
	}

	public void setPassword(String passaword) throws LoginException {
		if(passaword == null || "".equals(passaword))
			throw new LoginException("The password cannot be empty!");
		this.passaword = passaword;
	}
	
	public int getId_User() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	@Override
	public String toString(){
		return login;
	}		
}
