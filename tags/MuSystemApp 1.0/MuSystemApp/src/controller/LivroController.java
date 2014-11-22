package controller;

import java.util.List;

import model.Livro;
import dao.DaoFacade;

public class LivroController {
	
	private List<Livro> listaLivro;
	
	public void gravarLivro(Livro livro) throws Exception{
		DaoFacade.save(livro);
	}

	@SuppressWarnings("unchecked")
	public List<Livro> listarTodos() throws Exception {
		if(listaLivro != null)
			return listaLivro;
		listaLivro = (List<Livro>) DaoFacade.lerTodos(Livro.class);
		if(listaLivro.isEmpty())
			throw new Exception("Não há livros cadastrados na base!");
		
		return listaLivro;
	}

	public void deletarLivro(Livro livro) throws Exception {		
		DaoFacade.delete(livro);
		listaLivro.remove(livro);
	}

	public void atualizarLivro(Livro livro) throws Exception {
		DaoFacade.update(livro);		
	}

}