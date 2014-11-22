package model;

import services.validator.Validator;
import dao.annotations.DataAccessClass;
import exceptions.BusinessException;

@DataAccessClass(daoImpl="dao.implementation.MediaDaoImpl")
public class Media  implements Comercializavel{
	
	private int codigo;
	
	private String titulo;
	
	private String autor;
	
	private String distribuidora;
	
	private String tipo;
	
	private float preco;
	
	private int ano;
	
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) throws BusinessException {
		if(Validator.isZero(codigo))
			throw new BusinessException("O código de uma mídia não pode ser vazio!");
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) throws BusinessException{
		if(Validator.isEmpty(titulo))
			throw new BusinessException("O titulo de uma mídia não pode ser vazio!");
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDistribuidora() {
		return distribuidora;
	}

	public void setDistribuidora(String distribuidora) {
		this.distribuidora = distribuidora;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) throws BusinessException{
		if(Validator.isZero(preco))
			throw new BusinessException("O preço de uma mídia não pode ser nulo!");
		this.preco = preco;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
	@Override
	public String toString() {
		return titulo;
	}
	
	@Override
	public boolean equals(Object obj) {
		Media midia = (Media) obj;
		return titulo.equals(midia.titulo);
	}
}
