package testCases.sprint1;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.Fabricante;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.excepetions.DataAccessException;
import dao.implementation.FabricanteDaoImpl;
import exceptions.BusinessException;


public class FabricanteDaoImplTest{

	private static FabricanteDaoImpl dao = new FabricanteDaoImpl();
	private static Fabricante fabricanteCarregado = new Fabricante();
	private static Fabricante fabricanteVazio     = new Fabricante();
	
	
	@BeforeClass
	public static void before() throws BusinessException {
		fabricanteCarregado.setNome("Sombra");
		fabricanteCarregado.setContato("Bruno");
		fabricanteCarregado.setTelefone("19");			
	}
	
	@After
	public void after() throws Exception{
		dao.delete(fabricanteCarregado);
		dao.delete(fabricanteVazio);
	}
	
	@Test
	public void TC01GravarCarregado() throws Exception{
		dao.save(fabricanteCarregado);			
	}
	
	@Test(expected = DataAccessException.class)
	public void TC02GravarVazio() throws Exception{
		dao.save(fabricanteVazio);
	}
	
	@Test(expected = BusinessException.class)
	public void TC03GravarDuasVezes() throws Exception{
		dao.save(fabricanteCarregado);
		dao.save(fabricanteCarregado);
	}
	
	@Test
	public void TC04DeletarFabricante() throws Exception{
		dao.save(fabricanteCarregado);
		dao.delete(fabricanteCarregado);
	}

	@Test
	public void TC05AtualizarFabricante() throws Exception {	
		dao.save(fabricanteCarregado);
		fabricanteCarregado.setNome("Novo nome");			
		dao.update(fabricanteCarregado);		
	}
	
	@Test
	public void TC06LerTodos() throws Exception {
		dao.save(fabricanteCarregado);
		List<Fabricante> lista = dao.listAll();
		assertTrue(lista.contains(fabricanteCarregado));
	}

}