/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelaInicial;
import Classe.ListaConta;
import Classe.Tipo_Conta;
import Dao.ContaDao;
import Dao.Tipo_ContaDao;
import TelaInicial.TelaLogin;
import TelaInicial.RegistrarConta;
import java.awt.List;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author felipe
 */
public class TelaContasAPagar extends javax.swing.JFrame {
 DefaultTableModel modelo;
ContaDao funcoes = new ContaDao();
 
    /**
     * Creates new form TelaContasAPagar
     */
    public TelaContasAPagar() {
        initComponents();
        this.setLocationRelativeTo(null);
        
         ContaDao dao = new ContaDao();
         Tipo_Conta tipo = new Tipo_Conta();
         Tipo_ContaDao lista = new Tipo_ContaDao();
        funcoes.AtualizaVencido();
        CarregaTabela();
        ArrayList tipos = lista.RetornarTipos(tipo);
       
        
        //Adicionando valores no ComboBox
  
       //= dao.RetornaTotalPendente();
       
        
     
        
    }
     public void CarregaTabelaDetalhada(){
   
        
            this.table_conta_inicial.getColumnModel().getColumn(0).setMinWidth(0); // Oculta Coluna
           this.table_conta_inicial.getColumnModel().getColumn(0).setMaxWidth(0);
            this.table_conta_inicial.getColumnModel().getColumn(1).setMinWidth(0); // Oculta Coluna
            this.table_conta_inicial.getColumnModel().getColumn(1).setMaxWidth(0); // Oculta Coluna
        
        System.out.println("Chamou o Carrega tabela");
        modelo = (DefaultTableModel) this.table_conta_inicial.getModel();
             //Definindo modelo da tabela;
          modelo.setNumRows(0);   
           
            
            DecimalFormat df = new DecimalFormat("#,###.00");
            DecimalFormat df2 = new DecimalFormat("######.00"); //Criando formatação Decimal
            //Definindo o tamanho da Coluna; Se quiser
            
        
        
        
        try{
            
        ContaDao dao = new ContaDao();
        for(ListaConta c : dao.RetornaContaDetalhada()){
          
            //Pegando a data do banco no format yyyy-mm-dd e passando para dd/MM/yyyy para visualizar no relatorio
            SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");          
            java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd").parse(c.getData_vencimento_inicio());
            Calendar cal = Calendar.getInstance();
            cal.setTime(d1);
           
          
            modelo.addRow(new Object[]{
                c.getId_conta(),
                c.getId_item(),
                c.getTitulo_conta(),
                c.getNome_tipo(),
                formato.format(cal.getTime()),
                df2.format(c.getValor_conta()),
                c.getQtd_parcelas(),
                c.getStatus()
            });
            
       
       
        }
        }catch(Exception ex){
            System.out.println("Carrega tabela: " + ex.getMessage());
             
        }
        this.qtd_contas.setText(String.valueOf(this.table_conta_inicial.getRowCount()));
        AtualizarTotal();
         
};
    
 
    
