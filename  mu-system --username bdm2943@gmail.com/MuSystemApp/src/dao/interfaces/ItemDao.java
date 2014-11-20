package dao.interfaces;

import java.util.List;

import model.Item;

public interface ItemDao{
	
	void save(List<Item> itens) throws Exception;
	
}
