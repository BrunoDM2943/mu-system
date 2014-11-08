package testCases.UC01;
import org.junit.Test;

import services.login.User;
import dao.DaoFacade;
import dao.annotations.DataAccessClass;
import dao.excepetions.DataAccessException;

public class DaoFacadeTest{
	

	@Test
	public void CT01NoErrors() throws Exception{
		User user = new User();
		DaoFacade.gravar(user);				
	}
	
	@Test(expected = DataAccessException.class)
	public void CT002Error() throws Exception{
		DaoFacade.gravar(new Entidade());
	}
	
	@Test(expected = DataAccessException.class)
	public void CT003ErrorNullDataAccessClass() throws Exception{
		DaoFacade.gravar(new EntidadeDataClassNull());
	}

	
	@DataAccessClass(daoImpl = "")
	private class EntidadeDataClassNull{}
	private class Entidade{}
}
