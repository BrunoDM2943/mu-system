package model;

import services.validator.Validator;
import dao.annotations.DataAccessClass;
import exceptions.BusinessException;

@DataAccessClass(daoImpl = "dao.implementation.FabricanteDaoImpl")
public class Fabricante {

	private int cod;
	
	private String nome;
	
	private String telefone;
	
	private String contato;

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) throws BusinessException{
		if(Validator.isZero(cod))
			throw new BusinessException("O código de um fabricante não pode ser vazio!");
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws BusinessException{
		if(Validator.isEmpty(nome))
			throw new BusinessException("O nome de um fabricante não pode ser vazio!");
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		if(!Validator.isEmpty(telefone))
			this.telefone = telefone.replaceAll(".","").replace("-", "");
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) throws BusinessException{
		if(Validator.isEmpty(contato))
			new BusinessException("O nome de um contato do fabricante não pode ser vazio!");
		this.contato = contato;
	}
	
	@Override	
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean equals(Object obj) {		
		Fabricante f = (Fabricante) obj;		
		return f.getNome().equalsIgnoreCase(this.nome);	
	}
}