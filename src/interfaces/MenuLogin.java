package interfaces;

import java.util.Scanner;

import dao.ClienteDAO;
import modelo.Cliente;

public class MenuLogin {
	
	private Scanner entrada = MenuInicial.SCANNER;
	
	public void verificarLogin() {
		ClienteDAO tabela = new ClienteDAO();
		
		System.out.println("--------------------------------");
		System.out.print("Digite seu CPF: ");
		String cpf = entrada.nextLine();
		
		System.out.print("Digite sua senha: ");
		String senha = entrada.nextLine();
		
		Cliente cliente = tabela.verificarLogin(cpf, senha);
		
		if(cliente != null) {
			MenuPrincipal menuPrincipal = new MenuPrincipal(cliente);
			menuPrincipal.exibirMenuPrincipal();
		}else {
			System.out.println("CPF ou senha inválidos!");
		}
		System.out.println("--------------------------------");
		
	}

}
