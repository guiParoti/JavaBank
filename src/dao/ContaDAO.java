package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bancoDados.ConexaoSqlite;
import modelo.Conta;

/*
 * DAO da conta do cliente, responsavel por criar a tabela e juntamente fazer o relacionamento entre conta e cliente.
 * Aqui contem os metodos de salvar uma conta, ler os dados da conta, verificar e atualizar saldo da conta. 
 */

public class ContaDAO {

	public ContaDAO() {
		criarTabelaConta();
	}

	private void criarTabelaConta() {
		String sql = "CREATE TABLE IF NOT EXISTS contas (" + "id_conta INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "numero_conta TEXT UNIQUE NOT NULL," + "agencia TEXT," + "saldo DECIMAL,"
				+ "tipo_conta TEXT, dataAbertura TEXT, id_cliente INTEGER NOT NULL,"
				+ "FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente));";

		try (Connection conexao = ConexaoSqlite.conectar(); Statement stm = conexao.createStatement()) {
			stm.execute(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela contas!");
			e.printStackTrace();
		}
	}

	public void salvar(Conta conta) {
		String sql = "INSERT INTO contas (agencia, numero_conta, saldo, tipo_conta, dataAbertura, id_cliente)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conexao = ConexaoSqlite.conectar();
				PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, conta.getAgencia());
			ps.setString(2, conta.getNumeroConta());
			ps.setDouble(3, conta.getSaldo());
			ps.setString(4, conta.getTipoConta());
			ps.setString(5, conta.getDataAbertura());
			ps.setInt(6, conta.getIdCliente());

			ps.executeUpdate();

			// Aqui ele vai pegar o id da conta gerado e atualizar o número da conta usando
			// o id formatado.
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int idGerado = rs.getInt(1);
				String numeroContaFormatado = String.format("%06d", idGerado);

				String atualizarNumeroConta = "UPDATE contas SET numero_conta = ? WHERE id_conta = ?";
				try (PreparedStatement psUpdate = conexao.prepareStatement(atualizarNumeroConta)) {
					psUpdate.setString(1, numeroContaFormatado);
					psUpdate.setInt(2, idGerado);
					psUpdate.executeUpdate();
				}
			}

		} catch (SQLException e) {
			System.out.println("Erro ao criar conta!");
			e.printStackTrace();
		}
	}

	public Conta minhasInformacoes(int id) {
		Conta conta = null;

		String sql = "SELECT * FROM contas WHERE id_cliente = ?";

		try (Connection conexao = ConexaoSqlite.conectar(); PreparedStatement stm = conexao.prepareStatement(sql)) {

			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				conta = new Conta(rs.getInt("id_conta"), rs.getString("agencia"), rs.getString("numero_conta"),
						rs.getDouble("saldo"), rs.getString("tipo_conta"), rs.getString("dataAbertura"),
						rs.getInt("id_cliente"));
			}
			;

		} catch (SQLException e) {
			System.out.println("Erro ao carregar informações da conta!");
			e.printStackTrace();
		}

		return conta;
	}

	public double meuSaldo(int id) {
		double saldo = 0;

		String sql = "SELECT saldo FROM contas WHERE id_cliente = ?";

		try (Connection conexao = ConexaoSqlite.conectar(); PreparedStatement ps = conexao.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				saldo = rs.getDouble("saldo");
			}
			;

		} catch (SQLException e) {
			System.out.println("Erro ao carregar saldo da conta!");
			e.printStackTrace();
		}
		return saldo;
	}

	public void depositar(int id, double valor) {
		String sql = "UPDATE contas SET saldo = saldo + ? WHERE id_conta = ?";

		try (Connection conexao = ConexaoSqlite.conectar(); PreparedStatement ps = conexao.prepareStatement(sql)) {

			ps.setDouble(1, valor);
			ps.setInt(2, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erro ao realizar deposito!");
			e.printStackTrace();
		}
	}
	
	public void sacar(int id, double valor) {
		String sql = "UPDATE contas SET saldo = saldo - ? WHERE id_conta = ?";

		try (Connection conexao = ConexaoSqlite.conectar(); PreparedStatement ps = conexao.prepareStatement(sql)) {

			ps.setDouble(1, valor);
			ps.setInt(2, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erro ao realizar saque!");
			e.printStackTrace();
		}
	}

}
