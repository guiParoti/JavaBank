package interfaces;

import java.text.DecimalFormat;
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
	
	// CPF 36415114530
	// guilhermeparoti57@gmail.com
	// guisenhafalsa1234

	public void exibirMenuPrincipal() {
		linhas();
		System.out.println("Seja bem-vindo " + cliente.getNome() + "!");
		linhas();

		while (true) {

			System.out.print("1- Abrir conta\n" + "2- Ver informações da conta\n" + "3- Ver extrato"
					+ "\n4- Ver saldo\n" + "5- Depositar\n" + "6- Sacar\n" + "0- Sair\n" + "R: ");
			String opcaoMenuPrincipal = entrada.nextLine();

			try {
				int opcaoMenuPrincipalInt = Integer.parseInt(opcaoMenuPrincipal);

				switch (opcaoMenuPrincipalInt) {

				case 1:
					if (verificarContaExistente(1) == false) {
						System.out.println("Você já possui uma conta aberta!");
					} else {
						MenuAbrirConta menuAbrirconta = new MenuAbrirConta(cliente);
						menuAbrirconta.abrirConta();
					}
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
					new MenuInicial();
				default:
					System.out.println("Insira um número de 0 a 6!");
					continue;
				}

			} catch (NumberFormatException e) {
				System.out.println("Insira uma opção válida!");
				continue;
			}
			linhas();
		}
	}

	public void carregarInformacoesCadastro() {
		cliente = clienteDados.minhasInformacoes(cliente.getId());
		linhas();
		if (cliente != null) {
			System.out.println(cliente);
		} else {
			System.out.println("Erro ao carregar as informações!");
		}
	}

	public void carregarInformacoesConta() {
		linhas();
		if (verificarContaExistente(2)) {
			System.out.println(conta);
		}
	}

	public void verExtrato() {
		if (verificarContaExistente(3)) {
			System.out.println("--------------------------------\n" + "           EXTRATO");
			transacoes = transacaoDados.carregarExtrato(conta.getId());
			transacoes.forEach(System.out::print);
		}
	}

	public void verSaldo() {
		if (verificarContaExistente(4)) {
			double saldo = contaDados.meuSaldo(cliente.getId());
			DecimalFormat formatar = new DecimalFormat("#,##0.00");
			String saldoFormatado = formatar.format(saldo);
			System.out.println("--------------------------------");
			System.out.println("Saldo: " + saldoFormatado);
		}
	}

	public void depositar() {
		if (verificarContaExistente(5)) {
			System.out.println("Informe o valor do depósito que deseja fazer: ");
			String valorStr = entrada.nextLine();

			try {
				double valor = Double.parseDouble(valorStr);

				if (valor <= 0) {
					System.out.println("O valor do depósito deve ser maior do que zero!");
					return;
				}

				DecimalFormat formatar = new DecimalFormat("#,##0.00");
				String depositoFormatado = formatar.format(valor);

				contaDados.depositar(conta.getId(), valor);
				Transacao transacao = new Transacao("Depósito", valor, conta.getId());
				transacaoDados.salvarTransacao(transacao);
				System.out.println("Depósito no valor de " + depositoFormatado + " realizado!");

			} catch (NumberFormatException e) {
				System.out.println("Insira somente números!");
			}
		}
	}

	public void sacar() {
		if (verificarContaExistente(6)) {
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

	private boolean verificarContaExistente(int opcao) {
		conta = contaDados.minhasInformacoes(cliente.getId());
		if (conta == null && opcao > 1) {
			System.out.println("Você não possui uma conta ainda!");
			return false;
		} else if (conta != null && opcao == 1) {
			return false;
		}
		return true;
	}
	
	public void linhas() {
		System.out.println("--------------------------------");
	}

}
