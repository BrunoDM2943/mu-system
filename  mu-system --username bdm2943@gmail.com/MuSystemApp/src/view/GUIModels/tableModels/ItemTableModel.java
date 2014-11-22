package view.GUIModels.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Acessorio;
import model.Comercializavel;
import model.Instrumento;
import model.Item;
import model.Livro;
import model.Media;
import dao.implementation.AcessorioDaoImpl;
import dao.implementation.InstrumentoDaoImpl;
import dao.implementation.LivroDaoImpl;
import dao.implementation.MediaDaoImpl;

public class ItemTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	private List<Item> listaItem;
	
	String[] colunas = {"Codigo","Produto","Tipo","Quantidade", "Valor Unitario", "Total"};
	
	public ItemTableModel(List<Item> listaItems) {
		this.listaItem = new ArrayList<Item>(listaItems);
	}
	
	public ItemTableModel() {
		listaItem = new ArrayList<Item>();
	}

	@Override
	public int getRowCount() {		 
		return listaItem.size();
	}

	@Override
	public int getColumnCount() {		
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Item item = listaItem.get(rowIndex);
		
		switch(columnIndex){
		case 0:
			return item.getComercilizavel().getCodigo();
		case 1:
			return item.getComercilizavel().toString();
		case 2:
			return item.getComercilizavel().getClass();
		case 3:			
			return item.getQtd();
		case 4:			
			return item.getComercilizavel().getPreco();
		case 5:			
			return item.getTotalItem();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:			
			return Class.class;
		case 3:			
			return Integer.class;
		case 4:			
			return Float.class;
		case 5:			
			return Float.class;
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}

	@SuppressWarnings("unchecked")
	public Item get(int codigo, int rowIndex) throws Exception {
		Class<? extends Comercializavel> clazz = (Class<? extends Comercializavel>) getValueAt(rowIndex, 2);
		
		if(clazz == Livro.class){
			Livro livro = new LivroDaoImpl().getById(codigo);
			Item item = new Item(livro);
			return item;
		}else if(clazz == Media.class){
			Media media = new MediaDaoImpl().getById(codigo);
			Item item = new Item(media);
			return item;
		}else if(clazz == Acessorio.class){
			Acessorio acessorio = new AcessorioDaoImpl().getById(codigo);
			Item item = new Item(acessorio);
			return item;
		}	if(clazz == Instrumento.class){
			Instrumento instrumento = new InstrumentoDaoImpl().getById(codigo);
			Item item = new Item(instrumento);
			return item;
		}
		
		return null;
	}


}
