package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Venda;
import dao.connection.ConnectionFactory;
import dao.excepetions.DataAccessException;
import dao.interfaces.ClienteDao;
import dao.interfaces.ItemDao;
import dao.interfaces.SqlBuilder;
import dao.interfaces.VendaDao;

public class VendaDaoImpl implements VendaDao, SqlBuilder {

	private Connection con;
	
	/**
	 * Grava uma Venda na base
	 * de dados
	 */
	@Override
	public void save(Venda e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = insertBuilder();
		try{		
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getCliente().getCod());
			stmt.setObject(2,e.somar());
			stmt.setObject(3, e.getDataVenda());
			System.out.println(stmt.toString());			
			stmt.execute();
			
			e.setCodigo(getIdVenda());
			
			new ItemDaoImpl().save(e.getItens());
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
	}

	/**
	 * Busca pelo Id da último Venda
	 * inserido na base de dados!
	 * 
	 * @return Id da Venda
	 * @throws Exception
	 */
	private int getIdVenda() throws Exception {
		String sql = "SELECT cod_venda FROM VENDA ORDER BY cod_venda DESC LIMIT 1";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.execute();
		
		ResultSet result = stmt.getResultSet();
		result.next();
		return result.getInt(1);
		
	}


	/**
	 * Lista todos as Vendas cadastradas na base de dados
	 */
	@Override
	public List<Venda> listAll() throws Exception {
		ClienteDao daoCli = new ClienteDaoImpl();
		ItemDao daoItem = new ItemDaoImpl();
		List<Venda> lista = new ArrayList<Venda>();
		Venda venda  = null;
		String sql = "select * from Venda".toUpperCase();
		
		con = ConnectionFactory.getConnection();		
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet    result = null;		
		stmt.execute();	
		result = stmt.getResultSet();
		
		while(result.next()){
			venda = new Venda();
			int cod_venda = result.getInt("cod_venda");
			venda.setCodigo(cod_venda);
			int cod_cli = result.getInt("cod_cliente");
			venda.setCliente(daoCli.getById(cod_cli));
			venda.setItens(daoItem.listAll(cod_venda));
			venda.somar();
			venda.setDataVenda(result.getDate(4));
		    lista.add(venda);			                           
		}
		return lista;
	}

	@Override
	public String insertBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Venda");
		sql.append("(");
		sql.append("cod_cliente,");
		sql.append("total_venda,");
		sql.append("data_venda");
		sql.append(")");
		sql.append(" values ");
		sql.append("(?, ?, ?)");
		return sql.toString().toUpperCase();
	}

	@Override
	public String updateBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("update venda ");
		sql.append("set cod_cliente = ?, ");
		sql.append("set total_venda = ?, ");
		sql.append("set data_venda = ?");		
		sql.append("where cod_venda = ?");
		return sql.toString().toUpperCase();
	}

	@Override
	public void delete(Venda e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		new ItemDaoImpl().deleteAll(e.getItens());		
		String sql = "delete from venda where cod_venda = ?".toUpperCase();
		
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
	public void update(Venda e) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Venda getById(int cod) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}