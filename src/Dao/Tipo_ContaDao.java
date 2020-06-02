/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Classe.Tipo_Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Dao.Conecta;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author treinamento
 */
public class Tipo_ContaDao {
    
    public ArrayList<String> RetornarTipos(Tipo_Conta tipo){
        ArrayList<String> lista_tipo = new ArrayList();
        try{
            
            Connection con = Conecta.getConexao();
            String sql = "Select nome_tipo from TAB_TIPO_CONTA";
            
           PreparedStatement ps =  con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               tipo.setNome_tipo(rs.getString("nome_tipo"));
               lista_tipo.add(tipo.getNome_tipo());
              
           }
           ps.close();
           rs.close();
           con.close();
            
        }catch(Exception ex){
          System.out.print(ex.getMessage());
        }
        
       return lista_tipo;
    };

   
    public int retorna_id_tipo(String nome_tipos){
        int id_tipo=0;
        try{
            Connection con = Conecta.getConexao();
            String sql = "Select id_tipo from TAB_TIPO_CONTA "
                    + "WHERE nome_tipo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome_tipos);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id_tipo = rs.getInt("id_tipo");
                
            }
             rs.close();
             ps.close();
             con.close();

        }catch(Exception ex){
    System.out.print(ex.getMessage());
            }
           System.out.println("O ID RETORNADO NO METODO RETORNA ID FOI "+id_tipo);
           return id_tipo;
        }

    
    
    
    
}

