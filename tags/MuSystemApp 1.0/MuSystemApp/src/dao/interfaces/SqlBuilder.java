package dao.interfaces;

public interface SqlBuilder {
	
	/**
	 * Monta uma sql de Insert
	 * @return
	 */
	String insertBuilder();
	
	/**
	 * Monta uma sql de update
	 * @param <T>
	 * @return
	 */
	String updateBuilder();

}
