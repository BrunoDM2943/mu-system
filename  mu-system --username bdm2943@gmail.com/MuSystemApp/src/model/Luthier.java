package model;

import services.validator.Validator;
import dao.annotations.DataAccessClass;
import enums.Especialidade;
import enums.Estado;
import exceptions.BusinessException;

@DataAccessClass(daoImpl = "dao.implementation.LuthierDaoImpl")
public class Luthier {
	
	private int cod;
	
	private String nome;
	
	private String cpf;
	
	private String endereco;
	
	private String bairro;

	private String cidade;
	
	private String uf;
	
	private String telefone;
	
	private String email;
	
	private String especialidade;
	
	public int getCod() {
		return cod;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}
	
	public String getUf() {
		return uf;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setCod(int cod) throws BusinessException {
		if(Validator.isZero(cod))
			throw new BusinessException("O código de um luthier não pode ser vazio!");
		this.cod = cod;
	}

	public void setNome(String nome) throws BusinessException {
		if (Validator.isEmpty(nome))
			throw new BusinessException("O nome de um luthier não pode ser vazio!");
		this.nome = nome;
	}
	
	public void setCpf(String cpf) throws BusinessException {
		if (Validator.isEmpty(cpf))
			throw new BusinessException("O CPF de um luthier não pode ser vazio!");
		this.cpf = cpf.replaceAll("\\.","").replaceAll("\\-", "");
	}

	public void setEndereco(String endereco) throws BusinessException {
		if (Validator.isEmpty(endereco))
			throw new BusinessException("O endereço de um luthier não pode ser vazio!");
		this.endereco = endereco;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void setTelefone(String telefone) {
		if(!Validator.isEmpty(telefone))
			this.telefone = telefone.replace(".", "").replace("-", "");
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//FIXME Fazer validação
	public void setEspecialidade(String especialidade) throws BusinessException {
		this.especialidade = especialidade;
	}

	@Override	
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean equals(Object obj) {		
		Luthier l = (Luthier) obj;		
		return l.getCpf().equals(this.cpf);	
	}
}