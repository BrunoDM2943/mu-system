package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Acessorio;
import dao.connection.ConnectionFactory;
import dao.excepetions.DataAccessException;
import dao.interfaces.AcessorioDao;
import dao.interfaces.SqlBuilder;
import exceptions.BusinessException;

public class AcessorioDaoImpl implements AcessorioDao, SqlBuilder{

	private Connection con;
	
	/**
	 * Grava um Acessorio na base
	 * de dados
	 */
	@Override
	public void save(Acessorio e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		if(!isNovoAcessorio(e))
			throw new BusinessException("O acessorio " + e + " já está cadastrado!");
		String sql = insertBuilder();
		try{		
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getNome());
			stmt.setObject(2,e.getPreco());			
			System.out.println(stmt.toString());			
			stmt.execute();
			
			e.setCodigo(getIdAcessorio());
			
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
	}

	/**
	 * Busca pelo Id do último Acessorio
	 * inserido na base de dados!
	 * 
	 * @return Id do Acessorio
	 * @throws Exception
	 */
	private int getIdAcessorio() throws Exception {
		String sql = "SELECT cod_acessorio FROM ACESSORIO ORDER BY cod_acessorio DESC LIMIT 1";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.execute();
		
		ResultSet result = stmt.getResultSet();
		result.next();
		return result.getInt(1);
		
	}


	/**
	 * Valida se um Acessorio já existe no banco
	 * @param e Acessorio
	 * 
	 * @author bruno
	 */
	private boolean isNovoAcessorio(Acessorio e) throws Exception{
		PreparedStatement stmt = null;
		ResultSet result       = null;
		
		String sql = "select * from acessorio where nome_acessorio like ?".toUpperCase();	
		stmt = con.prepareStatement(sql);
		
		try{
			stmt.setString(1, e.getNome());
			System.out.println(stmt.toString());
			stmt.execute();
			result = stmt.getResultSet();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}
		
		return !result.next();
	}

	/**
	 * Deleta um Acessorio da base de
	 * dados
	 */
	@Override
	public void delete(Acessorio e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "delete from ACESSORIO where cod_acessorio = ?".toUpperCase();
		
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
	 * Atualiza um Acessorio na
	 * base de dados
	 * @throws Exception 
	 * 
	 */
	@Override
	public void update(Acessorio e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;		
		
		String sql = updateBuilder();

		try{
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getNome());
			stmt.setObject(2,e.getPreco());	
			stmt.setObject(3, e.getCodigo());
			System.out.println(stmt.toString());
			
			stmt.execute();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
	}



	/**
	 * Lista todos os Acessorios cadastrados na base de dados
	 */
	@Override
	public List<Acessorio> listAll() throws Exception {
		List<Acessorio> lista = new ArrayList<Acessorio>();
		Acessorio acessorio  = null;
		String sql = "select * from Acessorio".toUpperCase();
		
		con = ConnectionFactory.getConnection();		
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet    result = null;		
		stmt.execute();	
		result = stmt.getResultSet();
		
		while(result.next()){
			acessorio = new Acessorio();
			acessorio.setCodigo(result.getInt(1));
			acessorio.setNome(result.getString(2));
			acessorio.setPreco(result.getFloat(3));
		    
		    lista.add(acessorio);			                           
		}
		return lista;
	}

	@Override
	public String insertBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ACESSORIO");
		sql.append("(");
		sql.append("nome_acessorio,");
		sql.append("preco_acessorio");
		sql.append(")");
		sql.append(" values ");
		sql.append("(?, ?)");
		return sql.toString().toUpperCase();
	}

	@Override
	public String updateBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("update acessorio ");
		sql.append("set nome_acessorio = ?, ");
		sql.append("preco_acessorio = ?");		
		sql.append("where cod_acessorio = ?");
		return sql.toString().toUpperCase();
	}
	
}
