package modelo;

import java.time.LocalDate;

public class Conta {
	
	private int id;
	private String numeroConta;
	private String agencia;
	private double saldo;
	private String tipoConta;
	private LocalDate dataAbertura;
	
	
	
	public Conta(String numeroConta, String agencia, double saldo, String tipoConta) {
		this.numeroConta = numeroConta;
		this.agencia = agencia;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
		this.dataAbertura = LocalDate.now();
	}
	
	
	public Conta(int id, String numeroConta, String agencia, double saldo, String tipoConta) {
		this.id = id;
		this.numeroConta = numeroConta;
		this.agencia = agencia;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
	}


	public int getId() {
		return id;
	}


	public String getNumeroConta() {
		return numeroConta;
	}


	public String getAgencia() {
		return agencia;
	}


	public double getSaldo() {
		return saldo;
	}


	public String getTipoConta() {
		return tipoConta;
	}


	public LocalDate getDataAbertura() {
		return dataAbertura;
	}
	

}
