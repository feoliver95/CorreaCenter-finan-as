
package Dao;

import Classe.Fornecedor;
import Classe.Tipo_Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class FornecedorDao {
    
    
    public ArrayList<String> RetornarFornecedores(Fornecedor fornecedor){
        ArrayList<String> lista_fornecedor = new ArrayList();
        try{
            
            Connection con = Conecta.getConexao();
            String sql = "Select nome_fornecedor from fornecedor order by nome_fornecedor desc";
            
           PreparedStatement ps =  con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               fornecedor.setNome_fornecedor(rs.getString("nome_fornecedor"));
               lista_fornecedor.add(fornecedor.getNome_fornecedor());
              
           }
           ps.close();
           rs.close();
           con.close();
            
        }catch(Exception ex){
          System.out.print(ex.getMessage());
        }
        
       return lista_fornecedor;
    };
    
    public int retorna_id_fornecedor(String nome_fornecedor){
        int id_fornecedor=0;
        try{
            Connection con = Conecta.getConexao();
            String sql = "Select id_fornecedor from fornecedor "
                    + "WHERE nome_fornecedor = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome_fornecedor);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id_fornecedor = rs.getInt("id_fornecedor");
                
            }
             rs.close();
             ps.close();
             con.close();

        }catch(Exception ex){
    System.out.print(ex.getMessage());
            }
           System.out.println("O ID RETORNADO NO METODO RETORNA ID FOI "+id_fornecedor);
           return id_fornecedor;
        }
    
    
              public void InserirFornecedor(Fornecedor fornecedor){
         
         
         try{
             
           Connection con = Conecta.getConexao();
            String sql = "INSERT INTO fornecedor (nome_fornecedor) values (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,fornecedor.getNome_fornecedor());
   
  
            
            ps.execute();
            
            ps.close();
            con.close();
           
           
           // System.out.print("ADICIONADO COM SUCESSO");
             
             JOptionPane.showMessageDialog(null,"Registro Inserido com sucesso! \n fornecedor: "+ fornecedor.getNome_fornecedor());
        }catch(Exception ex){
            System.out.print(ex.getMessage());
            
           
        }
        
         
     }
              
         public int retorna_nome_fornecedor(String nome_fornecedor){
        int id_fornecedor=0;
        try{
            Connection con = Conecta.getConexao();
            String sql = "select id_fornecedor from fornecedor where nome_fornecedor= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome_fornecedor);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id_fornecedor = rs.getInt("id_fornecedor");
                
            }
             rs.close();
             ps.close();
             con.close();

        }catch(Exception ex){
    System.out.print(ex.getMessage());
            }
           System.out.println("O ID RETORNADO NO METODO RETORNA ID FOI ");
           return id_fornecedor;
        };
              
              
              
                  public void DeleteFornecedor(String nome_fornecedor){
      
      try{
          Connection con = Conecta.getConexao();
          String sql = "DELETE from fornecedor WHERE nome_fornecedor =?";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, nome_fornecedor);
          ps.execute();
          
          ps.close();
          con.close();
          
          JOptionPane.showMessageDialog(null, "Registro deletado com sucesso: "+nome_fornecedor);
          
      }catch(Exception ex){
          System.out.println("Erro DeleteVeiculo : " + ex.getMessage());
      }

    
}
                  
                  
     public void UpdateFornecedor (Fornecedor fornecedor){
      
      try{
          Connection con = Conecta.getConexao();
          String sql = "UPDATE fornecedor\n" +
"SET nome_fornecedor=?\n" +
"where id_fornecedor=?";
          
          PreparedStatement ps = con.prepareStatement(sql);
         
          ps.setString(1,fornecedor.getNome_fornecedor());
          ps.setInt(2, fornecedor.getId_fornecedor());
          

          
          ps.execute();
          
          ps.close();
          con.close();
          
         
          JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
      }catch(Exception ex){
          System.out.println("Erro UpdateEstado : " + ex.getMessage());
      }
      
       
}
    
}