    public void CarregaTabela(){
    
        
            this.table_conta_inicial.getColumnModel().getColumn(0).setMinWidth(0); // Oculta Coluna
           this.table_conta_inicial.getColumnModel().getColumn(0).setMaxWidth(0);
            this.table_conta_inicial.getColumnModel().getColumn(1).setMinWidth(0); // Oculta Coluna
            this.table_conta_inicial.getColumnModel().getColumn(1).setMaxWidth(0); // Oculta Coluna
        
        System.out.println("Chamou o Carrega tabela");
        modelo = (DefaultTableModel) this.table_conta_inicial.getModel();
             //Definindo modelo da tabela;
          modelo.setNumRows(0);   
           
            
            DecimalFormat df = new DecimalFormat("#,###.00");
            DecimalFormat df2 = new DecimalFormat("######.00"); //Criando formatação Decimal
            //Definindo o tamanho da Coluna; Se quiser
            
        
        
        
        try{
            
        ContaDao dao = new ContaDao();
        for(ListaConta c : dao.RetornaContaInicial()){
          
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
                c.getNome_categoria(),
                c.getNome_fornecedor(),
                c.getNome_tipo(),
                formato.format(cal.getTime()),
                df2.format(c.getValor_conta()),
                c.getStatus()
            });
            
       
       
        }
        }catch(Exception ex){
            System.out.println("Carrega tabela: " + ex.getMessage());
             
        }
        this.qtd_contas.setText(String.valueOf(this.table_conta_inicial.getRowCount()));
        AtualizarTotal();
         
};
    
    
    
    public void CarregaTabelaAVencer(){
    
        
            this.table_conta_inicial.getColumnModel().getColumn(0).setMinWidth(0); // Oculta Coluna
           this.table_conta_inicial.getColumnModel().getColumn(0).setMaxWidth(0);
            this.table_conta_inicial.getColumnModel().getColumn(1).setMinWidth(0); // Oculta Coluna
            this.table_conta_inicial.getColumnModel().getColumn(1).setMaxWidth(0); // Oculta Coluna
        
        System.out.println("Chamou o Carrega tabela");
        modelo = (DefaultTableModel) this.table_conta_inicial.getModel();
             //Definindo modelo da tabela;
          modelo.setNumRows(0);   
           
            
            DecimalFormat df = new DecimalFormat("#,###.00");
            DecimalFormat df2 = new DecimalFormat("######.00"); //Criando formatação Decimal
            //Definindo o tamanho da Coluna; Se quiser
            
        
        
        
        try{
            
        ContaDao dao = new ContaDao();
        for(ListaConta c : dao.RetornaContaInicialAVencer()){
          
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
                c.getNome_categoria(),
                c.getNome_fornecedor(),
                c.getNome_tipo(),
                formato.format(cal.getTime()),
                df2.format(c.getValor_conta()),
                c.getStatus()
            });
            
       
       
        }
        }catch(Exception ex){
            System.out.println("Carrega tabela: " + ex.getMessage());
             
        }
        this.qtd_contas.setText(String.valueOf(this.table_conta_inicial.getRowCount()));
        AtualizarTotal();
         
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

        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_conta_inicial = new javax.swing.JTable();
        p_btn_calcular = new javax.swing.JButton();
        p_btn_registrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        l_valor_total = new javax.swing.JLabel();
        bnt_sair = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        qtd_contas = new javax.swing.JLabel();
        B_pagamento2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        p_btn_registrar1 = new javax.swing.JButton();
        p_btn_registrar2 = new javax.swing.JButton();
        bnt_a_vencer = new javax.swing.JRadioButton();
        B_pagamento1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel6.setLayout(null);

        table_conta_inicial.setAutoCreateRowSorter(true);
        table_conta_inicial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "idConta", "codigo", "Numero do documento", "Titulo da Conta", "Categoria", "Fornecedor", "Tipo Conta", "Data_Vencimento Fim", "Valor", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_conta_inicial.setToolTipText("Clique 2 vez no item para Exibir Detalhes");
        table_conta_inicial.setGridColor(new java.awt.Color(102, 102, 102));
        table_conta_inicial.setSelectionBackground(new java.awt.Color(255, 51, 51));
        table_conta_inicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_conta_inicialMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(table_conta_inicial);

        jPanel6.add(jScrollPane5);
        jScrollPane5.setBounds(10, 130, 740, 250);

        p_btn_calcular.setBackground(new java.awt.Color(255, 255, 255));
        p_btn_calcular.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        p_btn_calcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rede.png"))); // NOI18N
        p_btn_calcular.setText("     Exibir Detalhes");
        p_btn_calcular.setFocusable(false);
        p_btn_calcular.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        p_btn_calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_btn_calcularActionPerformed(evt);
            }
        });
        jPanel6.add(p_btn_calcular);
        p_btn_calcular.setBounds(760, 220, 200, 40);

        p_btn_registrar.setBackground(new java.awt.Color(255, 255, 255));
        p_btn_registrar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        p_btn_registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mercearia.png"))); // NOI18N
        p_btn_registrar.setText("   Registrar Fornecedor");
        p_btn_registrar.setToolTipText("Registrar Conta a Pagar");
        p_btn_registrar.setActionCommand("");
        p_btn_registrar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        p_btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_btn_registrarActionPerformed(evt);
            }
        });
        jPanel6.add(p_btn_registrar);
        p_btn_registrar.setBounds(760, 270, 200, 60);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Valor Contas À Pagar: ");
        jPanel6.add(jLabel1);
        jLabel1.setBounds(10, 380, 180, 40);

        l_valor_total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_valor_total.setText("R$: 0,00");
        jPanel6.add(l_valor_total);
        l_valor_total.setBounds(210, 380, 150, 40);

        bnt_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sair.png"))); // NOI18N
        bnt_sair.setText("SAIR");
        bnt_sair.setBorder(null);
        bnt_sair.setBorderPainted(false);
        bnt_sair.setFocusable(false);
        bnt_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_sairActionPerformed(evt);
            }
        });
        jPanel6.add(bnt_sair);
        bnt_sair.setBounds(850, 0, 120, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Contas à Pagar no mês atual: ");
        jPanel6.add(jLabel2);
        jLabel2.setBounds(20, 70, 200, 30);

        qtd_contas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        qtd_contas.setText("0");
        jPanel6.add(qtd_contas);
        qtd_contas.setBounds(210, 70, 60, 30);

        B_pagamento2.setBackground(new java.awt.Color(255, 255, 255));
        B_pagamento2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pagar.png"))); // NOI18N
        B_pagamento2.setText("Alterar para Pago");
        B_pagamento2.setFocusable(false);
        B_pagamento2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_pagamento2ActionPerformed(evt);
            }
        });
        jPanel6.add(B_pagamento2);
        B_pagamento2.setBounds(760, 120, 200, 40);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon (1).png"))); // NOI18N
        jButton1.setText("Consultas");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);
        jButton1.setBounds(330, 0, 310, 60);

        p_btn_registrar1.setBackground(new java.awt.Color(255, 255, 255));
        p_btn_registrar1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        p_btn_registrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/construcao (1).png"))); // NOI18N
        p_btn_registrar1.setText("       Registrar Categoria");
        p_btn_registrar1.setToolTipText("Registrar Conta a Pagar");
        p_btn_registrar1.setActionCommand("");
        p_btn_registrar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        p_btn_registrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_btn_registrar1ActionPerformed(evt);
            }
        });
        jPanel6.add(p_btn_registrar1);
        p_btn_registrar1.setBounds(760, 340, 200, 60);

        p_btn_registrar2.setBackground(new java.awt.Color(204, 204, 204));
        p_btn_registrar2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        p_btn_registrar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconfinder_JD-16_2252374.png"))); // NOI18N
        p_btn_registrar2.setText("Registrar Nova Conta");
        p_btn_registrar2.setToolTipText("Registrar Conta a Pagar");
        p_btn_registrar2.setActionCommand("");
        p_btn_registrar2.setFocusable(false);
        p_btn_registrar2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        p_btn_registrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_btn_registrar2ActionPerformed(evt);
            }
        });
        jPanel6.add(p_btn_registrar2);
        p_btn_registrar2.setBounds(20, 0, 300, 60);

        bnt_a_vencer.setText("Contas a Vencer (nos proximos 7 dias)");
        bnt_a_vencer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bnt_a_vencerItemStateChanged(evt);
            }
        });
        bnt_a_vencer.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                bnt_a_vencerAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel6.add(bnt_a_vencer);
        bnt_a_vencer.setBounds(10, 110, 290, 23);

        B_pagamento1.setBackground(new java.awt.Color(255, 255, 255));
        B_pagamento1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit-Paste-icon.png"))); // NOI18N
        B_pagamento1.setText("Alterar para Separado");
        B_pagamento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_pagamento1ActionPerformed(evt);
            }
        });
        jPanel6.add(B_pagamento1);
        B_pagamento1.setBounds(760, 170, 200, 40);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Logo_CorreCenter.jpeg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setMaximumSize(new java.awt.Dimension(1280, 263));
        jLabel3.setMinimumSize(new java.awt.Dimension(820, 263));
        jLabel3.setPreferredSize(new java.awt.Dimension(1220, 263));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 411, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bnt_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_sairActionPerformed
        // TODO add your handling code here:
        this.dispose();
        TelaLogin inicio = new TelaLogin();
        inicio.setVisible(true);

    }//GEN-LAST:event_bnt_sairActionPerformed

    private void p_btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_btn_registrarActionPerformed

        TelaFornecedor registrarconta = new TelaFornecedor();
        registrarconta.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_p_btn_registrarActionPerformed

    private void p_btn_calcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_btn_calcularActionPerformed
        int id_conta = 0;
        int indiceTabela=0;

        indiceTabela = this.table_conta_inicial.getSelectedRow();

        if (indiceTabela != -1){
            id_conta = (int) this.table_conta_inicial.getValueAt(indiceTabela, 1);
            TelaContaUnica contaunica = new TelaContaUnica(id_conta);
            contaunica.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null,"Selecione o item que deseja exibir");
        }
    }//GEN-LAST:event_p_btn_calcularActionPerformed

    private void table_conta_inicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_conta_inicialMouseClicked
        if(evt.getClickCount() == 2){

            int indiceTabela = this.table_conta_inicial.getSelectedRow();
            int id_conta = (int) this.table_conta_inicial.getValueAt(indiceTabela, 1);

            TelaContaUnica contaunica = new TelaContaUnica(id_conta);
            contaunica.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_table_conta_inicialMouseClicked

    private void B_pagamento2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_pagamento2ActionPerformed
        int indice = this.table_conta_inicial.getSelectedRow();

        if(indice != -1){
            int id_conta = (int) this.table_conta_inicial.getValueAt(indice, 0);
            System.out.println("TESTE : " + indice+" "+ id_conta);
            ContaDao dao = new ContaDao();
            dao.UpdatePago(id_conta);
           
        }
        else{
            JOptionPane.showMessageDialog(null,"Selecione item que deseja alterar");
        }
                   if ( this.bnt_a_vencer.isSelected() == true){
            CarregaTabelaAVencer();
        }
        else{
            CarregaTabela();
        }

    }//GEN-LAST:event_B_pagamento2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TelaContasAPagar_Pesquisa pesquisa = new TelaContasAPagar_Pesquisa();
        pesquisa.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void p_btn_registrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_btn_registrar1ActionPerformed
         TelaCategoria telaCategoria = new TelaCategoria();
        telaCategoria.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_p_btn_registrar1ActionPerformed

    private void p_btn_registrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_btn_registrar2ActionPerformed
          RegistrarConta registrarconta = new RegistrarConta();
        registrarconta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_p_btn_registrar2ActionPerformed

    private void bnt_a_vencerAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_bnt_a_vencerAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_bnt_a_vencerAncestorAdded

    private void bnt_a_vencerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bnt_a_vencerItemStateChanged
        
        if ( this.bnt_a_vencer.isSelected() == true){
            CarregaTabelaAVencer();
        }
        else{
            CarregaTabela();
        }
    }//GEN-LAST:event_bnt_a_vencerItemStateChanged

    private void B_pagamento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_pagamento1ActionPerformed
        int indice = this.table_conta_inicial.getSelectedRow();
        if(indice != -1){
            int id_conta = (int) this.table_conta_inicial.getValueAt(indice, 0);
            System.out.println("TESTE : " + indice+" "+ id_conta);
            ContaDao dao = new ContaDao();
            dao.UpdateSeparado(id_conta);
            
              if ( this.bnt_a_vencer.isSelected() == true){
            CarregaTabelaAVencer();
        }
        else{
            CarregaTabela();
        }
            
        
        }
        else{
            JOptionPane.showMessageDialog(null,"Selecione item que deseja alterar");
        }
    }//GEN-LAST:event_B_pagamento1ActionPerformed


    public void AtualizarTotal(){
        DecimalFormat df = new DecimalFormat("#,###.00");
        BigDecimal total = BigDecimal.ZERO ; // Criando a Variavel total com o tipo BIGDECIMAIL (MELHOR PARA CONTAS)
         for (int i=0; i < this.table_conta_inicial.getRowCount();i++){ //PERCORRENDO A JTABLE DE ACORDO COM O TAMANHO)
         String  val =  this.table_conta_inicial.getValueAt(i, 8)+""; //PEGANDO O VALOR DA LINHA i DA COLUNA 4
         BigDecimal v1 = new BigDecimal(val.replace(",", ".")); //CRIANDO UMA VARIAVEL BIGDECIMAL PARA UILIZAR LIMPANDO a , pelo .
         total = total.add(v1); //ATRIBUINDO NA VARIAVEL TOTAL;
        }
      
         
         
        this.l_valor_total.setText("R$: " + df.format(total)); //apicando formatação
    }
    

    
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
            java.util.logging.Logger.getLogger(TelaContasAPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaContasAPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaContasAPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaContasAPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaContasAPagar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_pagamento1;
    private javax.swing.JButton B_pagamento2;
    private javax.swing.JRadioButton bnt_a_vencer;
    private javax.swing.JButton bnt_sair;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel l_valor_total;
    private javax.swing.JButton p_btn_calcular;
    private javax.swing.JButton p_btn_registrar;
    private javax.swing.JButton p_btn_registrar1;
    private javax.swing.JButton p_btn_registrar2;
    private javax.swing.JLabel qtd_contas;
    private javax.swing.JTable table_conta_inicial;
    // End of variables declaration//GEN-END:variables
}

