package testCases.sprint1;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import model.Acessorio;
import model.Cliente;
import model.Fabricante;
import model.Instrumento;
import model.Item;
import model.Livro;
import model.Media;
import model.Venda;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoFacade;
import enums.TiposMidia;

public class VendaDaoImplTest {
	
	private static Venda venda;
	private static Cliente cli;
	private static Item itemLivro;
	private static Item itemMedia;
	private static Item itemInstrumento;
	private static Item itemAcessorio;

	private static Livro livro;
	private static Media media;
	private static Instrumento instrumento;
	private static Fabricante fabricante;
	private static Acessorio acessorio;
 
	private static List<Item> listaItems;
	
	@BeforeClass
	public static void tearUp() throws Exception {
		cli = new Cliente();
		cli.setNome("Teste");
		cli.setRg("36075532B");
		cli.setEndereco("endereco");
		cli.setUf("SP");
		DaoFacade.save(cli);
		
		livro = new Livro();
		livro.setTitulo("Livro teste");	
		livro.setPreco(10F);
		DaoFacade.save(livro);
		
		fabricante = new Fabricante();
		fabricante.setNome("Sombra");
		fabricante.setContato("Bruno");
		fabricante.setTelefone("19");	
		
		DaoFacade.save(fabricante);
		
		instrumento = new Instrumento();
		instrumento.setNome("TecladoTeste");
		instrumento.setPreco(100f);
		instrumento.setEspecificacao("teclas");
		instrumento.setTipo("corda");
		instrumento.setFabricante(fabricante);
		
		DaoFacade.save(instrumento);
		
		media = new Media();
		media.setTitulo("Produto Carregado");
		media.setDistribuidora("Distro");
		media.setTipo(TiposMidia.DVD.toString());
		media.setAutor("Eu");
		media.setPreco(55F);
		media.setAno(2014);
					
		DaoFacade.save(media);
		
		acessorio = new Acessorio();
		acessorio.setNome("Que coisa cara");
		acessorio.setPreco(19F);			
		DaoFacade.save(acessorio);

		itemLivro = new Item(livro);
		itemLivro.setQtd(2);
		
		itemMedia = new Item(media);
		itemMedia.setQtd(1);
		
		itemInstrumento = new Item(instrumento);
		itemInstrumento.setQtd(5);
		
		itemAcessorio = new Item(acessorio);
		itemAcessorio.setQtd(6);
		
		listaItems = Arrays.asList(itemAcessorio, itemInstrumento, itemLivro, itemMedia);
		
			
		venda = new Venda();
		venda.setCliente(cli);
		venda.setDataVenda(new Date());
		venda.setItens(listaItems);
		
		listaItems.forEach(e -> e.setVenda(venda));		
	}
	
	@AfterClass
	public static void tearDown() throws Exception{
		DaoFacade.delete(venda);
		DaoFacade.delete(cli);
		DaoFacade.delete(livro);
		DaoFacade.delete(media);
		DaoFacade.delete(acessorio);
		DaoFacade.delete(instrumento);		
		DaoFacade.delete(fabricante);
	}
	
	@Test
	public void CT01GravarVenda() throws Exception{
		DaoFacade.save(venda);
	}
	
	@Test
	public void CT02SomaVenda() throws Exception{
		float somaTotal = 689f;
		float somaVenda = venda.somar();
		assertTrue(somaTotal == somaVenda);
	}

}
