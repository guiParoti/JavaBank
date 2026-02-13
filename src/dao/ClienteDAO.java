package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bancoDados.ConexaoSqlite;
import modelo.Cliente;

/*
 * DAO da parte do cliente, responsavel por criar a tabela de clientes e salvar os dados de cadastro do cliente.
 * Juntamente tambem contém os metodos de ler os dados do cliente e vereficar os dados do cliente para login.
 */

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

	public Cliente verificarLogin(String cpf, String senha) {
		String sql = "SELECT * FROM clientes WHERE cpf = ? AND senha = ?";
		Cliente cliente = null;

		try (Connection conexao = ConexaoSqlite.conectar(); PreparedStatement ps = conexao.prepareStatement(sql)) {

			ps.setString(1, cpf);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("cpf"), 
						rs.getString("email"), rs.getString("senha"), rs.getString("data_cadastro"));
			}
			;

		} catch (SQLException e) {
			System.out.println("Erro ao verificar informações!");
			e.printStackTrace();
		}

		return cliente;

	}
	
	public Cliente minhasInformacoes(int id) {
		Cliente cliente = null;
		
		String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
		
		try(Connection conexao = ConexaoSqlite.conectar(); 
				PreparedStatement stm = conexao.prepareStatement(sql)) {
			
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("cpf"), 
						rs.getString("email"), rs.getString("senha"), rs.getString("data_cadastro"));

			};

		}catch (SQLException e) {
			System.out.println("Erro ao carregar informações do cliente!");
			e.printStackTrace();
		}
		
		return cliente;
	}

}
