package modelo;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
	
	private int id;
	private String tipo;
	private double valor;
	private String data;
	private int idConta;
	private String horarioTransacao;
		
	public String getHorarioTransacao() {
		return horarioTransacao;
	}


	public Transacao(String tipo, double valor, int idConta) {
		this.tipo = tipo;
		this.valor = valor;
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.data = LocalDate.now().format(dataFormatada);
		DateTimeFormatter horaFormatada = DateTimeFormatter.ofPattern("HH:mm:ss");
		this.horarioTransacao = LocalTime.now().format(horaFormatada);
		this.idConta = idConta;
	}
	
	
	public Transacao(int id, String tipo, double valor, String data, String horario, int idConta) {
		this.id = id;
		this.tipo = tipo;
		this.valor = valor;
		this.data = data;
		this.horarioTransacao = horario;
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
	    DecimalFormat formatar = new DecimalFormat("#,##0.00");
	    String valorFormatado = formatar.format(valor);
	    return "--------------------------------\n"
	    		+"Valor: " + sinal + " " + valorFormatado +
	           "\nData: " + data + "\n" + "Horário: " + horarioTransacao + "\n";
	}

	

}
