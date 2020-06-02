
package Dao;

import Classe.Categoria;
import Classe.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CategoriaDao {
    
     public ArrayList<String> RetornarCategorias(Categoria categoria){
        ArrayList<String> lista_categoria = new ArrayList();
        try{
            
            Connection con = Conecta.getConexao();
            String sql = "Select nome_categoria from categoria order by nome_categoria asc";
            
           PreparedStatement ps =  con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               categoria.setNome_categoria(rs.getString("nome_categoria"));
               lista_categoria.add(categoria.getNome_categoria());
              
           }
           ps.close();
           rs.close();
           con.close();
            
        }catch(Exception ex){
          System.out.print(ex.getMessage());
        }
        
       return lista_categoria;
    };
    
     public int retorna_id_categoria(String nome_categoria){
        int id_categoria=0;
        try{
            Connection con = Conecta.getConexao();
            String sql = "Select id_categoria from categoria "
                    + "WHERE nome_categoria= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome_categoria);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id_categoria = rs.getInt("id_categoria");
                
            }
             rs.close();
             ps.close();
             con.close();

        }catch(Exception ex){
    System.out.print(ex.getMessage());
            }
           System.out.println("O ID RETORNADO NO METODO RETORNA ID FOI "+id_categoria);
           return id_categoria;
        }
     
       public void InserirCategoria(Categoria categoria){
         
         
         try{
             
           Connection con = Conecta.getConexao();
            String sql = "INSERT INTO categoria (nome_categoria) values (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,categoria.getNome_categoria());
   
  
            
            ps.execute();
            
            ps.close();
            con.close();
           
           
           // System.out.print("ADICIONADO COM SUCESSO");
             
             JOptionPane.showMessageDialog(null,"Registro Inserido com sucesso! \n categoria: "+ categoria.getNome_categoria());
        }catch(Exception ex){
            System.out.print(ex.getMessage());
            
           
        }
        
         
     }
              
         public int retorna_nome_categoria(String nome_categoria){
        int id_categoria=0;
        try{
            Connection con = Conecta.getConexao();
            String sql = "select id_categoria from categoria where nome_categoria= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome_categoria);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id_categoria = rs.getInt("id_categoria");
                
            }
             rs.close();
             ps.close();
             con.close();

        }catch(Exception ex){
    System.out.print(ex.getMessage());
            }
           System.out.println("O ID RETORNADO NO METODO RETORNA ID FOI ");
           return id_categoria;
        };
              
              
              
                  public void DeleteCategoria(String nome_categoria){
      
      try{
          Connection con = Conecta.getConexao();
          String sql = "DELETE from categoria WHERE nome_categoria =?";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, nome_categoria);
          ps.execute();
          
          ps.close();
          con.close();
          
          JOptionPane.showMessageDialog(null, "Registro deletado com sucesso: "+nome_categoria);
          
      }catch(Exception ex){
          System.out.println("Erro DeleteVeiculo : " + ex.getMessage());
      }

    
}
                  
                  
     public void UpdateCategoria (Categoria categoria){
      
      try{
          Connection con = Conecta.getConexao();
          String sql = "UPDATE categoria\n" +
"SET nome_categoria=?\n" +
"wwhere id_categoria=?";
          
          PreparedStatement ps = con.prepareStatement(sql);
         
          ps.setString(1,categoria.getNome_categoria());
          ps.setInt(2, categoria.getId_categoria());
          

          
          ps.execute();
          
          ps.close();
          con.close();
          
         
          JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
      }catch(Exception ex){
          System.out.println("Erro UpdateCategoria: " + ex.getMessage());
      }
      
       
}
    
    
}
