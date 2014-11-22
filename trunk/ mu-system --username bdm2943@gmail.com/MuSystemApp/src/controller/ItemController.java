package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.Comercializavel;
import model.Item;
import view.GUIModels.tableModels.ItemTableModel;
import dao.DaoFacade;

public class ItemController {
	
	private static List<Item> listaItem = new ArrayList<Item>();
		
	public void gravarItem(Item item) throws Exception{
		DaoFacade.save(item);
	}

	public List<Item> listarTodos() throws Exception {	
		listaItem.forEach(e -> e.somar());
		return listaItem;
	}

	public void deletarItem(Item item) throws Exception {		
		DaoFacade.delete(item);
		listaItem.remove(item);
	}

	public void atualizarItem(Item item) throws Exception {
		DaoFacade.update(item);		
	}
	
	public Vector<Comercializavel> listarItens(Class<? extends Comercializavel> clazz) throws Exception{
	 	Vector<Comercializavel> vector = new Vector<Comercializavel>();
	 	DaoFacade.lerTodos(clazz).forEach(e -> vector.add((Comercializavel) e));
	 	return vector;
	 }
	
	public ItemTableModel removeItem(Item item){
		listaItem.remove(item);
		return new ItemTableModel(listaItem);
	}
	
	public ItemTableModel addItem(Item item){
		listaItem.add(item);
		return new ItemTableModel(listaItem);
	}

}
