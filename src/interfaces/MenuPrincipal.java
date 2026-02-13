package interfaces;

import java.util.List;
import java.util.Scanner;

import dao.ClienteDAO;
import dao.ContaDAO;
import dao.TransacaoDAO;
import modelo.Cliente;
import modelo.Conta;
import modelo.Transacao;

/*
 * Menu Principal. 
 * Responsável por toda interface principal do banco, nele é possivel abrir uma conta, consultar sua conta,
 * verificar a movimentação da conta(extrato), consultar o saldo e realizar saques e depósitos.
 * Cliente tem relacionamento de 1 para 1 com conta e conta tem um relacionamento de 1 para n com transações. 
 */

public class MenuPrincipal {

	private Scanner entrada = MenuInicial.SCANNER;
	private Cliente cliente;
	private Conta conta;
	private List<Transacao> transacoes;
	private ClienteDAO clienteDados = new ClienteDAO();
	private ContaDAO contaDados = new ContaDAO();
	private TransacaoDAO transacaoDados = new TransacaoDAO();

	public MenuPrincipal(Cliente cliente) {
		this.cliente = cliente;
	}

	public void exibirMenuPrincipal() {
		System.out.println("--------------------------------");
		System.out.println("Seja bem-vindo " + cliente.getNome() + "!");

		while (true) {

			System.out.print("1- Abrir conta\n" + "2- Ver informações da conta\n" + "3- Ver extrato" + "\n4- Ver saldo\n"
					+ "5- Depositar\n" + "6- Sacar\n" + "0- Sair\n" + "R: ");
			String opcaoMenuPrincipal = entrada.nextLine();

			try {
				int opcaoMenuPrincipalInt = Integer.parseInt(opcaoMenuPrincipal);

				switch (opcaoMenuPrincipalInt) {

				case 1:
					MenuAbrirConta menuAbrirconta = new MenuAbrirConta(cliente);
					menuAbrirconta.abrirConta();
					break;
				case 2:
					carregarInformacoesConta();
					break;
				case 3:
					verExtrato();
					break;
				case 4:
					verSaldo();
					break;
				case 5:
					depositar();
					break;
				case 6:
					sacar();
					break;
				case 0:
					System.out.println("Encerrando sessão!");
					return;
				default:
					System.out.println("Insira um número de 0 a 6!");
					continue;
				}

			} catch (NumberFormatException e) {
				System.out.println("Insira uma opção válida!");
				continue;
			}
			System.out.println("--------------------------------");
		}
	}

	public void carregarInformacoesCadastro() {
		cliente = clienteDados.minhasInformacoes(cliente.getId());
		System.out.println("--------------------------------");
		if (cliente != null) {
			System.out.println(cliente);
		} else {
			System.out.println("Erro ao carregar as informações!");
		}
	}

	public void carregarInformacoesConta() {
		System.out.println("--------------------------------");
		if (verificarContaExistente()) {
			System.out.println(conta);
		}
	}

	public void verExtrato() {
		if (verificarContaExistente()) {
			System.out.println("--------------------------------\n"
					+ "           EXTRATO");
			transacoes = transacaoDados.carregarExtrato(conta.getId());
			transacoes.forEach(System.out::print);
		}
	}

	public void verSaldo() {
		if (verificarContaExistente()) {
			double saldo = contaDados.meuSaldo(cliente.getId());
			System.out.println("--------------------------------");
			System.out.println("Saldo: " + String.format("R$ %.2f", saldo));
		}
	}

	public void depositar() {
		if (verificarContaExistente()) {
			System.out.println("Informe o valor do depósito que deseja fazer: ");
			String valorStr = entrada.nextLine();

			try {
				double valor = Double.parseDouble(valorStr);

				if (valor <= 0) {
					System.out.println("O valor do depósito deve ser maior do que zero!");
					return;
				}

				contaDados.depositar(conta.getId(), valor);
				Transacao transacao = new Transacao("Depósito", valor, conta.getId());
				transacaoDados.salvarTransacao(transacao);
				System.out.println("Depósito no valor de " + valor + " realizado!");

			} catch (NumberFormatException e) {
				System.out.println("Insira somente números!");
			}
		}
	}

	public void sacar() {
		if (verificarContaExistente()) {
			System.out.println("Informe o valor do saque que deseja fazer: ");
			String valorStr = entrada.nextLine();

			try {
				double valor = Double.parseDouble(valorStr);
				double saldoDaConta = contaDados.meuSaldo(conta.getId());

				if (valor > saldoDaConta) {
					System.out.println("Saldo insuficiente!");
					return;
				}

				if (valor <= 0) {
					System.out.println("O valor do saque deve ser maior que zero!");
					return;
				}

				contaDados.sacar(conta.getId(), valor);
				Transacao transacao = new Transacao("Saque", valor, conta.getId());
				transacaoDados.salvarTransacao(transacao);
				System.out.println("Saque no valor de " + valor + " realizado!");

			} catch (NumberFormatException e) {
				System.out.println("Insira somente números!");
			}
		}
	}

	private boolean verificarContaExistente() {
		conta = contaDados.minhasInformacoes(cliente.getId());
		if (conta == null) {
			System.out.println("Você não possui uma conta ainda!");
			return false;
		}
		return true;
	}

}
