package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import bancoDados.ConexaoSqlite;

public class ContaDAO {

	public ContaDAO() {
		criarTabelaConta();
	}

	private void criarTabelaConta() {
		String sql = "CREATE TABLE IF NOT EXISTS contas (" + "id_conta INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "numero_conta TEXT UNIQUE NOT NULL," + "agencia TEXT," + "saldo DECIMAL," + "tipo_conta TEXT);";

		try (Connection conexao = ConexaoSqlite.conectar(); Statement stm = conexao.createStatement()) {
			stm.execute(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela contas!");
			e.printStackTrace();
		}
	}

}
