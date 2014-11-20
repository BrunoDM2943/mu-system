package view.GUIModels.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import model.Instrumento;
import enums.TiposMidia;
import exceptions.BusinessException;

public class InstrumentoTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -5460736422410533100L;

	private List<Instrumento> listaInstrumento;
	
	String[] colunas = {"Fabricante","Nome","Tipo",
						"Preco","Especificacao"};
	
	public InstrumentoTableModel(List<Instrumento> listainstrumento) {
		this.listaInstrumento = new ArrayList<Instrumento>(listainstrumento);
	}
	
	public InstrumentoTableModel() {
		listaInstrumento = new ArrayList<Instrumento>();
	}

	@Override
	public int getRowCount() {		 
		return listaInstrumento.size();
	}

	@Override
	public int getColumnCount() {		
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Instrumento ins = listaInstrumento.get(rowIndex);
		
		switch(columnIndex){
		case 0:
			return ins.getNome();
		case 1:			
			return ins.getTipo();
		case 2:
			return ins.getPreco();
		case 3:
			return ins.getEspecificacao();
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
		case 3:    
			return TiposMidia.class;
		case 4:    
			return Float.class;
		case 5:    
			return Integer.class;
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
		Instrumento ins = listaInstrumento.get(rowIndex);
		
		try {
			switch(columnIndex){
			case 0:
				ins.setNome(String.valueOf(aValue));
				break;
			case 1:			
				ins.setTipo(String.valueOf(aValue));
				break;
			case 2:
				ins.setPreco((Float)(aValue));
				break;
			case 3:
				ins.setEspecificacao(String.valueOf(aValue));
				break;			
			}		
		}catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());			
		}
	}
	
	public Instrumento get(String nome) throws Exception{	
		Instrumento ins = new Instrumento();
		try {
			ins.setNome(nome);
		} catch (BusinessException e) {
			throw new Exception("Nenhum instrumento foi selecionado");
		}
		int idx = listaInstrumento.indexOf(ins);
		return listaInstrumento.get(idx);
	}

}
