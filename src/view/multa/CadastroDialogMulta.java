/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.multa;

import dao.ProprietarioDAO;
import dao.VeiculoDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Multa;
import modelo.Proprietario;
import modelo.Veiculo;
import util.FormUtil;

/**
 *
 * @author Administrador
 */
public class CadastroDialogMulta extends javax.swing.JDialog {

    private Multa multa = new Multa();
    private boolean ok;
    private ProprietarioDAO daoProprietario;
    private VeiculoDAO daoVeiculo;

    /**
     * Creates new form CadastroDialog
     */
    public CadastroDialogMulta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        FormUtil.centraliza(this);
    }
    
    @Override
    public void setVisible(boolean show) {
        fillCombos();
        super.setVisible(show);
    }

    public void fillCombos() {
        fillComboProprietarios();
        fillComboVeiculos();
        fillComboTipos();
    }

    public void fillComboProprietarios() {
        comboProprietarios.removeAllItems();
        List<Proprietario> props = daoProprietario.getProprietarios();
        if (props != null) {
            for (Proprietario p : props) {
                comboProprietarios.addItem(p.getNome());
            }
        }
    }

    public void fillComboVeiculos() {
        comboVeiculos.removeAllItems();
        List<Veiculo> vei = daoVeiculo.getVeiculos();
        if (vei != null) {
            for (Veiculo v : vei) {
                comboVeiculos.addItem(v.getDescricao());
            }
        }
    }

    public void fillComboTipos() {
        comboTipos.removeAllItems();
        for (Multa.tipoPontuacao tipo : Multa.tipoPontuacao.values()) {
            comboTipos.addItem(tipo.name());
        }
    }

    public ProprietarioDAO getDaoProprietario() {
        return daoProprietario;
    }

    public void setDaoProprietario(ProprietarioDAO daoProprietario) {
        this.daoProprietario = daoProprietario;
    }

    public VeiculoDAO getDaoVeiculo() {
        return daoVeiculo;
    }

    public void setDaoVeiculo(VeiculoDAO daoVeiculo) {
        this.daoVeiculo = daoVeiculo;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        txtProprietario = new javax.swing.JLabel();
        txtVeiculo = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblPontuacao = new javax.swing.JLabel();
        txtTipo = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        txtPontuacao = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        comboProprietarios = new javax.swing.JComboBox();
        comboVeiculos = new javax.swing.JComboBox();
        comboTipos = new javax.swing.JComboBox();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Incluir Multa");

        txtProprietario.setText("Proprietario");

        txtVeiculo.setText("Veiculo");

        lblData.setText("Data");

        lblPontuacao.setText("Pontuação");

        txtTipo.setText("Tipo");

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        comboProprietarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboVeiculos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboTipos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblData)
                        .addGap(18, 18, 18)
                        .addComponent(txtData))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPontuacao)
                        .addGap(18, 18, 18)
                        .addComponent(txtPontuacao))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtProprietario)
                        .addGap(18, 18, 18)
                        .addComponent(comboProprietarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtVeiculo)
                        .addGap(41, 41, 41)
                        .addComponent(comboVeiculos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(0, 115, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTipo)
                        .addGap(18, 18, 18)
                        .addComponent(comboTipos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProprietario)
                    .addComponent(comboProprietarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVeiculo)
                    .addComponent(comboVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblData)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPontuacao)
                    .addComponent(txtPontuacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTipo)
                    .addComponent(comboTipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        multa = new Multa();
        multa.setPontuacao(Integer.parseInt(txtPontuacao.getText()));
        multa.setProprietario(daoProprietario.findById(daoProprietario.findIdByNome((String)comboProprietarios.getSelectedItem())));
        multa.setTipo(Multa.tipoPontuacao.valueOf((String)comboTipos.getSelectedItem()));
        multa.setVeiculo(daoVeiculo.findByDescricao((String)comboVeiculos.getSelectedItem()));
        try {
            multa.setData(new SimpleDateFormat("dd/MM/yyyy").parse(txtData.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(CadastroDialogMulta.class.getName()).log(Level.SEVERE, null, ex);
            multa.setData(new Date());
        }
        setOk(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroDialogMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroDialogMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroDialogMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroDialogMulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroDialogMulta dialog = new CadastroDialogMulta(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboProprietarios;
    private javax.swing.JComboBox comboTipos;
    private javax.swing.JComboBox comboVeiculos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblPontuacao;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtPontuacao;
    private javax.swing.JLabel txtProprietario;
    private javax.swing.JLabel txtTipo;
    private javax.swing.JLabel txtVeiculo;
    // End of variables declaration//GEN-END:variables
}
