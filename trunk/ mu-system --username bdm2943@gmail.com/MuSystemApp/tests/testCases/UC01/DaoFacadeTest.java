package testCases.UC01;
import model.Cliente;

import org.junit.Test;

import services.login.User;
import dao.DaoFacade;
import dao.annotations.DataAccessClass;
import dao.excepetions.DataAccessException;

public class DaoFacadeTest{
	
	private static User user;

	
	@Test
	public void CT01NoErrors() throws Exception{
		if(user != null){
			DaoFacade.delete(user);
		}else{		
			user = new User();
			user.setLogin("teste");
			user.setPassword("123");
		}
				
		DaoFacade.save(user);
		DaoFacade.delete(user);
	}
	
	@Test(expected = DataAccessException.class)
	public void CT002Error() throws Exception{
		DaoFacade.save(new Entidade());
	}
	
	@Test(expected = DataAccessException.class)
	public void CT003ErrorNullDataAccessClass() throws Exception{
		DaoFacade.save(new EntidadeDataClassNull());
	}

	@Test
	public void CT004DAOLerTodos() throws Exception{
		DaoFacade.lerTodos(Cliente.class);
	}
	
	@Test
	public void CT005DAODelete() throws Exception{
		if(user == null){
			user = new User();
			user.setLogin("teste");
			user.setPassword("123");				
			DaoFacade.save(user);
		}
		
		DaoFacade.delete(user);
		
	}
	
	@DataAccessClass(daoImpl = "")
	private class EntidadeDataClassNull{}
	private class Entidade{}
}
