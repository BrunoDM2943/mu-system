package testCases.sprint1;

import static org.junit.Assert.*;

import java.util.List;

import model.Cliente;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.implementation.ClienteDaoImpl;
import enums.Estado;
import exceptions.BusinessException;


public class ClienteDaoImplTest{

	private static ClienteDaoImpl dao = new ClienteDaoImpl();
	private static Cliente cliCarregado = new Cliente();
	private static Cliente cliVazio     = new Cliente();
	
	
	@BeforeClass
	public static void before() throws BusinessException {
		cliCarregado.setNome("Bruno");
		cliCarregado.setBairro("Parque Boturussu");
		cliCarregado.setCidade("Sao Paulo");
		cliCarregado.setEmail("bdm2943@gmail.com");
		cliCarregado.setEndereco("Dario Costa Mattos, 661");
		cliCarregado.setRg("36.075.532-X");
		cliCarregado.setTelefone("997840151");
		cliCarregado.setUf(Estado.SP);
		
		cliVazio.setNome("Nome Vazio");
		cliVazio.setRg("19");
		cliVazio.setEndereco("endereco vazio");
		cliVazio.setUf(Estado.SP);
	}
	
	@Test
	public void TC01GravarCarregado() throws Exception{
		try{
			dao.save(cliCarregado);			
		}catch(BusinessException e){
			dao.delete(cliCarregado);
			dao.save(cliCarregado);
		}
	}
	
	@Test
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
		try{
			dao.save(cliCarregado);
		}catch(BusinessException e1){
			
		}finally{
			dao.delete(cliCarregado);
		}
	}

	@Test
	public void TC05AtualizarCliente() throws Exception {
		dao.delete(cliCarregado);
		dao.save(cliCarregado);
		cliCarregado.setNome("Novo nome");			
		dao.update(cliCarregado);
		
	}
	
	@Test
	public void TC06LerTodos() throws Exception {
		dao.save(cliCarregado);
		List<Cliente> lista = dao.listAll();
		assertTrue(lista.contains(cliCarregado));
		dao.delete(cliCarregado);
	}
	
	

	@AfterClass
	public static void after() throws Exception {
		dao.delete(cliCarregado);
		dao.delete(cliVazio);
	}
}