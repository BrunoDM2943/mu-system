package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Fabricante;
import model.Instrumento;
import services.validator.Validator;
import dao.connection.ConnectionFactory;
import dao.excepetions.DataAccessException;
import dao.interfaces.FabricanteDao;
import dao.interfaces.InstrumentoDao;
import dao.interfaces.SqlBuilder;
import exceptions.BusinessException;

public class InstrumentoDaoImpl implements InstrumentoDao , SqlBuilder {
	
	private Connection con;
	
	/**
	 * Grava um instrumento na base
	 * de dados
	 */		
	@Override
	public void save(Instrumento e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		if(!isNovoInstrumento(e))
			throw new BusinessException("O instrumento " + e + "já está cadastrado!");
		String sql = insertBuilder();
		try{
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, e.getFabricante().getCod());
			stmt.setObject(2, e.getNome());
			stmt.setObject(3, e.getTipo());
			stmt.setObject(4, e.getPreco());
			stmt.setObject(5, Validator.nlv(e.getEspecificacao()));
			System.out.println(stmt.toString());			
			stmt.execute();
		
			e.setCod(getIdInstrumento());
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
		
	}
	
	/**
	 * Busca pelo Id do último instrumento
	 * inserido na base de dados!
	 * 
	 * @return Id do Instrumento
	 * @throws Exception
	 */
	private int getIdInstrumento() throws Exception {
		String sql = "SELECT cod_instrumento FROM INSTRUMENTO ORDER BY cod_instrumento DESC LIMIT 1";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.execute();
		
		ResultSet result = stmt.getResultSet();
		result.next();
		return result.getInt(1);
		
	}
	
	/**
	 * Valida se um instrumento já existe no banco
	 * @param e Instrumento
	 * 
	 * @author guilherme
	 */	
	private boolean isNovoInstrumento(Instrumento e) throws Exception{
		PreparedStatement stmt = null;
		ResultSet result       = null;
		
		String sql = "select * from INSTRUMENTO where nome_instrumento like ?".toUpperCase();	
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
	 * Deleta um instrumento da base de
	 * dados
	 */
	@Override
	public void delete(Instrumento e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "delete from instrumento where cod_instrumento = ?".toUpperCase();
		
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

	@Override
	public void update(Instrumento e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;		
		
		String sql = updateBuilder();

		try{
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getNome());
			stmt.setObject(2,e.getTipo());
			stmt.setObject(3,e.getPreco());
			stmt.setObject(4,e.getEspecificacao());
			stmt.setObject(5, e.getCodigo());
			System.out.println(stmt.toString());
			
			stmt.execute();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
		
	}

	@Override
	public List<Instrumento> listAll() throws Exception {
		List<Instrumento> lista 	 = new ArrayList<Instrumento>();
		FabricanteDao instrumentoDao = new FabricanteDaoImpl();
		ResultSet result 			 = null;	
		Instrumento instrumento      = null;
		Fabricante fabricante    	 = null;
		
		String sql = "select * from instrumento".toUpperCase();
		
		con = ConnectionFactory.getConnection();		
		PreparedStatement stmt = con.prepareStatement(sql);
			
		stmt.execute();	
		result = stmt.getResultSet();
		
		while(result.next()){
			instrumento = new Instrumento();			
			instrumento.setCod(result.getInt("cod_instrumento"));
			fabricante = instrumentoDao.getFabricanteById(result.getInt("cod_fabricante"));
			instrumento.setFabricante(fabricante);
		    instrumento.setNome(result.getString("nome_instrumento"));
		    instrumento.setTipo(result.getString("tipo_instrumento"));
		    instrumento.setPreco(result.getFloat("preco_instrumento"));
		    instrumento.setEspecificacao(result.getString("especificacao"));
		    
		    lista.add(instrumento);			                           
		}
		return lista;
	}

	
	@Override
	public String insertBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into instrumento");
		sql.append("(");
		sql.append("cod_fabricante,");
		sql.append("nome_instrumento,");
		sql.append("tipo_instrumento,");
		sql.append("preco_instrumento,");
		sql.append("especificacao");
		sql.append(")");
		sql.append(" values ");
		sql.append("(?, ?, ?, ?, ?)");
		return sql.toString().toUpperCase();
	}

	@Override
	public String updateBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("update instrumento ");
		sql.append("set nome_instrumento = ?, ");
		sql.append("tipo_instrumento = ?, ");
		sql.append("preco_instrumento = ?, ");
		sql.append("especificacao = ?");
		sql.append("where cod_instrumento = ?");
		return sql.toString().toUpperCase();
	}	
	
}
