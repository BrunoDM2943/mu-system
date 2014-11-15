package dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.excepetions.DataAccessException;

public class ConnectionFactory{
	
	private static Connection con;
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost/mu_system";
	private static final String usuario = "root";
	private static final String senha = "61417181@ab";
	
	public static Connection getConnection() throws DataAccessException {
	        try{
	            Class.forName(driver);	            
	            con = DriverManager.getConnection(url, usuario, senha);
	            return con;
	        }catch(ClassNotFoundException Driver){	        	
	            throw new DataAccessException("Driver not found!");
	        }catch(SQLException Fonte){
	        	throw new DataAccessException("Error on connection");
	        }
	}
	

	public static void close() throws SQLException {
		 con.close();
		
	}

}
