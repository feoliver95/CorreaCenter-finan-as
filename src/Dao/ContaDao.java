/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Classe.Conta;
import Classe.ListaConta;
import Classe.Tipo_Conta;
import java.sql.Connection;
import Dao.Conecta;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import TelaInicial.TelaContasAPagar_Pesquisa;
import java.text.ParseException;
import java.util.GregorianCalendar;


/**
 *
 * @author felipe
 */
public class ContaDao {
    
    
    public void Inserir(Conta conta) throws ParseException{
        int id=0;
        
       
        int parcelas = conta.getParcelas();
        int parcelas_nova;
        float valor = conta.getValor_titulo();
        float valor_inserir = valor/parcelas;
        String data = conta.getData_validade();
        String dt_pagamento = conta.getData_pagamento();
        int id_item = RetornaID_lastConta()+1;
        
        
//        if (dt_pagamento.length()>0){
//                          java.util.Date dt_pag =  new SimpleDateFormat("dd/MM/yyyy").parse(dt_pagamento);
//         Calendar cal2 = Calendar.getInstance();
//         cal2.setTime(dt_pag);
//        }
//        else{
//            dt_pagamento="";
//        }
      
          
          
        //System.out.print("inserido " + parcelas);
                
        if (parcelas> 1){
            parcelas_nova = 1;
        }
        else{
            parcelas_nova = parcelas;
        }
        
        
           
        
        try{
             for(int i = 0 ; i< parcelas; i++){
            SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");          
            java.util.Date d =  new SimpleDateFormat("dd/MM/yyyy").parse(data);
           
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MONTH, i);
            
 
            System.out.println(formato.format(cal.getTime()));
            System.out.println("id item: "+RetornaID_lastConta());
            
           
            

     
            Connection con = Conecta.getConexao();
            String sql = "INSERT INTO conta(id_item,titulo_conta,tipo_conta,descricao,valor_conta,data_venc,parcelas,status,numero_documento,id_categoria,id_fornecedor,dt_pagamento) "
                    + "Values(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id_item);
            ps.setString(2, conta.getTitulo_conta());
            ps.setInt(3, conta.getTipo_conta());
            ps.setString(4,conta.getDescricao());
            ps.setFloat(5, valor_inserir);
            ps.setString(6,formato.format(cal.getTime()));
            ps.setInt(7, parcelas_nova);
            ps.setString(8,conta.getStatus());
            ps.setString(9,conta.getNumero_documento());
            ps.setInt(10,conta.getTipo_categoria());
            ps.setInt(11,conta.getId_fornecedor());
            ps.setString(12,conta.getData_pagamento());
            ps.execute();
            
            ps.close();
            con.close();
           
           
           // System.out.print("ADICIONADO COM SUCESSO");
             }
             JOptionPane.showMessageDialog(null,"Registro Inserido com sucesso! \n codigo_item: "+ id_item);
        }catch(Exception ex){
            System.out.print("erro inseri "+ex.getMessage());
            
           
        }
        
        }
    
    
      public ArrayList<ListaConta> RetornaContaInicial(){
     ArrayList<ListaConta> lista = new ArrayList<ListaConta>(); 
     
     try{
         Connection con = Conecta.getConexao();
         String sql = "select id_conta,id_item, numero_documento,c.nome_categoria,f.nome_fornecedor,titulo_conta,nome_tipo,data_venc,valor_conta,parcelas,status\n" +
"from Conta\n" +
"INNER JOIN TAB_TIPO_CONTA t on conta.tipo_conta = t.id_tipo\n" +
"INNER JOIN categoria c on conta.id_categoria =c.id_categoria\n" +
"INNER JOIN fornecedor f on conta.id_fornecedor=f.id_fornecedor\n" +
"where strftime('%m-%Y' ,data_venc) like strftime('%m-%Y' ,'now') AND STATUS != \"PAGO\" OR STATUS =\"VENCIDO\"\n" +
"order by data_venc";
         PreparedStatement ps = con.prepareStatement(sql);
      
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             ListaConta conta = new ListaConta();
              conta.setId_conta(rs.getInt("id_conta"));
              conta.setId_item(rs.getInt("id_item"));
              conta.setNumero_documento(rs.getString("numero_documento"));
              conta.setNome_categoria(rs.getString("nome_categoria"));
              conta.setNome_fornecedor(rs.getString("nome_fornecedor"));
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNome_tipo(rs.getString("nome_tipo"));
              conta.setValor_conta(rs.getDouble("valor_conta"));
              conta.setData_vencimento_inicio(rs.getString("data_venc"));      
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              conta.setStatus(rs.getString("status"));
              lista.add(conta);
              
             }
              ps.close();
              rs.close();
              con.close();  
                 
         
     }catch(Exception ex){
         System.out.println("RetornaContaInicial : " +ex.getMessage());
     }
      
      
      return lista;
  }
      
      
      public ArrayList<ListaConta> RetornaContaInicialAVencer(){
     ArrayList<ListaConta> lista = new ArrayList<ListaConta>(); 
     
     try{
         Connection con = Conecta.getConexao();
         String sql = "select id_conta,id_item, numero_documento,c.nome_categoria,f.nome_fornecedor,titulo_conta,nome_tipo,data_venc,valor_conta,parcelas,status\n" +
"from Conta\n" +
"INNER JOIN TAB_TIPO_CONTA t on conta.tipo_conta = t.id_tipo\n" +
"INNER JOIN categoria c on conta.id_categoria =c.id_categoria\n" +
"INNER JOIN fornecedor f on conta.id_fornecedor=f.id_fornecedor\n" +
"where DATE(data_venc) between DATE(\"now\") and DATE(\"now\",\"+7 days\") AND STATUS != \"PAGO\" \n" +
"order by data_venc";
         PreparedStatement ps = con.prepareStatement(sql);
      
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             ListaConta conta = new ListaConta();
              conta.setId_conta(rs.getInt("id_conta"));
              conta.setId_item(rs.getInt("id_item"));
              conta.setNumero_documento(rs.getString("numero_documento"));
              conta.setNome_categoria(rs.getString("nome_categoria"));
              conta.setNome_fornecedor(rs.getString("nome_fornecedor"));
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNome_tipo(rs.getString("nome_tipo"));
              conta.setValor_conta(rs.getDouble("valor_conta"));
              conta.setData_vencimento_inicio(rs.getString("data_venc"));      
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              conta.setStatus(rs.getString("status"));
              lista.add(conta);
              
             }
              ps.close();
              rs.close();
              con.close();  
                 
         
     }catch(Exception ex){
         System.out.println("RetornaContaInicial : " +ex.getMessage());
     }
      
      
      return lista;
  }
    
    
    
    public int RetornaID_lastConta(){
        int teste = 0;
        try{
            
        
        Connection con = Conecta.getConexao();
        String sql = "Select max(id_item) from conta";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            teste = (rs.getInt("max(id_item)"));
             
            ps.close();
            rs.close();
            con.close();
        }
        else{
            teste = 1;
        }
        

        
        }catch(Exception ex){
           System.out.print(ex.getMessage());
           
        }
        
     
        
        return teste;
    }
    
   public void validaCampos(){
       
       
   }
   
   public ArrayList <Tipo_Conta> RetornaDados(){
      ArrayList lista = new ArrayList();
      
      try{
          Connection con = Conecta.getConexao();
          String sql ;
      }catch(Exception ex){
          System.out.printf(ex.getMessage());
      }
      
       
              return lista;
   }
   
   public void AtualizaVencido(){
       
       try{
           Connection con = Conecta.getConexao();
           String sql="  UPDATE CONTA\n" +
" SET STATUS = \"VENCIDO\"\n" +
" WHERE data_venc < strftime('%Y-%m-%d','now') and STATUS != \"PAGO\"";
           
           PreparedStatement ps = con.prepareStatement(sql);
           ps.execute();
           ps.close();
           con.close();
       }catch(Exception ex){
           System.out.println(ex.getMessage());
           
       }
       
   }
   
       
  public ArrayList<ListaConta> RetornaContaAgrupada2(){
     
      ArrayList <ListaConta> lista = new ArrayList<ListaConta>();
      try{
          Connection con = Conecta.getConexao();
          String sql = "select  a.id_item, \n" +
"a.numero_documento,\n" +
"a.titulo_conta,  \n" +
"a.descricao, \n" +
"a.dt_pagamento,\n" +
"t.nome_tipo,\n" +
"f.nome_fornecedor,\n" +
"c.nome_categoria,\n" +
"sum(a.valor_conta)as Valor_Total,\n" +
"min(a.data_venc) as Data_vencimento_inicio,\n" +
"max(a.data_venc)as Data_vencimento_fim, \n" +
"sum(a.parcelas)as Parcelas,	\n" +
"CASE\n" +
"	WHEN\n" +
"	CASE WHEN (SELECT count(status) from Conta as c where STATUS=\"VENCIDO\" AND C.id_item = a.id_item) >0 THEN \"PENDENTE\"\n" +
"	ELSE \"OK\" \n" +
"	END  \n" +
"	=  \n" +
"	CASE (SELECT count(status) from Conta as d where STATUS=\"PAGO\" AND d.id_item = a.id_item) \n" +
"		WHEN SUM(A.PARCELAS) THEN \"PAGO\" \n" +
"	ELSE \"PENDENTE\" \n" +
"    END  \n" +
"	THEN \"VENCIDO\"\n" +
"	ELSE  \n" +
"		CASE (SELECT count(status) from Conta as d where STATUS=\"PAGO\" AND d.id_item = a.id_item) \n" +
"			WHEN SUM(A.PARCELAS) THEN \"PAGO\" \n" +
"		ELSE \"PENDENTE\" \n" +
"		END \n" +
"END STATUS\n" +
"	FROM Conta as a \n" +
"            LEFT JOIN TAB_TIPO_CONTA t on a.tipo_conta = t.id_tipo\n" +
"            LEFT join categoria c on a.id_categoria=c.id_categoria\n" +
"            LEFT join fornecedor f on a.id_fornecedor=f.id_fornecedor \n" +
"          GROUP BY id_item \n" +
"	order by max(a.data_venc) asc";
                  
          
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          while(rs.next()){
              String testedata="";
             ListaConta conta = new ListaConta();
              
             testedata=rs.getString("dt_pagamento");
              conta.setId_item(rs.getInt("id_item"));
             
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNome_tipo(rs.getString("nome_tipo"));
              conta.setNumero_documento(rs.getString("numero_documento"));
              conta.setNome_fornecedor(rs.getString("nome_fornecedor"));
              conta.setNome_categoria(rs.getString("nome_categoria"));
              conta.setDescricao(rs.getString("descricao"));
              conta.setValor_conta(rs.getDouble("Valor_Total"));
              conta.setData_vencimento_inicio(rs.getString("Data_vencimento_inicio"));
              conta.setData_vencimento_fim(rs.getString("Data_vencimento_fim"));
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              conta.setStatus(rs.getString("status"));
              conta.setData_pagamento(rs.getString("dt_pagamento"));
              lista.add(conta);
              //System.out.println(rs.getString("dt_pagamento"));
             
          }
          ps.close();
          rs.close();
          con.close();
          
      }catch(Exception ex){
          System.out.println(ex.getMessage());
      }
      
      
      return lista;
  }
            
     
  public ListaConta CarregaEditaConta(int id_conta){
     
      ListaConta conta = new ListaConta();
   
      try{
          Connection con = Conecta.getConexao();
          String sql = "select \n" +
"    id_conta,\n" +
"    id_item,\n" +
"    titulo_conta,\n" +
"    descricao,\n" +
"    sum(valor_conta) valor_conta,\n" +
"    min(data_venc) data_venc,\n" +
"    sum(parcelas) parcelas,\n" +
"    numero_documento,\n" +
"    f.nome_fornecedor,\n" +
"    c.nome_categoria,\n" +
"    t.nome_tipo,\n" +
"    dt_pagamento,\n" +
"    status\n" +
"\n" +
"from conta\n" +
"     left join TAB_TIPO_CONTA t on t.id_tipo = conta.tipo_conta\n" +
"              LEFT join categoria c on Conta.id_categoria=c.id_categoria\n" +
"              LEFT join fornecedor f on Conta.id_fornecedor=f.id_fornecedor \n" +
"where id_item=?\n" +
"group by id_item";
                
                  
         
          
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setInt(1,id_conta);
            
            
          ResultSet rs = ps.executeQuery();
          while(rs.next()){
            
              conta.setId_conta(rs.getInt("id_conta"));
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNumero_documento(rs.getString("numero_documento"));
              conta.setNome_tipo(rs.getString("nome_tipo"));
              conta.setDescricao(rs.getString("descricao"));
              conta.setValor_conta(rs.getDouble("valor_conta"));
              conta.setNome_categoria(rs.getString("nome_categoria"));
              conta.setNome_fornecedor(rs.getString("nome_fornecedor"));
              conta.setData_vencimento_inicio(rs.getString("data_venc"));
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              conta.setStatus(rs.getString("status"));
          
          }
          ps.close();
          rs.close();
          con.close();
          
      }catch(Exception ex){
          System.out.println(ex.getMessage());
      }
      
      
      return conta;
  }      
    
  public ArrayList<ListaConta> RetornaContaAgrupada(){
     
      ArrayList <ListaConta> lista = new ArrayList<ListaConta>();
      try{
          Connection con = Conecta.getConexao();
          String sql = "select  a.id_item,\n" +
"		a.titulo_conta, \n" +
"		a.descricao,\n" +
"		a.dt_pagamento,\n" +                  
"		t.nome_tipo,\n" +
"		sum(a.valor_conta)as Valor_Total,\n" +
"		min(a.data_venc) as Data_vencimento_inicio,\n" +
"		max(a.data_venc)as Data_vencimento_fim,\n" +
"		sum(a.parcelas)as Parcelas,\n" +
"		(SELECT count(status) from Conta as b where STATUS=\"PENDENTE\" AND b.id_item = a.id_item) as PENDENTE,\n" +
"		(SELECT count(status) from Conta as b where STATUS=\"PAGO\" AND b.id_item = a.id_item) as PAGO,\n" +
"		CASE (SELECT count(status) from Conta as b where STATUS=\"PAGO\" AND b.id_item = a.id_item) \n" +
"			WHEN SUM(A.PARCELAS) THEN \"PAGO\"\n" +
"			ELSE \"PENDENTE\"\n" +
"		END STATUS\n" +
"               from Conta a\n" +
"               INNER JOIN TAB_TIPO_CONTA t on a.tipo_conta = t.id_tipo\n" +
"               group by id_item\n" ;
                
                  
          
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          while(rs.next()){
             ListaConta conta = new ListaConta();
              
              conta.setId_item(rs.getInt("id_item"));
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNome_tipo(rs.getString("nome_tipo"));
              conta.setDescricao(rs.getString("descricao"));
              conta.setValor_conta(rs.getDouble("Valor_Total"));
              conta.setData_vencimento_inicio(rs.getString("Data_vencimento_inicio"));
              conta.setData_vencimento_fim(rs.getString("Data_vencimento_fim"));
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              conta.setStatus(rs.getString("status"));
              lista.add(conta);
          }
          ps.close();
          rs.close();
          con.close();
          
      }catch(Exception ex){
          System.out.println(ex.getMessage());
      }
      
      
      return lista;
  }
  
    public ArrayList<ListaConta> RetornaContaAgrupadaFiltro(String data1, String data2){
     
      ArrayList <ListaConta> lista = new ArrayList<ListaConta>();
      try{
          Connection con = Conecta.getConexao();
          String sql = "select  a.id_item, \n" +
"a.numero_documento,\n" +
"a.titulo_conta,  \n" +
"a.descricao, \n" +
"t.nome_tipo,\n" +
"t.dt_pagamento,\n" +                  
"f.nome_fornecedor,\n" +
"c.nome_categoria,\n" +
"sum(a.valor_conta)as Valor_Total,\n" +
"min(a.data_venc) as Data_vencimento_inicio,\n" +
"max(a.data_venc)as Data_vencimento_fim, \n" +
"sum(a.parcelas)as Parcelas,	\n" +
"CASE\n" +
"	WHEN\n" +
"	CASE WHEN (SELECT count(status) from Conta as c where STATUS=\"VENCIDO\" AND C.id_item = a.id_item) >0 THEN \"PENDENTE\"\n" +
"	ELSE \"OK\" \n" +
"	END  \n" +
"	=  \n" +
"	CASE (SELECT count(status) from Conta as d where STATUS=\"PAGO\" AND d.id_item = a.id_item) \n" +
"		WHEN SUM(A.PARCELAS) THEN \"PAGO\" \n" +
"	ELSE \"PENDENTE\" \n" +
"    END  \n" +
"	THEN \"VENCIDO\"\n" +
"	ELSE  \n" +
"		CASE (SELECT count(status) from Conta as d where STATUS=\"PAGO\" AND d.id_item = a.id_item) \n" +
"			WHEN SUM(A.PARCELAS) THEN \"PAGO\" \n" +
"		ELSE \"PENDENTE\" \n" +
"		END \n" +
"END STATUS\n" +
"	FROM Conta as a \n" +
"            LEFT JOIN TAB_TIPO_CONTA t on a.tipo_conta = t.id_tipo\n" +
"            LEFT join categoria c on a.id_categoria=c.id_categoria\n" +
"            LEFT join fornecedor f on a.id_fornecedor=f.id_fornecedor \n" +
"          GROUP BY id_item\n" +
"          \n" +
"	HAVING  a.data_venc BETWEEN ? AND ?\n" +
"	order by max(a.data_venc) asc\n" +
" ";
                
                  
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, data1);
          ps.setString(2, data2);
          ResultSet rs = ps.executeQuery();
          while(rs.next()){
             ListaConta conta = new ListaConta();
              
              conta.setId_item(rs.getInt("id_item"));
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNome_tipo(rs.getString("nome_tipo"));
              conta.setDescricao(rs.getString("descricao"));
              conta.setValor_conta(rs.getDouble("Valor_Total"));
              conta.setData_vencimento_inicio(rs.getString("Data_vencimento_inicio"));
              conta.setData_vencimento_fim(rs.getString("Data_vencimento_fim"));
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              conta.setData_pagamento(rs.getString("dt_pagamento"));
              conta.setStatus(rs.getString("status"));
              conta.setNumero_documento(rs.getString("numero_documento"));
              conta.setNome_fornecedor(rs.getString("nome_fornecedor"));
              conta.setNome_categoria(rs.getString("nome_categoria"));
              lista.add(conta);
          }
          ps.close();
          rs.close();
          con.close();
          
      }catch(Exception ex){
          System.out.println(ex.getMessage());
      }
      
      
      return lista;
  }
    
    public ArrayList<ListaConta> RetornaContaAgrupadaFiltroMesYear(String mes, String ano){
        
           System.out.println("to na funcao: "+mes + " " + ano);
     
      ArrayList <ListaConta> lista = new ArrayList<ListaConta>();
      try{
          Connection con = Conecta.getConexao();
          String sql = "select  a.id_item, \n" +
"a.id_conta,\n" +
"a.numero_documento,\n" +
"a.titulo_conta,  \n" +
"a.descricao, \n" +
"a.dt_pagamento, \n" +                
"t.nome_tipo,\n" +
"f.nome_fornecedor,\n" +
"c.nome_categoria,\n" +
"sum(a.valor_conta)as Valor_Total,\n" +
"min(a.data_venc) as Data_vencimento_inicio,\n" +
"max(a.data_venc)as Data_vencimento_fim, \n" +
"sum(a.parcelas)as Parcelas,	\n" +
"CASE\n" +
"	WHEN\n" +
"	CASE WHEN (SELECT count(status) from Conta as c where STATUS=\"VENCIDO\" AND C.id_item = a.id_item) >0 THEN \"PENDENTE\"\n" +
"	ELSE \"OK\" \n" +
"	END  \n" +
"	=  \n" +
"	CASE (SELECT count(status) from Conta as d where STATUS=\"PAGO\" AND d.id_item = a.id_item) \n" +
"		WHEN SUM(A.PARCELAS) THEN \"PAGO\" \n" +
"	ELSE \"PENDENTE\" \n" +
"    END  \n" +
"	THEN \"VENCIDO\"\n" +
"	ELSE  \n" +
"		CASE (SELECT count(status) from Conta as d where STATUS=\"PAGO\" AND d.id_item = a.id_item) \n" +
"			WHEN SUM(A.PARCELAS) THEN \"PAGO\" \n" +
"		ELSE \"PENDENTE\" \n" +
"		END \n" +
"END STATUS\n" +
"	FROM Conta as a \n" +
"            INNER JOIN TAB_TIPO_CONTA t on a.tipo_conta = t.id_tipo\n" +
"            INNER join categoria c on a.id_categoria=c.id_categoria\n" +
"            INNER join fornecedor f on a.id_fornecedor=f.id_fornecedor\n" +
"             where strftime('%Y',data_venc) == ? and strftime('%m',data_venc) ==? \n" +
"          GROUP BY id_item \n" +
"         \n" +
"	order by max(a.data_venc) asc\n" +
" ";
                
                  
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, ano);
          ps.setString(2, mes);
          ResultSet rs = ps.executeQuery();
          while(rs.next()){
             ListaConta conta = new ListaConta();
              
              conta.setId_item(rs.getInt("id_item"));
              conta.setId_conta(rs.getInt("id_conta"));
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNome_tipo(rs.getString("nome_tipo"));
              conta.setDescricao(rs.getString("descricao"));
              conta.setValor_conta(rs.getDouble("Valor_Total"));
              conta.setData_vencimento_inicio(rs.getString("Data_vencimento_inicio"));
              conta.setData_vencimento_fim(rs.getString("Data_vencimento_fim"));
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              conta.setStatus(rs.getString("status"));
                conta.setData_pagamento(rs.getString("dt_pagamento"));
              conta.setNumero_documento(rs.getString("numero_documento"));
              conta.setNome_fornecedor(rs.getString("nome_fornecedor"));
              conta.setNome_categoria(rs.getString("nome_categoria"));
              lista.add(conta);
          }
          ps.close();
          rs.close();
          con.close();
          
      }catch(Exception ex){
          System.out.println(ex.getMessage());
      }
      
      
      return lista;
  }
    
    
    public ArrayList<ListaConta> RetornaContaAgrupadaFiltroMes(String mes){
     
      ArrayList <ListaConta> lista = new ArrayList<ListaConta>();
      try{
          Connection con = Conecta.getConexao();
          String sql = "select  a.id_item, \n" +
"a.id_conta,\n" +
"a.numero_documento,\n" +
"a.titulo_conta,  \n" +
"a.dt_pagamento,  \n" +                  
"a.descricao, \n" +
"t.nome_tipo,\n" +
"f.nome_fornecedor,\n" +
"c.nome_categoria,\n" +
"sum(a.valor_conta)as Valor_Total,\n" +
"min(a.data_venc) as Data_vencimento_inicio,\n" +
"max(a.data_venc)as Data_vencimento_fim, \n" +
"sum(a.parcelas)as Parcelas,	\n" +
"CASE\n" +
"	WHEN\n" +
"	CASE WHEN (SELECT count(status) from Conta as c where STATUS=\"VENCIDO\" AND C.id_item = a.id_item) >0 THEN \"PENDENTE\"\n" +
"	ELSE \"OK\" \n" +
"	END  \n" +
"	=  \n" +
"	CASE (SELECT count(status) from Conta as d where STATUS=\"PAGO\" AND d.id_item = a.id_item) \n" +
"		WHEN SUM(A.PARCELAS) THEN \"PAGO\" \n" +
"	ELSE \"PENDENTE\" \n" +
"    END  \n" +
"	THEN \"VENCIDO\"\n" +
"	ELSE  \n" +
"		CASE (SELECT count(status) from Conta as d where STATUS=\"PAGO\" AND d.id_item = a.id_item) \n" +
"			WHEN SUM(A.PARCELAS) THEN \"PAGO\" \n" +
"		ELSE \"PENDENTE\" \n" +
"		END \n" +
"END STATUS\n" +
"	FROM Conta as a \n" +
"            INNER JOIN TAB_TIPO_CONTA t on a.tipo_conta = t.id_tipo\n" +
"            INNER join categoria c on a.id_categoria=c.id_categoria\n" +
"            INNER join fornecedor f on a.id_fornecedor=f.id_fornecedor\n" +
"             where  strftime('%m',data_venc) ==?\n" +
"          GROUP BY id_item \n" +
"         \n" +
"	order by max(a.data_venc) asc\n" +
" ";
                
                  
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, mes);
   
          ResultSet rs = ps.executeQuery();
          while(rs.next()){
             ListaConta conta = new ListaConta();
              
              conta.setId_item(rs.getInt("id_item"));
              conta.setId_conta(rs.getInt("id_conta"));
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNome_tipo(rs.getString("nome_tipo"));
              conta.setDescricao(rs.getString("descricao"));
              conta.setValor_conta(rs.getDouble("Valor_Total"));
              conta.setData_vencimento_inicio(rs.getString("Data_vencimento_inicio"));
              conta.setData_vencimento_fim(rs.getString("Data_vencimento_fim"));
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              conta.setStatus(rs.getString("status"));
                conta.setData_pagamento(rs.getString("dt_pagamento"));
              conta.setNumero_documento(rs.getString("numero_documento"));
              conta.setNome_fornecedor(rs.getString("nome_fornecedor"));
              conta.setNome_categoria(rs.getString("nome_categoria"));
              lista.add(conta);
          }
          ps.close();
          rs.close();
          con.close();
          
      }catch(Exception ex){
          System.out.println(ex.getMessage());
      }
      
      
      return lista;
  }
    
    
    public ArrayList<ListaConta> RetornaContaAgrupadaFiltroAno(String ano){
     
      ArrayList <ListaConta> lista = new ArrayList<ListaConta>();
      try{
          Connection con = Conecta.getConexao();
          String sql = "select  a.id_item, \n" +
"a.id_conta,\n" +
"a.numero_documento,\n" +
"a.titulo_conta,  \n" +
"a.descricao, \n" +
"t.nome_tipo,\n" +
"f.nome_fornecedor,\n" +
"c.nome_categoria,\n" +
"sum(a.valor_conta)as Valor_Total,\n" +
"min(a.data_venc) as Data_vencimento_inicio,\n" +
"max(a.data_venc)as Data_vencimento_fim, \n" +
"sum(a.parcelas)as Parcelas,	\n" +
"CASE\n" +
"	WHEN\n" +
"	CASE WHEN (SELECT count(status) from Conta as c where STATUS=\"VENCIDO\" AND C.id_item = a.id_item) >0 THEN \"PENDENTE\"\n" +
"	ELSE \"OK\" \n" +
"	END  \n" +
"	=  \n" +
"	CASE (SELECT count(status) from Conta as d where STATUS=\"PAGO\" AND d.id_item = a.id_item) \n" +
"		WHEN SUM(A.PARCELAS) THEN \"PAGO\" \n" +
"	ELSE \"PENDENTE\" \n" +
"    END  \n" +
"	THEN \"VENCIDO\"\n" +
"	ELSE  \n" +
"		CASE (SELECT count(status) from Conta as d where STATUS=\"PAGO\" AND d.id_item = a.id_item) \n" +
"			WHEN SUM(A.PARCELAS) THEN \"PAGO\" \n" +
"		ELSE \"PENDENTE\" \n" +
"		END \n" +
"END STATUS\n" +
"	FROM Conta as a \n" +
"            INNER JOIN TAB_TIPO_CONTA t on a.tipo_conta = t.id_tipo\n" +
"            INNER join categoria c on a.id_categoria=c.id_categoria\n" +
"            INNER join fornecedor f on a.id_fornecedor=f.id_fornecedor\n" +
"             where  strftime('%Y',data_venc) ==? \n" +
"          GROUP BY id_item \n" +
"         \n" +
"	order by max(a.data_venc) asc\n" +
" ";
                
                  
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, ano);
   
          ResultSet rs = ps.executeQuery();
          while(rs.next()){
             ListaConta conta = new ListaConta();
              
              conta.setId_item(rs.getInt("id_item"));
              conta.setId_conta(rs.getInt("id_conta"));
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNome_tipo(rs.getString("nome_tipo"));
              conta.setDescricao(rs.getString("descricao"));
              conta.setValor_conta(rs.getDouble("Valor_Total"));
              conta.setData_vencimento_inicio(rs.getString("Data_vencimento_inicio"));
              conta.setData_vencimento_fim(rs.getString("Data_vencimento_fim"));
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              conta.setStatus(rs.getString("status"));
                conta.setData_pagamento(rs.getString("dt_pagamento"));
              conta.setNumero_documento(rs.getString("numero_documento"));
              conta.setNome_fornecedor(rs.getString("nome_fornecedor"));
              conta.setNome_categoria(rs.getString("nome_categoria"));
              lista.add(conta);
          }
          ps.close();
          rs.close();
          con.close();
          
      }catch(Exception ex){
          System.out.println(ex.getMessage());
      }
      
      
      return lista;
  }
    
    
    

  
   public ArrayList<ListaConta> RetornaContaDetalhada(){
     ArrayList<ListaConta> lista = new ArrayList<ListaConta>(); 
     
     try{
         Connection con = Conecta.getConexao();
         String sql = "select id_item,\n" +
"		titulo_conta,\n" +
"		numero_documento,\n" +
"		dt_pagamento,\n" +                
"		t.nome_tipo as tipo_conta,\n" +
"                          c.nome_categoria,\n" +
"                          f.nome_fornecedor,\n" +
"		descricao,\n" +
"                           dt_pagamento,\n" +
"		valor_conta,\n" +
"		data_venc,\n" +
"		parcelas,\n" +
"		valor_conta,\n" +
"		status\n" +
"              from Conta \n" +
"              inner join TAB_TIPO_CONTA t on t.id_tipo = conta.tipo_conta\n" +
"              LEFT join categoria c on Conta.id_categoria=c.id_categoria\n" +
"              LEFT join fornecedor f on Conta.id_fornecedor=f.id_fornecedor \n" +
"            \n" +
"              order by id_item";
         
         PreparedStatement ps = con.prepareStatement(sql);

         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             ListaConta conta = new ListaConta();
              conta.setId_item(rs.getInt("id_item"));
              
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNome_tipo(rs.getString("tipo_conta"));
              conta.setNumero_documento(rs.getString("numero_documento"));
              conta.setNome_fornecedor(rs.getString("nome_fornecedor"));
              conta.setNome_categoria(rs.getString("nome_categoria"));
              conta.setDescricao(rs.getString("descricao"));
              conta.setValor_conta(rs.getDouble("valor_conta"));
              conta.setData_pagamento(rs.getString("dt_pagamento"));
              conta.setData_vencimento_inicio(rs.getString("data_venc"));      
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              conta.setStatus(rs.getString("status"));
              lista.add(conta);
              
             }
              ps.close();
              rs.close();
              con.close();  
                 
         
     }catch(Exception ex){
         System.out.println(ex.getMessage());
     }
      
      
      return lista;
  }
   
   
     public ArrayList<ListaConta> RetornaContaDetalhadaFiltradaMesYear(String mes, String ano){
       
     ArrayList<ListaConta> lista = new ArrayList<ListaConta>(); 
     
  
     try{
         Connection con = Conecta.getConexao();
          String sql = "select id_item, \n" +
"    id_conta,   \n" +
"		titulo_conta,\n" +
"		numero_documento,\n" +
"		dt_pagamento,\n" +                
"		t.nome_tipo as tipo_conta,\n" +
"                          c.nome_categoria,\n" +
"                          f.nome_fornecedor,\n" +
"		descricao,\n" +
"		valor_conta,\n" +
"		data_venc,\n" +
"		parcelas,\n" +
"		status\n" +
"              from Conta \n" +
"              inner join TAB_TIPO_CONTA t on t.id_tipo = conta.tipo_conta\n" +
"              LEFT join categoria c on Conta.id_categoria=c.id_categoria\n" +
"              LEFT join fornecedor f on Conta.id_fornecedor=f.id_fornecedor \n" +
"              WHERE strftime('%m',data_venc) =? and strftime('%Y',data_venc) =?\n" +
"              order by id_item;\n" +
"\n" +
"";
         
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, mes);
           ps.setString(2, ano);

         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             ListaConta conta = new ListaConta();
                conta.setId_item(rs.getInt("id_item"));
                conta.setId_conta(rs.getInt("id_conta"));
              
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNome_tipo(rs.getString("tipo_conta"));
              conta.setNumero_documento(rs.getString("numero_documento"));
              conta.setNome_fornecedor(rs.getString("nome_fornecedor"));
              conta.setNome_categoria(rs.getString("nome_categoria"));
              conta.setDescricao(rs.getString("descricao"));
              conta.setValor_conta(rs.getDouble("valor_conta"));
               conta.setData_pagamento(rs.getString("dt_pagamento"));
              conta.setData_vencimento_inicio(rs.getString("data_venc"));      
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              conta.setStatus(rs.getString("status"));
              lista.add(conta);
             
              
             }
              ps.close();
              rs.close();
              con.close();  
                 
         
     }catch(Exception ex){
         System.out.println(ex.getMessage());
     }
      
      
      return lista;
  }
     
  
     
       public ArrayList<ListaConta> RetornaContaDetalhadaFiltradaMes(String mes){
   
     ArrayList<ListaConta> lista = new ArrayList<ListaConta>(); 
     
  
     try{
         Connection con = Conecta.getConexao();
          String sql = "select id_item, \n" +
"    id_conta,   \n" +
"		titulo_conta,\n" +
"		numero_documento,\n" +
"		dt_pagamento,\n" +                  
"		t.nome_tipo as tipo_conta,\n" +
"                          c.nome_categoria,\n" +
"                          f.nome_fornecedor,\n" +
"		descricao,\n" +
"		valor_conta,\n" +
"		data_venc,\n" +
"		parcelas,\n" +
"		status\n" +
"              from Conta \n" +
"              inner join TAB_TIPO_CONTA t on t.id_tipo = conta.tipo_conta\n" +
"              LEFT join categoria c on Conta.id_categoria=c.id_categoria\n" +
"              LEFT join fornecedor f on Conta.id_fornecedor=f.id_fornecedor \n" +
"              WHERE strftime('%m',data_venc) =?\n" +
"              order by id_item;\n" +
"\n" +
"";
         
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, mes);


         ResultSet rs = ps.executeQuery();
         while(rs.next()){
            ListaConta conta = new ListaConta();
                conta.setId_item(rs.getInt("id_item"));
                conta.setId_conta(rs.getInt("id_conta"));
              
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNome_tipo(rs.getString("tipo_conta"));
              conta.setNumero_documento(rs.getString("numero_documento"));
              conta.setNome_fornecedor(rs.getString("nome_fornecedor"));
              conta.setNome_categoria(rs.getString("nome_categoria"));
              conta.setDescricao(rs.getString("descricao"));
              conta.setValor_conta(rs.getDouble("valor_conta"));
               conta.setData_pagamento(rs.getString("dt_pagamento"));
              conta.setData_vencimento_inicio(rs.getString("data_venc"));      
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              conta.setStatus(rs.getString("status"));
              lista.add(conta);
              
             }
              ps.close();
              rs.close();
              con.close();  
                 
         
     }catch(Exception ex){
         System.out.println(ex.getMessage());
     }
      
      
      return lista;
  }
       
       public ArrayList<ListaConta> RetornaContaDetalhadaFiltradaYear(String year){
    
     ArrayList<ListaConta> lista = new ArrayList<ListaConta>(); 
     
  
     try{
         Connection con = Conecta.getConexao();
          String sql = "select id_item, \n" +
"    id_conta,   \n" +
"		titulo_conta,\n" +
"		numero_documento,\n" +
"		dt_pagamento,\n" +                 
"		t.nome_tipo as tipo_conta,\n" +
"                          c.nome_categoria,\n" +
"                          f.nome_fornecedor,\n" +
"		descricao,\n" +
"		valor_conta,\n" +
"		data_venc,\n" +
"		parcelas,\n" +
"		status\n" +
"              from Conta \n" +
"              inner join TAB_TIPO_CONTA t on t.id_tipo = conta.tipo_conta\n" +
"              LEFT join categoria c on Conta.id_categoria=c.id_categoria\n" +
"              LEFT join fornecedor f on Conta.id_fornecedor=f.id_fornecedor \n" +
"              WHERE  strftime('%Y',data_venc) =?\n" +
"              order by id_item;\n" +
"\n" +
"";
         
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, year);


         ResultSet rs = ps.executeQuery();
         while(rs.next()){
          ListaConta conta = new ListaConta();
                conta.setId_item(rs.getInt("id_item"));
                conta.setId_conta(rs.getInt("id_conta"));
              
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNome_tipo(rs.getString("tipo_conta"));
              conta.setNumero_documento(rs.getString("numero_documento"));
              conta.setNome_fornecedor(rs.getString("nome_fornecedor"));
              conta.setNome_categoria(rs.getString("nome_categoria"));
              conta.setDescricao(rs.getString("descricao"));
              conta.setValor_conta(rs.getDouble("valor_conta"));
               conta.setData_pagamento(rs.getString("dt_pagamento"));
              conta.setData_vencimento_inicio(rs.getString("data_venc"));      
              conta.setQtd_parcelas(rs.getInt("parcelas"));
                conta.setData_pagamento(rs.getString("dt_pagamento"));
              conta.setStatus(rs.getString("status"));
              lista.add(conta);
             }
              ps.close();
              rs.close();
              con.close();  
                 
         
     }catch(Exception ex){
         System.out.println(ex.getMessage());
     }
      
      
      return lista;
  }
   
   
      public ArrayList<ListaConta> RetornaContaDetalhadaFiltrada(String data1, String data2){
     ArrayList<ListaConta> lista = new ArrayList<ListaConta>(); 
     
     try{
         Connection con = Conecta.getConexao();
         String sql = "select id_item,\n" +
"		titulo_conta,\n" +
"		numero_documento,\n" +
"		dt_pagamento,\n" +                
"		t.nome_tipo as tipo_conta,\n" +
"                          c.nome_categoria,\n" +
"                          f.nome_fornecedor,\n" +
"		descricao,\n" +
"		valor_conta,\n" +
"		data_venc,\n" +
"		parcelas,\n" +
"		valor_conta,\n" +
"		status\n" +
"              from Conta \n" +
"              inner join TAB_TIPO_CONTA t on t.id_tipo = conta.tipo_conta\n" +
"              LEFT join categoria c on Conta.id_categoria=c.id_categoria\n" +
"              LEFT join fornecedor f on Conta.id_fornecedor=f.id_fornecedor \n" +
"              WHERE data_venc BETWEEN ? AND ?\n" +
"              order by id_item";
         
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, data1);
           ps.setString(2, data2);

         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             ListaConta conta = new ListaConta();
              conta.setId_item(rs.getInt("id_item"));
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNome_tipo(rs.getString("tipo_conta"));
              conta.setDescricao(rs.getString("descricao"));
              conta.setValor_conta(rs.getDouble("valor_conta"));
               conta.setData_pagamento(rs.getString("dt_pagamento"));
              conta.setData_vencimento_inicio(rs.getString("data_venc"));      
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              
              conta.setStatus(rs.getString("status"));
              lista.add(conta);
              
             }
              ps.close();
              rs.close();
              con.close();  
                 
         
     }catch(Exception ex){
         System.out.println(ex.getMessage());
     }
      
      
      return lista;
  }
  
  
  public ArrayList<ListaConta> RetornaConta(int id){
     ArrayList<ListaConta> lista = new ArrayList<ListaConta>(); 
     
     try{
         Connection con = Conecta.getConexao();
         String sql = "select 	id_conta,\n" +
"		id_item,\n" +
"		titulo_conta,\n" +
"		dt_pagamento,\n" +
                 
"                          numero_documento,\n" +
"                          f.nome_fornecedor,\n" +
"                          c.nome_categoria,\n" +
"		t.nome_tipo as tipo_conta,\n" +
"		descricao,\n" +
"		valor_conta,\n" +
"	data_venc,\n" +
"	parcelas,\n" +
"	valor_conta,\n" +
"	status\n" +
"            from Conta\n" +
"            inner join TAB_TIPO_CONTA t on t.id_tipo = conta.tipo_conta\n" +
"           left join fornecedor f on conta.id_fornecedor = f.id_fornecedor\n" +
"           left join categoria c on conta.id_categoria = c.id_categoria\n" +
"            where id_item = ?\n" +
"            order by data_venc asc";
         
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setInt(1, id);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             ListaConta conta = new ListaConta();
              conta.setId_conta(rs.getInt("id_conta"));
              conta.setId_item(rs.getInt("id_item"));
              conta.setTitulo_conta(rs.getString("titulo_conta"));
              conta.setNumero_documento(rs.getString("numero_documento"));
              conta.setNome_categoria(rs.getString("nome_categoria"));
              conta.setNome_fornecedor(rs.getString("nome_fornecedor"));
              conta.setNome_tipo(rs.getString("tipo_conta"));
              conta.setDescricao(rs.getString("descricao"));
              conta.setValor_conta(rs.getDouble("valor_conta"));
              conta.setData_vencimento_inicio(rs.getString("data_venc"));      
              conta.setQtd_parcelas(rs.getInt("parcelas"));
              conta.setStatus(rs.getString("status"));
              lista.add(conta);
              
             }
              ps.close();
              rs.close();
              con.close();  
                 
         
     }catch(Exception ex){
         System.out.println(ex.getMessage());
     }
      
      
      return lista;
  }
  
  public double RetornaTotalPendente(){
      double total=0;
      try{
          Connection con = Conecta.getConexao();
          String sql = "select sum(valor_conta) as Valor_total\n" +
                       "from conta";
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
              total = rs.getDouble("Valor_total");
          }
          
          
      }catch(Exception ex){
          System.out.printf(ex.getMessage());
          
      }
      
      return total;
      
  }
    
  public void UpdatePago(int id_conta){
      
      java.util.Date date = new java.util.Date(System.currentTimeMillis());
       GregorianCalendar dataCal = new GregorianCalendar();
       dataCal.setTime(date);
       
       String ano = String.valueOf(dataCal.get(Calendar.YEAR));
       String mes = String.valueOf(dataCal.get(Calendar.MONTH)+1);
       String dia = String.valueOf(dataCal.get(Calendar.DAY_OF_MONTH));
      
       String data_atual = dia+"/"+mes+"/"+ano;
      try{
          Connection con = Conecta.getConexao();
          String sql = "UPDATE Conta\n" +
"                       SET STATUS = \"PAGO\", dt_pagamento=?\n" +
"                       WHERE ID_CONTA=?";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, data_atual);
          ps.setInt(2, id_conta);
          ps.execute();
          
          ps.close();
          con.close();
          
         
          
      }catch(Exception ex){
          System.out.println("Erro UPDATEPAGO : " + ex.getMessage());
      }
      
      
      
  }
  
  public void UpdatePendente(int id_conta){
      
      try{
          Connection con = Conecta.getConexao();
          String sql = "UPDATE Conta\n" +
"                       SET STATUS = \"PENDENTE\", dt_pagamento=\"\"\n" +
"                       WHERE ID_CONTA=?";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, id_conta);
          ps.execute();
          
          ps.close();
          con.close();
          
         
          
      }catch(Exception ex){
          System.out.println("Erro UPDATEPAGO : " + ex.getMessage());
      }
      
      
      
  }
  
   public void UpdateSeparado(int id_conta){
      
      try{
          Connection con = Conecta.getConexao();
          String sql = "UPDATE Conta\n" +
"                       SET STATUS = \"SEPARADO\", dt_pagamento=\"\"\n" +
"                       WHERE ID_CONTA=?";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, id_conta);
          ps.execute();
          
          ps.close();
          con.close();
          
         
          
      }catch(Exception ex){
          System.out.println("Erro UPDATEPAGO : " + ex.getMessage());
      }
      
      
      
  }
  
  
    public void DeleteConta(int id_conta){
      
      try{
          Connection con = Conecta.getConexao();
          String sql = "delete from Conta where id_item=?";
          
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, id_conta);
          ps.execute();
          
          ps.close();
          con.close();
          
         
          
      }catch(Exception ex){
          System.out.println("Erro DeleteConta : " + ex.getMessage());
      }
      
      
      
  }
  
  
}
