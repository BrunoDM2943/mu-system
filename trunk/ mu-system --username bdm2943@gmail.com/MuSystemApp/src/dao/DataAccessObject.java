package dao;

import dao.excepetions.DataAccessException;

public interface DataAccessObject<T>{
		
	/**
	 * Saves a entity on the database
	 * 
	 * @param e The entity
	 * @throws DataAccessException
	 * @author bruno
	 * @throws Exception 
	 * @since 1.0
	 */
	void save(T e) throws Exception;


	/**
	 * Delete a entity on the database
	 * 
	 * @param e The entity
	 * @throws DataAccessException
	 * @author bruno
	 * @throws Exception 
	 * @since 1.0
	 */
	void delete(T e) throws Exception;
	
	/**
	 * Update a entity on the database
	 * 
	 * @param e The entity
	 * @throws DataAccessException
	 * @author bruno
	 * @throws Exception 
	 * @since 1.0
	 */
	void update(T e) throws Exception;			

}
