package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import services.login.LoginException;
import services.login.User;
import services.validator.Validator;
import dao.connection.ConnectionFactory;
import dao.excepetions.DataAccessException;
import dao.interfaces.SqlBuilder;
import dao.interfaces.UserDao;
import exceptions.BusinessException;

public class UserDaoImpl implements UserDao, SqlBuilder{

	private Connection con;
	
	@Override
	public void save(User e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		if(!isNovoUsuario(e))
			throw new BusinessException("O usuario " + e + "já está cadastrado!");
		String sql = insertBuilder();
		try{		
			stmt = con.prepareStatement(sql);
			stmt.setObject(1,e.getLogin());
			stmt.setObject(2,e.getPassaword());
			System.out.println(stmt.toString());			
			stmt.execute();
			
			e.setId_user(getIdUser());
			
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
	}

	@Override
	public void delete(User e) throws Exception {
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "delete from sis_user where id_user = ?".toUpperCase();
		
		try{
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, e.getId_User());			
			System.out.println(stmt.toString());			
			stmt.execute();
		}catch(SQLException e1){
			throw new DataAccessException(e1.getMessage());
		}finally{
			con.close();
		}
		
	}

	@Override
	public void update(User e) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User selectUser(String nome, String pw) throws Exception{
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet result       = null;
		String sql = "select * from sis_user where nome = ? and senha = ?".toUpperCase();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, nome);
		stmt.setString(2, pw);		
		stmt.execute();
		result = stmt.getResultSet();
		
		if(result.next()){
			User user = new User();
			user.setLogin(result.getString(1));
			user.setPassword(result.getString(2));
			return user;
		}else{
			throw new LoginException("No user found for the given combination");
		}
		
	}

	@Override
	public List<User> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Valida se um usuario já existe no banco
	 * @param e Usuário
	 * 
	 * @author bruno
	 */
	private boolean isNovoUsuario(User e) throws Exception{
		con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet result       = null;
		String sql = "select * from sis_user where nome = ? and senha = ?".toUpperCase();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, Validator.nlv(e.getLogin()));
		stmt.setString(2, Validator.nlv(e.getPassaword()));		
		stmt.execute();
		result = stmt.getResultSet();
		
		return !result.next();
	}
	
	/**
	 * Busca pelo Id do último usuário
	 * inserido na base de dados!
	 * 
	 * @return Id do usuário
	 * @throws Exception
	 */
	private int getIdUser() throws Exception {
		String sql = "SELECT id_user FROM SIS_USER ORDER BY id_user DESC LIMIT 1";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.execute();
		
		ResultSet result = stmt.getResultSet();
		result.next();
		return result.getInt(1);
		
	}
	
	

	@Override
	public String insertBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into sis_user");
		sql.append("(");
		sql.append("nome,");
		sql.append("senha");
		sql.append(")");
		sql.append(" values ");
		sql.append("(?, ?)");
		return sql.toString().toUpperCase();
	}

	@Override
	public String updateBuilder() {
		StringBuilder sql = new StringBuilder();
		sql.append("update sis_user ");
		sql.append("set nome = ?, ");
		sql.append("senha = ?");		
		sql.append("where id_user = ?");
		return sql.toString().toUpperCase();
	}

	@Override
	public User getById(int cod) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
