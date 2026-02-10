package interfaces;

import java.util.Scanner;

public class MenuInicial {

	public static final Scanner SCANNER = new Scanner(System.in);

	public MenuInicial() {

		while (true) {
			System.out.println("----------SEJA BEM-VINDO AO JAVABANK----------");
			System.out.print("1- Já sou cliente\n" + "2- Criar conta\n" + "0- Sair\n" + "R: ");
			String opcaoInicio = SCANNER.nextLine();

			try {
				int opcaoInicioInt = Integer.parseInt(opcaoInicio);

				switch (opcaoInicioInt) {

				case 1:
					MenuLogin login = new MenuLogin();
					login.verificarLogin();
					break;

				case 2:
					MenuCriarCadastro criar = new MenuCriarCadastro();
					criar.cadastro();
					break;

				case 0:
					System.out.println("Até mais!");
					return;

				default:
					System.out.println("Insira um número de 0 a 3!");
					continue;
				}

			} catch (NumberFormatException e) {
				System.out.println("Insira uma opção válida!");
				continue;
			}
		}

	}

}
