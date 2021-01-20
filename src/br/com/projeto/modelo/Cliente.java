package br.com.projeto.modelo;
 
public class Cliente {
    
    //Variáveis essenciais
    private int id;
    private String nome;
    private String sobrenome;
    private int n_mesa;
    private String pagamento;
    private int pagamentoId;
 
    //Variáveis de consumo e preco
    private String lanche;
    private String bebida;
    
    //Getters e Setters
    
    public int getPagamentoId() {
        return pagamentoId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPagamentoId(int pagamentoId) {
        this.pagamentoId = pagamentoId;
    }
    public String getLanche() {
        return lanche;
    }
    public void setLanche(String lanche) {
        this.lanche = lanche;
    }
    public String getBebida() {
        return bebida;
    }
    public void setBebida(String bebida) {
        this.bebida = bebida;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public String getPagamento() {
        return pagamento;
    }
    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }
    public int getN_mesa() {
        return n_mesa;
    }
    public void setN_mesa(int n_mesa) {
        this.n_mesa = n_mesa;
    }   
}