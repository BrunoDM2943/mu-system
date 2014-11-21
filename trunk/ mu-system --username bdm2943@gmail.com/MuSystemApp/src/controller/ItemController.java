package controller;

import java.util.List;

import model.Item;
import dao.DaoFacade;

public class ItemController {
	
	private List<Item> listaItem;
	
	public void gravarItem(Item item) throws Exception{
		DaoFacade.save(item);
	}

	@SuppressWarnings("unchecked")
	public List<Item> listarTodos() throws Exception {
		if(listaItem != null)
			return listaItem;
		listaItem = (List<Item>) DaoFacade.lerTodos(Item.class);
		if(listaItem.isEmpty())
			throw new Exception("Não há items cadastrados na base!");
		
		return listaItem;
	}

	public void deletarItem(Item item) throws Exception {		
		DaoFacade.delete(item);
		listaItem.remove(item);
	}

	public void atualizarItem(Item item) throws Exception {
		DaoFacade.update(item);		
	}

}
