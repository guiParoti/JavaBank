package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bancoDados.ConexaoSqlite;
import modelo.Transacao;

/*
 * DAO das transações da conta, aqui é responsavel por criar a tabela de transações junto com relacionamento de transações e conta.
 * Aqui contém os metodos para salvar uma transação e ler os dados de transações da conta.
 */

public class TransacaoDAO {
	
	public TransacaoDAO() {
		criarTabelaTransacoes();
	}

	private void criarTabelaTransacoes() {
		String sql = "CREATE TABLE IF NOT EXISTS transacoes("
				+ "id_transacao INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "tipo TEXT NOT NULL,"
				+ "valor REAL NOT NULL,"
				+ "data TEXT NOT NULL,"
				+ "id_conta INTEGER NOT NULL,"
				+ "FOREIGN KEY (id_conta) REFERENCES contas(id_conta));";
		
		try (Connection conexao = ConexaoSqlite.conectar(); Statement stm = conexao.createStatement()) {
			stm.execute(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela transações!");
			e.printStackTrace();
		}
		
	}
	
	public void salvarTransacao(Transacao transacao) {
		String sql = "INSERT INTO transacoes (tipo, valor, data, id_conta) "
				+ "VALUES (?, ?, ?, ?)";
		
		try (Connection conexao = ConexaoSqlite.conectar();
				PreparedStatement ps = conexao.prepareStatement(sql)) {
			
			ps.setString(1, transacao.getTipo());
			ps.setDouble(2, transacao.getValor());
			ps.setString(3, transacao.getData());
			ps.setInt(4, transacao.getIdConta());
			
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("Erro ao salvar transação!");
			e.printStackTrace();
		}

	}
	
	public List<Transacao> carregarExtrato(int id) {
		List<Transacao> listaDeTransacoes = new ArrayList<Transacao>();
		String sql = "SELECT * FROM transacoes WHERE id_conta = ? ORDER BY id_transacao DESC";
		
		try (Connection conexao = ConexaoSqlite.conectar();
				PreparedStatement stm = conexao.prepareStatement(sql)) {

			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Transacao transacao = new Transacao(rs.getInt("id_transacao"),
						rs.getString("tipo"), rs.getDouble("valor"), rs.getString("data"), rs.getInt("id_conta"));
				listaDeTransacoes.add(transacao);
			};

		} catch (SQLException e) {
			System.out.println("Erro ao carregar informações da conta!");
			e.printStackTrace();
		}
		
		return listaDeTransacoes;
	}

}
