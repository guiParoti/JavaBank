package modelo;

import java.time.LocalDate;

public class Cliente {
	
	private int id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private LocalDate dataCadastro;
	
	
	public Cliente(String nome, String cpf, String email, String senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.dataCadastro = LocalDate.now();
	}
	
	public Cliente(int id, String nome, String cpf, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
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


	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	
	public String toString() {
		return this.getNome() + "\n" + this.email;
	}
	
	
	
	

}
