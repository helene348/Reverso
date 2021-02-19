package Swing;

import Entities.Client;
import Entities.Prospect;
import Entities.Societe;

import javax.swing.*;
import java.awt.*;

public class FenetrePrincipale extends JFrame {
    //Panel accueil
    private JPanel accueil = new JPanel();
    private JLabel accueilLabel = new JLabel("Accueil");
    private JLabel choixSocieteLabel = new JLabel("Choix de gestion : ");
    private JRadioButton clients = new JRadioButton("Clients");
    private JRadioButton prospects = new JRadioButton("Prospects");
    private ButtonGroup choixSocieteButton = new ButtonGroup();

    //panel choix méthode
    private JPanel choixMethode = new JPanel();
    private JLabel choixClients = new JLabel("Clients");
    private JLabel choixProspects = new JLabel("Prospects");

    private JButton creation = new JButton("Création");
    private JButton modification = new JButton("Modification");
    private JButton affichage = new JButton("Affichage");
    private JButton suppression = new JButton("Suppression");

    //panel jpanel et jcombobox prospect
    private JPanel listeDeroulanteClient = new JPanel();
    private JComboBox<Client> raisonSocialeChoixModifClient = new JComboBox<Client>();

    //jpanel et jcombobox prospect
    private JPanel listeDeroulanteProspect = new JPanel();
    private JComboBox<Prospect> raisonSocialeChoixModifProspect = new JComboBox<Prospect>();

    //jpanel valider
    private JPanel validerPanel = new JPanel();
    private JButton valider = new JButton("Valider");

    private String titre;
    private String methode;
    private int identif = 0;
    private boolean panelClient;
    private boolean panelProspect;
    private boolean panelValidation;
    private boolean panelSuppression;
    private Prospect selectedProspect;
    private Client selectedClient;
    //panel fenetreaffichage
    private boolean clientPanel;
    private boolean prospectPanel;
   //constructeurs
    public FenetrePrincipale(){
        this.initComponents();
        this.layoutComponents();

    }


    public void initComponents(){
        this.updateListeClient();
        this.updateListeProspect();

        this.clients.addActionListener(e -> {
            choixMethode.setVisible(true);
            choixClients.setVisible(true);
            raisonSocialeChoixModifProspect.setVisible(false);
            raisonSocialeChoixModifClient.setVisible(false);
            choixProspects.setVisible(false);
            modification.setEnabled(true);
            affichage.setEnabled(true);
            creation.setEnabled(true);
            suppression.setEnabled(true);
            this.pack();
        });
        this.prospects.addActionListener(e -> {
            choixMethode.setVisible(true);
            choixProspects.setVisible(true);
            raisonSocialeChoixModifClient.setVisible(false);
            raisonSocialeChoixModifProspect.setVisible(false);
            choixClients.setVisible(false);
            modification.setEnabled(true);
            affichage.setEnabled(true);
            creation.setEnabled(true);
            suppression.setEnabled(true);
            this.pack();
        });
        this.creation.addActionListener(e -> {
            this.modification.setEnabled(false);
            this.affichage.setEnabled(false);
            this.suppression.setEnabled(false);
            this.validerPanel.setVisible(true);

            this.pack();
        });
        this.modification.addActionListener(e -> {
            creation.setEnabled(false);
            affichage.setEnabled(false);
            suppression.setEnabled(false);
            if(choixClients.isVisible()){
                listeDeroulanteClient.setVisible(true);
                raisonSocialeChoixModifClient.setVisible(true);
                raisonSocialeChoixModifProspect.setVisible(false);
            }
            if (choixProspects.isVisible()){
                listeDeroulanteProspect.setVisible(true);
                raisonSocialeChoixModifProspect.setVisible(true);
                raisonSocialeChoixModifClient.setVisible(false);
            }
            validerPanel.setVisible(true);
            this.pack();
        });
        this.affichage.addActionListener(e -> {
                if (choixClients.isVisible()){
                    titre = choixClients.getText();
                    setClientPanel(true);
                    setProspectPanel(false);
                    FenetreAffichage frame = new FenetreAffichage(titre, clientPanel, prospectPanel,
                            this, selectedClient);
                    frame.setVisible(true);

                    frame.setTitle("Affichage de " + titre);
                    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.pack();
                    this.setVisible(false);
                }
                if (choixProspects.isVisible()){
                    titre = choixProspects.getText();
                    setClientPanel(false);
                    setProspectPanel(true);
                    FenetreAffichage frame = new FenetreAffichage(titre, clientPanel, prospectPanel,
                            this, selectedProspect);
                    frame.setVisible(true);

                    frame.setTitle("Affichage de " + titre);
                    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.pack();
                    this.setVisible(false);
                }

        });
        this.suppression.addActionListener(e -> {
            modification.setEnabled(false);
            affichage.setEnabled(false);
            creation.setEnabled(false);
            if(choixClients.isVisible()){
                listeDeroulanteClient.setVisible(true);
                raisonSocialeChoixModifClient.setVisible(true);
                raisonSocialeChoixModifProspect.setVisible(false);
            }
            if (choixProspects.isVisible()){
                listeDeroulanteProspect.setVisible(true);
                raisonSocialeChoixModifProspect.setVisible(true);
                raisonSocialeChoixModifClient.setVisible(false);
            }
            validerPanel.setVisible(true);
            this.pack();
        });
        this.valider.addActionListener(e -> {
            if (choixClients.isVisible() && creation.isEnabled()){
                createClient();
            }
            if (choixClients.isVisible() && modification.isEnabled()){
                editClient();
            }
            if (choixClients.isVisible() && suppression.isEnabled()){
                supprimerClient();
            }
            if (choixProspects.isVisible() && creation.isEnabled()){
                createProspect();
            }
            if (choixProspects.isVisible() && modification.isEnabled()){
                editProspect();
            }
            if (choixProspects.isVisible() && suppression.isEnabled()) {
                supprimerProspect();

            }
        });

    }
    //autres méthodes

