package tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import model.Acessorio;
import exceptions.BusinessException;

public class AcessorioTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -1156878417620204883L;

	private List<Acessorio> listaAcessorios;
	
	String[] colunas = {"Nome","Preço"};
	
	public AcessorioTableModel(List<Acessorio> listaAcessorios) {
		this.listaAcessorios = new ArrayList<Acessorio>(listaAcessorios);
	}
	
	public AcessorioTableModel() {
		listaAcessorios = new ArrayList<Acessorio>();
	}

	@Override
	public int getRowCount() {		 
		return listaAcessorios.size();
	}

	@Override
	public int getColumnCount() {		
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Acessorio acessorio = listaAcessorios.get(rowIndex);
		
		switch(columnIndex){
		case 0:
			return acessorio.getNome();
		case 1:			
			return acessorio.getPreco();
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
		Acessorio acessorio = listaAcessorios.get(rowIndex);
		
		try {
			switch(columnIndex){
			case 0:
				acessorio.setNome(String.valueOf(aValue));
				break;
			case 1:			
				acessorio.setPreco((Float)aValue);
				break;
			}		
		}catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());			
		}
	}
	
	public Acessorio get(String nome) throws Exception{	
		Acessorio fab = new Acessorio();
		try {
			fab.setNome(nome);
		} catch (BusinessException e) {
			throw new Exception("Nenhum acessorio foi selecionado");
		}
		int idx = listaAcessorios.indexOf(fab);
		return listaAcessorios.get(idx);
	}

}