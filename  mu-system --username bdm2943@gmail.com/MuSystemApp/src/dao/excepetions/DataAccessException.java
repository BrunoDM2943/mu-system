package dao.excepetions;

@SuppressWarnings("serial")
public class DataAccessException extends Exception {
	

	public DataAccessException(String msg) {
		super(msg);
	}
}
