package view.GUIModels.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import model.Luthier;
import exceptions.BusinessException;

public class LuthierTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 6030444468786624426L;

	private List<Luthier> listaLuthier;
	
	String[] colunas = {"Nome","CPF","Endereco",
						"Bairro","Cidade","UF",     
						"Telefone","Email", "Especialidade"};
	
	public LuthierTableModel(List<Luthier> listaLuthier) {
		this.listaLuthier = new ArrayList<Luthier>(listaLuthier);
	}
	
	public LuthierTableModel() {
		listaLuthier = new ArrayList<Luthier>();
	}

	@Override
	public int getRowCount() {		 
		return listaLuthier.size();
	}

	@Override
	public int getColumnCount() {		
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Luthier f = listaLuthier.get(rowIndex);
		
		switch(columnIndex){
		case 0:
			return f.getNome();
		case 1:			
			return f.getCpf();
		case 2:
			return f.getEndereco();
		case 3:
			return f.getBairro();
		case 4:
			return f.getCidade();
		case 5:
			return f.getUf();
		case 6:
			return f.getTelefone();
		case 7:
			return f.getEmail();
		case 8:
			return f.getEspecialidade();
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
			return String.class;
		case 4:    
			return String.class;
		case 5:    
			return String.class;
		case 6:    
			return String.class;
		case 7:    
			return String.class;
		case 8:
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
		Luthier f = listaLuthier.get(rowIndex);
		
		try {
			switch(columnIndex){
			case 0:
				f.setNome(String.valueOf(aValue));
				break;
			case 1:			
				f.setCpf(String.valueOf(aValue));
				break;
			case 2:
				f.setEndereco(String.valueOf(aValue));
				break;
			case 3:
				f.setBairro(String.valueOf(aValue));
				break;
			case 4:
				f.setCidade(String.valueOf(aValue));
				break;
			case 5:
				f.setUf(String.valueOf(aValue));
				break;
			case 6:
				f.setTelefone(String.valueOf(aValue));
				break;
			case 7:
				f.setEmail(String.valueOf(aValue));
				break;
			case 8:
				f.setEspecialidade(String.valueOf(aValue));
			}		
		}catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());			
		}
	}
	
	public Luthier get(String cpf) throws Exception{	
		Luthier lut = new Luthier();
		try {
			lut.setCpf(cpf);
		} catch (BusinessException e) {
			throw new Exception("Nenhum luthier foi selecionado");
		}
		int idx = listaLuthier.indexOf(lut);
		return listaLuthier.get(idx);
	}

}