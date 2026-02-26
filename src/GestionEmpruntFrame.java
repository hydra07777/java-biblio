/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import daos.AbonneDao;
import daos.EmpruntDao;
import daos.LivreDao;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Abonne;
import models.Emprunt;
import models.Livre;

/**
 *
 * @author CAESAR
 */
public class GestionEmpruntFrame extends javax.swing.JFrame {

    // Color constants for the theme (same as GestionLivresFrame)
    private static final Color BG_NIGHT = new Color(10, 25, 47);
    private static final Color BG_MEDIUM = new Color(17, 34, 64);
    private static final Color CYAN_ACCENT = new Color(100, 255, 218);
    private static final Color GRAY_LIGHT = new Color(204, 214, 246);

    private EmpruntDao empruntDao;
    private AbonneDao abonneDao;
    private LivreDao livreDao;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates new form GestionEmpruntFrame
     */
    public GestionEmpruntFrame() {
        try {
            initComponents();
            empruntDao = new EmpruntDao();
            abonneDao = new AbonneDao();
            livreDao = new LivreDao();
            initListeners();
            applyTheme();
            chargerTousLesTableaux();
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
        jLabel7.setForeground(CYAN_ACCENT);
        jLabel7.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
        
        // Labels - Texte en gris clair
        java.awt.Font labelFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13);
        jLabel1.setForeground(GRAY_LIGHT);
        jLabel1.setFont(labelFont);
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
        
        // Labels des tableaux
        jLabel8.setForeground(CYAN_ACCENT);
        jLabel8.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        jLabel9.setForeground(CYAN_ACCENT);
        jLabel9.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        jLabel10.setForeground(CYAN_ACCENT);
        jLabel10.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        
        // TextFields - Theme sombre
        java.awt.Font textFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13);
        jTextField1.setBackground(BG_MEDIUM);
        jTextField1.setForeground(GRAY_LIGHT);
        jTextField1.setFont(textFont);
        jTextField1.setCaretColor(GRAY_LIGHT);
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        jTextField2.setBackground(BG_MEDIUM);
        jTextField2.setForeground(GRAY_LIGHT);
        jTextField2.setFont(textFont);
        jTextField2.setCaretColor(GRAY_LIGHT);
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        jTextField3.setBackground(BG_MEDIUM);
        jTextField3.setForeground(GRAY_LIGHT);
        jTextField3.setFont(textFont);
        jTextField3.setCaretColor(GRAY_LIGHT);
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        jTextField4.setBackground(BG_MEDIUM);
        jTextField4.setForeground(GRAY_LIGHT);
        jTextField4.setFont(textFont);
        jTextField4.setCaretColor(GRAY_LIGHT);
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        jTextField5.setBackground(BG_MEDIUM);
        jTextField5.setForeground(GRAY_LIGHT);
        jTextField5.setFont(textFont);
        jTextField5.setCaretColor(GRAY_LIGHT);
        jTextField5.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        jTextField6.setBackground(BG_MEDIUM);
        jTextField6.setForeground(GRAY_LIGHT);
        jTextField6.setFont(textFont);
        jTextField6.setCaretColor(GRAY_LIGHT);
        jTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        
        // Buttons - Theme cyan (comme GestionLivresFrame)
        java.awt.Font btnFontBold = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13);
        
        btnEmprunt.setBackground(CYAN_ACCENT);
        btnEmprunt.setForeground(BG_NIGHT);
        btnEmprunt.setFont(btnFontBold);
        btnEmprunt.setBorderPainted(false);
        
        btnEnregistrerEmprunt.setBackground(CYAN_ACCENT);
        btnEnregistrerEmprunt.setForeground(BG_NIGHT);
        btnEnregistrerEmprunt.setFont(btnFontBold);
        btnEnregistrerEmprunt.setBorderPainted(false);
        
        btnModifierStatut.setBackground(CYAN_ACCENT);
        btnModifierStatut.setForeground(BG_NIGHT);
        btnModifierStatut.setFont(btnFontBold);
        btnModifierStatut.setBorderPainted(false);
        
        btnActualiser.setBackground(CYAN_ACCENT);
        btnActualiser.setForeground(BG_NIGHT);
        btnActualiser.setFont(btnFontBold);
        btnActualiser.setBorderPainted(false);
        
        btnVider.setBackground(CYAN_ACCENT);
        btnVider.setForeground(BG_NIGHT);
        btnVider.setFont(btnFontBold);
        btnVider.setBorderPainted(false);
        
        // Hover effects
        addHoverEffect(btnEmprunt);
        addHoverEffect(btnEnregistrerEmprunt);
        addHoverEffect(btnModifierStatut);
        addHoverEffect(btnActualiser);
        addHoverEffect(btnVider);
        
        // Apply colors to all tables (like GestionLivresFrame)
        applyTableColors(jTable1, jScrollPane1);
        applyTableColors(jTable2, jScrollPane2);
        applyTableColors(jTable3, jScrollPane3);
    }
    
    private void applyTableColors(javax.swing.JTable table, javax.swing.JScrollPane scrollPane) {
        // Tableau (comme GestionLivresFrame)
        table.setBackground(BG_MEDIUM);
        table.setForeground(GRAY_LIGHT);
        table.setGridColor(BG_NIGHT);
        table.setSelectionBackground(CYAN_ACCENT);
        table.setSelectionForeground(BG_NIGHT);
        table.setRowHeight(25);
        
        // Table header
        scrollPane.getColumnHeader().setBackground(BG_NIGHT);
        scrollPane.getColumnHeader().setForeground(CYAN_ACCENT);
        scrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(BG_MEDIUM));
        scrollPane.getViewport().setBackground(BG_MEDIUM);
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
        btnEmprunt.addActionListener(e -> ajouterEmprunt());
        btnEnregistrerEmprunt.addActionListener(e -> enregistrerRetour());
        btnModifierStatut.addActionListener(e -> modifierStatut());
        btnActualiser.addActionListener(e -> chargerTousLesTableaux());
        btnVider.addActionListener(e -> viderFormulaire());

        // Tableau des emprunts
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() >= 0) {
                remplirFormulaireDepuisEmprunt(jTable1.getSelectedRow());
            }
        });
        
        // Tableau des abonnés - remplir ID abonné
        jTable2.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable2.getSelectedRow() >= 0) {
                int row = jTable2.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                int idAbonne = (int) model.getValueAt(row, 0);
                jTextField4.setText(String.valueOf(idAbonne));
            }
        });
        
        // Tableau des livres - remplir ID livre
        jTable3.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable3.getSelectedRow() >= 0) {
                int row = jTable3.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
                int idLivre = (int) model.getValueAt(row, 0);
                jTextField3.setText(String.valueOf(idLivre));
            }
        });
    }
    
    private void chargerTousLesTableaux() {
        // Charger les abonnés
        List<Abonne> abonnes = abonneDao.listerTous();
        DefaultTableModel modelAbonne = (DefaultTableModel) jTable2.getModel();
        modelAbonne.setRowCount(0);
        for (Abonne a : abonnes) {
            modelAbonne.addRow(new Object[]{a.getId(), a.getNom(), a.getPrenom()});
        }
        
        // Charger les livres (avec ID comme identifiant)
        List<Livre> livres = livreDao.listerTous();
        DefaultTableModel modelLivre = (DefaultTableModel) jTable3.getModel();
        modelLivre.setRowCount(0);
        for (Livre l : livres) {
            modelLivre.addRow(new Object[]{l.getId(), l.getTitre(), l.getAuteur()});
        }
        
        // Charger les emprunts
        chargerTableEmprunts();
    }
    
    private void chargerTableEmprunts() {
        try {
            List<Emprunt> emprunts = empruntDao.listerTous();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            for (Emprunt em : emprunts) {
                model.addRow(new Object[]{
                    em.getId(),
                    em.getTitreLivre() != null ? em.getTitreLivre() : "Livre #" + em.getIdLivre(),
                    em.getNomAbonne() != null ? em.getNomAbonne() + " " + em.getPrenomAbonne() : "Abonné #" + em.getIdAbonne(),
                    em.getDateEmprunt(),
                    em.getDateRetour(),
                    em.getStatut()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors du chargement des emprunts: " + e.getMessage(), 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void remplirFormulaireDepuisEmprunt(int row) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jTextField2.setText(String.valueOf(model.getValueAt(row, 0)));
        // Pour le remplissage depuis le tableau, on garde les IDs
        jTextField3.setText("");
        jTextField4.setText("");
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
        
        // Désélectionner les tableaux
        jTable1.clearSelection();
        jTable2.clearSelection();
        jTable3.clearSelection();
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
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un livre ET un abonné, et saisir la date d'emprunt.");
                return;
            }

            int idLivre = Integer.parseInt(idLivreStr);
            int idAbonne = Integer.parseInt(idAbonneStr);
            Date dateEmprunt = parseDateOrNull(dateEmpruntStr);
            Date dateRetour = parseDateOrNull(dateRetourStr);
            
            if (dateEmprunt == null) {
                JOptionPane.showMessageDialog(this, "La date d'emprunt est requise. Format: yyyy-MM-dd");
                return;
            }
            
            if (statut.isEmpty()) {
                statut = "EN_COURS";
            }

            Emprunt emprunt = new Emprunt(idLivre, idAbonne, dateEmprunt, dateRetour, statut);
            
            boolean success = empruntDao.ajouterEmprunt(emprunt);
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Emprunt ajouté avec succès !");
                viderFormulaire();
                chargerTousLesTableaux();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Erreur: L'emprunt n'a pas pu être ajouté.\n\n" +
                    "Causes possibles:\n" +
                    "- Le livre avec ID " + idLivre + " n'existe pas\n" +
                    "- L'abonné avec ID " + idAbonne + " n'existe pas\n\n" +
                    "Veuillez vérifier les IDs saisis.", 
                    "Erreur d'ajout d'emprunt", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID Livre / ID Abonné doivent être numériques.");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Format de date invalide. Utilisez yyyy-MM-dd.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void enregistrerRetour() {
        try {
            String idEmpruntStr = jTextField2.getText().trim();
            if (idEmpruntStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un emprunt dans le tableau pour enregistrer le retour.");
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
            chargerTousLesTableaux();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID Emprunt doit être numérique.");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Format de date retour invalide. Utilisez yyyy-MM-dd.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private void modifierStatut() {
        try {
            String idEmpruntStr = jTextField2.getText().trim();
            String nouveauStatut = jTextField1.getText().trim();
            
            if (idEmpruntStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un emprunt dans le tableau.");
                return;
            }
            
            if (nouveauStatut.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir le nouveau statut (EN_COURS, RETOURNE, ou RETARDE).");
                return;
            }
            
            // Valider le statut
            if (!nouveauStatut.equals("EN_COURS") && !nouveauStatut.equals("RETOURNE") && !nouveauStatut.equals("RETARDE")) {
                JOptionPane.showMessageDialog(this, "Le statut doit être: EN_COURS, RETOURNE, ou RETARDE");
                return;
            }
            
            int idEmprunt = Integer.parseInt(idEmpruntStr);
            
            // Récupérer l'emprunt existant
            Emprunt emprunt = empruntDao.trouverParId(idEmprunt);
            if (emprunt == null) {
                JOptionPane.showMessageDialog(this, "Emprunt non trouvé.");
                return;
            }
            
            // Modifier le statut
            emprunt.setStatut(nouveauStatut);
            empruntDao.modifierEmprunt(emprunt);
            
            
            JOptionPane.showMessageDialog(this, "Statut modifié avec succès !");
            viderFormulaire();
            chargerTousLesTableaux();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID Emprunt doit être numérique.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage());
            ex.printStackTrace();
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        
        btnEmprunt = new javax.swing.JButton();
        btnEnregistrerEmprunt = new javax.swing.JButton();
        btnModifierStatut = new javax.swing.JButton();
        btnActualiser = new javax.swing.JButton();
        btnVider = new javax.swing.JButton();
        
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Labels du formulaire
        jLabel1.setText("ID Emprunt :");
        jLabel2.setText("ID Livre *:");
        jLabel3.setText("ID Abonné *:");
        jLabel4.setText("Date Emprunt (yyyy-MM-dd) *:");
        jLabel5.setText("Date Retour (yyyy-MM-dd) :");
        jLabel6.setText("Statut :");
        
        // Labels des tableaux
        jLabel8.setText("Liste des Abonnés");
        jLabel9.setText("Liste des Livres");
        jLabel10.setText("Liste des Emprunts");

        // Tableau 1 - Emprunts
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{null, null, null, null, null, null}},
            new String [] {"ID", "Titre du Livre", "Nom de l'Abonné", "Date Emprunt", "Date Retour", "Statut"}
        ));
        jScrollPane1.setViewportView(jTable1);

        // Tableau 2 - Abonnés
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{null, null, null}},
            new String [] {"ID", "Nom", "Prénom"}
        ));
        jScrollPane2.setViewportView(jTable2);

        // Tableau 3 - Livres
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{null, null, null}},
            new String [] {"ID", "Titre", "Auteur"}
        ));
        jScrollPane3.setViewportView(jTable3);

        // Buttons
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

        btnModifierStatut.setText("Modifier Statut");
        btnModifierStatut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierStatutActionPerformed(evt);
            }
        });

        btnActualiser.setText("Actualiser Tout");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    // Titre
                    .addGroup(layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jLabel7))
                    
                    // Première rangée: Formulaire à gauche, tableaux à droite
                    .addGroup(layout.createSequentialGroup()
                        // Formulaire à gauche
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        // Tableaux à droite
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    
                    // Deuxième rangée: Tableau des emprunts
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1))
                    
                    // Troisième rangée: Buttons
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEmprunt)
                        .addGap(10, 10, 10)
                        .addComponent(btnEnregistrerEmprunt)
                        .addGap(10, 10, 10)
                        .addComponent(btnModifierStatut)
                        .addGap(10, 10, 10)
                        .addComponent(btnActualiser)
                        .addGap(10, 10, 10)
                        .addComponent(btnVider)))
                .addGap(20, 20, 20))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEmprunt)
                    .addComponent(btnEnregistrerEmprunt)
                    .addComponent(btnModifierStatut)
                    .addComponent(btnActualiser)
                    .addComponent(btnVider))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEmpruntActionPerformed(java.awt.event.ActionEvent evt) {
        ajouterEmprunt();
    }

    private void btnEnregistrerEmpruntActionPerformed(java.awt.event.ActionEvent evt) {
        enregistrerRetour();
    }

    private void btnModifierStatutActionPerformed(java.awt.event.ActionEvent evt) {
        modifierStatut();
    }

    private void btnActualiserActionPerformed(java.awt.event.ActionEvent evt) {
        chargerTousLesTableaux();
    }

    private void btnViderActionPerformed(java.awt.event.ActionEvent evt) {
        viderFormulaire();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(GestionEmpruntFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionEmpruntFrame().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton btnActualiser;
    private javax.swing.JButton btnEmprunt;
    private javax.swing.JButton btnEnregistrerEmprunt;
    private javax.swing.JButton btnModifierStatut;
    private javax.swing.JButton btnVider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
}
