/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import daos.AbonneDao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Abonne;

/**
 *
 * @author CAESAR
 */
public class GestionAbonneFrame extends javax.swing.JFrame {

    // Color constants for the theme (same as GestionLivresFrame)
    private static final java.awt.Color BG_NIGHT = new java.awt.Color(10, 25, 47);
    private static final java.awt.Color BG_MEDIUM = new java.awt.Color(17, 34, 64);
    private static final java.awt.Color CYAN_ACCENT = new java.awt.Color(100, 255, 218);
    private static final java.awt.Color GRAY_LIGHT = new java.awt.Color(204, 214, 246);

    private AbonneDao abonneDao;

    /**
     * Creates new form GestionAbonneFrame
     */
    public GestionAbonneFrame() {
        try {
            initComponents();
            abonneDao = new AbonneDao();
            initListeners();
            applyTheme();
            actualiserTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Erreur lors de l'initialisation: " + e.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void applyTheme() {
        // Fond principal
        getContentPane().setBackground(BG_NIGHT);
        
        // Titre
        jLabel1.setForeground(CYAN_ACCENT);
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
        
        // Labels - Texte en gris clair
        java.awt.Font labelFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);
        jLabel2.setForeground(GRAY_LIGHT);
        jLabel2.setFont(labelFont);
        jLabel3.setForeground(GRAY_LIGHT);
        jLabel3.setFont(labelFont);
        jLabel4.setForeground(GRAY_LIGHT);
        jLabel4.setFont(labelFont);
        jLabel5.setForeground(GRAY_LIGHT);
        jLabel5.setFont(labelFont);
        jLabel6.setForeground(GRAY_LIGHT);
        jLabel6.setFont(labelFont);
        
        // TextFields - Theme sombre
        java.awt.Font textFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);
        txtId.setBackground(BG_MEDIUM);
        txtId.setForeground(GRAY_LIGHT);
        txtId.setFont(textFont);
        txtId.setCaretColor(GRAY_LIGHT);
        txtId.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        txtNom.setBackground(BG_MEDIUM);
        txtNom.setForeground(GRAY_LIGHT);
        txtNom.setFont(textFont);
        txtNom.setCaretColor(GRAY_LIGHT);
        txtNom.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        txtPrenom.setBackground(BG_MEDIUM);
        txtPrenom.setForeground(GRAY_LIGHT);
        txtPrenom.setFont(textFont);
        txtPrenom.setCaretColor(GRAY_LIGHT);
        txtPrenom.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        txtEmail.setBackground(BG_MEDIUM);
        txtEmail.setForeground(GRAY_LIGHT);
        txtEmail.setFont(textFont);
        txtEmail.setCaretColor(GRAY_LIGHT);
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        txtTelephone.setBackground(BG_MEDIUM);
        txtTelephone.setForeground(GRAY_LIGHT);
        txtTelephone.setFont(textFont);
        txtTelephone.setCaretColor(GRAY_LIGHT);
        txtTelephone.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        // Panel

        
        // Buttons - Theme cyan (comme GestionLivresFrame)
        java.awt.Font btnFontBold = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13);
        
        btnAjouter.setBackground(CYAN_ACCENT);
        btnAjouter.setForeground(BG_NIGHT);
        btnAjouter.setFont(btnFontBold);
        btnAjouter.setBorderPainted(false);
        
        btnModifier.setBackground(CYAN_ACCENT);
        btnModifier.setForeground(BG_NIGHT);
        btnModifier.setFont(btnFontBold);
        btnModifier.setBorderPainted(false);
        
        btnSupprimer.setBackground(CYAN_ACCENT);
        btnSupprimer.setForeground(BG_NIGHT);
        btnSupprimer.setFont(btnFontBold);
        btnSupprimer.setBorderPainted(false);
        
        btnRechercher.setBackground(CYAN_ACCENT);
        btnRechercher.setForeground(BG_NIGHT);
        btnRechercher.setFont(btnFontBold);
        btnRechercher.setBorderPainted(false);
        
        btnActualiser.setBackground(CYAN_ACCENT);
        btnActualiser.setForeground(BG_NIGHT);
        btnActualiser.setFont(btnFontBold);
        btnActualiser.setBorderPainted(false);
        
        // Hover effects
        addHoverEffect(btnAjouter);
        addHoverEffect(btnModifier);
        addHoverEffect(btnSupprimer);
        addHoverEffect(btnRechercher);
        addHoverEffect(btnActualiser);
        
        // Tableau (comme GestionLivresFrame)
        jTable1.setBackground(BG_MEDIUM);
        jTable1.setForeground(GRAY_LIGHT);
        jTable1.setGridColor(BG_NIGHT);
        jTable1.setSelectionBackground(CYAN_ACCENT);
        jTable1.setSelectionForeground(BG_NIGHT);
        jTable1.setRowHeight(25);
        
        // Table header
        jScrollPane1.getColumnHeader().setBackground(BG_NIGHT);
        jScrollPane1.getColumnHeader().setForeground(CYAN_ACCENT);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        jScrollPane1.getViewport().setBackground(BG_MEDIUM);
    }
    
    private void addHoverEffect(javax.swing.JButton btn) {
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(BG_MEDIUM);
                btn.setForeground(CYAN_ACCENT);
                btn.setBorderPainted(true);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(CYAN_ACCENT);
                btn.setForeground(BG_NIGHT);
                btn.setBorderPainted(false);
            }
        });
    }

    private void initListeners() {
        btnAjouter.addActionListener(e -> ajouterAbonne());
        btnModifier.addActionListener(e -> modifierAbonne());
        btnSupprimer.addActionListener(e -> supprimerAbonne());
        btnRechercher.addActionListener(e -> rechercherAbonne());
        btnActualiser.addActionListener(e -> {
            viderFormulaire();
            actualiserTable();
        });
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() >= 0) {
                remplirFormulaireDepuisLigne(jTable1.getSelectedRow());
            }
        });
    }

    private void actualiserTable() {
        try {
            List<Abonne> abonnes = abonneDao.listerTous();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            for (Abonne a : abonnes) {
                model.addRow(new Object[]{a.getId(), a.getNom(), a.getPrenom(), a.getEmail(), a.getTelephone()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void remplirFormulaireDepuisLigne(int row) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        txtId.setText(String.valueOf(model.getValueAt(row, 0)));
        txtNom.setText(String.valueOf(model.getValueAt(row, 1)));
        txtPrenom.setText(String.valueOf(model.getValueAt(row, 2)));
        txtEmail.setText(String.valueOf(model.getValueAt(row, 3)));
        txtTelephone.setText(String.valueOf(model.getValueAt(row, 4)));
    }

    private void viderFormulaire() {
        txtId.setText("");
        txtNom.setText("");
        txtPrenom.setText("");
        txtEmail.setText("");
        txtTelephone.setText("");
    }

    private boolean validerFormulaire() {
        if (txtNom.getText().trim().isEmpty()
                || txtPrenom.getText().trim().isEmpty()
                || txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir au moins Nom, Prénom et Email !");
            return false;
        }
        return true;
    }

    private void ajouterAbonne() {
        if (!validerFormulaire()) {
            return;
        }
        Abonne abonne = new Abonne(
                txtNom.getText().trim(),
                txtPrenom.getText().trim(),
                txtEmail.getText().trim(),
                txtTelephone.getText().trim());
        abonneDao.ajouterAbonne(abonne);
        JOptionPane.showMessageDialog(this, "Abonné ajouté avec succès !");
        viderFormulaire();
        actualiserTable();
    }

    private void modifierAbonne() {
        String idStr = txtId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un abonné dans le tableau ou saisissez son ID.");
            return;
        }
        if (!validerFormulaire()) {
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            Abonne abonne = new Abonne(id,
                    txtNom.getText().trim(),
                    txtPrenom.getText().trim(),
                    txtEmail.getText().trim(),
                    txtTelephone.getText().trim());
            abonneDao.modifierAbonne(abonne);
            JOptionPane.showMessageDialog(this, "Abonné modifié avec succès !");
            viderFormulaire();
            actualiserTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID invalide.");
        }
    }

    private void supprimerAbonne() {
        String idStr = txtId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un abonné dans le tableau ou saisissez son ID.");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            int rep = JOptionPane.showConfirmDialog(this,
                    "Supprimer l'abonné ID " + id + " ?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (rep == JOptionPane.YES_OPTION) {
                abonneDao.supprimerAbonne(id);
                JOptionPane.showMessageDialog(this, "Abonné supprimé avec succès !");
                viderFormulaire();
                actualiserTable();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID invalide.");
        }
    }

    private void rechercherAbonne() {
        String idStr = JOptionPane.showInputDialog(this,
                "Entrez l'ID de l'abonné :",
                "Recherche",
                JOptionPane.QUESTION_MESSAGE);
        if (idStr == null) {
            return;
        }
        try {
            int id = Integer.parseInt(idStr.trim());
            Abonne abonne = abonneDao.trouverParId(id);
            if (abonne != null) {
                txtId.setText(String.valueOf(abonne.getId()));
                txtNom.setText(abonne.getNom());
                txtPrenom.setText(abonne.getPrenom());
                txtEmail.setText(abonne.getEmail());
                txtTelephone.setText(abonne.getTelephone());
            } else {
                JOptionPane.showMessageDialog(this, "Aucun abonné trouvé avec l'ID " + id);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un ID numérique valide.");
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
        txtId = new javax.swing.JTextField();
        txtPrenom = new javax.swing.JTextField();
        txtNom = new javax.swing.JTextField();
        txtTelephone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnAjouter = new javax.swing.JButton();
        btnModifier = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        btnRechercher = new javax.swing.JButton();
        btnActualiser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Gestion des abonnés");

        jLabel2.setText("Id :");

        jLabel3.setText("Nom : ");

        jLabel4.setText("Prenom : ");

        jLabel5.setText("Email : ");

        jLabel6.setText("Telephone :");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nom", "Prénom", "Email", "Telephone"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        txtPrenom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrenomActionPerformed(evt);
            }
        });

        txtNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomActionPerformed(evt);
            }
        });

        txtTelephone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelephoneActionPerformed(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        btnAjouter.setText("Ajouter");
        btnAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterActionPerformed(evt);
            }
        });

        btnModifier.setText("Modifier");

        btnSupprimer.setText("Supprimer");

        btnRechercher.setText("Rechercher");

        btnActualiser.setText("Actualiser");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(txtPrenom, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelephone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail)
                            .addComponent(txtId))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(279, 279, 279)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(164, 164, 164)
                                .addComponent(btnAjouter)
                                .addGap(18, 18, 18)
                                .addComponent(btnModifier)
                                .addGap(18, 18, 18)
                                .addComponent(btnSupprimer)
                                .addGap(18, 18, 18)
                                .addComponent(btnRechercher)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActualiser)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjouter)
                    .addComponent(btnModifier)
                    .addComponent(btnSupprimer)
                    .addComponent(btnRechercher)
                    .addComponent(btnActualiser))
                .addGap(89, 89, 89))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtPrenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrenomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrenomActionPerformed

    private void txtNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomActionPerformed

    private void txtTelephoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelephoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelephoneActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterActionPerformed
        ajouterAbonne();
    }//GEN-LAST:event_btnAjouterActionPerformed

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
            java.util.logging.Logger.getLogger(GestionAbonneFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionAbonneFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionAbonneFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionAbonneFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionAbonneFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualiser;
    private javax.swing.JButton btnAjouter;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnRechercher;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtPrenom;
    private javax.swing.JTextField txtTelephone;
    // End of variables declaration//GEN-END:variables
}
