/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author felipe
 */
public class loginDao {

    public int EfetuarLogin(String email, String senha) {
        int retorno =0;
          
           try{
             Connection con = Conecta.getConexao();
               
               System.out.println("Connection to SQLite has been established.");
               
             String sql = "SELECT * FROM USUARIO WHERE USUARIO =? AND SENHA =?";
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setString(1, email);
             ps.setString(2, senha);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
              retorno = 1;
             }
              con.close();
        
                System.out.println(retorno);
                
        }catch(Exception ex){
                 System.out.println(ex.getMessage());
                }

    return retorno;
        

    }
}
