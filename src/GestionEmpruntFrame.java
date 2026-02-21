/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import daos.EmpruntDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Emprunt;

/**
 *
 * @author CAESAR
 */
public class GestionEmpruntFrame extends javax.swing.JFrame {

    private EmpruntDao empruntDao;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates new form GestionEmpruntFrame
     */
    public GestionEmpruntFrame() {
        initComponents();
        empruntDao = new EmpruntDao();
        actualiserTable();
        initListeners();
    }

    private void initListeners() {
        btnEmprunt.addActionListener(e -> ajouterEmprunt());
        btnEnregistrerEmprunt.addActionListener(e -> enregistrerRetour());
        btnActualiser.addActionListener(e -> actualiserTable());
        btnVider.addActionListener(e -> viderFormulaire());

        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() >= 0) {
                remplirFormulaireDepuisLigne(jTable1.getSelectedRow());
            }
        });
    }

    private void actualiserTable() {
        List<Emprunt> emprunts = empruntDao.listerTous();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (Emprunt em : emprunts) {
            model.addRow(new Object[]{
                em.getId(),
                em.getIdLivre(),
                em.getIdAbonne(),
                em.getDateEmprunt(),
                em.getDateRetour(),
                em.getStatut()
            });
        }
    }

    private void remplirFormulaireDepuisLigne(int row) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jTextField2.setText(String.valueOf(model.getValueAt(row, 0)));
        jTextField3.setText(String.valueOf(model.getValueAt(row, 1)));
        jTextField4.setText(String.valueOf(model.getValueAt(row, 2)));
        jTextField6.setText(formatDateCell(model.getValueAt(row, 3)));
        jTextField5.setText(formatDateCell(model.getValueAt(row, 4)));
        jTextField1.setText(String.valueOf(model.getValueAt(row, 5)));
    }

    private String formatDateCell(Object cell) {
        if (cell == null) {
            return "";
        }
        if (cell instanceof Date d) {
            return dateFormat.format(d);
        }
        return String.valueOf(cell);
    }

    private void viderFormulaire() {
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField6.setText("");
        jTextField5.setText("");
        jTextField1.setText("");
    }

    private Date parseDateOrNull(String value) throws ParseException {
        String v = value == null ? "" : value.trim();
        if (v.isEmpty()) return null;
        dateFormat.setLenient(false);
        return dateFormat.parse(v);
    }

    private void ajouterEmprunt() {
        try {
            String idLivreStr = jTextField3.getText().trim();
            String idAbonneStr = jTextField4.getText().trim();
            String dateEmpruntStr = jTextField6.getText().trim();
            String dateRetourStr = jTextField5.getText().trim();
            String statut = jTextField1.getText().trim();

            if (idLivreStr.isEmpty() || idAbonneStr.isEmpty() || dateEmpruntStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir au minimum: ID Livre, ID Abonné, Date Emprunt.");
                return;
            }

            int idLivre = Integer.parseInt(idLivreStr);
            int idAbonne = Integer.parseInt(idAbonneStr);
            Date dateEmprunt = parseDateOrNull(dateEmpruntStr);
            Date dateRetour = parseDateOrNull(dateRetourStr);
            if (statut.isEmpty()) {
                statut = "EN_COURS";
            }

            Emprunt emprunt = new Emprunt(idLivre, idAbonne, dateEmprunt, dateRetour, statut);
            empruntDao.ajouterEmprunt(emprunt);
            JOptionPane.showMessageDialog(this, "Emprunt ajouté avec succès !");
            viderFormulaire();
            actualiserTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID Livre / ID Abonné doivent être numériques.");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Format de date invalide. Utilisez yyyy-MM-dd.");
        }
    }

    private void enregistrerRetour() {
        try {
            String idEmpruntStr = jTextField2.getText().trim();
            if (idEmpruntStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir ou sélectionner l'ID Emprunt.");
                return;
            }
            int idEmprunt = Integer.parseInt(idEmpruntStr);

            Date dateRetour = parseDateOrNull(jTextField5.getText());
            if (dateRetour == null) {
                dateRetour = new Date();
            }

            empruntDao.retournerEmprunt(idEmprunt, dateRetour);
            JOptionPane.showMessageDialog(this, "Retour enregistré avec succès !");
            viderFormulaire();
            actualiserTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID Emprunt doit être numérique.");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Format de date retour invalide. Utilisez yyyy-MM-dd.");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEmprunt = new javax.swing.JButton();
        btnEnregistrerEmprunt = new javax.swing.JButton();
        btnActualiser = new javax.swing.JButton();
        btnVider = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID Emprunt :");

        jLabel2.setText("ISBN Livre :");

        jLabel3.setText("ID Abonné :");

        jLabel4.setText("Date Emprunt (yyyy-MM-dd) :");

        jLabel5.setText("Date Retour (yyyy-MM-dd) :");

        jLabel6.setText("Statut :");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "ID Livre", "ID Abonné", "Date Emprunt", "Date Retour", "Statut"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnEmprunt.setText("Nouvel Emprunt");
        btnEmprunt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpruntActionPerformed(evt);
            }
        });

        btnEnregistrerEmprunt.setText("Enregistrer Retour");
        btnEnregistrerEmprunt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerEmpruntActionPerformed(evt);
            }
        });

        btnActualiser.setText("Actualiser");
        btnActualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualiserActionPerformed(evt);
            }
        });

        btnVider.setText("Vider");
        btnVider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViderActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Gestion d'emprunt");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEmprunt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEnregistrerEmprunt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualiser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVider)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
            .addGroup(layout.createSequentialGroup()
                .addGap(293, 293, 293)
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 169, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEmprunt)
                            .addComponent(btnEnregistrerEmprunt)
                            .addComponent(btnActualiser)
                            .addComponent(btnVider))))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEmpruntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpruntActionPerformed
        ajouterEmprunt();
    }//GEN-LAST:event_btnEmpruntActionPerformed

    private void btnEnregistrerEmpruntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerEmpruntActionPerformed
        enregistrerRetour();
    }//GEN-LAST:event_btnEnregistrerEmpruntActionPerformed

    private void btnActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualiserActionPerformed
        actualiserTable();
    }//GEN-LAST:event_btnActualiserActionPerformed

    private void btnViderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViderActionPerformed
        viderFormulaire();
    }//GEN-LAST:event_btnViderActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

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
            java.util.logging.Logger.getLogger(GestionEmpruntFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionEmpruntFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionEmpruntFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionEmpruntFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionEmpruntFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualiser;
    private javax.swing.JButton btnEmprunt;
    private javax.swing.JButton btnEnregistrerEmprunt;
    private javax.swing.JButton btnVider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
