package testCases.UC01;

import org.junit.Test;

import services.login.LoginService;

public class LoginTest {
	
	private LoginService logService = new LoginService();
	
	@Test
	public void TC01Login() throws Exception{
		logService.doLogin("Bruno", "19");
	}
	
	

}
