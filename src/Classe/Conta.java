/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

/**
 *
 * @author felipe
 */
public class Conta {
    int id_conta ;
    String titulo_conta;
    String numero_documento;
    int tipo_conta;
    int id_fornecedor;
    int tipo_categoria;
    String descricao;
    float valor_titulo;
    String data_validade;
    String data_pagamento;
    int parcelas;
    String status;

    public Conta() {
    }

    public Conta(int id_conta, String titulo_conta, String numero_documento, int tipo_conta, int id_fornecedor, int tipo_categoria, String descricao, float valor_titulo, String data_validade, String data_pagamento, int parcelas, String status) {
        this.id_conta = id_conta;
        this.titulo_conta = titulo_conta;
        this.numero_documento = numero_documento;
        this.tipo_conta = tipo_conta;
        this.id_fornecedor = id_fornecedor;
        this.tipo_categoria = tipo_categoria;
        this.descricao = descricao;
        this.valor_titulo = valor_titulo;
        this.data_validade = data_validade;
        this.data_pagamento = data_pagamento;
        this.parcelas = parcelas;
        this.status = status;
    }

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }

    public String getTitulo_conta() {
        return titulo_conta;
    }

    public void setTitulo_conta(String titulo_conta) {
        this.titulo_conta = titulo_conta;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public int getTipo_conta() {
        return tipo_conta;
    }

    public void setTipo_conta(int tipo_conta) {
        this.tipo_conta = tipo_conta;
    }

    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(int id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public int getTipo_categoria() {
        return tipo_categoria;
    }

    public void setTipo_categoria(int tipo_categoria) {
        this.tipo_categoria = tipo_categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor_titulo() {
        return valor_titulo;
    }

    public void setValor_titulo(float valor_titulo) {
        this.valor_titulo = valor_titulo;
    }

    public String getData_validade() {
        return data_validade;
    }

    public void setData_validade(String data_validade) {
        this.data_validade = data_validade;
    }

    public String getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
  
   
    
}
