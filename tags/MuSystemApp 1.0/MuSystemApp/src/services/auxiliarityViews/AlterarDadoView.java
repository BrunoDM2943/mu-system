package services.auxiliarityViews;

import java.sql.Date;

import javax.swing.JOptionPane;

import enums.Estado;

public abstract class AlterarDadoView{

	private static Object novoCampo;
	
	public static Object alterarDado(Class<?> clazz, String campo) throws ClassCastException{
		String msg = "Digite um novo valor para o campo " + campo + ":";		
		novoCampo = JOptionPane.showInputDialog(null, msg);
		if(clazz.equals(String.class))
			return (String) novoCampo;
		if(clazz.equals(Integer.class))
			return Integer.parseInt(novoCampo.toString());
		if(clazz.equals(Double.class))
			return Double.parseDouble(novoCampo.toString());
		if(clazz.equals(Long.class))
			return Long.parseLong(novoCampo.toString());
		if(clazz.equals(Date.class))
			return Date.valueOf(novoCampo.toString());
		if(clazz.equals(Float.class))
			return Float.parseFloat(novoCampo.toString());		
		if(clazz.equals(Estado.class)){
			try{
				return Estado.valueOf(novoCampo.toString());
			}catch(Exception e){
				throw new ClassCastException("Não há um estado chamado " + novoCampo.toString());
			}
		}
		
			
		return "";		
	}	
	
	public static Object alterarDado(String campo) throws ClassCastException{
		String msg = "Digite um novo valor para o campo " + campo + ":";		
		novoCampo = JOptionPane.showInputDialog(null, msg);			
		return novoCampo;		
	}
}
