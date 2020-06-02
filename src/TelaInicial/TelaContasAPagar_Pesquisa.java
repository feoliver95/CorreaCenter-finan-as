/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelaInicial;
import Classe.Categoria;
import Classe.Fornecedor;
import Classe.ListaConta;
import Classe.Tipo_Conta;
import Dao.CategoriaDao;
import Dao.ContaDao;
import Dao.FornecedorDao;
import Dao.Tipo_ContaDao;
import TelaInicial.TelaLogin;
import TelaInicial.RegistrarConta;
import java.awt.List;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author felipe
 */
public class TelaContasAPagar_Pesquisa extends javax.swing.JFrame {
 DefaultTableModel modelo;
TableRowSorter<DefaultTableModel> tr ;
ContaDao funcoes = new ContaDao();


 
    /**
     * Creates new form TelaContasAPagar
     */
    public TelaContasAPagar_Pesquisa() {
        initComponents();
        this.setLocationRelativeTo(null);
        CarregaTabela();
         ContaDao dao = new ContaDao();
         Tipo_Conta tipo = new Tipo_Conta();
         Tipo_ContaDao lista = new Tipo_ContaDao();
         
           
        Categoria categoria = new Categoria();
        CategoriaDao lista_categoria = new CategoriaDao();
        
        Fornecedor fornecedor = new Fornecedor();
        FornecedorDao lista_fornecedor = new FornecedorDao();
        
        ArrayList tipos = lista.RetornarTipos(tipo);
          
        ArrayList categorias = lista_categoria.RetornarCategorias(categoria);
        ArrayList fornecedores = lista_fornecedor.RetornarFornecedores(fornecedor);
       
       
        //Adicionando valores no ComboBox
       
        for (int i = 0; i< tipos.size();i++){
           String str = tipos.get(i).toString();
           
            this.j_tipos1.addItem(tipos.get(i));
            
        }
        
          for (int i = 0; i< fornecedores.size();i++){
           String str = fornecedores.get(i).toString();
          
            this.C_fornecedores.addItem(fornecedores.get(i));
            System.out.print(str);
        }
          
             for (int i = 0; i< categorias.size();i++){
           String str = categorias.get(i).toString();
           
            this.C_categorias.addItem(categorias.get(i));
            System.out.print("categoria"+str);
        }
             
             this.j_visaoGeral.setSelected(true);
       //= dao.RetornaTotalPendente();
      
        
     
        
    }
    
