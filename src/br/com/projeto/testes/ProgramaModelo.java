package br.com.projeto.testes;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
 
import br.com.projeto.banco.Conexao;
import br.com.projeto.modelo.Cliente;
 
public class ProgramaModelo {
 
    public static void main(String[] args) {
        
        Scanner leitor = new Scanner(System.in);
        boolean continuar = true;
        Connection con = Conexao.receberConexao();
 
        while(continuar == true) {
            System.out.println("+---- CARDPÁPIO VIRTUAL ----+ ");
            System.out.println("|                           |");
            System.out.println("| 1 - Cadastrar dados       |");
            System.out.println("| 2 - Lanches               |");
            System.out.println("| 3 - Bebidas               |");
            System.out.println("| 4 - Modo de pagamento     |");
            System.out.println("| 5 - Enviar para a cozinha |");
            System.out.println("| 6 - Abandonar sistema     |");
            System.out.println("+---------------------------+");
            System.out.print("\nDigite a opção: ");
            int opcao = leitor.nextInt();
            boolean continuar2 = true;
            Cliente dados = new Cliente();
            
            switch(opcao) { 
                case 1 :
                    System.out.print("Nome: ");
                    String nome = leitor.next();
                    dados.setNome(nome);
                    
                    System.out.print("Sobrenome: ");
                    String sobrenome = leitor.next();
                    dados.setSobrenome(sobrenome);
                    
                    System.out.print("Número da mesa: ");
                    int nmesa = leitor.nextInt();
                    dados.setN_mesa(nmesa);
                    
                    String sql = "INSERT INTO cliente(nome, sobrenome, nmesa) values(?,?,?)";
                    try {
                        PreparedStatement preparador = con.prepareStatement(sql);
                        preparador.setString(1, dados.getNome());
                        preparador.setString(2, dados.getSobrenome());
                        preparador.setInt(3, dados.getN_mesa());
                        
                        preparador.execute();
                        System.out.println("\nCadastro enviado com sucesso! \n");
                        
                        
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("ERROR");
                    }
                    break;
                case 2:
                    try {
                        while(continuar2 == true) {
                            System.out.println("\n+----------------------------------+");
                            System.out.println("| A1 - Burguer Bistro  |   R$10,00 |");
                            System.out.println("| A2 - Baretto Duplo   |   R$12,00 |");
                            System.out.println("| A3 - X-Salada        |   R$9,00  |");
                            System.out.println("| A4 - X-Tudo          |   R$14,00 |");
                            System.out.println("+----------------------------------+\n");
                            
                            System.out.print("Qual lanche deseja: ");
                            String hamb = leitor.next();
                            dados.setLanche(hamb);                      
 
                            String sql2 = "update cliente set lanche = ? where nome = ?";
                            
                            try {
                                
                                PreparedStatement preparador = con.prepareStatement(sql2);      
                                
                                System.out.print("Nome para confirmação: ");
                                nome = leitor.next();
                                dados.setNome(nome);
                                
                                preparador.setString(1, dados.getLanche());
                                preparador.setString(2, dados.getNome());
                                preparador.execute();
                                System.out.println("\nLanche registrado. =)\n");
                                
                            } catch (SQLException e) {
                                e.printStackTrace();
                                System.out.println("ERROR");
                            }
                            
                            continuar2 = false;
                        }
                        break;
                    }catch(ArrayIndexOutOfBoundsException e) {
                        System.out.println(e);
                    }
                    break;
                case 3:
                    try {
                        while(continuar2 == true) {
                            System.out.println("\n+--------------------------------+");
                            System.out.println("| B1 - Coca-Cola      |   R$5,00 |");
                            System.out.println("| B2 - Guaraná        |   R$5,00 |");
                            System.out.println("| B3 - Água           |   R$3,00 |");
                            System.out.println("| B4 - H20            |   R$4,00 |");
                            System.out.println("+--------------------------------+\n");
                            
                            System.out.print("Qual bebida deseja: ");
                            String liq = leitor.next();
                            
                            String sql3 = "update cliente set bebida = ? where nome = ?";
                            dados.setBebida(liq);
                                        
                            try {
                                PreparedStatement preparador = con.prepareStatement(sql3);
                                
                                System.out.print("Nome para confirmação: ");
                                nome = leitor.next();
                                dados.setNome(nome);
                                
                                preparador.setString(2, dados.getNome());
                                preparador.setString(1, dados.getBebida());
                                
                                preparador.execute();
                                System.out.println("\nBebida registrada. =)\n");
                                
                                
                            } catch (SQLException e) {
                                e.printStackTrace();
                                System.out.println("ERROR");
                            }
                            continuar2 = false;
                        }
                    }catch(ArrayIndexOutOfBoundsException e) {
                        System.out.println(e);
                    }
                    break;
                case 4:
                    System.out.println("\n+---- Modo de pagamento ----+");
                    System.out.println("| 1 - Crédito               |");
                    System.out.println("| 2 - Débito                |");
                    System.out.println("| 3 - PicPay                |");
                    System.out.println("| 4 - Dinheiro              |");
                    System.out.println("+---------------------------+");
                    System.out.print(" Insira a forma de pagamento: ");
                    int pag = leitor.nextInt();
                    dados.setPagamentoId(pag);
                    
                    String sql4 = "update cliente set pagamento = ? where nome = ?";
                    
                    try { 
                        PreparedStatement preparador = con.prepareStatement(sql4);
                        
                        System.out.print("Nome para confirmação: ");
                        nome = leitor.next();
                        dados.setNome(nome);
                        
                        if(dados.getPagamentoId() == 1 || dados.getPagamentoId() == 2 || dados.getPagamentoId() == 3) {
                            preparador.setString(2, dados.getNome());
                            preparador.setString(1, "Pagamento aprovado");
                            System.out.println("\nStatus de aprovado foi anotado.\n");
                            
                            preparador.execute();
                        } else if (dados.getPagamentoId() == 4) {
                            preparador.setString(2, dados.getNome());
                            preparador.setString(1, "Pendente");
                            System.out.println("\nStatus de pendente foi registrado.\n");
                            
                            preparador.execute();
                        } else {
                            System.out.println("Opção inválida");
                        }
                    }catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("ERROR");
                    }
                    break;
                case 5:
                    System.out.println("+---------------+");
                    System.out.println("| 1 - ENVIAR    |");
                    System.out.println("| 2 - RECUSAR   |");
                    System.out.println("+---------------+");
                    System.out.print(" Podemos enviar para a cozinha: ");
                    int arg = leitor.nextInt();
                    
                    if(arg == 1) {
                        System.out.print("Nome para confirmação: ");
                        String conf = leitor.next();
                        try {
                            String sql6 = "SELECT * from cliente WHERE nome = ?";
                            PreparedStatement preparador = con.prepareStatement(sql6);
                            preparador.setString(1, conf);
                
                        }catch (SQLException e) {
                            e.printStackTrace();
                            System.out.println("ERROR");
                        }
                        try { 
                            String sql5 = "SELECT * from cliente WHERE nome = ?";
                            PreparedStatement preparador2 = con.prepareStatement(sql5);
                            preparador2.setString(1, conf);
                            ResultSet resultado = preparador2.executeQuery();
                            
                            if(resultado.next()) { 
                                
                                if(resultado.getString("nome") == null || resultado.getString("sobrenome") == null || 
                                        resultado.getString("nmesa") == null || resultado.getString("pagamento") == null) {
                                    System.out.println("Não foi possível enviar para a cozinha."
                                            + " Algum dos requisitos não foi preenchido");
                                    break;
                                }
                                
                                dados.setNome(resultado.getString("nome"));
                                dados.setSobrenome(resultado.getString("sobrenome"));
                                dados.setN_mesa(resultado.getInt("nmesa"));
                                dados.setLanche(resultado.getString("lanche"));
                                dados.setBebida(resultado.getString("bebida"));
                                dados.setPagamento(resultado.getString("pagamento"));
 
                                String coz = "insert into cozinha (nome, sobrenome, nmesa, lanche, bebida, pagamento) values (?,?,?,?,?,?)";
                                PreparedStatement preparador = con.prepareStatement(coz);
                                
                                preparador.setString(1, dados.getNome());
                                preparador.setString(2, dados.getSobrenome());
                                preparador.setInt(3, dados.getN_mesa());
                                preparador.setString(4, dados.getLanche());
                                preparador.setString(5, dados.getBebida());
                                preparador.setString(6, dados.getPagamento());
                                
                                System.out.println("\nSeus dados: ");
                                System.out.println("+-----------------------------------------------+");
                                System.out.println("| Nome: " + resultado.getString("nome") + "                                  |");
                                System.out.println("| Sobrenome: " + resultado.getString("sobrenome")  + "                          |");
                                System.out.println("| N° da mesa: " + resultado.getString("nmesa") + "                                 |");
                                System.out.println("| Lanche: " + resultado.getString("lanche") + "                                    |");
                                System.out.println("| Bebida: " + resultado.getString("bebida") + "                                    |");
                                System.out.println("| Status do seu Pagamento: " +resultado.getString("pagamento") + "   |");
                                System.out.println("+-----------------------------------------------+");
                                
                                preparador.execute();
                                System.out.println("\nPedido enviado com sucesso =). Aguarde a entrega!\n");
                            }                                                       
                        }catch (SQLException e) {
                            e.printStackTrace();
                            System.out.println("ERROR");
                        }
                        
                        
                    }else if(arg == 2) {
                        break;
                    }else {
                        System.out.println("error");
                    }
                        
                    
                    break;
                case 6:
                    System.exit(0);
                    System.out.println("Cardápio fechado.");
                default:
                    System.out.println("Opção inválida");
            }
        
            
        }
        leitor.close();
    }
 
}