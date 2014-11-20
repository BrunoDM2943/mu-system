package testCases.sprint1;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.Cliente;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.excepetions.DataAccessException;
import dao.implementation.ClienteDaoImpl;
import exceptions.BusinessException;


public class ClienteDaoImplTest{

	private static ClienteDaoImpl dao = new ClienteDaoImpl();
	private static Cliente cliCarregado = new Cliente();
	private static Cliente cliVazio     = new Cliente();
	
	
	@BeforeClass
	public static void before() throws BusinessException {
		cliCarregado.setNome("Cliente Teste");
		cliCarregado.setBairro("Parque Boturussu");
		cliCarregado.setCidade("Sao Paulo");
		cliCarregado.setEmail("bdm2943@gmail.com");
		cliCarregado.setEndereco("Dario Costa Mattos, 661");
		cliCarregado.setRg("36.075.532-A");
		cliCarregado.setTelefone("997840151");
		cliCarregado.setUf("SP");
		
		cliVazio.setNome("Nome Vazio");
		cliVazio.setEndereco("endereco vazio");
		cliVazio.setUf("RJ");
	}
	
	@After
	public void after() throws Exception{
		dao.delete(cliCarregado);
		dao.delete(cliVazio);
	}
	
	@Test
	public void TC01GravarCarregado() throws Exception{
		dao.save(cliCarregado);			
	}
	
	@Test(expected = DataAccessException.class)
	public void TC02GravarVazio() throws Exception{
		dao.save(cliVazio);
	}
	
	@Test(expected = BusinessException.class)
	public void TC03GravarDuasVezes() throws Exception{
		dao.save(cliCarregado);
		dao.save(cliCarregado);
	}
	
	@Test
	public void TC04DeletarCliente() throws Exception{
		dao.save(cliCarregado);
		dao.delete(cliCarregado);
	}

	@Test
	public void TC05AtualizarCliente() throws Exception {
		dao.save(cliCarregado);
		cliCarregado.setNome("Novo nome");			
		dao.update(cliCarregado);
		
	}
	
	@Test
	public void TC06LerTodos() throws Exception {
		dao.save(cliCarregado);
		List<Cliente> lista = dao.listAll();
		assertTrue(lista.contains(cliCarregado));	
	}
	
}