package interfaces;

import java.util.Scanner;
import java.util.UUID;

import dao.ContaDAO;
import modelo.Cliente;
import modelo.Conta;

public class MenuAbrirConta {

	private Scanner entrada = MenuInicial.SCANNER;
	private Cliente cliente;

	public MenuAbrirConta(Cliente cliente) {
		this.cliente = cliente;
	}

	public void abrirConta() {
		ContaDAO tabelaConta = new ContaDAO();

		while (true) {
			System.out.println("--------------------------------");
			System.out.print("Tipo de conta\n" + "1- Corrente\n" + "2- Poupança\n" + "0- Sair\n" + "R: ");
			String opcao = entrada.nextLine();

			try {
				int opcaoTipoConta = Integer.parseInt(opcao);

				if (opcaoTipoConta == 0) {
					System.out.println("Até mais!");
					return;
				}

				if (opcaoTipoConta != 1 && opcaoTipoConta != 2) {
					System.out.println("Insira uma opção válida!");
					continue;
				}

				String tipo = (opcaoTipoConta == 1) ? "Corrente" : "Poupança";

				System.out.print("Você selecionou conta " + tipo + ", tem certeza?\n1- Sim\n2- Não\n" + "R: ");
				String confirmacao = entrada.nextLine();
				String numeroConta = UUID.randomUUID().toString().substring(0, 8);

				if (confirmacao.equals("1")) {
					Conta conta = new Conta("0001", numeroConta, 0.0, tipo, cliente.getId());

					tabelaConta.salvar(conta);
					System.out.println("Conta criada com sucesso!");
					return;
				} else {
					continue;
				}

			} catch (NumberFormatException e) {
				System.out.println("Insira apenas números!");
				continue;
			}
		}

	}

}
