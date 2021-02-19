package Swing;

import Entities.Client;
import Entities.Prospect;
import Entities.Societe;
import utilitaires.Adresse;
import utilitaires.DomaineSociete;
import utilitaires.SocieteException;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FenetreCreationModifSupp extends JFrame {
    //Panel infos
    private JPanel infos = new JPanel();
    private JButton retourAccueil = new JButton("Retour Accueil");
    private JLabel clientOuProspect = new JLabel();

    // formulaire de création/modif
    private JLabel creationOuModif = new JLabel();
    private JLabel identifiantLabel = new JLabel("Identifiant");
    private JTextField identifiant = new JTextField(20);
    private JLabel raisonSocialeLabel = new JLabel("Raison Sociale : ");
    private JTextField raisonSociale = new JTextField(20);

    //choix déroulant domaine
    private JLabel domaineLabel = new JLabel("Domaine : ");
    private JComboBox<DomaineSociete> domaineChoix = new JComboBox<>(DomaineSociete.values());

    private JLabel adresseLabel = new JLabel("Adresse : ");
    private JLabel numeroRueLabel = new JLabel("Numéro de rue : ");
    private JTextField numeroRue = new JTextField(20);
    private JLabel nomRueLabel = new JLabel("Nom de rue :");
    private JTextField nomRue = new JTextField(20);
    private JLabel codePostalLabel = new JLabel("Code postal : ");
    private JTextField codePostal = new JTextField(20);
    private JLabel villeLabel = new JLabel("Ville :");
    private JTextField ville = new JTextField(20);
    private JLabel numeroTelephoneLabel = new JLabel("Numéro de téléphone : ");
    private JTextField numeroTelephone = new JTextField(20);
    private JLabel adresseMailLabel = new JLabel("Adresse mail : ");
    private JTextField adresseMail = new JTextField(20);


    //panel pour client
    private JPanel choixClientPanel = new JPanel();
    private JLabel chiffreAffairesLabel = new JLabel("Chiffres d'affaires : ");
    private JTextField chiffreAffaires = new JTextField(20);
    private JLabel nombreEmployesLabel = new JLabel("Nombres d'employés : ");
    private JTextField nombreEmployes = new JTextField(20);
    private JLabel commentairesLabel1 = new JLabel("Commentaires : ");
    private JTextArea commentaires1 = new JTextArea(3, 20);
    private JScrollPane scrollPane = new JScrollPane(commentaires1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    //panel pour prospect
    private JPanel choixProspectPanel = new JPanel();
    private JLabel dateProspectionLabel = new JLabel("Date de prospection : ");
    private JTextField dateProspection = new JTextField(20);
    private JLabel prospectInteresseLabel = new JLabel("Prospect interessé : ");
    private String[] reponseInteret = new String[] {"OUI", "NON"};
    private JComboBox<String> prospectInteresse = new JComboBox<>(reponseInteret);
    private JLabel commentairesLabel2 = new JLabel("Commentaires : ");
    private JTextArea commentaires2 = new JTextArea(3, 20);
    private JScrollPane scrollPane2 = new JScrollPane(commentaires2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    //panel valider
    private JPanel validationPanel = new JPanel();
    private JButton validerForm = new JButton("Valider");

    //panel supprimer
    private JPanel supprimerPanel = new JPanel();
    private JButton suppression = new JButton("Supprimer");

    private FenetrePrincipale parent;
    private Client editedClient; //client à modifier
    private Prospect editedProspect; //prospect à modifier

    private Date date;

    //constructeurs

    /**
     * Constructeur de la jframe FenetreCreationModifSupp, permet d'afficher les jpanel, titres et boutons correspondants
     * à l'action souhaitée : ici uniquement la création de prospect/client.
     * @param methode : action souhaitée (création, modification ou suppression).
     * @param titre : type de société concernée (clients ou prospects).
     * @param identif : numéro d'identifiant unique pour chaque société.
     * @param panelClient : Jpanel contenant les boutons et jtextfields spécifique à un client (chiffres d'affaires et
     *                    nombres d'employés).
     * @param panelProspect : Jpanel contenant les boutons et jtextfields spécifique à un prospect (date de prospection et
     *      *                 jcombobox avec la réponse du prospect).
     * @param panelValidation : Jpanel contenant le bouton valider (en cas de création ou modification).
     * @param panelSuppression : Joanel contenant le bouton supprimer (en cas de suppression d'une société).
     * @param parent : Jframe "parent" de FenetreCreationModifSupp (ici FenetrePrincipale).
     */
    public FenetreCreationModifSupp(String methode, String titre, int identif,
                                    boolean panelClient, boolean panelProspect,
                                    boolean panelValidation, boolean panelSuppression,
                                    FenetrePrincipale parent){
        initComponents();
        layoutComponents();

        creationOuModif.setText(methode);
        clientOuProspect.setText(titre);
        identifiant.setText(String.valueOf(identif));
        choixClientPanel.setVisible(panelClient);
        choixProspectPanel.setVisible(panelProspect);
        validationPanel.setVisible(panelValidation);
        supprimerPanel.setVisible(panelSuppression);
        this.parent = parent;

    }

    /**
     * Constructeur de la jframe FenetreCreationModifSupp, permet d'afficher les jpanel, titres et boutons correspondants
     * à l'action souhaitée : ici la suppression ou modification d'un client.
     * @param methode : action souhaitée (création, modification ou suppression).
     * @param titre : type de société concernée (clients ou prospects).
     * @param identif : numéro d'identifiant unique pour chaque société.
     * @param panelClient : Jpanel contenant les boutons et jtextfields spécifique à un client (chiffres d'affaires et
     *                    nombres d'employés).
     * @param panelProspect : Jpanel contenant les boutons et jtextfields spécifique à un prospect (date de prospection et
     *                        jcombobox avec la réponse du prospect).
     * @param panelValidation : Jpanel contenant le bouton valider (en cas de création ou modification).
     * @param panelSuppression : Joanel contenant le bouton supprimer (en cas de suppression d'une société).
     * @param parent : Jframe "parent" de FenetreCreationModifSupp (ici FenetrePrincipale).
     * @param editedClient : client sélectionné dans la liste déroulante et que l'on souhaite modifier ou supprimer.
     */
    public FenetreCreationModifSupp(String methode, String titre, int identif,
                                    boolean panelClient, boolean panelProspect,
                                    boolean panelValidation, boolean panelSuppression,
                                    FenetrePrincipale parent, Client editedClient) {
        this.editedClient = editedClient;
        initComponents();
        layoutComponents();

        creationOuModif.setText(methode);
        clientOuProspect.setText(titre);
        identifiant.setText(String.valueOf(identif));
        choixClientPanel.setVisible(panelClient);
        choixProspectPanel.setVisible(panelProspect);
        validationPanel.setVisible(panelValidation);
        supprimerPanel.setVisible(panelSuppression);
        this.parent = parent;
    }

    /**
     * Constructeur de la jframe FenetreCreationModifSupp, permet d'afficher les jpanel, titres et boutons correspondants
     * à l'action souhaitée : ici la suppression ou modification d'un prospect.
     * @param methode : action souhaitée (création, modification ou suppression).
     * @param titre : type de société concernée (clients ou prospects).
     * @param identif : numéro d'identifiant unique pour chaque société.
     * @param panelClient : Jpanel contenant les boutons et jtextfields spécifique à un client (chiffres d'affaires et
     *                    nombres d'employés).
     * @param panelProspect : Jpanel contenant les boutons et jtextfields spécifique à un prospect (date de prospection et
     *                        jcombobox avec la réponse du prospect).
     * @param panelValidation : Jpanel contenant le bouton valider (en cas de création ou modification).
     * @param panelSuppression : Joanel contenant le bouton supprimer (en cas de suppression d'une société).
     * @param parent : Jframe "parent" de FenetreCreationModifSupp (ici FenetrePrincipale).
     * @param editedProspect : prospect sélectionné dans la liste déroulante et que l'on souhaite modifier ou supprimer.
     */
    public FenetreCreationModifSupp(String methode, String titre, int identif,
                                    boolean panelClient, boolean panelProspect,
                                    boolean panelValidation, boolean panelSuppression,
                                    FenetrePrincipale parent, Prospect editedProspect) {
        this.editedProspect = editedProspect;
        initComponents();
        layoutComponents();

        creationOuModif.setText(methode);
        clientOuProspect.setText(titre);
        identifiant.setText(String.valueOf(identif));
        choixClientPanel.setVisible(panelClient);
        choixProspectPanel.setVisible(panelProspect);
        validationPanel.setVisible(panelValidation);
        supprimerPanel.setVisible(panelSuppression);
        this.parent = parent;
    }



    public void initComponents(){
        //si le client choisi est null, alors les jtextfield sont vides, sinon, on ajoute les elements de la liste dans
        // les jtextfields
        if (this.editedClient != null) {
            this.identifiant.setText(String.valueOf(editedClient.getIdentifiant()));
            this.raisonSociale.setText(editedClient.getRaisonSociale());
            this.domaineChoix.setSelectedItem(editedClient.getDomaine());
            this.numeroRue.setText(String.valueOf(editedClient.adresse.getNumeroRue()));
            this.nomRue.setText(editedClient.adresse.getNomRue());
            this.codePostal.setText(editedClient.adresse.getCodePostal());
            this.ville.setText(editedClient.adresse.getVille());
            this.numeroTelephone.setText(editedClient.getTelephone());
            this.adresseMail.setText(editedClient.getAdresseMail());
            this.chiffreAffaires.setText(String.valueOf(editedClient.getChiffreAffairesClient()));
            this.nombreEmployes.setText(String.valueOf(editedClient.getNombreEmployes()));
            this.commentaires1.setText(editedClient.getCommentaires());
        }
        if(this.editedProspect != null) {
            this.identifiant.setText(String.valueOf(editedProspect.getIdentifiant()));
            this.raisonSociale.setText(editedProspect.getRaisonSociale());
            this.domaineChoix.setSelectedItem(editedProspect.getDomaine());
            this.numeroRue.setText(String.valueOf(editedProspect.adresse.getNumeroRue()));
            this.nomRue.setText(editedProspect.adresse.getNomRue());
            this.codePostal.setText(editedProspect.adresse.getCodePostal());
            this.ville.setText(editedProspect.adresse.getVille());
            this.numeroTelephone.setText(editedProspect.getTelephone());
            this.adresseMail.setText(editedProspect.getAdresseMail());
            this.dateProspection.setText(new SimpleDateFormat("dd/MM/yyyy").format((Date)
                    (editedProspect.getDateProspection())));
            this.prospectInteresse.setSelectedItem(editedProspect.getProspectInteresse());
            this.commentaires1.setText(editedProspect.getCommentaires());
        }
        this.retourAccueil.addActionListener(e -> {
            this.dispose();
            this.parent.setVisible(true);
        });
        this.suppression.addActionListener(e -> {
            if(clientOuProspect.getText().equals("Clients")){
                int reponse = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer ce client ?",
                        "Suppression", JOptionPane.YES_NO_CANCEL_OPTION);
                if (reponse == JOptionPane.YES_OPTION){
                    Client.getClientList().remove(this.editedClient);
                    this.dispose();
                    this.parent.updateListeClient();
                    this.parent.setVisible(true);
                }
            }
            if(clientOuProspect.getText().equals("Prospects")){
                int reponse = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer ce prospect ?",
                        "Suppression", JOptionPane.YES_NO_CANCEL_OPTION);
                if (reponse == JOptionPane.YES_OPTION){
                    Prospect.getProspectList().remove(this.editedProspect);
                    this.dispose();
                    this.parent.updateListeProspect();   
                    this.parent.setVisible(true);
                }
            }

        });
        this.validerForm.addActionListener(e -> {
            if (choixClientPanel.isVisible()){
                createNewClient();
            }
            if (choixProspectPanel.isVisible()) {
                createNewProspect();
            }
        });


    }

    public Date getDate() {
        return date;
    }

    public JPanel getChoixClientPanel() {
        return choixClientPanel;
    }

    public void setChoixClientPanel(JPanel choixClientPanel) {
        this.choixClientPanel = choixClientPanel;
    }

    public JPanel getChoixProspectPanel() {
        return choixProspectPanel;
    }

    public void setChoixProspectPanel(JPanel choixProspectPanel) {
        this.choixProspectPanel = choixProspectPanel;
    }


    public void setValidationPanel(JPanel validationPanel) {
        this.validationPanel = validationPanel;
    }

    public JPanel getSupprimerPanel() {
        return supprimerPanel;
    }

    public void setSupprimerPanel(JPanel supprimerPanel) {
        this.supprimerPanel = supprimerPanel;
    }

    public void layoutComponents(){
        GridBagLayout layoutgrille = new GridBagLayout();
        this.setLayout(layoutgrille);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.weightx = 1;
        gbc2.weighty = 1;
        gbc2.anchor = GridBagConstraints.PAGE_START;

        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        this.add(infos, gbc2);
        gbc2.gridx = 1;
        gbc2.gridy = 2;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        this.add(choixClientPanel, gbc2);
        gbc2.gridx = 1;
        gbc2.gridy = 2;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        this.add(choixProspectPanel, gbc2);
        gbc2.gridx = 1;
        gbc2.gridy = 3;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        this.add(validationPanel, gbc2);
        gbc2.gridx = 1;
        gbc2.gridy = 3;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        this.add(supprimerPanel, gbc2);
        choixProspectPanel.setVisible(false);
        choixClientPanel.setVisible(false);
        validationPanel.setVisible(false);
        supprimerPanel.setVisible(false);


        GridBagLayout layoutAccueil = new GridBagLayout();
        infos.setLayout(layoutAccueil);
        choixClientPanel.setLayout(layoutAccueil);
        choixProspectPanel.setLayout(layoutAccueil);
        validationPanel.setLayout(layoutAccueil);
        supprimerPanel.setLayout(layoutAccueil);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(retourAccueil, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        infos.add(clientOuProspect, gbc);


        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(creationOuModif, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(identifiantLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(identifiant, gbc);
        identifiant.setVisible(true);
        identifiant.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(raisonSocialeLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(raisonSociale, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(domaineLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(domaineChoix, gbc);
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(adresseLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(numeroRueLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(numeroRue, gbc);
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(nomRueLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 11;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(nomRue, gbc);
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.gridwidth =1;
        gbc.gridheight = 1;
        infos.add(codePostalLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(codePostal, gbc);
        gbc.gridx = 1;
        gbc.gridy = 13;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(villeLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 13;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(ville, gbc);
        gbc.gridx = 1;
        gbc.gridy = 14;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(numeroTelephoneLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 14;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(numeroTelephone,gbc);
        gbc.gridx = 1;
        gbc.gridy = 15;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(adresseMailLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 15;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        infos.add(adresseMail, gbc);



        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixClientPanel.add(chiffreAffairesLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixClientPanel.add(chiffreAffaires, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixClientPanel.add(nombreEmployesLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixClientPanel.add(nombreEmployes, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixClientPanel.add(commentairesLabel1, gbc);

        commentaires1.setLineWrap(true);
        commentaires1.setWrapStyleWord(true);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixClientPanel.add(scrollPane, gbc);


        gbc.gridx = 1;
        gbc.gridy = 16;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixProspectPanel.add(dateProspectionLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 16;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixProspectPanel.add(dateProspection, gbc);
        gbc.gridx = 1;
        gbc.gridy = 17;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixProspectPanel.add(prospectInteresseLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 17;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixProspectPanel.add(prospectInteresse, gbc);
        gbc.gridx = 1;
        gbc.gridy = 18;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixProspectPanel.add(commentairesLabel2, gbc);

        commentaires2.setLineWrap(true);
        commentaires2.setWrapStyleWord(true);
        gbc.gridx = 2;
        gbc.gridy = 18;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixProspectPanel.add(scrollPane2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        validationPanel.add(validerForm, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        supprimerPanel.add(suppression, gbc);

        this.pack();

    }

    /**
     * Fonction qui permet de créer un nouveau client dans le formulaire et de l'ajouter à la liste des prospects.
     * Chaque element de client est entouré de try/catch pour gérer les exceptions
     */
    public void createNewClient() {
        Client client;
        if (this.editedClient == null) {
            client = new Client();
        } else {
            client = this.editedClient;
        }
        boolean clientIsValid = true;
        client.setIdentifiant(Integer.parseInt(this.identifiant.getText()));
        try {
            client.setRaisonSociale(this.raisonSociale.getText());
            this.raisonSocialeLabel.setForeground(Color.BLACK);
        } catch (Exception a) {
            clientIsValid = false;
            this.raisonSocialeLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, a.getMessage());
        }

        client.setDomaine((DomaineSociete) this.domaineChoix.getSelectedItem());

        Adresse adresse = new Adresse();
        boolean adresseIsValid = true;
        try {
            adresse.setNumeroRue(Integer.parseInt(this.numeroRue.getText()));
            this.numeroRueLabel.setForeground(Color.BLACK);
        } catch (SocieteException a){
            adresseIsValid = false;
            this.numeroRueLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, a.getMessage());
        } catch (NumberFormatException n) {
            adresseIsValid = false;
            this.numeroRueLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, "Le numéro de rue doit être renseigné" +
                    " et écrit en chiffre");
        }
        try {
            adresse.setNomRue(this.nomRue.getText());
            this.nomRueLabel.setForeground(Color.BLACK);
        } catch (Exception b) {
            adresseIsValid = false;
            this.nomRueLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, b.getMessage());
        }
        try {
            adresse.setCodePostal(this.codePostal.getText());
            this.codePostalLabel.setForeground(Color.BLACK);
        } catch (Exception b) {
            adresseIsValid = false;
            this.codePostalLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, b.getMessage());
        }
        try {
            adresse.setVille(this.ville.getText());
            this.villeLabel.setForeground(Color.BLACK);
        } catch (Exception b) {
            adresseIsValid = false;
            this.villeLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, b.getMessage());
        }

        //ajout de l'adresse au client
        if (adresseIsValid) {
            try {
                client.setAdresse(adresse);
            } catch (Exception b) {
                clientIsValid = false;
                JOptionPane.showMessageDialog(this, b.getMessage());
            }
        } else {
            clientIsValid = false;
        }

        try {
            client.setTelephone(this.numeroTelephone.getText());
            this.numeroTelephoneLabel.setForeground(Color.BLACK);
        } catch (Exception a) {
            clientIsValid = false;
            this.numeroTelephoneLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, a.getMessage());
        }
        try {
            client.setAdresseMail(this.adresseMail.getText());
            this.adresseMailLabel.setForeground(Color.BLACK);
        } catch (Exception a) {
            clientIsValid = false;
            this.adresseMailLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, a.getMessage());
        }
        try {
            client.setChiffreAffairesClient(Integer.parseInt(this.chiffreAffaires.getText()),
                    Integer.parseInt(this.nombreEmployes.getText()));
            this.chiffreAffairesLabel.setForeground(Color.BLACK);
        } catch (SocieteException s) {
            clientIsValid = false;
            this.chiffreAffairesLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, s.getMessage());
        } catch (ArithmeticException b) {
            clientIsValid = false;
            this.chiffreAffairesLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, "Le chiffre d'affaires doit être différent de 0");
        } catch (NumberFormatException n) {
            clientIsValid = false;
            this.chiffreAffairesLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, "Le chiffre d'affaires doit être renseigné");
        }

        try {
            client.setNombreEmployes(Integer.parseInt(this.nombreEmployes.getText()));
            this.nombreEmployesLabel.setForeground(Color.BLACK);
        } catch (SocieteException n) {
            clientIsValid = false;
            this.nombreEmployesLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, n.getMessage());
        } catch (NumberFormatException n) {
            clientIsValid = false;
            this.nombreEmployesLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, "Le nombre d'employés doit être renseigné");
        }

        client.setCommentaires(this.commentaires1.getText());

        if (clientIsValid) {
            Client.getClientList().add(client);
            this.dispose();
            this.parent.updateListeClient();
            this.parent.setVisible(true);
        }
    }

    /**
     * Fonction qui permet de créer un nouveau prospect dans le formulaire et de l'ajouter à la liste des prospects.
     * Chaque element de client est entouré de try/catch pour gérer les exceptions
     */
    public void createNewProspect() {
            /// création de prospect
            Prospect prospect;
            if (this.editedProspect == null){
                prospect = new Prospect();
            } else {
                prospect = this.editedProspect;
            }
            boolean prospectIsValid = true;

            prospect.setIdentifiant(Integer.parseInt(this.identifiant.getText()));
            try{
                prospect.setRaisonSociale(this.raisonSociale.getText());
                this.raisonSocialeLabel.setForeground(Color.BLACK);
            } catch (Exception a) {
                prospectIsValid = false;
                this.raisonSocialeLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this, a.getMessage());
            }

            prospect.setDomaine((DomaineSociete) this.domaineChoix.getSelectedItem());

            Adresse adresse = new Adresse();
            boolean adresseIsValid = true;
            try {
                adresse.setNumeroRue(Integer.parseInt(this.numeroRue.getText()));
                this.numeroRueLabel.setForeground(Color.BLACK);
            } catch (SocieteException a){
                adresseIsValid = false;
                this.numeroRueLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this, a.getMessage());
            } catch (NumberFormatException n) {
                adresseIsValid = false;
                this.numeroRueLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this, "Le numéro de rue doit être renseigné" +
                        " et écrit en chiffres");
            }
            try {
                adresse.setNomRue(this.nomRue.getText());
                this.nomRueLabel.setForeground(Color.BLACK);
            } catch (Exception b) {
                adresseIsValid = false;
                this.nomRueLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this, b.getMessage());
            }
            try {
                adresse.setCodePostal(this.codePostal.getText());
                this.codePostalLabel.setForeground(Color.BLACK);
            } catch (Exception b) {
                adresseIsValid = false;
                this.codePostalLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this, b.getMessage());
            }
            try {
                adresse.setVille(this.ville.getText());
                this.villeLabel.setForeground(Color.BLACK);
            } catch (Exception b) {
                adresseIsValid = false;
                this.villeLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this, b.getMessage());
            }
            if (adresseIsValid) {
                try {
                    prospect.setAdresse(adresse);
                } catch (Exception b) {
                    prospectIsValid = false;
                    JOptionPane.showMessageDialog(this, b.getMessage());
                }
            } else {
                prospectIsValid = false;
            }

            try{
                prospect.setTelephone(this.numeroTelephone.getText());
                this.numeroTelephoneLabel.setForeground(Color.BLACK);
            } catch (Exception a){
                prospectIsValid = false;
                this.numeroTelephoneLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this, a.getMessage());
            }
            try{
                prospect.setAdresseMail(this.adresseMail.getText());
                this.adresseMailLabel.setForeground(Color.BLACK);
            } catch (Exception a){
                prospectIsValid = false;
                this.adresseMailLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this, a.getMessage());
            }
            try{
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                date = df.parse(this.dateProspection.getText());
                prospect.setDateProspection(date);
                this.adresseMailLabel.setForeground(Color.BLACK);
            } catch (ParseException p){
                prospectIsValid = false;
                this.dateProspectionLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this, "La date doit être renseignée," +
                        "et de format type jj/mm/aaaa");
            } catch (SocieteException a){
                prospectIsValid = false;
                this.dateProspectionLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this, a.getMessage());
            }

            prospect.setProspectInteresse((String) this.prospectInteresse.getSelectedItem());

            prospect.setCommentaires(this.commentaires2.getText());

            if (prospectIsValid){
                Prospect.getProspectList().add(prospect);
                this.dispose();
                this.parent.updateListeProspect();
                this.parent.setVisible(true);
            }
        }

    }
