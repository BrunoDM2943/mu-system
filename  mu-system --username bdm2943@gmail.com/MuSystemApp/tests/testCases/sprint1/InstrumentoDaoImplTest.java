package testCases.sprint1;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.Fabricante;
import model.Instrumento;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoFacade;
import dao.implementation.InstrumentoDaoImpl;
import dao.interfaces.InstrumentoDao;
import exceptions.BusinessException;


public class InstrumentoDaoImplTest{

	private static InstrumentoDao dao = new InstrumentoDaoImpl();
	private static Fabricante fabricanteCarregado = new Fabricante();
	private static Instrumento instrumentoCarregado = new Instrumento();
	
	
	@BeforeClass
	public static void before() throws Exception {
		fabricanteCarregado.setNome("Sombra");
		fabricanteCarregado.setContato("Bruno");
		fabricanteCarregado.setTelefone("19");
		
		DaoFacade.save(fabricanteCarregado);
		
		instrumentoCarregado.setNome("TecladoTeste");
		instrumentoCarregado.setPreco(100f);
		instrumentoCarregado.setEspecificacao("teclas");
		instrumentoCarregado.setTipo("corda");
		instrumentoCarregado.setFabricante(fabricanteCarregado);
		
	}
	
	@After
	public void after() throws Exception{		
		dao.delete(instrumentoCarregado);		
	}
	
	@Test
	public void TC01GravarCarregado() throws Exception{
		dao.save(instrumentoCarregado);			
	}
	
	@Test(expected = BusinessException.class)
	public void TC02GravarDuasVezes() throws Exception{
		dao.save(instrumentoCarregado);
		dao.save(instrumentoCarregado);
	}
	
	@Test
	public void TC03DeletarFabricante() throws Exception{
		dao.save(instrumentoCarregado);
		dao.delete(instrumentoCarregado);
	}

	@Test
	public void TC04AtualizarFabricante() throws Exception {	
		dao.save(instrumentoCarregado);
		fabricanteCarregado.setNome("Novo nome");			
		dao.update(instrumentoCarregado);		
	}
	
	@Test
	public void TC05LerTodos() throws Exception {
		dao.save(instrumentoCarregado);
		List<Instrumento> lista = dao.listAll();
		assertTrue(lista.contains(instrumentoCarregado));
	}
	
	@AfterClass
	public static void afterClass() throws Exception{
		DaoFacade.delete(fabricanteCarregado);
		DaoFacade.delete(instrumentoCarregado);
	}

}