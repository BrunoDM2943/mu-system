package services.login;

import dao.annotations.DataAccessClass;

@DataAccessClass(daoImpl = "dao.implementation.UserDaoImpl")
public class User {
	
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

	public void setPassaword(String passaword) throws LoginException {
		if(passaword == null || "".equals(passaword))
			throw new LoginException("The password cannot be empty!");
		this.passaword = passaword;
	}
	
	@Override
	public String toString(){
		return login;
	}		
}
