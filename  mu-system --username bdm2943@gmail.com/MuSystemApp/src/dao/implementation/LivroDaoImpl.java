package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import services.validator.Validator;
import model.Livro;
import dao.connection.ConnectionFactory;
import dao.excepetions.DataAccessException;
import dao.interfaces.LivroDao;
import dao.interfaces.SqlBuilder;
import exceptions.BusinessException;

public class LivroDaoImpl implements LivroDao, SqlBuilder{

	private Connection con;
	
	/**
	 * Grava um Livro na base
	 * de dados
	 */
	@Override
	public void save(Livro e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		if(!isNovoLivro(e))
			throw new BusinessException("O livro " + e + " já está cadastrado!");
		String sql = insertBuilder();
		try{		
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getTitulo());
			stmt.setObject(2,e.getAutor());
			stmt.setObject(3,e.getEditora());
			stmt.setObject(4,e.getPreco());
			stmt.setObject(5,e.getAno());
			System.out.println(stmt.toString());			
			stmt.execute();
			
			e.setCodigo(getIdLivro());
			
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
	}

	/**
	 * Busca pelo Id do último Livro
	 * inserido na base de dados!
	 * 
	 * @return Id do Livro
	 * @throws Exception
	 */
	private int getIdLivro() throws Exception {
		String sql = "SELECT cod_livro FROM LIVRO ORDER BY cod_livro DESC LIMIT 1";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.execute();
		
		ResultSet result = stmt.getResultSet();
		result.next();
		return result.getInt(1);
		
	}


	/**
	 * Valida se um Livro já existe no banco
	 * @param e Livro
	 * 
	 * @author joão
	 */
	private boolean isNovoLivro(Livro e) throws Exception{
		PreparedStatement stmt = null;
		ResultSet result       = null;
		
		String sql = "select * from livro where titulo_livro like ?".toUpperCase();	
		stmt = con.prepareStatement(sql);
		
		try{
			stmt.setString(1, e.getTitulo());
			System.out.println(stmt.toString());
			stmt.execute();
			result = stmt.getResultSet();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}
		
		return !result.next();
	}

	/**
	 * Deleta um Livro da base de
	 * dados
	 */
	@Override
	public void delete(Livro e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "delete from LIVRO where cod_livro = ?".toUpperCase();
		
		try{
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, e.getCodigo());
			
			System.out.println(stmt.toString());
			
			stmt.execute();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
	}

	/**
	 * Atualiza um Livro na
	 * base de dados
	 * @throws Exception 
	 * 
	 */
	@Override
	public void update(Livro e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;		
		
		String sql = updateBuilder();

		try{
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getTitulo());
			stmt.setObject(2,Validator.nlv(e.getAutor()));
			stmt.setObject(3,Validator.nlv(e.getEditora()));
			stmt.setObject(4,e.getPreco());
			stmt.setObject(5,Validator.nlv(e.getAno()));
			stmt.setObject(6,e.getCodigo());
			System.out.println(stmt.toString());
			
			stmt.execute();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
	}



	/**
	 * Lista todos os Livros cadastrados na base de dados
	 */
	@Override
	public List<Livro> listAll() throws Exception {
		List<Livro> lista = new ArrayList<Livro>();
		Livro livro  = null;
		String sql = "select * from Livro".toUpperCase();
		
		con = ConnectionFactory.getConnection();		
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet    result = null;		
		stmt.execute();	
		result = stmt.getResultSet();
		
		while(result.next()){
			livro = new Livro();
			livro.setCodigo((result.getInt("cod_livro")));
		    livro.setTitulo(result.getString("titulo_livro"));
		    livro.setAutor(result.getString("autor_livro"));
		    livro.setEditora(result.getString("editora_livro"));
		    livro.setPreco(result.getFloat("preco_livro"));
		    livro.setAno(result.getInt("ano_livro"));		    
		    lista.add(livro);              
		}
		return lista;
	}

	/**
	 * Cria a query para insert no banco
	 * @author joão
	 */
	@Override
	public String insertBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Livro");
		sql.append("(");
		sql.append("titulo_livro,");
		sql.append("autor_livro,");
		sql.append("editora_livro,");
		sql.append("preco_livro,");
		sql.append("ano_livro");
		sql.append(")");
		sql.append(" values ");
		sql.append("(?, ?, ?, ?, ?)");
		return sql.toString().toUpperCase();
	}

	/**
	 * Cria a query para update no banco
	 * @author joão
	 */
	@Override
	public String updateBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("update LIVRO ");
		sql.append("set titulo_livro = ?, ");
		sql.append("autor_livro = ?, ");
		sql.append("editora_livro = ?, ");
		sql.append("preco_livro = ? ,");
		sql.append("ano_livro = ? ");
		sql.append("where cod_livro = ?");
		return sql.toString().toUpperCase();
	}
	
}