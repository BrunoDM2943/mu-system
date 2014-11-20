package model;

import services.validator.Validator;
import dao.annotations.DataAccessClass;
import exceptions.BusinessException;

@DataAccessClass(daoImpl="dao.implementation.LivroDaoImpl")
public class Livro {
	
	private int codigo;
	
	private String titulo;
	
	private String autor;
	
	private String editora;
	
	private float preco;
	
	private int ano;
	
	
	public int getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getEditora() {
		return editora;
	}

	public float getPreco() {
		return preco;
	}

	public int getAno() {
		return ano;
	}

	public void setCodigo(int codigo) throws BusinessException{
		if (Validator.isZero(codigo))
			throw new BusinessException("O código do livro não pode ser vazio!");
		this.codigo = codigo;
	}

	public void setTitulo(String titulo) throws BusinessException{
		if (Validator.isEmpty(titulo))
			throw new BusinessException("O título de um livro não pode ser vazio!");
		this.titulo = titulo;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	//TODO: Validar a entrada de números negativos e letras!
	public void setPreco(float preco) throws BusinessException {
		if (Validator.isZero(preco))
			throw new BusinessException("O preço de um livro não pode ser zero!");
		this.preco = preco;
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
		Livro livro = (Livro) obj;
		return titulo.equals(livro.titulo);
	}
}