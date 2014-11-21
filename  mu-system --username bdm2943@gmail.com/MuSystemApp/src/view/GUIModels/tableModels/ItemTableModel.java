package view.GUIModels.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Item;

public class ItemTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	private List<Item> listaItem;
	
	String[] colunas = {"Produto","Quantidade", "Valor Unitario", "Total"};
	
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
			return item.getComercilizavel().toString();
		case 1:			
			return item.getQtd();
		case 2:			
			return item.getComercilizavel().getPreco();
		case 3:			
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
			return String.class;
		case 1:			
			return Integer.class;
		case 2:			
			return Float.class;
		case 3:			
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


}
