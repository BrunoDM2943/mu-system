package dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.excepetions.DataAccessException;

public class ConnectionFactory{
	
	private static Connection con;
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost/mu_system";
	private static final String usuario = "root";
	private static final String senha = "61417181@ab";
	
	private static Logger log = Logger.getGlobal();

	public static Connection getConnection() throws DataAccessException {
	        try{
	            Class.forName(driver);	            
	            log.log(Level.INFO, "Connecting to database");
	            con = DriverManager.getConnection(url, usuario, senha);
	            log.log(Level.INFO, "Connected");
	            return con;
	        }catch(ClassNotFoundException Driver){
	        	log.log(Level.INFO, "Driver not found");
	            throw new DataAccessException("Driver not found!");
	        }catch(SQLException Fonte){
	        	log.log(Level.INFO, "Error on connection");
	        	throw new DataAccessException("Error on connection");
	        }
	}
	

	public static void close() throws SQLException {
		 con.close();
		
	}

}
