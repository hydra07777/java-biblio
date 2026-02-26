/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import daos.EmpruntDao;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Emprunt;

/**
 *
 * @author CAESAR
 */
public class GestionAmendesFrame extends javax.swing.JFrame {

    // Color constants for the theme (same as GestionLivresFrame)
    private static final java.awt.Color BG_NIGHT = new java.awt.Color(10, 25, 47);
    private static final java.awt.Color BG_MEDIUM = new java.awt.Color(17, 34, 64);
    private static final java.awt.Color CYAN_ACCENT = new java.awt.Color(100, 255, 218);
    private static final java.awt.Color GRAY_LIGHT = new java.awt.Color(204, 214, 246);

    private EmpruntDao empruntDao;
    private static final double AMENDE_PAR_JOUR = 10.0; // 10$ par jour de retard

    /**
     * Creates new form GestionAmendesFrame
     */
    public GestionAmendesFrame() {
        try {
            initComponents();
            empruntDao = new EmpruntDao();
            applyTheme();
            actualiserTable();
            initListeners();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Erreur lors de l'initialisation: " + e.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void initListeners() {
        btnActualiser.addActionListener(e -> actualiserTable());
        btnPayerAmende.addActionListener(e -> payerAmende());
        
        // Ajouter un listener pour remplir le champ ID quand on clique sur une ligne
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() >= 0) {
                int idEmprunt = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
                txtIdEmprunt.setText(String.valueOf(idEmprunt));
            }
        });
    }
    
    private void applyTheme() {
        // Fond principal
        getContentPane().setBackground(BG_NIGHT);
        
        // Titre
        jLabel7.setForeground(CYAN_ACCENT);
        jLabel7.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
        
        // Labels - Texte en gris clair
        java.awt.Font labelFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);
        jLabel1.setFont(labelFont);
        jLabel1.setForeground(GRAY_LIGHT);
        
        // TextField - Theme sombre
        txtIdEmprunt.setBackground(BG_MEDIUM);
        txtIdEmprunt.setForeground(GRAY_LIGHT);
        txtIdEmprunt.setCaretColor(GRAY_LIGHT);
        txtIdEmprunt.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        // Buttons - Theme cyan (comme GestionLivresFrame)
        java.awt.Font btnFontBold = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13);
        
        btnActualiser.setBackground(CYAN_ACCENT);
        btnActualiser.setForeground(BG_NIGHT);
        btnActualiser.setFont(btnFontBold);
        btnActualiser.setBorderPainted(false);
        
        btnPayerAmende.setBackground(CYAN_ACCENT);
        btnPayerAmende.setForeground(BG_NIGHT);
        btnPayerAmende.setFont(btnFontBold);
        btnPayerAmende.setBorderPainted(false);
        
        // Hover effects
        addHoverEffect(btnActualiser);
        addHoverEffect(btnPayerAmende);
        
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


    private void actualiserTable() {
        // Charger les emprunts avec statut RETARDE depuis la table emprunt
        List<Emprunt> emprunts = empruntDao.listerTous();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (Emprunt em : emprunts) {
            // Ne afficher que les emprunts avec statut RETARDE
            if ("RETARDE".equals(em.getStatut())) {
                // Calculer l'amende (10$ par jour après la date de retour)
                double montantAmende = calculerAmende(em);
                
                model.addRow(new Object[]{
                    em.getId(),
                    em.getTitreLivre() != null ? em.getTitreLivre() : "Livre #" + em.getIdLivre(),
                    em.getNomAbonne() != null ? em.getNomAbonne() + " " + em.getPrenomAbonne() : "Abonné #" + em.getIdAbonne(),
                    em.getDateEmprunt(),
                    em.getDateRetour(),
                    String.format("%.2f $", montantAmende),
                    em.getStatut()
                });
            }
        }
    }
    
    private double calculerAmende(Emprunt em) {
        if (em.getDateRetour() == null) {
            return 0;
        }
        
        Date maintenant = new Date();
        long diffMs = maintenant.getTime() - em.getDateRetour().getTime();
        long joursRetard = diffMs / (1000L * 60L * 60L * 24L);
        
        // Amende de 10$ par jour de retard (minimum 10$ si retard)
        if (joursRetard > 0) {
            return joursRetard * AMENDE_PAR_JOUR;
        }
        return 0;
    }
    
    private void payerAmende() {
        String idText = txtIdEmprunt.getText().trim();
        
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez entrer l'ID de l'emprunt ou sélectionner une ligne dans le tableau.",
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int idEmprunt = Integer.parseInt(idText);
            
            // Récupérer l'emprunt
            Emprunt em = empruntDao.trouverParId(idEmprunt);
            
            if (em == null) {
                JOptionPane.showMessageDialog(this, 
                    "Emprunt non trouvé avec l'ID: " + idEmprunt,
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!"RETARDE".equals(em.getStatut())) {
                JOptionPane.showMessageDialog(this, 
                    "L'emprunt n'est pas en retard. Statut actuel: " + em.getStatut(),
                    "Information", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Demander confirmation
            int confirm = JOptionPane.showConfirmDialog(this,
                "Voulez-vous payer l'amende pour l'emprunt #" + idEmprunt + " ?\n\n"
                + "Cela va changer le statut de l'emprunt à RETOURNE.",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                // Modifier le statut de l'emprunt à RETOURNE
                em.setStatut("RETOURNE");
                empruntDao.modifierEmprunt(em);
                
                JOptionPane.showMessageDialog(this, 
                    "Amende payée avec succès !\nLe statut de l'emprunt est maintenant RETOURNE.",
                    "Succès", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Actualiser le tableau
                actualiserTable();
                txtIdEmprunt.setText("");
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "L'ID doit être un nombre valide.",
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtIdEmprunt = new javax.swing.JTextField();
        btnActualiser = new javax.swing.JButton();
        btnPayerAmende = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Tableau des emprunts en retard
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Titre du Livre", "Nom de l'Abonné", "Date Emprunt", "Date Retour", "Amende", "Statut"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel7.setText("Gestion Amendes");
        
        jLabel1.setText("ID Emprunt:");
        txtIdEmprunt.setText("");

        btnActualiser.setText("Actualiser");
        btnActualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualiserActionPerformed(evt);
            }
        });

        btnPayerAmende.setText("Payer l'Amende");
        btnPayerAmende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayerAmendeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(txtIdEmprunt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnPayerAmende)
                        .addGap(10, 10, 10)
                        .addComponent(btnActualiser)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txtIdEmprunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPayerAmende)
                    .addComponent(btnActualiser))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualiserActionPerformed(java.awt.event.ActionEvent evt) {
        actualiserTable();
    }

    private void btnPayerAmendeActionPerformed(java.awt.event.ActionEvent evt) {
        payerAmende();
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
            java.util.logging.Logger.getLogger(GestionAmendesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionAmendesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionAmendesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionAmendesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionAmendesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualiser;
    private javax.swing.JButton btnPayerAmende;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtIdEmprunt;
    // End of variables declaration//GEN-END:variables
}
