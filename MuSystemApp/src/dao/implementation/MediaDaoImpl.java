package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Media;
import services.validator.Validator;
import dao.connection.ConnectionFactory;
import dao.excepetions.DataAccessException;
import dao.interfaces.MediaDao;
import dao.interfaces.SqlBuilder;
import exceptions.BusinessException;

public class MediaDaoImpl implements MediaDao, SqlBuilder{

	private Connection con;
	
	/**
	 * Grava uma midia na base
	 * de dados
	 */
	@Override
	public void save(Media e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		if(!isNovoMidia(e))
			throw new BusinessException("A midia " + e + " já está cadastrado!");
		String sql = insertBuilder();
		try{		
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getTitulo());
			stmt.setObject(2,e.getAutor());
			stmt.setObject(3,Validator.nlv(e.getDistribuidora()));
			stmt.setObject(4,Validator.nlv(e.getTipo()));
			stmt.setObject(5,e.getPreco());
			stmt.setObject(6,Validator.nlv(e.getAno()));
			System.out.println(stmt.toString());			
			stmt.execute();
			
			e.setCodigo(getIdMidia());
			
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
	}

	/**
	 * Busca pelo Id do último Midia
	 * inserido na base de dados!
	 * 
	 * @return Id do Midia
	 * @throws Exception
	 */
	private int getIdMidia() throws Exception {
		String sql = "SELECT cod_midia FROM MIDIA ORDER BY cod_midia DESC LIMIT 1";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.execute();
		
		ResultSet result = stmt.getResultSet();
		result.next();
		return result.getInt(1);
		
	}


	/**
	 * Valida se um Midia já existe no banco
	 * @param e Midia
	 * 
	 * @author bruno
	 */
	private boolean isNovoMidia(Media e) throws Exception{
		PreparedStatement stmt = null;
		ResultSet result       = null;
		
		String sql = "select * from MIDIA where titulo_midia like ?".toUpperCase();	
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
	 * Deleta um Midia da base de
	 * dados
	 */
	@Override
	public void delete(Media e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "delete from MIDIA where cod_midia = ?".toUpperCase();
		
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
	 * Atualiza uma midia na
	 * base de dados
	 * @throws Exception 
	 * 
	 */
	@Override
	public void update(Media e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;		
		
		String sql = updateBuilder();

		try{
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getCodigo());
			stmt.setObject(2,e.getTitulo());
			stmt.setObject(3,Validator.nlv(e.getAutor()));
			stmt.setObject(4,Validator.nlv(e.getDistribuidora()));
			stmt.setObject(5,Validator.nlv(e.getTipo()));
			stmt.setObject(6,e.getPreco());
			stmt.setObject(7,Validator.nlv(e.getAno()));
			System.out.println(stmt.toString());			
			stmt.execute();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
	}


	/**
	 * Lista todos os Midias cadastrados na base de dados
	 */
	@Override
	public List<Media> listAll() throws Exception {
		List<Media> lista = new ArrayList<Media>();
		Media midia  = null;
		String sql = "select * from Midia".toUpperCase();
		
		con = ConnectionFactory.getConnection();		
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet    result = null;		
		stmt.execute();	
		result = stmt.getResultSet();
		
		while(result.next()){
			midia = new Media();
			midia.setCodigo((result.getInt("cod_midia")));
		    midia.setTitulo(result.getString("titulo_midia"));
		    midia.setAutor(result.getString("autor_midia"));
		    midia.setDistribuidora(result.getString("dist_midia"));
		    midia.setTipo(result.getString("tipo_midia"));
		    midia.setPreco(result.getFloat("preco_midia"));
		    midia.setAno(result.getInt("ano_midia"));		    
		    lista.add(midia);              
		}
		return lista;
	}

	/**
	 * Cria a query para insert no banco
	 * @author bruno
	 */
	@Override
	public String insertBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Midia");
		sql.append("(");
		sql.append("titulo_midia,");
		sql.append("autor_midia,");
		sql.append("dist_midia,");
		sql.append("tipo_midia,");
		sql.append("preco_midia,");
		sql.append("ano_midia");
		sql.append(")");
		sql.append(" values ");
		sql.append("(?, ?, ?, ?, ?, ?)");
		return sql.toString().toUpperCase();
	}

	/**
	 * Cria a query para update no banco
	 * @author bruno 
	 */
	@Override
	public String updateBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("update MIDIA ");
		sql.append("set titulo_midia = ?, ");
		sql.append("autor_midia = ?, ");
		sql.append("dist_midia = ?, ");
		sql.append("tipo_midia = ? ,");
		sql.append("preco_midia = ? ,");
		sql.append("ano_midia = ? ");
		sql.append("where cod_midia = ?");
		return sql.toString().toUpperCase();
	}

	@Override
	public Media getById(int cod) throws Exception {
		Media media  = null;
		String sql = "select * from Midia".toUpperCase();
		
		con = ConnectionFactory.getConnection();		
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet    result = null;		
		stmt.execute();	
		result = stmt.getResultSet();
		
		while(result.next()){
			media = new Media();
			media.setCodigo((result.getInt("cod_midia")));
		    media.setTitulo(result.getString("titulo_midia"));
		    media.setAutor(result.getString("autor_midia"));
		    media.setDistribuidora(result.getString("dist_midia"));
		    media.setTipo(result.getString("tipo_midia"));
		    media.setPreco(result.getFloat("preco_midia"));
		    media.setAno(result.getInt("ano_midia"));		             
		}
		return media;
	}
	
}
