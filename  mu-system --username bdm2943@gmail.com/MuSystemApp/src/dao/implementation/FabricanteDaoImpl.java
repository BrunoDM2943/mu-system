package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Fabricante;
import services.validator.Validator;
import dao.connection.ConnectionFactory;
import dao.excepetions.DataAccessException;
import dao.interfaces.FabricanteDao;
import dao.interfaces.SqlBuilder;
import exceptions.BusinessException;

public class FabricanteDaoImpl implements FabricanteDao, SqlBuilder {
	
private Connection con;
	
	/**
	 * Grava um fabricante na base
	 * de dados
	 */
	@Override
	public void save(Fabricante e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		if(!isNovoFabricante(e))
			throw new BusinessException("O fabricante " + e + "já está cadastrado!");
		String sql = insertBuilder();
		try{		
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getNome());
			stmt.setObject(2,e.getTelefone());
			stmt.setObject(3,Validator.nlv(e.getContato()));
			System.out.println(stmt.toString());			
			stmt.execute();
			
			e.setCod(getIdFabricante());
			
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
	}

	/**
	 * Busca pelo Id do último fabricante
	 * inserido na base de dados!
	 * 
	 * @return Id do fabricante
	 * @throws Exception
	 */
	private int getIdFabricante() throws Exception {
		String sql = "SELECT cod_fabricante FROM FABRICANTE ORDER BY cod_fabricante DESC LIMIT 1";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.execute();
		
		ResultSet result = stmt.getResultSet();
		result.next();
		return result.getInt(1);
		
	}


	/**
	 * Valida se um fabricante já existe no banco
	 * @param e Fabricante
	 * 
	 * @author bruno e joão
	 */
	private boolean isNovoFabricante(Fabricante e) throws Exception{
		PreparedStatement stmt = null;
		ResultSet result       = null;
		
		String sql = "select * from fabricante where nome_fabricante like ?".toUpperCase();	
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
	 * Deleta um fabricante da base de
	 * dados
	 */
	@Override
	public void delete(Fabricante e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "delete from fabricante where cod_fabricante like ?".toUpperCase();
		
		try{
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, e.getCod());
			
			System.out.println(stmt.toString());
			
			stmt.execute();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
	}

	/**
	 * Atualiza um fabricante na
	 * base de dados
	 * @throws Exception 
	 * 
	 */
	@Override
	public void update(Fabricante e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;		
		
		String sql = updateBuilder();

		try{
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getNome());
			stmt.setObject(2,e.getTelefone());
			stmt.setObject(3,e.getContato());
			stmt.setObject(4,e.getCod());
			System.out.println(stmt.toString());
			
			stmt.execute();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
	}



	/**
	 * Lista todos os fabricantes cadastrados na base de dados
	 */
	@Override
	public List<Fabricante> listAll() throws Exception {
		List<Fabricante> lista = new ArrayList<Fabricante>();
		Fabricante f  = null;
		String sql = "select * from fabricante".toUpperCase();
		
		con = ConnectionFactory.getConnection();		
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet    result = null;		
		stmt.execute();	
		result = stmt.getResultSet();
		
		while(result.next()){
			f = new Fabricante();
			f.setCod(result.getInt("cod_fabricante"));
			f.setNome(result.getString("nome_fabricante"));
		    f.setTelefone(result.getString("telefone_fabricante"));
		    f.setContato(result.getString("nome_contato"));		 
		    
		    lista.add(f);			                           
		}
		return lista;
	}

	@Override
	public String insertBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into fabricante");
		sql.append("(");
		sql.append("nome_fabricante,");
		sql.append("telefone_fabricante,");
		sql.append("nome_contato");
		sql.append(")");
		sql.append(" values ");
		sql.append("(?, ?, ?)");
		return sql.toString().toUpperCase();
	}

	@Override
	public String updateBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("update fabricante ");
		sql.append("set nome_fabricante = ?, ");
		sql.append("telefone_fabricante = ?, ");
		sql.append("nome_contato = ? ");
		sql.append("where cod_fabricante = ?");
		return sql.toString().toUpperCase();
	}

	/**
	 * Busca um fabricante na base pelo seu
	 * id
	 * 
	 * @param id id do fabricante
	 * @return Fabricante
	 * @throws Exception
	 * 
	 * @author bruno
	 */
	@Override
	public Fabricante getFabricanteById(int id) throws Exception {
		Fabricante fabricante = null;
		ResultSet result      = null;
		String sql = "select * from fabricante where cod_fabricante = ? ".toUpperCase();
		
		con = ConnectionFactory.getConnection();		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
				
		stmt.execute();	
		result = stmt.getResultSet();
		
		
		
		while(result.next()){
			fabricante = new Fabricante();
			fabricante.setCod(result.getInt("cod_fabricante"));
			fabricante.setNome(result.getString("nome_fabricante"));
		    fabricante.setTelefone(result.getString("telefone_fabricante"));
		    fabricante.setContato(result.getString("nome_contato"));		    		    			                          
		}
				
		return fabricante;
	}
	
}