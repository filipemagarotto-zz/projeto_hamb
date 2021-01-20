package br.com.projeto.testes;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
 
import br.com.projeto.banco.Conexao;
import br.com.projeto.modelo.Funcionarios;
 
public class ProgramaGarçom {
 
    public static void main(String[] args) {
 
        Scanner leitor = new Scanner(System.in);
        boolean continuar = true;
        boolean continuar2 = true;
        Connection con = Conexao.receberConexao();
        Funcionarios func = new Funcionarios();
        
        
        while(continuar == true) {
            System.out.println("\n+------- Garçom -------+");
            System.out.println("| 1 - Cadastrar Usuário |");
            System.out.println("| 2 - Logar             |");
            System.out.println("| 3 - Editar cadastro   |");
            System.out.println("| 4 - Sair do sistema   |");
            System.out.println("+-----------------------+\n");
            System.out.println("Selecione a opção: ");
            int opcao = leitor.nextInt();
 
            switch(opcao) {
                case 1:
                    
                    func.cadastrarUsuario();
                    break;
                    
                case 2:
                    System.out.println("Nome: ");
                    String nome = leitor.next();
                    func.setNome(nome);
                    
                    System.out.println("Login: ");
                    String login = leitor.next();
                    func.setLogin(login);
                    
                    System.out.println("Senha: ");
                    String senha = leitor.next();
                    func.setSenha(senha);
                    
                    String sql2 = "SELECT * from funclogin WHERE nome = ?";
                    try {
                        PreparedStatement preparador = con.prepareStatement(sql2);
                        preparador.setString(1, func.getNome());
                        ResultSet resultado = preparador.executeQuery();
                        if(resultado.next()) {
                            if(resultado.getString("login").equals(func.getLogin()) && resultado.getString("senha").equals(func.getSenha())) {
                                System.out.println("---> Login Correto\nSeja bem-vindo, "+func.getNome() + "\n");
                                while(continuar2 == true) {
                                    System.out.println("\n+-------- Sistema cozinha --------+");
                                    System.out.println("| 1 - Editar pedido               |");
                                    System.out.println("| 2 - Pedidos prontos             |");
                                    System.out.println("| 3 - Finalizar sessão            |");
                                    System.out.println("+---------------------------------+\n");
                                    System.out.print("Selecione a opção: ");
                                    opcao = leitor.nextInt();
                                    switch(opcao) {
                                        case 1:
                                            System.out.print("pedido de quem: ");
                                            nome = leitor.next();
                                            func.setNome(nome);
 
                                            try {
                                                System.out.print("O que deseja mudar: ");
                                                String pedido = leitor.next();
                                                
                                                System.out.print("Para que deseja alterar: ");
                                                String forwhat = leitor.next();
                                                
                                                if(pedido.equals("lanche")) {
                                                    sql2 = "update cozinha set lanche = ? where nome = ?";
                                                    PreparedStatement preparador2 = con.prepareStatement(sql2);     
                                                    preparador2.setString(1, forwhat);
                                                    preparador2.setString(2, nome);
                                                    preparador2.execute();  
                                                    System.out.println("\n----> Comida alterada com sucesso.");
                                                    
                                                } else if(pedido.equals("bebida")) {
                                                    sql2 = "update cozinha set bebida = ? where nome = ?";
                                                    PreparedStatement preparador2 = con.prepareStatement(sql2);
                                                    preparador2.setString(1, forwhat);
                                                    preparador2.setString(2, nome);
                                                    preparador2.execute();  
                                                    System.out.println("\n----> Bebida alterada com sucesso =) ");
                                                }
                                                String sql = "update cozinha set pagamento = ? where nome = ?";
                                                PreparedStatement preparador2 = con.prepareStatement(sql);
                                                preparador2.setString(1, "Pendente");
                                                preparador2.setString(2, nome);
                                                preparador2.execute();
                                            
                                            }catch (SQLException e) {
                                                e.printStackTrace();
                                                System.out.println("ERROR");
                                            }
                                            break;
                                        case 2:
                                            String sql = "SELECT * FROM garçom";
                                            
                                            preparador = con.prepareStatement(sql);
                                            resultado = preparador.executeQuery();
                                            
                                            while(resultado.next()) {
                                                System.out.println("\n+---- Pedidos prontos -----+");
                                                System.out.println("Id: " + resultado.getInt("id"));
                                                System.out.println("Lanche: " + resultado.getString("lanche"));
                                                System.out.println("Bebida: " + resultado.getString("bebida"));
                                                System.out.println("N° da mesa: " + resultado.getInt("nmesa"));
                                                System.out.println("+-------------------------------+\n");
                                            }
                                            break;
                                        case 3:
                                            continuar2 = false;
                                            break;
                                        default:
                                            System.out.println("Opção inválida.");
                                    }
                                }
                                
 
                                
                            } else {
                                System.out.println("\nlogin e/ou senha inválidos.\n");
                            }
                        }
                        
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("ERROR");
                    }
                    break;
                    
                case 3:
                    
                    func.editarUsuario();
                    break;
                    
                case 4:
                    System.exit(0);
                    System.out.println("Sistema finalizado.");
                default:
                    System.out.println("Opção inválida");
            }
        }
        leitor.close();
 
    }
 
}