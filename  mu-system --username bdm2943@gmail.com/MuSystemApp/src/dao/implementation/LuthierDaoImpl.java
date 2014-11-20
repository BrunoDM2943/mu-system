package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Luthier;
import services.validator.Validator;
import dao.connection.ConnectionFactory;
import dao.excepetions.DataAccessException;
import dao.interfaces.LuthierDao;
import dao.interfaces.SqlBuilder;
import enums.Especialidade;
import enums.Estado;
import exceptions.BusinessException;

public class LuthierDaoImpl implements LuthierDao, SqlBuilder{
	
	private Connection con;
	
	/**
	 * Grava um luthier na base
	 * de dados
	 */
	@Override
	public void save(Luthier e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		if(!isNovoLuthier(e))
			throw new BusinessException("O luthier " + e + "já está cadastrado!");
		String sql = insertBuilder();
		try{		
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getNome());
			stmt.setObject(2,e.getCpf());
			stmt.setObject(3,e.getEndereco());
			stmt.setObject(4,Validator.nlv(e.getBairro()));
			stmt.setObject(5,Validator.nlv(e.getCidade()));
			stmt.setObject(6,Validator.nlv(String.valueOf(e.getUf())));
			stmt.setObject(7,Validator.nlv(e.getTelefone()));
			stmt.setObject(8,Validator.nlv(e.getEmail()));
			stmt.setObject(9,e.getEspecialidade());
			System.out.println(stmt.toString());			
			stmt.execute();
			
			e.setCod(getIdLuthier());
			
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
	}

	/**
	 * Busca pelo Id do último luthier
	 * inserido na base de dados!
	 * 
	 * @return Id do LUTHIER
	 * @throws Exception
	 */
	private int getIdLuthier() throws Exception {
		String sql = "SELECT cod_luthier FROM LUTHIER ORDER BY cod_luthier DESC LIMIT 1";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.execute();
		
		ResultSet result = stmt.getResultSet();
		result.next();
		return result.getInt(1);
		
	}


	/**
	 * Valida se um luthier já existe no banco
	 * @param e Luthier
	 * 
	 * @author joão
	 */
	private boolean isNovoLuthier(Luthier e) throws Exception{
		PreparedStatement stmt = null;
		ResultSet result       = null;
		
		String sql = "select * from luthier where cpf_luthier like ?".toUpperCase();	
		stmt = con.prepareStatement(sql);
		
		try{
			stmt.setString(1, e.getCpf());
			System.out.println(stmt.toString());
			stmt.execute();
			result = stmt.getResultSet();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}
		
		return !result.next();
	}

	/**
	 * Deleta um luthier da base de
	 * dados
	 */
	@Override
	public void delete(Luthier e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "delete from luthier where cod_luthier = ?".toUpperCase();
		
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
	 * Atualiza um luthier na
	 * base de dados
	 * @throws Exception 
	 * 
	 */
	@Override
	public void update(Luthier e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;		
		
		String sql = updateBuilder();

		try{
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getNome());
			stmt.setObject(2,e.getCpf());
			stmt.setObject(3,e.getEndereco());
			stmt.setObject(4,Validator.nlv(e.getBairro()));
			stmt.setObject(5,Validator.nlv(e.getCidade()));
			stmt.setObject(6,Validator.nlv(String.valueOf(e.getUf())));
			stmt.setObject(7,Validator.nlv(e.getTelefone()));
			stmt.setObject(8,Validator.nlv(e.getEmail()));
			stmt.setObject(9,e.getEspecialidade());
			stmt.setObject(10,e.getCod());
			System.out.println(stmt.toString());
			
			stmt.execute();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
	}



	/**
	 * Lista todos os luthier cadastrados na base de dados
	 */
	@Override
	public List<Luthier> listAll() throws Exception {
		List<Luthier> lista = new ArrayList<Luthier>();
		Luthier f  = null;
		String sql = "select * from luthier".toUpperCase();
		
		con = ConnectionFactory.getConnection();		
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet    result = null;		
		stmt.execute();	
		result = stmt.getResultSet();
		
		while(result.next()){
			f = new Luthier();
			f.setNome(result.getString(1));
		    f.setCpf(result.getString(2));
		    f.setEndereco(result.getString(3));
		    f.setBairro(result.getString(4));
		    f.setCidade(result.getString(5));
		    f.setUf(Estado.valueOf(result.getString(6)));
		    f.setTelefone(result.getString(7));
		    f.setEmail(result.getString(8));
		    f.setEspecialidade(Especialidade.valueOf(result.getString(9)));
		    f.setCod(result.getInt(10));
		    
		    lista.add(f);			                           
		}
		return lista;
	}

	@Override
	public String insertBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into luthier");
		sql.append("(");
		sql.append("nome_luthier,");
		sql.append("cpf_luthier,");
		sql.append("endereco_luthier,");
		sql.append("bairro_luthier,");
		sql.append("cidade_luthier,");
		sql.append("uf_luthier,");
		sql.append("telefone_luthier,");
		sql.append("email_luthier,");
		sql.append("especialidade_luthier");
		sql.append(")");
		sql.append(" values ");
		sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return sql.toString().toUpperCase();
	}

	@Override
	public String updateBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("update luthier ");
		sql.append("set nome_luthier = ?, ");
		sql.append("cpf_luthier = ?, ");
		sql.append("endereco_luthier = ?, ");
		sql.append("bairro_luthier = ? ,");
		sql.append("cidade_luthier = ? ,");
		sql.append("uf_luthier = ? ,");
		sql.append("telefone_luthier = ? ,");
		sql.append("email_luthier = ? ,");
		sql.append("especialidade_luthier = ?");
		sql.append("where cod_luthier = ?");
		return sql.toString().toUpperCase();	
	}

}