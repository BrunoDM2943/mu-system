package view.GUIModels.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import model.Media;
import enums.TiposMidia;
import exceptions.BusinessException;

public class MediaTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -5460736422410533100L;

	private List<Media> listaMedia;
	
	String[] colunas = {"Titulo","Autor","Distribuidora",
						"Tipo","Preco","Ano"};
	
	public MediaTableModel(List<Media> listaMedias) {
		this.listaMedia = new ArrayList<Media>(listaMedias);
	}
	
	public MediaTableModel() {
		listaMedia = new ArrayList<Media>();
	}

	@Override
	public int getRowCount() {		 
		return listaMedia.size();
	}

	@Override
	public int getColumnCount() {		
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Media media = listaMedia.get(rowIndex);
		
		switch(columnIndex){
		case 0:
			return media.getTitulo();
		case 1:			
			return media.getAutor();
		case 2:
			return media.getDistribuidora();
		case 3:
			return media.getTipo();
		case 4:
			return media.getPreco();
		case 5:
			return media.getAno();
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
		Media media = listaMedia.get(rowIndex);
		
		try {
			switch(columnIndex){
			case 0:
				media.setTitulo(String.valueOf(aValue));
				break;
			case 1:			
				media.setAutor(String.valueOf(aValue));
				break;
			case 2:
				media.setDistribuidora(String.valueOf(aValue));
				break;
			case 3:
				media.setTipo(String.valueOf(aValue));
				break;
			case 4:
				media.setPreco((Float)(aValue));
				break;
			case 5:
				media.setAno((Integer)(aValue));
				break;
			}		
		}catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());			
		}
	}
	
	public Media get(String titulo) throws Exception{	
		Media media = new Media();
		try {
			media.setTitulo(titulo);
		} catch (BusinessException e) {
			throw new Exception("Nenhum media foi selecionado");
		}
		int idx = listaMedia.indexOf(media);
		return listaMedia.get(idx);
	}

}
