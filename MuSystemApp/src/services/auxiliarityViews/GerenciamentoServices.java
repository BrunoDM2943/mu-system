package services.auxiliarityViews;

import java.util.List;

/**
 * GerenciamentoServices
 *  
 * 
 * Essa interface é repsonsável por oferecer
 * os métodos básicos em telas de gerenciamento
 * @author bruno
 * @param <T>
 *
 */
public interface GerenciamentoServices<T> {

	/**
	 * Troca 
	 */
	void alterar();
	
	List<T> procurar();
	
	void deletar();

}
