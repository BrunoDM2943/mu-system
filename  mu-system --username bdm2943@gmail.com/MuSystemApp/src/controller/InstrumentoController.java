package controller;

import java.util.List;

import model.Instrumento;
import dao.DaoFacade;

public class InstrumentoController {
	
	private List<Instrumento> listaInstrumentos;
	
	public void gravarInstrumento(Instrumento instrumento) throws Exception{
		DaoFacade.save(instrumento);
	}

	@SuppressWarnings("unchecked")
	public List<Instrumento> listarTodos() throws Exception {
		if(listaInstrumentos != null)
			return listaInstrumentos;
		listaInstrumentos = (List<Instrumento>) DaoFacade.lerTodos(Instrumento.class);
		if(listaInstrumentos.isEmpty())
			throw new Exception("Não há instrumentos cadastrados na base!");
		
		return listaInstrumentos;
	}

	public void deletarInstrumento(Instrumento ins) throws Exception {		
		DaoFacade.delete(ins);
		listaInstrumentos.remove(ins);
	}

	public void atualizarInstrumento(Instrumento ins) throws Exception {
		DaoFacade.update(ins);		
	}

}