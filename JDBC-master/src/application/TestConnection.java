package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jdbc.db;

public class TestConnection {

	public static void main(String[] args) throws Exception, SQLException {
		
		Connection conexao =  db.getConexao();
		System.out.println("Conexão realizada com sucesso !");

		
		db.fechaConexao();
		System.out.println("Conexão fechada com sucesso !");

}
}
