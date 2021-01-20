package br.com.projeto.banco;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
 
    public static Connection receberConexao() {
        
        Connection con = null;
        
        try {
        	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_hamb", "root", "toor");
            System.out.println("");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados." + e.getMessage());
        }
        
        return con;
        
    }
}