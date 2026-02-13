package interfaces;

import java.util.Scanner;

import dao.ClienteDAO;
import modelo.Cliente;

public class MenuLogin {

	/*
	 * Menu de Login. 
	 * Responsável pela interface login, nesse menu se você já possuir um cadastro, basta inserir as informações para logar.
	 */
	
	private Scanner entrada = MenuInicial.SCANNER;

	public void verificarLogin() {
		ClienteDAO tabela = new ClienteDAO();

		System.out.println("-------------Login--------------");
		while (true) {
			System.out.println("0 para sair");

			System.out.print("Digite seu CPF: ");
			String cpf = entrada.nextLine();

			if (cpf.equals("0")) {
				return;
			}

			System.out.print("Digite sua senha: ");
			String senha = entrada.nextLine();

			if (senha.equals("0")) {
				return;
			}

			Cliente cliente = tabela.verificarLogin(cpf, senha);

			if (cliente != null) {
				MenuPrincipal menuPrincipal = new MenuPrincipal(cliente);
				menuPrincipal.exibirMenuPrincipal();
			} else {
				System.out.println("CPF ou senha inválidos!");
				continue;
			}
			System.out.println("--------------------------------");

		}
	}

}
