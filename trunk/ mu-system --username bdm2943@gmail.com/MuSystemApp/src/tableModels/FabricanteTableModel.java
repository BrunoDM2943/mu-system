package tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import model.Fabricante;
import exceptions.BusinessException;

public class FabricanteTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -1156878417620204883L;

	private List<Fabricante> listaFabricantes;
	
	String[] colunas = {"Nome","Telefone","Contato"};
	
	public FabricanteTableModel(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = new ArrayList<Fabricante>(listaFabricantes);
	}
	
	public FabricanteTableModel() {
		listaFabricantes = new ArrayList<Fabricante>();
	}

	@Override
	public int getRowCount() {		 
		return listaFabricantes.size();
	}

	@Override
	public int getColumnCount() {		
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Fabricante f = listaFabricantes.get(rowIndex);
		
		switch(columnIndex){
		case 0:
			return f.getNome();
		case 1:			
			return f.getTelefone();
		case 2:
			return f.getContato();
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
			return String.class;
		case 2:
			return String.class;
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
		Fabricante f = listaFabricantes.get(rowIndex);
		
		try {
			switch(columnIndex){
			case 0:
				f.setNome(String.valueOf(aValue));
				break;
			case 1:			
				f.setTelefone(String.valueOf(aValue));
				break;
			case 2:
				f.setContato(String.valueOf(aValue));
			}		
		}catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());			
		}
	}
	
	public Fabricante get(String nome) throws Exception{	
		Fabricante fab = new Fabricante();
		try {
			fab.setNome(nome);
		} catch (BusinessException e) {
			throw new Exception("Nenhum fabricante foi selecionado");
		}
		int idx = listaFabricantes.indexOf(fab);
		return listaFabricantes.get(idx);
	}

}