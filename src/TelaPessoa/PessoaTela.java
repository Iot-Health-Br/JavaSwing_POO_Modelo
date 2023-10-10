/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TelaPessoa;

import ConexaoBancoDeDados.DatabaseConnection;
import PessoaControle.IPessoaControle;
import PessoaControle.PessoaControle;
import PessoaModelo.PessoaModelo;
import PessoaPersistencia.IPessoaDao;
import PessoaPersistencia.PessoaDao;
import TelaPessoa.PessoaTela;
import Renderizador.RendererFoto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Igor
 */
public class PessoaTela extends javax.swing.JFrame {

    /**
     * Creates new form PessoTela
     */
    public PessoaTela() {
        initComponents();       
        
        // INICIA MAXIMIZADA
        setExtendedState(PessoaTela.MAXIMIZED_BOTH);
        
        // SETA VAZIO NOS CAMPOS DE TEXTO
        Txt_Nome.setText("");
        
        // Inicia a Tabela tabelaMarcas atualizada.
        IPessoaDao marcaDao = new PessoaDao();
        DefaultTableModel tableModel = (DefaultTableModel) TabelaPessoa.getModel();

        // Limpar dados existentes na tabela
        tableModel.setRowCount(0);

        // Obter lista de marcas do banco de dados
        List<PessoaModelo> marcas = marcaDao.listarPessoa();

        // Preencher tabela com os dados das marcas
        for (PessoaModelo marca : marcas) {
            Object[] rowData = {marca.getId(), marca.getNome()};
            tableModel.addRow(rowData);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Txt_Nome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Panel_Logo = new javax.swing.JLabel();
        Btn_Add = new javax.swing.JButton();
        Btn_Salvar = new javax.swing.JButton();
        Btn_Alterar = new javax.swing.JButton();
        Btn_Excluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaPessoa = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro"));

        Txt_Nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Txt_NomeActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Foto"));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_Logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Btn_Add.setText("Add");
        Btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_AddActionPerformed(evt);
            }
        });

        Btn_Salvar.setText("Salvar");
        Btn_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_SalvarActionPerformed(evt);
            }
        });

        Btn_Alterar.setText("Alterar");
        Btn_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_AlterarActionPerformed(evt);
            }
        });

        Btn_Excluir.setText("Excluir");
        Btn_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Txt_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(Btn_Salvar)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_Alterar)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_Excluir)))
                .addGap(15, 15, 15))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(Btn_Add)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Txt_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Salvar)
                    .addComponent(Btn_Alterar)
                    .addComponent(Btn_Excluir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Add)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        TabelaPessoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Foto"
            }
        ));
        TabelaPessoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaPessoaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelaPessoa);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Txt_NomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Txt_NomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Txt_NomeActionPerformed

    private void TabelaPessoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaPessoaMouseClicked
        int selectedRow = TabelaPessoa.getSelectedRow();
        if (selectedRow != -1) {
            String pessoaSelecionada = (String) TabelaPessoa.getValueAt(selectedRow, 1);
            Txt_Nome.setText(pessoaSelecionada);
        }
        try {
            this.Txt_Nome.setText((String) this.TabelaPessoa.getValueAt(TabelaPessoa.getSelectedRow(), 1));
        }
        catch (Exception erro) {
            JOptionPane.showMessageDialog(this, erro);
        }
    }//GEN-LAST:event_TabelaPessoaMouseClicked

    private void Btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_AddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_AddActionPerformed

    private void Btn_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_SalvarActionPerformed
         if(!Txt_Nome.getText().equals("")){
            String nomePessoa = Txt_Nome.getText().toUpperCase(); // Supondo que jTextFieldNomeMarca seja o campo de entrada para o nome da marca

            IPessoaDao pessoaDao = new PessoaDao();
            IPessoaControle marcaControle = new PessoaControle(pessoaDao, (DefaultTableModel) TabelaPessoa.getModel());
            marcaControle.adicionarPessoa(nomePessoa);
        }
        else{
            JOptionPane.showMessageDialog(null, "Campos vazios !");
        }
    }//GEN-LAST:event_Btn_SalvarActionPerformed

    private void Btn_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_AlterarActionPerformed
         if(!Txt_Nome.getText().equals("")){
            String nomePessoa = Txt_Nome.getText().toUpperCase(); // Supondo que jTextFieldNomeMarca seja o campo de entrada para o nome da marca
            
            int selectedRow = TabelaPessoa.getSelectedRow();
                if (selectedRow != -1) {
                    int idMarca = (int) TabelaPessoa.getValueAt(selectedRow, 0);
                    IPessoaControle atualiza = new PessoaControle(new PessoaDao(), (DefaultTableModel) TabelaPessoa.getModel());
                    atualiza.atualizarPessoa(idMarca, nomePessoa);
                }
        }        
        else{
            JOptionPane.showMessageDialog(null, "Campos vazios !");
        }
    }//GEN-LAST:event_Btn_AlterarActionPerformed

    private void Btn_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ExcluirActionPerformed
         if(!Txt_Nome.getText().equals("")){
            int selectedRow = TabelaPessoa.getSelectedRow();
                if (selectedRow != -1) {
                    int idMarca = (int) TabelaPessoa.getValueAt(selectedRow, 0);
                    IPessoaControle atualiza = new PessoaControle(new PessoaDao(), (DefaultTableModel) TabelaPessoa.getModel());
                    atualiza.removerPessoa(idMarca);}}        
        else{
            JOptionPane.showMessageDialog(null, "Campos vazios !");}
    }//GEN-LAST:event_Btn_ExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(PessoaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PessoaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PessoaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PessoaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PessoaTela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Add;
    private javax.swing.JButton Btn_Alterar;
    private javax.swing.JButton Btn_Excluir;
    private javax.swing.JButton Btn_Salvar;
    private javax.swing.JLabel Panel_Logo;
    private javax.swing.JTable TabelaPessoa;
    private javax.swing.JTextField Txt_Nome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
