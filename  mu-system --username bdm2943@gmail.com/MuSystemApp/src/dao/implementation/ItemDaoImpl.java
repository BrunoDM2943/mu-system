package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Acessorio;
import model.Comercializavel;
import model.Instrumento;
import model.Item;
import model.Livro;
import model.Media;
import services.validator.Validator;
import dao.connection.ConnectionFactory;
import dao.interfaces.AcessorioDao;
import dao.interfaces.DataAccessObject;
import dao.interfaces.InstrumentoDao;
import dao.interfaces.ItemDao;
import dao.interfaces.LivroDao;
import dao.interfaces.MediaDao;

public class ItemDaoImpl implements ItemDao{

	private Connection con;
	
	@Override
	public void save(List<Item> itens) throws Exception {
		con = ConnectionFactory.getConnection();
		for(Item item : itens){
			save(item);
		}
	}
	
	/**
	 * Retorna o nome da coluna q deve ser persistida 
	 * conforme a classe passada
	 * 
	 * @param clazz Classe do item
	 * @return Nome da coluna na table
	 */
	private String getColunaByClazz(Class<?> clazz) {
		if(clazz.equals(Livro.class))
			return "cod_livro";
		if(clazz.equals(Media.class))
			return "cod_midia";
		if(clazz.equals(Acessorio.class))
			return "cod_acessorio";
		if(clazz.equals(Instrumento.class))
			return "cod_instrumento";
		return null;
	}

	private String insertBuilder(String campo){
		StringBuilder builder = new StringBuilder();
		builder.append("insert into ITEM ");
		builder.append("(");
		builder.append(campo + ",");
		builder.append("cod_venda,");
		builder.append("quantidade");
		builder.append(")");
		builder.append(" values ");
		builder.append("(?,?,?)");
		
		return builder.toString().toUpperCase();
	}

	@Override
	public List<Item> listAll(int id) throws Exception {
		con = ConnectionFactory.getConnection();
		
		InstrumentoDao instruDao = new InstrumentoDaoImpl();
		AcessorioDao   acessoDao = new AcessorioDaoImpl();
		MediaDao	   mediaDao  = new MediaDaoImpl();
		LivroDao	   livroDao  = new LivroDaoImpl();
		PreparedStatement stmt = null;
		ResultSet rs           = null;
		List<Item> itens = new ArrayList<Item>();
		
		
		String sql = "select * from ITEM where cod_venda = ?".toUpperCase();
		int codInstrumento = 0;
		int codAcessorio   = 0; 
		int codMidia       = 0;
		int codLivro	   = 0;
		int qtd 		   = 0;
		
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		
		stmt.execute();
		rs = stmt.getResultSet();
		
		while(rs.next()){
			codInstrumento = rs.getInt("cod_instrumento");
			codAcessorio   = rs.getInt("cod_acessorio");
			codMidia       = rs.getInt("cod_midia");
			codLivro	   = rs.getInt("cod_livro");
			qtd 		   = rs.getInt("quantidade");
						
			itens.add(getItemByCod(codInstrumento, instruDao, qtd));
			itens.add(getItemByCod(codAcessorio, acessoDao, qtd));
			itens.add(getItemByCod(codMidia, mediaDao, qtd));
			itens.add(getItemByCod(codLivro, livroDao, qtd));
			
		}
		
		
		return itens;
	}
	
	/**
	 * Retorna a entidade relacionada ao codigo
	 *
	 * @param codigo Codigo da entidade
	 * @param dao  Dao pertencente
	 * @param qtd Qtd de itens
	 * @return O item relacionado, ou null, se o código for Zero
	 * @throws Exception
	 * 
	 * @author bruno
	 */
	@SuppressWarnings("rawtypes")
	private Item getItemByCod(int codigo, DataAccessObject dao, int qtd) throws Exception{
		if(Validator.isZero(codigo))
			return null;
		
		Comercializavel comex = null;
		
		comex = (Comercializavel) dao.getById(codigo);
		Item item = new Item(comex);
		
		item.setQtd(qtd);
		return item;
		
	}

	@Override
	public void save(Item e) throws Exception {
		PreparedStatement stmt = null;
		String coluna = "";
		String sql = "";
		Comercializavel comex = null;
		Class<? extends Comercializavel> clazz = null;
		
		comex = e.getComercilizavel();
		clazz = comex.getClass();
		coluna = getColunaByClazz(clazz);
		sql = insertBuilder(coluna);
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, comex.getCodigo());
		stmt.setInt(2, e.getVenda().getCodigo());
		stmt.setInt(3, e.getQtd());
		
		System.out.println(stmt.toString());
		stmt.execute();
		
		e.setCodigo(getCodigoItem());
	}

	private int getCodigoItem() throws SQLException {
		String sql = "SELECT cod_item FROM ITEM ORDER BY cod_item DESC LIMIT 1";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.execute();
		
		ResultSet result = stmt.getResultSet();
		result.next();
		return result.getInt(1);		
	}

	@Override
	public void delete(Item e) throws Exception {
		PreparedStatement stmt = null;
		String sql = "delete from item where cod_item = ?".toUpperCase();
		
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, e.getCodigo());
		stmt.execute();
		
	}

	@Override
	public void update(Item e) throws Exception {}

	@Override
	public List<Item> listAll() throws Exception {return null;}
	
	@Override
	public Item getById(int cod) throws Exception {return null;}

	@Override
	public void deleteAll(List<Item> itens) throws Exception {
		con = ConnectionFactory.getConnection();
		for(Item item : itens){
			delete(item);
		}
		
	}

	}