     public void CarregaTabelaDetalhada(){
        
        
        
        System.out.println("Chamou o Carrega tabela");
        modelo = (DefaultTableModel) this.table_conta.getModel();
             //Definindo modelo da tabela;
         
            modelo.setNumRows(0);
            DecimalFormat df = new DecimalFormat("#,###.00");
            DecimalFormat df2 = new DecimalFormat("######.00"); //Criando formatação Decimal
            //Definindo o tamanho da Coluna; Se quiser
            
        ContaDao dao = new ContaDao();
        
        
        try{
            
        
        for(ListaConta c : dao.RetornaContaDetalhada()){
          
            //Pegando a data do banco no format yyyy-mm-dd e passando para dd/MM/yyyy para visualizar no relatorio
            SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");          
            java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_inicio());
            Calendar cal = Calendar.getInstance();
            java.util.Date d2 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_inicio());
           
            Calendar cal2 = Calendar.getInstance();

            cal.setTime(d1);
 
            modelo.addRow(new Object[]{
                c.getId_conta(),
                c.getId_item(),
                c.getNumero_documento(),
                c.getTitulo_conta(),
                c.getNome_tipo(),
                c.getNome_fornecedor(),
                c.getNome_categoria(),
                formato.format(cal.getTime()),
                formato.format(cal.getTime()),
                c.getData_pagamento(),
                df2.format(c.getValor_conta()),
                c.getQtd_parcelas(),
                c.getStatus()
            });
            
       AtualizarTotal();
        }
        }catch(Exception ex){
            System.out.println("Carrega tabela: " + ex.getMessage());
        }
            
};
    
 
    
    public void CarregaTabela(){
        
         this.table_conta.getColumnModel().getColumn(0).setMinWidth(0); // Oculta Coluna
           this.table_conta.getColumnModel().getColumn(0).setMaxWidth(0);
            this.table_conta.getColumnModel().getColumn(1).setMinWidth(0); // Oculta Coluna
            this.table_conta.getColumnModel().getColumn(1).setMaxWidth(0); 
        funcoes.AtualizaVencido();
        
        
       
        modelo = (DefaultTableModel) this.table_conta.getModel();
             //Definindo modelo da tabela;
         
            modelo.setNumRows(0);
            DecimalFormat df = new DecimalFormat("#,###.00");
            DecimalFormat df2 = new DecimalFormat("######.00"); //Criando formatação Decimal
            //Definindo o tamanho da Coluna; Se quiser
            
        ContaDao dao = new ContaDao();
        
      
        try{
            
        
        for(ListaConta c : dao.RetornaContaAgrupada2()){

            //Pegando a data do banco no format yyyy-mm-dd e passando para dd/MM/yyyy para visualizar no relatorio
            SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");          
            java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_inicio());
            java.util.Date d2 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_fim());
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.setTime(d1);
            cal2.setTime(d2);
            
//             java.util.Date d3 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_pagamento());
//             Calendar cal3 = Calendar.getInstance();
//             cal3.setTime(d3);
            
            
          System.out.println(c.getData_pagamento());
            modelo.addRow(new Object[]{
                c.getId_conta(),
                c.getId_item(),
                c.getNumero_documento(),
                c.getTitulo_conta(),
                c.getNome_tipo(),
                c.getNome_fornecedor(),
                c.getNome_categoria(),
                formato.format(cal.getTime()),
                formato.format(cal2.getTime()),
                c.getData_pagamento(),
                df2.format(c.getValor_conta()),
                c.getQtd_parcelas(),
                c.getStatus()
            });
            
       AtualizarTotal();
        }
        }catch(Exception ex){
            System.out.println("Carrega tabela erro: " + ex.getMessage());
        }
            
};

     public void CarregaTabelaFiltradaDetalhada(String data1, String data2){
    
        
 
        
        modelo = (DefaultTableModel) this.table_conta.getModel();
             //Definindo modelo da tabela;
            modelo.setNumRows(0);
            DecimalFormat df = new DecimalFormat("#,###.00");
            DecimalFormat df2 = new DecimalFormat("######.00"); //Criando formatação Decimal
            //Definindo o tamanho da Coluna; Se quiser
            
        ContaDao dao = new ContaDao();
       
        
        try{
            
            SimpleDateFormat formato2 =  new SimpleDateFormat("yyyy-MM-dd");          
             java.util.Date da =  new SimpleDateFormat("dd/MM/yyyy").parse(data1); 
             java.util.Date db =  new SimpleDateFormat("dd/MM/yyyy").parse(data2); 
             System.out.println("Data 1" + formato2.format(da));
             System.out.println("Data 2" + formato2.format(db));
        for(ListaConta c : dao.RetornaContaDetalhadaFiltrada(formato2.format(da),formato2.format(db))){
          
            //Pegando a data do banco no format yyyy-mm-dd e passando para dd/MM/yyyy para visualizar no relatorio
             SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");          
            java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_inicio());

            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.setTime(d1);

           
         
            
            
            
          
            modelo.addRow(new Object[]{
                c.getId_conta(),
                c.getId_item(),
                c.getNumero_documento(),
                c.getTitulo_conta(),
                c.getNome_tipo(),
                c.getNome_fornecedor(),
                c.getNome_categoria(),
                formato.format(cal.getTime()),
                formato.format(cal.getTime()),
                c.getData_pagamento(),
                df2.format(c.getValor_conta()),
                c.getQtd_parcelas(),
                c.getStatus()
            });
            
       AtualizarTotal();
        }
        }catch(Exception ex){
            System.out.println("Carrega tabela: " + ex.getMessage());
        }
            
};
     
    public void CarregaTabelaFiltradaMesYear(String mes, String year){
    
                System.out.println(mes); 
                System.out.println(year);
        
        modelo = (DefaultTableModel) this.table_conta.getModel();
             //Definindo modelo da tabela;
            modelo.setNumRows(0);
            DecimalFormat df = new DecimalFormat("#,###.00");
            DecimalFormat df2 = new DecimalFormat("######.00"); //Criando formatação Decimal
            //Definindo o tamanho da Coluna; Se quiser
            
        ContaDao dao = new ContaDao();
       
       
        
        try{

        for(ListaConta c : dao.RetornaContaDetalhadaFiltradaMesYear(mes,year)){
          
            //Pegando a data do banco no format yyyy-mm-dd e passando para dd/MM/yyyy para visualizar no relatorio
             SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");          
            java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_inicio());

            Calendar cal = Calendar.getInstance();
            cal.setTime(d1);


           
             System.out.println("caiu no RetornaContaDetalhadaFiltradaMesYear");
            System.out.println(c.getData_vencimento_inicio());
            
            
          
            modelo.addRow(new Object[]{
                c.getId_conta(),
                c.getId_item(),
                c.getNumero_documento(),
                c.getTitulo_conta(),
                c.getNome_tipo(),
                c.getNome_fornecedor(),
                c.getNome_categoria(),
                formato.format(cal.getTime()),
                formato.format(cal.getTime()),
                c.getData_pagamento(),
                df2.format(c.getValor_conta()),
                c.getQtd_parcelas(),
                c.getStatus()
                    
                    
                    
            });
            
       AtualizarTotal();
        }
        }catch(Exception ex){
            System.out.println("Carrega tabela: " + ex.getMessage());
        }
            
};
     
    public void CarregaTabelaFiltradaMes(String mes){
    
                 
 
        
        modelo = (DefaultTableModel) this.table_conta.getModel();
             //Definindo modelo da tabela;
            modelo.setNumRows(0);
            DecimalFormat df = new DecimalFormat("#,###.00");
            DecimalFormat df2 = new DecimalFormat("######.00"); //Criando formatação Decimal
            //Definindo o tamanho da Coluna; Se quiser
            
        ContaDao dao = new ContaDao();
       
        
        try{

        for(ListaConta c : dao.RetornaContaDetalhadaFiltradaMes(mes)){
          
            //Pegando a data do banco no format yyyy-mm-dd e passando para dd/MM/yyyy para visualizar no relatorio
             SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");          
            java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_inicio());
            
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.setTime(d1);

           
         
            
            
            
          
            modelo.addRow(new Object[]{
                c.getId_conta(),
                c.getId_item(),
                c.getNumero_documento(),
                c.getTitulo_conta(),
                c.getNome_tipo(),
                c.getNome_fornecedor(),
                c.getNome_categoria(),
                formato.format(cal.getTime()),
                formato.format(cal.getTime()),
                c.getData_pagamento(),
                df2.format(c.getValor_conta()),
                c.getQtd_parcelas(),
                c.getStatus()
            });
            
       AtualizarTotal();
        }
        }catch(Exception ex){
            System.out.println("Carrega tabela: " + ex.getMessage());
        }
            
};
     
    public void CarregaTabelaFiltradaYear(String year){
    
                 
 
        
        modelo = (DefaultTableModel) this.table_conta.getModel();
             //Definindo modelo da tabela;
            modelo.setNumRows(0);
            DecimalFormat df = new DecimalFormat("#,###.00");
            DecimalFormat df2 = new DecimalFormat("######.00"); //Criando formatação Decimal
            //Definindo o tamanho da Coluna; Se quiser
            
        ContaDao dao = new ContaDao();
       
        
        try{

        for(ListaConta c : dao.RetornaContaDetalhadaFiltradaYear(year)){
          
            //Pegando a data do banco no format yyyy-mm-dd e passando para dd/MM/yyyy para visualizar no relatorio
             SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");          
            java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_inicio());
   
            Calendar cal = Calendar.getInstance();

            cal.setTime(d1);

         
            
            
            
          
            modelo.addRow(new Object[]{
                c.getId_conta(),
                c.getId_item(),
                c.getNumero_documento(),
                c.getTitulo_conta(),
                c.getNome_tipo(),
                c.getNome_fornecedor(),
                c.getNome_categoria(),
                formato.format(cal.getTime()),
                formato.format(cal.getTime()),
                c.getData_pagamento(),
                df2.format(c.getValor_conta()),
                c.getQtd_parcelas(),
                c.getStatus()
            });
            
       AtualizarTotal();
        }
        }catch(Exception ex){
            System.out.println("Carrega tabela: " + ex.getMessage());
        }
            
};
    
     public void CarregaTabelaFiltrada(String data1, String data2){
    
                 
 
        
        modelo = (DefaultTableModel) this.table_conta.getModel();
             //Definindo modelo da tabela;
            modelo.setNumRows(0);
            DecimalFormat df = new DecimalFormat("#,###.00");
            DecimalFormat df2 = new DecimalFormat("######.00"); //Criando formatação Decimal
            //Definindo o tamanho da Coluna; Se quiser
            
        ContaDao dao = new ContaDao();
       
        
        try{
            
            SimpleDateFormat formato2 =  new SimpleDateFormat("yyyy-MM-dd");          
             java.util.Date da =  new SimpleDateFormat("dd/MM/yyyy").parse(data1); 
             java.util.Date db =  new SimpleDateFormat("dd/MM/yyyy").parse(data2); 
             System.out.println("Data 1" + formato2.format(da));
             System.out.println("Data 2" + formato2.format(db));
        for(ListaConta c : dao.RetornaContaAgrupadaFiltro(formato2.format(da),formato2.format(db))){
          
            //Pegando a data do banco no format yyyy-mm-dd e passando para dd/MM/yyyy para visualizar no relatorio
             SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");          
            java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_inicio());
            java.util.Date d2 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_fim());
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.setTime(d1);
            cal2.setTime(d2);
           
         
            
            
            
          
            modelo.addRow(new Object[]{
                c.getId_conta(),
                c.getId_item(),
                c.getNumero_documento(),
                c.getTitulo_conta(),
                c.getNome_tipo(),
                c.getNome_fornecedor(),
                c.getNome_categoria(),
                formato.format(cal.getTime()),
                formato.format(cal2.getTime()),
                c.getData_pagamento(),
                df2.format(c.getValor_conta()),
                c.getQtd_parcelas(),
                c.getStatus()
            });
            
       AtualizarTotal();
        }
        }catch(Exception ex){
            System.out.println("Carrega tabela: " + ex.getMessage());
        }
            
};
     
     
     public void CarregaTabelaAgrupadaFiltradaMesYear(String mes, String ano){
    
                 
 
        
        modelo = (DefaultTableModel) this.table_conta.getModel();
             //Definindo modelo da tabela;
            modelo.setNumRows(0);
            DecimalFormat df = new DecimalFormat("#,###.00");
            DecimalFormat df2 = new DecimalFormat("######.00"); //Criando formatação Decimal
            //Definindo o tamanho da Coluna; Se quiser
            
        ContaDao dao = new ContaDao();
       
        
        try{
            
            SimpleDateFormat formato2 =  new SimpleDateFormat("yyyy-MM-dd");          


        for(ListaConta c : dao.RetornaContaAgrupadaFiltroMesYear(mes, ano)){
            
            
            System.out.println("filtro mes e ano " + c.getNumero_documento());
          
            //Pegando a data do banco no format yyyy-mm-dd e passando para dd/MM/yyyy para visualizar no relatorio
             SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");          
            java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_inicio());
            java.util.Date d2 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_fim());
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.setTime(d1);
            cal2.setTime(d2);
           
         
            
            
            
          
            modelo.addRow(new Object[]{
                c.getId_conta(),
                c.getId_item(),
                c.getNumero_documento(),
                c.getTitulo_conta(),
                c.getNome_tipo(),
                c.getNome_fornecedor(),
                c.getNome_categoria(),
                formato.format(cal.getTime()),
                formato.format(cal2.getTime()),
                c.getData_pagamento(),
                df2.format(c.getValor_conta()),
                c.getQtd_parcelas(),
                c.getStatus()
            });
            
       AtualizarTotal();
        }
        }catch(Exception ex){
            System.out.println("Carrega tabela: " + ex.getMessage());
        }
            
};
     
     public void CarregaTabelaAgrupadaFiltradaMes(String mes){
    
                 
 
        
        modelo = (DefaultTableModel) this.table_conta.getModel();
             //Definindo modelo da tabela;
            modelo.setNumRows(0);
            DecimalFormat df = new DecimalFormat("#,###.00");
            DecimalFormat df2 = new DecimalFormat("######.00"); //Criando formatação Decimal
            //Definindo o tamanho da Coluna; Se quiser
            
        ContaDao dao = new ContaDao();
       
        
        try{
            
            SimpleDateFormat formato2 =  new SimpleDateFormat("yyyy-MM-dd");          


        for(ListaConta c : dao.RetornaContaAgrupadaFiltroMes(mes)){
            System.out.println(c.getNumero_documento());
          
            //Pegando a data do banco no format yyyy-mm-dd e passando para dd/MM/yyyy para visualizar no relatorio
             SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");          
            java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_inicio());
            java.util.Date d2 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_fim());
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.setTime(d1);
            cal2.setTime(d2);
           
         
            
            
            
          
            modelo.addRow(new Object[]{
                c.getId_conta(),
                c.getId_item(),
                c.getNumero_documento(),
                c.getTitulo_conta(),
                c.getNome_tipo(),
                c.getNome_fornecedor(),
                c.getNome_categoria(),
                formato.format(cal.getTime()),
                formato.format(cal2.getTime()),
                c.getData_pagamento(),
                df2.format(c.getValor_conta()),
                c.getQtd_parcelas(),
                c.getStatus()
            });
            
       AtualizarTotal();
        }
        }catch(Exception ex){
            System.out.println("Carrega tabela: " + ex.getMessage());
        }
            
};
     
     
     public void CarregaTabelaAgrupadaFiltradaYear(String ano){
    
                 
 
        
        modelo = (DefaultTableModel) this.table_conta.getModel();
             //Definindo modelo da tabela;
            modelo.setNumRows(0);
            DecimalFormat df = new DecimalFormat("#,###.00");
            DecimalFormat df2 = new DecimalFormat("######.00"); //Criando formatação Decimal
            //Definindo o tamanho da Coluna; Se quiser
            
        ContaDao dao = new ContaDao();
       
        
        try{
            
            SimpleDateFormat formato2 =  new SimpleDateFormat("yyyy-MM-dd");          


        for(ListaConta c : dao.RetornaContaAgrupadaFiltroAno(ano)){
            
          System.out.println(c.getNumero_documento());
            //Pegando a data do banco no format yyyy-mm-dd e passando para dd/MM/yyyy para visualizar no relatorio
             SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");          
            java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_inicio());
            java.util.Date d2 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_fim());
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.setTime(d1);
            cal2.setTime(d2);
           
         
            
            
            
          
            modelo.addRow(new Object[]{
                c.getId_conta(),
                c.getId_item(),
                c.getNumero_documento(),
                c.getTitulo_conta(),
                c.getNome_tipo(),
                c.getNome_fornecedor(),
                c.getNome_categoria(),
                formato.format(cal.getTime()),
                formato.format(cal2.getTime()),
                c.getData_pagamento(),
                df2.format(c.getValor_conta()),
                c.getQtd_parcelas(),
                c.getStatus()
            });
            
       AtualizarTotal();
        }
        }catch(Exception ex){
            System.out.println("Carrega tabela: " + ex.getMessage());
        }
            
};


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
 
    Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_conta = new javax.swing.JTable();
        p_btn_pesquisa = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        j_ano = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        l_valor_total = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        C_fornecedores = new javax.swing.JComboBox();
        input_data_inicio = new javax.swing.JFormattedTextField();
        input_data_fim = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        t_numero_documento = new javax.swing.JTextField();
        j_visaoGeral = new javax.swing.JRadioButton();
        j_visaoDetalhada = new javax.swing.JRadioButton();
        Pesquisa_Voltar = new javax.swing.JButton();
        bnt_sair = new javax.swing.JButton();
        j_status1 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        C_categorias = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        t_titulo_conta1 = new javax.swing.JTextField();
        j_tipos1 = new javax.swing.JComboBox();
        j_meses1 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_redefinir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel6.setLayout(null);

        table_conta.setAutoCreateRowSorter(true);
        table_conta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id_conta", "id_item", "Numero do Documento", "Titulo da Conta", "Tipo Conta", "Fornecedor", "Categoria", "Data Vencimento Inicio", "Data_Vencimento Fim", "dt_pagamento", "Valor", "Parcelas", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_conta.setToolTipText("Clique 2 vez no item para Exibir Detalhes");
        table_conta.setGridColor(new java.awt.Color(102, 102, 102));
        table_conta.setSelectionBackground(new java.awt.Color(255, 51, 51));
        table_conta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_contaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(table_conta);

        jPanel6.add(jScrollPane5);
        jScrollPane5.setBounds(20, 120, 1090, 290);

        p_btn_pesquisa.setText("Pesquisar");
        p_btn_pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_btn_pesquisaActionPerformed(evt);
            }
        });
        jPanel6.add(p_btn_pesquisa);
        p_btn_pesquisa.setBounds(980, 80, 100, 30);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("FORNECEDOR");
        jPanel6.add(jLabel15);
        jLabel15.setBounds(960, 10, 100, 20);

        j_ano.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "2016", "2017", "2018", "2019", "2020", "2021", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        j_ano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_anoActionPerformed(evt);
            }
        });
        jPanel6.add(j_ano);
        j_ano.setBounds(400, 90, 90, 20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Valor Contas: ");
        jPanel6.add(jLabel1);
        jLabel1.setBounds(20, 400, 120, 40);

        l_valor_total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_valor_total.setText("jLabel2");
        jPanel6.add(l_valor_total);
        l_valor_total.setBounds(140, 400, 150, 40);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("TIPO DE CONTA");
        jPanel6.add(jLabel16);
        jLabel16.setBounds(550, 10, 110, 20);

        C_fornecedores.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS" }));
        jPanel6.add(C_fornecedores);
        C_fornecedores.setBounds(960, 30, 120, 30);

        try {
            input_data_inicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        input_data_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_data_inicioActionPerformed(evt);
            }
        });
        input_data_inicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                input_data_inicioKeyTyped(evt);
            }
        });
        jPanel6.add(input_data_inicio);
        input_data_inicio.setBounds(20, 30, 110, 30);

        try {
            input_data_fim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        input_data_fim.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        input_data_fim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_data_fimActionPerformed(evt);
            }
        });
        input_data_fim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                input_data_fimKeyTyped(evt);
            }
        });
        jPanel6.add(input_data_fim);
        input_data_fim.setBounds(160, 30, 110, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Pesquisar por Periodo");
        jPanel6.add(jLabel2);
        jLabel2.setBounds(100, 10, 140, 14);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Numero Documento");
        jPanel6.add(jLabel4);
        jLabel4.setBounds(280, 10, 120, 20);
        jPanel6.add(t_numero_documento);
        t_numero_documento.setBounds(280, 30, 120, 30);

        buttonGroup1.add(j_visaoGeral);
        j_visaoGeral.setText("Visão Geral");
        j_visaoGeral.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                j_visaoGeralStateChanged(evt);
            }
        });
        j_visaoGeral.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                j_visaoGeralItemStateChanged(evt);
            }
        });
        j_visaoGeral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_visaoGeralActionPerformed(evt);
            }
        });
        jPanel6.add(j_visaoGeral);
        j_visaoGeral.setBounds(20, 70, 100, 23);

        buttonGroup1.add(j_visaoDetalhada);
        j_visaoDetalhada.setText("Visão Detalhada");
        j_visaoDetalhada.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                j_visaoDetalhadaStateChanged(evt);
            }
        });
        j_visaoDetalhada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                j_visaoDetalhadaItemStateChanged(evt);
            }
        });
        j_visaoDetalhada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_visaoDetalhadaActionPerformed(evt);
            }
        });
        jPanel6.add(j_visaoDetalhada);
        j_visaoDetalhada.setBounds(130, 70, 150, 23);

        Pesquisa_Voltar.setText("Voltar");
        Pesquisa_Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pesquisa_VoltarActionPerformed(evt);
            }
        });
        jPanel6.add(Pesquisa_Voltar);
        Pesquisa_Voltar.setBounds(950, 410, 80, 23);

        bnt_sair.setText("SAIR");
        bnt_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_sairActionPerformed(evt);
            }
        });
        jPanel6.add(bnt_sair);
        bnt_sair.setBounds(1040, 410, 57, 23);

        j_status1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "PAGO", "PENDENTE", "VENCIDO", "SEPARADO" }));
        jPanel6.add(j_status1);
        j_status1.setBounds(680, 30, 120, 30);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("STATUS");
        jPanel6.add(jLabel17);
        jLabel17.setBounds(680, 10, 70, 20);

        C_categorias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS" }));
        C_categorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_categoriasActionPerformed(evt);
            }
        });
        jPanel6.add(C_categorias);
        C_categorias.setBounds(820, 30, 120, 30);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("CATEGORIA");
        jPanel6.add(jLabel18);
        jLabel18.setBounds(820, 10, 70, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Titulo da Conta");
        jPanel6.add(jLabel5);
        jLabel5.setBounds(430, 10, 100, 20);
        jPanel6.add(t_titulo_conta1);
        t_titulo_conta1.setBounds(420, 30, 120, 30);

        j_tipos1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS" }));
        jPanel6.add(j_tipos1);
        j_tipos1.setBounds(550, 30, 120, 30);

        j_meses1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        j_meses1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                j_meses1ItemStateChanged(evt);
            }
        });
        j_meses1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j_meses1ActionPerformed(evt);
            }
        });
        jPanel6.add(j_meses1);
        j_meses1.setBounds(290, 90, 100, 20);

        jLabel6.setText("ano");
        jPanel6.add(jLabel6);
        jLabel6.setBounds(400, 70, 50, 14);

        jLabel7.setText("mês");
        jPanel6.add(jLabel7);
        jLabel7.setBounds(290, 70, 50, 14);

        btn_redefinir.setText("Redefinir");
        btn_redefinir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_redefinirActionPerformed(evt);
            }
        });
        jPanel6.add(btn_redefinir);
        btn_redefinir.setBounds(860, 80, 100, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Logo_CorreCenter.jpeg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setMaximumSize(new java.awt.Dimension(1280, 263));
        jLabel3.setMinimumSize(new java.awt.Dimension(820, 263));
        jLabel3.setPreferredSize(new java.awt.Dimension(1220, 263));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bnt_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_sairActionPerformed
        // TODO add your handling code here:
         this.dispose();
            TelaLogin inicio = new TelaLogin();
            inicio.setVisible(true);
        
    }//GEN-LAST:event_bnt_sairActionPerformed

    
    public void FiltraData(){
          ArrayList<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2); //criando lista de filtros
       //atribuindo filtros na lista
        
        
    }
    public void AtualizarTotal(){
        DecimalFormat df = new DecimalFormat("#,###.00");
        BigDecimal total = BigDecimal.ZERO ; // Criando a Variavel total com o tipo BIGDECIMAIL (MELHOR PARA CONTAS)
         for (int i=0; i < this.table_conta.getRowCount();i++){ //PERCORRENDO A JTABLE DE ACORDO COM O TAMANHO)
         String  val =  this.table_conta.getValueAt(i, 10)+""; //PEGANDO O VALOR DA LINHA i DA COLUNA 4
         BigDecimal v1 = new BigDecimal(val.replace(",", ".")); //CRIANDO UMA VARIAVEL BIGDECIMAL PARA UILIZAR LIMPANDO a , pelo .
         total = total.add(v1); //ATRIBUINDO NA VARIAVEL TOTAL;
        }
      
         
         
        this.l_valor_total.setText("R$: " + df.format(total)); //apicando formatação
    }
   private void AplicaDateFiltro2(){
       int aux=2;
  

       String mes = this.j_meses1.getSelectedItem().toString();
       String year = this.j_ano.getSelectedItem().toString();
       
       System.out.println("Ano:" + year + " mes: "+mes);
       
       if (mes == "TODOS"){
            aux += -1;
       }
       
       if (year == "TODOS"){
            aux += -1;
       }
   
       
       if (aux >=1){
           System.out.println("Aplicou filtro data2");
           if (this.j_visaoDetalhada.isSelected()){
               
               //CarregaTabelaFiltradaMesYear(mes, year);
               
            if(year != "TODOS" && mes =="TODOS"){
                System.out.println("caiu no carrega tabelaDetalhada aplicando filtro ano");    
               CarregaTabelaFiltradaYear(year);
               
           }
            
             if(year == "TODOS" && mes !="TODOS"){
                 System.out.println("caiu no carrega tabelaDetalhada aplicando filtro mes");    
               CarregaTabelaFiltradaMes(mes);
               
           }
//             
                if(year != "TODOS" && mes !="TODOS"){
                    System.out.println("caiu no carrega tabelaDetalhada aplicando filtro mes e ano");       
               CarregaTabelaFiltradaMesYear(mes,year);
               
           }
                

            // Criar funcao
           System.out.println("Caiu no filtro detalhado");
           }
           else{
               
                      if(year != "TODOS" && mes =="TODOS"){
                System.out.println("caiu no carrega tabelaAgrupado aplicando filtro ano");    
                          CarregaTabelaAgrupadaFiltradaYear (year);
                          
           }
            
             if(year == "TODOS" && mes !="TODOS"){
                 System.out.println("caiu no carrega tabelaAgrupado  aplicando filtro mes");    
                 CarregaTabelaAgrupadaFiltradaMes(mes);
               
           }
//             
                if(year != "TODOS" && mes !="TODOS"){
                    System.out.println("caiu no carrega tabelaAgrupado aplicando filtro mes e ano");       
                    CarregaTabelaAgrupadaFiltradaMesYear(mes, year);
               
           }
          // Criar funcao
             System.out.println("Não Caiu no filtro detalhado");
           }
           
           
              this.input_data_inicio.setText("");
       this.input_data_fim.setText("");
           
       }
       else{
           System.out.println("Não foi aplicado filtro data2");
          
       }
    }

    private void AplicaDateFiltro(){
       int aux=2;
       String datainicio = this.input_data_inicio.getText().toString();
       String datafim = this.input_data_fim.getText().toString();
       
       if (datainicio.equals("  /  /    ")){
           aux += -1;
       }
       
       if (datafim.equals("  /  /    ")){
           aux += -1;
       }
   
       
       if (aux ==2){
           System.out.println("Aplicou filtro data");
           if (this.j_visaoDetalhada.isSelected()){
            CarregaTabelaFiltradaDetalhada(datainicio,datafim);
           System.out.println("Caiu no filtro detalhado");
           }
           else{
            CarregaTabelaFiltrada(datainicio,datafim);
             System.out.println("Não Caiu no filtro detalhado");
           }
           
           
              this.input_data_inicio.setText("");
       this.input_data_fim.setText("");
           
       }
       else{
           System.out.println("Não foi aplicado filtro data");
          
       }
    }
    
    private void input_data_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_data_inicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_data_inicioActionPerformed

    private void input_data_inicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_input_data_inicioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_input_data_inicioKeyTyped

    private void input_data_fimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_data_fimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_data_fimActionPerformed

    private void input_data_fimKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_input_data_fimKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_input_data_fimKeyTyped

    private void table_contaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_contaMouseClicked
        if(evt.getClickCount() == 2){

            ExibirDetalhes();
        }
    }//GEN-LAST:event_table_contaMouseClicked

    
    public void ExibirDetalhes(){
           int indiceTabela = this.table_conta.getSelectedRow();
            int id_conta = (int) this.table_conta.getValueAt(indiceTabela, 1);

            TelaContaUnica contaunica = new TelaContaUnica(id_conta);
            contaunica.setVisible(true);
            this.dispose();
    }
    private void p_btn_pesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_btn_pesquisaActionPerformed
        // TODO add your handling code here:
        AplicaFiltro();

    }//GEN-LAST:event_p_btn_pesquisaActionPerformed
 public void AplicaFiltro(){
        AplicaDateFiltro();
        AplicaDateFiltro2();

        String tipo_de_conta =this.j_tipos1.getSelectedItem().toString();;
        String status = this.j_status1.getSelectedItem().toString();
        String titulo = this.t_titulo_conta1.getText().toString();
        String fornecedor = this.C_fornecedores.getSelectedItem().toString();
        String categoria = this.C_categorias.getSelectedItem().toString();
        String numero_documento = this.t_numero_documento.getText().toString();
        
//
//       
         tr = new TableRowSorter<DefaultTableModel>(modelo); //Definindo uma tabela filtrada
        this.table_conta.setRowSorter(tr);

        //Para Executar 2 filtros simultaneamente é necessario criar uma lista com os filtros desejados,
        //informando o valor e a coluna que ira filtrar
//
        ArrayList<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(6); //criando lista de filtros
        //atribuindo filtros na lista
        if(status != "TODOS"){ //Só aplica filtro se for diferente de todos
            filters.add(RowFilter.regexFilter(status,12));//atribuindo filtros na lista
        }
        if(tipo_de_conta != "TODOS"){ //Só aplica filtro se for diferente de todos
            filters.add(RowFilter.regexFilter(tipo_de_conta,4));//atribuindo filtros na lista
        }
        
          if(fornecedor != "TODOS"){ //Só aplica filtro se for diferente de todos;;
            filters.add(RowFilter.regexFilter(fornecedor,5));//atribuindo filtros na lista
        }
//          
            if(categoria != "TODOS"){ //Só aplica filtro se for diferente de todos
            filters.add(RowFilter.regexFilter(categoria,6));//atribuindo filtros na lista
        }
        
        if( titulo.length()>0){
            filters.add(RowFilter.regexFilter(titulo, 3));
             System.out.println("Tamanho:titulo: " +titulo.length() + " " + titulo);
     }
//        
//        
          if( numero_documento.length()>0){
            filters.add(RowFilter.regexFilter(numero_documento, 2));
             System.out.println("Tamanho:numero_documento: " +numero_documento.length() + " " + numero_documento);
     }
          
          try{
                      tr.setRowFilter(RowFilter.andFilter(filters)); //aplicando filtro na Jtable

          }catch(Exception ex){
              System.out.println(ex.getMessage());
        }

        AtualizarTotal();
 
 }
    private void j_visaoGeralStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_j_visaoGeralStateChanged
        // TODO add your handling code here:
   
    }//GEN-LAST:event_j_visaoGeralStateChanged

    private void j_visaoDetalhadaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_j_visaoDetalhadaStateChanged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_j_visaoDetalhadaStateChanged

    private void j_visaoDetalhadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_j_visaoDetalhadaItemStateChanged
        // TODO add your handling code here:
         CarregaTabelaDetalhada();
           tr = new TableRowSorter<DefaultTableModel>(modelo);
    }//GEN-LAST:event_j_visaoDetalhadaItemStateChanged

    private void j_visaoGeralItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_j_visaoGeralItemStateChanged
        // TODO add your handling code here:
        CarregaTabela();
          tr = new TableRowSorter<DefaultTableModel>(modelo);
    }//GEN-LAST:event_j_visaoGeralItemStateChanged

    private void Pesquisa_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pesquisa_VoltarActionPerformed
        // TODO add your handling code here:
        TelaContasAPagar inicio = new TelaContasAPagar();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Pesquisa_VoltarActionPerformed

    private void j_visaoDetalhadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_visaoDetalhadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_j_visaoDetalhadaActionPerformed

    private void j_visaoGeralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_visaoGeralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_j_visaoGeralActionPerformed

    private void C_categoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_categoriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C_categoriasActionPerformed

    private void j_anoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_anoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_j_anoActionPerformed

    private void j_meses1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_meses1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_j_meses1ActionPerformed

    private void j_meses1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_j_meses1ItemStateChanged
        if (this.j_meses1.getSelectedItem().toString() != "TODOS"){
             Date date = new Date(System.currentTimeMillis());
       GregorianCalendar dataCal = new GregorianCalendar();
       dataCal.setTime(date);
       System.out.println("data_atual" +date);
       int ano = dataCal.get(Calendar.YEAR);
       System.out.println("ano atual "+ano);
       this.j_ano.setSelectedItem(String.valueOf(ano));
       
       this.input_data_inicio.enable(false);
       this.input_data_fim.enable(false);
            this.input_data_inicio.setText("");
       this.input_data_fim.setText("");
            
        }
        else{
            this.j_ano.setSelectedItem(String.valueOf("TODOS"));
            
            
       this.input_data_inicio.enable(true);
       this.input_data_fim.enable(true);
        }
    }//GEN-LAST:event_j_meses1ItemStateChanged

    private void btn_redefinirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_redefinirActionPerformed
       this.dispose();
            TelaContasAPagar_Pesquisa TelaRegistrar = new TelaContasAPagar_Pesquisa();
            TelaRegistrar.setVisible(true);
    }//GEN-LAST:event_btn_redefinirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaContasAPagar_Pesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaContasAPagar_Pesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaContasAPagar_Pesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaContasAPagar_Pesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaContasAPagar_Pesquisa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox C_categorias;
    private javax.swing.JComboBox C_fornecedores;
    private javax.swing.JButton Pesquisa_Voltar;
    private javax.swing.JButton bnt_sair;
    private javax.swing.JButton btn_redefinir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField input_data_fim;
    private javax.swing.JFormattedTextField input_data_inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JComboBox j_ano;
    private javax.swing.JComboBox j_meses1;
    private javax.swing.JComboBox j_status1;
    private javax.swing.JComboBox j_tipos1;
    private javax.swing.JRadioButton j_visaoDetalhada;
    private javax.swing.JRadioButton j_visaoGeral;
    private javax.swing.JLabel l_valor_total;
    private javax.swing.JButton p_btn_pesquisa;
    private javax.swing.JTextField t_numero_documento;
    private javax.swing.JTextField t_titulo_conta1;
    private javax.swing.JTable table_conta;
    // End of variables declaration//GEN-END:variables
}

