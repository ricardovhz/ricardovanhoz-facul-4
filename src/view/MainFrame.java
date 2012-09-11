/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bancodados.BancoDados;
import bancodados.MySql;
import util.FormUtil;

/**
 *
 * @author Administrador
 */
public class MainFrame extends javax.swing.JFrame {

    private BancoDados banco;

    public BancoDados getBanco() {
        return banco;
    }

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        FormUtil.centraliza(this);
        iniciaBanco();
    }
    
    public void iniciaBanco() {
        banco = new MySql();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        menuProprietario = new javax.swing.JMenuItem();
        menuVeiculo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuCadastro.setText("Cadastro");

        menuProprietario.setText("Proprietario");
        menuProprietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProprietarioActionPerformed(evt);
            }
        });
        menuCadastro.add(menuProprietario);

        menuVeiculo.setText("Veículo");
        menuVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVeiculoActionPerformed(evt);
            }
        });
        menuCadastro.add(menuVeiculo);
        menuCadastro.add(jSeparator1);

        menuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        menuSair.setText("Sair");
        menuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSairActionPerformed(evt);
            }
        });
        menuCadastro.add(menuSair);

        jMenuBar1.add(menuCadastro);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuSairActionPerformed

    private void menuProprietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProprietarioActionPerformed
        // TODO add your handling code here:
        CadastroProprietario prop = new CadastroProprietario();
        prop.setBanco(banco);
        prop.setarDAO();
        FormUtil.centraliza(prop);
        prop.setVisible(true);
    }//GEN-LAST:event_menuProprietarioActionPerformed

    private void menuVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVeiculoActionPerformed
        // TODO add your handling code here:
        CadastroVeiculos veic = new CadastroVeiculos();
        veic.setBanco(banco);
        veic.setarDAO();
        FormUtil.centraliza(veic);
        veic.setVisible(true);
    }//GEN-LAST:event_menuVeiculoActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenuItem menuProprietario;
    private javax.swing.JMenuItem menuSair;
    private javax.swing.JMenuItem menuVeiculo;
    // End of variables declaration//GEN-END:variables
}
