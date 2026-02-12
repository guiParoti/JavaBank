package modelo;

import java.time.LocalDate;

public class Cliente {
	
	private int id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private String dataCadastro;
	
	
	public Cliente(String nome, String cpf, String email, String senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.dataCadastro = LocalDate.now().toString();
	}
	
	public Cliente(int id, String nome, String cpf, String email, String senha, String dataCadastro) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
	}


	public int getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public String getCpf() {
		return cpf;
	}


	public String getEmail() {
		return email;
	}


	public String getSenha() {
		return senha;
	}


	public String getDataCadastro() {
		return dataCadastro;
	}
	
	@Override
	public String toString() {
		return "CPF: " + this.cpf + "\nNome: " + this.nome +
				"\nEmail: " + this.email + "\nCadastro: " + this.dataCadastro;
	}
	
	
	
	

}
