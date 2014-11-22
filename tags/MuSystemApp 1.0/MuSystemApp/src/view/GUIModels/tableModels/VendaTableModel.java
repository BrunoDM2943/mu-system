package view.GUIModels.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Venda;
import exceptions.BusinessException;

public class VendaTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -5460736422410533100L;

	private List<Venda> listaVenda;
	
	String[] colunas = {"Codigo","Cliente","Valor Total"};
	
	public VendaTableModel(List<Venda> listaVendas) {
		this.listaVenda = new ArrayList<Venda>(listaVendas);
	}
	
	public VendaTableModel() {
		listaVenda = new ArrayList<Venda>();
	}

	@Override
	public int getRowCount() {		 
		return listaVenda.size();
	}

	@Override
	public int getColumnCount() {		
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Venda venda = listaVenda.get(rowIndex);
		
		switch(columnIndex){
		case 0:
			return venda.getCodigo();
		case 1:
			return venda.getCliente().getNome();
		case 2:			
			return venda.somar();	
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
	
	public Venda get(int codigo) throws Exception{	
		Venda Venda = new Venda();
		try {
			Venda.setCodigo(codigo);
		} catch (BusinessException e) {
			throw new Exception("Nenhum Venda foi selecionado");
		}
		int idx = listaVenda.indexOf(Venda);
		return listaVenda.get(idx);
	}

}
