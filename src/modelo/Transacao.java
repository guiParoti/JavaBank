package modelo;

import java.time.LocalDate;

public class Transacao {
	
	private int id;
	private String tipo;
	private double valor;
	private String data;
	private int idConta;
		
	public Transacao(String tipo, double valor, int idConta) {
		this.tipo = tipo;
		this.valor = valor;
		this.data = LocalDate.now().toString();
		this.idConta = idConta;
	}
	
	
	public Transacao(int id, String tipo, double valor, String data, int idConta) {
		this.id = id;
		this.tipo = tipo;
		this.valor = valor;
		this.data = data;
		this.idConta = idConta;
	}
	
	public int getId() {
		return id;
	}
	public String getTipo() {
		return tipo;
	}
	public double getValor() {
		return valor;
	}
	public String getData() {
		return data;
	}
	public int getIdConta() {
		return idConta;
	}
	
	@Override
	public String toString() {
	    String sinal = tipo.equalsIgnoreCase("Depósito") ? "+" : "-";
	    return "--------------------------------\n"
	    		+"Valor: " + sinal + String.format("R$ %.2f", valor) +
	           "\nData: " + data + "\n";
	}

	

}
