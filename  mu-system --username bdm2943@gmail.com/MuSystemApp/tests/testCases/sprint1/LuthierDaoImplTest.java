package testCases.sprint1;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.Luthier;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.implementation.LuthierDaoImpl;
import dao.interfaces.LuthierDao;
import exceptions.BusinessException;


public class LuthierDaoImplTest{

	private static LuthierDao dao = new LuthierDaoImpl();
	private static Luthier luthierCarregado = new Luthier();
	private static Luthier luthierVazio     = new Luthier();
	
	
	@BeforeClass
	public static void before() throws BusinessException {
		luthierCarregado.setNome("Luthier Teste");
		luthierCarregado.setBairro("Parque Boturussu");
		luthierCarregado.setCidade("Sao Paulo");
		luthierCarregado.setEmail("bdm2943@gmail.com");
		luthierCarregado.setEndereco("Dario Costa Mattos, 661");
		luthierCarregado.setCpf("36.075.532-A");
		luthierCarregado.setTelefone("997840151");
		luthierCarregado.setUf("SP");
//		
//		luthierVazio.setNome("Nome Vazio");
//		luthierVazio.setRg("19");
//		luthierVazio.setEndereco("endereco vazio");
//		luthierVazio.setUf("RJ");
	}
	
	@After
	public void after() throws Exception{
		dao.delete(luthierCarregado);
		dao.delete(luthierVazio);
	}
	
	@Test
	public void TC01GravarCarregado() throws Exception{
		dao.save(luthierCarregado);			
	}
	
	@Test
	public void TC02GravarVazio() throws Exception{
		dao.save(luthierVazio);
	}
	
	@Test(expected = BusinessException.class)
	public void TC03GravarDuasVezes() throws Exception{
		dao.save(luthierCarregado);
		dao.save(luthierCarregado);
	}
	
	@Test
	public void TC04DeletarLuthier() throws Exception{
		dao.save(luthierCarregado);
		dao.delete(luthierCarregado);
	}

	@Test
	public void TC05AtualizarLuthier() throws Exception {
		dao.save(luthierCarregado);
		luthierCarregado.setNome("Novo nome");			
		dao.update(luthierCarregado);
		
	}
	
	@Test
	public void TC06LerTodos() throws Exception {
		dao.save(luthierCarregado);
		List<Luthier> lista = dao.listAll();
		assertTrue(lista.contains(luthierCarregado));	
	}
	
}