    /**
     * Mise à jour de la liste des clients. Tous les éléments de la liste sont supprimés, puis ajoutés à chaque appel
     * de la méthode.
     */
    public void updateListeClient(){
        this.raisonSocialeChoixModifClient.removeAllItems();

        for (int i = 0; i < Client.getClientList().size(); i++) {
            this.raisonSocialeChoixModifClient.addItem(Client.getClientList().get(i));
        }
    }

    /**
     * Mise à jour de la liste des prospects. Tous les éléments de la liste sont supprimés, puis ajoutés à chaque appel
     * de la méthode.
     */
    public void updateListeProspect(){
        this.raisonSocialeChoixModifProspect.removeAllItems();

        for (int i = 0; i < Prospect.getProspectList().size(); i++) {
            this.raisonSocialeChoixModifProspect.addItem(Prospect.getProspectList().get(i));
        }

    }

    /**
     * Méthode pour créer une JFrame FenetreCreationModifSupp, avec les panels et boutons correspondants à la création
     * d'un client.
     */
    public void createClient(){
        this.setVisible(false);
        titre = choixClients.getText();
        methode = creation.getText();
        setPanelClient(true);
        setPanelProspect(false);
        setPanelValidation(true);
        setPanelSuppression(false);
        identif++;

        FenetreCreationModifSupp formFrame = new FenetreCreationModifSupp(methode, titre, identif, panelClient,
                panelProspect, panelValidation, panelSuppression, this);
        formFrame.setVisible(true);
        formFrame.setTitle(methode + " de " + titre);
        formFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        formFrame.setLocationRelativeTo(null);
        formFrame.setVisible(true);
        formFrame.pack();
        this.setVisible(false);
    }

    /**
     * Méthode pour créer une Frame FenetreCreationModifSupp, avec les panels et boutons correspondants à la création
     * d'un prospect.
     */
    public void createProspect(){
        this.setVisible(false);
        titre = choixProspects.getText();
        methode = creation.getText();
        setPanelProspect(true);
        setPanelClient(false);
        setPanelValidation(true);
        setPanelSuppression(false);
        identif++;

        FenetreCreationModifSupp formFrame = new FenetreCreationModifSupp(methode, titre, identif, panelClient,
                panelProspect, panelValidation, panelSuppression, this);
        formFrame.setVisible(true);
        formFrame.setTitle(methode + " de " + titre);
        formFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        formFrame.setLocationRelativeTo(null);
        formFrame.setVisible(true);
        formFrame.pack();
        this.setVisible(false);
    }

    /**
     * Méthode pour créer une Frame FenetreCreationModifSupp, avec les panels et boutons correspondants à l'édition
     * d'un client.
     */
    public void editClient() {
        this.setVisible(false);
        titre = choixClients.getText();
        methode = modification.getText();
        setPanelClient(true);
        setPanelProspect(false);
        setPanelValidation(true);
        setPanelSuppression(false);
        selectedClient = (Client) this.raisonSocialeChoixModifClient.getSelectedItem();

        FenetreCreationModifSupp formFrame = new FenetreCreationModifSupp(methode, titre, identif, panelClient,
                panelProspect, panelValidation, panelSuppression, this, selectedClient);
        formFrame.setVisible(true);
        formFrame.setTitle(methode + " de " + titre);
        formFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        formFrame.setLocationRelativeTo(null);
        formFrame.setVisible(true);
        formFrame.pack();
        this.setVisible(false);
        Client.getClientList().remove(selectedClient);
    }

