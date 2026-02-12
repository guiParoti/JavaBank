package modelo;

import java.time.LocalDate;

public class Conta {
	
	private int id;
	private String numeroConta;
	private String agencia = "0001";
	private double saldo;
	private String tipoConta;
	private String dataAbertura;
	private int idCliente;
	
	
	
	public Conta(String agencia, String numeroConta, double saldo, String tipoConta, int idCliente) {
		this.numeroConta = numeroConta;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
		this.dataAbertura = LocalDate.now().toString();
		this.idCliente = idCliente;
	}
	
	
	public Conta(int id, String agencia, String numeroConta, double saldo, String tipoConta, String dataAbertura, int idCliente) {
		this.id = id;
		this.numeroConta = numeroConta;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
		this.dataAbertura = dataAbertura;
		this.idCliente = idCliente;
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


	public String getDataAbertura() {
		return dataAbertura;
	}


	public int getIdCliente() {
		return idCliente;
	}
	
	public String toString() {
		return "Agencia: " + this.agencia + 
				"\nNúmero da conta: " + this.numeroConta + 
				"\nTipo de conta: " + this.tipoConta + "\nConta aberta em: " + this.getDataAbertura();
	}
	
	
	

}
