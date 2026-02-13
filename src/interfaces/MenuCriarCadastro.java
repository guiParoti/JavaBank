package interfaces;

import java.util.Scanner;

import dao.ClienteDAO;
import modelo.Cliente;

/*
 * Menu de Cadastro.
 * Responsavel pela interface de cadastro do cliente no banco, aqui você insere seus dados, como nome, cpf, email e senha.
 * Sendo seu cpf e senha utilizados para logar.
 */

public class MenuCriarCadastro {

	private Scanner entrada = MenuInicial.SCANNER;
	private ClienteDAO tabelaCliente = new ClienteDAO();

	public void cadastro() {
		System.out.println("--------------------------------");
		while (true) {
			System.out.print("Insira seu nome: ");
			String nomeCli = entrada.nextLine();

			if (validarNome(nomeCli)) {

				System.out.print("Insira seu CPF: ");
				String cpfCli = entrada.nextLine();

				if (validarCpf(cpfCli)) {
					
					System.out.print("Insira seu email: ");
					String emailCli = entrada.nextLine();

					if (validarEmail(emailCli)) {

						System.out.print("Crie uma senha: ");
						String senhaCli = entrada.nextLine();

						System.out.print("Confirme a senha: ");
						String senhaCliConf = entrada.nextLine();

						if (senhaCliConf.equals(senhaCli)) {
							Cliente cliente = new Cliente(nomeCli, cpfCli, emailCli, senhaCliConf);
							tabelaCliente.salvar(cliente);
							System.out.println("Conta criada com sucesso!");
							break;
						} else {
							System.out.println("As senhas não batem!");
							continue;
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else {
				continue;
			}
		}
		System.out.println("--------------------------------");

	}

	public boolean validarNome(String nome) {
		if (nome.length() >= 3 && nome.matches(("[A-Za-zÀ-ÖØ-öø-ÿ ]+"))) {
			return true;
		}
		System.out.println("Nome inválido!");
		return false;
	}

	public boolean validarCpf(String cpf) {
		if (!cpf.matches("\\d{11}")) {
			System.out.println("CPF inválido! Digite apenas 11 números!");
			return false;
		}
		return true;
	}

	public boolean validarEmail(String email) {
		if (email.contains("@") && email.endsWith(".com")) {
			return true;
		}
		System.out.println("Email inválido!");
		return false;
	}

}
