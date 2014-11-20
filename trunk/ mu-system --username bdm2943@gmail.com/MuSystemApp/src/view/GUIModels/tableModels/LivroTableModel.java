package view.GUIModels.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import model.Livro;
import exceptions.BusinessException;

public class LivroTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 8817509332788681179L;

	private List<Livro> listaLivro;
	
	String[] colunas = {"Titulo","Autor","Editora",
						"Preco","Ano"};
	
	public LivroTableModel(List<Livro> listaLivro) {
		this.listaLivro = new ArrayList<Livro>(listaLivro);
	}
	
	public LivroTableModel() {
		listaLivro = new ArrayList<Livro>();
	}

	@Override
	public int getRowCount() {		 
		return listaLivro.size();
	}

	@Override
	public int getColumnCount() {		
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Livro livro = listaLivro.get(rowIndex);
		
		switch(columnIndex){
		case 0:
			return livro.getTitulo();
		case 1:			
			return livro.getAutor();
		case 2:
			return livro.getEditora();
		case 3:
			return livro.getPreco();
		case 4:
			return livro.getAno();
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
			return Float.class;
		case 4:    
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
		Livro livro = listaLivro.get(rowIndex);
		
		try {
			switch(columnIndex){
			case 0:
				livro.setTitulo(String.valueOf(aValue));
				break;
			case 1:			
				livro.setAutor(String.valueOf(aValue));
				break;
			case 2:
				livro.setEditora(String.valueOf(aValue));
				break;
			case 3:
				livro.setPreco((Float)(aValue));
				break;
			case 4:
				livro.setAno((Integer)(aValue));
				break;
			}		
		}catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());			
		}
	}
	
	public Livro get(String titulo) throws Exception{	
		Livro livro = new Livro();
		try {
			livro.setTitulo(titulo);
		} catch (BusinessException e) {
			throw new Exception("Nenhum livro foi selecionado");
		}
		int idx = listaLivro.indexOf(livro);
		return listaLivro.get(idx);
	}

}
