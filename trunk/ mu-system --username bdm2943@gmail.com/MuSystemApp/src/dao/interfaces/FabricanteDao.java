package dao.interfaces;

import model.Fabricante;

public interface FabricanteDao extends DataAccessObject<Fabricante>{
	
	/**
	 * Busca um fabricante na base pelo seu
	 * id
	 * 
	 * @param id id do fabricante
	 * @return Fabricante
	 * @throws Exception
	 * 
	 * @author bruno
	 */
	Fabricante getFabricanteById(int id) throws Exception;

}