    /**
     * Méthode pour créer une Frame FenetreCreationModifSupp, avec les panels et boutons correspondants à l'édition
     * d'un prospect.
     */
    public void editProspect(){
        this.setVisible(false);
        titre = choixProspects.getText();
        methode = modification.getText();
        setPanelClient(false);
        setPanelProspect(true);
        setPanelValidation(true);
        setPanelSuppression(false);
        selectedProspect = (Prospect) this.raisonSocialeChoixModifProspect.getSelectedItem();

        FenetreCreationModifSupp formFrame = new FenetreCreationModifSupp(methode, titre, identif, panelClient,
                panelProspect, panelValidation, panelSuppression, this, selectedProspect);
        formFrame.setVisible(true);
        formFrame.setTitle(methode + " de " + titre);
        formFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        formFrame.setLocationRelativeTo(null);
        formFrame.setVisible(true);
        formFrame.pack();
        this.setVisible(false);
        Prospect.getProspectList().remove(selectedProspect);
    }

    /**
     * Méthode pour créer une Frame FenetreCreationModifSupp, avec les panels et boutons correspondants à la suppression
     * d'un client.
     */
    public void supprimerClient(){
        this.setVisible(false);
        titre = choixClients.getText();
        methode = suppression.getText();
        setPanelClient(true);
        setPanelProspect(false);
        setPanelSuppression(true);
        setPanelValidation(false);

        selectedClient = (Client) this.raisonSocialeChoixModifClient.getSelectedItem();

        FenetreCreationModifSupp formFrame = new FenetreCreationModifSupp(methode, titre, identif, panelClient,
                panelProspect, panelValidation, panelSuppression, this, selectedClient);
        formFrame.setVisible(true);
        formFrame.setTitle(methode + " de " + titre);
        formFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        formFrame.setLocationRelativeTo(null);
        formFrame.setVisible(true);
        formFrame.pack();
        this.setVisible(false);
    }

    /**
     * Méthode pour créer une Frame FenetreCreationModifSupp, avec les panels et boutons correspondants à la suppression
     * d'un prospect.
     */
    public void supprimerProspect(){
        this.setVisible(false);
        titre = choixProspects.getText();
        methode = suppression.getText();
        setPanelProspect(true);
        setPanelClient(false);
        setPanelSuppression(true);
        setPanelValidation(false);
        selectedProspect = (Prospect) this.raisonSocialeChoixModifProspect.getSelectedItem();

        FenetreCreationModifSupp formFrame = new FenetreCreationModifSupp(methode, titre, identif, panelClient,
                panelProspect, panelValidation, panelSuppression, this, selectedProspect);
        formFrame.setVisible(true);
        formFrame.setTitle(methode + " de " + titre);
        formFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        formFrame.setLocationRelativeTo(null);
        formFrame.setVisible(true);
        formFrame.pack();
        this.setVisible(false);
    }
    public void setPanelClient(boolean panelClient) {
        this.panelClient = panelClient;
    }

    public void setPanelProspect(boolean panelProspect) {
        this.panelProspect = panelProspect;
    }

    public void setPanelValidation(boolean panelValidation) {
        this.panelValidation = panelValidation;
    }

    public void setPanelSuppression(boolean panelSuppression) {
        this.panelSuppression = panelSuppression;
    }

    public void setClientPanel(boolean clientPanel) {
        this.clientPanel = clientPanel;
    }

    public void setProspectPanel(boolean prospectPanel) {
        this.prospectPanel = prospectPanel;
    }

    //emplacements des boutons (layout)
    public void layoutComponents(){
        this.setLayout(new GridLayout(5,1));
        this.add(accueil);
        this.add(choixMethode);
        this.add(listeDeroulanteClient);
        this.add(listeDeroulanteProspect);
        this.add(validerPanel);

        GridBagLayout gbLayout = new GridBagLayout();
        accueil.setLayout(gbLayout);
        choixMethode.setLayout(gbLayout);
        choixMethode.setVisible(false);
        listeDeroulanteClient.setLayout(gbLayout);
        listeDeroulanteProspect.setVisible(false);
        validerPanel.setLayout(gbLayout);
        validerPanel.setVisible(false);

        //layout panel accueil
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        accueil.add(accueilLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        accueil.add(choixSocieteLabel, gbc);

        choixSocieteButton.add(clients);
        choixSocieteButton.add(prospects);

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        accueil.add(clients, gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        accueil.add(prospects, gbc);

        //panel choixw méthode
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixMethode.add(choixClients, gbc);
        choixClients.setVisible(false);


        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixMethode.add(choixProspects, gbc);
        choixProspects.setVisible(false);

        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixMethode.add(creation,gbc);


        gbc.gridx = 3;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixMethode.add(affichage,gbc);


        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixMethode.add(modification,gbc);

        gbc.gridx = 3;
        gbc.gridy = 12;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        choixMethode.add(suppression,gbc);

        //panel liste déroulante

        gbc.gridx = 3;
        gbc.gridy = 13;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        listeDeroulanteClient.add(raisonSocialeChoixModifClient,gbc);
        raisonSocialeChoixModifClient.setVisible(false);

        gbc.gridx = 3;
        gbc.gridy = 13;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        listeDeroulanteProspect.add(raisonSocialeChoixModifProspect,gbc);
        raisonSocialeChoixModifProspect.setVisible(false);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        validerPanel.add(valider,gbc);

        this.pack();
    }


}
