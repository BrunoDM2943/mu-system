package testCases.sprint1;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.Media;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.excepetions.DataAccessException;
import dao.implementation.MediaDaoImpl;
import enums.TiposMidia;
import exceptions.BusinessException;


public class MidiaDaoImplTest{

	private static MediaDaoImpl dao = new MediaDaoImpl();
	private static Media midiaCarregado = new Media();
	private static Media midiaVazio     = new Media();
	
	
	@BeforeClass
	public static void before() throws BusinessException {
		midiaCarregado.setTitulo("Produto Carregado");
		midiaCarregado.setDistribuidora("Distro");
		midiaCarregado.setTipo(TiposMidia.DVD.toString());
		midiaCarregado.setAutor("Eu");
		midiaCarregado.setPreco(55F);
		midiaCarregado.setAno(2014);
	}
	
	@After
	public void afterMethod() throws Exception{
		dao.delete(midiaCarregado);
	}
	
	@Test
	public void TC01GravarCarregado() throws Exception{
		dao.save(midiaCarregado);
		
	}
	
	@Test(expected = DataAccessException.class)
	public void TC02GravarVazio() throws Exception{
		dao.save(midiaVazio);
	}
	
	@Test(expected = BusinessException.class)
	public void TC03GravarDuasVezes() throws Exception{
		dao.save(midiaCarregado);
		dao.save(midiaCarregado);
	}
	
	@Test
	public void TC04DeletarMidia() throws Exception{
		dao.save(midiaCarregado);
	}

	@Test
	public void TC05AtualizarMidia() throws Exception {		
		dao.save(midiaCarregado);
		midiaCarregado.setTitulo("Novo titulo");			
		dao.update(midiaCarregado);		
	}
	
	@Test
	public void TC06LerTodos() throws Exception {
		dao.save(midiaCarregado);
		List<Media> lista = dao.listAll();
		assertTrue(lista.contains(midiaCarregado));
	}
	
	

	@AfterClass
	public static void after() throws Exception {
		dao.delete(midiaCarregado);
	}
}