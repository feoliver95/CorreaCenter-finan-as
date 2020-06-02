
package Classe;


public class Fornecedor {
    
    int id_fornecedor;
    String nome_fornecedor;

    public Fornecedor() {
    }

    public Fornecedor(int id_fornecedor, String nome_fornecedor) {
        this.id_fornecedor = id_fornecedor;
        this.nome_fornecedor = nome_fornecedor;
    }

    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(int id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public String getNome_fornecedor() {
        return nome_fornecedor;
    }

    public void setNome_fornecedor(String nome_fornecedor) {
        this.nome_fornecedor = nome_fornecedor;
    }
    
    
    
    
}
