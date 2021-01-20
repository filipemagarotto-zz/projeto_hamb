package br.com.projeto.modelo;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
 
import br.com.projeto.banco.Conexao;
 
public class Funcionarios extends Cliente {
    
    Scanner leitor = new Scanner(System.in);
    private String nome;
    private String login;
    private String senha;
    boolean continuar = true;
    boolean continuar2 = true;
    Connection con = Conexao.receberConexao();
    
    public void cadastrarUsuario() {
        while(continuar == true) {
            System.out.print("Nome: ");
            String nome = leitor.next();
            setNome(nome);
            
            System.out.print("Login: ");
            String login = leitor.next();
            setLogin(login);
            
            System.out.print("Senha: ");
            String senha = leitor.next();
            setSenha(senha);
            
            String sql = "INSERT INTO funclogin (nome, login, senha) values (?,?,?)";
            
            try {
                PreparedStatement preparador = con.prepareStatement(sql);
                
                preparador.setString(1, getNome());
                preparador.setString(2, getLogin());
                preparador.setString(3, getSenha());
            
                preparador.execute();
                
                System.out.println("\nUsuário cadastrado com sucesso =)\n");
                
                
                
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR");
            }
            continuar = false;
            break;
        }
    }
 
    public void editarUsuario() {
        while(continuar2 == true) {
            System.out.println("\n+--- Editar cadastro ---+");
            System.out.print("| Por 'segurança', digite seu nome: ");
            nome = leitor.next();
            setNome(nome);
            System.out.print("| Insira o novo login: ");
            login = leitor.next();
            setLogin(login);
            System.out.print("| Insira a nova senha: ");
            senha = leitor.next();
            setSenha(senha);
            System.out.println("+-----------------------+\n");
            
            String sql = "update funclogin set login = ? where nome = ?";
            String sql2 = "update funclogin set senha = ? where nome = ?";
            
            try {
                PreparedStatement preparador = con.prepareStatement(sql);
                PreparedStatement preparador2 = con.prepareStatement(sql2);
                preparador.setString(1, getLogin());
                preparador.setString(2, getNome());
                
                preparador2.setString(1, getSenha());
                preparador2.setString(2, getNome());
                
                preparador2.execute();
                preparador.execute();
                
                System.out.println("\nUsuario alterado com sucesso.\n");
                
                
            }catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR");
            }
            continuar2 = false;
            break;
        }
    }
 
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
 
}