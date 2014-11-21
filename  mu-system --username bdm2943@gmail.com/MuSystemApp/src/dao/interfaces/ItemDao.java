package dao.interfaces;

import java.util.List;

import model.Item;

public interface ItemDao extends DataAccessObject<Item>{
	
	void save(List<Item> itens) throws Exception;

	List<Item> listAll(int idVenda) throws Exception;
	
	void deleteAll(List<Item> itens) throws Exception;
	
}
