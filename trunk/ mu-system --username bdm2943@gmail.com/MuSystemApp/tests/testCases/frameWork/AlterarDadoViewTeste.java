package testCases.frameWork;

import org.junit.Test;

import services.auxiliarityViews.AlterarDadoView;

public class AlterarDadoViewTeste {
	
	@Test
	public void TC001AlterarDadoValido(){
		String nome = "Bruno";
		nome = (String) AlterarDadoView.alterarDado(String.class,"nome");
		System.out.println(nome);
	}
	
	@Test(expected = ClassCastException.class)
	public void TC001AlterarDadoInvalido(){
		int valor = 19;	
		valor = (int) AlterarDadoView.alterarDado(String.class, "valor");
	}
	
	@Test
	public void TC002AlterarDadoNumero(){
		int valor = 19;	
		valor = (int) AlterarDadoView.alterarDado(Integer.class, "valor");
	}

}
