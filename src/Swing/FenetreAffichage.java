package Swing;

import Entities.Client;
import Entities.Prospect;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FenetreAffichage extends JFrame {

    //variables
    private JPanel hautPage = new JPanel();
    private JButton retourAccueil = new JButton("Retour accueil");
    private JLabel affichageLabel = new JLabel("Liste des clients/prospects");

    //panel jtable client
    private JPanel tableauClientPanel = new JPanel();
    private String[] colonneTitresClient = {"Identifiant", "Raison sociale", "Domaine", "Numéro de rue", "Nom de rue",
            "Code postal","Ville", "Numéro de telephone", "Adresse mail",
            "Chiffre d'affaires", "Nombre d'employes", "Commentaires"};

    private DefaultTableModel tableauModel1 = new DefaultTableModel(colonneTitresClient, 0);
    private JTable affichageTab1 = new JTable();

    //panel jtable prospect
    private JPanel tableauProspectPanel = new JPanel();
    private String[] colonneTitresProspect = {"Identifiant", "Raison sociale", "Domaine","Numéro de rue", "Nom de rue",
            "Code postal","Ville", "Numéro de telephone", "Adresse mail",
            "Date de prospection", "Prospect interessé", "Commentaires"};

    private DefaultTableModel tableauModel2 = new DefaultTableModel(colonneTitresProspect, 0);
    private JTable affichageTab2 = new JTable();

    private FenetrePrincipale parent = null;
    private Prospect prospect;
    private Client client;

    //constructeur

    /**
     * constructeur de frame FenetreAffichage.
     * @param titre : titre de la frame (jlabel).
     * @param clientPanel : jpanel contenant la jtable client.
     * @param prospectPanel : jpanel contenant la jtable prospect.
     * @param parent : Jframe précédente, ici FenetrePrincipale
     * @param client : permet d'ajouter les clients modifiés à la jtable
     */
    public FenetreAffichage(String titre, boolean clientPanel,
                            boolean prospectPanel, FenetrePrincipale parent, Client client){

        initComponents();
        layoutComponents();
        tableauClientPanel.setVisible(clientPanel);
        tableauProspectPanel.setVisible(prospectPanel);
        affichageLabel.setText(titre);
        this.client = client;
        this.parent = parent;
    }

    /**
     * constructeur de frame FenetreAffichage.
     * @param titre : titre de la frame (jlabel).
     * @param clientPanel : jpanel contenant la jtable client.
     * @param prospectPanel : jpanel contenant la jtable prospect.
     * @param parent : Jframe précédente, ici FenetrePrincipale
     * @param prospect : permet d'ajouter les prospects modifiés à la jtable
     */
    public FenetreAffichage(String titre, boolean clientPanel,
                            boolean prospectPanel, FenetrePrincipale parent, Prospect prospect){

        initComponents();
        layoutComponents();
        tableauClientPanel.setVisible(clientPanel);
        tableauProspectPanel.setVisible(prospectPanel);
        affichageLabel.setText(titre);
        this.parent = parent;
        this.prospect = prospect;
    }
    public DefaultTableModel getTableauModel1() {
        return tableauModel1;
    }

    public DefaultTableModel getTableauModel2() {
        return tableauModel2;
    }

    public JTable getAffichageTab1() {
        return affichageTab1;
    }

    public void initComponents(){
    //boucle pour ajouter les elements de la liste à la Jtable
        for (int i = 0; i < Client.getClientList().size(); i++) {
                tableauModel1.addRow(new Object[]{
                        Client.getClientList().get(i).getIdentifiant(),
                        Client.getClientList().get(i).getRaisonSociale(),
                        Client.getClientList().get(i).getDomaine(),
                        Client.getClientList().get(i).adresse.getNumeroRue(),
                        Client.getClientList().get(i).adresse.getNomRue(),
                        Client.getClientList().get(i).adresse.getCodePostal(),
                        Client.getClientList().get(i).adresse.getVille(),
                        Client.getClientList().get(i).getTelephone(),
                        Client.getClientList().get(i).getAdresseMail(),
                        Client.getClientList().get(i).getChiffreAffairesClient(),
                        Client.getClientList().get(i).getNombreEmployes(),
                        Client.getClientList().get(i).getCommentaires()}

                );
            }
        affichageTab1.setPreferredScrollableViewportSize(new Dimension(900, 200));
        affichageTab1.setModel(tableauModel1);


        for (int i = 0; i < Prospect.getProspectList().size(); i++) {

            tableauModel2.addRow(new Object[]{
                    Prospect.getProspectList().get(i).getIdentifiant(),
                    Prospect.getProspectList().get(i).getRaisonSociale(),
                    Prospect.getProspectList().get(i).getDomaine(),
                    Prospect.getProspectList().get(i).adresse.getNumeroRue(),
                    Prospect.getProspectList().get(i).adresse.getNomRue(),
                    Prospect.getProspectList().get(i).adresse.getCodePostal(),
                    Prospect.getProspectList().get(i).adresse.getVille(),
                    Prospect.getProspectList().get(i).getTelephone(),
                    Prospect.getProspectList().get(i).getAdresseMail(),
                    new SimpleDateFormat("dd/MM/yyyy").format((Date)
                            (Prospect.getProspectList().get(i).getDateProspection())),
                    Prospect.getProspectList().get(i).getProspectInteresse(),
                    Prospect.getProspectList().get(i).getCommentaires()}
            );
        }
        affichageTab2.setPreferredScrollableViewportSize(new Dimension(900, 200));
        affichageTab2.setModel(tableauModel2);



        this.retourAccueil.addActionListener(e -> {
            this.dispose();
            this.parent.setVisible(true); // rend FenetrePrincipale visible à nouveau
        });


    }
    // agencement des différents boutons et panels
    public void layoutComponents(){
        this.setLayout(new BorderLayout());
        this.add(hautPage, BorderLayout.NORTH);
        this.add(tableauClientPanel, BorderLayout.CENTER);
        this.add(tableauProspectPanel, BorderLayout.SOUTH);
        tableauClientPanel.setVisible(false);
        tableauProspectPanel.setVisible(false);

        hautPage.setLayout(new BorderLayout());
        hautPage.add(retourAccueil, BorderLayout.LINE_START);
        hautPage.add(affichageLabel, BorderLayout.CENTER);

        tableauClientPanel.setLayout(new BorderLayout());
        tableauClientPanel.add(new JScrollPane(affichageTab1), BorderLayout.CENTER);

        tableauProspectPanel.setLayout(new BorderLayout());
        tableauProspectPanel.add(new JScrollPane(affichageTab2), BorderLayout.CENTER);

    }
}
