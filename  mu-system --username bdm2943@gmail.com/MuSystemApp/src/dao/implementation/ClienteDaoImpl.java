package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cliente;
import services.validator.Validator;
import dao.connection.ConnectionFactory;
import dao.excepetions.DataAccessException;
import exceptions.BusinessException;

public class ClienteDaoImpl implements ClienteDao{

	private Connection con;
	
	/**
	 * Grava um cliente na base
	 * de dados
	 */
	@Override
	public void save(Cliente e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		if(!isNovoCliente(e))
			throw new BusinessException("O cliente " + e + "já está cadastrado!");
		String sql = sqlInsertBuilder(e);
		try{		
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getNome());
			stmt.setObject(2,e.getRg());
			stmt.setObject(3,e.getEndereco());
			stmt.setObject(4,Validator.nlv(e.getBairro()));
			stmt.setObject(5,Validator.nlv(e.getCidade()));
			stmt.setObject(6,Validator.nlv(String.valueOf(e.getUf())));
			stmt.setObject(7,Validator.nlv(e.getTelefone()));
			stmt.setObject(8,Validator.nlv(e.getEmail()));
			System.out.println(stmt.toString());			
			stmt.execute();
			
			e.setCod(getIdCliente());
			
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
	}

	/**
	 * Busca pelo Id do último cliente
	 * inserido na base de dados!
	 * 
	 * @return Id do cliente
	 * @throws Exception
	 */
	private int getIdCliente() throws Exception {
		String sql = "SELECT cod_cliente FROM CLIENTE ORDER BY cod_cliente DESC LIMIT 1";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.execute();
		
		ResultSet result = stmt.getResultSet();
		result.next();
		return result.getInt(1);
		
	}

	/**
	 * Constroi o sql para inserir o
	 * cliente
	 * @param e cliente
	 * @return sql construido
	 * 
	 * @author bruno
	 */
	private String sqlInsertBuilder(Cliente e) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into cliente");
		sql.append("(");
		sql.append("nome_cliente,");
		sql.append("rg_cliente,");
		sql.append("endereco_cliente,");
		sql.append("bairro_cliente,");
		sql.append("cidade_cliente,");
		sql.append("uf_cliente,");
		sql.append("telefone_cliente,");
		sql.append("email_cliente");
		sql.append(")");
		sql.append(" values ");
		sql.append("(?, ?, ?, ?, ?, ?, ?, ?)");
		return sql.toString().toUpperCase();
	}

	/**
	 * Valida se um cliente já existe no banco
	 * @param e Cliente
	 * 
	 * @author bruno
	 */
	private boolean isNovoCliente(Cliente e) throws Exception{
		PreparedStatement stmt = null;
		ResultSet result       = null;
		
		String sql = "select * from cliente where rg_cliente like ?".toUpperCase();	
		stmt = con.prepareStatement(sql);
		
		try{
			stmt.setString(1, e.getRg());
			System.out.println(stmt.toString());
			stmt.execute();
			result = stmt.getResultSet();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}
		
		return !result.next();
	}

	/**
	 * Deleta um cliente da base de
	 * dados
	 */
	@Override
	public void delete(Cliente e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "delete from cliente where rg_cliente like ?".toUpperCase();
		
		try{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, e.getRg());
			
			System.out.println(stmt.toString());
			
			stmt.execute();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
	}

	/**
	 * Atualiza um cliente na
	 * base de dados
	 * @throws Exception 
	 * 
	 */
	@Override
	public void update(Cliente e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;		
		
		String sql = sqlUpdateBuilder(e);

		try{
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getNome());
			stmt.setObject(2,e.getRg());
			stmt.setObject(3,e.getEndereco());
			stmt.setObject(4,Validator.nlv(e.getBairro()));
			stmt.setObject(5,Validator.nlv(e.getCidade()));
			stmt.setObject(6,Validator.nlv(String.valueOf(e.getUf())));
			stmt.setObject(7,Validator.nlv(e.getTelefone()));
			stmt.setObject(8,Validator.nlv(e.getEmail()));
			stmt.setObject(9,e.getCod());
			System.out.println(stmt.toString());
			
			stmt.execute();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
	}

	/**
	 * Constroi o sql para atualizar o
	 * cliente na base de dados
	 * @param e cliente
	 * @return sql construido
	 * 
	 * @author bruno
	 */
	private String sqlUpdateBuilder(Cliente e) {
		StringBuilder sql = new StringBuilder();
		sql.append("update cliente ");
		sql.append("set nome_cliente = ?, ");
		sql.append("rg_cliente = ?, ");
		sql.append("endereco_cliente = ?, ");
		sql.append("bairro_cliente = ? ,");
		sql.append("cidade_cliente = ? ,");
		sql.append("uf_cliente = ? ,");
		sql.append("telefone_cliente = ? ,");
		sql.append("email_cliente = ? ");
		sql.append("where cod_cliente = ?");
		return sql.toString().toUpperCase();
	}
	
}
