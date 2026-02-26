
import daos.LivreDao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Livre;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author CAESAR
 */
public class GestionLivresFrame extends javax.swing.JFrame {

    // Color constants for the theme
    private static final java.awt.Color BG_NIGHT = new java.awt.Color(10, 25, 47);        // #0A192F
    private static final java.awt.Color BG_MEDIUM = new java.awt.Color(17, 34, 64);       // #112240
    private static final java.awt.Color CYAN_ACCENT = new java.awt.Color(100, 255, 218); // #64FFDA
    private static final java.awt.Color GRAY_LIGHT = new java.awt.Color(204, 214, 246); // #CCD6F6

    private LivreDao livreDao;

    /**
     * Creates new form GestionLivresFrame
     */
    public GestionLivresFrame() {
        initComponents();
        livreDao = new LivreDao();
        actualiserTable();
        initListeners();
        applyTheme();
    }

    private void applyTheme() {
        getContentPane().setBackground(BG_NIGHT);
        
        // Title
        jLabel1.setForeground(CYAN_ACCENT);
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
        
        // Labels
        java.awt.Font labelFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);
        jLabel2.setForeground(GRAY_LIGHT);
        jLabel2.setFont(labelFont);
        jLabel3.setForeground(GRAY_LIGHT);
        jLabel3.setFont(labelFont);
        jLabel4.setForeground(GRAY_LIGHT);
        jLabel4.setFont(labelFont);
        jLabel5.setForeground(GRAY_LIGHT);
        jLabel5.setFont(labelFont);
        
        // Text fields
        txtISBN.setBackground(BG_MEDIUM);
        txtISBN.setForeground(GRAY_LIGHT);
        txtISBN.setCaretColor(GRAY_LIGHT);
        txtISBN.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        txtTitre.setBackground(BG_MEDIUM);
        txtTitre.setForeground(GRAY_LIGHT);
        txtTitre.setCaretColor(GRAY_LIGHT);
        txtTitre.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        txtAuteur.setBackground(BG_MEDIUM);
        txtAuteur.setForeground(GRAY_LIGHT);
        txtAuteur.setCaretColor(GRAY_LIGHT);
        txtAuteur.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        txtQte.setBackground(BG_MEDIUM);
        txtQte.setForeground(GRAY_LIGHT);
        txtQte.setCaretColor(GRAY_LIGHT);
        txtQte.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        jTextField1.setBackground(BG_MEDIUM);
        jTextField1.setForeground(GRAY_LIGHT);
        jTextField1.setCaretColor(GRAY_LIGHT);
        
        // Panel
        jPanel1.setBackground(BG_MEDIUM);
        
        // Table
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
        
