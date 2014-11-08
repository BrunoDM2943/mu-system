package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import services.login.LoginException;
import services.login.User;
import dao.connection.ConnectionFactory;
import dao.excepetions.DataAccessException;

public class UserDaoImpl implements UserDao{

	private Connection con;
	
	@Override
	public void save(User e) throws DataAccessException {

	}

	@Override
	public void delete(User e) throws DataAccessException {
		// TODO Auto-generated method stub
		
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
			user.setPassaword(result.getString(2));
			return user;
		}else{
			throw new LoginException("No user found for the given combination");
		}
		
	}


}
