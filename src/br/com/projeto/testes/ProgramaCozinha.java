package br.com.projeto.testes;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
 
import br.com.projeto.banco.Conexao;
import br.com.projeto.modelo.Cozinha;
import br.com.projeto.modelo.Funcionarios;
 
public class ProgramaCozinha {
 
    public static void main(String[] args) {
        
        Scanner leitor = new Scanner(System.in);
        boolean continuar = true;
        boolean continuar2 = true;
        Connection con = Conexao.receberConexao();
        
        while(continuar == true) {
            
            System.out.println("\n+------- Cozinha -------+");
            System.out.println("| 1 - Cadastrar Usuário |");
            System.out.println("| 2 - Logar             |");
            System.out.println("| 3 - Editar cadastro   |");
            System.out.println("| 4 - Sair do sistema   |");
            System.out.println("+-----------------------+\n");
            System.out.print("Selecione a opção: ");
            int opcao = leitor.nextInt();
            Funcionarios func = new Funcionarios();
            Cozinha cozinha = new Cozinha();
            
            switch(opcao) {
                case 1:
                    
                    
                    func.cadastrarUsuario();
                    
                    break;
                    
                    
                case 2:
                    System.out.print("Nome: ");
                    String nome = leitor.next();
                    func.setNome(nome);
                    
                    System.out.print("Login: ");
                    String login = leitor.next();
                    func.setLogin(login);
                    
                    System.out.print("Senha: ");
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
                                    System.out.println("| 1 - Checar pedidos em andamento  |");
                                    System.out.println("| 2 - Colocar para preparo         |");
                                    System.out.println("| 3 - Pedidos em preparo           |");
                                    System.out.println("| 4 - Colocar para pedidos prontos |");
                                    System.out.println("| 5 - Finalizar sessão             |");
                                    System.out.println("+---------------------------------+\n");
                                    System.out.print("Selecione a opção: ");
                                    opcao = leitor.nextInt();
                                    
                                    switch(opcao) {
                                    case 1:
                                        System.out.println("\nPedidos em andamento");
                                        
                                        String sql = "SELECT * FROM cozinha";
                                        
                                        preparador = con.prepareStatement(sql);
                                        resultado = preparador.executeQuery();
                                        
                                        while(resultado.next()) {
                                            System.out.println("\n+---- Pedidos em andamento -----+");
                                            System.out.println("Id: " + resultado.getInt("id"));
                                            System.out.println("Lanche: " + resultado.getString("lanche"));
                                            System.out.println("Bebida: " + resultado.getString("bebida"));
                                            System.out.println("N° da mesa: " + resultado.getInt("nmesa"));
                                            System.out.println("+-------------------------------+\n");
                                        }
                                        break;
                                    case 2:
                                        System.out.print("\nDigite o id do pedido: ");
                                        opcao = leitor.nextInt();
                                        
                                        sql = "INSERT INTO cozinhaPreparo(id, nome, sobrenome, nmesa, lanche, bebida, pagamento) values(?,?,?,?,?,?,?)";
                                        sql2 = "SELECT * FROM cozinha WHERE id = ?";
                                        String sql3 = "DELETE from cozinha WHERE id=?";                                         
                                            
                                        
                                        try {
                                            preparador = con.prepareStatement(sql2);
                                            preparador.setInt(1, opcao);
                                            resultado = preparador.executeQuery();
                                            
                                            PreparedStatement preparador2 = con.prepareStatement(sql);
                                            if(resultado.next()) {
                                                cozinha.setId(resultado.getInt("id"));
                                                cozinha.setNome(resultado.getString("nome"));
                                                cozinha.setSobrenome(resultado.getString("sobrenome"));
                                                cozinha.setN_mesa(resultado.getInt("nmesa"));
                                                cozinha.setLanche(resultado.getString("lanche"));
                                                cozinha.setBebida(resultado.getString("bebida"));
                                                cozinha.setPagamento(resultado.getString("pagamento"));
                                            }
                                            
                                            PreparedStatement preparador3 = con.prepareStatement(sql3);
                                            preparador3.setInt(1, opcao);
                                            
                                            preparador3.execute();
                                            
                                            preparador2.setInt(1, cozinha.getId());
                                            preparador2.setString(2, cozinha.getNome());
                                            preparador2.setString(3, cozinha.getSobrenome());
                                            preparador2.setInt(4, cozinha.getN_mesa());
                                            preparador2.setString(5, cozinha.getLanche());
                                            preparador2.setString(6, cozinha.getBebida());
                                            preparador2.setString(7, cozinha.getPagamento());
                                            
                                            preparador2.execute();
                                            
                                            System.out.println("\nPedido colocado em preparo =) mão na massa!!\n");
                                        }catch (SQLException e) {
                                            e.printStackTrace();
                                            System.out.println("ERROR");
                                        }
                                        break;
                                    case 3:
                                        sql = "SELECT * FROM cozinhaPreparo";
                                        
                                        preparador = con.prepareStatement(sql);
                                        resultado = preparador.executeQuery();
                                        
                                        while(resultado.next()) {
                                            System.out.println("\n+---- Pedidos em preparo -----+");
                                            System.out.println("Id: " + resultado.getInt("id"));
                                            System.out.println("Lanche: " + resultado.getString("lanche"));
                                            System.out.println("Bebida: " + resultado.getString("bebida"));
                                            System.out.println("N° da mesa: " + resultado.getInt("nmesa"));
                                            System.out.println("+-------------------------------+\n");
                                        }
                                        break;
                                    case 4:
                                        System.out.print("\nDigite o id do pedido: ");
                                        opcao = leitor.nextInt();
                                        
                                        sql = "INSERT INTO garçom(id, nome, sobrenome, nmesa, lanche, bebida, pagamento) values(?,?,?,?,?,?,?)";
                                        sql2 = "SELECT * FROM cozinhaPreparo WHERE id = ?";
                                        String sql4 = "DELETE from cozinhaPreparo WHERE id=?";                                          
                                            
                                        try {
                                            preparador = con.prepareStatement(sql2);
                                            preparador.setInt(1, opcao);
                                            resultado = preparador.executeQuery();
                                            
                                            PreparedStatement preparador2 = con.prepareStatement(sql);
                                            if(resultado.next()) {
                                                cozinha.setId(resultado.getInt("id"));
                                                cozinha.setNome(resultado.getString("nome"));
                                                cozinha.setSobrenome(resultado.getString("sobrenome"));
                                                cozinha.setN_mesa(resultado.getInt("nmesa"));
                                                cozinha.setLanche(resultado.getString("lanche"));
                                                cozinha.setBebida(resultado.getString("bebida"));
                                                cozinha.setPagamento(resultado.getString("pagamento"));
                                            }
                                            
                                            PreparedStatement preparador3 = con.prepareStatement(sql4);
                                            preparador3.setInt(1, opcao);
                                            
                                            preparador3.execute();
                                            
                                            preparador2.setInt(1, cozinha.getId());
                                            preparador2.setString(2, cozinha.getNome());
                                            preparador2.setString(3, cozinha.getSobrenome());
                                            preparador2.setInt(4, cozinha.getN_mesa());
                                            preparador2.setString(5, cozinha.getLanche());
                                            preparador2.setString(6, cozinha.getBebida());
                                            preparador2.setString(7, cozinha.getPagamento());
                                            
                                            preparador2.execute();
                                            
                                            System.out.println("\nPedido finalizado :D!!\n");
                                        }catch (SQLException e) {
                                            e.printStackTrace();
                                            System.out.println("ERROR");
                                        }       
                                    break;
                                    case 5:
                                        continuar2 = false;
                                        System.out.println("Sistema fechado");
                                    }
                                }                               
                            } else {
                                
                                System.out.println("login e/ou senha inválidos\n");
                            }
                        }
                        preparador.execute();       
                        
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