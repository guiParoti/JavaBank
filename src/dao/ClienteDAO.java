package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import bancoDados.ConexaoSqlite;
import modelo.Cliente;

public class ClienteDAO {

	public ClienteDAO() {
		criarTabelaCliente();
	}

	private void criarTabelaCliente() {
		String sql = "CREATE TABLE IF NOT EXISTS clientes (" + "id_cliente INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nome TEXT," + "cpf TEXT UNIQUE NOT NULL," + "email TEXT UNIQUE NOT NULL," + "senha TEXT,"
				+ "data_cadastro TEXT);";

		try (Connection conexao = ConexaoSqlite.conectar(); Statement stm = conexao.createStatement()) {
			stm.execute(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela clientes!");
			e.printStackTrace();
		}

	}

	public void salvar(Cliente cliente) {
		String sql = "INSERT INTO clientes (nome, cpf, email, senha, data_cadastro)" + "VALUES (?, ?, ?, ?, ?)";

		try (Connection conexao = ConexaoSqlite.conectar(); PreparedStatement ps = conexao.prepareStatement(sql)) {

			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getSenha());
			ps.setString(5, cliente.getDataCadastro().toString());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erro ao criar conta!");
			e.printStackTrace();
		}
	}

}
