/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

/**
 *
 * @author treinamento
 */
public class ListaConta {
    int id_conta;
    int id_item;
    String numero_documento;
    String titulo_conta;
    String nome_tipo;
    
    String nome_categoria;
    String nome_fornecedor;
    String descricao;
    double valor_conta;
    String data_vencimento_inicio;
    String data_vencimento_fim;
    String data_pagamento;
    int qtd_parcelas;
    String status;

    public ListaConta() {
    }

    public ListaConta(int id_conta, int id_item, String numero_documento, String titulo_conta, String nome_tipo, String nome_categoria, String nome_fornecedor, String descricao, double valor_conta, String data_vencimento_inicio, String data_vencimento_fim, String data_pagamento, int qtd_parcelas, String status) {
        this.id_conta = id_conta;
        this.id_item = id_item;
        this.numero_documento = numero_documento;
        this.titulo_conta = titulo_conta;
        this.nome_tipo = nome_tipo;
        this.nome_categoria = nome_categoria;
        this.nome_fornecedor = nome_fornecedor;
        this.descricao = descricao;
        this.valor_conta = valor_conta;
        this.data_vencimento_inicio = data_vencimento_inicio;
        this.data_vencimento_fim = data_vencimento_fim;
        this.data_pagamento = data_pagamento;
        this.qtd_parcelas = qtd_parcelas;
        this.status = status;
    }



      public ListaConta(int id_conta, int id_item, String numero_documento, String titulo_conta, String nome_tipo, String nome_categoria, String nome_fornecedor, String descricao, double valor_conta, String data_vencimento_inicio, int qtd_parcelas, String status,String data_pagamento) {
        this.id_conta = id_conta;
        this.id_item = id_item;
        this.numero_documento = numero_documento;
        this.titulo_conta = titulo_conta;
        this.nome_tipo = nome_tipo;
        this.nome_categoria = nome_categoria;
        this.nome_fornecedor = nome_fornecedor;
        this.descricao = descricao;
        this.valor_conta = valor_conta;
        this.data_vencimento_inicio = data_vencimento_inicio;
        this.qtd_parcelas = qtd_parcelas;
                this.data_pagamento = data_pagamento;
        this.status = status;
        
        
    }

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getTitulo_conta() {
        return titulo_conta;
    }

    public void setTitulo_conta(String titulo_conta) {
        this.titulo_conta = titulo_conta;
    }

    public String getNome_tipo() {
        return nome_tipo;
    }

    public void setNome_tipo(String nome_tipo) {
        this.nome_tipo = nome_tipo;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

    public String getNome_fornecedor() {
        return nome_fornecedor;
    }

    public void setNome_fornecedor(String nome_fornecedor) {
        this.nome_fornecedor = nome_fornecedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor_conta() {
        return valor_conta;
    }

    public void setValor_conta(double valor_conta) {
        this.valor_conta = valor_conta;
    }

    public String getData_vencimento_inicio() {
        return data_vencimento_inicio;
    }

    public void setData_vencimento_inicio(String data_vencimento_inicio) {
        this.data_vencimento_inicio = data_vencimento_inicio;
    }

    public String getData_vencimento_fim() {
        return data_vencimento_fim;
    }

    public void setData_vencimento_fim(String data_vencimento_fim) {
        this.data_vencimento_fim = data_vencimento_fim;
    }

    public String getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public int getQtd_parcelas() {
        return qtd_parcelas;
    }

    public void setQtd_parcelas(int qtd_parcelas) {
        this.qtd_parcelas = qtd_parcelas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  
   
    
}
