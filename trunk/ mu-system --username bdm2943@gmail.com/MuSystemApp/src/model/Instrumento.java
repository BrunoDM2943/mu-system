package model;

import services.validator.Validator;
import dao.annotations.DataAccessClass;
import exceptions.BusinessException;

@DataAccessClass(daoImpl = "dao.implementation.InstrumentoDaoImpl")
public class Instrumento {
	
	private int cod;
	
	private String nome;
	
	private String tipo;
	
	private float preco;
	
	private String especificacao;
	
	private Fabricante fabricante;

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) throws BusinessException {
		if(Validator.isZero(cod))
			throw new BusinessException("O código de um instrumento não pode ser vazio!");
		this.cod = cod;
		}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws BusinessException {
		if(Validator.isEmpty(nome))
			throw new BusinessException("O nome de instrumento não pode ser vazio!");
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) throws BusinessException {
		if(Validator.isEmpty(tipo))
			throw new BusinessException("O tipo de um instrumento não pode ser vazio!");
		this.tipo = tipo;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) throws BusinessException {
		if(Validator.isZero(preco))
			throw new BusinessException("O preço de um instrumento não pode ser vazio!");
		this.preco = preco;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}
	
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) throws BusinessException {
		if(fabricante == null)
			throw new BusinessException("O fabricante de um produto não pode ficar vazio!");
		this.fabricante = fabricante;
	}

	@Override	
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean equals(Object obj) {		
		Instrumento i = (Instrumento) obj;		
		return i.getNome().equalsIgnoreCase(this.nome);	
	}
}
