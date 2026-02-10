package interfaces;

import java.util.Scanner;

import dao.ClienteDAO;
import modelo.Cliente;

public class MenuPrincipal {

	private Scanner entrada = MenuInicial.SCANNER;
	private Cliente cliente;
	
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
//					MenuAbrirConta conta = new MenuAbrirConta();
					break;
				case 2:
					carregarInformacoes();
					break;
				case 3:
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

	public void carregarInformacoes() {
		ClienteDAO clienteDados = new ClienteDAO();
		cliente = clienteDados.minhasInformacoes(cliente.getId());
		System.out.println("--------------------------------");
		if (cliente != null) {
			System.out.println(cliente);
		}else {
			System.out.println("Erro ao carregar as informações!");
		}
		System.out.println("--------------------------------");
	}
}
