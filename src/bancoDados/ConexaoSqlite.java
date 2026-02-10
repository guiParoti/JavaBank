package bancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSqlite {

	private static final String URL = "jdbc:sqlite:dados.db";
	
	public static Connection conectar() {
		
		try {
			return DriverManager.getConnection(URL);
		}catch(SQLException e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}
}