        // Buttons
        java.awt.Font btnFontBold = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13);
        
        btnAjouter.setBackground(CYAN_ACCENT);
        btnAjouter.setForeground(BG_NIGHT);
        btnAjouter.setFont(btnFontBold);
        btnAjouter.setBorderPainted(false);
        
        btnModif.setBackground(CYAN_ACCENT);
        btnModif.setForeground(BG_NIGHT);
        btnModif.setFont(btnFontBold);
        btnModif.setBorderPainted(false);
        
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
        addHoverEffect(btnModif);
        addHoverEffect(btnSupprimer);
        addHoverEffect(btnRechercher);
        addHoverEffect(btnActualiser);
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
        btnAjouter.addActionListener(e -> ajouterLivre());
        btnModif.addActionListener(e -> modifierLivre());
        btnSupprimer.addActionListener(e -> supprimerLivre());
        btnRechercher.addActionListener(e -> rechercherLivre());
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtISBN = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtQte = new javax.swing.JTextField();
        txtTitre = new javax.swing.JTextField();
        txtAuteur = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAjouter = new javax.swing.JButton();
        btnModif = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        btnRechercher = new javax.swing.JButton();
        btnActualiser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Gestion Livre");

        jLabel2.setText("Titre : ");

        txtISBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtISBNActionPerformed(evt);
            }
        });

        jLabel3.setText("isbn :");

        jLabel4.setText("Auteur :");

        jLabel5.setText("Quantité :");

        txtQte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQteActionPerformed(evt);
            }
        });

        txtTitre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTitreActionPerformed(evt);
            }
        });

        txtAuteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAuteurActionPerformed(evt);
            }
        });

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addContainerGap(211, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQte, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabel3)
                    .addContainerGap(223, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtQte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jLabel3)
                    .addContainerGap(156, Short.MAX_VALUE)))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ISBN", "TITRE", "AUTEUR", "QUANTITE"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnAjouter.setText("Ajouter");

        btnModif.setText("Modifier");

        btnSupprimer.setText("Supprimer");

        btnRechercher.setText("Rechercher");

        btnActualiser.setText("Actualiser");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(btnAjouter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModif)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSupprimer))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRechercher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualiser))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjouter)
                    .addComponent(btnModif)
                    .addComponent(btnSupprimer)
                    .addComponent(btnRechercher)
                    .addComponent(btnActualiser))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtISBNActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtISBNActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtISBNActionPerformed

    private void txtQteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtQteActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtQteActionPerformed

    private void txtTitreActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtTitreActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtTitreActionPerformed

    private void txtAuteurActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtAuteurActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtAuteurActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionLivresFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionLivresFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionLivresFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionLivresFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionLivresFrame().setVisible(true);
            }
        });
    }

    private boolean validerFormulaire() {
        if (txtISBN.getText().trim().isEmpty() ||
                txtTitre.getText().trim().isEmpty() ||
                txtAuteur.getText().trim().isEmpty() ||
                txtQte.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs !");
            return false;
        }
        try {
            Integer.parseInt(txtQte.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La quantité doit être un nombre !");
            return false;
        }
        return true;
    }

    private void viderFormulaire() {
        txtISBN.setText("");
        txtTitre.setText("");
        txtAuteur.setText("");
        txtQte.setText("");
    }

    private void ajouterLivre() {
        if (validerFormulaire()) {
            Livre livre = new Livre(
                    txtISBN.getText().trim(),
                    txtTitre.getText().trim(),
                    txtAuteur.getText().trim(),
                    Integer.parseInt(txtQte.getText().trim()));
            livreDao.ajouterLivre(livre);
            JOptionPane.showMessageDialog(this, "Livre ajouté avec succès !");
            viderFormulaire();
            actualiserTable();
        }
    }

    private void actualiserTable() {
        List<Livre> livres = livreDao.listerTous();
        chargerTable(livres);
    }

    private void chargerTable(List<Livre> livres) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (Livre l : livres) {
            model.addRow(new Object[] { l.getIsbn(), l.getTitre(), l.getAuteur(), l.getQuantite() });
        }
    }

    private void remplirFormulaireDepuisLigne(int row) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        txtISBN.setText(model.getValueAt(row, 0).toString());
        txtTitre.setText(model.getValueAt(row, 1).toString());
        txtAuteur.setText(model.getValueAt(row, 2).toString());
        txtQte.setText(model.getValueAt(row, 3).toString());
    }

    private void modifierLivre() {
        if (txtISBN.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un livre à modifier ou saisissez l'ISBN.");
            return;
        }
        if (validerFormulaire()) {
            Livre livre = new Livre(
                    txtISBN.getText().trim(),
                    txtTitre.getText().trim(),
                    txtAuteur.getText().trim(),
                    Integer.parseInt(txtQte.getText().trim()));
            livreDao.modifierLivre(livre);
            JOptionPane.showMessageDialog(this, "Livre modifié avec succès !");
            viderFormulaire();
            actualiserTable();
        }
    }

    private void supprimerLivre() {
        String isbn = txtISBN.getText().trim();
        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un livre à supprimer ou saisissez l'ISBN.");
            return;
        }
        int rep = JOptionPane.showConfirmDialog(this, "Supprimer le livre ISBN " + isbn + " ?", "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.YES_OPTION) {
            livreDao.supprimerLivre(isbn);
            JOptionPane.showMessageDialog(this, "Livre supprimé avec succès !");
            viderFormulaire();
            actualiserTable();
        }
    }

    private void rechercherLivre() {
        String critere = JOptionPane.showInputDialog(this, "Entrez un mot-clé (ISBN, titre ou auteur) :", "Recherche",
                JOptionPane.QUESTION_MESSAGE);
        if (critere == null)
            return; // Annulé
        List<Livre> livres = livreDao.rechercher(critere);
        chargerTable(livres);
        if (livres.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun livre trouvé.");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualiser;
    private javax.swing.JButton btnAjouter;
    private javax.swing.JButton btnModif;
    private javax.swing.JButton btnRechercher;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtAuteur;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtQte;
    private javax.swing.JTextField txtTitre;
    // End of variables declaration//GEN-END:variables
}
