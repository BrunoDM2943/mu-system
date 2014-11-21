package testCases;

import java.util.Vector;

import javax.swing.JComboBox;

import dao.DaoFacade;
import model.Comercializavel;
import model.Livro;


public class TesteBasicos {

	
	public static void main(String[] args) throws Exception {
		Vector<Comercializavel> livros = listar(Livro.class);
		livros.forEach(e -> System.out.println(e));
		JComboBox<Comercializavel> cb = new JComboBox<Comercializavel>(livros);
		
	}
	
	
	private static Vector<Comercializavel> listar(Class<? extends Comercializavel> clazz) throws Exception{
		Vector<Comercializavel> vector = new Vector<Comercializavel>();
		
		DaoFacade.lerTodos(clazz).forEach(e -> vector.add((Comercializavel) e));
		return vector;
	}
}
