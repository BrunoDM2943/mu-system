package model;

import services.validator.Validator;
import dao.annotations.DataAccessClass;
import exceptions.BusinessException;

@DataAccessClass(daoImpl = "dao.implementation.AcessorioDaoImpl")
public class Acessorio {
	
	private int codigo;
	
	private String nome;
	
	private float preco;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) throws BusinessException {
		if(Validator.isZero(codigo))
			throw new BusinessException("O código de um acessorio não pode ser vazio!");
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws BusinessException{
		if(Validator.isEmpty(nome))
			throw new BusinessException("O nome de um acessório não pode ser vazio!");
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) throws BusinessException {
		if(Validator.isZero(preco))
			throw new BusinessException("O preco de um acessório não pode ser nulo!");
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean equals(Object obj) {	
		Acessorio ace = (Acessorio) obj;
		return this.nome.equalsIgnoreCase(ace.getNome());
	}


}
