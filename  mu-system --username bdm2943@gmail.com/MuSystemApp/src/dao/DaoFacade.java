package dao;

import java.util.List;

import dao.annotations.DataAccessClass;
import dao.excepetions.DataAccessClassException;
import dao.excepetions.DataAccessException;

public class DaoFacade{
	
	
	/**
	 * Save one entity
	 * @param <T>
	 * 
	 * @param e
	 * @throws Exception 
	 */
	public static <T> void save(Object e) throws Exception{
		DataAccessClass daoClass = getAccessClass(e);
		Class<?> clazzImpl = getClass(daoClass.daoImpl());
		DataAccessObject<Object> dao = getImplementation(clazzImpl);		
		dao.save(e);			
	}
	
	/**
	 * Delete one entity
	 * @param <T>
	 * 
	 * @param e
	 * @throws Exception 
	 */
	public static <T> void delete(Object e) throws Exception{
		DataAccessClass daoClass = getAccessClass(e);
		Class<?> clazzImpl = getClass(daoClass.daoImpl());
		DataAccessObject<Object> dao = getImplementation(clazzImpl);		
		dao.delete(e);			
	}
	
	/**
	 * Recebe uma classe como parâmetros e
	 * retorna uma lista do banco com todos os registros
	 * da classe desejada
	 * 
	 * @throws Exception se a classe não tiver uma DAO relacionada
	 */
	@SuppressWarnings("rawtypes")
	public static List<?> lerTodos(Class clazz) throws Exception {
		DataAccessClass daoClass = getAccessClas(clazz);
		Class<?> clazzImpl = getClass(daoClass.daoImpl());
		DataAccessObject<Object> dao = getImplementation(clazzImpl);				
		return dao.listAll();		
	}
	

	/**
	 * Return a class for a given name
	 * @param clazz The class name
	 * @return Returns the .class
	 * @throws DataAccessException
	 * @author bruno
	 */
	private static Class<?> getClass(String clazz) throws DataAccessException{
		try {
			return Class.forName(clazz);
		} catch (ClassNotFoundException e) {
			throw new DataAccessException("The class " + clazz + " was not found!");
		}
	}
	
	/**
	 * Get the class that implements the 
	 * DataAccessObject
	 * 
	 * @param clazzImpl The class received through the annotation
	 * @return The class implementation
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	private static DataAccessObject<Object> getImplementation(Class<?> clazzImpl)
			throws  DataAccessException {
		DataAccessObject<Object> newInstance = null;
	
		try{
			newInstance = ((DataAccessObject<Object>) clazzImpl.newInstance());
		}catch(InstantiationException e){
			throw new DataAccessException(e.getMessage());
		}catch(IllegalAccessException e){
			throw new DataAccessException(e.getMessage());
		}
		return newInstance;
	}

	/**
	 * Get the DataAccessClass for a given
	 * object
	 * 
	 * @param e The object
	 * @return The DaoClass for the object
	 * @throws DataAccessClassException
	 * 
	 * @author bruno
	 */
	private static DataAccessClass getAccessClass(Object e) throws DataAccessClassException{
		DataAccessClass daoClass = e.getClass().getAnnotation(DataAccessClass.class);
		if(daoClass == null){
			throw new DataAccessClassException("No DataAccessClass found for " +  e.getClass());
		}else if("".equals(daoClass.daoImpl())){
			throw new DataAccessClassException("The DataAccessClass is empty");
		}else{
			return daoClass;
		}
	}
	
	/**
	 * Get the DataAccessClass for a given
	 * class
	 * 
	 * @param e The class
	 * @return The DaoClass for the object
	 * @throws DataAccessClassException
	 * 
	 * @author bruno
	 */
	@SuppressWarnings("rawtypes")
	private static DataAccessClass getAccessClas(Class clazz) throws DataAccessClassException {
		@SuppressWarnings("unchecked")
		DataAccessClass daoClass = (DataAccessClass) clazz.getAnnotation(DataAccessClass.class);
		if(daoClass == null){
			throw new DataAccessClassException("No DataAccessClass found for " +  clazz.toString());
		}else if("".equals(daoClass.daoImpl())){
			throw new DataAccessClassException("The DataAccessClass is empty");
		}else{
			return daoClass;
		}
	}

	
	
	
	
}
