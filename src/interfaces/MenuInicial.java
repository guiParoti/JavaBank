package interfaces;

import java.util.Scanner;

public class MenuInicial {

	public static final Scanner SCANNER = new Scanner(System.in);

	public MenuInicial() {
		
		System.out.println("----------SEJA BEM-VINDO AO JAVABANK----------");
		while(true) {
		System.out.print("1- Já sou cliente\n"
				+ "2- Criar conta\n"
				+ "3- Sair\n"
				+ "R: ");
			String opcaoInicio = SCANNER.nextLine();	
			
			try {
				int opcaoInicioInt = Integer.parseInt(opcaoInicio);
				
				switch(opcaoInicioInt) {
				
				case 1:
					MenuLogin login = new  MenuLogin();
					login.verificarLogin();
					break;
				
				case 2:
					MenuCriarCadastro criar = new MenuCriarCadastro();
					criar.cadastro();
					break;
				
				case 3:
					System.out.println("Até mais!");
					return;
					
				default:
					System.out.println("Insira um número de 1 a 3!");
					continue;
				}
				
				
			
			}catch (NumberFormatException e) {
				System.out.println("Insira uma opção válida!");
				continue;
			}
		}
			
	}

}
