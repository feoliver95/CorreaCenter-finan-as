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
public class Tipo_Conta {
    int id_tipo;
    String nome_tipo;

    public Tipo_Conta() {
    }
    

    public Tipo_Conta(int id_tipo, String nome_tipo) {
        this.id_tipo = id_tipo;
        this.nome_tipo = nome_tipo;
        
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getNome_tipo() {
        return nome_tipo;
    }

    public void setNome_tipo(String nome_tipo) {
        this.nome_tipo = nome_tipo;
    }
    
}
