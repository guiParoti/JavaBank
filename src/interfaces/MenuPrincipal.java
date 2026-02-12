package interfaces;

import java.util.Scanner;

import dao.ClienteDAO;
import dao.ContaDAO;
import modelo.Cliente;
import modelo.Conta;

public class MenuPrincipal {

	private Scanner entrada = MenuInicial.SCANNER;
	private Cliente cliente;
	private Conta conta;
	private ClienteDAO clienteDados = new ClienteDAO();
	private ContaDAO contaDados = new ContaDAO();

	public MenuPrincipal(Cliente cliente) {
		this.cliente = cliente;
	}

	public void exibirMenuPrincipal() {
		System.out.println("--------------------------------");
		System.out.println("Seja bem-vindo " + cliente.getNome() + "!");

		while (true) {

			System.out.print("1- Abrir conta\n" + "2- Ver informações da conta\n" + "3- Ver saldo\n" + "4- Depositar\n"
					+ "5- Sacar\n" + "0- Sair\n" + "R: ");
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
					verSaldo();
					break;
				case 4:
					break;
				case 5:
					break;
				case 0:
					System.out.println("Encerrando sessão!");
					return;
				default:
					System.out.println("Insira um número de 0 a 5!");
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
			System.out.println("Você não possui uma conta ainda!");
		}
	}

	public void carregarInformacoesConta() {
		conta = contaDados.minhasInformacoes(cliente.getId());
		System.out.println("--------------------------------");
		if (conta != null) {
			System.out.println(conta);
		} else {
			System.out.println("Você não possui uma conta ainda!");
		}
	}

	public void verSaldo() {
		Conta contaS = contaDados.minhasInformacoes(cliente.getId());
		
		if (contaS != null) {
			double saldo = contaDados.meuSaldo(cliente.getId());
			System.out.println("--------------------------------");
			System.out.println("Saldo: " + String.format("R$ %.2f", saldo));
		}else {
			System.out.println("Você não possui uma conta ainda!");
		}
	}
}
