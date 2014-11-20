package testCases.sprint1;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.Acessorio;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.implementation.AcessorioDaoImpl;
import exceptions.BusinessException;


public class AcessorioDaoImplTest{

	private static AcessorioDaoImpl dao = new AcessorioDaoImpl();
	private static Acessorio AcessorioCarregado = new Acessorio();

	
	
	@BeforeClass
	public static void before() throws BusinessException {
		AcessorioCarregado.setNome("Que coisa cara");
		AcessorioCarregado.setPreco(19F);			
	}
	
	@After
	public void after() throws Exception{
		dao.delete(AcessorioCarregado);
	}
	
	@Test
	public void TC01GravarCarregado() throws Exception{
		dao.save(AcessorioCarregado);			
	}
	
	@Test(expected = BusinessException.class)
	public void TC03GravarDuasVezes() throws Exception{
		dao.save(AcessorioCarregado);
		dao.save(AcessorioCarregado);
	}
	
	@Test
	public void TC04DeletarAcessorio() throws Exception{
		dao.save(AcessorioCarregado);
		dao.delete(AcessorioCarregado);
	}
	

	@Test
	public void TC05AtualizarAcessorio() throws Exception {
		dao.save(AcessorioCarregado);
		AcessorioCarregado.setNome("Novo nome");			
		dao.update(AcessorioCarregado);
		
	}
	
	@Test
	public void TC06LerTodos() throws Exception {
		dao.save(AcessorioCarregado);
		List<Acessorio> lista = dao.listAll();
		assertTrue(lista.contains(AcessorioCarregado));
	}
	
	

	
}